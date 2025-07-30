package br.mil.mar.saudenaval.senpe.services.solicitacoes;


import br.mil.mar.saudenaval.senpe.config.CreateTime;
import br.mil.mar.saudenaval.senpe.config.FileUpload;
import br.mil.mar.saudenaval.senpe.config.GenerateProtocol;
import br.mil.mar.saudenaval.senpe.domain.solicitacoes.*;
import br.mil.mar.saudenaval.senpe.entities.Ressonancia;
import br.mil.mar.saudenaval.senpe.entities.Solicitacoes;
import br.mil.mar.saudenaval.senpe.entities.Tomografia;
import br.mil.mar.saudenaval.senpe.entities.User;
import br.mil.mar.saudenaval.senpe.enums.Status;
import br.mil.mar.saudenaval.senpe.repositories.RessonanciaRepository;
import br.mil.mar.saudenaval.senpe.repositories.SolicitacoesRepository;
import br.mil.mar.saudenaval.senpe.repositories.TomografiaRepository;
import br.mil.mar.saudenaval.senpe.repositories.UserRepository;
import br.mil.mar.saudenaval.senpe.services.EmailService;
import br.mil.mar.saudenaval.senpe.services.NotificationProducer;
import br.mil.mar.saudenaval.senpe.services.TokenService;
import org.json.JSONObject;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class SolicitacoesService {

    @Autowired
    private SolicitacoesRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TomografiaRepository tomografiaRepository;

    @Autowired
    private RessonanciaRepository ressonanciaRepository;

    private final RabbitTemplate rabbitTemplate;


    public SolicitacoesService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Autowired
    private NotificationProducer notificationService;

    public ResponseEntity<String> saveSolicitacaoTomografia(MultipartFile file, IdentificacaoData data, TomografiaData questionario) {
        Integer error = 0;
        String message = "";
        Solicitacoes sol = new Solicitacoes();
        Tomografia tomografia = new Tomografia();
        sol.setUserId(data.getUserID());
        sol.setOm(data.getOm());
        sol.setTel(data.getTel());
        sol.setPeso(data.getPeso());
        sol.setAltura(data.getAltura());
        sol.setDum(data.getDum());
        sol.setTipo(data.getTipo());
        sol.setUnidade(data.getLocal());
        sol.setFilename(file.getOriginalFilename());
        sol.setAghuse(data.getAghuse());



        UUID uuid = UUID.randomUUID();

        sol.setQuestionarioId(uuid.toString());
        String protocolo = GenerateProtocol.set();
        sol.setProtocolo(protocolo.toUpperCase());

        sol.setCreatedAt(CreateTime.now());
        sol.setSituacao(Status.ENVIADO.name());
        sol.setNip(data.getUsername());

        tomografia.setId(uuid.toString());
        tomografia.setFrutosDoMar(questionario.getFrutosDoMar() != null && questionario.getFrutosDoMar().equals("Sim"));
        tomografia.setDescricaoFrutosDoMar(questionario.getDescricaoFrutosDoMar());
        tomografia.setIodo(questionario.getIodo() != null && questionario.getIodo().equals("Sim"));
        tomografia.setMedicamentos(questionario.getMedicamentos() != null && questionario.getMedicamentos().equals("Sim"));
        tomografia.setDescricaoMedicamentos(questionario.getDescricaoMedicamentos());
        tomografia.setAlimentos(questionario.getAlimentos() != null && questionario.getAlimentos().equals("Sim"));
        tomografia.setDescricaoAlimentos(questionario.getDescricaoAlimentos());
        tomografia.setAsma(questionario.getAsma() != null && questionario.getAsma().equals("Sim"));
        tomografia.setCrise(questionario.getCrise());
        tomografia.setCirurgias(questionario.getCirurgias() != null && questionario.getCirurgias().equals("Sim"));
        tomografia.setOperacoes(questionario.getOperacoes());
        tomografia.setRenais(questionario.getRenais() != null && questionario.getRenais().equals("Sim"));
        tomografia.setListRenais(questionario.getListRenais());
        tomografia.setContrasteVenoso(questionario.getContrasteVenoso() != null && questionario.getContrasteVenoso().equals("Sim"));
        tomografia.setUsoVenoso(questionario.getUsoVenoso());
        tomografia.setDataUsoVenoso(questionario.getDataUsoVenoso());
        tomografia.setReacao(questionario.getReacao());
        tomografia.setTipoReacao(questionario.getTipoReacao());
        tomografia.setTavi(questionario.getTavi());
        tomografia.setConsentimento(questionario.getConsentimento());

        var possibleUser = userRepository.findById(data.getUserID());
        if(possibleUser.isPresent()){
        User user = possibleUser.get();
        String email = user.getEmail();
        String[] fullName = user.getName().split(" ");
        var firstName = Arrays.stream(fullName).findFirst();
        String name = firstName.get();

            LocalDate nascimento = user.getDataNascimento();
            LocalDate today = LocalDate.now();
            Period idade = Period.between(nascimento,today);
            sol.setPrioridade(!data.getDetalhes().equals("nodetails") || idade.getYears() < 12);
            sol.setDetalhes(data.getDetalhes().equals("nodetails") && idade.getYears() < 12 ? "Menor" : data.getDetalhes());

            try {
                repository.save(sol);
                tomografiaRepository.save(tomografia);
                sol.setFile(FileUpload.save(data.getUsername(),file));
            } catch (IOException e) {
                error = 1;
            }

           /* try {
                emailService.sendProtocolByEmail(email,name,protocolo.toUpperCase());
            } catch (MessagingException e) {
                error = 2;
            }*/
            
            String nome = name.toLowerCase();
            String rename = nome.substring(0,1).toUpperCase() + nome.substring(1);
            
            Map<String,String> json = new HashMap<>();
            json.put("title","Confirmação de Solicitação de Marcação de Exame");
            json.put("message",rename + ", recebemos o seu pedido de exame. O número de protocolo é: " + protocolo.toUpperCase()+". Em breve, entraremos em contato para confirmar a data e horário do seu exame.");

            JSONObject jsonObject = new JSONObject(json);
            String jsonString = jsonObject.toString();


            
            notificationService.createUserQueue(data.getUsername());
            String routingKey = "notification."+data.getUsername();
            rabbitTemplate.convertAndSend("notification-exchange",routingKey,jsonString);

    }

        switch (error){
            case 1-> {
                message = "Houve um problema ao tentar salvar sua solicitação de exames. Por favor, tente novamente mais tarde.";

            }
            case 2->{
                message = "Não foi possível enviar seu número de protocolo por email. Por favor, verifique o histórico de suas solicitações para confirmar sua solicitação.";
            }
            default -> message = "Solicitação enviada com sucesso!";
        }
        return error.equals(0) ? ResponseEntity.ok().body(message): ResponseEntity.badRequest().body(message);
    }






    public ResponseEntity<String> saveSolicitacaoRessonancia(MultipartFile file, IdentificacaoData data, RessonanciaData questionario) {
       Integer error = 0;
       String message = "";
        Solicitacoes sol = new Solicitacoes();
        Ressonancia ressonancia = new Ressonancia();
        sol.setUserId(data.getUserID());
        sol.setOm(data.getOm());
        sol.setTel(data.getTel());
        sol.setPeso(data.getPeso());
        sol.setAltura(data.getAltura());
        sol.setDum(data.getDum());
        sol.setTipo(data.getTipo());
        sol.setUnidade(data.getLocal());
        sol.setFilename(file.getOriginalFilename());
        sol.setAghuse(data.getAghuse());




        UUID uuid = UUID.randomUUID();

        sol.setQuestionarioId(uuid.toString());
        String protocolo = GenerateProtocol.set();
        sol.setProtocolo(protocolo.toUpperCase());

        sol.setCreatedAt(CreateTime.now());
        sol.setSituacao(Status.ENVIADO.name());
        sol.setNip(data.getUsername());
        sol.setAghuse(data.getAghuse());


        ressonancia.setId(uuid.toString());
        ressonancia.setAlergia(questionario.getAlergia() != null && questionario.getAlergia().equals("Sim"));
        ressonancia.setDescricaoAlergia(questionario.getDescricaoAlergia());
        ressonancia.setSintomasAlergia(questionario.getSintomasAlergia());
        ressonancia.setAsma(questionario.getAsma()!= null && questionario.getAsma().equals("Sim"));
        ressonancia.setDataCriseAsma(questionario.getDataCriseAsma());
        ressonancia.setDoenca(questionario.getDoenca() != null && questionario.getDoenca().equals("Sim"));
        ressonancia.setDescricaoDoencas(questionario.getDescricaoDoencas());
        ressonancia.setDor(questionario.getDor() != null && questionario.getDor().equals("Sim"));
        ressonancia.setLocalDor(questionario.getLocalDor());
        ressonancia.setLadoDor(questionario.getLadoDor());
        ressonancia.setProtese(questionario.getProtese() != null && questionario.getProtese().equals("Sim"));
        ressonancia.setDescricaoProtese(questionario.getDescricaoProtese());
        ressonancia.setMaterialProtese(questionario.getMaterialProtese());
        ressonancia.setPeniana(questionario.getPeniana() != null && questionario.getPeniana().equals("Sim"));
        ressonancia.setMaterialPeniana(questionario.getMaterialPeniana());
        ressonancia.setArma(questionario.getArma() != null && questionario.getArma().equals("Sim"));
        ressonancia.setLocalArma(questionario.getLocalArma());
        ressonancia.setImplante(questionario.getImplante() != null && questionario.getImplante().equals("Sim"));
        ressonancia.setImplanteMaterial(questionario.getImplanteMaterial());
        ressonancia.setRins(questionario.getRins() != null && questionario.getRins().equals("Sim"));
        ressonancia.setDescricaoRins(questionario.getDescricaoRins());
        ressonancia.setHemodialise(questionario.getHemodialise() != null && questionario.getHemodialise().equals("Sim"));
        ressonancia.setTatuagem(questionario.getTatuagem() != null && questionario.getTatuagem().equals("Sim"));
        ressonancia.setInsulina(questionario.getInsulina() != null && questionario.getInsulina().equals("Sim"));
        ressonancia.setInternacao(questionario.getInternacao() != null && questionario.getInternacao().equals("Sim"));
        ressonancia.setDataInternacao(questionario.getDataInternacao());
        ressonancia.setMotivoInternacao(questionario.getMotivoInternacao());
        ressonancia.setCancer(questionario.getCancer() != null && questionario.getCancer().equals("Sim"));
        ressonancia.setLocalCancer(questionario.getLocalCancer());
        ressonancia.setFamiliar(questionario.getFamiliar() != null && questionario.getFamiliar().equals("Sim"));
        ressonancia.setFamiliarCancer(questionario.getFamiliarCancer());
        ressonancia.setLocalFamiliarCancer(questionario.getLocalFamiliarCancer());
        ressonancia.setFebre(questionario.getFebre() != null && questionario.getFebre().equals("Sim"));
        ressonancia.setEmagrecimento(questionario.getEmagrecimento() != null && questionario.getEmagrecimento().equals("Sim"));
        ressonancia.setPasso(questionario.getPasso() != null && questionario.getPasso().equals("Sim"));
        ressonancia.setAneurisma(questionario.getAneurisma() != null && questionario.getAneurisma().equals("Sim"));
        ressonancia.setForca(questionario.getForca()!= null && questionario.getForca().equals("Sim"));
        ressonancia.setFormigamento(questionario.getFormigamento() != null && questionario.getFormigamento().equals("Sim"));
        ressonancia.setLocalFormigamento(questionario.getLocalFormigamento());
        ressonancia.setNeuro(questionario.getNeuro() != null && questionario.getNeuro().equals("Sim"));
        ressonancia.setVomito(questionario.getVomito()!= null);
        ressonancia.setConvulsao(questionario.getConvulsao() != null);
        ressonancia.setTontura(questionario.getTontura()!= null);
        ressonancia.setTremor(questionario.getTremor()!= null);
        ressonancia.setEnjoo(questionario.getEnjoo() != null);
        ressonancia.setFala(questionario.getFala() != null);
        ressonancia.setCabeca(questionario.getCabeca() != null );
        ressonancia.setAudicao(questionario.getAudicao()!= null);
        ressonancia.setVisual(questionario.getVisual()!= null);
        ressonancia.setCirurgia(questionario.getCirurgia() != null && questionario.getCirurgia().equals("Sim"));
        ressonancia.setDataCirurgia(questionario.getDataCirurgia());
        ressonancia.setLocalCirurgia(questionario.getLocalCirurgia());
        ressonancia.setQuimio(questionario.getQuimio() != null && questionario.getQuimio().equals("Sim"));
        ressonancia.setQtdQuimio(questionario.getQtdQuimio());
        ressonancia.setLastSession(questionario.getLastSession());
        ressonancia.setMedicamentos(questionario.getMedicamentos() != null && questionario.getMedicamentos().equals("Sim"));
        ressonancia.setDescricaoMedicamentos(questionario.getDescricaoMedicamentos());
        ressonancia.setContraste(questionario.getContraste() != null && questionario.getContraste().equals("Sim"));
        ressonancia.setDescricaoContraste(questionario.getDescricaoContraste());
        ressonancia.setDataExameContraste(questionario.getDataExameContraste());
        ressonancia.setReacaoContraste(questionario.getReacaoContraste() != null && questionario.getReacaoContraste().equals("Sim"));
        ressonancia.setSintomasReacaoContraste(questionario.getSintomasReacaoContraste());
        ressonancia.setAcidente(questionario.getAcidente() != null && questionario.getAcidente().equals("Sim"));
        ressonancia.setInfoAcidente(questionario.getInfoAcidente());
        ressonancia.setListAcidente(questionario.getListAcidente());
        ressonancia.setConsentimento(questionario.getConsentimento()!= null);




        var possibleUser = userRepository.findById(data.getUserID());
        if(possibleUser.isPresent()){
            User user = possibleUser.get();
            String email = user.getEmail();
            String[] fullName = user.getName().split(" ");
            var firstName = Arrays.stream(fullName).findFirst();
            String name = firstName.get();
            LocalDate nascimento = user.getDataNascimento();
            LocalDate today = LocalDate.now();
            Period idade = Period.between(nascimento,today);
            sol.setPrioridade(!data.getDetalhes().equals("nodetails") || idade.getYears() < 12);
            sol.setDetalhes(data.getDetalhes().equals("nodetails") && idade.getYears() < 12 ? "Menor" : data.getDetalhes());

            if(user.getSexo().equals("F")){
                ressonancia.setBiopsiaMama(questionario.getBiopsiaMama() != null && questionario.getBiopsiaMama().equals("Sim"));
                ressonancia.setDataBiopsia(questionario.getDataBiopsia());
                ressonancia.setLadoBiopsia(questionario.getLadoBiopsia());
                ressonancia.setNodulo(questionario.getNodulo() != null && questionario.getNodulo().equals("Sim"));
                ressonancia.setRadioMama(questionario.getRadioMama() != null && questionario.getRadioMama().equals("Sim"));
                ressonancia.setNumRadioMama(questionario.getNumRadioMama());
                ressonancia.setDataRadioMama(questionario.getDataRadioMama());
                ressonancia.setGestante(questionario.getGestante() != null && questionario.getGestante().equals("Sim"));
                ressonancia.setTempoGestacao(questionario.getTempoGestacao());
                ressonancia.setSangramento(questionario.getSangramento() != null && questionario.getSangramento().equals("Sim"));
                ressonancia.setDataSangramento(questionario.getDataSangramento());
                ressonancia.setReposicaoHormonal(questionario.getReposicaoHormonal() != null && questionario.getReposicaoHormonal().equals("on"));
                ressonancia.setAnticoncepcional(questionario.getAnticoncepcional() != null && questionario.getAnticoncepcional().equals("on"));
                ressonancia.setTabelinha(questionario.getTabelinha() != null && questionario.getTabelinha().equals("on"));

            }


            try {
                sol.setFile(FileUpload.save(data.getUsername(), file));
                repository.save(sol);
                ressonanciaRepository.save(ressonancia);

            } catch (IOException e) {
                error = 1;
            }

            /*try {
                emailService.sendProtocolByEmail(email, name, protocolo.toUpperCase());
            } catch (MessagingException e) {
                error = 2;
            }*/
            
            String nome = name.toLowerCase();
            String rename = nome.substring(0,1).toUpperCase() + nome.substring(1);
            
            Map<String,String> json = new HashMap<>();
            json.put("title","Confirmação de Solicitação de Marcação de Exame");           
            json.put("message",rename + ", recebemos o seu pedido de exame. O número de protocolo é: " + protocolo.toUpperCase()+". Em breve, entraremos em contato para confirmar a data e horário do seu exame.");

            JSONObject jsonObject = new JSONObject(json);
            String jsonString = jsonObject.toString();


            
            notificationService.createUserQueue(data.getUsername());
            String routingKey = "notification."+data.getUsername();
            rabbitTemplate.convertAndSend("notification-exchange",routingKey,jsonString);
            
            
        }

        switch (error){
            case 1-> {
                message = "Houve um problema ao tentar salvar sua solicitação de exames. Por favor, tente novamente mais tarde.";

            }
            case 2->{
                message = "Não foi possível enviar seu número de protocolo por email. Por favor, verifique o histórico de suas solicitações para confirmar sua solicitação.";
            }
            default -> message = "Solicitação enviada com sucesso!";
        }
        return error.equals(0) ? ResponseEntity.ok().body(message): ResponseEntity.badRequest().body(message);
    }


    public List<Solicitacoes> getHistory(String token){

        String username = tokenService.validateTokenExpiration(token);

        Optional<User> possibleUser = username.equals("Expired") ? Optional.empty() : userRepository.findUserByUsername(username);

        if (possibleUser.isPresent()){
            User user = possibleUser.get();
            String userId = user.getId();
           return  repository.findAllByUserId(userId)
                    .stream().sorted(Comparator.comparing(Solicitacoes::getCreatedAt).reversed())
                    .toList();
        }else {
           return Arrays.asList();
        }
    }

    public List<Solicitacoes> requests(){
        List<Solicitacoes> list = repository.findAll();
        return  list.stream().sorted(Comparator.comparing(Solicitacoes::getCreatedAt).reversed()).toList();
    }

    public List<Solicitacoes> filter(String tipo, Status status){
        List<Solicitacoes> list = repository.findAllByTypeOrStatus(tipo,status);
        return list.stream().sorted(Comparator.comparing(Solicitacoes::getCreatedAt).reversed()).toList();
    }

    public List<Solicitacoes> search(String protocolo){
        return repository.findByProtocol(protocolo);
    }


    public Solicitacoes getOne(String id){
        var possibleSol  = repository.findById(id);
        return possibleSol.orElse(null);
    }

    public ResponseEntity<String> schedule(MarcacaoData data, String id){
       Integer error = 0;
       String errorMessage = "";
        var possibleSol = repository.findById(id);
        if (possibleSol.isPresent()){
            Solicitacoes solicitacao = possibleSol.get();
            solicitacao.setDataExame(data.getDataExame());
            solicitacao.setExame(data.getExame());
            solicitacao.setHorario(data.getHorario());
            solicitacao.setObs(data.getObs());
            solicitacao.setOperador(data.getOperador());
            solicitacao.setSituacao(data.getStatus());
            solicitacao.setUpdatedAt(CreateTime.now());

            var possibleOperador = userRepository.findUserByUsername(data.getOperador());


            if(possibleOperador.isPresent()){
                User operator = possibleOperador.get();
                //String posto = renamePosto(operator.getPosto());
                //String nome = operator.getName();
                //String operador = posto + " " + nome;
                solicitacao.setOperador(operator.getUsername());
            }

            String userId = solicitacao.getUserId();
            var possibleUser = userRepository.findById(userId);
            User user = possibleUser.orElse(null);
            String[] fullName = user.getName().split(" ");
            var firstName = Arrays.stream(fullName).findFirst();
            String name = firstName.get();
            //String message = "";

            /*if(data.getStatus().equals(Status.AGENDADO.name())){
                message = this.scheduleToMail(name, solicitacao.getProtocolo(),data.getDataExame(),data.getHorario());
            }else{
                 message = this.messageToMail(solicitacao.getProtocolo(),name, data.getStatus());
            }*/

           /* try{
                emailService.confirmationByMail(message,user.getEmail());
            } catch (MessagingException e) {
               error = 1;
               // throw new RuntimeException(e);
            }*/


            String nome = name.toLowerCase();
            String rename = nome.substring(0,1).toUpperCase() + nome.substring(1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = data.getDataExame().format(formatter);

            Map<String,String> json = new HashMap<>();


            if(data.getStatus().equals(Status.AGENDADO.name())){
                   String mensagem =  this.scheduleToNotification(rename,solicitacao.getProtocolo(),formattedDate,data.getHorario());
                   json.put("title","Confirmação de Solicitação de Marcação de Exame");
                   json.put("message",mensagem);
                }else{
                    json.put("title","Informações sobre sua solicitação de exame");
                    String mensagem = this.messageToNotification(solicitacao.getProtocolo(),rename,data.getStatus());
                    json.put("message", mensagem);
            }

            JSONObject jsonObject = new JSONObject(json);
            String jsonString = jsonObject.toString();


            repository.save(solicitacao);


            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            MessageProperties props = new MessageProperties();
            props.setContentType(MessageProperties.CONTENT_TYPE_JSON);
            Message message = new Message(jsonString.getBytes(StandardCharsets.UTF_8),props);
            notificationService.createUserQueue(user.getUsername());
            String routingKey = "notification."+user.getUsername();
            rabbitTemplate.convertAndSend("notification-exchange",routingKey,message);

        }else{
            error = 2;

        }
        switch (error){
            case 1->{
                errorMessage = "Efetuamos o agendamento, porém não foi possível enviar a notificação ao paciente por e-mail. Solicitamos que o contato seja realizado manualmente.";
            }
            case 2->{
               errorMessage = "Não foi possível concluir este registro. Por favor, entre em contato com o administrador ou tente novamente mais tarde.";
            }
            default -> errorMessage = "Registro efetuado com sucesso";
        }

        return error.equals(0) ? ResponseEntity.ok().body(errorMessage) : ResponseEntity.badRequest().body(errorMessage);
    }

    private String renamePosto(String posto){
        String graduacao = "";
        switch (posto){
            case "1S"-> graduacao = "1ºSG";
            case "2S"-> graduacao = "2ºSG";
            case "3S"-> graduacao = "3ºSG";
            case "DEP"-> graduacao = "SC";
            default -> graduacao = posto;
        }
        return graduacao;
    }

    public ResponseEntity<Resource> download(String id){
        var possibleDoc = repository.findById(id);
        Solicitacoes solicitacao = possibleDoc.orElse(null);
        String folder = solicitacao.getNip();
        if (solicitacao != null){
            return FileUpload.download(folder,solicitacao.getFilename());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public void changeStatus(String id){
        var possibleSol = repository.findById(id);
        if(possibleSol.isPresent()){
            Solicitacoes sol = possibleSol.get();
            sol.setSituacao(Status.ANALISE.name());
            sol.setUpdatedAt(CreateTime.now());
            String userId = sol.getUserId();
            var possibleUser = userRepository.findById(userId);
            User user = possibleUser.orElse(null);
            String email = user.getEmail();
            String[] fullName = user.getName().split(" ");
            var firstName = Arrays.stream(fullName).findFirst();
            String name = firstName.get();
            repository.save(sol);
            //emailService.changeStatusByMail(email,name, sol.getProtocolo());

            String nome = name.toLowerCase();
            String rename = nome.substring(0,1).toUpperCase() + nome.substring(1);

            Map<String,String> json = new HashMap<>();
            json.put("title","Informação da situação do Pedido de Exame");
            json.put("message", rename + ", Gostaríamos informar que sua solicitação de exame, registrada sob o número de protocolo " + sol.getProtocolo() +" está em análise. Em breve, enviaremos o resultado desta verificação.");

            JSONObject jsonObject = new JSONObject(json);
            String jsonString = jsonObject.toString();



            notificationService.createUserQueue(user.getUsername());
            String routingKey = "notification."+user.getUsername();
            rabbitTemplate.convertAndSend("notification-exchange",routingKey,jsonString);


        }
    }

    public String updateFile(MultipartFile file,String id) throws IOException{
        var possibleSol = repository.findById(id);
        if(possibleSol.isPresent()){
            Solicitacoes solicitacao = possibleSol.get();
            solicitacao.setFilename(file.getOriginalFilename());
            String userId = solicitacao.getUserId();
            var possibleUser = userRepository.findById(userId);
            User user = possibleUser.orElse(null);
            solicitacao.setFile(FileUpload.save(user.getUsername(),file));
            solicitacao.setSituacao(Status.ENVIADO.name());
            repository.save(solicitacao);
            return "Registro atualizado com sucesso!";
        }else{
            return "Ocorreu um erro ao tentar atualizar este registro. Entre em contato com o Administrador para obter assistência";
        }
    }


    private String messageToMail(String protocolo, String nome, String status){
        String message = "";
        switch (status) {
            case "REMOVIDO":
                message = "<h2><span style='font-size: 14pt;'>Ol&aacute;, <strong>" + nome + "</strong>!</span></h2>\n" + "&nbsp;\n" +
                        "<p>Gostaríamos de confirmar que sua solicitação para a marcação de exame com o número de protocolo <b>" + protocolo + "</b> foi desmarcada conforme o seu pedido.</p> " +
                        "<p>Caso necessite de um novo agendamento ou tenha alguma dúvida, por favor, entre em contato conosco.</p>";

                break;
            case "PENDENCIA":
                message = "<h2><span style='font-size: 14pt;'>Ol&aacute;, <strong>" + nome + "</strong>!</span></h2>\n" + "&nbsp;\n" +
                        "<p>Gostaríamos de informá-lo(a) que sua solicitação de marcação de exame, com número de protocolo <b>" + protocolo + "</b>, apresenta pendências. Para que possamos concluir o agendamento, atualize as informações de sua solicitação.</p>" +
                        "<p>Pedimos que atualize as informações o mais rápido possível para darmos continuidade ao processo. Em caso de dúvidas, entre em contato conosco.</p>";
                break;
            case "CANCELADO":
                message = "<h2><span style='font-size: 14pt;'>Ol&aacute;, <strong>" + nome + "</strong>!</span></h2>\n" + "&nbsp;\n" +
                        "<p>Gostaríamos de informar que sua solicitação de exame, com número de protocolo <b>" + protocolo + "</b>, foi cancelada após a análise do seu pedido.</p>" +
                        "<p>Se precisar de mais alguma informação ou desejar realizar um novo agendamento, por favor, acesse o sistema novamente.</p>";
                break;
            case "NEGADO":
                message = "<h2><span style='font-size: 14pt;'>Ol&aacute;, <strong>" + nome + "</strong>!</span></h2>\n" + "&nbsp;\n" +
                        "<p>Lamentamos informar que sua solicitação de exame, com número de protocolo <b>"+ protocolo +"</b>, foi negada pelo nosso analista.</p>" +
                        "<p>Caso tenha alguma dúvida sobre a razão da negativa ou precise de mais informações, por favor, acesse nosso sistema.</p>";
                    break;
        }
        return message;
    }

    private String scheduleToMail(String nome, String protocolo, LocalDate data, String horario){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = data.format(formatter);
        String message ="<h2><span style='font-size: 14pt;'>Ol&aacute;, <strong>" + nome + "</strong>!</span></h2>\n" + "&nbsp;\n" +
                "<p>Gostaríamos de confirmar que seu exame foi agendado com sucesso. Seguem os detalhes do agendamento:" +
                "<ul>" +
                "<li><b>Número de Protocolo:</b> "+ protocolo+"</li>" +
                "<li><b>Data:</b> " + formattedDate + " </li>" +
                "<li><b>Horário:</b> "+ horario +"</li>" +
                "</ul>"+
                "<p>Por favor, certifique-se de comparecer no horário marcado e trazer os documentos necessários. Se precisar cancelar o agendamento, acesse o sistema e solicite o cancelamento.</p>";
        return message;
    }


    private String scheduleToNotification(String name, String protocolo,String data,String horario){

       return name + ", Gostaríamos de confirmar que seu exame, registrada sob o número de protocolo " + protocolo.toUpperCase() + ", foi agendado para o dia " + data + " às " + horario;
    }

    private String messageToNotification(String protocolo, String nome, String status){
        String message = "";
        switch (status) {
            case "REMOVIDO":
                message =  nome + ", Gostaríamos de confirmar que sua solicitação para a marcação de exame, registrada sob o número de protocolo " + protocolo + " foi desmarcada conforme o seu pedido.";
                break;
            case "PENDENCIA":
                message =  nome + ", Gostaríamos de informá-lo(a) que sua solicitação de marcação de exame, registrada sob o número de protocolo " + protocolo + ", apresenta pendências. Para que possamos concluir o agendamento, atualize as informações de sua solicitação.";

                break;
            case "CANCELADO":
                message =  nome + ", Gostaríamos de informar que sua solicitação de exame,registrada sob o número de protocolo " + protocolo + ", foi cancelada após a análise do seu pedido.";
                break;
            case "NEGADO":
                message =  nome + ", Lamentamos informar que sua solicitação de exame, registrada sob o número de protocolo "+ protocolo +", foi negada pelo nosso analista." ;
                break;
            case "DESMARCADO":
                message =  nome + ", Lamentamos informar que sua solicitação de exame, registrada sob o número de protocolo "+ protocolo +", foi desmarcada pela clínica responsável pelo procedimento.";
                break;
        }
        return message;
    }


    public Optional<Tomografia> getTomografia(String id){
        return tomografiaRepository.findById(id);
    }

    public Optional<Ressonancia> getRessonancia(String id){
       return ressonanciaRepository.findById(id);
    }

    public ResponseEntity<String> setResultado(ResultadoData resultado){
        var possibleSol = repository.findById(resultado.getId());
         if (possibleSol.isPresent()){
            Solicitacoes solicitacao =  possibleSol.get();
            solicitacao.setResultado(resultado.getResult());
            repository.save(solicitacao);

            String username = solicitacao.getNip();

             var possibleUser = userRepository.findUserByUsername(username);
             if (possibleUser.isPresent()){
                 User user = possibleUser.get();

                 String[] fullName = user.getName().split(" ");
                 var firstName = Arrays.stream(fullName).findFirst();
                 String name = firstName.get();

                 String nome = name.toLowerCase();
                 String rename = nome.substring(0,1).toUpperCase() + nome.substring(1);

                 Map<String,String> json = new HashMap<>();
                 json.put("title","Informação da situação do Pedido de Exame");
                 json.put("message", rename + ", Gostaríamos informar que o resultado do seu exame, registrada sob o número de protocolo " + solicitacao.getProtocolo() +", encontra-se disponível.");

                 JSONObject jsonObject = new JSONObject(json);
                 String jsonString = jsonObject.toString();

                 notificationService.createUserQueue(user.getUsername());
                 String routingKey = "notification."+user.getUsername();
                 rabbitTemplate.convertAndSend("notification-exchange",routingKey,jsonString);
             }

            return ResponseEntity.ok().body("Resultado emitido com sucesso!");
         }else{
             return ResponseEntity.badRequest().body("Não foi possível emitir este resultado. Tente novamente");
         }



    }


    public User getOperator(String username){
        var possibleUser = userRepository.findUserByUsername(username);
        return possibleUser.orElse(null);
    }

    public String userCancelSchedule(String id){
        var possibleSol  = repository.findById(id);
        if (possibleSol.isPresent()){
            Solicitacoes sol = possibleSol.get();
            sol.setSituacao(Status.REMOVIDO.name());
            LocalDate data = LocalDate.now();
            sol.setDataExame(data);
            repository.save(sol);

            String username = sol.getNip();
            var possibleUser = userRepository.findUserByUsername(username);
            if (possibleUser.isPresent()){
                User user = possibleUser.get();

                String[] fullName = user.getName().split(" ");
                var firstName = Arrays.stream(fullName).findFirst();
                String name = firstName.get();

                String nome = name.toLowerCase();
                String rename = nome.substring(0,1).toUpperCase() + nome.substring(1);

                Map<String,String> json = new HashMap<>();
                json.put("title","Informação da situação do Pedido de Exame");
                json.put("message", rename + ", Gostaríamos de confirmar que sua solicitação para a marcação de exame, registrada sob o número de protocolo " + sol.getProtocolo() + " foi desmarcada conforme o seu pedido.");

                JSONObject jsonObject = new JSONObject(json);
                String jsonString = jsonObject.toString();

                notificationService.createUserQueue(user.getUsername());
                String routingKey = "notification."+user.getUsername();
                rabbitTemplate.convertAndSend("notification-exchange",routingKey,jsonString);
            }


            return "Solicitação cancelada com sucesso!";
        }else{
            return "Ocorreu um erro e não conseguimos cancelar sua solicitação. Compareça a clínica ou entre em contato pelo telefone";
        }

    }

}

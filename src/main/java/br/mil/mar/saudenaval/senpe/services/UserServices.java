package br.mil.mar.saudenaval.senpe.services;

import br.mil.mar.saudenaval.senpe.config.CreateTime;
import br.mil.mar.saudenaval.senpe.config.DateConvert;
import br.mil.mar.saudenaval.senpe.domain.Register.RegisterData;
import br.mil.mar.saudenaval.senpe.domain.UserSsm;
import br.mil.mar.saudenaval.senpe.entities.User;
import br.mil.mar.saudenaval.senpe.enums.Perfil;
import br.mil.mar.saudenaval.senpe.repositories.UserRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserServices {

    private static final int MAX_FAILED_ATTEMPTS = 4;

    @Autowired
    EmailService emailService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    private boolean registered = false;

    public void updateFusma(MultipartFile file) throws IOException {

        String defaultPath = "/home/dsm/senpe/default/files/";
        List<User> listUsers = repository.findAll();
        List<String> listNip = new ArrayList<>();
        if (!listUsers.isEmpty()){
            listUsers.forEach(user-> listNip.add(user.getUsername()));
        }


        Path uploadPath = Paths.get(defaultPath,"fusma/");
        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(file.getOriginalFilename());
        Files.deleteIfExists(filePath);
        Files.copy(file.getInputStream(),filePath);
        String savedFile = uploadPath.toString();
        String uri = savedFile+"/"+file.getOriginalFilename();


        try (CSVReader reader = new CSVReader(new FileReader(uri))) {
            String[] line;
            int lineNumber = 0;
            int batchSize = 2500;
            List<User> users = new ArrayList<>();

            while ((line = reader.readNext()) != null) {
                lineNumber++;

                if (lineNumber == 1) {
                    continue;
                }

                if (line.length >= 9) {
                    User user = new User();
                    String titular = line[0].isEmpty() ? null : line[0];
                    user.setNipTitular(titular);
                    user.setPosto(line[6].isEmpty() ? line[1] : "DEP");
                    user.setEspecialidade(line[6].isEmpty() ? line[2] : "");
                    user.setName(line[3]);
                    user.setDataNascimento(DateConvert.parse(line[4]));
                    user.setSexo(line[5]);
                    String vinculado = line[6].isEmpty() ? null : line[6];
                    user.setNipVinculado(vinculado);
                    user.setPai(line[7]);
                    user.setMae(line[8]);
                    String username = line[6].isEmpty() ? line[0] : line[6];
                    user.setPerfil(Perfil.PACIENTE);
                    user.setLoginCount(0);
                    user.setAccountNonLocked(true);
                    user.setFailedAttempt(0);
                    user.setUsername(username);
                    if(!username.equals("07349386")){
                        users.add(user);
                    }


                    // Save batch when batchSize is reached
                    if (users.size() >= batchSize) {
                        if(listUsers.isEmpty() || listUsers.size() == 1) {
                            repository.saveAll(users);
                        }else{

                          users.forEach(item->{
                              if(listNip.contains(item.getUsername())){
                                  listNip.remove(item.getUsername());
                              }else{
                                  //System.out.println("salvar no banco");
                                  repository.save(item);
                              }
                          });


                        }
                        users.clear(); // Clear the list for the next batch
                    }
                }
            }

            // Save any remaining users in the list
            if (!users.isEmpty()) {
                users.forEach(item->{
                    if(listNip.contains(item.getUsername())){
                        listNip.remove(item.getUsername());
                    }else{
                        //System.out.println("salvar no banco");
                        repository.save(item);
                    }
                });
               // repository.saveAll(users);
            }

        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUsersSSM(MultipartFile file) throws IOException {
        List<User> listUsers = repository.findAll();
        Map<String, User> userMap = new HashMap<>();

        for (User user : listUsers) {
            userMap.put(user.getUsername(), user);
        }

        String defaultPath = "/home/dsm/senpe/default/files/";
        Path uploadPath = Paths.get(defaultPath, "fusma/");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(file.getOriginalFilename());
        Files.deleteIfExists(filePath);
        Files.copy(file.getInputStream(), filePath);
        String uri = uploadPath.toString() + "/" + file.getOriginalFilename();

        try (CSVReader reader = new CSVReader(new FileReader(uri))) {
            String[] line;
            int lineNumber = 0;
            int batchSize = 2500;
            List<User> updatedUsers = new ArrayList<>();
            List<UserSsm> users = new ArrayList<>();

            while ((line = reader.readNext()) != null) {
                lineNumber++;

                if (lineNumber == 1) {
                    continue;
                }

                if (line.length >= 5) {
                    UserSsm user = new UserSsm();
                    user.setName(line[0]);
                    user.setNip(line[1]);
                    user.setCpf(line[2]);
                    user.setEmail(line[3]);
                    user.setTel(line[4]);

                    users.add(user);

                    if (users.size() >= batchSize) {
                        processBatch(users, userMap, updatedUsers);
                        repository.saveAll(updatedUsers);
                        updatedUsers.clear();
                        users.clear(); // Clear the list for the next batch
                    }
                }
            }

            // Save any remaining users in the list
            if (!users.isEmpty()) {
                processBatch(users, userMap, updatedUsers);
                repository.saveAll(updatedUsers);
            }

        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    private void processBatch(List<UserSsm> users, Map<String, User> userMap, List<User> updatedUsers) {
        users.forEach(item -> {
            User usuario = userMap.get(item.getNip());
            if (usuario != null) {
                String cpf = usuario.getCpf() == null ? item.getCpf() : usuario.getCpf();
                String email = usuario.getEmail() == null ? item.getEmail() : usuario.getEmail();
                usuario.setEmail(email.toLowerCase());
                usuario.setCpf(cpf);
                updatedUsers.add(usuario);
            }
        });
    }


    public void saveAdminConfig(){
        var possibleuser = repository.findUserByUsername("07349386");
        if (!possibleuser.isPresent()) {
            User user = new User();
            String titular = "07349386";
            user.setNipTitular(titular);
            user.setPosto("2S");
            user.setEspecialidade("PD");
            user.setName("REGINALDO ELIAS BARBOSA");
            user.setDataNascimento(DateConvert.parse("19850310"));
            user.setSexo("M");
            user.setPai("AMILTON DOS SANTOS BARBOSA");
            user.setMae("ELIANE ELIAS DA COSTA");
            String username = "07349386";
            user.setPerfil(Perfil.ADMINISTRADOR);
            user.setUsername(username);
            user.setEmail("reginaldoeli@gmail.com");
            user.setLoginCount(0);
            user.setAccountNonLocked(true);
            String encryptedPassword = new BCryptPasswordEncoder().encode("M@rinha20");
            user.setPassword(encryptedPassword);
            repository.save(user);
            //return user;
        }
    }

    public String getUsername(String data){
      String username = data;
       if (data.length() > 8){
           var possibleUser = repository.findByCpf(data);
           if(possibleUser.isPresent()){
               User user = possibleUser.get();
               username = user.getUsername();
           }
       }
       return username;
    }

    @Transactional
    public void setUpdatelogin(String username){
        repository.setLastLogin(username, CreateTime.now());
    }
    public User findByToken(String token){
        String username = tokenService.validateToken(token);
        var possibleUser = repository.findUserByUsername(username);
        return possibleUser.orElse(null);
    }


    public ResponseEntity<String> registerUser(RegisterData data) {
        var posssibleUser = repository.findByNipAndDate(data.getNip(), data.getDataNascimento());

        if (posssibleUser.isPresent()) {
            User user = posssibleUser.get();
            String[] cpf = user.getCpf().split("");
            List<String> validador = Arrays.stream(cpf).limit(4).toList();
            String resultado = String.join("", validador);
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                if (resultado.equals(data.getCpf())) {
                    user.setEmail(data.getEmail());
                    String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
                    user.setPassword(encryptedPassword);
                    user.setPerfil(Perfil.valueOf("PACIENTE"));
                    repository.save(user);
                    registered = true;
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não conseguimos validar este usuário. Verifique seus dados e tente novamente");
                }
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este usuário já está registrado!");
            }

            return ResponseEntity.ok().body("Usuário cadastrado com sucesso!");

        } else {
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }
    }


    public String recoverPassword(String nip, LocalDate dataNascimento) throws MessagingException {
        var possibleUser = repository.findByNipAndDate(nip,dataNascimento);
        String email = "";
        if (possibleUser.isPresent()){
            User user = possibleUser.get();
            email = user.getEmail();
            String[] fullname = user.getName().split(" ");
            String firstName = fullname[0];

            String token = tokenService.generateTokenToRecoverPassword(user);

            emailService.sendInstructionsByMail(email,firstName,token);
        }

        return email;
    }

    public Optional<User> getUserToResetPassword(String token){

        String id = tokenService.validateTokenExpiration(token);
        if (id.equals("Expired")){
            return Optional.empty();
            //throw new RuntimeException("Token has Expired");
        }else{
        return repository.findById(id);
    }
}
    @Transactional
    public int updatePassword(String username, String password) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);

        return repository.updatePassword(username, encryptedPassword);
    }

    public void unlockUser(User user){
        user.setAccountNonLocked(true);
        user.setFailedAttempt(0);
        repository.save(user);
    }


    public List<User> getAllUsers() {
        return repository.findAll();
    }


    public User findByUsername(String nip){
        var possibleUser = repository.findUserByUsername(nip);
        return possibleUser.orElse(null);
    }

    @Transactional
    public String updatePerfil(String nip,String perfil){
      //  Perfil profile = Perfil.getValue(perfil);
        var possibleUser = repository.findUserByUsername(nip);
      //  System.out.println(profile);
        if (possibleUser.isPresent()){
            User user = possibleUser.get();
           // user.setPerfil(profile);
           // repository.save(user);
            repository.updateProfile(user.getUsername(), perfil);
            return "Usuário cadastrado com sucesso!";
        }else{
            return "Não foi possível alterar o perfil para este usuário.";
        }
       // return "ok";
    }

    public User getById(String id){
        var possibleUser =  repository.findById(id);
        return possibleUser.orElse(null);
    }

    public ResponseEntity<String> civilUserRegister(RegisterData data){
        var possibleUser = repository.findByCpf(data.getCpf());
        var possibleUsername = repository.findUserByUsername(data.getCpf());

        if (possibleUser.isPresent() || possibleUsername.isPresent()){
            return ResponseEntity.badRequest().body("Este usuário já está cadastrado.");
        }else{
            User user = new User();
            String titular = "00000000";
            user.setNipTitular(titular);
            user.setPosto("SC");
            user.setEspecialidade("");
            user.setName(data.getName().toUpperCase());
            user.setDataNascimento(data.getDataNascimento());
            user.setSexo(data.getSexo());
            user.setPai("");
            user.setMae("");
            String username = data.getCpf();
            user.setPerfil(Perfil.valueOf(data.getPerfil()));
            user.setUsername(username);
            user.setCpf(data.getCpf());
            user.setEmail(data.getEmail());
            user.setLoginCount(0);
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
            user.setPassword(encryptedPassword);
            repository.save(user);
            return ResponseEntity.ok().body("Usuário registrado com sucesso");
        }
    }

    public void incrementFailedAttempts(String username){
        var possibleUser = repository.findUserByUsername(username);
        if (possibleUser.isPresent()){

            User user = possibleUser.get();
            Integer failedAttempts = user.getFailedAttempt();
            user.setFailedAttempt(failedAttempts + 1);

            if (failedAttempts >= MAX_FAILED_ATTEMPTS){
                user.setAccountNonLocked(false);
            }

            repository.save(user);
        }

    }


    public void resetFaildAttempts(String username){
        var possibleUser = repository.findUserByUsername(username);
        if (possibleUser.isPresent()){
            User user = possibleUser.get();
            user.setFailedAttempt(0);
            user.setAccountNonLocked(true);
            repository.save(user);
        }

    }

    public Boolean isAccountNonLocked(String username){
        var possibleUser = repository.findUserByUsername(username);
        User user = possibleUser.orElse(null);
        return user.getAccountNonLocked();
    }



    public String updateUser(String email, String tel,String username,Boolean whatsapp){
        var possibleUser = repository.findUserByUsername(username);
        if (possibleUser.isPresent()){
            User user = possibleUser.get();
            user.setEmail(email);
            user.setTel(tel);
            user.setWhatsapp(whatsapp);
            repository.save(user);
            return "Usuário atualizado com sucesso";
        }else{
            return "Usuário não encontrado.";
        }
    }

}

package br.mil.mar.saudenaval.senpe.controller.solicitacoes;


import br.mil.mar.saudenaval.senpe.domain.solicitacoes.*;
import br.mil.mar.saudenaval.senpe.entities.Solicitacoes;
import br.mil.mar.saudenaval.senpe.entities.User;
import br.mil.mar.saudenaval.senpe.services.solicitacoes.SolicitacoesService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacoesController {

    @Autowired
    private SolicitacoesService service;

    @PostMapping("/request/tomografia")
    public ResponseEntity<String> requestTomografia(@RequestPart("file") MultipartFile file, @RequestPart("data") IdentificacaoData data, @RequestPart("info") TomografiaData details){

        return service.saveSolicitacaoTomografia(file, data, details);

    }


    @PostMapping("/request/ressonancia")
    public ResponseEntity<String> requestRessonancia(@RequestPart("file") MultipartFile file, @RequestPart("data") IdentificacaoData data, @RequestPart("info") RessonanciaData details){
       return  service.saveSolicitacaoRessonancia(file, data, details);
        //return ResponseEntity.ok().build();
    }


    @GetMapping("/historico/{token}")
    public ResponseEntity<List<Solicitacoes>> history(@PathVariable String token){
        List<Solicitacoes> list = service.getHistory(token);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/requests")
    public ResponseEntity<List<Solicitacoes>> requests(){
        List<Solicitacoes> list = service.requests();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Solicitacoes>> filter(@RequestBody SolicitacaoData data){
        //System.out.println(data.getTipo());
        List<Solicitacoes> list = service.filter(data.getTipo(), data.getStatus());
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Solicitacoes>> searchByProtocol(@RequestBody SolicitacaoData data){
        List<Solicitacoes> solicitacoes = service.search(data.getProtocolo());
        return  ResponseEntity.ok().body(solicitacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitacoes> getOne(@PathVariable String id){
        Solicitacoes sol = service.getOne(id);
        return ResponseEntity.ok().body(sol);
    }

    @GetMapping("/details/{tipo}/{id}")
    public ResponseEntity<Optional> getDetails(@PathVariable String tipo, @PathVariable String id){
        var possibleResult =   tipo.equals("ressonancia") ?  service.getRessonancia(id) :  service.getTomografia(id);
        return ResponseEntity.ok().body(possibleResult);
    }


    @PostMapping("/schedule/{id}")
    public ResponseEntity<String> schedule(@RequestBody MarcacaoData data, @PathVariable String id){
        return service.schedule(data,id);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<Resource> download(@PathVariable String id){
        System.out.println(id);
        return service.download(id);
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<String> change(@PathVariable String id){
       // ResponseEntity<Object> response;
        service.changeStatus(id);
       /* try {

            response =
        } catch (MessagingException e) {
           // throw new RuntimeException(e);
            response = ResponseEntity.badRequest().build();
        }*/
        return ResponseEntity.ok().body("");
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestParam("file") MultipartFile file,@RequestParam String id){
        String message;
        try {
            message = service.updateFile(file,id);
        } catch (IOException e) {
           message = "Ocorreu um erro ao tentar atualizar este registro. Entre em contato com o Administrador para obter assistência";
        }
        return ResponseEntity.ok().body(message);
    }


    @PostMapping("/resultado")
    public ResponseEntity<String> setResultado(@RequestBody ResultadoData resultado){
        return service.setResultado(resultado);
    }

    @GetMapping("/operator/{username}")
    public ResponseEntity<User> getOperator(@PathVariable String username){
        User user = service.getOperator(username);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/schedule/cancel/{id}")
    public ResponseEntity<String> cancelSchedule(@PathVariable String id){
        String result = service.userCancelSchedule(id);
        return ResponseEntity.ok().body(result);
    }
}

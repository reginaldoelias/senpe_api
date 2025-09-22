package br.mil.mar.saudenaval.senpe.controller;

import br.mil.mar.saudenaval.senpe.domain.LoginResponseToken;
import br.mil.mar.saudenaval.senpe.domain.Register.CodigoDTO;
import br.mil.mar.saudenaval.senpe.domain.Register.RegisterData;
import br.mil.mar.saudenaval.senpe.entities.Login;
import br.mil.mar.saudenaval.senpe.entities.User;
import br.mil.mar.saudenaval.senpe.repositories.UserRepository;
import br.mil.mar.saudenaval.senpe.services.AuthorizationService;
import br.mil.mar.saudenaval.senpe.services.TokenService;
import br.mil.mar.saudenaval.senpe.services.UserServices;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserServices userServices;


    @PostMapping("/system")
    public ResponseEntity<String> system(@RequestPart("file") MultipartFile file){
        ResponseEntity<String> response;
        try {
            userServices.updateFusma(file);
            response = ResponseEntity.ok().body("Upload realizado com sucesso e os dados do conteúdo do arquivo foram salvos.");
        } catch (IOException e) {
            response = ResponseEntity.badRequest().body("Não foi possível carregar os dados enviados para o sistema. Por favor, tente novamente ou entre em contato com o suporte técnico.");
        }

        return response;
    }

    @PostMapping("/system/ssm")
    public ResponseEntity<String> updateSystem(@RequestPart("file") MultipartFile file){
        ResponseEntity<String> response;
        try {
            userServices.saveUsersSSM(file);
            response = ResponseEntity.ok().body("Upload realizado com sucesso e os dados do conteúdo do arquivo foram salvos.");
        } catch (IOException e) {
            response = ResponseEntity.badRequest().body("Não foi possível carregar os dados enviados para o sistema. Por favor, tente novamente ou entre em contato com o suporte técnico.");
        }

        return response;
    }


    @PostMapping("/register")
    public ResponseEntity<String> firstTime(@RequestBody RegisterData data) {
        return userServices.registerUser(data);
    }

    @PostMapping("/civil/register")
    public ResponseEntity<String> register(@RequestBody RegisterData data){
        return userServices.civilUserRegister(data);
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Login login) {

        String username = userServices.getUsername(login.getUsername());

        if (userServices.isAccountNonLocked(username)){
            try{
                var usernamePassword = new UsernamePasswordAuthenticationToken(username, login.getPassword());
                var auth = this.authenticationManager.authenticate(usernamePassword);
                if (auth.isAuthenticated()){
                    var token = "";
                    if (login.getBiometria()){
                        token = tokenService.generateBiometriaToken((User) auth.getPrincipal());
                    }else {
                        token = tokenService.generateToken((User) auth.getPrincipal());
                    }
                    userServices.setUpdatelogin(username);
                    userServices.resetFaildAttempts(username);
                    return ResponseEntity.ok(LoginResponseToken.showToken(token));
                }else{

                    return ResponseEntity.status(500).body("Ocorreu um erro no servidor. Por favor, tente novamente mais tarde.");
                }
            }catch (Exception e){
               // userServices.incrementFailedAttempts(username);
                return ResponseEntity.badRequest().body("Não conseguimos encontrar um usuário com as informações fornecidas. Por favor, verifique os dados e tente novamente.");
            }

        }else{
            return ResponseEntity.badRequest().body("Você efetuou muitas tentativas de acesso sem sucesso. Esta conta foi bloqueada. Se esqueceu sua senha solicite a recuperação.");
        }



    }


    @PostMapping("/change-password")
    public ResponseEntity changePassword(@RequestBody Login login) {
        var update = userServices.updatePassword(login.getUsername(), login.getPassword());
        if (update == 0) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/lists/**")
    public ResponseEntity<List<User>> getAllUsers() {
        // System.out.print("Token: "+token);
        List<User> users = userServices.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/import")
    public ResponseEntity<String> processCSV() {
        userServices.saveAdminConfig();
      return ResponseEntity.ok().body("Administrator user set successfully!");
    }

    @PostMapping("/recover")
    public ResponseEntity<?> recoverPassword(@RequestBody RegisterData data) {


           /* String email = userServices.recoverPassword(data.getNip(), data.getDataNascimento());
            if (email.isBlank()){
                return ResponseEntity.badRequest().body("Usuário não encontrado. Verifique se o NIP ou a data de nascimento foi digitado corretamente e tente novamente.");
            }else{
                GoogleAuthenticatorKey key = userServices.gerarChave();
                return ResponseEntity.ok().body(email);
            }*/
            String email = userServices.recoverPassword(data.getNip(), data.getDataNascimento());
            return email.isBlank() ? ResponseEntity.badRequest().build() : ResponseEntity.ok().body(email);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> updatePassword(@RequestBody RegisterData data) {

        var possibleUser = userServices.getUserToResetPassword(data.getId());
        if (possibleUser.isPresent()) {
            User user = possibleUser.get();
            int update = userServices.updatePassword(user.getUsername(), data.getPassword());
            if (update == 0) {
                return ResponseEntity.badRequest().body("Não foi possível alterar a senha. Por favor, tente novamente mais tarde.");
            }
            userServices.unlockUser(user);
            return ResponseEntity.ok().body("Sua senha foi alterada com sucesso.");
        }else {
            return ResponseEntity.badRequest().body("O link de recuperação de senha expirou. Por favor, solicite um novo link para redefinir sua senha.");
        }
    }


    @GetMapping("/user/{token}")
    public ResponseEntity<User> findUserByToken(@PathVariable String token){
        User user = userServices.findByToken(token);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/operador/{nip}")
    public ResponseEntity<User> findUserByNip(@PathVariable String nip){
        User user = userServices.findByUsername(nip);
       // System.out.println(nip);
        return ResponseEntity.ok().body(user);
    }
    @GetMapping("/perfil/{nip}/{perfil}")
    public ResponseEntity<String> updateProfile(@PathVariable String nip, @PathVariable String perfil){
        String result = userServices.updatePerfil(nip,perfil);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable String id){
        User user = userServices.getById(id);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<String> updateUser(@RequestParam String email, @RequestParam String tel,@PathVariable String username, @RequestParam Boolean whatsapp){

        String info = userServices.updateUser(email,tel,username,whatsapp);
        return ResponseEntity.ok().body(info);
    }

    @PostMapping("/oath/setup")
    public ResponseEntity<?> setup(@RequestBody Map<String, String> body) {
        //String email = body.get("email");
        String nip = body.get("nip");
        GoogleAuthenticatorKey key = userServices.gerarChave();

        var  possibleUser = repository.findUserByUsername(nip);
        if(possibleUser.isPresent()){
            User usuario = possibleUser.get();
            usuario.setSecret(key.getKey());
            usuario.setUsando2FA(true);
            String email = usuario.getEmail();
            repository.save(usuario);
            String secret = key.getKey();
            String qrCodeUrl = userServices.gerarQrCode("SaudeNaval-SENPE", email, key);
            Map<String,String> response = new HashMap<>();
            response.put("secret",secret);
            response.put("email",email);
            response.put("qrCodeUrl",qrCodeUrl);
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/validar")
    public ResponseEntity<String> validar(@RequestBody CodigoDTO dto) {
        var possibleUser = repository.findByEmail(dto.getEmail());

        if (possibleUser.isPresent()){
            User user = possibleUser.get();
            boolean valido = userServices.validarCodigo(user.getSecret(), dto.getCodigo());

            if (valido) {
                String token = tokenService.generateTokenToRecoverPassword(user);
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Código inválido.");
            }
        }else{
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }
    }

    @PostMapping("/reauthenticate")
    public ResponseEntity<?> reauthenticate(@RequestBody RegisterData data){
        return userServices.reauthenticate(data) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
}

@PostMapping("/app/qrcode")
    public ResponseEntity<?> settings(@RequestBody Map<String, String> body) {
    String nip = body.get("nip");
    String nascimento = body.get("nascimento");
    String cpf = body.get("cpf");

    GoogleAuthenticatorKey key = userServices.gerarChave();
    User user = userServices.validateUserSettings(nip, nascimento, cpf);
    user.setSecret(key.getKey());
    user.setUsando2FA(true);
    String email = user.getEmail();

    if (!email.isBlank()) {
            repository.save(user);
            String secret = key.getKey();
            String qrCodeUrl = userServices.gerarQrCode("SaudeNaval-SENPE", email, key);
            Map<String, String> response = new HashMap<>();
            response.put("secret", secret);
            response.put("email", email);
            response.put("qrCodeUrl", qrCodeUrl);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}

package med.voll.api.controller;

import java.net.Authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.user.UserDTO;
import med.voll.api.domain.user.UserJPA;
import med.voll.api.domain.user.UserRepository;
import med.voll.api.system.security.TokenDTO;
import med.voll.api.system.security.TokenService;

@RestController
@RequestMapping("/sign")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private UserRepository repository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/in")
    public ResponseEntity<TokenDTO> signIn(@RequestBody @Valid UserDTO user) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(user.login(), user.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.newToken((UserJPA) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }

    @PostMapping("/up")
    @Transactional
    public ResponseEntity<String> signUp(@RequestBody @Valid UserDTO user) {
        repository.save(new UserJPA(user));

        return ResponseEntity.ok().build();
    }

}

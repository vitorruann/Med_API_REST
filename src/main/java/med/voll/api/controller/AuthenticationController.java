package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.user.UserDTO;
import med.voll.api.domain.user.UserJPA;
import med.voll.api.domain.user.UserRepository;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserRepository repository;

    @PostMapping
    public ResponseEntity<String> signIn(@RequestBody @Valid UserDTO user) {
        var token = new UsernamePasswordAuthenticationToken(user.login(), user.password());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/up")
    @Transactional
    public ResponseEntity<String> signUp(@RequestBody @Valid UserDTO user) {
        var newUser = new UserJPA(user);
        System.out.println(newUser.getPassword());
        System.out.println(newUser.getUsername());

        // repository.save(newUser);

        return ResponseEntity.ok().build();
    }

}

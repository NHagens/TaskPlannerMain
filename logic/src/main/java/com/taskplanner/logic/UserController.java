package com.taskplanner.logic;

import com.taskplanner.logic.security.JwtTokenRequest;
import com.taskplanner.logic.security.JwtTokenResponse;
import com.taskplanner.logic.security.JwtTokenUtil;
import com.taskplanner.logic.security.JwtUserDetailsService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserRepository repository;

    public UserController(IUserRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        List<User> baseUsers = Arrays.asList(
                new User("Test1", passwordEncoder.encode("Test1")),
                new User("Test2", passwordEncoder.encode("Test2"))
        );
        repository.saveAll(baseUsers);
    }

    @GetMapping("/getUser")
    public User getUser(@Valid @RequestParam("username") String name) {
        return repository.findByName(name);
    }

    @GetMapping("/getAll")
    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
            throws AuthenticationException {
        JwtUserDetailsService service = new JwtUserDetailsService(repository);
        JwtTokenUtil util = new JwtTokenUtil();

        final UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getUsername());
        final String token = util.generateToken(userDetails);

        return ResponseEntity.ok(new JwtTokenResponse(token));
    }
}

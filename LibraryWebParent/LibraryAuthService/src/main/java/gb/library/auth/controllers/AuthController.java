package gb.library.auth.controllers;

import gb.library.auth.dtos.JwtRequest;
import gb.library.auth.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authenticate")
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest request) {
        return authService.authenticate(request);
    }
}
package gb.library.auth.controllers;

import gb.library.auth.dtos.JwtRequest;
import gb.library.auth.dtos.JwtResponse;
import gb.library.auth.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
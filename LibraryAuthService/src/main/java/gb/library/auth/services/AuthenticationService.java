package gb.library.auth.services;

import gb.library.auth.dtos.JwtRequest;
import gb.library.auth.dtos.JwtResponse;
import gb.library.common.exceptions.AppError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public ResponseEntity<?> authenticate(JwtRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.UNAUTHORIZED.value(),
                            "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails user = userService.loadUserByUsername(request.getEmail());
        String jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }
}

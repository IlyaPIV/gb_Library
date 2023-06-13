package gb.library.auth.services;

import gb.library.auth.dtos.JwtRequest;
import gb.library.auth.dtos.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
        authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetails user = userService.loadUserByUsername(request.getEmail());
        String jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }
}

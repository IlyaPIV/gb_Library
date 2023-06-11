package gb.library.auth.dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String userEmail;
    private String password;
}
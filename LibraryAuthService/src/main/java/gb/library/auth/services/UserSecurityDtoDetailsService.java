package gb.library.auth.services;

import gb.library.auth.dtos.UserSecurityDto;
import gb.library.auth.repositories.UserRepository;
import gb.library.common.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSecurityDtoDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(email);
        return user.map(UserSecurityDto::new).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with email %s not found", email)));
    }
}

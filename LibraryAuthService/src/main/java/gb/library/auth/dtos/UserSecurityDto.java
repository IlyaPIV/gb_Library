package gb.library.auth.dtos;

import gb.library.common.entities.Role;
import gb.library.common.entities.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
public class UserSecurityDto extends User implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(Role role: getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getRoleType().name()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }
}

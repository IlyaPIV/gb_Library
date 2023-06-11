package gb.library.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gb.library.common.entities.User ;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}

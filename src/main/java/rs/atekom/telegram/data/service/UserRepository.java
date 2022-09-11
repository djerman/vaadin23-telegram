package rs.atekom.telegram.data.service;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.atekom.telegram.data.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);
}
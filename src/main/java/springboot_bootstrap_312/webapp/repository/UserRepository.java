package springboot_bootstrap_312.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot_bootstrap_312.webapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByName(String username);
    public User findByEmail(String email);
}

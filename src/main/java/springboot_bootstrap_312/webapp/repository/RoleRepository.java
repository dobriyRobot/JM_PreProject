package springboot_bootstrap_312.webapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import springboot_bootstrap_312.webapp.model.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public List<Role> findAll();
}

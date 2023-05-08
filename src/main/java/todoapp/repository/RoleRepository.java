package todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todoapp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}

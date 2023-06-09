package todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todoapp.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}

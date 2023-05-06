package todoapp;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import todoapp.repository.TodoRepository;

@SpringBootApplication
@AllArgsConstructor
public class TodoappApplication implements CommandLineRunner {


	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}

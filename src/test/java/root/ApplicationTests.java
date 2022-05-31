package root;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import root.entities.Role;
import root.entities.User;
import root.repositories.UserRepository;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void addAdmin(@Autowired UserRepository userRepository) {
		User user = new User();
		user.setName("Admin");
		user.setAge(0);
		user.setEmail("admin@mail.box");
		user.setPassword("admin");
		user.setRole(Role.ADMIN);
		user.setEnabled(true);

		userRepository.save(user);
	}

	@Test
	void addUsers(@Autowired UserRepository userRepository) {
		User user1 = new User();
		user1.setName("User");
		user1.setAge(0);
		user1.setEmail("user@mail.box");
		user1.setPassword("user");
		user1.setRole(Role.USER);
		user1.setEnabled(true);

		User user2 = new User();
		user2.setName("Иван Иванов");
		user2.setAge(20);
		user2.setEmail("ivan@mail.box");
		user2.setPassword("user");
		user2.setRole(Role.USER);
		user2.setEnabled(true);

		User user3 = new User();
		user3.setName("Петр Петров");
		user3.setAge(30);
		user3.setEmail("petr@mail.box");
		user3.setPassword("user");
		user3.setRole(Role.USER);
		user3.setEnabled(false);

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
	}
}

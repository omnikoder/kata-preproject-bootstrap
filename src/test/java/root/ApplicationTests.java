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

}

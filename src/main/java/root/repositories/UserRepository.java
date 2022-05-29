package root.repositories;

import org.springframework.validation.BindingResult;
import root.entities.User;

import java.util.List;

public interface UserRepository {
    List<User> getUsers();
    User getUserById(Long id);
    User getUserByEmail(String email);
    void save(User user);
    void update(User user);
    void delete(Long id);
    boolean isExisting(String email);
    void validateEmail(String email, BindingResult bindingResult);
}

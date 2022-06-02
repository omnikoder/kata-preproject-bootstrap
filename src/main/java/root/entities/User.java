package root.entities;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users",
        uniqueConstraints =
        @UniqueConstraint(name = "email", columnNames = { "email" })
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 255, message = "* Длина имени должна быть от 2 до 255 символов")
    private String name;

    @NotNull(message = "* Укажите возраст")
    @Min(value = 0, message = "* Возраст не может быть меньше 0")
    @Max(value = 120, message = "* Возраст не может превышать 120")
    private Integer age;

    @NotEmpty(message = "* Введите email")
    @Email(message = "* Неправильный email-адрес")
    private String email;

    @NotEmpty(message = "* Введите пароль")
    private String password;

    @NotNull(message = "* Назначьте роль")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    private boolean enabled;

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return String.format(
                "ID:     %d\nNAME:   %s\nAGE:    %d\nEMAIL:  %s\nROLE:   %s\nACTIVE: %b\n",
                id, name, age, email, role, enabled);
    }
}

package br.com.zup.SkillForge.login;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Email should be valid.")
    @NotEmpty(message = "Email cannot be empty.")
    private String email;

    @NotEmpty(message = "Password cannot be empty.")
    private String password;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
package br.com.zup.SkillForge.register.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class RegisterUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Size(min = 8, message = "Password should have at least 8 characters")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @Transient
    @Size(min = 8, message = "Password confirmation should have at least 8 characters")
    @NotEmpty(message = "Password confirmation cannot be empty")
    private String confirmPassword;

    public RegisterUser() {}

}
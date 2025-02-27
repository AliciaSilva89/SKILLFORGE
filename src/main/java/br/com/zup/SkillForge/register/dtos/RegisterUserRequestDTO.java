package br.com.zup.SkillForge.register.dtos;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequestDTO {

    @Email(message = "Email should be valid.")
    @NotEmpty(message = "Email cannot be empty.")
    private String email;

    @Size(min = 8, message = "Password should have at least 8 characters.")
    @NotEmpty(message = "Password cannot be empty.")
    private String password;

    @Transient
    @Size(min = 8, message = "Password confirmation should have at least 8 characters.")
    @NotEmpty(message = "Password confirmation cannot be empty.")
    private String confirmPassword;

    @AssertTrue(message = "Passwords do not match.")
    public boolean isPasswordsEqual() {
        return password != null && password.equals(confirmPassword);
    }

    private RegisterUserRequestDTO(Builder builder) {
        this.email = builder.email;
        this.password = builder.password;
        this.confirmPassword = builder.confirmPassword;
    }

    public static class Builder {
        private String email;
        private String password;
        private String confirmPassword;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder confirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public RegisterUserRequestDTO build() {
            return new RegisterUserRequestDTO(this);
        }
    }
}
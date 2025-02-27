package br.com.zup.SkillForge.login.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String email;
    private String token;

    private UserResponseDTO(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.token = builder.token;
    }

    public static class Builder {
        private Long id;
        private String email;
        private String token;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public UserResponseDTO build() {
            return new UserResponseDTO(this);
        }
    }
}
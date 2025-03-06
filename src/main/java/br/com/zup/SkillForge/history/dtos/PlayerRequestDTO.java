package br.com.zup.SkillForge.history;

import br.com.zup.SkillForge.login.dtos.LoginUserRequestDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequestDTO {

    @NotEmpty(message = "Ranking cannot be empty.")
    private int ranking;
    @NotEmpty(message = "Score cannot be empty.")
    private int score;

    private PlayerRequestDTO(PlayerRequestDTO.Builder builder) {
        this.ranking = builder.ranking;
        this.score = builder.score;
    }

    public static class Builder {
        private int ranking;
        private int score;

        public PlayerRequestDTO.Builder ranking(int ranking) {
            this.ranking = ranking;
            return this;
        }

        public PlayerRequestDTO.Builder score(int score) {
            this.score = score;
            return this;
        }

        public PlayerRequestDTO build() {
            return new PlayerRequestDTO(this);
        }
    }
}

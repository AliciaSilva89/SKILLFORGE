package br.com.zup.SkillForge.history;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponseDTO {
    private int ranking;
    private int score;

    private PlayerResponseDTO(PlayerResponseDTO.Builder builder) {
        this.ranking = builder.ranking;
        this.score = builder.score;
    }

    public static class Builder {
        private int ranking;
        private int score;

        public PlayerResponseDTO.Builder ranking(int ranking) {
            this.ranking = ranking;
            return this;
        }

        public PlayerResponseDTO.Builder score(int score) {
            this.score = score;
            return this;
        }

        public PlayerResponseDTO build() {
            return new PlayerResponseDTO(this);
        }
    }
}

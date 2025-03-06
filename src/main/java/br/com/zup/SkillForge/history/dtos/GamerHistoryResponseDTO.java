package br.com.zup.SkillForge.history.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamerHistoryResponseDTO {

    private Long id;
    private String matchResult;
    private int pointsEarned;

    private GamerHistoryResponseDTO(GamerHistoryResponseDTO.Builder builder) {
        this.id = builder.id;
        this.matchResult = builder.matchResult;
        this.pointsEarned = builder.pointsEarned;
    }

    public static class Builder {
        private Long id;
        private String matchResult;
        private int pointsEarned;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder matchResult(String matchResult) {
            this.matchResult = matchResult;
            return this;
        }

        public Builder pointsEarned(int pointsEarned) {
            this.pointsEarned = pointsEarned;
            return this;
        }

        public GamerHistoryResponseDTO build() {
            return new GamerHistoryResponseDTO(this);
        }
    }
}


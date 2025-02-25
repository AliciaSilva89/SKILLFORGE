package br.com.zup.SkillForge.softSkill.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionsRequestDTO {

    @NotNull(message = "Title cannot be null.")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters.")
    private String title;

    @NotNull(message = "Option A cannot be null.")
    @Size(min = 1, max = 100, message = "Option A must be between 1 and 100 characters.")
    private String optionA;

    @NotNull(message = "Option B cannot be null.")
    @Size(min = 1, max = 100, message = "Option B must be between 1 and 100 characters.")
    private String optionB;

    @NotNull(message = "Option C cannot be null.")
    @Size(min = 1, max = 100, message = "Option C must be between 1 and 100 characters.")
    private String optionC;

    private QuestionsRequestDTO(Builder builder) {
        this.title = builder.title;
        this.optionA = builder.optionA;
        this.optionB = builder.optionB;
        this.optionC = builder.optionC;
    }

    public static class Builder {
        private String title;
        private String optionA;
        private String optionB;
        private String optionC;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder optionA(String optionA) {
            this.optionA = optionA;
            return this;
        }

        public Builder optionB(String optionB) {
            this.optionB = optionB;
            return this;
        }

        public Builder optionC(String optionC) {
            this.optionC = optionC;
            return this;
        }

        public QuestionsRequestDTO build() {
            return new QuestionsRequestDTO(this);
        }
    }
}


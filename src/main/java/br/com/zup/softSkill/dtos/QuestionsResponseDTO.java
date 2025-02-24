package br.com.zup.softSkill.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionsResponseDTO {

    private Long id;

    private String title;
    private String optionA;
    private String optionB;
    private String optionC;

    private QuestionsResponseDTO(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.optionA = builder.optionA;
        this.optionB = builder.optionB;
        this.optionC = builder.optionC;
    }

    public static class Builder {
        private Long id;
        private String title;
        private String optionA;
        private String optionB;
        private String optionC;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

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

        public QuestionsResponseDTO build() {
            return new QuestionsResponseDTO(this);
        }
    }
}

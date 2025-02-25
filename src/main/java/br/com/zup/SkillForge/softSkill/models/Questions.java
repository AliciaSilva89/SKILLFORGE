package br.com.zup.SkillForge.softSkill.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode
@ToString
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Questions() {
    }

    public Questions(String title, String optionA, String optionB, String optionC) {
        this.title = title;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
    }
}

package br.com.zup.SkillForge.softSkill.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title cannot be null.")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters.")
    private String title;

    @NotEmpty(message = "Option A cannot be null.")
    @Size(min = 1, max = 100, message = "Option A must be between 1 and 100 characters.")
    private String optionA;

    @NotEmpty(message = "Option B cannot be null.")
    @Size(min = 1, max = 100, message = "Option B must be between 1 and 100 characters.")
    private String optionB;

    @NotEmpty(message = "Option C cannot be null.")
    @Size(min = 1, max = 100, message = "Option C must be between 1 and 100 characters.")
    private String optionC;
}
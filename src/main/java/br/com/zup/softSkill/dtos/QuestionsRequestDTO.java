package br.com.zup.softSkill.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionsRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 3, message = "Title must be at least 3 characters long")
    private String title;

    @NotBlank(message = "Option A is required")
    @Size(min = 3, message = "Option A must be at least 3 characters long")
    private String optionA;

    @NotBlank(message = "Option B is required")
    @Size(min = 3, message = "Option B must be at least 3 characters long")
    private String optionB;

    @NotBlank(message = "Option C is required")
    @Size(min = 3, message = "Option C must be at least 3 characters long")
    private String optionC;
}


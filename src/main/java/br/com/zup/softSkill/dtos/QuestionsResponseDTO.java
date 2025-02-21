package br.com.zup.softSkill.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class QuestionsResponseDTO {

    private UUID id;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
}


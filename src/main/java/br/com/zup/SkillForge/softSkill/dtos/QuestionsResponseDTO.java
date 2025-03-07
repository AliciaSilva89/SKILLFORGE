package br.com.zup.SkillForge.softSkill.dtos;

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

}

package br.com.zup.SkillForge.history.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamerHistoryRequestDTO {

    private String matchResult;
    private int pointsEarned;
}

package br.com.zup.SkillForge.history;

import br.com.zup.SkillForge.history.dtos.GamerHistoryRequestDTO;
import br.com.zup.SkillForge.history.dtos.GamerHistoryResponseDTO;
import br.com.zup.SkillForge.history.model.GameHistory;
import org.springframework.stereotype.Component;

@Component
public class GameHistoryMapper {

    public GameHistory toEntity(GamerHistoryRequestDTO dto) {
        return new GameHistory(dto.getMatchResult(), dto.getPointsEarned(), null);
    }

    public GamerHistoryResponseDTO toDTO(GameHistory entity) {
        return new GamerHistoryResponseDTO.Builder()
                .id(entity.getId())
                .matchResult(entity.getMatchResult())
                .pointsEarned(entity.getPointsEarned())
                .build();
    }
}
package br.com.zup.SkillForge.history.model;

import br.com.zup.SkillForge.history.dtos.GamerHistoryRequestDTO;
import br.com.zup.SkillForge.history.dtos.GamerHistoryResponseDTO;
import br.com.zup.SkillForge.history.mapper.GameHistoryMapper;
import br.com.zup.SkillForge.history.model.GameHistory;
import br.com.zup.SkillForge.history.model.Player;
import br.com.zup.SkillForge.history.repository.GameHistoryRepository;
import br.com.zup.SkillForge.history.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameHistoryService {

    private final GameHistoryRepository gameHistoryRepository;
    private final PlayerRepository playerRepository;
    private final GameHistoryMapper gameHistoryMapper;

    private static final Logger logger = LoggerFactory.getLogger(GameHistoryService.class);

    public GamerHistoryResponseDTO createGameHistory(Long playerId, GamerHistoryRequestDTO requestDTO) {
        logger.info("Creating game history for player with ID: {}", playerId);

        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isEmpty()) {
            logger.error("Player with ID {} not found", playerId);
            throw new IllegalArgumentException("Player not found");
        }

        Player player = playerOptional.get();
        GameHistory gameHistory = gameHistoryMapper.toEntity(requestDTO);
        gameHistory.setPlayer(player);

        GameHistory savedGameHistory = gameHistoryRepository.save(gameHistory);
        logger.info("Game history created with ID: {}", savedGameHistory.getId());

        return gameHistoryMapper.toDTO(savedGameHistory);
    }
}
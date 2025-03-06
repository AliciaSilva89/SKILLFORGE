package br.com.zup.SkillForge;

import br.com.zup.SkillForge.history.model.GameHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameHistoryRepository extends JpaRepository<GameHistory, Long> {
}
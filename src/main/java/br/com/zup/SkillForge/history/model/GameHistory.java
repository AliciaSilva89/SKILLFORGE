package br.com.zup.SkillForge.history.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class GameHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matchResult;
    private int pointsEarned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    public GameHistory() {}

    public GameHistory(String matchResult, int pointsEarned, Player player) {
        this.matchResult = matchResult;
        this.pointsEarned = pointsEarned;
        this.player = player;
    }
}

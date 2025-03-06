package br.com.zup.SkillForge.history;

import br.com.zup.SkillForge.login.models.LoginUser;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "players")
public class Player extends LoginUser {

    private int ranking;
    private int score;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GameHistory> gameHistory;

    public Player() {}

    public Player(String email, String password, int ranking, int score) {
        super(email, password);
        this.ranking = ranking;
        this.score = score;
    }
}


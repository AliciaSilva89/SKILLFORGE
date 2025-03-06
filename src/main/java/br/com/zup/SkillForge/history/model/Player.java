package br.com.zup.SkillForge.history.model;

import br.com.zup.SkillForge.login.models.LoginUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Player extends LoginUser {

    private int ranking;
    private int score;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GameHistory> gameHistory;

    public Player(String email, String password, int ranking, int score) {
        super(email, password);
        this.ranking = ranking;
        this.score = score;
    }
}


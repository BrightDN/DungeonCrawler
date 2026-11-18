package domain.entities;

import java.util.ArrayList;
import java.util.List;

public class EnemyRepository {
    private List<Enemy> enemies = new ArrayList<>();

    public void createEnemy(Difficulty difficulty, String name) {
        Enemy newEnemy = new Enemy(difficulty, name);
        enemies.add(newEnemy);
    }
}

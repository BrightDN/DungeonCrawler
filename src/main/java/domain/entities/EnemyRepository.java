package domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnemyRepository {
    private final List<Enemy> enemies = new ArrayList<>();

    public void createEnemy(Enemy newEnemy) {
        enemies.add(newEnemy);
    }

    public Enemy getEnemy(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("blank name");
        for (Enemy enemy : enemies) {
            if (Objects.equals(enemy.getName(), name)) return enemy;
        }
        return null;
    }
}

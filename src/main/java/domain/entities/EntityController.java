package domain.entities;

import domain.dungeons.Direction;
import dto.PlayerDTO;

public class EntityController {
    private Player player;
    private EnemyRepository enemyRepository;

    public EntityController() {
        this.enemyRepository = new EnemyRepository();
    }

    public void createPlayer(Difficulty difficulty, String name) {
        if (player != null) throw new IllegalArgumentException("A player already exists");
        this.player = new Player(difficulty, name);
    }

    public void createEnemy(Difficulty difficulty, String name, Double expYield) {
        Enemy newEnemy = new Enemy(difficulty, name, expYield);
        enemyRepository.createEnemy(newEnemy);
    }

    public void movePlayer(Direction direction, int dungeonSize) {
        player.move(direction, dungeonSize);
    }

    public PlayerDTO getPlayer() {
        return new PlayerDTO(
                this.player.getName()
        );
    }
}

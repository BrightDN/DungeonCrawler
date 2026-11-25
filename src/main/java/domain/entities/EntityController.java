package domain.entities;

import domain.Difficulty;
import domain.dungeons.Direction;
import dto.PlayerDTO;

public class EntityController {
    private Player player;
    private EnemyRepository enemyRepository;

    public EntityController() {
        this.enemyRepository = new EnemyRepository();
    }

    public void createPlayer(Difficulty difficulty, String name, int health) {
        if (player != null) throw new IllegalArgumentException("A player already exists");
        this.player = new Player(difficulty, name, health);
    }

    public void createEnemy(Difficulty difficulty, String name, Double expYield, int health) {
        Enemy newEnemy = new Enemy(difficulty, name, expYield, health);
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

    public Enemy getEnemy(String name) {
        return enemyRepository.getEnemy(name);
    }
}

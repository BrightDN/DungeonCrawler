package domain.entities;

import domain.dungeons.Direction;
import domain.dungeons.Dungeon;
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

    public void createEnemy(Difficulty difficulty, String name) {
        enemyRepository.createEnemy(difficulty, name);
    }

    public void movePlayer(Direction direction, Dungeon dungeon) {
        player.move(direction, dungeon);
    }

    public PlayerDTO getPlayer() {
        return new PlayerDTO(
                this.player.getName()
        );
    }
}

package domain.entities;

public class EntityController {
    private Player player;
    private EnemyRepository enemyRepository;

    public EntityController() {
        this.enemyRepository = new EnemyRepository();
    }

    public void createPlayer(Difficulty difficulty) {
        if (player != null) throw new IllegalArgumentException("A player already exists");
        this.player = new Player(difficulty);
    }
}

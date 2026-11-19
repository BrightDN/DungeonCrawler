package domain;

import domain.dungeons.Direction;
import domain.dungeons.DungeonController;
import domain.entities.Difficulty;
import domain.entities.EntityController;

public class GameController {
    private final Difficulty DEFAULT_DIFFICULTY = Difficulty.MEDIUM;
    private boolean gameRunning;
    private final DungeonController dc;
    private final EntityController ec;
    // TODO : Add remaining controllers to the GameController
    //  -> CUI | GUI will only communicate with the GameController

    private Difficulty difficulty = DEFAULT_DIFFICULTY;

    public GameController() {
        this.dc = new DungeonController();
        this.ec = new EntityController();
    }

    public void movePlayer(Direction direction) {
        ec.movePlayer(direction, dc.getCurrentDungeonSize());
    }

    public void createPlayer(String name) {
        ec.createPlayer(difficulty, name);
    }

    public void createEnemy(String name, Double expYield) {
        ec.createEnemy(difficulty, name, expYield);
    }

    public void startGame() {
        gameRunning = true;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }


}

package domain;

import domain.dungeons.Direction;
import domain.dungeons.DungeonController;
import domain.entities.EntityController;

public class GameController {
    private boolean gameRunning;
    private final DungeonController dc;
    private final EntityController ec;
    private final DifficultySettings ds;
    // TODO : Add remaining controllers to the GameController
    //  -> CUI | GUI will only communicate with the GameController

    public GameController() {
        this.dc = new DungeonController();
        this.ec = new EntityController();
        this.ds = new DifficultySettings();
    }

    public void movePlayer(Direction direction) {
        ec.movePlayer(direction, dc.getCurrentDungeonSize());
    }

    public void createPlayer(String name) {
        ec.createPlayer(Difficulty.MEDIUM, name, ds.getPlayerInitialHealth());
    }

    public void createEnemy(String name, Double expYield, int health) {
        ec.createEnemy(Difficulty.MEDIUM, name, expYield, health);
    }

    public void startGame() {
        gameRunning = true;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public String getDifficulty() {
        return ds.getDifficulty();
    }

    public void setDifficulty(String difficulty) {
        ds.setDifficulty(difficulty);
    }
}

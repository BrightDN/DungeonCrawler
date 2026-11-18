package domain;

import domain.dungeons.DungeonController;
import domain.entities.Difficulty;
import domain.entities.EntityController;

public class GameController {
    private final Difficulty DEFAULT_DIFFICULTY = Difficulty.MEDIUM;
    private final DungeonController dc;
    private final EntityController ec;
    // TODO : Add remaining controllers to the GameController
    //  -> CUI | GUI will only communicate with the GameController

    public GameController() {
        this.dc = new DungeonController();
        this.ec = new EntityController();
    }
}

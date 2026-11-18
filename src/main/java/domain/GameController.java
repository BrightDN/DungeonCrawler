package domain;

import domain.dungeons.DungeonController;

public class GameController {
    private final DungeonController dc;
    // TODO : Add remaining controllers to the GameController
    //  -> CUI | GUI will only communicate with the GameController

    public GameController() {
        this.dc = new DungeonController();
    }
}

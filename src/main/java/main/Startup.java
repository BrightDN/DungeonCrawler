package main;

import cui.DungeonCrawlerApplication;
import domain.GameController;

public class Startup {
    static GameController gc = new GameController();
    static DungeonCrawlerApplication dca = new DungeonCrawlerApplication(gc);

    static void main(String[] args) {
        dca.start();
    }
}

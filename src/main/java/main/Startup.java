package main;

import cui.DungeonCrawlerApplication;
import domain.GameController;

public class Startup {
    static void main(String[] args) {
        new DungeonCrawlerApplication(new GameController()).start();
    }
}

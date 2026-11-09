package domain.dungeons;

import java.security.SecureRandom;

public class Dungeon {
    public static final String DEFAULT_DUNGEON_NAME = "Unknown dungeon";
    public static final SecureRandom seed = new SecureRandom();
    public static final int DEFAULT_DUNGEON_SIZE = 5;

    private long currentSeed;
    private Room[][] rooms;

    private String dungeonName;

    public Dungeon(String dungeonName, long customSeed) {
        setDungeonName(dungeonName);
        setSeed(customSeed);
        generateDungeon();
    }

    private void generateDungeon() {
        rooms = new Room[DEFAULT_DUNGEON_SIZE][DEFAULT_DUNGEON_SIZE];
        for (int row = 0; row < rooms.length; row++) {
            for (int col = 0; col < rooms[row].length; col++) {
                Room cr = new Room(seed);
                rooms[row][col] = cr;
                if (col == 0 && cr.hasLeftExit()) {
                    cr.toggleLeftExit();
                }
                if (col == rooms[row].length - 1 && cr.hasRightExit()) {
                    cr.toggleRightExit();
                }
                if (row == 0 && cr.hasTopExit()) {
                    cr.toggleTopExit();
                }
                if (row == rooms.length - 1 && cr.hasBottomExit()) {
                    cr.toggleBottomExit();
                }
            }
        }
    }

    /**
     *
     * Sets the dungeonName
     *
     * @param dungeonName The name given to the dungeon instance
     *
     */
    private void setDungeonName(String dungeonName) {
        if (dungeonName != null && !dungeonName.isBlank()) {
            this.dungeonName = dungeonName;
            return;
        }
        this.dungeonName = DEFAULT_DUNGEON_NAME;
    }

    private void setSeed(long customSeed) {
        if (customSeed == 0) return;
        seed.setSeed(customSeed);
        currentSeed = customSeed;
    }

    public long getSeed() {
        return currentSeed;
    }

    public String getDungeonName() {
        return dungeonName;
    }
}

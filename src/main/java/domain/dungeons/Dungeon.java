package domain.dungeons;

import java.security.SecureRandom;

public class Dungeon {
    private static final String DEFAULT_DUNGEON_NAME = "Unknown dungeon";
    private static final SecureRandom seed = new SecureRandom();

    private long currentSeed;

    private String dungeonName;

    public Dungeon(String dungeonName, long customSeed) {
        setDungeonName(dungeonName);
        setSeed(customSeed);
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

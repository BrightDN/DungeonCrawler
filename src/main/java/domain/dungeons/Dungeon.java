package domain.dungeons;

public class Dungeon {
    private static final String DEFAULT_DUNGEON_NAME = "Unknown dungeon";
    private final Room[][] rooms;
    private String dungeonName;


    public Dungeon(String dungeonName) {
        setDungeonName(dungeonName);
        rooms = new Room[4][4];
    }

    private void setDungeonName(String dungeonName) {
        if (dungeonName != null && !dungeonName.isBlank()) {
            this.dungeonName = dungeonName;
            return;
        }
        this.dungeonName = DEFAULT_DUNGEON_NAME;
    }
}

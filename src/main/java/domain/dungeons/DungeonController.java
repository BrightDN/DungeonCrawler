package domain.dungeons;

import dto.DungeonDTO;

public class DungeonController {
    private final DungeonRepository dungeonRepo;
    private Dungeon currentDungeon;

    public DungeonController() {
        dungeonRepo = new DungeonRepository();
    }

    public void createDungeon(String name, int floorSizeOffset, int roofSizeOffset, long seed) {
        Dungeon dungeon = new Dungeon(name, floorSizeOffset, roofSizeOffset, seed);
        dungeonRepo.add(dungeon);
    }

    public void enterDungeon(String name) {
        this.currentDungeon = dungeonRepo.getDungeon(name);
        currentDungeon.getRoom(0, 0).setVisited(true);
    }

    public boolean hasRoomLoot(int hPos, int vPos) {
        return this.currentDungeon.getRoom(hPos, vPos).hasLoot();
    }

    public DungeonDTO getCurrentDungeon() {
        return new DungeonDTO(
                this.currentDungeon.getDungeonName()
        );
    }
}

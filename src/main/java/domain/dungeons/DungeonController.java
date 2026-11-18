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

    /**
     * Moves the player in one of the 4 cardinal directions to a new {@link Room}
     *
     * @param direction The cardinal direction from {@link Direction} in which to move
     */
    // TODO: Move to player
    public void movePlayer(Direction direction) {
//        switch (direction) {
//            case NORTH -> {
//                if (currentHorizontalPosition == 0) {
//                    throw new IllegalArgumentException("Illegal move, out of bounds");
//                }
//                currentHorizontalPosition--;
//            }
//            case SOUTH -> {
//                if (currentHorizontalPosition == currentDungeon.getDungeonSize() - 1) {
//                    throw new IllegalArgumentException("Illegal move, out of bounds");
//                }
//                currentHorizontalPosition++;
//            }
//            case EAST -> {
//                if (currentVerticalPosition == currentDungeon.getDungeonSize() - 1) {
//                    throw new IllegalArgumentException("Illegal move, out of bounds");
//                }
//                currentVerticalPosition++;
//            }
//            case WEST -> {
//                if (currentVerticalPosition == 0) {
//                    throw new IllegalArgumentException("Illegal move, out of bounds");
//                }
//                currentVerticalPosition--;
//            }
//        }
//        currentRoom = currentDungeon.getRoom(currentHorizontalPosition, currentVerticalPosition);
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

    public long getDungeonSeed() {
        return currentDungeon.getSeed();
    }
}

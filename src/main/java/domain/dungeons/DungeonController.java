package domain.dungeons;

public class DungeonController {
    private final DungeonRepository dungeonRepo;
    private Dungeon currentDungeon;
    private int currentHorizontalPosition;
    private int currentVerticalPosition;

    DungeonController() {
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
    public void movePlayer(Direction direction) {
        switch (direction) {
            case NORTH -> {
                if (currentHorizontalPosition == 0) {
                    throw new IllegalArgumentException("Illegal move, out of bounds");
                }
                currentHorizontalPosition--;
            }
            case SOUTH -> {
                if (currentHorizontalPosition == currentDungeon.getDungeonSize() - 1) {
                    throw new IllegalArgumentException("Illegal move, out of bounds");
                }
                currentHorizontalPosition++;
            }
            case EAST -> {
                if (currentVerticalPosition == currentDungeon.getDungeonSize() - 1) {
                    throw new IllegalArgumentException("Illegal move, out of bounds");
                }
                currentVerticalPosition++;
            }
            case WEST -> {
                if (currentVerticalPosition == 0) {
                    throw new IllegalArgumentException("Illegal move, out of bounds");
                }
                currentVerticalPosition--;
            }
        }
    }

    public long getDungeonSeed() {
        return currentDungeon.getSeed();
    }
}

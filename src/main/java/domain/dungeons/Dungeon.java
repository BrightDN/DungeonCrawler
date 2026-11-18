package domain.dungeons;

import java.security.SecureRandom;

public class Dungeon {
    public static final String DEFAULT_DUNGEON_NAME = "Unknown dungeon";
    public static final int MINIMUM_DUNGEON_SIZE = 5;
    public static final int MAXIMUM_DUNGEON_SIZE = 30;
    public static final int DEFAULT_DUNGEON_SIZE = 10;
    public static final int MINIMUM_EXIT_DISTANCE_FROM_START = 6;
    private static final SecureRandom seed = new SecureRandom();

    private Room[][] rooms;
    private int dungeonSize;

    private String dungeonName;

    public Dungeon(String dungeonName, int dungeonSizeLowerOffset, int dungeonSizeUpperOffset, long customSeed) {
        setDungeonName(dungeonName);
        setSeed(customSeed);
        setDungeonSize(dungeonSizeLowerOffset, dungeonSizeUpperOffset);
        generateDungeon();
    }

    private void generateDungeon() {
        int exitDistance = decideExitDistanceFromEntrance();
        int horizontalExitPosition = setHorizontalExitDistance(exitDistance);
        int verticalExitPosition = setVerticalExitDistance(exitDistance, horizontalExitPosition);
        createInitialDungeonLayout(horizontalExitPosition, verticalExitPosition);
        openPathways();
        if (!ensuredPathToExit()) {
            seed.reseed();
            generateDungeon();
        }
        resetVisits();
    }

    private int decideExitDistanceFromEntrance() {
        return seed.nextInt(MINIMUM_EXIT_DISTANCE_FROM_START, Math.powExact(dungeonSize, 2) + 1);
    }

    private int setHorizontalExitDistance(int totalDistance) {
        int leftBound = Math.max(0, totalDistance - dungeonSize);
        int rightBound = Math.min(dungeonSize, totalDistance);
        if (leftBound == rightBound - 1) {
            return leftBound;
        }
        return seed.nextInt(leftBound, rightBound);
    }

    private int setVerticalExitDistance(int totalDistance, int hExitPos) {
        return Math.max(0, totalDistance - hExitPos - 1);
    }

    private void createInitialDungeonLayout(int hExitPos, int vExitPos) {
        boolean exitHasBeenSet = false;
        rooms = new Room[dungeonSize][dungeonSize];
        for (int row = 0; row < rooms.length; row++) {
            for (int col = 0; col < rooms[row].length; col++) {
                Room cr = new Room(seed);
                rooms[row][col] = cr;
                if (col == 0 && cr.hasLeftExit()) cr.toggleLeftExit();
                if (col == rooms[row].length - 1 && cr.hasRightExit()) cr.toggleRightExit();
                if (row == 0 && cr.hasTopExit()) cr.toggleTopExit();
                if (row == rooms.length - 1 && cr.hasBottomExit()) cr.toggleBottomExit();

                if (!exitHasBeenSet && row == hExitPos && col == vExitPos) {
                    exitHasBeenSet = true;
                    cr.setHasDungeonExit();
                }
            }
        }
        if (!exitHasBeenSet) {
            rooms[dungeonSize - 1][dungeonSize - 1].setHasDungeonExit();
        }
    }

    /**
     * Opens the pathways in surrounding {@link Room} objects within this dungeon
     */
    private void openPathways() {
        for (int row = 0; row < rooms.length; row++) {
            for (int col = 0; col < rooms[row].length; col++) {
                if (row != 0 && rooms[row][col].hasTopExit()) {
                    if (!rooms[row - 1][col].hasBottomExit()) rooms[row - 1][col].toggleBottomExit();
                }
                if (col != 0 && rooms[row][col].hasLeftExit()) {
                    if (!rooms[row][col - 1].hasBottomExit()) rooms[row][col - 1].toggleRightExit();
                }
                if (row != rooms.length - 1 && rooms[row][col].hasBottomExit()) {
                    if (!rooms[row + 1][col].hasTopExit()) rooms[row - 1][col].toggleTopExit();
                }
                if (col != rooms[row].length - 1 && rooms[row][col].hasRightExit()) {
                    if (!rooms[row][col + 1].hasLeftExit()) rooms[row][col + 1].toggleLeftExit();
                }
            }
        }
    }

    private boolean ensuredPathToExit() {
        return ensurePathToExitRecursively(0, 0);
    }

    private boolean ensurePathToExitRecursively(int currentRow, int currentCol) {
        this.rooms[currentRow][currentCol].setVisited(true);
        if (this.rooms[currentRow][currentCol].hasDungeonExit()) {
            return true;
        }

        if (currentRow > 0
                && this.rooms[currentRow][currentCol].hasLeftExit()
                && !this.rooms[currentRow - 1][currentCol].isVisited()) {
            if (this.ensurePathToExitRecursively(currentRow - 1, currentCol)) {
                return true;
            }
        }

        if (currentRow < this.dungeonSize - 1
                && this.rooms[currentRow][currentCol].hasRightExit()
                && !this.rooms[currentRow + 1][currentCol].isVisited()) {
            if (this.ensurePathToExitRecursively(currentRow + 1, currentCol)) {
                return true;
            }
        }

        if (currentCol > 0
                && this.rooms[currentRow][currentCol].hasTopExit()
                && !this.rooms[currentRow][currentCol - 1].isVisited()) {
            if (this.ensurePathToExitRecursively(currentRow, currentCol - 1)) {
                return true;
            }
        }

        if (currentCol < this.dungeonSize - 1
                && this.rooms[currentRow][currentCol].hasBottomExit()
                && !this.rooms[currentRow][currentCol + 1].isVisited()) {
            return this.ensurePathToExitRecursively(currentRow, currentCol + 1);
        }
        return false;
    }

    private void resetVisits() {
        for (Room[] row : rooms) {
            for (Room room : row) {
                room.setVisited(false);
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
    }

    /**
     * Sets the size of the dungeon between a {@code floor} and a {@code roof} value
     *
     * @param floor Minimum amount of {@link Room}s
     *              for each row in the {@code dungeon}, defaults to {@link MINIMUM_DUNGEON_SIZE} if 0 or lower
     * @param roof  Maximum amount of {@link Room}s
     *              for each row in the {@code dungeon}, defaults to {@link MINIMUM_DUNGEON_SIZE} if 0 or lower
     */
    private void setDungeonSize(int floor, int roof) {
        if (floor <= 0 && roof <= 0) {
            this.dungeonSize = DEFAULT_DUNGEON_SIZE;
            return;
        }
        floor = Math.max(floor, 0);
        roof = Math.max(roof, 0);
        int proposedSize = DEFAULT_DUNGEON_SIZE + seed.nextInt((floor * -1), roof + 1);
        this.dungeonSize = Math.clamp(proposedSize, MINIMUM_DUNGEON_SIZE, MAXIMUM_DUNGEON_SIZE);
    }

    public int getDungeonSize() {
        return this.dungeonSize;
    }

    public String getDungeonName() {
        return dungeonName;
    }

    public Room getRoom(int horizontalPosition, int verticalPosition) {
        return rooms[horizontalPosition][verticalPosition];
    }
}

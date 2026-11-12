package domain.dungeons;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Dungeon {
    public static final String DEFAULT_DUNGEON_NAME = "Unknown dungeon";
    public static final SecureRandom seed = new SecureRandom();
    public static final int DEFAULT_DUNGEON_SIZE = 10;
    public static final int MINIMUM_DUNGEON_SIZE = 5;
    public static final int MAXIMUM_DUNGEON_SIZE = 30;
    public static final int MINIMUM_EXIT_DISTANCE_FROM_START = 6;

    private long currentSeed;
    private Room[][] rooms;
    private int dungeonSize;
    private int horizontalExitPosition;
    private int verticalExitPosition;

    private String dungeonName;

    public Dungeon(String dungeonName, int dungeonSizeLowerOffset, int dungeonSizeUpperOffset, long customSeed) {
        setDungeonName(dungeonName);
        setSeed(customSeed);
        setDungeonSize(dungeonSizeLowerOffset, dungeonSizeUpperOffset);
        generateDungeon();
    }

    private void generateDungeon() {
        int exitDistance = decideExitDistanceFromEntrance();
        horizontalExitPosition = setHorizontalExitDistance(exitDistance);
        verticalExitPosition = setVerticalExitDistance(exitDistance);
        createInitialDungeonLayout();
        openPathways();
        ensurePathToExit();
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

    private int setVerticalExitDistance(int totalDistance) {
        return Math.max(0, totalDistance - horizontalExitPosition - 1);
    }

    private void createInitialDungeonLayout() {
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

                if (!exitHasBeenSet && row == horizontalExitPosition && col == verticalExitPosition) {
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

    private void ensurePathToExit() { // TODO: work in progress, figuring out best algorithm, not just a simple "down or right"
        boolean exitReached = false;
        int currentH = 0;
        int currentW = 0;
        List<Room> pathRooms = new ArrayList<>();
        pathRooms.add(rooms[0][0]);
        do {
            int direction = seed.nextInt(0, 4);

            if (currentH == horizontalExitPosition && currentW == verticalExitPosition) {
                exitReached = true;
            }
        } while (exitReached);
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

    public long getSeed() {
        return currentSeed;
    }

    public String getDungeonName() {
        return dungeonName;
    }
}

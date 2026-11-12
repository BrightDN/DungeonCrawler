package domain.dungeons;

import java.security.SecureRandom;

public class Room {
    private boolean hasTopExit;
    private boolean hasBottomExit;
    private boolean hasLeftExit;
    private boolean hasRightExit;

    private boolean hasDungeonExit;
    private boolean hasLoot;

    public Room(SecureRandom seed) {
        generateInitialExits(seed);
        setHasLoot(seed);
    }

    private void generateInitialExits(SecureRandom seed) {
        hasTopExit = seed.nextBoolean();
        hasBottomExit = seed.nextBoolean();
        hasLeftExit = seed.nextBoolean();
        hasRightExit = seed.nextBoolean();
    }

    /**
     * Sets the {@link hasTopExit} to {@code True} if {@code False}
     * and vice versa
     *
     */
    public void toggleTopExit() {
        hasTopExit = !hasTopExit;
    }

    /**
     * Sets the {@link hasBottomExit} to {@code True} if {@code False}
     * and vice versa
     *
     */
    public void toggleBottomExit() {
        hasBottomExit = !hasBottomExit;
    }

    /**
     * Sets the {@link hasRightExit} to {@code True} if {@code False}
     * and vice versa
     *
     */
    public void toggleRightExit() {
        hasRightExit = !hasRightExit;
    }

    /**
     * Sets the {@link hasLeftExit} to {@code True} if {@code False}
     * and vice versa
     *
     */
    public void toggleLeftExit() {
        hasLeftExit = !hasLeftExit;
    }

    private void setHasLoot(SecureRandom seed) {
        this.hasLoot = seed.nextBoolean();
    }

    public void setHasDungeonExit() {
        this.hasDungeonExit = true;
    }

    public boolean hasDungeonExit() {
        return this.hasDungeonExit;
    }

    /**
     *
     * returns {@code true} if the {@code Room} has a top exit, otherwise
     * returns {@code false}
     *
     */
    public boolean hasTopExit() {
        return hasTopExit;
    }

    /**
     *
     * returns {@code true} if the {@code Room} has a right exit, otherwise
     * returns {@code false}
     *
     */
    public boolean hasRightExit() {
        return hasRightExit;
    }

    /**
     *
     * returns {@code true} if the {@code Room} has a left exit, otherwise
     * returns {@code false}
     *
     */
    public boolean hasLeftExit() {
        return hasLeftExit;
    }

    /**
     *
     * returns {@code true} if the {@code Room} has a bottom exit, otherwise
     * returns {@code false}
     *
     */
    public boolean hasBottomExit() {
        return hasBottomExit;
    }

    public boolean hasLoot() {
        return hasLoot;
    }

    public int getTotalExits() {
        int total = 0;
        if (this.hasTopExit) total++;
        if (this.hasRightExit) total++;
        if (this.hasBottomExit) total++;
        if (this.hasLeftExit) total++;
        return total;
    }
}

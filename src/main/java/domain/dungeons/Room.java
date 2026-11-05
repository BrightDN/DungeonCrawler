package domain.dungeons;

import java.util.Random;

public class Room {
    private static final double DEFAULT_LOOT_CHANCE = 0.4;
    private static final int DEFAULT_EXIT_CHANCE = 50;
    private double lootChance;
    private boolean hasTopExit;
    private boolean hasBottomExit;
    private boolean hasLeftExit;
    private boolean hasRightExit;

    private boolean hasLoot; // TODO : Fix this boolean to actually contain an item when Items have been made

    public Room(Random seed) {
        this(seed, DEFAULT_LOOT_CHANCE);
    }

    public Room(Random seed, double lootChance) {
        setLootChance(lootChance);
        generateInitialExits(seed);
        setHasLoot();
    }

    private void generateInitialExits(Random seed) {
        int MAX_BOUND_FOR_EXIT_ROLLS = 100;
        if (seed.nextInt(0, MAX_BOUND_FOR_EXIT_ROLLS) > DEFAULT_EXIT_CHANCE) {
            hasTopExit = true;
        }
        if (seed.nextInt(0, MAX_BOUND_FOR_EXIT_ROLLS) > DEFAULT_EXIT_CHANCE) {
            hasBottomExit = true;
        }
        if (seed.nextInt(0, MAX_BOUND_FOR_EXIT_ROLLS) > DEFAULT_EXIT_CHANCE) {
            hasLeftExit = true;
        }
        if (seed.nextInt(0, MAX_BOUND_FOR_EXIT_ROLLS) > DEFAULT_EXIT_CHANCE) {
            hasRightExit = true;
        }
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

    /**
     *
     * Sets the lootChance of the room to the given parameter
     * Sets it to the {@code DEFAULT_LOOT_CHANCE} if the parameter contains a value less than {@code 0}
     *
     * @param lootChance The chance for an item to appear in this room
     *
     *
     */
    private void setLootChance(double lootChance) {
        if (lootChance < 0) {
            this.lootChance = DEFAULT_LOOT_CHANCE;
            return;
        }
        this.lootChance = lootChance;
    }

    private void setHasLoot() {
    }

    /**
     *
     * @return returns the {@link lootChance} of this {@link Room} instance
     */
    public double getLootChance() {
        return lootChance;
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
}

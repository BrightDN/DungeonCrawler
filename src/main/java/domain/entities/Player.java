package domain.entities;

import domain.Difficulty;
import domain.dungeons.Direction;
import domain.dungeons.Room;

public class Player extends Entity {
    public final double DEFAULT_EXP_LEVEL_THRESHOLD = 100;
    public final double DEFAULT_EXP_LEVEL_THRESHOLD_INCREASE = 1.5;

    private double exp;
    private double expMultiplier;
    private int level = 1;
    private double expLevelThreshold = DEFAULT_EXP_LEVEL_THRESHOLD;
    private double expLevelThresholdIncrease = DEFAULT_EXP_LEVEL_THRESHOLD_INCREASE;

    private int xPos;
    private int yPos;


    public Player(Difficulty difficulty, String name, int health) {
        super(health, difficulty.armor, difficulty.damage, name);
        setExpMultiplier(difficulty.expMultiplier);
    }

    public void gainEXP(float baseAmount) {
        if (baseAmount <= 0) return;
        this.exp += (baseAmount * expMultiplier);


    }

    private boolean isLeveledUp() {
        if (this.exp < expLevelThreshold) return false;
        level++;
        expLevelThreshold *= expLevelThresholdIncrease;
        return true;
    }

    private void setExpMultiplier(double expMultiplier) {
        if (expMultiplier <= 0) return;
        this.expMultiplier = expMultiplier;
    }

    /**
     * Moves the player in one of the 4 cardinal directions to a new {@link Room}
     *
     * @param direction The cardinal direction from {@link Direction} in which to move
     */
    public void move(Direction direction, int dungeonSize) {
        switch (direction) {
            case NORTH -> {
                if (xPos == 0) {
                    throw new IllegalArgumentException("Illegal move, out of bounds");
                }
                xPos--;
            }
            case SOUTH -> {
                if (xPos == dungeonSize - 1) {
                    throw new IllegalArgumentException("Illegal move, out of bounds");
                }
                xPos++;
            }
            case EAST -> {
                if (yPos == dungeonSize - 1) {
                    throw new IllegalArgumentException("Illegal move, out of bounds");
                }
                yPos++;
            }
            case WEST -> {
                if (yPos == 0) {
                    throw new IllegalArgumentException("Illegal move, out of bounds");
                }
                yPos--;
            }
        }
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public double getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }
}
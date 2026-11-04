package domain.entities;

public class Player extends Entity {
    public final double DEFAULT_EXP_MULTIPLIER = 1;
    public final double DEFAULT_EXP_LEVEL_THRESHOLD = 100;
    public final double DEFAULT_EXP_LEVEL_THRESHOLD_INCREASE = 1.5;

    private double exp = 0;
    private double expMultiplier = DEFAULT_EXP_MULTIPLIER;
    private int level = 1;
    private double expLevelThreshold = DEFAULT_EXP_LEVEL_THRESHOLD;
    private double expLevelThresholdIncrease = DEFAULT_EXP_LEVEL_THRESHOLD_INCREASE;


    public Player() {
        super();
    }

    public Player(int health, float expMultiplier) {
        super(health);
        setExpMultiplier(expMultiplier);
    }

    public Player(int health, int armor, float expMultiplier) {
        super(health, armor);
        setExpMultiplier(expMultiplier);
    }

    public Player(int health, int armor, int strength, float expMultiplier) {
        super(health, armor, strength);
        setExpMultiplier(expMultiplier);
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

    public double getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }
}

void main() {
    Player p = new Player();
}
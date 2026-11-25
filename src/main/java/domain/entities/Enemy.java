package domain.entities;

import domain.Difficulty;

public class Enemy extends Entity {
    private double expYield;

    public Enemy(Difficulty difficulty, String name, double expYield, int health) {
        super(health, difficulty.armor, difficulty.damage, name);
        setExpYield(expYield);
    }

    private void setExpYield(double expYield) {
        if (expYield < 0) {
            throw new IllegalArgumentException("Negative EXP YIELD");
        }
        this.expYield = expYield;
    }

    public double getExpYield() {
        return expYield;
    }
}

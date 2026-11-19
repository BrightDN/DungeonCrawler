package domain.entities;

public class Enemy extends Entity {
    private double expYield;

    public Enemy(Difficulty difficulty, String name, double expYield) {
        super(difficulty.health, difficulty.armor, difficulty.damage, name);
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

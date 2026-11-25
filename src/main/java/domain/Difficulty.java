package domain;


public enum Difficulty {
    EASY(20, 20, 1.5),
    MEDIUM(10, 15, 1),
    HARD(0, 10, 0.8);

    public final int armor;
    public final int damage;
    public final double expMultiplier;

    Difficulty(int armor, int damage, double expMultiplier) {
        this.armor = armor;
        this.damage = damage;
        this.expMultiplier = expMultiplier;
    }
}
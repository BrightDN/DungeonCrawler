package utils;


public enum Difficulty {
    EASY(20, 20, 1.5, 1.5, 0.75),
    MEDIUM(10, 15, 1, 1, 1),
    HARD(0, 10, 0.8, 0.75, 1.5);

    private final double playerStatMultiplier;
    private final double enemyStatMultiplier;
    public final int armor;
    public final int damage;
    public final double expMultiplier;

    Difficulty(int armor, int damage, double expMultiplier, double playerStatMultiplier, double enemyStatMultiplier) {
        this.armor = armor;
        this.damage = damage;
        this.expMultiplier = expMultiplier;


        this.playerStatMultiplier = playerStatMultiplier;
        this.enemyStatMultiplier = enemyStatMultiplier;
    }

    public static Difficulty[] getAllDifficulties() {
        return Difficulty.values();
    }

    public double getPlayerStatMultiplier() {
        return playerStatMultiplier;
    }

    public double getEnemyStatMultiplier() {
        return enemyStatMultiplier;
    }
}
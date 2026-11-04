package domain.entities;

public enum Difficulty {
    EASY(150, 20, 20, 1.5),
    MEDIUM(100, 10, 15, 1),
    HARD(70, 0, 10, 0.8);

    final int health;
    final int armor;
    final int damage;
    final double expMultiplier;

    Difficulty(int health, int armor, int damage, double expMultiplier) {
        this.health = health;
        this.armor = armor;
        this.damage = damage;
        this.expMultiplier = expMultiplier;
    }
}

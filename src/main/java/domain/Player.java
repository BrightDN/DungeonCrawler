package domain;

public class Player {
    static final int DEFAULT_HEALTH = 100;
    static final int DEFAULT_ARMOR = 10;
    static final int DEFAULT_STRENGTH = 10;

    private int totalHealth;
    private int currentHealth;
    private int armor;
    private int strength;

    public Player() {
        this(DEFAULT_HEALTH);
    }

    public Player(int health) {
        this(health, DEFAULT_ARMOR);
    }

    public Player(int health, int armor) {
        setHealth(health);
        setArmor(armor);
    }

    public void takeDamage(int amount) {
        if (amount < 0) return;
        if (currentHealth == 0) return;

        int damage = calculateDamageTaken(amount);
        this.currentHealth -= damage;
    }

    private int calculateDamageTaken(int base) {
        int damage = base - armor;
        return Math.max(damage, 0);
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    private void setHealth(int health) {
        if (health <= 0) {
            this.totalHealth = DEFAULT_HEALTH;
            this.currentHealth = DEFAULT_HEALTH;
            return;
        }
        this.totalHealth = health;
    }

    private void setArmor(int armor) {
        if (armor < 0) this.armor = DEFAULT_ARMOR;
        this.armor = armor;
    }


    public double getTotalHealth() {
        return totalHealth;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public int getArmor() {
        return armor;
    }

    public int getStrength() {
        return strength;
    }
}

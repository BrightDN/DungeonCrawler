package domain.entities;

abstract class Entity {
    static final int DEFAULT_HEALTH = 100;
    static final int DEFAULT_ARMOR = 10;
    static final int DEFAULT_STRENGTH = 10;

    private int totalHealth;
    private int currentHealth;
    private int armor;
    private int strength;

    public Entity() {
        this(DEFAULT_HEALTH);
    }

    public Entity(int health) {
        this(health, DEFAULT_ARMOR);
    }

    public Entity(int health, int armor) {
        this(health, armor, DEFAULT_STRENGTH);
    }

    public Entity(int health, int armor, int strength) {
        setHealth(health);
        setArmor(armor);
        setStrength(strength);
    }

    public void resetHealth() {
        this.currentHealth = totalHealth;
    }

    public void increaseTotalHealth(int amount) {
        if (amount <= 0) return;
        totalHealth += amount;
    }

    public void decreaseTotalHealth(int amount) {
        if (amount <= 0) return;
        totalHealth -= amount;
    }

    public void heal(int amount) {
        if (currentHealth <= 0) return;
    }

    public void dealDamageTo(Entity target) {
        int damage = calculateDamageDealt();
        target.takeDamage(damage);
    }

    public int calculateDamageDealt() {
        return this.strength;
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

    private void setStrength(int strength) {
        if (strength <= 0) {
            this.strength = DEFAULT_STRENGTH;
            return;
        }
        this.strength = strength;
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



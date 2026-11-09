package domain.entities;

abstract class Entity {
    static final int DEFAULT_HEALTH = 100;
    static final int DEFAULT_ARMOR = 10;
    static final int DEFAULT_STRENGTH = 10;

    private int maxHealth;
    private int currentHealth;
    private int armor;
    private int strength;

    public Entity(int health, int armor, int strength) {
        setHealth(health);
        setArmor(armor);
        
        setStrength(strength);
    }

    public void resetHealth() {
        this.currentHealth = maxHealth;
    }

    public void increaseTotalHealth(int amount) {
        if (amount <= 0) return;
        maxHealth += amount;
    }

    public void decreaseTotalHealth(int amount) {
        if (amount <= 0) return;
        maxHealth -= amount;
    }

    public void heal(int amount) {
        if (getCurrentHealth() <= 0) return;
        currentHealth = (int) Math.min(currentHealth += amount, getTotalHealth());
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
            this.maxHealth = DEFAULT_HEALTH;
            this.currentHealth = DEFAULT_HEALTH;
            return;
        }
        this.maxHealth = health;
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
        return maxHealth;
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



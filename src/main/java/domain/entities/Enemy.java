package domain.entities;

public class Enemy extends Entity {

    public Enemy(Difficulty difficulty, String name) {
        super(difficulty.health, difficulty.armor, difficulty.damage, name);
    }
}

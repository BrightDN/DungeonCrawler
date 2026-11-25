package domain;

public class DifficultySettings {
    private final Difficulty DEFAULT_DIFFICULTY = Difficulty.MEDIUM;
    private Difficulty difficulty = DEFAULT_DIFFICULTY;

    public String getDifficulty() {
        return difficulty.name();
    }

    public void setDifficulty(String difficultyStr) {
        Difficulty difficulty = Difficulty.valueOf(difficultyStr.toUpperCase());
    }

    public int getPlayerInitialHealth() {
        return switch (difficulty) {
            case EASY -> 150;
            case MEDIUM -> 100;
            case HARD -> 70;
        };
    }
}
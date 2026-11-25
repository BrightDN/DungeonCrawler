package cui;

import domain.GameController;

import java.util.Scanner;

public class DungeonCrawlerApplication {
    private GameController gc;
    private final Scanner in = new Scanner(System.in);

    public DungeonCrawlerApplication(GameController gc) {
        this.gc = gc;
    }

    public void start() {
        showStartMenu();
    }

    private void showStartMenu() {
        System.out.print("Welcome to Dungeon Crawler\n\n\n\n");
        String chosenOption = chooseOption("", "start", "change difficulty", "exit");
        processChosenStartOption(chosenOption);
    }

    private void processChosenStartOption(String chosenOption) {
        switch (chosenOption.toLowerCase()) {
            case "start" -> gc.startGame();
            case "change difficulty" -> showDifficultyMenu();
            case "exit" -> exitApplication();
        }
    }

    private void showDifficultyMenu() {
    }

    private void exitApplication() {
        System.out.println("Thank you for visiting! See you next time!");
        System.exit(0);
    }

    private String chooseOption(String preText, String... options) {
        if (options.length == 0) throw new IllegalArgumentException("Empty list of options is used");
        if (options.length == 1) return options[0];

        boolean validChoice = false;
        String choice;
        do {
            if (preText != null && !preText.isBlank()) {
                System.out.println(preText);
            }
            for (String option : options) {
                System.out.println("option");
            }
            choice = in.nextLine();
            if (choice == null || choice.isBlank()) continue;

            for (String option : options) {
                if (option == null || option.isBlank()) continue;
                if (option.equalsIgnoreCase(choice)) {
                    validChoice = true;
                    break;
                }
            }
        } while (!validChoice);

        return choice;
    }
}

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
        while (true) {
            System.out.println("Welcome to Dungeon Crawler");
            String chosenOption = chooseOption("Type in your choice", "start", "change difficulty", "exit");
            processChosenStartOption(chosenOption);
        }
    }

    private void processChosenStartOption(String chosenOption) {
        switch (chosenOption.toLowerCase()) {
            case "start" -> startGame();
            case "change difficulty" -> showDifficultyMenu();
            case "exit" -> exitApplication();
            default -> System.out.println("Something went wrong, please reboot the application");
        }
    }

    private void startGame() {
        gc.startGame();
        gc.createPlayer(askName());
    }

    private void showDifficultyMenu() {
        System.out.printf("The current difficulty is: %s%n", gc.getDifficulty());
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
                System.out.println(option);
            }
            System.out.print("Your choice: ");
            choice = in.nextLine();
            if (choice == null || choice.isBlank()) {
                System.out.println("Cannot process an empty option, please try again.");
                continue;
            }

            for (String option : options) {
                if (option == null || option.isBlank()) continue;
                if (option.equalsIgnoreCase(choice)) {
                    validChoice = true;
                    break;
                }
            }

            if (!validChoice) System.out.println("Invalid or unknown choice, please try again");
        } while (!validChoice);

        return choice;
    }

    private String askName() {
        String name;
        while (true) {
            System.out.print("What's your name? > ");
            name = in.nextLine();
            if (name != null && !name.isBlank()) break;
            System.out.println("The given name is invalid, try again.");
        }
        return name;
    }
}

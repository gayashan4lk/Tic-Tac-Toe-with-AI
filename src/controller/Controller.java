package controller;

import model.Ai;
import model.Game;
import view.View;
import java.util.Scanner;

public class Controller {
    private String[] commandSequence;
    private final String[] difficulty = new String[] {"user", "easy", "medium", "hard"};
    private int[] coordinates = new int[2];
    private boolean validCommand;
    private boolean validCoordinates;
    private boolean exitCommand;
    private String coordinatesErrorMessage;
    private Scanner s = new Scanner(System.in);

    private Game game;
    private View view;
    private Ai ai;

    public Controller(Game game, View view) {
        this.game = game;
        this.view = view;
        this.ai = new Ai(game);
        validCommand = false;
        validCoordinates = false;
        exitCommand = false;
        coordinatesErrorMessage = "";
    }

    public void menu() {
        view.printInstructions();
        do {
            System.out.print("Input command: ");
            readCommand();
            analyseCommand();
            if (!validCommand) {
                System.out.println("Bad parameters!");
            } else if (exitCommand) {
                break;
            } else {
                while (!game.isGameOver()) {
                    game.checkGame();
                    char c;
                    for (int i = 1; i < 3; i++) {
                        view.printGrid(game.getGrid());
                        if (i == 1) {
                            c = 'X';
                        } else {
                            c = 'O';
                        }
                        switch (commandSequence[i]) {
                            case "user":
                                do{
                                    System.out.print("Enter the coordinates: ");
                                    readCoordinates();
                                    if (validCoordinates) {
                                        game.writeToCell(coordinates, c);
                                        break;
                                    } else {
                                        System.out.println(coordinatesErrorMessage);
                                    }
                                } while (true);
                                break;
                            case "easy":
                                System.out.println("Making move level \"easy\"");
                                ai.easyAiMove(c);
                                break;

                            case "medium":
                                System.out.println("Making move level \"medium\"");
                                ai.mediumAiMove(c);
                                break;

                            case "hard":
                                System.out.println("Making move level \"hard\"");
                                ai.hardAiMove(c);
                                break;
                        }
                        game.checkGame();
                        if (game.isGameOver()) {
                            view.printGrid(game.getGrid());
                            System.out.println(game.getState());
                            break;
                        }
                    }
                }
                game.makeNewGame();
            }
        } while(!exitCommand);
    }

    private void readCommand() {
        String m = s.nextLine();
        m = m.trim();
        m = m.toLowerCase();
        commandSequence = m.split("\\s+");
    }

    private void analyseCommand() {
        if (commandSequence.length == 1 && commandSequence[0].equals("exit")) {
            validCommand = true;
            exitCommand = true;
        } else if (commandSequence.length == 3 && commandSequence[0].equals("start")) {
            int count = 0;
            for (int i = 1; i < 3; i++) {
                for (String d : difficulty) {
                    if (commandSequence[i].equals(d)) {
                        count++;
                    }
                }
                validCommand = count == 2;
            }
        }
    }

    private void readCoordinates() {
        String c = s.nextLine();
        c = c.replaceAll("\\s", "");
        if (c.length() == 2) {
            try {
                int row = Integer.parseInt(String.valueOf(c.charAt(0)));
                int col = Integer.parseInt(String.valueOf(c.charAt(1)));
                if ((0 < row && row < 4) && (0 < col && col < 4)) {
                    coordinates[0] = row - 1;
                    coordinates[1] = col - 1;
                    if (game.isCellEmpty(coordinates)) {
                        validCoordinates = true;
                        coordinatesErrorMessage = null;
                    } else {
                        validCoordinates = false;
                        coordinatesErrorMessage = "This cell is occupied! Choose another one!";
                    }
                } else {
                    coordinatesErrorMessage = "Coordinates should be from 1 to 3!";
                    validCoordinates = false;
                }
            } catch (NumberFormatException e) {
                coordinatesErrorMessage = "You should enter numbers!";
                validCoordinates = false;
            }
        } else {
            coordinatesErrorMessage = "You should enter numbers!";
            validCoordinates = false;
        }
    }
}

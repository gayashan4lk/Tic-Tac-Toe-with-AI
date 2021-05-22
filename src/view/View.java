package view;

public class View {
    public void printGrid(char[][] grid) {
        System.out.println("—————————");
        for (int i = 0; i < 3; i++){
            System.out.print("| ");
            for (int j = 0; j < 3; j++){
                System.out.print(grid [i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("—————————");
    }

    public void printInstructions() {
        System.out.println("###### TIC TAC TOE ######\n" +
                "###### INSTRUCTIONS ######\n" +
                "Your command should be in the following format.\n" +
                "To start a new game : start [player1] [player2].\n" +
                "[Player1] plays X, [Player2] plays O.\n" +
                "[player1] and [player2] can be human or computer AI.\n" +
                "AI has three intelligent levels: easy, medium, hard.\n" +
                "You can play as human-human, human-AI or AI-AI.\n\n" +
                "Sample Input commands:\n" +
                "human - human => \"Input command:  start user user\"\n" +
                "human - easy AI => \"Input command: start user easy\"\n" +
                "easy AI - easy AI => \"Input command:  start easy hard\"\n" +
                "medium AI - hard AI => \"Input command:  start medium hard\"\n" +
                "hard AI - hard AI => \"Input command:  start hard hard\"\n\n" +
                "Coordinates should be in following format.\n" +
                "Prints in the upper left corner cell =>\" Enter the coordinates: 1 1\"\n" +
                "Prints in the middle cell =>\" Enter the coordinates: 2 2\"\n" +
                "Prints in the lower right corner cell =>\" Enter the coordinates: 3 3\"\n" +
                "Coordinates of Board:");
        printCoordinates();
    }

    public void printCoordinates() {
        System.out.println("—————————————————————");
        for (int i = 0; i < 3; i++){
            System.out.print("| ");
            for (int j = 0; j < 3; j++){
                System.out.print((i+1) + "," );
                System.out.print(j+1);
                System.out.print("   ");
            }
            System.out.println("|");
        }
        System.out.println("—————————————————————");
    }
}
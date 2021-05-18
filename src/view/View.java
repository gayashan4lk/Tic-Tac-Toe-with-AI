package view;

public class View {
    public void printGrid(char[][] grid) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++){
            System.out.print("| ");
            for (int j = 0; j < 3; j++){
                System.out.print(grid [i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
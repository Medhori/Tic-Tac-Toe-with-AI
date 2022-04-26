package tictactoe;

import java.util.List;
import java.util.Scanner;

public class User implements Player{

    static Scanner scanner = new Scanner(System.in);

    @Override
    public void playGame(char[][] table, List<String> playerCoordinates, char symbol) {
        boolean correctCd = false;
        do {
            System.out.print("Enter the coordinates: ");
            String coordinates = scanner.nextLine();
            if (!coordinates.matches("\\d\\s\\d")) {
                System.out.println("You should enter numbers!");
            } else if (!coordinates.matches("[1-3]\\s[1-3]")) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (occupied(coordinates, table)) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                setCoordinates(table, coordinates, playerCoordinates,symbol);
                correctCd = true;
            }
        } while (!correctCd);
    }

    @Override
    public boolean occupied(String coordinates, char[][] table) {
        String[] cd = coordinates.split("\\s");
        int i = Integer.parseInt(cd[0]) - 1;
        int j = Integer.parseInt(cd[1]) - 1;
        return table[i][j] != ' ';
    }

    @Override
    public void setCoordinates(char[][] table, String coordinates, List<String> playerCoordinates
            ,char symbol) {
        String[] cd = coordinates.split("\\s");
        int i = Integer.parseInt(cd[0]) - 1;
        int j = Integer.parseInt(cd[1]) - 1;
        table[i][j] = symbol;
        playerCoordinates.add(coordinates);
    }
}

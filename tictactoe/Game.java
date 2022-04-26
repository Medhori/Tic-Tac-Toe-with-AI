package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    static List<String> coordinatesPlayer1 = new ArrayList<>();
    static List<String> coordinatesPlayer2 = new ArrayList<>();
    public static final char SYMBOL_PLAYER1 = 'X';
    public static final char SYMBOL_PLAYER2 = 'O';
    static boolean gameOver;
    static char[][] table = new char[3][3];
    static Scanner scanner = new Scanner(System.in);
    Player player1;
    Player player2;


    public void start() {

        String command;
        while (true) {
            System.out.print("Input command: ");
            command = scanner.nextLine();

            if ("exit".equals(command)) {
                break;
            }

            if (!command.matches("start\\s(easy|user|medium|hard)\\s(easy|user|medium|hard)")) {
                System.out.println("Bad parameters!");
            } else {
                startGame(command);
            }
        }
    }

    private void startGame(String command) {
        gameOver = false;
        String[] cm = command.split("\\s");
        String levelPlayer1 = cm[1];
        String levelPlayer2 = cm[2];

        player1 = "user".equals(levelPlayer1) ? new User()
                : new Ai(levelPlayer1);


        player2 = "user".equals(levelPlayer2) ? new User()
                : new Ai(levelPlayer2);


        fillTable(table);
        print(table);


        while (!gameOver) {
            player1.playGame(table, coordinatesPlayer1, SYMBOL_PLAYER1);
            print(table);
            System.out.println(checkWin());
            if (gameOver) {
                break;
            }
            player2.playGame(table, coordinatesPlayer2, SYMBOL_PLAYER2);
            //  System.out.println(coordinatesPlayer2);
            print(table);
            System.out.println(checkWin());

        }

        coordinatesPlayer1.clear();
        coordinatesPlayer2.clear();
        table = new char[3][3];
    }


    private String checkWin() {
        List<String> topRow = List.of("1 1", "1 2", "1 3");
        List<String> midRow = List.of("2 1", "2 2", "2 3");
        List<String> botRow = List.of("3 1", "3 2", "3 3");
        List<String> leftCol = List.of("1 1", "2 1", "3 1");
        List<String> midCol = List.of("1 2", "2 2", "3 2");
        List<String> rightCol = List.of("1 3", "2 3", "3 3");
        List<String> cross1 = List.of("1 1", "2 2", "3 3");
        List<String> cross2 = List.of("1 3", "2 2", "3 1");
        List<List<String>> winning = List.of(topRow, midRow, botRow, leftCol, midCol, rightCol, cross1, cross2);

        for (List<String> l : winning) {
            if (coordinatesPlayer1.containsAll(l)) {
                gameOver = true;
                return "X wins";
            } else if (coordinatesPlayer2.containsAll(l)) {
                gameOver = true;
                return "O wins";
            }
        }

        if (coordinatesPlayer1.size() + coordinatesPlayer2.size() == 9) {
            gameOver = true;
            return "Draw";
        }

        return "";
    }


    private void print(char[][] table) {
        System.out.println("---------");
        for (char[] chars : table) {
            System.out.print("| ");
            for (int j = 0; j < table.length; j++) {
                var cell = chars[j];
                if (cell == '_') {
                    System.out.print("  ");
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    void fillTable(char[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                table[i][j] = ' ';
            }
        }
    }
}

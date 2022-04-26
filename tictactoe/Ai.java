package tictactoe;

import java.util.List;
import java.util.Random;

public class Ai implements Player {
    public static final char WS = ' ';
    Random random = new Random();
    String level;

    public Ai(String level) {
        this.level = level;
    }

    @Override
    public void playGame(char[][] table, List<String> playerCoordinates, char symbol) {

        switch (level) {
            case "easy":
                playEasy(table, playerCoordinates, symbol);
                break;
            case "medium":
                playMedium(table, playerCoordinates, symbol);
                break;
            case "hard":
                playHard(table, playerCoordinates, symbol);
                break;
            default:
                System.out.println("level not found");
                break;
        }

    }

    private void playHard(char[][] table, List<String> playerCoordinates, char symbol) {
        System.out.println("Making move level \"hard\"");
        char opponentSymbol = symbol == 'O' ? 'X' : 'O';
        HardMove hardMove = new HardMove(symbol, opponentSymbol);
        String coordinates = hardMove.getBestMoveCoordinates(table);
        setCoordinates(table, coordinates, playerCoordinates, symbol);
    }

    private void playMedium(char[][] table, List<String> playerCoordinates, char symbol) {
        int[][] corAsNumberSolution = new int[table.length][table.length];
        int[][] corAsNumberBlock = new int[table.length][table.length];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j] == symbol) {
                    corAsNumberSolution[i][j] = 1;
                } else {
                    corAsNumberSolution[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j] != symbol && table[i][j] != WS) {
                    corAsNumberBlock[i][j] = 1;
                } else {

                    corAsNumberBlock[i][j] = 0;
                }
            }
        }

        System.out.println("Making move level \"medium\"");


        int topRowSum = corAsNumberSolution[0][0] + corAsNumberSolution[0][1] + corAsNumberSolution[0][2];
        int midRowSum = corAsNumberSolution[1][0] + corAsNumberSolution[1][1] + corAsNumberSolution[1][2];
        int botRowSum = corAsNumberSolution[2][0] + corAsNumberSolution[2][1] + corAsNumberSolution[2][2];
        int leftColSum = corAsNumberSolution[0][0] + corAsNumberSolution[1][0] + corAsNumberSolution[2][0];
        int midColSum = corAsNumberSolution[0][1] + corAsNumberSolution[1][1] + corAsNumberSolution[2][1];
        int rightColSum = corAsNumberSolution[0][2] + corAsNumberSolution[1][2] + corAsNumberSolution[2][2];
        int cross1Sum = corAsNumberSolution[0][0] + corAsNumberSolution[1][1] + corAsNumberSolution[2][2];
        int cross2Sum = corAsNumberSolution[0][2] + corAsNumberSolution[1][1] + corAsNumberSolution[2][0];


        int topRowSumBlock = corAsNumberBlock[0][0] + corAsNumberBlock[0][1] + corAsNumberBlock[0][2];
        int midRowSumBlock = corAsNumberBlock[1][0] + corAsNumberBlock[1][1] + corAsNumberBlock[1][2];
        int botRowSumBlock = corAsNumberBlock[2][0] + corAsNumberBlock[2][1] + corAsNumberBlock[2][2];
        int leftColSumBlock = corAsNumberBlock[0][0] + corAsNumberBlock[1][0] + corAsNumberBlock[2][0];
        int midColSumBlock = corAsNumberBlock[0][1] + corAsNumberBlock[1][1] + corAsNumberBlock[2][1];
        int rightColSumBlock = corAsNumberBlock[0][2] + corAsNumberBlock[1][2] + corAsNumberBlock[2][2];
        int cross1SumBlock = corAsNumberBlock[0][0] + corAsNumberBlock[1][1] + corAsNumberBlock[2][2];
        int cross2SumBlock = corAsNumberBlock[0][2] + corAsNumberBlock[1][1] + corAsNumberBlock[2][0];
        String coordinates;

        if (topRowSum == 2 && topRowSumBlock == 0) {
            // System.out.println("Con1");
            for (int i = 0; i < 3; i++) {
                if (table[0][i] == WS) {
                    table[0][i] = symbol;
                    coordinates = String.format("%d %d", 1, i + 1);
                    playerCoordinates.add(coordinates);
                }
            }
        } else if (midRowSum == 2 && midRowSumBlock == 0) {
            // System.out.println("Con2");
            for (int i = 0; i < 3; i++) {
                if (table[1][i] == WS) {
                    table[1][i] = symbol;
                    coordinates = String.format("%d %d", 2, i + 1);
                    playerCoordinates.add(coordinates);
                }
            }
        } else if (botRowSum == 2 && botRowSumBlock == 0) {
            // System.out.println("Con3");
            for (int i = 0; i < 3; i++) {
                if (table[2][i] == WS) {
                    table[2][i] = symbol;
                    coordinates = String.format("%d %d", 3, i + 1);
                    playerCoordinates.add(coordinates);
                }
            }
        } else if (leftColSum == 2 && leftColSumBlock == 0) {
            // System.out.println("Con4");
            for (int i = 0; i < 3; i++) {
                if (table[i][0] == WS) {
                    table[i][0] = symbol;
                    coordinates = String.format("%d %d", i + 1, 1);
                    playerCoordinates.add(coordinates);
                }
            }
        } else if (midColSum == 2 && midColSumBlock == 0) {
            // System.out.println("Con5");
            for (int i = 0; i < 3; i++) {
                if (table[i][1] == WS) {
                    table[i][1] = symbol;
                    coordinates = String.format("%d %d", i + 1, 2);
                    playerCoordinates.add(coordinates);
                }
            }
        } else if (rightColSum == 2 && rightColSumBlock == 0) {
            //  System.out.println("Con6");
            for (int i = 0; i < 3; i++) {
                if (table[i][2] == WS) {
                    table[i][2] = symbol;
                    coordinates = String.format("%d %d", i + 1, 3);
                    playerCoordinates.add(coordinates);
                }
            }
        } else if (cross1Sum == 2 && cross1SumBlock == 0) {
            // System.out.println("Con7");
            for (int i = 0; i < 3; i++) {
                if (table[i][i] == WS) {
                    table[i][i] = symbol;
                    coordinates = String.format("%d %d", i + 1, i + 1);
                    playerCoordinates.add(coordinates);
                }
            }
        } else if (cross2Sum == 2 && cross2SumBlock == 0) {
            //  System.out.println("Con8");
            int j = 2;
            for (int i = 0; i < 3; i++) {
                if (table[i][j] == WS) {
                    table[i][j] = symbol;
                    coordinates = String.format("%d %d", i + 1, j + 1);
                    playerCoordinates.add(coordinates);
                }
                j--;
            }
        } else if (topRowSumBlock == 2 && topRowSum == 0) {
            // System.out.println("Con9");
            for (int i = 0; i < 3; i++) {
                if (table[0][i] == WS) {
                    table[0][i] = symbol;
                    coordinates = String.format("%d %d", 1, i + 1);
                    playerCoordinates.add(coordinates);
                }
            }
        } else if (midRowSumBlock == 2 && midRowSum == 0) {
            // System.out.println("Con10");
            for (int i = 0; i < 3; i++) {
                if (table[1][i] == WS) {
                    table[1][i] = symbol;
                    coordinates = String.format("%d %d", 2, i + 1);
                    playerCoordinates.add(coordinates);
                }
            }
        } else if (botRowSumBlock == 2 && botRowSum == 0) {
            // System.out.println("Con11");
            for (int i = 0; i < 3; i++) {
                if (table[2][i] == WS) {
                    table[2][i] = symbol;
                    coordinates = String.format("%d %d", 3, i + 1);
                    playerCoordinates.add(coordinates);
                }
            }
        } else if (leftColSumBlock == 2 && leftColSum == 0) {
            // System.out.println("Con12");
            for (int i = 0; i < 3; i++) {
                if (table[i][0] == WS) {
                    table[i][0] = symbol;
                    coordinates = String.format("%d %d", i + 1, 1);
                    playerCoordinates.add(coordinates);
                }
            }
        } else if (midColSumBlock == 2 && midColSum == 0) {
            // System.out.println("Con13");
            for (int i = 0; i < 3; i++) {
                if (table[i][1] == WS) {
                    table[i][1] = symbol;
                    coordinates = String.format("%d %d", i + 1, 2);
                    playerCoordinates.add(coordinates);
                }
            }
        } else if (rightColSumBlock == 2 && rightColSum == 0) {
            //  System.out.println("Con14");
            for (int i = 0; i < 3; i++) {
                if (table[i][2] == WS) {
                    table[i][2] = symbol;
                    coordinates = String.format("%d %d", i + 1, 3);
                    playerCoordinates.add(coordinates);
                }
            }
        } else if (cross1SumBlock == 2 && cross1Sum == 0) {
            // System.out.println("Con15");
            for (int i = 0; i < 3; i++) {
                if (table[i][i] == WS) {
                    table[i][i] = symbol;
                    coordinates = String.format("%d %d", i + 1, i + 1);
                    playerCoordinates.add(coordinates);
                }
            }
        } else if (cross2SumBlock == 2 && cross2Sum == 0) {
            // System.out.println("Con16");
            int j = 2;
            for (int i = 0; i < 3; i++) {
                if (table[i][j] == WS) {
                    table[i][j] = symbol;
                    coordinates = String.format("%d %d", i + 1, j + 1);
                    playerCoordinates.add(coordinates);
                }
                j--;
            }
        } else {
            // System.out.println("hello17");
            playEasy(table, playerCoordinates, symbol);
        }
    }

    private void playEasy(char[][] table, List<String> playerCoordinates, char symbol) {
        String coordinates;
        do {
            int i = random.nextInt(3) + 1;
            int j = random.nextInt(3) + 1;
            coordinates = String.format("%d %d", i, j);
        } while (occupied(coordinates, table));
        if (level.equals("easy")) {
            System.out.println("Making move level \"easy\"");
        }
        setCoordinates(table, coordinates, playerCoordinates, symbol);
    }

    @Override
    public boolean occupied(String coordinates, char[][] table) {
        String[] cd = coordinates.split("\\s");
        int i = Integer.parseInt(cd[0]) - 1;
        int j = Integer.parseInt(cd[1]) - 1;
        return table[i][j] != ' ';
    }

    @Override
    public void setCoordinates(char[][] table, String coordinates, List<String> playerCoordinates, char symbol) {
        String[] cd = coordinates.split("\\s");
        int i = Integer.parseInt(cd[0]) - 1;
        int j = Integer.parseInt(cd[1]) - 1;
        table[i][j] = symbol;
        playerCoordinates.add(coordinates);

    }
}

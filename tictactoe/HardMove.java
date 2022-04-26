package tictactoe;

public class HardMove {

    public static final char WS = ' ';
    char playerSymbol;
    char opponentSymbol;

    public HardMove(char playerSymbol, char opponentSymbol) {
        this.playerSymbol = playerSymbol;
        this.opponentSymbol = opponentSymbol;
    }


    public String getBestMoveCoordinates(char[][] board) {
        Move bestMove = findBestMove(board);
        return String.format("%d %d", bestMove.row + 1, bestMove.col + 1);
    }

    static class Move {
        int row, col;
    }


    boolean isMovesLeft(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == WS) {
                    return true;
                }
            }
        }
        return false;
    }

    int evaluate(char[][] b) {
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
                if (b[row][0] == playerSymbol) {
                    return 10;
                } else if (b[row][0] == opponentSymbol) {
                    return -10;
                }
            }
        }

        for (int col = 0; col < 3; col++) {
            if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
                if (b[0][col] == playerSymbol) {
                    return 10;
                } else if (b[0][col] == opponentSymbol) {
                    return -10;
                }
            }
        }

        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == playerSymbol) {
                return 10;
            } else if (b[0][0] == opponentSymbol) {
                return -10;
            }
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == playerSymbol)
                return 10;
            else if (b[0][2] == opponentSymbol)
                return -10;
        }

        return 0;
    }


    int minimax(char[][] board,
                int depth, Boolean isMax) {
        int score = evaluate(board);
        if (score == 10)
            return score;
        if (score == -10)
            return score;

        if (!isMovesLeft(board))
            return 0;

        int best;
        if (isMax) {
            best = -Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == WS) {
                        board[i][j] = playerSymbol;
                        best = Math.max(best, minimax(board,
                                depth + 1, false));
                        board[i][j] = WS;
                    }
                }
            }
        } else {
            best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == WS) {
                        board[i][j] = opponentSymbol;

                        best = Math.min(best, minimax(board,
                                depth + 1, true));

                        board[i][j] = WS;
                    }
                }
            }
        }
        return best;
    }


    Move findBestMove(char[][] board) {
        int bestVal = -Integer.MAX_VALUE;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == WS) {
                    board[i][j] = playerSymbol;
                    int moveVal = minimax(board, 0, false);
                    board[i][j] = WS;

                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;

    }

}

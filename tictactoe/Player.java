package tictactoe;

import java.util.List;

public interface Player {
    void playGame(char[][] table, List<String> playerCoordinates,char symbol);
    boolean occupied(String coordinates,char[][] table);
    void setCoordinates(char[][] table, String coordinates, List<String> playerCoordinates,char symbol);
}

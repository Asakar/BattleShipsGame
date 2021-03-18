package model;

public class GameBoard {

    private String[][] board;

    public GameBoard(int rows, int columns) {
        this.board = new String[rows][columns];
    }

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }
}

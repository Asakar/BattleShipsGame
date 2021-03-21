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

    public String getValue(int row, int column) {
        return board[row][column];
    }

    public void setValue(int row, int column, String value) {
        board[row][column] = value;
    }

}

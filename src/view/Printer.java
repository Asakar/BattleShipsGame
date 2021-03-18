package view;

import model.GameBoard;
import start.Rows;

public class Printer {

    public static void printBoard(GameBoard gameBoard) {
        String[][] board = gameBoard.getBoard();
        Rows[] row = Rows.values();
        System.out.print(" ");
        for (int i = 0; i < board[0].length; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print(row[i].name() + " ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}

package view;

import model.GameBoard;
import model.Ship;
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

    public static void printInstructions() {
        System.out.println("There are two ships which need destroying:");
        System.out.println("A destroyer which takes up 2 spaces and a battleship that takes up 4 spaces.");
        System.out.println("These ships will be randomly placed on the board either horizontally or vertically.");
        System.out.println("Once all ships have been destroyed you win.");
        System.out.println();
    }

    public static void quitGame(GameBoard gameBoard) {
        Printer.printBoard(gameBoard);
        System.out.println("see the ship positions above.");
        System.out.println("Thank you for playing.");
    }

    public static void message(Object sentence) {
        System.out.println(sentence);
    }



}

package com.asakar.controller;

import com.asakar.Utilities.Randomizer;
import com.asakar.model.Ship;
import com.asakar.model.GameBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoardController {

    private static String sea = ".";

    public static void initializeBoard(GameBoard gameBoard) {
        for (String[] strings : gameBoard.getBoard()) {
            Arrays.fill(strings, sea);
        }
    }

    public static void addShip(GameBoard gameBoard, Ship ship, int noOfShips) {
        int noOfShipsPlaced = 0;
        while (noOfShipsPlaced < noOfShips) {
            String[] orientationOfShip =  {"horizontal","vertical"};
            String orientation = orientationOfShip[Randomizer.getRandomInRange(0,orientationOfShip.length)];
            if (orientation.equals("S")) {
                gameBoard.setBoard(placeShipHorizontally(gameBoard, ship));
            } else {
                gameBoard.setBoard(placeShipVertically(gameBoard, ship));
            }
            noOfShipsPlaced++;
        }
    }

    private static String[][] placeShipHorizontally(GameBoard gameBoard, Ship ship) {
        String[][] board = gameBoard.getBoard();
        int[] temp = generateRandomPosition(gameBoard);
        int row = temp[0];
        int col = temp[1];
        int action = 1;
        int shipMarks = 0;
        List<Integer> places = new ArrayList<>();
        while (shipMarks != ship.getSize()) {
            if (!board[row][col].equals(sea)) {
                temp = generateRandomPosition(gameBoard);
                places.clear();
                shipMarks = 0;
                row = temp[0];
                col = temp[1];
                continue;
            } else {
                places.add(col);
                shipMarks++;
            }
            col += action;
            if (col >= board[0].length-1) {
                action = -1;
                col -= (shipMarks + 1);
            }
        }
        for (Integer point : places) {
            board[row][point] = ship.toString();
        }
        return board;
    }

    private static String[][] placeShipVertically(GameBoard gameBoard, Ship ship) {
        String[][] board = gameBoard.getBoard();
        int[] temp = generateRandomPosition(gameBoard);
        int row = temp[0];
        int col = temp[1];
        int action = 1;
        int shipMarks = 0;
        List<Integer> places = new ArrayList<>();
        while (shipMarks != ship.getSize()) {
            if (!board[row][col].equals(sea)) {
                temp = generateRandomPosition(gameBoard);
                places.clear();
                shipMarks = 0;
                row = temp[0];
                col = temp[1];
                continue;
            } else {
                places.add(row);
                shipMarks++;
            }
            row += action;
            if (row >= board.length-1) {
                action = -1;
                row -= (shipMarks + 1);
            }
        }
        for (Integer point : places) {
            board[point][col] = ship.toString();
        }
        return board;
    }

    private static int[] generateRandomPosition(GameBoard board) {
        return new int[]{Randomizer.getRandomInRange(0, board.getBoard().length),
                Randomizer.getRandomInRange(0, board.getBoard()[0].length)};
    }

}

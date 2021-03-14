import model.BattleShip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BattleShipGame {

    private static String[][] board = new String[10][10];
    private static int noOfBattleShips = 3;
    private static int noOfDestroyers = 3;
    private static int noOfBattleShipsPlaced = 0;


    private static void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "";
            }
        }
    }

    private static void addBattleShips() {
        while (noOfBattleShipsPlaced < noOfBattleShips) {

            BattleShip battleShip = new BattleShip(5);

            String[] orientationOfShip =  {"horizontal","vertical"};
            String orientation = orientationOfShip[getRandomInRange(0,orientationOfShip.length)];
            System.out.println(orientation);
            if (orientation.equals("horizontal")) {
                placeShipHorizontally();
                noOfBattleShipsPlaced++;
            } else {
                placeShipVertically();
                noOfBattleShipsPlaced++;
            }
        }
    }

    private static void placeShipHorizontally() {
        int[] temp = generatePosition();
        int row = temp[0];
        int position = temp[1];
        int action = 1;
        int shipMarks = 0;
        List<Integer> places = new ArrayList<>();
        while (shipMarks != 3) {
            if (board[row][position].equals("ship")) {
                temp = generatePosition();
                places.removeAll(places);
                shipMarks = 0;
                row = temp[0];
                position = temp[1];
                continue;
            } else {
                places.add(position);
                shipMarks++;
            }
            position += action;
            if (position >= board[0].length-1) {
                action = -1;
                position -= (shipMarks + 1);
            }
        }
        for (Integer place : places) {
            System.out.println(row + " " + place);
            board[row][place] = "ship";
        }
    }

    private static void placeShipVertically() {
        int[] temp = generatePosition();
        int row = temp[0];
        int position = temp[1];
        int action = 1;
        int shipMarks = 0;
        List<Integer> places = new ArrayList<>();
        while (shipMarks != 3) {
            if (board[row][position].equals("ship")) {
                temp = generatePosition();
                places.removeAll(places);
                shipMarks = 0;
                row = temp[0];
                position = temp[1];
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
        for (Integer place : places) {
            System.out.println(place + " " + position);
            board[place][position] = "ship";
        }
    }

    private static int[] generatePosition() {
        return new int[]{getRandomInRange(0, board.length), getRandomInRange(0, board[0].length)};
    }

    public static int getRandomInRange(int start, int end){
        Random generator = new Random();
        return start + generator.nextInt(end - start);
    }

    public static void main(String[] args) {
        initializeBoard();
        addBattleShips();
//        System.out.println(board[1].length);
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                System.out.println(board[i][j]);
//            }
//        }
    }

}

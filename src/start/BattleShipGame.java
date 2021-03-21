package start;

import contoller.GameBoardController;
import model.Ship;
import model.GameBoard;
import view.Printer;

import java.util.Scanner;

public class BattleShipGame {

    private static final GameBoard computerBoard = new GameBoard(10, 10);
    private static final GameBoard playerBoard = new GameBoard(10, 10);
    private static final Ship destroyer = new Ship(2, "destroyer");
    private static final Ship battleShip = new Ship(4, "battle ship");

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        final int noOfHitsRequired = destroyer.getSize() + battleShip.getSize();
        int hits = 0;
        int selectedRow;
        int selectedColumn;
        setup();
        while (hits < noOfHitsRequired) {
            String input = scanner.next();
            if (input.matches("quit")) {
                Printer.quitGame(computerBoard);
                break;
            }
            input = checkInput(scanner, input);
            selectedRow = getSelectedRow(input);
            selectedColumn = getSelectedColumn(input);
            String boardValue = computerBoard.getValue(selectedRow, selectedColumn);
            if (!boardValue.equals(".")) {
                playerBoard.setValue(selectedRow, selectedColumn, "x");
                updateShipDamage(boardValue);
                evaluateShip(boardValue);
                hits++;
            } else {
                System.out.println("miss");
                playerBoard.setValue(selectedRow, selectedColumn, "o");
            }
            Printer.printBoard(playerBoard);
        }
        if (hits == noOfHitsRequired) {
            System.out.println("All ships sunk");
        }
    }

    private static int getSelectedColumn(String input) {
        return Integer.parseInt(input.replaceAll("[A-Z]", "")) - 1;
    }

    private static int getSelectedRow(String input) {
        int selectedRow;
        String temp = input.replaceAll("\\d", "");
        selectedRow = Rows.valueOf(temp).getRow() - 1;
        return selectedRow;
    }

    private static void setup() {
        Printer.printInstructions();
        GameBoardController.initializeBoard(computerBoard);
        GameBoardController.initializeBoard(playerBoard);
        GameBoardController.addShip(computerBoard, destroyer, 1);
        GameBoardController.addShip(computerBoard, battleShip, 1);
        Printer.printBoard(playerBoard);
        Printer.printBoard(computerBoard);
    }

    private static String checkInput(Scanner scanner, String input) {
        while (true) {
            if (input.matches("[A-J][1-9]") || input.matches("[A-J]10")) {
                break;
            } else {
                System.out.println("incorrect input");
                input = scanner.next();
            }
        }
        return input;
    }

    public static void updateShipDamage(String boardValue) {
        getShipType(boardValue).incrementDamage();
    }

    public static void evaluateShip(String boardValue) {
        Ship temp = getShipType(boardValue);
        if (temp.getDamage() == temp.getSize()) {
            System.out.println(temp.getName() + " sunk!");
        } else {
            System.out.println("hit " + temp.getName());
        }
    }

    public static Ship getShipType(String boardValue) {
        switch (boardValue) {
            case "b":
                return BattleShipGame.battleShip;
            case "d":
                return BattleShipGame.destroyer;
            default:
                return null;
        }
    }

}

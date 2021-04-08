package com.asakar.start;

import com.asakar.controller.GameBoardController;
import com.asakar.model.Ship;
import com.asakar.model.GameBoard;
import java.util.Scanner;
import com.asakar.view.Printer;

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
                Printer.message("miss");
                playerBoard.setValue(selectedRow, selectedColumn, "o");
            }
            Printer.printBoard(playerBoard);
            if (hits == noOfHitsRequired) {
                Printer.message("All ships sunk");
            }
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
    }

    private static String checkInput(Scanner scanner, String input) {
        while (true) {
            if (input.matches("[A-J][1-9]") || input.matches("[A-J]10")) {
                break;
            } else {
                Printer.message("incorrect input");
                input = scanner.next();
            }
        }
        return input;
    }

    private static void updateShipDamage(String boardValue) {
        getShipType(boardValue).incrementDamage();
    }

    private static void evaluateShip(String boardValue) {
        Ship temp = getShipType(boardValue);
        assert temp != null;
        if (temp.getDamage() == temp.getSize()) {
            Printer.message(temp.getName() + " sunk!");
        } else {
            Printer.message("hit " + temp.getName());
        }
    }

    private static Ship getShipType(String boardValue) {
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

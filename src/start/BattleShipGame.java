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

    public static void startGame() {
        Scanner scanner = new Scanner(System.in);
        Printer.printInstructions();
        GameBoardController.initializeBoard(computerBoard);
        GameBoardController.initializeBoard(playerBoard);
        GameBoardController.addShip(computerBoard, destroyer, 1);
        GameBoardController.addShip(computerBoard, battleShip, 1);
        Printer.printBoard(playerBoard);
        final int noOfHitsRequired = destroyer.getSize() + battleShip.getSize();
        int hits = 0;
        int selectedRow;
        int selectedColumn;
        while (hits < noOfHitsRequired) {
            String input = scanner.next();
            if (input.matches("quit")) {
                Printer.printBoard(computerBoard);
                System.out.println("see the ship positions above.");
                System.out.println("Thank you for playing.");
                break;
            }
            input = checkInput(scanner, input);
            String temp = input.replaceAll("\\d", "");
            selectedRow = Rows.valueOf(temp).getRow() - 1;
            selectedColumn = Integer.parseInt(input.replaceAll("[A-Z]", "")) - 1;
            String boardValue = computerBoard.getBoard()[selectedRow][selectedColumn];
            if (!boardValue.equals(".")) {
                playerBoard.getBoard()[selectedRow][selectedColumn] = ("x");
                updateShipDamage(boardValue);
                evaluateShip(boardValue);
                hits++;
            } else {
                System.out.println("miss");
                playerBoard.getBoard()[selectedRow][selectedColumn] = ("o");
            }
            Printer.printBoard(playerBoard);
        }
        if (hits == noOfHitsRequired) {
            System.out.println("All ships sunk");
        }
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

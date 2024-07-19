package tictactoe.view;

import tictactoe.model.Cell;
import tictactoe.model.GameBoard;

import java.util.Iterator;

public class ConsoleView {
    private final int rowSize = 3;

    public ConsoleView () {}

    public static void printReferenceBoard() {
        System.out.println("Введите цифру от 1 до 9, чтобы выбрать клетку, куда нужно поставить X или O.");
        System.out.println("1|2|3");
        System.out.println("-+-+-");
        System.out.println("4|5|6");
        System.out.println("-+-+-");
        System.out.println("7|8|9");
        System.out.println();
    }

    public void printBoard(GameBoard board) {
        Iterator<Cell> iterator = board.iterator();
        int rowLength = 0;

        while (iterator.hasNext()) {
            if (rowLength < rowSize) {
                System.out.print(rowLength == 1 ? "|" + iterator.next().toString() + "|" : iterator.next().toString());
                rowLength += 1;
            }
            else {
                rowLength = 0;
                System.out.println();
                System.out.println("-+-+-");
            }

        }
        System.out.println();
    }
}

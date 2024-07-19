package tictactoe.model;

import java.util.*;

public class GameBoard {
    private final int fieldSize;
    public static final EmptyCell emptyCell = new EmptyCell();
    public static final X xCell = new X();
    public static final O oCell = new O();

    private final List<Cell> board;

    public GameBoard() {
        fieldSize = 3;
        int size = 3;
        board = new ArrayList<>(size * size);
        for (int i=0; i < size * size; i++) {
            board.add(emptyCell);
        }
    }

    public Cell get(Integer fieldNumber) {
        return board.get(fieldNumber);
    }

    public void put(Integer fieldNumber, Cell mark) throws NotEmptyCell {
        if (board.get(fieldNumber).equals(emptyCell)) {
            board.set(fieldNumber, mark);
        }
        else {
            throw new NotEmptyCell();
        }
    }

    public Iterator<Cell> iterator() {
        return board.iterator();
    }

    public void clear() {
        ListIterator<Cell> iterator = board.listIterator();
        while (iterator.hasNext()) {
            iterator.set(emptyCell);
        }
    }

    boolean winRow(Integer position, Cell checkedMark) {
        for (int i = 0; i < fieldSize; i++) {
            if (!checkedMark.equals(board.get(position + i)))
                break;
            if (i == fieldSize - 1)
                return true;
        }
        return false;
    }

    boolean winColumn(Integer position, Cell checkedMark) {
        for (int i = 0; i < fieldSize; i++) {
            if (!checkedMark.equals(board.get(fieldSize * i + position)))
                break;
            if (i == fieldSize - 1)
                return true;
        }
        return false;
    }

    boolean winDiag(Cell checkedMark) {
        for (int i = 0; i < fieldSize; i++) {
            if (!checkedMark.equals(board.get(fieldSize * i + i)))
                break;
            if (i == fieldSize - 1)
                return true;
        }
        return false;
    }

    boolean winReverseDiag(Cell checkedMark) {
        for (int i = 0; i < fieldSize; i++) {
            if (!checkedMark.equals(board.get((fieldSize - 1) - i + fieldSize * i)))
                break;
            if (i == fieldSize - 1)
                return true;
        }
        return false;
    }

    public boolean isEndGame(Integer position){
        Cell checkedMark = board.get(position);
        if (position.equals(null) || board.get(position).equals(emptyCell)) {
            return false;
        }
        Integer row = position / fieldSize;
        Integer col = position % fieldSize;

        if (
                winRow(row, checkedMark) ||
                winColumn(col, checkedMark) ||
                winDiag(checkedMark) ||
                winReverseDiag(checkedMark)
        ) {
            System.out.println("Победил " + checkedMark.toString());
            return true;
        }

        if (!board.contains(emptyCell)) {
            System.out.println("Ничья");
            return true;
        }
        else {
            return false;
        }
    }
}

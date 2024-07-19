package tictactoe.controller;

import tictactoe.model.*;
import tictactoe.view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Controller {
    private final GameBoard board;
    private final ConsoleView view;
    private Cell whichTurn;
    private BufferedReader reader;

    public Controller(GameBoard board, ConsoleView view) {
        this.board = board;
        this.view = view;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() {
        ConsoleView.printReferenceBoard();
        int i = 0;
        while (i == 0) {
            System.out.println("Выберите режим игры:");
            System.out.println("1 - совместная игры");
            System.out.println("2 - игра с компьютером");
            System.out.print("Введите номер: ");
            try {
                i = Integer.parseInt(this.reader.readLine());
                switch (i) {
                    case 1:
                        startTwoPlayers();
                        break;
                    case 2:
                        startWithComputer();
                        break;
                    default:
                        System.out.println("Неверный режим!");
                        i = 0;
                }

            } catch (NumberFormatException e) {
                System.err.println("Неверный формат!");
                continue;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void startWithComputer() {
        Integer lastTurn = 0;
        Cell player = null;
        Cell computer = null;

        while (whichTurn == null) {
            System.out.println("Выберите сторону:");
            System.out.println("X - крестик");
            System.out.println("O - нолик");
            System.out.print("Введите букву: ");
            try {
                String s = reader.readLine();
                switch (s) {
                    case "X":
                        player = GameBoard.xCell;
                        computer = GameBoard.oCell;
                        whichTurn = player;
                        break;
                    case "O":
                        player = GameBoard.oCell;
                        computer = GameBoard.xCell;
                        whichTurn = computer;
                        break;
                    default:
                        System.out.println("Неправильная буква!");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        while(!board.isEndGame(lastTurn)) {
            if (!player.equals(whichTurn)){
                lastTurn = computerTurn();
                whichTurn = player;
            }
            else {
                view.printBoard(board);
                lastTurn = makeTurn();
                whichTurn = computer;
            }
        }
        view.printBoard(board);
    }

    private void startTwoPlayers() {
        Integer lastTurn = 0;

        if (whichTurn == null) {
            whichTurn = GameBoard.xCell;
        }

        view.printBoard(board);
        lastTurn = makeTurn();

        while(!board.isEndGame(lastTurn)) {
            if (whichTurn.equals(GameBoard.xCell)) {
                whichTurn = GameBoard.oCell;
            }
            else {
                whichTurn = GameBoard.xCell;
            }

            view.printBoard(board);
            lastTurn = makeTurn();
        }
        view.printBoard(board);
    }

    private Integer makeTurn() {
        Integer i;

        while (true) {
            System.out.println("Ход " + whichTurn.toString());
            System.out.print("Введите номер клетки: ");
            try {
                i = Integer.parseInt(this.reader.readLine()) - 1;
                board.put(i, whichTurn);
            } catch (NumberFormatException e) {
                System.err.println("Неверный формат числа!");
                continue;
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Такой клетки не существует!");
                continue;
            } catch (NotEmptyCell e) {
                System.err.println("Клетка уже занята!");
                continue;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return i;
        }

    }

    private int computerTurn() {
        Random rand = new Random();
        int n = rand.nextInt(1, 9);
        while (!board.get(n).equals(GameBoard.emptyCell)) {
            n = rand.nextInt(1, 9);
        }

        try {
            board.put(n, whichTurn);
        } catch (NotEmptyCell e) {}

        if (whichTurn.equals(GameBoard.xCell)) {
            whichTurn = GameBoard.oCell;
        }
        else {
            whichTurn = GameBoard.xCell;
        }
        return n;
    }
}

package tictactoe;

import tictactoe.controller.Controller;
import tictactoe.model.GameBoard;
import tictactoe.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        ConsoleView view = new ConsoleView();

        Controller controller = new Controller(gameBoard, view);

        controller.start();
    }
}
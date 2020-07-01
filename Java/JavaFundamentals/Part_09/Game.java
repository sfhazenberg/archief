package assignment42;

import java.util.Random;
import java.util.Scanner;

class Game {

    private int moveCounter;
    private boolean playerTurn;
    private Player player;
    private Board board;

    Game() {
        player = new Player();
        System.out.println("XX = player marker \n" +
                "OO = computer marker");
        board = new Board();
        playerTurn = true;
        askUserInput();
    }

    private void askUserInput() {
        System.out.println("Your move ([a-c][1-3])");
        int indexToCheck;
        mainLoop:   //checkt of input binnen a1 tot c3 valt. For-loop ipv switch-case haalbaar?
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(!input.isBlank()) {
                input = input.substring(0,1).toUpperCase() + input.substring(1);
            }
            switch (input) {
                case "A1":
                    indexToCheck = 0;
                    break mainLoop;
                case "B1":
                    indexToCheck = 1;
                    break mainLoop;
                case "C1":
                    indexToCheck = 2;
                    break mainLoop;
                case "A2":
                    indexToCheck = 3;
                    break mainLoop;
                case "B2":
                    indexToCheck = 4;
                    break mainLoop;
                case "C2":
                    indexToCheck = 5;
                    break mainLoop;
                case "A3":
                    indexToCheck = 6;
                    break mainLoop;
                case "B3":
                    indexToCheck = 7;
                    break mainLoop;
                case "C3":
                    indexToCheck = 8;
                    break mainLoop;
                default:
                    System.out.println("Invalid input, try again");
                    break;
            }
        }
        validateInput(indexToCheck);
    }

    private void validateInput(int index) {
        if(board.getCurrentBoardBack()[index] == 0) {
            board.updateBoard(playerTurn, index);
            incrementMoveCounter();
            changeTurn();
            isGameOver();
        } else {
            System.out.println("Chosen index is already filled in, choose again");
            askUserInput();
        }
    }

    private void cpuInput() {
        System.out.println("CPU's turn");
        while (true) {
            int rnd = new Random().nextInt(board.getCurrentBoardBack().length);
            if (board.getCurrentBoardBack()[rnd] == 0) {
                board.updateBoard(playerTurn, rnd);
                //System.out.println("Made a move at: " + board.getCurrentBoardFront()[rnd]);
                break;
            }
        }
        incrementMoveCounter();
        changeTurn();
        isGameOver();
    }

    private void incrementMoveCounter(){
        moveCounter++;
    }

    private void changeTurn(){
        playerTurn = !playerTurn;
    }

    private void isGameOver() {
        if (board.isThreeInRow() == 1) {
            gameWon();  //player won
        } else if (board.isThreeInRow() == 2) {
            gameWon();  //cpu won
        } else  if (board.isThreeInRow() == 0 && moveCounter == 9) {
            System.out.println("Draw!");
            newGame();
        }

        if (playerTurn) {
            askUserInput();
        } else {
            cpuInput();
        }
    }

    private void gameWon() {
        System.out.println("Game ended");
        System.out.println(playerTurn ? "CPU won!" : "You won!");
        newGame();
    }

    private void newGame() {
        System.out.println("Do you want to play a new game? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if (answer.equals("y")) {
            new Game();
        } else {
            exit();
        }
    }

    private void exit() {
        System.exit(0);
    }
}

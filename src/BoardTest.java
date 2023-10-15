import java.util.ArrayList;
import java.util.Scanner;

public class BoardTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X); 

        System.out.println("Tic-Tac-Toe Game");
        System.out.println("You are O, and the CPU is X.");
        System.out.println("Enter your moves in the format 'row col', e.g., '0 0' for the top-left corner.");
        System.out.println();

        boolean gameIsOver = false;
        boolean playerTurn = false; // Start with CPU's turn

        while (!gameIsOver) {
            System.out.println("Current board:");
            printBoard(board);

            if (!playerTurn) {
                System.out.println("CPU's move:");
                ArrayList<Move> cpuMoves = cpuPlayer.getNextMoveAB(board);
                Move cpuMove = cpuMoves.get(0); 
                board.play(cpuMove, Mark.X);
            }

            if (playerTurn) {
                boolean validMove = false;
                while (!validMove) {
                    System.out.print("Your move: ");
                    int row = scanner.nextInt();
                    int col = scanner.nextInt();

                    if (isValidMove(board, row, col)) {
                        board.play(new Move(row, col), Mark.O);
                        validMove = true;
                    } else {
                        System.out.println("Invalid move. Try again.");
                    }
                }
            }

            playerTurn = !playerTurn;

            if (boardIsFull(board) || isGameWon(board, Mark.O) || isGameWon(board, Mark.X)) {
                gameIsOver = true;
            }
        }

        System.out.println("Final board:");
        printBoard(board);
        int result = board.evaluate(Mark.O);
        if (result == 100) {
            System.out.println("You win!");
        } else if (result == -100) {
            System.out.println("Alpha-Beta CPU wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    public static boolean isValidMove(Board board, int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board.getMark(row, col) == Mark.EMPTY;
    }

    public static boolean boardIsFull(Board board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getMark(i, j) == Mark.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isGameWon(Board board, Mark mark) {
        // Check row
        for (int i = 0; i < 3; i++) {
            if (board.getMark(i, 0) == mark && board.getMark(i, 1) == mark && board.getMark(i, 2) == mark) {
                return true;
            }
        }

        // Check col
        for (int i = 0; i < 3; i++) {
            if (board.getMark(0, i) == mark && board.getMark(1, i) == mark && board.getMark(2, i) == mark) {
                return true;
            }
        }

        // Check diago
        if (board.getMark(0, 0) == mark && board.getMark(1, 1) == mark && board.getMark(2, 2) == mark) {
            return true;
        }
        if (board.getMark(0, 2) == mark && board.getMark(1, 1) == mark && board.getMark(2, 0) == mark) {
            return true;
        }

        return false;
    }

    public static void printBoard(Board board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getMark(i, j) == Mark.EMPTY) {
                    System.out.print("[ ] ");
                } else {
                    System.out.print(board.getMark(i, j) + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

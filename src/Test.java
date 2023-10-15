import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);

        System.out.println("Tic-Tac-Toe Game");
        System.out.println("You are O, and the CPU is X.");
        String choice = "";
        while (!Objects.equals(choice, "ab") && !Objects.equals(choice, "mm")) {
            System.out.println("Chose Alphabeta (ab) or Minimax (mm)");
            choice = scanner.nextLine();
        }

        System.out.println("Enter your moves in the format 'row col', e.g., '0 0' for the top-left corner.");
        System.out.println();

        boolean gameIsOver = false;
        boolean playerTurn = false; // Start with CPU's turn

        while (!gameIsOver) {
            System.out.println("Current board:");
            printBoard(board);

            if (!playerTurn) {
                System.out.println("CPU's move:");
                if (choice.equals("ab")) {
                    ArrayList<Move> cpuMoves = cpuPlayer.getNextMoveAB(board);
                    Move cpuMove = cpuMoves.get(0);
                    board.play(cpuMove, Mark.X);
                } else if (choice.equals("mm")) {
                    ArrayList<Move> cpuMoves = cpuPlayer.getNextMoveMinMax(board);
                    Move cpuMove = cpuMoves.get(0);
                    board.play(cpuMove, Mark.X);
                }
                System.out.println(cpuPlayer.getNumOfExploredNodes());
            }

            if (playerTurn) {
                boolean validMove = false;
                while (!validMove) {
                    System.out.print("Your move: ");
                    int row = scanner.nextInt();
                    int col = scanner.nextInt();

                    if (board.isTileEmpty(row, col)) {
                        board.play(new Move(row, col), Mark.O);
                        validMove = true;
                    } else {
                        System.out.println("Invalid move. Try again.");
                    }
                }
            }

            playerTurn = !playerTurn;

            if (board.getAvailableMoves().size() == 0 || board.evaluate(Mark.X) == 100 || board.evaluate(Mark.O) == 100) {
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




    public static void printBoard(Board board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isTileEmpty(i, j)) {
                    System.out.print("[] ");
                } else {
                    System.out.print(board.getBoard()[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
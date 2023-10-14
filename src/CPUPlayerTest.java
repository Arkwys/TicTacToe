import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CPUPlayerTest {

    @Test
    void testMinMaxAlgorithm_PlayerX_Win() {
        // Create a new board
        Board board = new Board();

        // Create a CPUPlayer with 'X' mark
        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);

        // Make valid moves for 'X' player to win
        // X |   |
        //   |   |
        //   |   | X
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(2, 2), Mark.X);

        // Get the next move using the Minimax algorithm
        ArrayList<Move> possibleMoves = cpuPlayer.getNextMoveMinMax(board);

        // Assert that the best move for 'X' is at (1, 1).
        // X |   |
        //   | X |
        //   |   | X
        System.out.println("Chosen Move: " + possibleMoves.get(0).getRow() + ", " + possibleMoves.get(0).getCol());
        assertEquals(1, possibleMoves.get(0).getRow());
        assertEquals(1, possibleMoves.get(0).getCol());
    }

    @Test
    void testMinMaxAlgorithm_PlayerOWin() {
        // Create a new board
        Board board = new Board();

        // Create a CPUPlayer with 'O' mark
        CPUPlayer cpuPlayer = new CPUPlayer(Mark.O);

        // Make valid moves for 'O' player to win
        // X |   |
        //   |   |
        //   |   | X
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(2, 2), Mark.X);

        // Get the next move using the Minimax algorithm
        ArrayList<Move> possibleMoves = cpuPlayer.getNextMoveMinMax(board);

        // Assert that the best move for 'O' is at (1, 1).
        // X |   |
        //   | O |
        //   |   | X
        System.out.println("Chosen Move: " + possibleMoves.get(0).getRow() + ", " + possibleMoves.get(0).getCol());
        assertEquals(1, possibleMoves.get(0).getRow());
        assertEquals(1, possibleMoves.get(0).getCol());
    }

    @Test
    void testAlphaBetaAlgorithm_PlayerX_Win() {
        // Create a new board
        Board board = new Board();

        // Create a CPUPlayer with 'X' mark
        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);

        // Make valid moves for 'X' player to win
        // X | O |
        // O | X |
        //   |   |
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.O);
        board.play(new Move(1, 1), Mark.X);
        board.play(new Move(1, 0), Mark.O);

        // Get the next move using the Alpha-Beta Pruning algorithm
        ArrayList<Move> possibleMoves = cpuPlayer.getNextMoveAB(board);

        // Assert that the best move for 'X' is at (2, 2).
        // X | O |
        // O | X |
        //   |   | X
        System.out.println("Chosen Move: " + possibleMoves.get(0).getRow() + ", " + possibleMoves.get(0).getCol());
        assertEquals(2, possibleMoves.get(0).getRow());
        assertEquals(2, possibleMoves.get(0).getCol());
    }

    @Test
    void testAlphaBetaAlgorithm_PlayerOWin() {
        // Create a new board
        Board board = new Board();

        // Create a CPUPlayer with 'O' mark
        CPUPlayer cpuPlayer = new CPUPlayer(Mark.O);

        // Make valid moves for 'O' player to win
        // X | O |
        // O | X |
        //   |   |
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.O);
        board.play(new Move(1, 1), Mark.X);
        board.play(new Move(1, 0), Mark.O);

        // Get the next move using the Alpha-Beta Pruning algorithm
        ArrayList<Move> possibleMoves = cpuPlayer.getNextMoveAB(board);

        // Assert that the best move for 'O' is at (2, 2).
        // X | O |
        // O | X |
        //   |   | O
        System.out.println("Chosen Move: " + possibleMoves.get(0).getRow() + ", " + possibleMoves.get(0).getCol());
        assertEquals(2, possibleMoves.get(0).getRow());
        assertEquals(2, possibleMoves.get(0).getCol());
    }
}

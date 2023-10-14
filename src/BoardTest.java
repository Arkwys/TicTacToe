import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void testEvaluateNoWinner() {
        // Créez un plateau sans gagnant
        //   |   |
        //   |   |
        //   |   |
        Board board = new Board();

        // Assurez-vous que le résultat de l'évaluation est de 0 (pas de gagnant pour le moment)
        int result = board.evaluate(Mark.X);
        assertEquals(0, result);
    }

    @Test
    void testEvaluateHorizontalWinForX() {
        // Créez un plateau avec une victoire horizontale pour X
        // X | X | X
        //   |   |
        //   |   |
        Board board = new Board();
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.X);
        board.play(new Move(0, 2), Mark.X);

        // Assurez-vous que le résultat de l'évaluation est de 100 (X gagne)
        int result = board.evaluate(Mark.X);
        assertEquals(100, result);
    }

    @Test
    void testEvaluateVerticalWinForO() {
        // Créez un plateau avec une victoire verticale pour O
        //   | O |
        //   | O |
        //   | O |
        Board board = new Board();
        board.play(new Move(0, 1), Mark.O);
        board.play(new Move(1, 1), Mark.O);
        board.play(new Move(2, 1), Mark.O);

        // Assurez-vous que le résultat de l'évaluation est de 100 (O gagne)
        int result = board.evaluate(Mark.O);
        assertEquals(100, result);
    }

    @Test
    void testEvaluateDiagonalWinForX() {
        // Créez un plateau avec une victoire diagonale pour X
        // X |   |
        //   | X |
        //   |   | X
        Board board = new Board();
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(1, 1), Mark.X);
        board.play(new Move(2, 2), Mark.X);

        // Assurez-vous que le résultat de l'évaluation est de 100 (X gagne)
        int result = board.evaluate(Mark.X);
        assertEquals(100, result);
    }

    @Test
    void testEvaluateAntiDiagonalWinForO() {
        // Créez un plateau avec une victoire en diagonale inverse pour O
        //   |   | O
        //   | O |
        // O |   |
        Board board = new Board();
        board.play(new Move(0, 2), Mark.O);
        board.play(new Move(1, 1), Mark.O);
        board.play(new Move(2, 0), Mark.O);

        // Assurez-vous que le résultat de l'évaluation est de 100 (O gagne)
        int result = board.evaluate(Mark.O);
        assertEquals(100, result);
    }

    @Test
    void testEvaluateDraw() {
        // Créez un nouveau plateau
        Board board = new Board();

        // Effectuez des mouvements aboutissant à une partie nulle
        // X | O | X
        // X | X | O
        // O | X | O
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.O);
        board.play(new Move(0, 2), Mark.X);
        board.play(new Move(1, 0), Mark.X);
        board.play(new Move(1, 1), Mark.X);
        board.play(new Move(1, 2), Mark.O);
        board.play(new Move(2, 0), Mark.O);
        board.play(new Move(2, 1), Mark.X);
        board.play(new Move(2, 2), Mark.O);

        // Évaluez le plateau
        int result = board.evaluate(Mark.X);

        // Dans cet exemple, la partie se termine par une égalité, le résultat devrait donc être -1.
        assertEquals(-1, result);
    }

    @Test
    void testEvaluateHorizontalWinForO() {
        // Créez un plateau avec une victoire horizontale pour O
        //   |   |
        // X | X | X
        //   |   |
        Board board = new Board();
        board.play(new Move(1, 0), Mark.X);
        board.play(new Move(1, 1), Mark.X);
        board.play(new Move(1, 2), Mark.X);

        // Assurez-vous que le résultat de l'évaluation est de 100 (X gagne)
        int result = board.evaluate(Mark.X);
        assertEquals(100, result);
    }

    @Test
    void testEvaluateVerticalWinForX() {
        // Créez un plateau avec une victoire verticale pour X
        //   |   | X
        // O | O | X
        //   |   | X
        Board board = new Board();
        board.play(new Move(0, 2), Mark.X);
        board.play(new Move(1, 2), Mark.X);
        board.play(new Move(2, 2), Mark.X);

        // Assurez-vous que le résultat de l'évaluation est de 100 (X gagne)
        int result = board.evaluate(Mark.X);
        assertEquals(100, result);
    }

    @Test
    void testEvaluateDiagonalWinForO() {
        // Créez un plateau avec une victoire en diagonale pour O
        // X |   | O
        //   | O |
        // O |   | X
        Board board = new Board();
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 2), Mark.O);
        board.play(new Move(1, 1), Mark.O);
        board.play(new Move(2, 0), Mark.O);
        board.play(new Move(2, 2), Mark.X);

        // Assurez-vous que le résultat de l'évaluation est de 100 (O gagne)
        int result = board.evaluate(Mark.O);
        assertEquals(100, result);
    }

    @Test
    void testEvaluateAntiDiagonalWinForX() {
        // Créez un plateau avec une victoire en diagonale inverse pour X
        //   |   | X
        //   | X |
        // X |   |
        Board board = new Board();
        board.play(new Move(0, 2), Mark.X);
        board.play(new Move(1, 1), Mark.X);
        board.play(new Move(2, 0), Mark.X);

        // Assurez-vous que le résultat de l'évaluation est de 100 (X gagne)
        int result = board.evaluate(Mark.X);
        assertEquals(100, result);
    }
}

public class BoardTest {
    public static void main(String[] args) {
        // Créer un plateau de jeu de taille 3x3
        Board board = new Board(3);

        // Test 1 : Vérifier la méthode play() en plaçant des pièces
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(1, 1), Mark.O);
        board.play(new Move(0, 1), Mark.X);
        board.play(new Move(2, 2), Mark.O);

        // Afficher le plateau après les mouvements
        printBoard(board);

        // Test 2 : Vérifier la méthode evaluate() pour X (joueur X)
        int resultX = board.evaluate(Mark.X);
        System.out.println("Résultat de l'évaluation pour X : " + resultX);

        // Test 3 : Vérifier la méthode evaluate() pour O (joueur O)
        int resultO = board.evaluate(Mark.O);
        System.out.println("Résultat de l'évaluation pour O : " + resultO);

        board.play(new Move(0, 2), Mark.X);
        // Afficher le plateau après les mouvements
        printBoard(board);

        // Test 2 : Vérifier la méthode evaluate() pour X (joueur X)
        resultX = board.evaluate(Mark.X);
        System.out.println("Résultat de l'évaluation pour X : " + resultX);

        // Test 3 : Vérifier la méthode evaluate() pour O (joueur O)
        resultO = board.evaluate(Mark.O);
        System.out.println("Résultat de l'évaluation pour O : " + resultO);
    }

    // Méthode pour afficher le plateau
    private static void printBoard(Board board) {
        int size = board.getSize();
        Mark[][] grid = board.getBoard();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[row][col] == Mark.EMPTY) {
                    System.out.print("[]");
                } else {
                    System.out.print(grid[row][col] + " ");
                }

            }
            System.out.println();
        }
    }
}

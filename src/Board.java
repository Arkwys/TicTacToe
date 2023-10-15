import java.util.ArrayList;

class Board {
    private Mark[][] board;

    // Constructeur pour initialiser le plateau de jeu vide
    public Board() {
        board = new Mark[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Mark.EMPTY;
            }
        }
    }

    // Place la pièce 'mark' sur le plateau à la position spécifiée dans Move
    public void play(Move m, Mark mark) {
        int row = m.getRow();
        int col = m.getCol();

        if (mark == Mark.EMPTY) {
            board[row][col] = mark;
        } else if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == Mark.EMPTY) {
            board[row][col] = mark;
        }
    }
    // Évalue l'état du jeu pour la marque spécifiée
    public int evaluate(Mark mark) {
        // À implémenter : logique d'évaluation du jeu
        if(mark == Mark.EMPTY) return -1;
        Mark adversaire = getOpponentMark(mark);

        // Vérifier les lignes, les colonnes et les diagonales pour déterminer le résultat du jeu.

        // Vérifier les lignes horizontales
        for (int row = 0; row < 3; row++) {
            int count = 0;
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == mark) count++;
                if (board[row][col] == adversaire) count--;
            }
            if (count == 3) {
                // Le joueur 'mark' a gagné
                return 100;
            }
            if (count == -3) {
                // Le joueur 'adversaire' a gagné
                return -100;
            }
        }
        // Vérifier les lignes verticales
        for (int col = 0; col < 3; col++) {
            int countVert = 0;
            for (int row = 0; row < 3; row++) {
                if (board[row][col] == mark) countVert++;
                if (board[row][col] == adversaire) countVert--;
            }
            if (countVert == 3) {
                // Le joueur 'mark' a gagné
                return 100;
            }
            if (countVert == -3) {
                // Le joueur 'adversaire' a gagné
                return -100;
            }
        }

        if(diagWin(mark)) return 100;
        if(diagWin(adversaire)) return -100;

        // Si aucune victoire n'est détectée, vous pouvez vérifier s'il y a égalité (match nul).
        boolean isTie = true;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == Mark.EMPTY) {
                    isTie = false; // Il reste au moins une case vide, ce n'est pas un match nul
                    break;
                }
            }
        }

        if (isTie) {
            return 0; // Match nul
        }

        // Si aucune victoire ni égalité n'est détectée, le jeu continu
        return -1; // Le jeu continu
    }

    public Mark[][] getBoard() {
        return board;
    }

    public boolean diagWin(Mark mark) {
        return (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) ||
                (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark);
    }

    public ArrayList<Move> getAvailableMoves() {
        ArrayList<Move> availableMoves = new ArrayList<>();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (isTileEmpty(row, col)) {
                    availableMoves.add(new Move(row, col));
                }
            }
        }

        return availableMoves;
    }

    public Mark getOpponentMark(Mark playerMark) {
        return (playerMark == Mark.X) ? Mark.O : Mark.X;
    }

    public boolean isTileEmpty(int row, int col) {
        return board[row][col] == Mark.EMPTY;
    }
}

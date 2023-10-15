import java.util.ArrayList;

class Board {
    private Mark[][] board;
    private int size;

    // Constructeur pour initialiser le plateau de jeu vide
    public Board() {
        size = 3; // Définir la taille sur 3x3
        board = new Mark[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Mark.EMPTY;
            }
        }
    }

    // Place la pièce 'mark' sur le plateau à la position spécifiée dans Move
    public void play(Move m, Mark mark) {
        int row = m.getRow();
        int col = m.getCol();

        // Assurez-vous que la case est valide et vide
        if (row >= 0 && row < size && col >= 0 && col < size && board[row][col] == Mark.EMPTY) {
            board[row][col] = mark;
        } else {
            // Gérer les erreurs si la case n'est pas valide ou déjà occupée
            //System.out.println("Mouvement invalide !");
        }
    }

    public int evaluate(Mark mark) {
        Mark opponentMark = (mark == Mark.X) ? Mark.O : Mark.X;
        int size = getSize();

        // Vérifiez les conditions de victoire du joueur
        for (int i = 0; i < size; i++) {
            if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) {
                return 100;  // Le joueur gagne en ligne
            }
            if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark) {
                return 100;  // Le joueur gagne en colonne
            }
        }

        // Vérifiez les conditions de victoire de l'adversaire et retournez -100
        for (int i = 0; i < size; i++) {
            if (board[i][0] == opponentMark && board[i][1] == opponentMark && board[i][2] == opponentMark) {
                return -100;  // L'adversaire gagne en ligne
            }
            if (board[0][i] == opponentMark && board[1][i] == opponentMark && board[2][i] == opponentMark) {
                return -100;  // L'adversaire gagne en colonne
            }
        }

        // Vérifiez les diagonales
        if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) {
            return 100;  // Le joueur gagne en diagonale principale
        }
        if (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark) {
            return 100;  // Le joueur gagne en diagonale inverse
        }

        // Vérifiez les victoires en diagonale de l'adversaire
        if (board[0][0] == opponentMark && board[1][1] == opponentMark && board[2][2] == opponentMark) {
            return -100;  // L'adversaire gagne en diagonale principale
        }
        if (board[0][2] == opponentMark && board[1][1] == opponentMark && board[2][0] == opponentMark) {
            return -100;  // L'adversaire gagne en diagonale inverse
        }

        // Vérifiez un match nul
        boolean isDraw = true;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == Mark.EMPTY) {
                    isDraw = false;
                    break;
                }
            }
        }

        if (isDraw) {
            return -1;  // C'est un match nul
        }

        // Si aucune victoire, défaite ou match nul n'est détecté, le jeu continue.
        return 0;
    }

    public int getSize() {
        return size;
    }

    public Mark[][] getBoard() {
        return board;
    }

    public ArrayList<Move> getAvailableMoves() {
        ArrayList<Move> availableMoves = new ArrayList<>();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == Mark.EMPTY) {
                    availableMoves.add(new Move(row, col));
                }
            }
        }

        return availableMoves;
    }

    public Mark getOpponentMark(Mark playerMark) {
        return (playerMark == Mark.X) ? Mark.O : Mark.X;
    }

    public boolean isTileMarked(int row, int col) {
        return board[row][col] != Mark.EMPTY;
    }
}

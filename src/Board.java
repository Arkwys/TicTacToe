// Enumeration pour représenter les marques sur le plateau
class Board {
    private Mark[][] board;
    private int size;

    private Mark winner;

    // Constructeur pour initialiser le plateau de jeu vide
    public Board(int size) {
        this.size = size;
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
            System.out.println("Mouvement invalide !");
        }
    }

    // Évalue l'état du jeu pour la marque spécifiée
    public int evaluate(Mark mark) {
        // À implémenter : logique d'évaluation du jeu

        // Vérifier les lignes, les colonnes et les diagonales pour déterminer le résultat du jeu.

        // Par exemple, vérifier les lignes horizontales
        for (int row = 0; row < size; row++) {
            int count = 0;
            for (int col = 0; col < size; col++) {
                if (board[row][col] == mark) {
                    count++;
                } else if (board[row][col] != Mark.EMPTY) {
                    // Si une case est occupée par l'adversaire, aucune victoire possible dans cette ligne
                    count = 0;
                    break;
                }
            }
            if (count == size) {
                // Le joueur 'mark' a gagné
                winner = mark;
                return 100;
            }
            if (winner != null && winner != mark) {
                return -100;
            }
        }

        // Vous devrez faire des vérifications similaires pour les colonnes et les diagonales.

        // Si aucune victoire n'est détectée, vous pouvez vérifier s'il y a égalité (match nul).
        boolean isTie = true;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
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

    public int getSize() {
        return size;
    }

    public Mark[][] getBoard() {
        return board;
    }
}

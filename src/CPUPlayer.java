import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer {
    private int numExploredNodes;
    private Mark cpuMark; // Le joueur MAX (X ou O)

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu) {
        this.cpuMark = cpu;
    }

    // Ne pas changer cette méthode
    public int getNumOfExploredNodes() {
        return numExploredNodes;
    }

    // Retourne la liste des coups possibles en utilisant l'algorithme Minimax
    public ArrayList<Move> getNextMoveMinMax(Board board) {
        numExploredNodes = 0;

        ArrayList<Move> possibleMoves = new ArrayList<>();

        // À implémenter : Utiliser l'algorithme Minimax pour générer les coups possibles
        // Ajouter les coups possibles à la liste possibleMoves
        numExploredNodes = 0;
        ArrayList<Move> possibleMoves = new ArrayList<>();

        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getBoard()[i][j] == Mark.EMPTY) {
                    board.play(new Move(i, j), cpuMark);
                    int score = minimax(board, 0, false);
                    board.play(new Move(i, j), Mark.EMPTY);

                    if (score > bestScore) {
                        bestScore = score;
                        possibleMoves.clear();
                        possibleMoves.add(new Move(i, j));
                    } else if (score == bestScore) {
                        possibleMoves.add(new Move(i, j));
                    }
                }
            }
        }
        
        return possibleMoves;
    }
    
    private int minimax(Board board, int depth, boolean isMaximizing) {
        numExploredNodes++;

        int result = board.evaluate(cpuMark);

        if (result != -1 || depth == 0) {
            return result;
        }

        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;

            for (Move move : getAllPossibleMoves(board)) {
                board.play(move, cpuMark);
                int eval = minimax(board, depth - 1, false);
                board.play(move, Mark.EMPTY);
                maxEval = Math.max(maxEval, eval);
            }

            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;

            for (Move move : getAllPossibleMoves(board)) {
                board.play(move, getOpponentMark(cpuMark));
                int eval = minimax(board, depth - 1, true);
                board.play(move, Mark.EMPTY);
                minEval = Math.min(minEval, eval);
            }

            return minEval;
        }
    }

    // Retourne la liste des coups possibles en utilisant l'algorithme Alpha-Beta Pruning
    public ArrayList<Move> getNextMoveAB(Board board) {
        numExploredNodes = 0;

        ArrayList<Move> possibleMoves = new ArrayList<>();

        // À implémenter : Utiliser l'algorithme Alpha-Beta Pruning pour générer les coups possibles
        // Ajouter les coups possibles à la liste possibleMoves

        return possibleMoves;
    }
}

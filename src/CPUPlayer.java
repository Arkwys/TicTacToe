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

        return possibleMoves;
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

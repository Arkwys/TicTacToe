import java.util.ArrayList;

class CPUPlayer {
    private int numExploredNodes;
    private Mark cpu;

    public CPUPlayer(Mark cpu) {
        this.cpu = cpu;
    }

    public int getNumOfExploredNodes() {
        return numExploredNodes;
    }

    public ArrayList<Move> getNextMoveMinMax(Board board) {
        numExploredNodes = 0;
        ArrayList<Move> possibleMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;

        for (Move move : board.getAvailableMoves()) {
            board.play(move, cpu);
            int score = minimax(board, 0, false);
            board.play(move, Mark.EMPTY);

            if (score > bestScore) {
                possibleMoves.clear();
                bestScore = score;
            }

            if (score == bestScore) {
                possibleMoves.add(move);
            }
        }

        return possibleMoves;
    }

    public ArrayList<Move> getNextMoveAB(Board board) {
        numExploredNodes = 0;
        ArrayList<Move> possibleMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (Move move : board.getAvailableMoves()) {
            board.play(move, cpu);
            int score = alphaBeta(board, 0, alpha, beta, false);
            board.play(move, Mark.EMPTY);

            if (score > bestScore) {
                possibleMoves.clear();
                bestScore = score;
            }

            if (score == bestScore) {
                possibleMoves.add(move);
            }

            alpha = Math.max(alpha, bestScore);

            if (alpha >= beta) {
                return possibleMoves; // Prune the remaining branches
            }
        }

        return possibleMoves;
    }

    private int minimax(Board board, int depth, boolean isMaximizing) {
        numExploredNodes++;

        int result = board.evaluate(cpu);
        if (result != 0) {
            return result;
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Move move : board.getAvailableMoves()) {
            board.play(move, isMaximizing ? cpu : board.getOpponentMark(cpu));
            int score = minimax(board, depth + 1, !isMaximizing);
            board.play(move, Mark.EMPTY);

            bestScore = isMaximizing ? Math.max(score, bestScore) : Math.min(score, bestScore);
        }

        return bestScore;
    }

    private int alphaBeta(Board board, int depth, int alpha, int beta, boolean isMaximizing) {
        numExploredNodes++;

        int result = board.evaluate(cpu);
        if (result != 0) {
            return result;
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Move move : board.getAvailableMoves()) {
            board.play(move, isMaximizing ? cpu : board.getOpponentMark(cpu));
            int score = alphaBeta(board, depth + 1, alpha, beta, !isMaximizing);
            board.play(move, Mark.EMPTY);

            if (isMaximizing) {
                bestScore = Math.max(score, bestScore);
                alpha = Math.max(alpha, bestScore);
            } else {
                bestScore = Math.min(score, bestScore);
                beta = Math.min(beta, bestScore);
            }

            if (alpha >= beta) {
                return bestScore; // Prune the remaining branches
            }
        }

        return bestScore;
    }
}

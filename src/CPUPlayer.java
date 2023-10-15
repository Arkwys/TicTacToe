import java.util.ArrayList;
public class CPUPlayer {
    private int numExploredNodes;
    private Mark cpu;
    private static final int MAX_DEPTH = 6;

    public CPUPlayer(Mark cpu) {
        this.cpu = cpu;
    }

    public int getNumOfExploredNodes() {
        return numExploredNodes;
    }

    public ArrayList<Move> getNextMoveMinMax(Board board) {
        numExploredNodes = 0;
        int bestValue = Integer.MIN_VALUE;
        ArrayList<Move> bestMoves = new ArrayList<>();

        for (Move move : board.getAvailableMoves()) {
            board.play(move, cpu);
            int moveValue = miniMax(board, MAX_DEPTH, false);
            board.play(move, Mark.EMPTY);

            if (moveValue > bestValue) {
                bestValue = moveValue;
                bestMoves.clear();
                bestMoves.add(move);
            } else if (moveValue == bestValue) {
                bestMoves.add(move);
            }
        }
        return bestMoves;
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

    private int miniMax(Board board, int depth, boolean isMaximizing) {
        numExploredNodes++;

        int boardVal = board.evaluate(cpu);

        // Terminal node (win/lose/draw) or max depth reached.
        if (Math.abs(boardVal) == 100 || depth == 0 || board.getAvailableMoves().isEmpty()) {
            return boardVal;
        }

        // Maximizing player, find the maximum attainable value.
        if (isMaximizing) {
            int highestVal = Integer.MIN_VALUE;
            for (Move move : board.getAvailableMoves()) {
                board.play(move, cpu);
                int moveValue = miniMax(board, depth - 1, false);
                board.play(move, Mark.EMPTY);
                highestVal = Math.max(highestVal, moveValue);
            }
            return highestVal;
        } else {
            // Minimizing player, find the minimum attainable value;
            int lowestVal = Integer.MAX_VALUE;
            for (Move move : board.getAvailableMoves()) {
                board.play(move, board.getOpponentMark(cpu));
                int moveValue = miniMax(board, depth - 1, true);
                board.play(move, Mark.EMPTY);
                lowestVal = Math.min(lowestVal, moveValue);
            }
            return lowestVal;
        }
    }

    private int alphaBeta(Board board, int depth, int alpha, int beta, boolean isMaximizing) {
        numExploredNodes++;

        int boardVal = board.evaluate(cpu);

        // Terminal node (win/lose/draw) or max depth reached.
        if (Math.abs(boardVal) == 100 || depth == 0 || board.getAvailableMoves().isEmpty()) {
            return boardVal;
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Move move : board.getAvailableMoves()) {
            board.play(move, isMaximizing ? cpu : board.getOpponentMark(cpu));
            int score = alphaBeta(board, depth - 1, alpha, beta, !isMaximizing);
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

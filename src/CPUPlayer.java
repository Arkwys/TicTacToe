import java.util.ArrayList;
public class CPUPlayer {
    private Mark cpu;
    private static final int MAX_DEPTH = 6;

    public CPUPlayer(Mark cpu) {
        this.cpu = cpu;
    }

    public ArrayList<Move> getNextMoveMinMax(Board board) {
        int bestScore = Integer.MIN_VALUE;
        ArrayList<Move> bestMoves = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isTileEmpty(i, j)) {
                    board.play(new Move(i, j), cpu);
                    int score = miniMax(board, 0, true);
                    board.play(new Move(i, j), Mark.EMPTY);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMoves.clear();
                    }
                    if (score == bestScore) {
                        bestMoves.add(new Move(i, j));
                    }
                }
            }
        }
        return bestMoves;
    }

    public ArrayList<Move> getNextMoveAB(Board board) {
        ArrayList<Move> possibleMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (Move move : board.getAvailableMoves()) {
            board.play(move, cpu);
            int score = alphaBeta(board, 0, alpha, beta, true);
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
        if (board.evaluate(cpu) != -1) {
            return board.evaluate(cpu) - depth;
        }
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isTileEmpty(i, j)) {
                        board.play(new Move(i, j), cpu);
                        int score = miniMax(board, depth + 1, false);
                        board.play(new Move(i, j), Mark.EMPTY);
                        bestScore = Integer.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isTileEmpty(i, j)) {
                        board.play(new Move(i, j), board.getOpponentMark(cpu));
                        int score = -(miniMax(board, depth + 1, true));
                        board.play(new Move(i, j), Mark.EMPTY);
                        bestScore = Integer.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    private int alphaBeta(Board board, int depth, int alpha, int beta, boolean isMaximizing) {
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

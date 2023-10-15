import java.util.ArrayList;

public class CPUPlayer {
    private int numExploredNodes;
    private Mark playerMax;
    private Mark playerMin;

    public CPUPlayer(Mark cpu) {
        this.playerMax = cpu;
        this.playerMin = (cpu == Mark.X) ? Mark.O : Mark.X;
    }

    public int getNumOfExploredNodes() {
        return numExploredNodes;
    }

    public ArrayList<Move> getNextMoveAB(Board board) {
        numExploredNodes = 0;
        int bestScore = Integer.MIN_VALUE;
        ArrayList<Move> bestMoves = new ArrayList<>();
        ArrayList<Move> possibleMoves = generateMoves(board);

        for (Move move : possibleMoves) {
            board.play(move, playerMax);
            int score = alphaBeta(board, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
            board.undoMove(move);

            if (score > bestScore) {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(move);
            } else if (score == bestScore) {
                bestMoves.add(move);
            }
        }

        return bestMoves;
    }


    private int alphaBeta(Board board, int depth, int alpha, int beta, boolean isMaximizingPlayer) {
        numExploredNodes++;
        int result = board.evaluate(playerMax);

        if (result != 0 || depth >= 6) { // Increase the depth for better performance
            return result;
        }

        ArrayList<Move> possibleMoves = generateMoves(board);

        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Move move : possibleMoves) {
                board.play(move, playerMax);
                int eval = alphaBeta(board, depth + 1, alpha, beta, false);
                board.undoMove(move);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Move move : possibleMoves) {
                board.play(move, playerMin);
                int eval = alphaBeta(board, depth + 1, alpha, beta, true);
                board.undoMove(move);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return minEval;
        }
    }

    private ArrayList<Move> generateMoves(Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getMark(i, j) == Mark.EMPTY) {
                    moves.add(new Move(i, j));
                }
            }
        }
        return moves;
    }
}

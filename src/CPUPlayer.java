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
            return bestScore - depth;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isTileEmpty(i, j)) {
                        board.play(new Move(i, j), board.getOpponentMark(cpu));
                        int score = miniMax(board, depth + 1, true);
                        board.play(new Move(i, j), Mark.EMPTY);
                        bestScore = Integer.min(score, bestScore);
                    }
                }
            }
            return bestScore + depth;
        }
    }

    public ArrayList<Move> getNextMoveAB(Board board) {
        int bestScore = Integer.MIN_VALUE;
        ArrayList<Move> bestMoves = new ArrayList<>();
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isTileEmpty(i, j)) {
                    board.play(new Move(i, j), cpu);
                    int score = alphaBeta(board, 0, alpha, beta, false);
                    board.play(new Move(i, j), Mark.EMPTY);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMoves.clear();
                    }
                    if (score == bestScore) {
                        bestMoves.add(new Move(i, j));
                    }
                    alpha = Math.max(alpha, bestScore);
                }
            }
        }
        return bestMoves;
    }


    private int alphaBeta(Board board, int depth, int alpha, int beta, boolean isMaximizing) {
        int result = board.evaluate(cpu);
        if (result != -1) {
            return result - depth;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isTileEmpty(i, j)) {
                        board.play(new Move(i, j), cpu);
                        int score = alphaBeta(board, depth + 1, alpha, beta, false);
                        board.play(new Move(i, j), Mark.EMPTY);
                        bestScore = Math.max(score, bestScore);
                        alpha = Math.max(alpha, bestScore);

                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
            return bestScore - depth;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isTileEmpty(i, j)) {
                        board.play(new Move(i, j), board.getOpponentMark(cpu));
                        int score = alphaBeta(board, depth + 1, alpha, beta, true);
                        board.play(new Move(i, j), Mark.EMPTY);
                        bestScore = Math.min(score, bestScore);
                        beta = Math.min(beta, bestScore);

                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
            return bestScore + depth;
        }
    }

}

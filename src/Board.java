import java.util.ArrayList;
import java.util.List;

class Board {
    private Mark[][] board;
    private List<Move> moveHistory;

    public Board() {
        board = new Mark[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Mark.EMPTY;
            }
        }
        moveHistory = new ArrayList<>();
    }

    public void play(Move m, Mark mark) {
        board[m.getRow()][m.getCol()] = mark;
        moveHistory.add(m);
    }

    public void undoMove(Move m) {
        board[m.getRow()][m.getCol()] = Mark.EMPTY;
        moveHistory.remove(moveHistory.size() - 1);
    }

    public int evaluate(Mark mark) {
        //Victory Check
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) {
                return 100; // Win
            }
            if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark) {
                return 100; // Win
            }
        }
        if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) {
            return 100; // Win
        }
        if (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark) {
            return 100; // Win
        }

        // Tie Check
        boolean isDraw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Mark.EMPTY) {
                    isDraw = false;
                    break;
                }
            }
        }
        if (isDraw) {
            return 0; 
        }

        return -100; // No victory, no tie (game continues)
    }


    public Mark getMark(int row, int col) {
        return board[row][col];
    }

    public void setMark(int row, int col, Mark mark) {
        board[row][col] = mark;
    }

   
    public boolean isGameOver() {
        return evaluate(Mark.X) != 0 || evaluate(Mark.O) != 0;
    }

   
    public List<Move> getAvailableMoves() {
        List<Move> availableMoves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Mark.EMPTY) {
                    availableMoves.add(new Move(i, j));
                }
            }
        }
        return availableMoves;
    }
}

// Copyright 2025 UNN-CS

package com.mycompany.app;

public class AIEngine {
    private Board board_obj;
    
    public AIEngine(Board b_obj) {
        this.board_obj = b_obj;
    }
    
    public int[] getOptimalMove() {
        int bestScoreVal = Integer.MIN_VALUE;
        int[] bestMovePos = {-1, -1};
        
        for (int row_cur = 0; row_cur < 3; row_cur++) {
            for (int col_cur = 0; col_cur < 3; col_cur++) {
                if (board_obj.canMoveHere(row_cur, col_cur)) {
                    board_obj.doMove(row_cur, col_cur);
                    int scoreVal = minimaxAlgo(false);
                    undoLastMove(row_cur, col_cur);
                    if (scoreVal > bestScoreVal) {
                        bestScoreVal = scoreVal;
                        bestMovePos = new int[]{row_cur, col_cur};
                    }
                }
            }
        }
        return bestMovePos;
    }
    
    private int minimaxAlgo(boolean isMaxTurn) {
        GameState stateFlag = board_obj.getGameFlag();
        if (stateFlag == GameState.FIRST_WON) return 10;
        if (stateFlag == GameState.SECOND_WON) return -10;
        if (stateFlag == GameState.TIE_GAME) return 0;
        
        if (isMaxTurn) {
            int bestVal = Integer.MIN_VALUE;
            for (int r_cur = 0; r_cur < 3; r_cur++) {
                for (int c_cur = 0; c_cur < 3; c_cur++) {
                    if (board_obj.canMoveHere(r_cur, c_cur)) {
                        board_obj.doMove(r_cur, c_cur);
                        int scoreVal = minimaxAlgo(false);
                        undoLastMove(r_cur, c_cur);
                        bestVal = Math.max(scoreVal, bestVal);
                    }
                }
            }
            return bestVal;
        } else {
            int bestVal = Integer.MAX_VALUE;
            for (int r_cur = 0; r_cur < 3; r_cur++) {
                for (int c_cur = 0; c_cur < 3; c_cur++) {
                    if (board_obj.canMoveHere(r_cur, c_cur)) {
                        board_obj.doMove(r_cur, c_cur);
                        int scoreVal = minimaxAlgo(true);
                        undoLastMove(r_cur, c_cur);
                        bestVal = Math.min(scoreVal, bestVal);
                    }
                }
            }
            return bestVal;
        }
    }
    
    private void undoLastMove(int row_pos, int col_pos) {
        board_obj.doMove(row_pos, col_pos);
        board_obj.doMove(row_pos, col_pos);
    }
}

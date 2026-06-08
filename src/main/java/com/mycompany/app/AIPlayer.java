// Copyright 2025 UNN-CS
// Nazyrov A.A.

package com.mycompany.app;

public class AiPlayer {
    private BoardGrid board;
    
    public AiPlayer(BoardGrid b) {
        this.board = b;
    }
    
    public int[] findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestPos = {-1, -1};
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.isMoveLegal(row, col)) {
                    board.placeMark(row, col);
                    int score = minimax(false);
                    undoMove(row, col);
                    if (score > bestScore) {
                        bestScore = score;
                        bestPos = new int[]{row, col};
                    }
                }
            }
        }
        return bestPos;
    }
    
    private int minimax(boolean isMaximizing) {
        GameStatus state = board.getGameStatus();
        if (state == GameStatus.X_VICTORY) return 10;
        if (state == GameStatus.O_VICTORY) return -10;
        if (state == GameStatus.TIE) return 0;
        
        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board.isMoveLegal(row, col)) {
                        board.placeMark(row, col);
                        int score = minimax(false);
                        undoMove(row, col);
                        best = Math.max(score, best);
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board.isMoveLegal(row, col)) {
                        board.placeMark(row, col);
                        int score = minimax(true);
                        undoMove(row, col);
                        best = Math.min(score, best);
                    }
                }
            }
            return best;
        }
    }
    
    private void undoMove(int row, int col) {
        board.placeMark(row, col);
        board.placeMark(row, col);
    }
}

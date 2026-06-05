// Copyright 2025 UNN-CS

package com.mycompany.app;

public class AIPlayer {
    private Board board;
    
    public AIPlayer(Board b) {
        board = b;
    }
    
    public int[] getBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] best = {-1, -1};
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isValidMove(i, j)) {
                    board.makeMove(i, j);
                    int score = minimax(false);
                    // ?????????? ???
                    board.makeMove(i, j); // ?????????? ?????? ??????
                    if (score > bestScore) {
                        bestScore = score;
                        best = new int[]{i, j};
                    }
                }
            }
        }
        return best;
    }
    
    private int minimax(boolean isMax) {
        GameState s = board.getState();
        if (s == GameState.X_WON) return 10;
        if (s == GameState.O_WON) return -10;
        if (s == GameState.DRAW) return 0;
        
        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isValidMove(i, j)) {
                        board.makeMove(i, j);
                        int score = minimax(false);
                        board.makeMove(i, j);
                        best = Math.max(score, best);
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isValidMove(i, j)) {
                        board.makeMove(i, j);
                        int score = minimax(true);
                        board.makeMove(i, j);
                        best = Math.min(score, best);
                    }
                }
            }
            return best;
        }
    }
}

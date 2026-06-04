package com.nazyrov;

public class AIPlayer {
    private Board board;
    
    public AIPlayer(Board board) {
        this.board = board;
    }
    
    public int[] getBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[]{-1, -1};
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isMoveValid(i, j)) {
                    board.makeMove(i, j);
                    int score = minimax(false);
                    board.undoMove(i, j);
                    
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }
        return bestMove;
    }
    
    private int minimax(boolean isMaximizing) {
        GameState state = board.getGameState();
        
        if (state == GameState.X_WON) return 10;
        if (state == GameState.O_WON) return -10;
        if (state == GameState.DRAW) return 0;
        
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isMoveValid(i, j)) {
                        board.makeMove(i, j);
                        int score = minimax(false);
                        board.undoMove(i, j);
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isMoveValid(i, j)) {
                        board.makeMove(i, j);
                        int score = minimax(true);
                        board.undoMove(i, j);
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
}
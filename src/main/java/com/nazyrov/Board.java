package com.nazyrov;

public class Board {
    private char[][] cells;
    private char currentPlayer;
    private GameState state;
    
    public Board() {
        cells = new char[3][3];
        currentPlayer = 'X';
        state = GameState.IN_PROGRESS;
        initializeBoard();
    }
    
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = '-';
            }
        }
    }
    
    public boolean makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            cells[row][col] = currentPlayer;
            checkGameState();
            switchPlayer();
            return true;
        }
        return false;
    }
    
    public void undoMove(int row, int col) {
        cells[row][col] = '-';
        switchPlayer();
        state = GameState.IN_PROGRESS;
    }
    
    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && cells[row][col] == '-';
    }
    
    private void checkGameState() {
        if (checkWin(currentPlayer)) {
            state = (currentPlayer == 'X') ? GameState.X_WON : GameState.O_WON;
        } else if (isBoardFull()) {
            state = GameState.DRAW;
        }
    }
    
    private boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (cells[i][0] == player && cells[i][1] == player && cells[i][2] == player)
                return true;
            if (cells[0][i] == player && cells[1][i] == player && cells[2][i] == player)
                return true;
        }
        if (cells[0][0] == player && cells[1][1] == player && cells[2][2] == player)
            return true;
        if (cells[0][2] == player && cells[1][1] == player && cells[2][0] == player)
            return true;
        return false;
    }
    
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == '-') return false;
            }
        }
        return true;
    }
    
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    
    public char getCurrentPlayer() {
        return currentPlayer;
    }
    
    public GameState getGameState() {
        return state;
    }
    
    public char getCell(int row, int col) {
        return cells[row][col];
    }
    
    public boolean isGameOver() {
        return state != GameState.IN_PROGRESS;
    }
    
    public boolean isMoveValid(int row, int col) {
        return isValidMove(row, col);
    }
    
    public void display() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
    }
}

// Copyright 2025 UNN-CS
// Nazyrov A.A.

package com.mycompany.app;

public class BoardGrid {
    private char[][] cells;
    private char turn;
    private GameStatus status;
    
    public BoardGrid() {
        cells = new char[3][3];
        turn = 'X';
        status = GameStatus.PLAYING;
        clearField();
    }
    
    private void clearField() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col] = '-';
            }
        }
    }
    
    public boolean placeMark(int row, int col) {
        if (isValidPosition(row, col)) {
            cells[row][col] = turn;
            analyzeGameState();
            flipTurn();
            return true;
        }
        return false;
    }
    
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && cells[row][col] == '-';
    }
    
    private void analyzeGameState() {
        if (checkWinCondition(turn)) {
            status = (turn == 'X') ? GameStatus.X_VICTORY : GameStatus.O_VICTORY;
        } else if (isFieldFull()) {
            status = GameStatus.TIE;
        }
    }
    
    private boolean checkWinCondition(char player) {
        for (int i = 0; i < 3; i++) {
            if (cells[i][0] == player && cells[i][1] == player && cells[i][2] == player) return true;
            if (cells[0][i] == player && cells[1][i] == player && cells[2][i] == player) return true;
        }
        if (cells[0][0] == player && cells[1][1] == player && cells[2][2] == player) return true;
        if (cells[0][2] == player && cells[1][1] == player && cells[2][0] == player) return true;
        return false;
    }
    
    private boolean isFieldFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (cells[row][col] == '-') return false;
            }
        }
        return true;
    }
    
    private void flipTurn() {
        turn = (turn == 'X') ? 'O' : 'X';
    }
    
    public char getCurrentTurn() { return turn; }
    public GameStatus getGameStatus() { return status; }
    public char getCellValue(int row, int col) { return cells[row][col]; }
    public boolean isMoveLegal(int row, int col) { return isValidPosition(row, col); }
    public boolean isGameFinished() { return status != GameStatus.PLAYING; }
    
    public void printBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(cells[row][col] + " ");
            }
            System.out.println();
        }
    }
}

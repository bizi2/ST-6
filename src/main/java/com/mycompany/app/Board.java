// Copyright 2025 UNN-CS

package com.mycompany.app;

public class Board {
    private char[][] cells;
    private char currentPlayer;
    private GameState state;
    
    public Board() {
        cells = new char[3][3];
        currentPlayer = 'X';
        state = GameState.IN_PROGRESS;
        initBoard();
    }
    
    private void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = '-';
            }
        }
    }
    
    public boolean makeMove(int row, int col) {
        if (isValid(row, col)) {
            cells[row][col] = currentPlayer;
            checkState();
            switchPlayer();
            return true;
        }
        return false;
    }
    
    private boolean isValid(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && cells[row][col] == '-';
    }
    
    private void checkState() {
        if (win(currentPlayer)) {
            state = (currentPlayer == 'X') ? GameState.X_WON : GameState.O_WON;
        } else if (isFull()) {
            state = GameState.DRAW;
        }
    }
    
    private boolean win(char p) {
        for (int i = 0; i < 3; i++) {
            if (cells[i][0] == p && cells[i][1] == p && cells[i][2] == p) return true;
            if (cells[0][i] == p && cells[1][i] == p && cells[2][i] == p) return true;
        }
        if (cells[0][0] == p && cells[1][1] == p && cells[2][2] == p) return true;
        if (cells[0][2] == p && cells[1][1] == p && cells[2][0] == p) return true;
        return false;
    }
    
    private boolean isFull() {
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
    
    public char getCurrentPlayer() { return currentPlayer; }
    public GameState getState() { return state; }
    public char getCell(int row, int col) { return cells[row][col]; }
    public boolean isGameOver() { return state != GameState.IN_PROGRESS; }
    public boolean isValidMove(int row, int col) { return isValid(row, col); }
    
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
    }
}

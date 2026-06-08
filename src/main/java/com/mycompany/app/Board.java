// Copyright 2025 UNN-CS

package com.mycompany.app;

public class Board {
    private char[][] board_arr;
    private char curr_player;
    private GameState game_flag;
    
    public Board() {
        board_arr = new char[3][3];
        curr_player = 'X';
        game_flag = GameState.GAME_ACTIVE;
        initBoardArr();
    }
    
    private void initBoardArr() {
        for (int row_idx = 0; row_idx < 3; row_idx++) {
            for (int col_idx = 0; col_idx < 3; col_idx++) {
                board_arr[row_idx][col_idx] = '-';
            }
        }
    }
    
    public boolean doMove(int row_pos, int col_pos) {
        if (isValidPos(row_pos, col_pos)) {
            board_arr[row_pos][col_pos] = curr_player;
            updateGameStatus();
            switchCurrPlayer();
            return true;
        }
        return false;
    }
    
    private boolean isValidPos(int r_pos, int c_pos) {
        return r_pos >= 0 && r_pos < 3 && c_pos >= 0 && c_pos < 3 && board_arr[r_pos][c_pos] == '-';
    }
    
    private void updateGameStatus() {
        if (checkWinCondition(curr_player)) {
            game_flag = (curr_player == 'X') ? GameState.FIRST_WON : GameState.SECOND_WON;
        } else if (isBoardFull()) {
            game_flag = GameState.TIE_GAME;
        }
    }
    
    private boolean checkWinCondition(char player_char) {
        for (int idx = 0; idx < 3; idx++) {
            if (board_arr[idx][0] == player_char && board_arr[idx][1] == player_char && board_arr[idx][2] == player_char) return true;
            if (board_arr[0][idx] == player_char && board_arr[1][idx] == player_char && board_arr[2][idx] == player_char) return true;
        }
        if (board_arr[0][0] == player_char && board_arr[1][1] == player_char && board_arr[2][2] == player_char) return true;
        if (board_arr[0][2] == player_char && board_arr[1][1] == player_char && board_arr[2][0] == player_char) return true;
        return false;
    }
    
    private boolean isBoardFull() {
        for (int r_idx = 0; r_idx < 3; r_idx++) {
            for (int c_idx = 0; c_idx < 3; c_idx++) {
                if (board_arr[r_idx][c_idx] == '-') return false;
            }
        }
        return true;
    }
    
    private void switchCurrPlayer() {
        curr_player = (curr_player == 'X') ? 'O' : 'X';
    }
    
    public char getCurrPlayer() { return curr_player; }
    public GameState getGameFlag() { return game_flag; }
    public char getCellVal(int r_pos, int c_pos) { return board_arr[r_pos][c_pos]; }
    public boolean canMoveHere(int r_pos, int c_pos) { return isValidPos(r_pos, c_pos); }
    public boolean isGameFinished() { return game_flag != GameState.GAME_ACTIVE; }
    
    public void printBoardState() {
        for (int r_idx = 0; r_idx < 3; r_idx++) {
            for (int c_idx = 0; c_idx < 3; c_idx++) {
                System.out.print(board_arr[r_idx][c_idx] + " ");
            }
            System.out.println();
        }
    }
}

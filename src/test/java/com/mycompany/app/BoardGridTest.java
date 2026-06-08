// Copyright 2025 UNN-CS
// Nazyrov A.A.

package com.mycompany.app;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BoardGridTest {
    
    private BoardGrid board;
    
    @Before
    public void createNewBoard() {
        board = new BoardGrid();
    }
    
    // ===== ????????? ????????? =====
    @Test
    public void newBoardHasEmptyCells() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                assertEquals('-', board.getCellValue(row, col));
            }
        }
    }
    
    @Test
    public void newBoardHasXTurn() {
        assertEquals('X', board.getCurrentTurn());
    }
    
    @Test
    public void newBoardIsPlaying() {
        assertEquals(GameStatus.PLAYING, board.getGameStatus());
    }
    
    // ===== ?????????? ????? =====
    @Test
    public void placeMarkAtValidPosition() {
        assertTrue(board.placeMark(0, 0));
        assertEquals('X', board.getCellValue(0, 0));
    }
    
    @Test
    public void cannotPlaceOnOccupiedCell() {
        board.placeMark(0, 0);
        assertFalse(board.placeMark(0, 0));
    }
    
    @Test
    public void cannotPlaceOutsideBoard() {
        assertFalse(board.placeMark(3, 3));
        assertFalse(board.placeMark(-1, 0));
    }
    
    @Test
    public void turnSwitchesAfterMove() {
        board.placeMark(0, 0);
        assertEquals('O', board.getCurrentTurn());
    }
    
    // ===== ???????? ????? =====
    @Test
    public void xWinsTopRow() {
        board.placeMark(0, 0);
        board.placeMark(1, 0);
        board.placeMark(0, 1);
        board.placeMark(1, 1);
        board.placeMark(0, 2);
        assertEquals(GameStatus.X_VICTORY, board.getGameStatus());
    }
    
    @Test
    public void oWinsMiddleColumn() {
        board.placeMark(0, 0);
        board.placeMark(0, 1);
        board.placeMark(1, 0);
        board.placeMark(1, 1);
        board.placeMark(2, 0);
        board.placeMark(2, 1);
        assertEquals(GameStatus.O_VICTORY, board.getGameStatus());
    }
    
    @Test
    public void xWinsMainDiagonal() {
        board.placeMark(0, 0);
        board.placeMark(0, 1);
        board.placeMark(1, 1);
        board.placeMark(0, 2);
        board.placeMark(2, 2);
        assertEquals(GameStatus.X_VICTORY, board.getGameStatus());
    }
    
    @Test
    public void oWinsAntiDiagonal() {
        board.placeMark(0, 1);
        board.placeMark(0, 2);
        board.placeMark(1, 0);
        board.placeMark(1, 1);
        board.placeMark(2, 2);
        board.placeMark(2, 0);
        assertEquals(GameStatus.O_VICTORY, board.getGameStatus());
    }
    
    // ===== ????? =====
    @Test
    public void fullBoardEndsInTie() {
        board.placeMark(0, 0);
        board.placeMark(0, 1);
        board.placeMark(0, 2);
        board.placeMark(1, 1);
        board.placeMark(1, 0);
        board.placeMark(1, 2);
        board.placeMark(2, 1);
        board.placeMark(2, 0);
        board.placeMark(2, 2);
        assertEquals(GameStatus.TIE, board.getGameStatus());
    }
    
    // ===== ??????????? ?????? =====
    @Test
    public void isMoveLegalWorks() {
        assertTrue(board.isMoveLegal(0, 0));
        board.placeMark(0, 0);
        assertFalse(board.isMoveLegal(0, 0));
    }
    
    @Test
    public void isGameFinishedAfterWin() {
        board.placeMark(0, 0);
        board.placeMark(1, 0);
        board.placeMark(0, 1);
        board.placeMark(1, 1);
        board.placeMark(0, 2);
        assertTrue(board.isGameFinished());
    }
    
    @Test
    public void isGameFinishedAfterTie() {
        board.placeMark(0, 0);
        board.placeMark(0, 1);
        board.placeMark(0, 2);
        board.placeMark(1, 1);
        board.placeMark(1, 0);
        board.placeMark(1, 2);
        board.placeMark(2, 1);
        board.placeMark(2, 0);
        board.placeMark(2, 2);
        assertTrue(board.isGameFinished());
    }
}

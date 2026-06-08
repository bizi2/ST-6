// Copyright 2025 UNN-CS
// Nazyrov A.A.

package com.mycompany.app;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    
    private Board boardObj;
    
    @Before
    public void setUpTest() {
        boardObj = new Board();
    }
    
    @Test
    public void testEmptyBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                assertEquals('-', boardObj.getCellVal(r, c));
            }
        }
    }
    
    @Test
    public void testFirstPlayer() {
        assertEquals('X', boardObj.getCurrPlayer());
    }
    
    @Test
    public void testValidMoveExecution() {
        assertTrue(boardObj.doMove(0, 0));
        assertEquals('X', boardObj.getCellVal(0, 0));
    }
    
    @Test
    public void testInvalidMoveOutOfRange() {
        assertFalse(boardObj.doMove(3, 3));
        assertFalse(boardObj.doMove(-1, 0));
    }
    
    @Test
    public void testOccupiedCellBlock() {
        boardObj.doMove(0, 0);
        assertFalse(boardObj.doMove(0, 0));
    }
    
    @Test
    public void testPlayerSwitching() {
        boardObj.doMove(0, 0);
        assertEquals('O', boardObj.getCurrPlayer());
    }
    
    @Test
    public void testXWinsTopRow() {
        boardObj.doMove(0, 0);
        boardObj.doMove(1, 0);
        boardObj.doMove(0, 1);
        boardObj.doMove(1, 1);
        boardObj.doMove(0, 2);
        assertEquals(GameState.FIRST_WON, boardObj.getGameFlag());
    }
    
    @Test
    public void testOWinsMiddleColumn() {
        boardObj.doMove(0, 0);
        boardObj.doMove(0, 1);
        boardObj.doMove(1, 0);
        boardObj.doMove(1, 1);
        boardObj.doMove(2, 0);
        boardObj.doMove(2, 1);
        assertEquals(GameState.SECOND_WON, boardObj.getGameFlag());
    }
    
    @Test
    public void testXWinsDiagonal() {
        boardObj.doMove(0, 0);
        boardObj.doMove(0, 1);
        boardObj.doMove(1, 1);
        boardObj.doMove(0, 2);
        boardObj.doMove(2, 2);
        assertEquals(GameState.FIRST_WON, boardObj.getGameFlag());
    }
    
    @Test
    public void testTieGame() {
        boardObj.doMove(0, 0);
        boardObj.doMove(0, 1);
        boardObj.doMove(0, 2);
        boardObj.doMove(1, 1);
        boardObj.doMove(1, 0);
        boardObj.doMove(1, 2);
        boardObj.doMove(2, 1);
        boardObj.doMove(2, 0);
        boardObj.doMove(2, 2);
        assertEquals(GameState.TIE_GAME, boardObj.getGameFlag());
    }
    
    @Test
    public void testValidPositionCheck() {
        assertTrue(boardObj.canMoveHere(0, 0));
        boardObj.doMove(0, 0);
        assertFalse(boardObj.canMoveHere(0, 0));
    }
    
    @Test
    public void testGameFinishAfterWin() {
        boardObj.doMove(0, 0);
        boardObj.doMove(1, 0);
        boardObj.doMove(0, 1);
        boardObj.doMove(1, 1);
        boardObj.doMove(0, 2);
        assertTrue(boardObj.isGameFinished());
    }
}

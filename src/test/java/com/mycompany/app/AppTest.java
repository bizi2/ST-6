// Copyright 2025 UNN-CS

package com.mycompany.app;

import static org.junit.Assert.*;
import org.junit.Test;

public class AppTest {
    
    @Test
    public void testInitialBoardEmpty() {
        Board b = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals('-', b.getCell(i, j));
            }
        }
    }
    
    @Test
    public void testInitialPlayerX() {
        Board b = new Board();
        assertEquals('X', b.getCurrentPlayer());
    }
    
    @Test
    public void testMakeValidMove() {
        Board b = new Board();
        assertTrue(b.makeMove(0, 0));
        assertEquals('X', b.getCell(0, 0));
    }
    
    @Test
    public void testMakeInvalidMoveOutOfBounds() {
        Board b = new Board();
        assertFalse(b.makeMove(3, 3));
        assertFalse(b.makeMove(-1, 0));
    }
    
    @Test
    public void testCannotMoveOnOccupied() {
        Board b = new Board();
        b.makeMove(0, 0);
        assertFalse(b.makeMove(0, 0));
    }
    
    @Test
    public void testPlayerSwitch() {
        Board b = new Board();
        b.makeMove(0, 0);
        assertEquals('O', b.getCurrentPlayer());
    }
    
    @Test
    public void testXWinsRow() {
        Board b = new Board();
        b.makeMove(0, 0);
        b.makeMove(1, 0);
        b.makeMove(0, 1);
        b.makeMove(1, 1);
        b.makeMove(0, 2);
        assertEquals(GameState.X_WON, b.getState());
    }
    
    @Test
    public void testOWinsColumn() {
        Board b = new Board();
        b.makeMove(0, 0);
        b.makeMove(0, 1);
        b.makeMove(1, 0);
        b.makeMove(1, 1);
        b.makeMove(2, 0);
        b.makeMove(2, 1);
        assertEquals(GameState.O_WON, b.getState());
    }
    
    @Test
    public void testXWinsDiagonal() {
        Board b = new Board();
        b.makeMove(0, 0);
        b.makeMove(0, 1);
        b.makeMove(1, 1);
        b.makeMove(0, 2);
        b.makeMove(2, 2);
        assertEquals(GameState.X_WON, b.getState());
    }
    
    @Test
    public void testDraw() {
        Board b = new Board();
        b.makeMove(0, 0);
        b.makeMove(0, 1);
        b.makeMove(0, 2);
        b.makeMove(1, 1);
        b.makeMove(1, 0);
        b.makeMove(1, 2);
        b.makeMove(2, 1);
        b.makeMove(2, 0);
        b.makeMove(2, 2);
        assertEquals(GameState.DRAW, b.getState());
    }
    
    @Test
    public void testIsValidMove() {
        Board b = new Board();
        assertTrue(b.isValidMove(0, 0));
        b.makeMove(0, 0);
        assertFalse(b.isValidMove(0, 0));
    }
    
    @Test
    public void testAIGetBestMoveNotNull() {
        Board b = new Board();
        AIPlayer ai = new AIPlayer(b);
        int[] move = ai.getBestMove();
        assertNotNull(move);
        assertEquals(2, move.length);
    }
    
    @Test
    public void testAIMoveValid() {
        Board b = new Board();
        AIPlayer ai = new AIPlayer(b);
        int[] move = ai.getBestMove();
        assertTrue(b.isValidMove(move[0], move[1]));
    }
}

// Copyright 2025 UNN-CS
// Nazyrov A.A.

package com.mycompany.app;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AiPlayerTest {
    
    private BoardGrid board;
    private AiPlayer ai;
    
    @Before
    public void init() {
        board = new BoardGrid();
        ai = new AiPlayer(board);
    }
    
    @Test
    public void aiReturnsValidMove() {
        int[] move = ai.findBestMove();
        assertNotNull(move);
        assertEquals(2, move.length);
        assertTrue(board.isMoveLegal(move[0], move[1]));
    }
    
    @Test
    public void aiMoveWithinBounds() {
        int[] move = ai.findBestMove();
        assertTrue(move[0] >= 0 && move[0] < 3);
        assertTrue(move[1] >= 0 && move[1] < 3);
    }
    
    @Test
    public void aiTakesCenterFirst() {
        int[] move = ai.findBestMove();
        assertTrue((move[0] == 1 && move[1] == 1) ||
                   (move[0] == 0 && move[1] == 0) ||
                   (move[0] == 0 && move[1] == 2) ||
                   (move[0] == 2 && move[1] == 0) ||
                   (move[0] == 2 && move[1] == 2));
    }
}

package com.nazyrov;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    
    private Board board;
    
    @BeforeEach
    void setUp() {
        board = new Board();
    }
    
    @Test
    void testInitialBoardEmpty() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals('-', board.getCell(i, j));
            }
        }
    }
    
    @Test
    void testInitialPlayerIsX() {
        assertEquals('X', board.getCurrentPlayer());
    }
    
    @Test
    void testMakeValidMove() {
        assertTrue(board.makeMove(0, 0));
        assertEquals('X', board.getCell(0, 0));
    }
    
    @Test
    void testMakeInvalidMove() {
        assertFalse(board.makeMove(3, 3));
        assertFalse(board.makeMove(-1, 0));
    }
    
    @Test
    void testCannotMoveOnOccupiedCell() {
        board.makeMove(0, 0);
        assertFalse(board.makeMove(0, 0));
    }
    
    @Test
    void testPlayerSwitchesAfterMove() {
        board.makeMove(0, 0);
        assertEquals('O', board.getCurrentPlayer());
    }
    
    @Test
    void testIsMoveValid() {
        assertTrue(board.isMoveValid(0, 0));
        board.makeMove(0, 0);
        assertFalse(board.isMoveValid(0, 0));
    }
    
    @Test
    void testGameNotOverInitially() {
        assertFalse(board.isGameOver());
    }
    
    @Test
    void testXWinsRow() {
        board.makeMove(0, 0); // X
        board.makeMove(1, 0); // O
        board.makeMove(0, 1); // X
        board.makeMove(1, 1); // O
        board.makeMove(0, 2); // X - wins
        assertEquals(GameState.X_WON, board.getGameState());
        assertTrue(board.isGameOver());
    }
    
    @Test
    void testOWinsColumn() {
        board.makeMove(0, 0); // X
        board.makeMove(0, 1); // O
        board.makeMove(1, 0); // X
        board.makeMove(1, 1); // O
        board.makeMove(2, 0); // X
        board.makeMove(2, 1); // O - wins column
        assertEquals(GameState.O_WON, board.getGameState());
    }
    
    @Test
    void testXWinsDiagonal() {
        board.makeMove(0, 0); // X
        board.makeMove(0, 1); // O
        board.makeMove(1, 1); // X
        board.makeMove(0, 2); // O
        board.makeMove(2, 2); // X - wins
        assertEquals(GameState.X_WON, board.getGameState());
    }
    
    @Test
    void testDrawGame() {
        // X O X
        // X O O
        // O X X
        board.makeMove(0, 0); // X
        board.makeMove(0, 1); // O
        board.makeMove(0, 2); // X
        board.makeMove(1, 1); // O
        board.makeMove(1, 0); // X
        board.makeMove(1, 2); // O
        board.makeMove(2, 1); // X
        board.makeMove(2, 0); // O
        board.makeMove(2, 2); // X
        assertTrue(board.isGameOver());
        assertEquals(GameState.DRAW, board.getGameState());
    }
    
    @Test
    void testBoardFullDetection() {
        board.makeMove(0, 0);
        board.makeMove(0, 1);
        board.makeMove(0, 2);
        board.makeMove(1, 0);
        board.makeMove(1, 1);
        board.makeMove(1, 2);
        board.makeMove(2, 0);
        board.makeMove(2, 1);
        board.makeMove(2, 2);
        assertTrue(board.isGameOver());
    }
}

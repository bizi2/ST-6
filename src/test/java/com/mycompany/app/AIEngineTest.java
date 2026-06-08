// Copyright 2025 UNN-CS
// Nazyrov A.A.

package com.mycompany.app;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AIEngineTest {
    
    private Board boardObj;
    private AIEngine aiObj;
    
    @Before
    public void setUpTest() {
        boardObj = new Board();
        aiObj = new AIEngine(boardObj);
    }
    
    @Test
    public void testAIMoveNotNull() {
        int[] moveArr = aiObj.getOptimalMove();
        assertNotNull(moveArr);
        assertEquals(2, moveArr.length);
    }
    
    @Test
    public void testAIMoveWithinBounds() {
        int[] moveArr = aiObj.getOptimalMove();
        assertTrue(moveArr[0] >= 0 && moveArr[0] < 3);
        assertTrue(moveArr[1] >= 0 && moveArr[1] < 3);
    }
    
    @Test
    public void testAIMoveOnEmptyField() {
        int[] moveArr = aiObj.getOptimalMove();
        assertTrue(boardObj.canMoveHere(moveArr[0], moveArr[1]));
    }
}

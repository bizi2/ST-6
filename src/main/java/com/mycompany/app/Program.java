// Copyright 2025 UNN-CS
// Nazyrov A.A.

package com.mycompany.app;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Board gameBoard = new Board();
        AIEngine computerAI = new AIEngine(gameBoard);
        Scanner inputScan = new Scanner(System.in);
        
        System.out.println("Tic Tac Toe Game");
        
        while (!gameBoard.isGameFinished()) {
            gameBoard.printBoardState();
            System.out.print("Enter your move (row col): ");
            int rowVal = inputScan.nextInt();
            int colVal = inputScan.nextInt();
            if (gameBoard.doMove(rowVal, colVal)) {
                if (gameBoard.isGameFinished()) break;
                System.out.println("AI is making decision...");
                int[] aiMove = computerAI.getOptimalMove();
                gameBoard.doMove(aiMove[0], aiMove[1]);
            } else {
                System.out.println("Invalid position!");
            }
        }
        gameBoard.printBoardState();
        System.out.println("Game result: " + gameBoard.getGameFlag());
        inputScan.close();
    }
}

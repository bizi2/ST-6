package com.nazyrov;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Board board = new Board();
        AIPlayer ai = new AIPlayer(board);
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Tic Tac Toe vs AI ===");
        System.out.println("You are 'X', AI is 'O'");
        
        while (!board.isGameOver()) {
            board.display();
            System.out.println("\nYour turn (row col): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            
            if (board.makeMove(row, col)) {
                if (board.isGameOver()) break;
                
                System.out.println("AI is thinking...");
                int[] aiMove = ai.getBestMove();
                board.makeMove(aiMove[0], aiMove[1]);
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
        
        board.display();
        GameState result = board.getGameState();
        switch (result) {
            case X_WON:
                System.out.println("You won!");
                break;
            case O_WON:
                System.out.println("AI won!");
                break;
            case DRAW:
                System.out.println("It's a draw!");
                break;
        }
        scanner.close();
    }
}
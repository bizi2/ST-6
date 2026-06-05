// Copyright 2025 UNN-CS

package com.mycompany.app;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Board board = new Board();
        AIPlayer ai = new AIPlayer(board);
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Tic Tac Toe vs AI");
        
        while (!board.isGameOver()) {
            board.printBoard();
            System.out.print("Your move (row col): ");
            int r = sc.nextInt();
            int c = sc.nextInt();
            if (board.makeMove(r, c)) {
                if (board.isGameOver()) break;
                System.out.println("AI thinking...");
                int[] move = ai.getBestMove();
                board.makeMove(move[0], move[1]);
            } else {
                System.out.println("Invalid move!");
            }
        }
        board.printBoard();
        System.out.println("Game over: " + board.getState());
        sc.close();
    }
}

// Copyright 2025 UNN-CS
// Nazyrov A.A.

package com.mycompany.app;

import java.util.Scanner;

public class GameLauncher {
    public static void main(String[] args) {
        BoardGrid field = new BoardGrid();
        AiPlayer computer = new AiPlayer(field);
        Scanner input = new Scanner(System.in);
        
        System.out.println("Tic Tac Toe vs AI");
        
        while (!field.isGameFinished()) {
            field.printBoard();
            System.out.print("Your move (0-2 0-2): ");
            int row = input.nextInt();
            int col = input.nextInt();
            if (field.placeMark(row, col)) {
                if (field.isGameFinished()) break;
                System.out.println("AI thinking...");
                int[] move = computer.findBestMove();
                field.placeMark(move[0], move[1]);
            } else {
                System.out.println("Wrong move!");
            }
        }
        field.printBoard();
        System.out.println("Result: " + field.getGameStatus());
        input.close();
    }
}

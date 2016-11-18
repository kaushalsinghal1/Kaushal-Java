package com.ace.training.usecase.chessgame;

public class Board implements Display{
	
	Position[][] board;
	
	public Board() {
		board = new Position[8][8];
		for (int i= 0;  i< board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = new Position(i, j);
			}
		}
		
		
	}
	
	public Position getPosition(int x, int y){
		return board[x][y];
	}
	
	public void display() {
		
		System.out.println("-------------------CHESS BOARD--------------------");
		System.out.print("   ");
		for (int i = 0; i < board.length; i++) {
			System.out.print("  "+i+"   ");
		}
		System.out.println();
		for (int i= 0;  i< board.length; i++) {
			System.out.println("--------------------------------------------------");
			System.out.print(i+" ");
			for (int j = 0; j < board.length; j++) {
				board[i][j].display();
			}
			System.out.println();
		}
		System.out.println("--------------------------------------------------");
		
		
	}

}

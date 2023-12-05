package Project3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ChoHan extends Game {

    private char[][] board;
    private char currentPlayer;
    private char oppositePlayer;
    private int rowInput;
	private int colInput;
	private int rowRandom;
	private int colRandom;
	private boolean someWin;

    public ChoHan() {
    	this.someWin = false;
        this.board = new char[3][3];
        this.currentPlayer = 'X';
        this.oppositePlayer = 'O';
        initializeBoard();
        
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    @Override
    public String explainRules() {
    	printBoard();
        return "Welcome to ChoHan. Surprise, this game is actually Tic-Tac-Toe! Try to get three in a row,column or diagonal to win the game.";
    }

    @Override
    public String setup() {
        return "Player " + currentPlayer + ", enter your move ('row column', for example '1 2'): ";
    }
    private void compMove() {

        	if(isBoardFull()) {
        		return;
        	}
        	
            Random random = new Random();
            while (true) {
                this.rowRandom = random.nextInt(3);
                this.colRandom = random.nextInt(3);
                if (board[rowRandom][colRandom] == '-') {
                    board[rowRandom][colRandom] = oppositePlayer;
                    printBoard();
                    break; 
                }
            }
        }
    
    
    private void playerMove(int row,int col) {
        	board[row-1][col-1] = currentPlayer;  
    }

    @Override
    public boolean goodPlayerInput(String input) {
    	try{
    		String[] answer = input.split(" ");
	    	this.rowInput = Integer.valueOf(answer[0]);
	    	this.colInput = Integer.valueOf(answer[1]);
	        if(this.rowInput >= 1 && this.rowInput <= 3 && this.colInput >= 1 && this.colInput <= 3 && board[this.rowInput-1][this.colInput-1]=='-') {
	        	playerMove(this.rowInput, this.colInput);
	        	printBoard();
	        	compMove();
	        	return true;
	        }
    	}catch(Exception e) {
	    	return false;
	    }
        return false;
    }
    
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public String checkWinOrLose() {
    	if((hasThree(this.rowInput-1,this.colInput-1,currentPlayer))) {
            printBoard();
            this.someWin=true;
            return "Player " + currentPlayer + " wins!"; 
    	}else if(hasThree(this.rowRandom,this.colRandom,oppositePlayer)) {
    		printBoard();
    		this.someWin=true;
    		return "Player " + oppositePlayer + " wins!";
    	}else if(isBoardFull()) {
    		return "It's a tie!";
    	}else {
    		return "Keep fighting!";
    	}
    }

    @Override
    public boolean canPlayAgain() {
        return !isBoardFull() && !this.someWin;
    }

    private boolean hasThree(int row, int col, char player) {
        // Check row
        if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
            return true;
        }
        // Check column
        if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
            return true;
        }
        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if ( board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    private void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }
}

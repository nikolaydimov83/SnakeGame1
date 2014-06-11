

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.KeyStroke;

class Position
{
    public int row, col;
    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }
    
}

public class Snake {
	 
	public static void main(String[] args) {
		
		byte right = 0;
        byte left = 1;
        byte down = 2;
        byte up = 3;
        double foodDissapearTime = 10500;
        int negativePoints = 0;
        double sleepTime=150;
        int obstacleColumns;
        int obstacleRows;
        int dificulty=0;
        
        
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        TerminalSize terminalSize = terminal.getTerminalSize();
        
        terminal.moveCursor(6,6);
        Write("PLEASE SELECT PLAYING GROUND",terminal);
        terminal.moveCursor(6,7);
		Write("PRESS 1 FOR EASY", terminal);
		terminal.moveCursor(6,8);
		Write("PRESS 2 FOR DIFFICULT", terminal);
		
		while (dificulty==0){
		Key p = terminal.readInput();
    	if (p != null)
    	{
    		System.out.println(p);
    		if (p.getKind() == Key.Kind.Insert) {
    			dificulty=1;
    			break;
    		}
    		
    		if (p.getKind() == Key.Kind.Delete) {
    			dificulty=2;
    			break;
    		}		
    	}
    	}
		terminal.clearScreen();
        
		Position[] directions = new Position[]
	            {
	                new Position(1, 0), // right
	                new Position(-1, 0), // left
	                new Position(0, 1), // down
	                new Position(0, -1), // up
	            };
		
		int direction = right;
		
		ArrayList<Position> Grid =new ArrayList<Position>();		
		Grid=Obstacles.Obstacles(terminalSize,dificulty);      
        terminal.setCursorVisible(false);
        for (Position i: Grid) 
        { 
  
        	terminal.moveCursor(i.col, i.row);
        	terminal.putCharacter('♻');
 
        }
        
        Queue<Position> snakeElements = new LinkedList<Position>(); 
       
        for (int i = 6; i <= 9; i++)
        {
            snakeElements.add(new Position(i,15));
            
        }
        terminal.applyForegroundColor(Terminal.Color.RED);
        
        Position snakeHead =new Position(9,15);
        
        snakeHead = snakeDraw(terminal, snakeElements, snakeHead);
             
       Position food;

       boolean isInGrid=false;
       boolean isInSnake=false;
		food = foodDraw(terminal, terminalSize, Grid, snakeElements);
		terminal.putCharacter('@');
		long tStart = System.currentTimeMillis();
        while(true) {
        	Key key = terminal.readInput();
        	if (key != null)
        	{
        		System.out.println(key);
        		if (key.getKind() == Key.Kind.ArrowLeft) {
        			if (direction != right) direction = left;
        		}
        		
        		if (key.getKind() == Key.Kind.ArrowRight) {
        			if (direction != left) direction = right;
        		}
        		
        		if (key.getKind() == Key.Kind.ArrowUp) {
        			if (direction != down) direction = up;
        		}
        		
        		if (key.getKind() == Key.Kind.ArrowDown) {
        			if (direction != up) direction = down;
        		}
        		if (key.getKind() == Key.Kind.Tab) {
        			while(true)
        			{
        				Key p = terminal.readInput();
        	        	if (p != null){
        				if (p.getKind() == Key.Kind.Tab)
        				{
        					break;
        				}
        				}
        			}
        		}
        		
        		if (key.getKind() == Key.Kind.Escape) {
        			System.exit(0);
        		}
  		
        	}
        	
        	Position nextDirection = directions[direction];
    		Position snakeNewHead = new Position(
                    snakeHead.col + nextDirection.col,
                    snakeHead.row + nextDirection.row);
    		boolean  snakeBite=false;
    		boolean isBitingTheGrid=false;
    		for(Position i:snakeElements){
    		if (snakeNewHead.row==i.row&&snakeNewHead.col==i.col)
    		{
    			snakeBite=true;
    		}
    		}
    		for(Position i:Grid){
        		if (snakeNewHead.row==i.row&&snakeNewHead.col==i.col)
        		{
        			isBitingTheGrid=true;
        		}
        		}
    		if (isBitingTheGrid==true || snakeBite==true)
                {
    			terminal.moveCursor(6,6);
    			Write("GAME OVER", terminal);
    			
    			terminal.moveCursor(6,7);
    			
    			Write("YOUR POINTS", terminal);
    			                   
               int userPoints = Math.max(0, ((snakeElements.size() - 4) * 100)+negativePoints);
               String userPointsStr=Integer.toString(userPoints);
               char[] charArray = userPointsStr.toCharArray();
               
               for (int i=0;i<=charArray.length-1;i++)
               {
            	   terminal.putCharacter((char)charArray[i]);   
               }
                    
    			while(true) 
    			{
    	        	Key exit = terminal.readInput();
    	        	if (exit != null)
    	        	{
    	        		if (exit.getKind() == Key.Kind.Escape) 
    	        		{
    	        			System.exit(0); 
    	        		}	
    	        	}
    			}
                }
    		
    		if (snakeNewHead.col==food.col&&snakeNewHead.row==food.row)//smiata ide
    		{
    			snakeHead = snakeDraw(terminal, snakeElements, snakeNewHead);
        		do { 
        			isInGrid=false;
        		    isInSnake=false;
        	   	    obstacleColumns = 2 + (int)(Math.random() * ((terminalSize.getColumns() - 2) ));        	   	    	
        	   	    obstacleRows = 2 + (int)(Math.random() * ((terminalSize.getRows() - 2) ));       	    	   
        	    	Position obstacle = new Position(obstacleColumns,obstacleRows);
        	   
        	    	for(Position i:Grid)
        	    	{
        	    		if (i.row==obstacleRows&&i.col==obstacleColumns)
        	    		{
        	    		isInGrid=true;
        	    		}
        	    	}        	            	    
        	    	for(Position i:snakeElements)
        	    	{
        	    	if (i.row==obstacleRows&&i.col==obstacleColumns)
        	    	{
        	    		isInSnake=true;
        	    	}
        	    	}
        	    	terminal.moveCursor(obstacle.col, obstacle.row);
        	    	terminal.applyForegroundColor(Terminal.Color.DEFAULT);
        	    	
        	       }
        		while((isInGrid==true)||(isInSnake==true)||food.col<1||food.col>terminalSize.getColumns()-1||food.row<1||food.row>terminalSize.getRows()-1);
        		Position obstacle = new Position(obstacleColumns,obstacleRows);
        		Grid.add(obstacle);
        		terminal.putCharacter('♻');
        		
        		
        		food = foodDraw(terminal, terminalSize, Grid, snakeElements);
        	
        		terminal.putCharacter('@');
        		tStart = System.currentTimeMillis();
        		sleepTime=sleepTime-1;	
    		}
    		
    		else{//zmiata se dvivi
    			
    			snakeHead = snakeDraw(terminal, snakeElements, snakeNewHead);	
    			Position removeLast = snakeElements.poll();
    			terminal.moveCursor(removeLast.col, removeLast.row);
    			terminal.putCharacter(' ');
        	
    		}
    		
    		long tEnd = System.currentTimeMillis();
    		
    		if (tEnd-tStart>foodDissapearTime)
    		{
    			terminal.moveCursor(food.col, food.row);
				terminal.putCharacter(' ');
    			food = foodDraw(terminal, terminalSize, Grid, snakeElements);
    			terminal.putCharacter('@');
    			tStart = System.currentTimeMillis();
    			negativePoints-=10;
    			
    		}
        	
        	try {
				Thread.sleep((int)sleepTime);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
        }	
        
	}

	private static Position foodDraw(Terminal terminal,
			TerminalSize terminalSize, ArrayList<Position> Grid,
			Queue<Position> snakeElements) {
		Position food;
		int foodColumns;
		int foodRows;
		boolean isInGrid;
		boolean isInSnake;
		do { 
						
			isInGrid=false;
		    isInSnake=false;
		    foodColumns = 2 + (int)(Math.random() * ((terminalSize.getColumns() - 2) ));        	   	    	
		    foodRows = 2 + (int)(Math.random() * ((terminalSize.getRows() - 2) ));       	    	   
			food = new Position(foodColumns,foodRows);
 		   
			for(Position i:Grid)
			{
				if (i.row==foodRows&&i.col==foodColumns)
				{
				isInGrid=true;
				}
			}        	            	    
			for(Position i:snakeElements)
			{
				if (i.row==foodRows&&i.col==foodColumns)
				{
				isInSnake=true;
				}
			}
			terminal.moveCursor(food.col, food.row);
			terminal.applyForegroundColor(Terminal.Color.YELLOW);
			
		   }
		while((isInGrid==true)||(isInSnake==true)||foodColumns<1||foodColumns>terminalSize.getColumns()-1||foodRows<1||foodRows>terminalSize.getRows()-1);
		return food;
	}

	private static Position snakeDraw(Terminal terminal,
			Queue<Position> snakeElements, Position snakeNewHead) {
		Position snakeHead;
		snakeElements.add(snakeNewHead);
		snakeHead =snakeNewHead;
 		
		for (Position p: snakeElements) 
		{ 
			terminal.moveCursor(p.col, p.row);
			terminal.applyForegroundColor(Terminal.Color.RED);
			if (p.equals(snakeNewHead)==false){
				 
			terminal.putCharacter('#');
			}
			else {
				 
		  	terminal.putCharacter('O'); 
			}

		}
		return snakeHead;
	}
	
	private static void Write(String print, Terminal terminal) {
		char[] printToChar = print.toCharArray();
		for (int i=0;i<print.length();i++)
		{
			terminal.putCharacter(printToChar[i]);	
		}
	}

}

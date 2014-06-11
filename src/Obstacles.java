

import java.util.ArrayList;

import com.googlecode.lanterna.terminal.TerminalSize;


public class Obstacles {
	
	public static ArrayList<Position> Obstacles(TerminalSize terminalSize, int dificulty){
		 ArrayList<Position> Grid =new ArrayList<Position>();
	        switch(dificulty)
	        {
	        case 1:
	        for (int colGrid=0; colGrid<=terminalSize.getColumns();colGrid++)
	        {
	        Grid.add(new Position(colGrid, 0));	
	        }
	        
	        for (int colGrid=0; colGrid<=terminalSize.getColumns();colGrid++)
	        {
	        Grid.add(new Position(colGrid, terminalSize.getRows()-1));	
	        }
	        
	        for (int rowGrid=0; rowGrid<=terminalSize.getRows();rowGrid++)
	        {
	        Grid.add(new Position(0, rowGrid));	
	        }
	        
	        for (int rowGrid=0; rowGrid<=terminalSize.getRows();rowGrid++)
	        {
	        Grid.add(new Position(terminalSize.getColumns()-1, rowGrid));	
	        }
	        
	        for (int colGrid=0; colGrid<=11;colGrid++)
	        {
	        Grid.add(new Position(20, colGrid));	
	        }
	        
	        for (int colGrid=19; colGrid<=terminalSize.getColumns();colGrid++)
	        {
	        Grid.add(new Position(30, colGrid));	
	        }
	        
	        for (int colGrid=0; colGrid<=11;colGrid++)
	        {
	        Grid.add(new Position(45, colGrid));	
	        }
	        
	        for (int colGrid=19; colGrid<=terminalSize.getColumns();colGrid++)
	        {
	        Grid.add(new Position(55, colGrid));	
	        }
	        
	        for (int colGrid=0; colGrid<=11;colGrid++)
	        {
	        Grid.add(new Position(70, colGrid));	
	        }
	        
	        for (int colGrid=19; colGrid<=terminalSize.getColumns();colGrid++)
	        {
	        Grid.add(new Position(80, colGrid));
	        }
	        break;
	        default:
	        	
		        for (int colGrid=0; colGrid<=terminalSize.getColumns();colGrid++)
		        {
		        Grid.add(new Position(colGrid, 0));	
		        }
		        
		        for (int colGrid=0; colGrid<=terminalSize.getColumns();colGrid++)
		        {
		        Grid.add(new Position(colGrid, terminalSize.getRows()-1));	
		        }
		        
		        for (int rowGrid=0; rowGrid<=terminalSize.getRows();rowGrid++)
		        {
		        Grid.add(new Position(0, rowGrid));	
		        }
		        
		        for (int rowGrid=0; rowGrid<=terminalSize.getRows();rowGrid++)
		        {
		        Grid.add(new Position(terminalSize.getColumns()-1, rowGrid));	
		        }
		        
		        for (int colGrid=0; colGrid<=11;colGrid++)
		        {
		        Grid.add(new Position(20, colGrid));	
		        }
		        
		        for (int colGrid=19; colGrid<=terminalSize.getColumns();colGrid++)
		        {
		        Grid.add(new Position(30, colGrid));	
		        }
		        
		        for (int colGrid=0; colGrid<=11;colGrid++)
		        {
		        Grid.add(new Position(45, colGrid));	
		        }
		        
		        for (int colGrid=19; colGrid<=terminalSize.getColumns();colGrid++)
		        {
		        Grid.add(new Position(55, colGrid));	
		        }
		        
		        for (int colGrid=0; colGrid<=11;colGrid++)
		        {
		        Grid.add(new Position(70, colGrid));	
		        }
		        
		        for (int colGrid=19; colGrid<=terminalSize.getColumns();colGrid++)
		        {
		        Grid.add(new Position(80, colGrid));
		        }
		        
		        for (int colGrid=13; colGrid<=15;colGrid++)
		        {
		        Grid.add(new Position(18, colGrid));
		        }
		        
		        for (int colGrid=13; colGrid<=15;colGrid++)
		        {
		        Grid.add(new Position(33, colGrid));
		        }
		        
		        for (int colGrid=13; colGrid<=15;colGrid++)
		        {
		        Grid.add(new Position(48, colGrid));
		        }
		        
		        for (int colGrid=13; colGrid<=15;colGrid++)
		        {
		        Grid.add(new Position(63, colGrid));
		        }
		        
		        for (int colGrid=13; colGrid<=15;colGrid++)
		        {
		        Grid.add(new Position(78, colGrid));
		        }
		        
		        for (int colGrid=13; colGrid<=15;colGrid++)
		        {
		        Grid.add(new Position(92, colGrid));
		        }
	        	
	        	break;
	        }
	        return Grid;
	        
	}

}

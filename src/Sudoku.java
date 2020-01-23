/*
 Carlos Aybar
 Advanced Java
 01/19/20
 The main goal of this program is to be able to solve any Sudoku puzzle
 that you might want to solve. all you have to do is write the Sudoku in
 a text file and run this program and within a matter of seconds it will 
 have a solution
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Sudoku{
	int [] [] grid = new int [9][9];
	int  gridGenerated;
	
	/**
	 * the following is the constructor that calls the readPuzzle method, which contains the file path 
	 * @param filePath the location of the sudoku
	 * @throws FileNotFoundException in case the file path is incorrect
	 * @throws IOException handles input and outputs
	 */
	public Sudoku(String filePath) throws FileNotFoundException, IOException 
	{
		readPuzzle(filePath); //calls the readPuzzle method
	}
	
	
	/**
	 * this method reads the puzzle from the file Path that the user entered
	 * it stores the puzzle into a 9 by 9 grid
	 * @param filePath the location of the file typed in by the user
	 * @return returns the 9 by 9 grid
	 * @throws IOException handles input and output exceptions
	 * @throws FileNotFoundException in case the file path is not found
	 */
	public int [][] readPuzzle(String filePath) throws IOException, FileNotFoundException
	{
		Scanner file = new Scanner (new File(filePath));
		
		for(int row = 0; row < grid.length; row++) //iterates through the rows of the grid
		{
			for(int col = 0; col < grid.length; col++) //iterates through the columns of the grid
			{
				if(file.hasNextInt()) //reads from the file until there it reaches the end of the file
				{
					grid[row][col] = file.nextInt(); //stores all the integers from the file into the grid array
				}
			}
		}
		file.close(); // closes the file
		return grid;
	}
	
	/**
	 * this toString method returns the elements in the grid 
	 * as a string in a 9 by 9 order
	 */
	
	public String toString()
	{
		
		for(int row = 0; row < grid.length; row++)
		{
			for(int col = 0; col < grid.length; col++)
			{

					System.out.print(grid[row][col] + " "); //prints all the integers from every position in the grid in a 9 by 9 format
			}
			System.out.print("\n");
		}
		
		return null;
	}
	
	
	/**
	 * this method loops through each row and column and gets the
	 * possible candidates for each one. it iterates thoru the candidates array
	 * and remove the candidates that do not belong in the row then it does the same for the columns
	 * @param row each row of the grid to be checked
	 * @param col each col of the grid to be checked
	 * @return the possible candidates for each row and column
	 */
	public ArrayList<Integer> getCandidates(int row , int col)
	{
		
		int []  numbers = {1,2,3,4,5,6,7,8,9}; 
		ArrayList candidates = new ArrayList<Integer>(); 
		for(int i = 0; i < numbers.length; i++)
		{
			candidates.add(numbers[i]); //adds the number from the array of ints into the Arraylist candidates
		}
		
		
		
		/*
		 * the following for loops iterate through each row and column one at the time
		 * it looks from the numbers that are already in the grid and removes them from
		 * list of candidates, it then returns the list of candidates for each row and column
		 */
		for(int x = 0; x < grid.length; x++)
		{
			for(int y = 0; y  < grid.length; y++)
			{
				if(candidates.contains(grid[x][row])) 
				{
					candidates.remove(new Integer(grid[x][row]));	

					if(candidates.contains(grid[col][y]))
					{
					candidates.remove(new Integer(grid[col][y]));
					
					}
				}

				
			}
			
		}
		 return candidates;
	}
	
	/**
	 * checks to see if the numbers in each row are valid
	 * essentially it check to see if it has all the numbers from 1-9
	 * without repetition 
	 * @param row keeps track of the row being checked
	 * @return whether the row is valid or not (true or false)
	 */
	public boolean isRowValid(int row)
	{
		int []  numbers = {1,2,3,4,5,6,7,8,9};
		ArrayList candidates = new ArrayList<Integer>();
		boolean isValid = false;
		for(int i = 0; i < numbers.length; i++)
		{
			candidates.add(numbers[i]);
		}

		/*
		 * checks to see if the candidates, which are all the numbers from 1-9 is equal to the 
		 * numbers in each row of the grid and return either true or false
		 */
		for(int x = 0; x < grid.length; x++)
		{
			if(candidates.contains(grid[x][row]))
			{
				isValid = true;
				break;
			}
		}
		return isValid;
		
	}
	
	
	/**
	 * checks to see if the numbers in each column are valid
	 * essentially it check to see if it has all the numbers from 1-9
	 * without repetition 
	 * @param col keeps track of the col being checked
	 * @return whether the col is valid or not (true or false)
	 */
	public boolean isColumnValid(int col)
	{
		int []  numbers = {1,2,3,4,5,6,7,8,9};
		ArrayList candidates = new ArrayList<Integer>();
		boolean isValid = false;
		for(int i = 0; i < numbers.length; i++)
		{
			candidates.add(numbers[i]);
		}
		
		/*
		 * checks to see if the candidates, which are all the numbers from 1-9 is equal to the 
		 * numbers in each column of the grid and return either true or false
		 */
		for(int y = 0; y < grid.length; y++)
		{
			if(candidates.contains(grid[col][y]))
			{
				isValid = true;
				break;
			}
		}
		return isValid;
		
	}
	
	/**
	 * breaks the nine by nine grid into three by threes and
	 * checks to see if the numbers in each three by three are valid
	 * essentially it check to see if it has all the numbers from 1-9
	 * without repetition 
	 * @param row keeps track of the row being checked
	 * @param col keeps track of the column being checked
	 * @return whether the subgrid is valid or not (true or false)
	 */
	public boolean isSubGridValid(int row, int col)
	{
		boolean isValid = false;
		int []  numbers = {1,2,3,4,5,6,7,8,9};
		ArrayList candidates = new ArrayList<Integer>();
		
		ArrayList solution = new ArrayList<Integer>();
		for(int i = 0; i < numbers.length; i++)
		{
			candidates.add(numbers[i]);
		}
		 
		int rows = row % 3; //each row is now only three columns
		int cols = col % 3; //each column is now only three rows, that way it is a three by three
		
		for(row = 0; row < rows + 3; row++) 
			for(col = 0; col < cols + 3; col++)
				solution = new ArrayList(grid[row][col]); //iterates through the 3 by 3 and gets all the integers stored in eahc
		
		Collections.sort(solution);
		if(solution.equals(candidates)) {  //compares the numbers in the three by three to the candidates to make sure all numbers from 1-9 are there
			isValid = true;
		}
		return isValid;
	}
	
	
	
	/**
	 * the solve method has an if statement that checks to see if the 
	 * row ,  column, and subgrid are all valid. if they are all valid then
	 * the puzzle is solved, otherwise it tries to solve it again until the right
	 * solution is found
	 * @return returns the solved sudoku
	 */
	public int[][] solve()
	{
		boolean isValid = false;
		for (int row = 0; row < grid.length; row++)
		{
			for(int col = 0; col < grid.length; col++)
			{
				
				if(isRowValid(row) && isColumnValid(col) && isSubGridValid(row , col)) //checks if the column, rows, and subgrids are valid
					isValid = true;
					gridGenerated++; //keeps track of how many grids have been created to get to the solution
			}
		}
		
		
		System.out.println("\n" + "this is the solved grid");
		Random random = new Random();
		do {
		for(int row = 0; row < grid.length; row++)
		{
			for(int col = 0; col < grid.length; col++)
			{
				if(grid[row][col] == 0 )
				{
					/*
					 * here I create a new arrayList made up of the possible numbers that can be plugged in
					 * I generate a number lass then or equal to the size of the possible candidates
					 * then I use that random number as the index to get of of the possible candidates and plug it
					 * into the rows and cols
					 */
					ArrayList<Integer> possibleNum = getCandidates(row , col);
					int index = random.nextInt(possibleNum.size());
					grid[row][col] = possibleNum.get(index);
					
				}
				System.out.print(grid[row][col] + " ");
			}
			System.out.print("\n");
		}
		
		return grid;
		}while(isValid == false);  //this for loop will executed as long as the puzzle is not completely solved
	}
	
	
	/**
	 * this method keeps track of all the grids generated in order to come up to the solution
	 * @return returns the number of grids that were generated in order to come to the solution 
	 */
	public int getGridsGenerated()
	{
		//create a while loop that runs through solve and keep a counter
		System.out.println("The number of grids generated to get to this solution were : " + gridGenerated);
		return gridGenerated;
	}
	
	
	
	

}
/*
 Carlos Aybar
 Advanced Java
 01/19/20
 The main goal of this program is to be able to solve any Sudoku puzzle
 that you might want to solve. all you have to do is write the Sudoku in
 a text file and run this program and within a matter of seconds it will 
 have a solution
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.IllegalArgumentException;

public class Game {
	/**
	 * 
	 * @param args args[0] is where the path to the txt file will be stored
	 * @throws FileNotFoundException in case the file path is incorrect
	 * @throws IOException will handle any input and output exceptions
	 */
	public static void main(String[] args) throws FileNotFoundException,  IOException, IllegalArgumentException
{
		String filePath = args[0];
		Sudoku sod = new Sudoku(filePath); //creates an instance of the class Sudoku
		sod.toString();
		sod.solve();
		sod.getGridsGenerated();
}


}

//coment
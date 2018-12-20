import java.util.Scanner ;
import javax.swing.JOptionPane;
/*
A class that creates a square matrix which is N rows by N columns. The matrix
will contain the numbers 1 through N-squared, with each number appearing once; 
and the matrix will have the property of all sums of each row, each column, and 
both main diagonals being equivalent.
*/

/**
 * A class that creates a Magic Square.
 */
public class MagicSquare
{
    private int[][] square; // 2-D square array of integers
    private int row;        // Length of row and column of array
      
    /**
     * Creates a Magic Square by getting user input for N and constructing
     * an N by N matrix.
     */
    public MagicSquare()
    {
        row = getUserInput() ;   // Call private method to get value for length
                                 // of the magic square
        
        // If number entered by user is not a positive, odd number...try again
        while (row == -1)
        {
            JOptionPane.showMessageDialog(null, "You did not enter a positive,"
                    + " odd number...") ;
            
            row = getUserInput() ;   // Enter another number, if still not a 
                                     // positive, odd number...try again
        }
        
        square = new int[row][row] ; // Set dimensions of magic square to a 
                                     // value provided by the user
        
        fillSquare(row) ;    // fill the magic square
    }
    
    /**
     * Gets user input for the value of N, to create an N by N 2-D array matrix.
     * The input must be an positive, odd integer.
     * @return the integer entered by the user, or -1 if the integer was not a 
     * positive, odd integer
     */
    private int getUserInput()
    {
        // Get input from user for length N for a magic square that is N x N
        String input = JOptionPane.showInputDialog("To create a Magic Square "
                + "of N rows by N columns,\nenter a positive, odd integer for "
                + "N: ") ;
        
        Scanner scan = new Scanner(input) ;  // scanner gets user input
        
        int nValue = scan.nextInt() ;    // set variable to user input
        
        // Value for N must be a positive, odd number 
        if (nValue > 0 && nValue % 2 == 1)
            return nValue ;
        else
            return -1 ;
    }
    
    /**
     * Fills the Magic Square. Takes the users input for the size of N to create 
     * an N x N matrix, then fills it with the integer 1 through N in the 
     * appropriate indices, using a magic algorithm.
     * @param n the value to create an N x N matrix
     */
    private void fillSquare(int n)
    {
        int[][] temp = new int[n][n] ; // array gets length of n squared
        
        int row = n - 1 ;    // index [row][col] will be starting index to fill  
        int col = n / 2 ;    // square, the first index will always be the last 
                             // row, middle column
        
        // Fill the square
        for (int num = 1; num <= n * n; num++)
        {   
            // if move leads to non-existent row and non-existent column...
            if (row == n && col == n)
            {
                row = row - 2 ;  
                col-- ;          // set number to same column, previous row
            }
            // If move leads to non-existent row...
            else if (row == n)
            {
                row = 0 ;        // set number to first row, one column to right
            }
            // If move leads to non-existent column...
            else if (col == n)
            {
                col = 0 ;        // set number to first column, one row down
            }
            // If move leads to position that is already filled...
            else if (temp[row][col] > 0)
            {
                row = row - 2 ;
                col-- ;          // set number to same column, previous row
            }
            
            // If move leads to position that is emppty, set number
            temp[row][col] = num ;
                
            row++ ;  // Attempt to place the next number in the index one below
            col++ ;  // and one to the right of previous number
        }
    
        // Copy the filled temp array to magic square array
        for (int i = 0; i < temp.length; i++)
            for (int j = 0; j < temp[i].length; j++)
                square[i][j] = temp[i][j] ;
    }
     
    /**
     * Determines if the created Magic Square is actually magic. If the sum of 
     * each row, the sum of each column, and the sum of both main diagonals are 
     * equivalent, the square is indeed magic
     * @return true if the created Magic Square is magical
     */
    public boolean isMagic()       
    {
        boolean magic = true ;
        
        int rows = square.length ; // the number of rows in the magic square
        int cols = square[0].length ; // the number of cols in the magic square
        
        // Temporary array to hold the sums of each row
        int[] sumRows = new int[rows] ;
        
        // Sums up each row in Magic Square, sets each sum to an index in the 
        // temporary array
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                sumRows[i] = sumRows[i] + square[i][j] ;
        
        // If sums of all rows in Magic Square are equal, continue on to test
        // if sums of columns are equal as well
        for (int i = 0; i < sumRows.length - 1; i++)
        {
            if (sumRows[i] == sumRows[i + 1])
                continue ;
            else
                magic = false ;   
        }
        
        // Temporary array to hold the sums of each column
        int[] sumCols = new int[rows] ;
        
        // Sums up each column in Magic Square, sets each column to an index in 
        // the temporary array
        for (int i = 0; i < cols; i++)
            for (int j = 0; j < rows; j++)
                sumCols[i] = sumCols[i] + square[i][j] ;
        
        // If sums of all columns in Magic Square are equal, continue on to test
        // if sums of diagonals are equal as well
        for (int i = 0; i < sumCols.length - 1; i++)
        {
            if (sumCols[i] == sumCols[i + 1])
                continue ;
            else
                magic = false ;
        }   
        
        // Temporary array to hold the sum of the first main diagonal
        int[] sumDiagonal1 = new int[1] ;
        
        // Sums up first diagonal in Magic Square, sets sum to an index in the 
        // temporary array
        for (int i = 0; i < rows; i++)
            sumDiagonal1[0] = sumDiagonal1[0] + square[i][i] ;
            
        // Temporary array to hold the sum of the second main diagonal        
        int[] sumDiagonal2 = new int[1] ;
        
        // Sums up second diagonal in Magic Square, sets sum to an index in the 
        // temporary array
        for (int i = 1; i <= rows; i++)
            sumDiagonal2[0] = sumDiagonal2[0] + square[rows - i][i - 1] ;
            
        // If sums of each row were not equal to each other, or if sums of each
        // column were not equal to each other, the square is not magic
        if (magic == false)
            return magic ;
        
        // If boolean is still true each row sum is equivalent, and each column
        // sum is equivalent. Set 4 variables, 1 to equal the sum of a row, 1 to
        // equal the sum of a column, 1 to equal the sum of the first diagonal, 
        // and 1 to equal a sum of the second diagonal, to test is they are all 
        // equivalent 
        int sumRow = sumRows[0] ; 
        int sumCol = sumCols[0] ;
        int diagonal1 = sumDiagonal1[0] ;
        int diagonal2 = sumDiagonal2[0] ;
        
        // If rows, columns, and diagonal are equivalent, the square is magic
        if (sumRow == sumCol && sumCol == diagonal1 && diagonal1 == diagonal2)
            return magic ;
        
        // is square magic or not?
        return magic ;
    }
    
    /**
     * Prints the Magic Square.
     */
    public void printTable()
    {
        // For each row...
        for (int row = 0; row < square.length; row++)
        {
            // For each column...
            for (int col = 0; col < square[row].length; col++)
                // print the element in the [row][column]
                System.out.printf("%-5s", square[row][col]) ;
                                               
            System.out.println() ;
            System.out.println() ;
        }
    }
}


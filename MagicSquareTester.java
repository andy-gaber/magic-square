
// Test class for MagicSquare.java

public class MagicSquareTester
{
    public static void main(String[] args)
    {
        // Create magic square object
        MagicSquare theSquare = new MagicSquare() ;
        
        // If the square is magic, print it
        if (theSquare.isMagic())
            theSquare.printTable() ;
        // Else, inform user that the suqare is not magic
        else
            System.out.println("Sorry, this square is not magic") ;
    }
}
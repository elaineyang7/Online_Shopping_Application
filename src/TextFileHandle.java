import java.io.*;
import java.util.Scanner;

/***
 * 
 * File IO handle class, Write and read.
 * This is the class responsible for IO manipulation.
 */
public class TextFileHandle
{
	public static final String CUSTOMER_DATA = "Customer.txt";
	public static final String BOOK_FILE = "Book.txt";
    public static final String CD_FILE = "CD.txt";
    public static final String MOVIE_FILE = "Movie.txt";
    public static final String ORDER_FILE = "Order.txt";
    public static final String CART_FILE = "cart.txt";
    public static final String SPLIT_CEMI = ";";
    public static final String SPLIT_COMMA = ",";

    /**
     * read information from files
     *
     * @param filename
     * @return data stored in the files
     */
    public static String readFromFile(String filename)
    {
        String data = "";
        try
        {
            FileReader inputFile = new FileReader(filename);
            Scanner parser = new Scanner(inputFile);
            while (parser.hasNextLine())
            {
                data = data + parser.nextLine() + SPLIT_CEMI;
            }
            inputFile.close();
            return data;
        } 
        catch (FileNotFoundException exception)
        {
            System.out.println("File does not exist.");
        } 
        catch (IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
        return data;
    }

    /**
     * 
     * write data to overwrite file
     *
     * @param saveData the data which needs to be saved
     * @param filePath the file which will write
     */
    public static void writeToFile(String saveData, String filePath)
    {
        try
        {
            PrintWriter outputFile = new PrintWriter(filePath);
            outputFile.println(saveData);
            outputFile.close();
        } catch (IOException e)
        {
            System.out.println("Sorry, fail to save borrowers' data!");
        }
    }
    
    /**
     * 
     * append data to file
     *
     * @param saveData the data which needs to be saved
     * @param filePath the file which will write
     */
    public static void appendToFile(String saveData, String filePath)
    {
    	try
    	{
    	    FileWriter fw = new FileWriter(filePath,true);
    	    fw.write(saveData); //appends the string to the file
    	    fw.close();
    	}
    	catch(IOException ioe)
    	{
    	    System.err.println("IOException: " + ioe.getMessage());
    	}
    }
    
}

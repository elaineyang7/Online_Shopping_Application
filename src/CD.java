
/***
 * 
 * This class represents a CD product entity
 */
public class CD extends Product
{

    private String singer;
    private String genre;
    private int numOfSongs;

    public CD(String name, double price, int quantity, ProductType type, int year)
    {
        super(name, price, quantity, type,year);
    }

    public CD(ProductType type, String name, double price, int year, String genre, String singer, int quantity, int numOfSongs)
    {
        super(name, price, quantity, type,year);
        this.singer = singer;
        this.genre = genre;
        this.numOfSongs = numOfSongs;
    }

    public String getSinger() 
    {
        return singer;
    }

    public void setSinger(String singer) 
    {
        this.singer = singer;
    }

    public String getGenre() 
    {
        return genre;
    }

    public void setGenre(String genre) 
    {
        this.genre = genre;
    }

    public int getYear() 
    {
        return year;
    }

    public void setYear(int year) 
    {
        this.year = year;
    }

    public int getNumOfSongs() 
    {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) 
    {
        this.numOfSongs = numOfSongs;
    }

    @Override
    public String toString()
    {
    	// File format: name, singer, genre, year, quantity, numOfSongs, price
        return this.getName() + TextFileHandle.SPLIT_COMMA +
                this.getSinger() +TextFileHandle.SPLIT_COMMA +
                this.getGenre() + TextFileHandle.SPLIT_COMMA +
                this.getYear() + TextFileHandle.SPLIT_COMMA +
                this.getQuantity()	+ TextFileHandle.SPLIT_COMMA +
                this.getNumOfSongs()+ TextFileHandle.SPLIT_COMMA +
                this.getPrice();
    }

}

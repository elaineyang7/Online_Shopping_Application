
/***
 * 
 * This class represents a movie product entity
 */
public class Movie extends Product
{
	private String genre;
	private String director;
	private String starring;
	
	public Movie(String name, float price, int quantity, ProductType type, int year) 
	{
		super(name, price, quantity, type, year);
	}
	
	public Movie(ProductType type, String name, double price, int year, String genre, String director, String starring, int quantity)
	{
		super(name, price, quantity, type, year);
		this.genre = genre;
		this.director = director;
		this.starring = starring;
	}

	public int getYear() 
	{
		return year;
	}

	public void setYear(int year) 
	{
		this.year = year;
	}

	public String getGenre() 
	{
		return genre;
	}

	public void setGenre(String genre) 
	{
		this.genre = genre;
	}

	public String getDirector() 
	{
		return director;
	}

	public void setDirector(String director) 
	{
		this.director = director;
	}
	
	public String getStarring() 
	{
		return starring;
	}

	public void setStarring(String starring) 
	{
		this.starring = starring;
	}

	 @Override
		public String toString() 
	 {
		// File format: name, year, genre, price, director, starring, quantity
			return this.getName() + TextFileHandle.SPLIT_COMMA + 
					this.year + TextFileHandle.SPLIT_COMMA + 
					this.genre +TextFileHandle.SPLIT_COMMA + 
					this.getPrice()	+ TextFileHandle.SPLIT_COMMA + 
					this.director + TextFileHandle.SPLIT_COMMA + 
					this.starring + TextFileHandle.SPLIT_COMMA+
					this.getQuantity();
		}

}

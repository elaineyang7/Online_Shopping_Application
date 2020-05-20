
/***
 * 
 * This class represents a book product entity
 */
public class Book extends Product
{
	private String author;
	private String style;
            
    public Book(ProductType type, String name, String style, String author, double price, int quantity)
    {
        super(name,price,quantity,type);
        this.author = author;
        this.style = style;
    }


    public String getAuthor()
    {
        return author;
    }

    public String getStyle()
    {
        return style;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public void setStyle(String style)
    {
        this.style = style;
    }

    @Override
	public String toString() 
    {
    	// File format: name, style, autor, price, quantity
		return this.getName() + TextFileHandle.SPLIT_COMMA + 
				this.getStyle() + TextFileHandle.SPLIT_COMMA +
				this.getAuthor() + TextFileHandle.SPLIT_COMMA +
				this.getPrice()	+ TextFileHandle.SPLIT_COMMA + 
				this.getQuantity();
	}
    
}

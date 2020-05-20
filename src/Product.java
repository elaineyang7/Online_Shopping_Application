
import java.util.HashMap;
import javax.swing.ImageIcon;
 
/***
 * 
 * This class represents a product in the store.
 *
 */
public class Product 
{

	private HashMap<String, Object> props = new HashMap<String, Object>();
	private HashMap<String, String> dispNames = new HashMap<String, String>();
	
	private String name;
	private double price;
	private int quantity;
	private ProductType type;
	protected int year;
	
	/**
	 * 
	 * Product constructor.
	 * 
	 * @param name The name of the product
	 */
	public Product(String name,double price, int quantity, ProductType type, int year)
	{
		setName(name);
		setPrice(price);
		setType(type);
		setQuantity(quantity);
		setYear(year);
	}
	
	public Product(String name,double price, int quantity, ProductType type)
	{
		setName(name);
		setPrice(price);
		setType(type);
		setQuantity(quantity);
	}
	
	public Product(String name, double price, int quantity,  int year)
	{
		setName(name);
		setPrice(price);
		setQuantity(quantity);
		setYear(year);
	}
	
	/**
	 * 
	 * Sets the name of this product.
	 * 
	 * @param name The new product name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	/**
	 * 
	 * Returns the name of this product.
	 * 
	 * @return The name of this product
	 */
	public String getName()
	{
		return name;
	}
	

	public double getPrice() 
	{
		return price;
	}

	public void setPrice(double price) 
	{
		this.price = price;
	}

	public int getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}

	public ProductType getType() 
	{
		return type;
	}

	public void setType(ProductType type) 
	{
		this.type = type;
	}

	public int getYear() 
	{
		return year;
	}

	public void setYear(int year) 
	{
		this.year = year;
	}

	/**
	 * Sets a property of this product
	 * 
	 * A property consists of an item, a display name  and a value
	 * {display name}: {value}
	 * 
	 * @param item
	 * @param displayName
	 * @param value
	 */
	public void setProperty(String item, String displayName, double value)
	{
		this.props.put(item, value);
		this.dispNames.put(item, displayName);
	}
	
	
	/**
	 * Returns the value of the specified property
	 * 
	 * @param item The property to get the value of
	 * @return The value of the property
	 */
	public Object getPropertyValue(String item)
	{
		return props.get(item);
	}
	
	
	/**
	 * Returns the display name of the specified property
	 * 
	 * @param property The property
	 * @return The display name of the property
	 */
	public String getPropertyDisplayName(String item)
	{
		return dispNames.get(item);
	}
	
	
	/**
	 * Checks if this products contains a specific property
	 * 
	 * @param item The property item
	 * @return Returns true if this product contains the property, false otherwise
	 */
	public boolean hasProperty(String item)
	{
		return props.containsKey(item);
	}
}

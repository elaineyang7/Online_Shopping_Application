
/***
 * 
 * This class represents a group of a specific product in the cart.
 *
 */
public class CartItem 
{

	/**
	 * The product that is represented by this CartItem
	 */
	public Product product;
	
	
	/**
	 * The amount of the product that this CartItem represents
	 */
	public int quantity;

	public CartItem(Product product, int quantity) 
	{
		this.product = product;
		this.quantity = quantity;
	}
	
	
	/**
	 * Adds 1 to the current quantity.
	 */
	public void add()
	{
		add(1);
	}
	
	
	/**
	 * Adds the desired amount to the quantity.
	 * 
	 * @param quantity The quantity to add
	 */
	public void add(float quantity)
	{
		this.quantity += quantity;
	}
	
	
	@Override
	public String toString()
	{
		return this.product.getName() + TextFileHandle.SPLIT_COMMA + this.quantity;
	}
}

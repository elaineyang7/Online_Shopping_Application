
import java.util.ArrayList;
import java.util.List;

/***
 * 
 * This class represents a shopping cart, the place where the selected items are held before they are purchased.
 * 
 */
public class Cart 
{
	private ArrayList<CartItem> items = new ArrayList<>();
	
	/**
	 * Adds a certain quantity of a certain product to the cart.
	 * 
	 * @param p The product to add
	 * @param quantity The quantity of the product to add
	 */
	public void add(Product p, int quantity)
	{
		for(CartItem ci : items)
		{
			if(ci.product.equals(p))
			{
				ci.quantity += quantity;
				return;
			}
		}
		add(new CartItem(p, quantity));
	}
	
	
	/**
	 * Adds a CartItem to the cart.
	 * Use add(Product, double) as that method performs checks to prevent duplicate items.
	 * 
	 * @param item The CartItem to add
	 */
	public void add(CartItem item)
	{
		items.add(item);
	}
	
	
	/**
	 * Returns a list of all CartItems.
	 * 
	 * @return The list of CartItems
	 */
	public List<CartItem> getList()
	{
		return items;
	}
	
	
	/**
	 * Removes all items from this cart.
	 */
	public void clear()
	{
		items.clear();
	}

	
	/**
	 * Replaces the list of items with a different one.
	 * 
	 * @param items The new list of items.
	 */
	public void setItems(ArrayList<CartItem> items) 
	{
		this.items = items;
	}
	
	
	/**
	 * Removes a specific item from the cart.
	 * 
	 * @param item The item to remove
	 */
	public void remove(CartItem item)
	{
		items.remove(item);
	}
	
}

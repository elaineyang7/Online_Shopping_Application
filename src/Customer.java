
/***
 * 
 * This class represents a customer, it holds all of the data that the store needs to know about someone.
 * 
 * Customer is the main user of this shopping system. 
 * Customer is able to view all the products, save products into their own cart and place orders.
 */
public class Customer
{
	//private String name; // customer name
	private String username; // user name in system
	private String password; // customer password
	private String address; // customer address
	private String cardNumber; // credit card number
	private String phone; // customer's phone number
	private Cart cart;

	// Customer account constructor
	public Customer(String username, String password, String phone, String address, String cardNumber)
	{
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.cardNumber = cardNumber;
		
		if(cart == null)
		{
			this.cart = new Cart();	
		}
	}
	
	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getAddress() 
	{
		return address;
	}

	public String getCardNumber() 
	{
		return cardNumber;
	}

	public String getPhone() 
	{
		return phone;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}

	public void setCardNumber(String cardNumber) 
	{
		this.cardNumber = cardNumber;
	}

	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public Cart getCart() 
	{
		return cart;
	}

	public void setCart(Cart cart) 
	{
		this.cart = cart;
	}

}

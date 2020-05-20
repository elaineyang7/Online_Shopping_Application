
import java.util.HashMap;
import java.util.List;

/***
 *  
 * This class is the link between the application itself and the back-end systems that control it.
 * 
 */
public interface Model 
{
	
	/**
	 * This method returns the list of all products in the store.
	 * 
	 * @return The list of products in the store.
	 */
	public List<Product> getProducts();
	
	
	/**
	 * This method determines if a login was successful or not.
	 * 
	 * The criteria for a successful login is usually:
	 * - The username is valid and the user exists
	 * - The password is a valid password
	 * 
	 * @param username The user ID of the user attempting to login
	 * @param password The supplied password to be tested
	 * @return This method returns true if the login was successful, or false if it was not
	 */
	public boolean login(String username, String password);
	
	
	/**
	 * This method creates an account for the user.
	 * 
	 * The account creation should be unsuccessful if any of the following are true:
	 * - A user with the requested username already exists.
	 * - The username is too short.
	 * - The password is too short.
	 * - The password is too insecure
	 * 
	 * @param username 
	 * @param password
	 * @return This method returns true if the user account was successfully created, and false otherwise.
	 */
	public boolean signup(String username, String password, String phone, String address, String cardNumber);
	
	
	/**
	 * This method takes a Cart object and returns the total price of all items in that cart.
	 * 
	 * @param cart The cart to calculate the price of
	 * @return The total price of all items in the cart.
	 */
	public double getPrice(Cart cart);
	
	
	/**
	 * Get a user's information.
	 * 
	 * @param username The username of the user.
	 * @return A Customer object with the user's information.
	 */
	public Customer getUserInfo(String username);
	
	
	/**
	 * Updates the users information in the back-end.
	 * 
	 * @param username The username of the user.
	 * @param info The information to set.
	 * @return Returns true if the operation succeeded, false otherwise.
	 */
	public boolean setUserInfo(String username, Customer info);
	
	
	/**
	 * This method processes an user's order. 
	 *  
	 * @param currentUsername The username of the user.
	 * @param cart The cart full of items that the user wishes to buy.
	 * @return 0 if the order was successful, return 1 if the product is not enough
	 */
	public int processOrder(String currentUsername, Cart cart);
	
	public HashMap<String, Customer> getCustomerList();
	
	public List<Product> getProductList();
	
}

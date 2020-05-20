
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/***
 * 
 * This class is the main shop system worked behind views
 */
public class ShopSystem
{
	private JFrame window = new JFrame();
	private Model backend;
	private String currentUsername;
	
	
	/**
	 * Creates a new instance of ShopSystem.
	 * Make sure to call the "init" method after this!
	 * 
	 * @param m The Model with all of the back-end links that the store is to use
	 */
	public ShopSystem(Model m)
	{
		this.backend = m;
	}
	
	
	/**
	 * Sets the shop's current view after setting the view's system and initializing it.
	 * 
	 * @param view The view to set
	 */
	public void setView(View view)
	{
		view.setSystem(this);
		view.initialize();
		window.setContentPane(view);
		window.revalidate();
	}
	
	
	/**
	 * @return The JFrame that holds the shop.
	 */
	public JFrame getWindow()
	{
		return window;
	}
	
	
	/**
	 * @return The Model instance controlling the shop.
	 */
	public Model getBackend()
	{
		return this.backend;
	}
	
	
	/**
	 * Initialize and show the shop window.
	 * Displays the LoginView.
	 */
	public void init()
	{
		window.setResizable(false);
		window.setTitle("Shop");
		window.setSize(900, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		this.setView(new LoginView());
	}
	
	
	/**
	 * Shows a popup message.
	 * 
	 * @param message The text in the popup window.
	 */
	public void showPopup(String message)
	{
		JOptionPane.showMessageDialog(window, message);
	}
	
	
	/////////////////////
	// LOGIN AND USERS //
	/////////////////////

	/**
	 * @return The customer details received from the Model instance.
	 */
	public Customer getCurrentCustomerDetails()
	{
		return getBackend().getUserInfo(currentUsername);
	}
	
	
	/**
	 * Attempts to sign up a user.
	 * 
	 * This will display an error to the user if any of the following are true:
	 * - The user ID is less than 3 chars long
	 * - The password is less than 5 chars long
	 * - The two passwords do not match.
	 * 
	 * If all of the above tests succeed then the back-end will be asked to create a user.
	 * If the back-end succeeds in creating an account, the user will be logged in, if not, they will be shown an error.
	 * 
	 * @param username The entered user ID.
	 * @param pass The entered password.
	 * @param confPass The entered confirmed password.
	 * @param address The entered address
	 * @param phone The entered phone
	 * @param card The entered card
	 */
	public void signup(String username, String pass, String confPass, String address, String phone, String card)
	{
		
		// Ensuring length
		if(username.length() < 3) 
		{
			showPopup("Your username must be at least 3 chars long!");
			return;
		}
		else if(pass.length() < 6)
		{
			showPopup("Your password must be at least 6 chars long!");
			return;
		}
		else if(!pass.equals(confPass))
		{
			showPopup("The passwords do not match");
			return;
		}
		
		boolean success = getBackend().signup(username, pass, phone, address, card);
		
		if(!success)
		{
			showPopup("Signup failed, that username may already be in use!");
		} 
		else 
		{
			showPopup("Your account has been created, please login again and edit your details by clicking 'My account' in the top right.");
			String info = "\n" + username + TextFileHandle.SPLIT_COMMA
					+ pass + TextFileHandle.SPLIT_COMMA
					+ phone + TextFileHandle.SPLIT_COMMA
					+ address + TextFileHandle.SPLIT_COMMA
					+ card;
					
			TextFileHandle.appendToFile(info, TextFileHandle.CUSTOMER_DATA);
			
			Model shopModel = new LoadData();
			
			ShopSystem s = new ShopSystem(shopModel);
			s.init();
		}
	}
	
	/**
	 * If the login succeeds, they will be presented with the product list, if not, they will be shown an error.
	 * 
	 * @param username The supplied username
	 * @param password The supplied password
	 */
	public void login(String username, String password)
	{
		if(backend.login(username, password))
		{
			currentUsername = username;
			showProductList();
			
		} else 
		{
			showPopup("Login failed! Please ensure that your username and password are correct.");
		}
	}
	
	
	/**
	 * Calls the appropriate methods on the Model instance to update the information about the current user.
	 * 
	 * If no user is logged in, an error message will be displayed in the console.
	 * 
	 * @param c The new user details.
	 */
	public void updateUserDetails(Customer c)
	{
		if (this.currentUsername != null)
		{
			boolean success = getBackend().setUserInfo(this.currentUsername, c);
			if(!success)
			{
				showPopup("There was an error saving your information! Please try again later.");
			}
		} 
		else 
		{
			System.err.println("Can't update user info, no one is signed in!");
		}
	}

	
	///////////////////////
	// PRODUCTS AND CART //
	///////////////////////

	/**
	 * Shows the checkout dialog.
	 */
	public void showCheckout()
	{
		ConfirmDialog.display(this);
	}
	
	
	/**
	 * @return The current user's cart.
	 */
	public Cart getCart()
	{
		return getCurrentCustomerDetails().getCart();
	}
	
	
	/**
	 * Adds a product to the cart.
	 * 
	 * @param p The product
	 * @param quantity The quantity to add
	 */
	public void addToCart(Product p, int quantity)
	{
		getCurrentCustomerDetails().getCart().add(p, quantity);
		
	}
	
	
	/**
	 * Shows the cart view.
	 */
	public void showCartView()
	{
		setView(new CartView());
	}
	
	
	/**
	 * See "Model.getPrice(Cart)" for more information.
	 * 
	 * @return The total price of all item in the cart
	 */
	public double getTotalCartPrice()
	{
		return getBackend().getPrice(getCurrentCustomerDetails().getCart());
	}

	
	/**
	 * Shows the product list view.
	 */
	public void showProductList() 
	{
		setView(new ProductListView());
	}

	
	/**
	 * Attempts a transaction using the current user's details and the current cart.
	 */
	public void attemptTransaction() 
	{
		Customer c = getBackend().getUserInfo(currentUsername);
		String prefix = "Order failed! ";
		if(c.getAddress().trim().equals(""))
		{
			showPopup(prefix + "You have not entered your home address!");
			return;
		}
		else if(c.getPhone().trim().equals(""))
		{
			showPopup(prefix + "You have not entered your phone number!");
			return;
		}
		else if(c.getCardNumber().trim().equals(""))
		{
			showPopup(prefix + "You have not entered your card number!");
			return;
		}
		
		int success = getBackend().processOrder(currentUsername, getCurrentCustomerDetails().getCart());
		
		String wrongMsg = "Sorry, your order could not be placed! ";
		if( success == 1 )
		{
			showPopup(wrongMsg + "Product quantity is larger than stock.");
		}
		else if (success == 2)
		{
			showPopup(wrongMsg + "Please ensure that all of your information is correct.");
		}
		else 
		{
			showPopup("Your order has been placed successfully! Have a nice day!");
			getCurrentCustomerDetails().getCart().clear();
			this.showCartView();
		}
	}
	
	
	/**
	 * Store all customer's cart data into file
	 */
	public void storeCartData()
	{
		String cartData = "";
		for(Entry<String, Customer> entry : getBackend().getCustomerList().entrySet()) 
		{
		        // Retrieve each customer
		        Customer customer = entry.getValue();
		        cartData += entry.getKey();
		        
		        // Get customer's cart 
		        Cart cart = customer.getCart();
		        
		        // Get itemList in cart
		        for (CartItem ct: cart.getList())
		        {
		        	cartData += TextFileHandle.SPLIT_COMMA + ct.toString();
		        }
		        cartData += "\n";
		    }
		TextFileHandle.writeToFile(cartData, TextFileHandle.CART_FILE);
	}
	
	
	/**
	 * Add product item into productList
	 * @param product item
	 */
	
	public void addProduct(Product p)
	{
		this.backend.getProductList().add(p);
	}
	
}

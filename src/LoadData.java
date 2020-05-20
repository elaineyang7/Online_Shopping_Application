import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/***
 * 
 * This class contains some basic funtions of shopping system
 */
public class LoadData implements Model 
{
	ArrayList<Product> productList = new ArrayList<Product>();
	HashMap<String, String> passwords = new HashMap<>();
	HashMap<String, Customer> customerList = new HashMap<>();
	List<Order> orderList = new ArrayList<Order>();

	public LoadData() 
	{
		loadData();
	}

	private void loadData() 
	{
		// load product data
		loadProductData();
		
		// load customer data
		loadCusteomerData();
		
		// load orer data
		orderList = loadOrderList();
	}

	/**
	 * load product data
	 */
	private void loadProductData() 
	{
		loadBookData();
		loadCDData();
		loadMovieData();
	}
	
	
	/**
	 * Load book data from file File format:
	 * name, style, author, price, quantity
	 */
	private void loadBookData() 
	{
		String bookData = TextFileHandle.readFromFile(TextFileHandle.BOOK_FILE);
		String[] bookArray = bookData.split(TextFileHandle.SPLIT_CEMI);
		for (int i = 0; i < bookArray.length; i++) 
		{
			String[] book = bookArray[i].split(TextFileHandle.SPLIT_COMMA);
			String name = book[0];
			String style = book[1];
			String author = book[2];
			float price = Float.parseFloat(book[3]);
			int quantity = Integer.parseInt(book[4]);
			
			Book b = new Book(ProductType.BOOK, name, style, author, price, quantity);
			productList.add(b);
		}
	}
	
	
	/**
	 * load CD data
	 * name, singer, genre, year, quantity, numOfSongs, price
	 */
	private void loadCDData() 
	{
		String CDData = TextFileHandle.readFromFile(TextFileHandle.CD_FILE);
		String[] CDArray = CDData.split(TextFileHandle.SPLIT_CEMI);
		for (int i = 0; i < CDArray.length; i++) 
		{
			String[] CD = CDArray[i].split(TextFileHandle.SPLIT_COMMA);
			String name = CD[0];
			String singer = CD[1];
			String genre = CD[2];
			int year = Integer.parseInt(CD[3]);
			int quantity = Integer.parseInt(CD[4]);
			int numOfSongs = Integer.parseInt(CD[5]);
			float price = Float.parseFloat(CD[6]);
			
			CD CDObj = new CD(ProductType.CD, name, price, year, genre, singer, quantity, numOfSongs);
			productList.add(CDObj);
		}
	}

	
	/**
	 * Load movie data from file File format:
	 * name, year, genre, price, director, starring, quantity
	 */
	private void loadMovieData() 
	{
		String movieData = TextFileHandle.readFromFile(TextFileHandle.MOVIE_FILE);
		String[] movieArray = movieData.split(TextFileHandle.SPLIT_CEMI);
		for (int i = 0; i < movieArray.length; i++)
		{
			String[] movie = movieArray[i].split(TextFileHandle.SPLIT_COMMA);
			String name = movie[0];
			int year = Integer.parseInt(movie[1]);
			String genre = movie[2];
			float price = Float.parseFloat(movie[3]);
			String director = movie[4];
			String starring = movie[5];
			int quantity = Integer.parseInt(movie[6]);

			Movie c = new Movie(ProductType.MOVIE, name, price, year, genre, director, starring, quantity);
			productList.add(c);
		}
	}


	/**
	 * Load customer data
	 */
	private void loadCusteomerData() 
	{
		loadCustomerDetail();
		loadCustomerCart();
	}

	
	/**
	 * Load customer personal information
	 */
	private void loadCustomerDetail() 
	{
		String customerData = TextFileHandle.readFromFile(TextFileHandle.CUSTOMER_DATA);
		String[] customerArray = customerData.split(TextFileHandle.SPLIT_CEMI);
		
		for (int i = 0; i < customerArray.length; i++) 
		{
			String[] customers = customerArray[i].split(TextFileHandle.SPLIT_COMMA);
			
			String username = customers[0];
			String password = customers[1];
			String phone = customers[2];
			String address = customers[3];
			String cardNumber = customers[4];
			
			Customer c = new Customer(username, password, phone, address, cardNumber);
			
			customerList.put(username, c);
			passwords.put(username, password);
		}
	}

	
	/**
	 * Load customer cart information
	 */
	private void loadCustomerCart() 
	{
		String cartData = TextFileHandle.readFromFile(TextFileHandle.CART_FILE);
		String[] cartArray = cartData.split(TextFileHandle.SPLIT_CEMI);
		
		for (int i = 0; i < cartArray.length; i++) 
		{
			String[] cartOfCustomer = cartArray[i].split(TextFileHandle.SPLIT_COMMA);
			String username = cartOfCustomer[0];
			
			Cart cart = customerList.get(username).getCart();
			
			for (int j = 1; j < cartOfCustomer.length; j = j + 2) 
			{
				Product p = getProductByName(cartOfCustomer[j]);
				int productQuantity = Integer.parseInt(cartOfCustomer[j + 1]);
				CartItem ct = new CartItem(p, productQuantity);
				cart.add(ct);
			}
		}
	}
	

	/**
	 * Gets a product by its name
	 * 
	 * @param name product name
	 * @return product
	 */
	private Product getProductByName(String name) 
	{
		for (Product p : productList) 
		{
			if (p.getName().equals(name)) 
			{
				return p;
			}
		}
		return null;
	}

	
	@Override
	public List<Product> getProducts() 
	{
		return productList;
	}
	

	@Override
	public boolean login(String username, String password)
	{
		
		Customer loginUser = customerList.get(username);
		if (loginUser != null && password.equals(loginUser.getPassword())) 
		{
			return true;
		}
	
		return false;
	}
	

	@Override
	public boolean signup(String username, String password, String phone, String address, String cardNumber) 
	{
		if (passwords.containsKey(username)) 
		{
			return false;
		}
		passwords.put(username, password);
		customerList.put(username, new Customer(username, "", "", "",""));
		return true;
	}
	

	@Override
	public Customer getUserInfo(String username) 
	{
		return customerList.get(username);
	}
	

	@Override
	public boolean setUserInfo(String username, Customer info) 
	{
		customerList.put(username, info);
		return true;
	}

	
	@Override
	public double getPrice(Cart cart) 
	{
		double total = 0;
		for (CartItem item : cart.getList()) 
		{
			total += item.product.getPrice() * item.quantity;
			
		}
		return total;
	}
	

	@Override
	public int processOrder(String currentUsername, Cart cart) 
	{
		// Check if the quantity is enough
		for (CartItem ct : cart.getList()) 
		{
			// if product quantity is not enough, not processing
			int productQuantity = ct.product.getQuantity();
			if (productQuantity < ct.quantity) 
			{
				return 1;
			}
			// Update the quantity of the product
			int currentProductQuantity = (int) (productQuantity - ct.quantity);
			ct.product.setQuantity(currentProductQuantity);
		}
		// save product
		saveProductData();
		// save order
		saveOrderData(currentUsername, cart);
		return 0;
	}

	
	/***
	 * Save order data into file
	 */
	public void saveOrderData(String currentUsername, Cart cart) 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		// order file data format
		String dateStr = dateFormat.format(date);
		
		// Order id
		int orderid = orderList.size() + 1;

		// Add order into orderList
		Order order = new Order();
		order.setCustomerUsername(currentUsername);
		order.setOrderItem(cart.getList());
		order.setOrderedDate(dateStr);
		order.setOrderId(orderid);
		orderList.add(order);

		// orderId, username, time, productName, productQuantity, productName, productQuantity
		String orderData = orderid + TextFileHandle.SPLIT_COMMA + currentUsername
				+ TextFileHandle.SPLIT_COMMA + dateStr + TextFileHandle.SPLIT_COMMA;
		
		for (CartItem ct : cart.getList()) 
		{
			// different product and its quantity is separated by ","
			orderData += ct.toString() + TextFileHandle.SPLIT_COMMA;
		}
		orderData += "\n";
		
		TextFileHandle.appendToFile(orderData, TextFileHandle.ORDER_FILE);
	}

	
	/**
	 * Save product data into file
	 */
	public void saveProductData() 
	{
		// initialize different data which needs to be stored in file
		String bookData = "";
		String CDData = "";
		String movieData = "";
		
		for (Product p : productList) 
		{
			switch (p.getType()) 
			{
			case BOOK:
				bookData += p.toString() + "\n";
				break;
			case CD:
				CDData += p.toString() + "\n";
				break;
			case MOVIE:
				movieData += p.toString() + "\n";
				break;
			default:
				break;
			}
		}
		TextFileHandle.writeToFile(bookData, TextFileHandle.BOOK_FILE);
		TextFileHandle.writeToFile(CDData, TextFileHandle.CD_FILE);
		TextFileHandle.writeToFile(movieData, TextFileHandle.MOVIE_FILE);
	}
	

	@Override
	public HashMap<String, Customer> getCustomerList() 
	{
		return customerList;
	}

	
	@Override
	public List<Product> getProductList() 
	{
		return productList;
	}
	

	private List<Order> loadOrderList() 
	{
		List<Order> orders = new ArrayList<Order>();
		String orderData = TextFileHandle.readFromFile(TextFileHandle.ORDER_FILE);
		String[] orderArray = orderData.split(TextFileHandle.SPLIT_CEMI);
		Order o;
		
		for (int i = 0; i < orderArray.length; i++) 
		{
			o = new Order();
			String[] order = orderArray[i].split(TextFileHandle.SPLIT_COMMA);
			int orderId = Integer.parseInt(order[0]);
			String currentUsername = order[1];
			String orderedDate = order[2].substring(0, 10);
			
			// Set attributes into Order object
			o.setOrderId(orderId);
			o.setCustomerUsername(currentUsername);
			o.setOrderedDate(orderedDate);
			
			// Assemble order's cartItem data
			for (int j = 3; j < order.length; j = j + 2) 
			{
				String pname = order[j];
				Product p = getProductByName(pname);
				int pquantity = Integer.parseInt(order[j + 1]);
				CartItem ci = new CartItem(p, pquantity);
				o.getOrderItem().add(ci);
			}
			orders.add(o);
		}
		return orders;
	}
	
}

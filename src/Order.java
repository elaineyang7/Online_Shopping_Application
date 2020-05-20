import java.util.*;

/***
 * 
 * This class represent a order entity
 */
public class Order 
{
	private int orderId;
	private String customerUsername;
	private String orderedDate;
	private List<CartItem> orderItem;
	private String deliverAddress;
	
	public Order()
	{
		orderItem = new ArrayList<CartItem>();
	}

	public int getOrderId() 
	{
		return orderId;
	}

	public void setOrderId(int orderId) 
	{
		this.orderId = orderId;
	}
	
	public String getCustomerUsername() 
	{
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) 
	{
		this.customerUsername = customerUsername;
	}

	public String getOrderedDate() 
	{
		return orderedDate;
	}

	public void setOrderedDate(String orderedDate) 
	{
		this.orderedDate = orderedDate;
	}

	public List<CartItem> getOrderItem() 
	{
		return orderItem;
	}

	public void setOrderItem(List<CartItem> orderItem) 
	{
		this.orderItem = orderItem;
	}

	public String getDeliverAddress()
	{
		return deliverAddress;
	}
	
	public void setDeliverAddress(String deliverAddress)
	{
		this.deliverAddress = deliverAddress;
	}
}

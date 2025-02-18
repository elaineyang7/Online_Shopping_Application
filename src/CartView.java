
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

/***
 * 
 * This class represents the customer's cart view.
 */
public class CartView extends View 
{
	private static final long serialVersionUID = 1L;
	private static NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
	
	private JPanel scrollPanel;
	private JButton btnClear, btnCheckout;

	public CartView() 
	{

		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel, BorderLayout.NORTH);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				getSystem().showProductList();
			}
		});
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(btnBack);
		
		btnClear = new JButton("Remove all from cart");
		panel.add(btnClear);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		
		btnCheckout = new JButton("Checkout");
		panel.add(btnCheckout);

		scrollPanel = new JPanel();
		JScrollPane scroll = new JScrollPane(scrollPanel);
		scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(scroll, BorderLayout.CENTER);
		
	}

	public void initialize() 
	{
		
		scrollPanel.removeAll();
		
		for(final CartItem item : getSystem().getCart().getList())
		{
			JPanel itemPanel = new JPanel();
			itemPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
			itemPanel.setAlignmentX(LEFT_ALIGNMENT);
			itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
			JPanel titlePanel = new JPanel();
			itemPanel.add(titlePanel);
			titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
			JLabel lblNewLabel_3 = new JLabel(item.product.getName());
			lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			titlePanel.add(lblNewLabel_3);
			Component horizontalGlue_1 = Box.createHorizontalGlue();
			titlePanel.add(horizontalGlue_1);
			JPanel propertiesPanel = new JPanel();
			itemPanel.add(propertiesPanel);
			propertiesPanel.setLayout(new BoxLayout(propertiesPanel, BoxLayout.X_AXIS));
			JPanel quantityPanel = new JPanel();
			propertiesPanel.add(quantityPanel);
			quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.X_AXIS));
			JLabel lblQuantuty = new JLabel("Quantity: ");
			quantityPanel.add(lblQuantuty);
			JLabel lblNewLabel_1 = new JLabel(item.quantity + "");
			quantityPanel.add(lblNewLabel_1);
			JLabel spacer_1 = new JLabel("          ");
			quantityPanel.add(spacer_1);
			JLabel lblNewLabel = new JLabel("Price ($):   ");
			quantityPanel.add(lblNewLabel);
			
			Cart thisItemInACart = new Cart();
			thisItemInACart.add(item);
			
			String priceString = defaultFormat.format(item.product.getPrice() * item.quantity);
			JLabel lblNewLabel_2 = new JLabel(priceString);
			
			quantityPanel.add(lblNewLabel_2);
			JLabel label = new JLabel("          ");
			quantityPanel.add(label);
			
			JButton btnRemove = new JButton("Remove");
			btnRemove.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					getSystem().getCart().remove(item);
					getSystem().showCartView();
				}
			});
			propertiesPanel.add(btnRemove);
			
			Component horizontalGlue = Box.createHorizontalGlue();
			propertiesPanel.add(horizontalGlue);
			JSeparator separator = new JSeparator();
			itemPanel.add(separator);
			scrollPanel.add(itemPanel);
		}
		
		btnClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				getSystem().getCart().setItems(new ArrayList<CartItem>());
				getSystem().showCartView();
			}
		});
		
		btnCheckout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				getSystem().showCheckout();
			}
		});
		
		Component verticalGlue = Box.createVerticalGlue();
		scrollPanel.add(verticalGlue);
		
	}

}

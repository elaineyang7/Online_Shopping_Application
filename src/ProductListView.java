
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.Box;

/***
 * 
 * This class is the main view for customer shopping.
 * The products list page
 */
public class ProductListView extends View 
{
	private static final long serialVersionUID = 1L;
	
	private JPanel scrollPanel;
	
	public ProductListView() 
	{
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			
			JLabel lblNewLabel = new JLabel("   ");
			panel.add(lblNewLabel);
		
			//Product Categorize GUI
			JLabel comboBoxLabel = new JLabel();
			comboBoxLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			panel.add(comboBoxLabel);
			comboBoxLabel.setHorizontalAlignment(SwingConstants.LEFT);
			comboBoxLabel.setText("Choose Product Category:");
			
			final JComboBox<String> productBox = new JComboBox<String>();
			panel.add(productBox);
			
			Component horizontalGlue_1 = Box.createHorizontalGlue();
			panel.add(horizontalGlue_1);
			
			Component horizontalGlue = Box.createHorizontalGlue();
			panel.add(horizontalGlue);
			
			JButton myInfoButton = new JButton("My account");
			panel.add(myInfoButton);
			
			JButton cartButton = new JButton("View cart");
			panel.add(cartButton);
			
			// log out
			JButton logoutBtn = new JButton("Logout");
			panel.add(logoutBtn);
			logoutBtn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to logout?","Confirm", 2);
					if(dialogResult == JOptionPane.YES_OPTION)
					{
						// save cart data
						getSystem().storeCartData();
						
						// redirect to log in view
						getSystem().getWindow().dispose();
						getSystem().init();
					}
				}
			});
			
			cartButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					getSystem().showCartView();
				}
			});
			
			myInfoButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					CustomerDetails.display(getSystem());
				}
			});
			productBox.addItem("All Products");
			productBox.addItem("Book");
			productBox.addItem("CD");
			productBox.addItem("Movie");
			
			productBox.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					String s = (String) productBox.getSelectedItem();
					
					switch (s){
					case "CD":
						initializeByProduct("CD");
						break;
					case "Movie":
						initializeByProduct("Movie");
						break;
					case "Book":
						initializeByProduct("Book");
						break;
					default:
						initialize();break;
					}
				}
			});
		
		scrollPanel = new JPanel();
		JScrollPane scroll = new JScrollPane(scrollPanel);
		scrollPanel.setLayout(new GridLayout(0, 2, 0, 0));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(scroll, BorderLayout.CENTER);

	}

	public void initialize() 
	{
		scrollPanel.removeAll();
		List<Product> list = getSystem().getBackend().getProducts();
		for(Product p : list)
		{
			scrollPanel.add(new ProductThumbnail(getSystem(), p));
		}
		revalidate();
	}
	
	// display product by catogorization
	public void initializeByProduct(String productType) 
	{
		scrollPanel.removeAll();
		List<Product> list = getSystem().getBackend().getProducts();
		for(Product p : list)
		{
			if(p.getType().toString().equals(productType.toUpperCase()))
			scrollPanel.add(new ProductThumbnail(getSystem(), p));
		}
		revalidate();
	}
}

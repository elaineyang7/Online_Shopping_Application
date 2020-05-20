
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/***
 * 
 * This is the view showed when customer clicked "View" in the product list, 
 * and shows more detailed information.
 */
public class ProductDetails extends JDialog 
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JSpinner spinner;

	public static void display(ShopSystem s, Product p)
	{
		ProductDetails dialog = new ProductDetails(s, p);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(s.getWindow());
		dialog.setVisible(true);
	}

	public ProductDetails(final ShopSystem s, final Product p) 
	{
		setBounds(100, 100, 461, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			contentPanel.add(panel);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			{
				JLabel lblNewLabel_3 = new JLabel();
				//lblNewLabel_3.setIcon(p.getImage());
				panel.add(lblNewLabel_3);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setBorder(null);
				FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
				fl_panel_1.setAlignment(FlowLayout.LEFT);
				{
					JLabel lblNewLabel_1 = new JLabel(p.getName());
					panel_1.add(lblNewLabel_1);
					lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
					lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
				fl_panel_1.setAlignment(FlowLayout.LEFT);
				panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
				{
					
					String priceString = "Price: ";
					NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
					priceString += defaultFormat.format(p.getPrice());

					JLabel lblNewLabel_2 = new JLabel(priceString);
					panel_1.add(lblNewLabel_2);
					
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
				fl_panel_1.setAlignment(FlowLayout.LEFT);
				panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
				{
					String info = getInfo(p);
					
					JLabel lblNewLabel_3 = new JLabel(info);
					lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
					panel_1.add(lblNewLabel_3);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel lblNewLabel = new JLabel("Quantity:");
				buttonPane.add(lblNewLabel);
			}
			{
				spinner = new JSpinner();
				spinner.setModel(new SpinnerNumberModel(1, 0, 100000, 1));
				spinner.setPreferredSize(new Dimension(100, 30));
				buttonPane.add(spinner);
			}
			{
				final JDialog me = this;
				{
					JLabel lblNewLabel_4 = new JLabel("             ");
					buttonPane.add(lblNewLabel_4);
				}
				JButton okButton = new JButton("Add to cart");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						s.addToCart(p, Integer.parseInt(spinner.getValue() + ""));
						me.dispose();
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				final JDialog me = this;
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						me.dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	// get each product type information
	public String getInfo(Product p)
	{
		String info = "<html>DESCRIPTION<br>";
		String[] data = p.toString().split(TextFileHandle.SPLIT_COMMA);
		
		switch (p.getType()) 
		{
		case BOOK:
			info += "Autor: " + data[2] + "<br>" 
					+ "Book type: " + data[1]+ "</html>";
			break;
		case CD:
			info += "Singer: " + data[1] + "<br>" 
					+ "Genre: " + data[2] + "<br>" 
					+ "Year: " + data[3] + "<br>" 
					+ "Number of songs: " + data[5] + "</html>";
			break;
		case MOVIE:
			String[] startingList = data[5].split("\t");
			String starting = "";
			for (String s : startingList)
			{
				starting += s + "<br>";
			}
			info += "Year: " + data[1] + "<br>" 
					+ "Genre: " + data[2] + "<br>" 
					+ "Director: " + data[4] + "<br>" 
					+ "Starring: " + "<br>" + starting + "</html>";
			
			break;
		default:
			break;
		}
		return info;
	}
}

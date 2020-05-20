
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/***
 * 
 * This class is the view used for customer checking out and confirm the order.
 */
public class ConfirmDialog extends JDialog 
{
	private static final long serialVersionUID = 1L;
	private static NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();

	public static void display(ShopSystem s)
	{
		ConfirmDialog dialog = new ConfirmDialog(s);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(s.getWindow());
		dialog.setVisible(true);
	}
	
	public ConfirmDialog(final ShopSystem s) 
	{
		final JDialog me = this;
		setBounds(100, 100, 502, 591);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			{
				JLabel lblNewLabel = new JLabel("Order Details");
				panel.add(lblNewLabel);
			}
			{
				JLabel spacer = new JLabel("  ");
				panel.add(spacer);
			}
			{
				String text = "<html>";
				
				double total = 0;
				for(CartItem item : s.getCart().getList())
				{
					Cart thisItemInACart = new Cart();
					thisItemInACart.add(item);
					String priceString = defaultFormat.format(s.getBackend().getPrice(thisItemInACart));
					total += s.getBackend().getPrice(thisItemInACart);
					text += item.product.getName() +
							"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;x: " + item.quantity +
							"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;price: " + priceString + "<br>" + "<br>";
							//"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PRICE: " + c.getBackend().getPrice(thisItemInACart) + "<br>";
				}
				String totalString = defaultFormat.format(total);
				text += "Total Price: " + totalString + "</html>";
				{
					JLabel lblNewLabel_1 = new JLabel("Item:");
					panel.add(lblNewLabel_1);
				}
				{
					JLabel spacer = new JLabel("  ");
					panel.add(spacer);
				}
				JLabel details = new JLabel(text);
				panel.add(details);
			}
			{
				JLabel spacer = new JLabel("  ");
				panel.add(spacer);
			}
			{
				JLabel spacer = new JLabel("  ");
				panel.add(spacer);
			}
			{
				JLabel lblNewLabel = new JLabel("Delivery Address:");
				panel.add(lblNewLabel);
			}
			{
				JLabel spacer = new JLabel("  ");
				panel.add(spacer);
			}
			{
				String text = "<html>";
				
				text += s.getCurrentCustomerDetails().getAddress();
				
				text += "</html>";
				JLabel details = new JLabel(text);
				panel.add(details);
			}
			{
				JLabel spacer = new JLabel("  ");
				panel.add(spacer);
			}
			{
				JLabel lblNewLabel = new JLabel("Phone Number:");
				panel.add(lblNewLabel);
			}
			{
				JLabel spacer = new JLabel("  ");
				panel.add(spacer);
			}
			{
				String text = "<html>";
				
				text += s.getCurrentCustomerDetails().getPhone();
				
				text += "</html>";
				JLabel details = new JLabel(text);
				panel.add(details);
			}
			{
				JLabel spacer = new JLabel("  ");
				panel.add(spacer);
			}
			{
				JLabel lblNewLabel = new JLabel("Creadit Card Number:");
				panel.add(lblNewLabel);
			}
			{
				JLabel spacer = new JLabel("  ");
				panel.add(spacer);
			}
			{
				String text = "<html>";
				
				text += s.getCurrentCustomerDetails().getCardNumber();
				
				text += "</html>";
				JLabel details = new JLabel(text);
				panel.add(details);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton confirmButton = new JButton("Confirm order");
				confirmButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						s.attemptTransaction();
						me.dispose();
					}
				});
				confirmButton.setActionCommand("OK");
				buttonPane.add(confirmButton);
				getRootPane().setDefaultButton(confirmButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						me.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

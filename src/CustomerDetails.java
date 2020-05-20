
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/***
 * 
 * This class is the view shows customer's personal information.
 */
public class CustomerDetails extends JDialog 
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField username;
	private JTextField phoneNumber;
	private JTextField homeAddr;
	private JTextField cardNum;

	public static void display(ShopSystem s)
	{
		CustomerDetails dialog = new CustomerDetails(s);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(s.getWindow());
		dialog.setVisible(true);
	}
	
	public Customer toCustomer(Customer customer)
	{
		customer.setAddress(homeAddr.getText());
		customer.setUsername(username.getText());
		customer.setCardNumber(cardNum.getText());
		customer.setPhone(phoneNumber.getText());
		return customer;
	}
	
	public CustomerDetails(final ShopSystem s) 
	{
		
		Customer user = s.getCurrentCustomerDetails();
		
		setTitle("User Information");
		setBounds(100, 100, 450, 300);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 143, 251, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblUserName = new JLabel("Username:");
			lblUserName.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblUserName = new GridBagConstraints();
			gbc_lblUserName.anchor = GridBagConstraints.WEST;
			gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
			gbc_lblUserName.gridx = 1;
			gbc_lblUserName.gridy = 1;
			contentPanel.add(lblUserName, gbc_lblUserName);
		}
		{
			username = new JTextField();
			GridBagConstraints gbc_username = new GridBagConstraints();
			gbc_username.insets = new Insets(0, 0, 5, 0);
			gbc_username.fill = GridBagConstraints.HORIZONTAL;
			gbc_username.gridx = 2;
			gbc_username.gridy = 1;
			contentPanel.add(username, gbc_username);
			username.setColumns(10);
			username.setText(user.getUsername());
		}
		{
			JLabel lblPhoneNumber = new JLabel("Phone number:");
			GridBagConstraints gbc_lblPhoneNumber = new GridBagConstraints();
			gbc_lblPhoneNumber.anchor = GridBagConstraints.WEST;
			gbc_lblPhoneNumber.insets = new Insets(0, 0, 5, 5);
			gbc_lblPhoneNumber.gridx = 1;
			gbc_lblPhoneNumber.gridy = 2;
			contentPanel.add(lblPhoneNumber, gbc_lblPhoneNumber);
		}
		{
			phoneNumber = new JTextField();
			GridBagConstraints gbc_phoneNumber = new GridBagConstraints();
			gbc_phoneNumber.insets = new Insets(0, 0, 5, 0);
			gbc_phoneNumber.fill = GridBagConstraints.HORIZONTAL;
			gbc_phoneNumber.gridx = 2;
			gbc_phoneNumber.gridy = 2;
			contentPanel.add(phoneNumber, gbc_phoneNumber);
			phoneNumber.setColumns(10);
			phoneNumber.setText(user.getPhone());
		}
		{
			JLabel lblHomeAddress = new JLabel("Home address:");
			lblHomeAddress.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblHomeAddress = new GridBagConstraints();
			gbc_lblHomeAddress.anchor = GridBagConstraints.WEST;
			gbc_lblHomeAddress.insets = new Insets(0, 0, 5, 5);
			gbc_lblHomeAddress.gridx = 1;
			gbc_lblHomeAddress.gridy = 3;
			contentPanel.add(lblHomeAddress, gbc_lblHomeAddress);
		}
		{
			homeAddr = new JTextField();
			GridBagConstraints gbc_homeAddr = new GridBagConstraints();
			gbc_homeAddr.insets = new Insets(0, 0, 5, 0);
			gbc_homeAddr.fill = GridBagConstraints.HORIZONTAL;
			gbc_homeAddr.gridx = 2;
			gbc_homeAddr.gridy = 3;
			contentPanel.add(homeAddr, gbc_homeAddr);
			homeAddr.setColumns(10);
			homeAddr.setText(user.getAddress());
		}
		{
			JLabel lblCardNumber = new JLabel("Credit card number:");
			GridBagConstraints gbc_lblCardNumber = new GridBagConstraints();
			gbc_lblCardNumber.anchor = GridBagConstraints.WEST;
			gbc_lblCardNumber.insets = new Insets(0, 0, 0, 5);
			gbc_lblCardNumber.gridx = 1;
			gbc_lblCardNumber.gridy = 4;
			contentPanel.add(lblCardNumber, gbc_lblCardNumber);
		}
		{
			cardNum = new JTextField();
			GridBagConstraints gbc_cardNum = new GridBagConstraints();
			gbc_cardNum.fill = GridBagConstraints.HORIZONTAL;
			gbc_cardNum.gridx = 2;
			gbc_cardNum.gridy = 4;
			contentPanel.add(cardNum, gbc_cardNum);
			cardNum.setColumns(10);
			cardNum.setText(user.getCardNumber());
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				final JDialog me = this;
				JButton okButton = new JButton("Save");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						s.updateUserDetails(toCustomer(s.getCurrentCustomerDetails()));
						me.dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				final JDialog me = this;
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						me.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

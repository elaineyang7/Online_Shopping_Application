
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/***
 * 
 * This class is worked when user created an account.
 */
public class SignupView extends View 
{

	private JTextField username, pwd, card;
	private JButton newAccButton;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblUserName;
	private JPanel panel_4;
	private JLabel lblPassword;
	private JPanel panel_1;
	private JPanel panel_5;
	private JLabel lblConfirmPassword;
	private JTextField pwdConfirm;
	private JButton btnNewButton;
	private JLabel lblUserId_1;
	private JTextField textField;
	private JLabel lblUserId_2;
	private JLabel lbUserCardNumber;
	private JTextField textField_1;
	private JPanel panel_6;
	private JLabel lblEnterYourAddress;
	private JTextField add;
	private JPanel panel_7;
	private JLabel lblEnterYourPhone;
	private JTextField phone;
	private JPanel panel_8;
	private JLabel lblEnterYourCredit;
	private JTextField textField_2;
	private Component horizontalGlue;
	private Component horizontalGlue_1;
	private Component horizontalGlue_2;
	private Component horizontalGlue_3;
	private Component horizontalGlue_4;
	private Component horizontalGlue_5;

	public SignupView() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{546, 0};
		gridBagLayout.rowHeights = new int[]{383, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		panel_3 = new JPanel();
		panel_1.add(panel_3);
		FlowLayout fl_panel_3 = (FlowLayout) panel_3.getLayout();
		fl_panel_3.setVgap(0);
		//flowLayout.setHgap(0);
		fl_panel_3.setAlignment(FlowLayout.LEFT);
		
		lblUserName = new JLabel("Enter your username              ");
		panel_3.add(lblUserName);
		
		horizontalGlue = Box.createHorizontalGlue();
		panel_3.add(horizontalGlue);
		
		username = new JTextField();
		username.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_3.add(username);
		username.setColumns(10);
		
		panel_4 = new JPanel();
		panel_1.add(panel_4);
		FlowLayout fl_panel_4 = (FlowLayout) panel_4.getLayout();
		fl_panel_4.setVgap(0);
		//flowLayout_1.setHgap(0);
		fl_panel_4.setAlignment(FlowLayout.LEFT);
		
		lblPassword = new JLabel("Enter your password              ");
		panel_4.add(lblPassword);
		
		horizontalGlue_1 = Box.createHorizontalGlue();
		panel_4.add(horizontalGlue_1);
		
		pwd = new JTextField();
		pwd.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(pwd);
		pwd.setColumns(10);
		
		panel_5 = new JPanel();
		FlowLayout fl_panel_5 = (FlowLayout) panel_5.getLayout();
		fl_panel_5.setVgap(0);
		//flowLayout_2.setHgap(0);
		fl_panel_5.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_5);
		
		lblConfirmPassword = new JLabel("Conform your password        ");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
		panel_5.add(lblConfirmPassword);
		
		horizontalGlue_2 = Box.createHorizontalGlue();
		panel_5.add(horizontalGlue_2);
		
		pwdConfirm = new JTextField();
		pwdConfirm.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_5.add(pwdConfirm);
		pwdConfirm.setColumns(10);
		
		panel_7 = new JPanel();
		FlowLayout fl_panel_7 = (FlowLayout) panel_7.getLayout();
		fl_panel_7.setVgap(0);
		//flowLayout_7.setHgap(0);
		fl_panel_7.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_7);
		
		lblEnterYourPhone = new JLabel("Enter your phone number      ");
		panel_7.add(lblEnterYourPhone);
		
		horizontalGlue_3 = Box.createHorizontalGlue();
		panel_7.add(horizontalGlue_3);
		
		phone = new JTextField();
		phone.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_7.add(phone);
		phone.setColumns(10);
		
		panel_6 = new JPanel();
		FlowLayout fl_panel_6 = (FlowLayout) panel_6.getLayout();
		fl_panel_6.setVgap(0);
		//flowLayout_3.setHgap(0);
		fl_panel_6.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_6);
		
		lblEnterYourAddress = new JLabel("Enter your address                 ");
		lblEnterYourAddress.setHorizontalAlignment(SwingConstants.LEFT);
		panel_6.add(lblEnterYourAddress);
		
		horizontalGlue_4 = Box.createHorizontalGlue();
		panel_6.add(horizontalGlue_4);
		
		add = new JTextField();
		add.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_6.add(add);
		add.setColumns(10);
		
		panel_8 = new JPanel();
		FlowLayout fl_panel_8 = (FlowLayout) panel_8.getLayout();
		fl_panel_8.setVgap(0);
		//fl_panel_8.setHgap(0);
		fl_panel_8.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_8);
		
		lblEnterYourCredit = new JLabel("Enter your credit card number");
		panel_8.add(lblEnterYourCredit);
		
		horizontalGlue_5 = Box.createHorizontalGlue();
		panel_8.add(horizontalGlue_5);
		
		card = new JTextField();
		card.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_8.add(card);
		card.setColumns(10);
		
		panel_2 = new JPanel();
		FlowLayout fl_panel_2 = (FlowLayout) panel_2.getLayout();
		fl_panel_2.setVgap(0);
		fl_panel_2.setHgap(0);
		fl_panel_2.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_2);
		
		newAccButton = new JButton("Create my account");
		newAccButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				getSystem().signup(username.getText(), pwd.getText(), pwdConfirm.getText(), phone.getText(), add.getText(), card.getText());
			}
		});
		
		btnNewButton = new JButton("Back to login");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				getSystem().setView(new LoginView());
			}
		});
		panel_2.add(btnNewButton);
		panel_2.add(newAccButton);
		
		panel_1.setMaximumSize( new Dimension(500, 800) );
		
	}

	public void initialize() 
	{
		
	}

}

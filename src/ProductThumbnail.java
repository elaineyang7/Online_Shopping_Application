
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ProductThumbnail extends JPanel 
{
	private static final long serialVersionUID = 1L;

	public ProductThumbnail(final ShopSystem s, final Product p) 
	{
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel title = new JLabel(p.getName());
		panel.add(title);
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JButton view = new JButton("View");
		panel_1.add(view);
		
		
		JButton addOne = new JButton("Add to cart");
		panel_1.add(addOne);
		
		addOne.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				//System.out.print(p.getName()); //debug
				s.addToCart(p, 1);
				
			}
		});
		
		view.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				ProductDetails.display(s, p);
			}
		});

	}

}

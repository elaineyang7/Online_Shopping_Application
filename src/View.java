
import javax.swing.JPanel;

/***
 * 
 * This is an abstract class view which is a self contained "page" in the application.
 * 
 */
public abstract class View extends JPanel 
{
	public View() {
	}
	private static final long serialVersionUID = 1L;
	private ShopSystem system;
	
	/**
	 * This sets the system for this view, 
	 * this system is often used by the views to trigger events or request data from the back-end.
	 * 
	 * @param s The system
	 */
	public void setSystem(ShopSystem s)
	{
		this.system = s;
	}

	/**
	 * Returns this view's system.
	 * 
	 * @return This views system
	 */
	public ShopSystem getSystem()
	{
		return system;
	}
	
	/**
	 * This method is called every time the view is displayed to the screen.
	 * Use this method to initialize all of the components in the view with data from the system
	 */
	public abstract void initialize();
	
}

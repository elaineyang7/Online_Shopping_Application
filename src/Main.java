
/***
 * 
 * This is the entry of the whole system.
 */
public class Main 
{
	public static void main(String[] args)
	{
		
		Model shopModel = new LoadData();
		
		ShopSystem s = new ShopSystem(shopModel);
		s.init();
		
	}
}

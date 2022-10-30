public class Admin implements Role{
	
	private static Admin instance = new Admin();
	public static Admin getInstance() {return instance;}
	
	public Admin() {}

	@Override
	public String getRoleName() {
		// TODO Auto-generated method stub
		return "Admin";
	}
	



}

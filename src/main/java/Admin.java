package main;

public class Admin implements Role{
	private static Admin instance = new Admin();
	public static Admin getInstance() {return instance;}
	
	private Admin() {}

	@Override
	public String getRole(User user) {
		// TODO Auto-generated method stub
		return "Admin";
	}

}

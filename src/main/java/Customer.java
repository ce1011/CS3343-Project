public class Customer implements Role{
	
	private static Customer instance = new Customer();
	public static Customer getInstance() {return instance;}
	
	public Customer() {}

	@Override
	public String getRoleName() {
		// TODO Auto-generated method stub
		return "Customer";
	}


}

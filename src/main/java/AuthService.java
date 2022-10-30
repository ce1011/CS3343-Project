import java.util.*;

public class AuthService {
	
	private ArrayList<User> users = new ArrayList<User>();
	private static AuthService instance = AuthService.getInstance();
	
	public static AuthService getInstance() {
		if(instance == null) {
			instance = new AuthService();
		}
		return instance;
	}
	
	
	public User register(String username, String password, String roleType) throws ExistedUserFoundException{
		User user = null;
		if (searchUser(username)!=null) { //found existed user
			throw new ExistedUserFoundException(username);
		} else {
		
			Role r = null;
			
			if(roleType.equals("Admin")) {
				r = Admin.getInstance();
			} else if(roleType.equals("Customer")) {
				r = Customer.getInstance();
			}
			
			user = new User(username,password,r);
			users.add(user);
			
		}
		return user;
	}
	
	public User searchUser(String username) {
		User searching = null;
		for (User user: users) {
			if(user.getUsername().equals(username)) { //username exists
				searching = user;
			}
		}
		return searching;
	}
	
	public boolean login(String username, String password) throws WrongPasswordException, UserNotFoundException{
		boolean loginState = false;
		User user = searchUser(username);
			if(user!=null) { //username exists
				if(user.getPassword().equals(password)) {//username matches password
					loginState = true;
				} else {//wrong password
					throw new WrongPasswordException();
				}
			} else {// user not found
				throw new UserNotFoundException(username);
			}
		return loginState;
		
	}
	
}

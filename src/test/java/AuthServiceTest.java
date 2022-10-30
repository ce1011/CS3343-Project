package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Admin;
import main.AuthService;
import main.Customer;
import main.ExistedUserFoundException;
import main.User;
import main.UserNotFoundException;
import main.WrongPasswordException;

class AuthServiceTest {
	
	void setupUser() throws ExistedUserFoundException{
//		AuthService.getInstance().register("user", "user", "Customer");
		new User("user", "user", new Customer());
		new User("admin", "admin", new Admin());
//		AuthService.getInstance().register("admin", "admin", "Admin");
	}
	
	void test_getInstance() {
		AuthService authService = AuthService.getInstance();
		assertNotNull(authService);
	}

	void test_RegisterWithoutSameName() throws ExistedUserFoundException{
//		User user = AuthService.getInstance().register("user1", "user1", "Customer");
		AuthService authService = AuthService.getInstance();
		User user = new User("user1", "user1", new Customer());
		assertNotNull(user);
	}
	
	void test_RegisterWithSameName() throws ExistedUserFoundException{
		User user = AuthService.getInstance().register("user", "user", "Customer");
		assertEquals(null,user);
	}
	
	void test_LoginSuccess() throws ExistedUserFoundException, WrongPasswordException, UserNotFoundException{
		boolean result = AuthService.getInstance().login("user", "user");
		assertEquals(true,result);
	}
	
	void test_LoginUserNotFound() throws ExistedUserFoundException, WrongPasswordException, UserNotFoundException{
		
		assertThrows(UserNotFoundException.class, ()-> AuthService.getInstance().login("use", "use"));
	}
	
	void test_LoginWrongPassword() throws ExistedUserFoundException, WrongPasswordException, UserNotFoundException{
		
		assertThrows(WrongPasswordException.class, ()-> AuthService.getInstance().login("user", "user2"));
	}
	
	void test_SearchUser() throws ExistedUserFoundException{
		AuthService instance = AuthService.getInstance();
		User user = instance.register("user", "user", "Admin");
		User result = instance.searchUser("user");
		assertNotNull(result);
	}
	
	void test_SearchUserFail() throws ExistedUserFoundException{
		AuthService instance = AuthService.getInstance();
		User user = instance.register("user", "user", "Admin");
		User result = instance.searchUser("user");
		assertEquals(null,result);
	}

}

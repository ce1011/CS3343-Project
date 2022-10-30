package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import main.Admin;
import main.AuthService;
import main.Customer;
import main.ExistedUserFoundException;
import main.Role;
import main.User;

class UserTest {

	@Test
	void test_GetName() throws ExistedUserFoundException {
		User user = new User("user", "user", Customer.getInstance());
		String result = user.getUsername();
		assertEquals("user",result);
	}
	
	@Test
	void test_GetPassword() throws ExistedUserFoundException {
		User user = new User("user", "user", new Customer());
		String result = user.getPassword();
		assertEquals("user",result);
	}
	
	@Test
	void test_GetRole() throws ExistedUserFoundException {
		User user = new User("user", "user", new Customer());
		Role result = user.getRole();
		assertNotNull(result);
//		assertEquals(Customer.getInstance(),result);
	}
	
	@Test
	void test_SetUsername() throws ExistedUserFoundException {
		User user = new User("user", "user", new Customer());
		user.setUsername("new user");
		String result = user.getUsername();
		assertEquals("new user", result);
	}
	
	@Test
	void test_SetPassword() throws ExistedUserFoundException {
		User user = new User("user", "user", new Customer());
		user.setPassword("new password");
		String result = user.getPassword();
		assertEquals("new password", result);
	}
	
	@Test
	void test_SetRole() throws ExistedUserFoundException {
		User user = new User("new", "new", new Customer());
		user.setRole(Admin.getInstance());
		Role result = user.getRole();
		assertEquals(Admin.getInstance(), result);
	}

}

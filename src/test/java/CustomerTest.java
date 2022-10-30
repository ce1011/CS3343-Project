package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Admin;
import main.AuthService;
import main.Customer;
import main.ExistedUserFoundException;
import main.User;

class CustomerTest {

	@Test
	void test_GetRole() throws ExistedUserFoundException{
		
		User user = AuthService.getInstance().register("user", "user", "Customer");
		String result = user.getRole().getRoleName();
		assertEquals("Customer",result);
		
	}

}

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Admin;
import main.AuthService;
import main.ExistedUserFoundException;
import main.User;

class AdminTest {
	

	@Test
	void test_GetRole() throws ExistedUserFoundException{
		
		User user = AuthService.getInstance().register("admin", "admin", "Admin");
		String result = user.getRole().getRoleName();
		assertEquals("Admin",result);
	}

}



import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;


class AdminTest {
	

	@Test
	void test_GetRole() throws ExistedUserFoundException{
		
		User user = AuthService.getInstance().register("admin", "admin", "Admin");
		String result = user.getRole().getRoleName();
		assertEquals("Admin",result);
	}

}

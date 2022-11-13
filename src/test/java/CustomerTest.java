
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerTest {

	@Test
	void test_GetRole() throws ExistedUserFoundException{
		
		User user = AuthService.getInstance().register("user", "user", "Customer");
		String result = user.getRole().getRoleName();
		assertEquals("Customer",result);
		
	}

}

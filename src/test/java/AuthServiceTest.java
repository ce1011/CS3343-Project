import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AuthServiceTest {
	
	@AfterEach
	void resetAuthService() {
		AuthService.getInstance().resetAuthService();
	}
	
	@BeforeEach
	void setUp() throws ExistedUserFoundException{
		AuthService.getInstance().tempData();
	}
	
	@Test
	void test_getInstance() {
		AuthService authService = AuthService.getInstance();
		assertNotNull(authService);
	}

	@Test
	void test_RegisterWithoutSameName() throws ExistedUserFoundException{
		AuthService authService = AuthService.getInstance();
		User user = authService.register("user1","user1","Customer");
		assertEquals("user1",user.getUsername());
	}
	
	@Test
	void test_RegisterWithSameName() throws ExistedUserFoundException{
		assertThrows(ExistedUserFoundException.class, ()-> AuthService.getInstance().register("customer", "customer", "Customer"));
	}
	
	@Test
	void test_LoginSuccess() throws ExistedUserFoundException, WrongPasswordException, UserNotFoundException{
		boolean result = AuthService.getInstance().login("customer", "customer");
		assertEquals(true,result);
	}
	
	@Test
	void test_LoginUserNotFound() throws ExistedUserFoundException, WrongPasswordException, UserNotFoundException{
		
		assertThrows(UserNotFoundException.class, ()-> AuthService.getInstance().login("custome", "customer"));
	}
	
	@Test
	void test_LoginWrongPassword() throws ExistedUserFoundException, WrongPasswordException, UserNotFoundException{
		
		assertThrows(WrongPasswordException.class, ()-> AuthService.getInstance().login("customer", "custome"));
	}
	
	@Test
	void test_SearchUser() throws ExistedUserFoundException{
		AuthService instance = AuthService.getInstance();
		instance.register("user", "user", "Admin");
		User result = instance.searchUser("user");
		assertNotNull(result);
	}
	
	@Test
	void test_SearchUserFail() throws ExistedUserFoundException{
		AuthService instance = AuthService.getInstance();
		User user = instance.register("user", "user", "Admin");
		User result = instance.searchUser("use");
		assertEquals(null,result);
	}

}

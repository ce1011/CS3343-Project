

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
	void test_RegisterWithSameName() {
		Throwable exception = assertThrows(ExistedUserFoundException.class, ()-> AuthService.getInstance().register("customer", "customer", "Customer"));
		assertEquals("Username: customer already exists",exception.getMessage());
	}
	
	@Test
	void test_LoginSuccess() throws ExistedUserFoundException, WrongPasswordException, UserNotFoundException{
		User result = AuthService.getInstance().login("customer", "customer");
		assertEquals("Customer",result.getRole().getRoleName());
	}
	
	@Test
	void test_AdminRegister() throws ExistedUserFoundException, WrongPasswordException, UserNotFoundException{
		User result = AuthService.getInstance().register("newAdmin", "newAdmin", "Admin");
		assertEquals("Admin",result.getRole().getRoleName());
	}
	
	
	
	@Test
	void test_LoginUserNotFound() throws ExistedUserFoundException, WrongPasswordException, UserNotFoundException{
		
		assertEquals("Username: custome not found",assertThrows(UserNotFoundException.class, ()-> AuthService.getInstance().login("custome", "customer")).getMessage());
		
	}
	
	@Test
	void test_LoginWrongPassword() throws ExistedUserFoundException, WrongPasswordException, UserNotFoundException{
		
		assertEquals("The password is not valid",assertThrows(WrongPasswordException.class, ()-> AuthService.getInstance().login("customer", "custome")).getMessage());
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

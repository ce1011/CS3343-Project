

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class StoreTest {
	
	@BeforeEach
	void setUp(){
		User user = new User("customer","customer",new Customer());
		Store store = Store.getInstance();
		store.setCurrentUser(user);
	}

	@Test
	void test_getInstance() {
		Store store = Store.getInstance();
		assertNotNull(store);
	}
	
	@Test
	void test_getCurrentUser() {
		Store store = Store.getInstance();
		User result = store.getCurrentUser();
		assertEquals("customer",result.getUsername());
	}
	

}

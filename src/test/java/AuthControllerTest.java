import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthControllerTest {
	private AuthService as;
	
	
	@BeforeEach
	public void setup() throws ExistedUserFoundException{
		as = AuthService.getInstance();
		as.register("user", "user", "Customer");
		as.register("admin", "admin", "Admin");
		
	}
	
	@AfterEach
	public void reset() {
		as.resetAuthService();
	}
	

	@Test
	public void entryAuthViewTest() {
	    System.setIn(new ByteArrayInputStream("4".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		AuthController ac = new AuthController();
		ac.entry();
		assertTrue(outContent.toString().contains("Please enter your choice: "));
	}
	
	
	@Test
	public void exitAuthViewTest() {
	    System.setIn(new ByteArrayInputStream("3".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		AuthController ac = new AuthController();
		ac.entry();
		assertTrue(outContent.toString().contains("GoodBye"));
	}
	
	@Test
	public void entryAuthViewTest_InvalidChoice() {
	    System.setIn(new ByteArrayInputStream("-1\n4".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		AuthController ac = new AuthController();
		ac.entry();
		assertEquals(true,outContent.toString().contains("Invalid choice!"));
	}
	
	@Test
	public void entryAuthViewTest_LoginPage() throws ExistedUserFoundException {
		
		ByteArrayInputStream stream1 = new ByteArrayInputStream("1\nuser\nuser\n".getBytes());
		ByteArrayInputStream stream2 = new ByteArrayInputStream("4".getBytes());
		Vector<ByteArrayInputStream> vector = new Vector<ByteArrayInputStream>();
		vector.add(stream1);
		vector.add(stream2);
		Enumeration<ByteArrayInputStream> enumeration = vector.elements();
		SequenceInputStream sis = new SequenceInputStream(enumeration);
		System.setIn(sis);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.out.print(outContent);
		AuthController ac = new AuthController();
		ac.entry();
		assertEquals(true,outContent.toString().contains("Welcome to the customer home page"));
	}
	
	@Test
	public void adminLoginViewTest() {
		
		ByteArrayInputStream stream1 = new ByteArrayInputStream("1\nadmin\nadmin\n".getBytes());
		ByteArrayInputStream stream2 = new ByteArrayInputStream("6".getBytes());
		Vector<ByteArrayInputStream> vector = new Vector<ByteArrayInputStream>();
		vector.add(stream1);
		vector.add(stream2);
		Enumeration<ByteArrayInputStream> enumeration = vector.elements();
		SequenceInputStream sis = new SequenceInputStream(enumeration);
		System.setIn(sis);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.out.print(outContent);
		AuthController ac = new AuthController();
		ac.entry();
		assertEquals(true,outContent.toString().contains("Welcome to Admin page"));
	}
	
	
	@Test
	public void wrongPwdViewTest_LoginPage() {
		
		ByteArrayInputStream stream1 = new ByteArrayInputStream("1\nuser\nuser1\n".getBytes());
		ByteArrayInputStream stream2 = new ByteArrayInputStream("4".getBytes());
		Vector<ByteArrayInputStream> vector = new Vector<ByteArrayInputStream>();
		vector.add(stream1);
		vector.add(stream2);
		Enumeration<ByteArrayInputStream> enumeration = vector.elements();
		SequenceInputStream sis = new SequenceInputStream(enumeration);
		System.setIn(sis);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.out.print(outContent);
		AuthController ac = new AuthController();
		ac.entry();
		assertEquals(true,outContent.toString().contains("Wrong Password!"));
	}
	
	@Test
	public void userNotFoundViewTest_LoginPage(){
		
		ByteArrayInputStream stream1 = new ByteArrayInputStream("1\nhead\nhead\n".getBytes());
		ByteArrayInputStream stream2 = new ByteArrayInputStream("4".getBytes());
		Vector<ByteArrayInputStream> vector = new Vector<ByteArrayInputStream>();
		vector.add(stream1);
		vector.add(stream2);
		Enumeration<ByteArrayInputStream> enumeration = vector.elements();
		SequenceInputStream sis = new SequenceInputStream(enumeration);
		System.setIn(sis);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.out.print(outContent);
		AuthController ac = new AuthController();
		ac.entry();
		assertEquals(true,outContent.toString().contains("User Not Found!"));
	}
	
	
	@Test
	public void registerViewTest() throws ExistedUserFoundException {
		ByteArrayInputStream stream1 = new ByteArrayInputStream("2\nuser1\nuser1\n".getBytes());
		ByteArrayInputStream stream2 = new ByteArrayInputStream("user1\nuser1\n".getBytes());
		ByteArrayInputStream stream3 = new ByteArrayInputStream("4".getBytes());
		Vector<ByteArrayInputStream> vector = new Vector<ByteArrayInputStream>();
		vector.add(stream1);
		vector.add(stream2);
		vector.add(stream3);
		Enumeration<ByteArrayInputStream> enumeration = vector.elements();
		SequenceInputStream sis = new SequenceInputStream(enumeration);
		System.setIn(sis);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		AuthController ac = new AuthController();
		ac.entry();
		assertEquals(true,outContent.toString().contains("Register Success!"));
	}
	
	@Test
	public void registerExistingUserTest() throws ExistedUserFoundException {
		ByteArrayInputStream stream1 = new ByteArrayInputStream("2\ncustomer\ncustomer\n".getBytes());
		ByteArrayInputStream stream2 = new ByteArrayInputStream("4".getBytes());
		Vector<ByteArrayInputStream> vector = new Vector<ByteArrayInputStream>();
		vector.add(stream1);
		vector.add(stream2);
		Enumeration<ByteArrayInputStream> enumeration = vector.elements();
		SequenceInputStream sis = new SequenceInputStream(enumeration);
		System.setIn(sis);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		AuthController ac = new AuthController();
		ac.entry();
		assertEquals(true,outContent.toString().contains("User Existed!"));
	}

}

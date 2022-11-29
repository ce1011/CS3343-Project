import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TestName;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DeliveryViewTest {
    @Rule public TestName name = new TestName();

	
	@Test
	public void test() {
		InputStream stdin = System.in;
	    System.setIn(new ByteArrayInputStream("3\n4\n".getBytes()));
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(byteArrayOutputStream);
	    DeliveryView dv = new DeliveryView(new DeliveryController());
	  	dv.entryView();
	    System.setIn(stdin);
	    assertEquals("output", "7");
	}


}

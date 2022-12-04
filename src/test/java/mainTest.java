import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

import org.junit.jupiter.api.Test;

class mainTest {
	
	@Test
	void adminCreateProductTest() {
		//admin
		main m = new main();
		//create coupon
		ByteArrayInputStream stream5 = new ByteArrayInputStream("1\nadmin\nadmin\n".getBytes());
		ByteArrayInputStream stream6 = new ByteArrayInputStream("2\n".getBytes()); //product management
		ByteArrayInputStream stream7 = new ByteArrayInputStream("nike\n1\n100\n1\n".getBytes());
		ByteArrayInputStream stream8 = new ByteArrayInputStream("+\n".getBytes());
		ByteArrayInputStream stream9 = new ByteArrayInputStream("nike\n2.0\nnike\n5\n0.8\n".getBytes());
		ByteArrayInputStream stream10 = new ByteArrayInputStream("b\n".getBytes());
		ByteArrayInputStream stream11 = new ByteArrayInputStream("6\n".getBytes());
		
		Vector<ByteArrayInputStream> vector = new Vector<ByteArrayInputStream>();
		vector.add(stream5);
		vector.add(stream6);
		vector.add(stream7);
		vector.add(stream8);
		vector.add(stream9);
		vector.add(stream10);
		vector.add(stream11);
		Enumeration<ByteArrayInputStream> enumeration = vector.elements();
		SequenceInputStream sis = new SequenceInputStream(enumeration);
		System.setIn(sis);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		main.main(null);
		assertTrue(outContent.toString().contains("Product added successfully!"));
		
	}
	

	
	@Test
	void customerBuyFlowTest() throws ExistingProductWithSameNameFoundException {
		//admin
		main m = new main();
		ProductController pc = new ProductController();
		pc.addProduct("product", 2.0, "product", 2, 0.8);
		//create coupon
		ByteArrayInputStream stream1 = new ByteArrayInputStream("1\ncustomer\ncustomer\n".getBytes());
		ByteArrayInputStream stream2 = new ByteArrayInputStream("2\n".getBytes()); //coupon management
		ByteArrayInputStream stream3 = new ByteArrayInputStream("2\nproduct\n1\n6\n".getBytes());
		ByteArrayInputStream stream4 = new ByteArrayInputStream("1\n".getBytes());
		ByteArrayInputStream stream5 = new ByteArrayInputStream("N/A\nhk\nHong Kong\n".getBytes());
		
		Vector<ByteArrayInputStream> vector = new Vector<ByteArrayInputStream>();
		vector.add(stream1);
		vector.add(stream2);
		vector.add(stream3);
		vector.add(stream4);
		vector.add(stream5);
		Enumeration<ByteArrayInputStream> enumeration = vector.elements();
		SequenceInputStream sis = new SequenceInputStream(enumeration);
		System.setIn(sis);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		main.main(null);
		assertTrue(outContent.toString().contains("Place order successfully!"));
		
	}
	


}

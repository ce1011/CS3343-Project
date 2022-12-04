import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private static Logger log = Logger.getLogger(ProductControllerTest.class.toString());


    @BeforeAll
    static void setupTest() throws ExistingProductWithSameNameFoundException {
        ProductService.getInstance().createProduct(new Product("productA", 12.9, "description", 10, new ProductState_Launch(), 1.2));
        ProductService.getInstance().createProduct(new Product("productB", 13.9, "description", 10, new ProductState_Launch(), 1.2));
        ProductService.getInstance().createProduct(new Product("productC", 14.9, "description", 10, new ProductState_Launch(), 1.2));
        ProductService.getInstance().createProduct( new Product("productDelete", 14.9, "description", 10, new ProductState_Deleted(), 1.2));
        ProductService.getInstance().createProduct(new Product("productDiscontinued", 14.9, "description", 10, new ProductState_Discontinued(), 1.2));
    }

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void displayAdminProductListWithBackAsNextStep() {
        System.setIn(new ByteArrayInputStream("b\nb\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        productController.displayAdminProductList("product", 0, 100, new ProductSortByNameDesc());
        assertEquals("""
                 No.|           Name|   Price|                             Description|    Status
                   1|productDiscontinued|    14.9|                             description|Discontinued
                   2|       productC|    14.9|                             description|    Launch
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));

    }

    @Test
    void displayCustomerProductList() {
        System.setIn(new ByteArrayInputStream("b".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        productController.displayCustomerProductList("product", 0, 100, new ProductSortByNameDesc());
        String expect = "                 No.|           Name|   Price|                             Description|    Status\r\n"
        		+ "                   1|productDiscontinued|    14.9|                             description|Discontinued\r\n"
        		+ "                   2|       productC|    14.9|                             description|    Launch\r\n"
        		+ "                (No) Add To Cart\r\n"
        		+ "                (b) Back\r\n"
        		+ "                Please enter your choice:";
        assertEquals(expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void displayAdminProductListWithAddProductAsNextStep() {
        System.setIn(new ByteArrayInputStream("+\ntest\n12.9\ndescription\n10\n2.4\nb\n".getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.displayAdminProductList("test", 0, 100, new ProductSortByNameDesc());
        }catch (Exception e){

        }

        assertEquals("""
                 No.|           Name|   Price|                             Description|    Status
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                Please enter the product name:\s
                Please enter the product price:\s
                Please enter the product description:\s
                Please enter the product in stock quantity:\s
                Please enter the product weight:\s
                Product added successfully!
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|    12.9|                             description|    Launch
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }


    @Test
    void addProduct() throws ExistingProductWithSameNameFoundException {
        System.setIn(new ByteArrayInputStream("b".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        productController.addProduct("test addProduct()", 12.9, "description", 10, 2.4);
        assertEquals("", outContent.toString());
    }

    @Test
    void addProductWithExceptionThrown() throws ExistingProductWithSameNameFoundException {

        assertThrows(ExistingProductWithSameNameFoundException.class, () -> {
            ProductController productController = new ProductController();
            productController.addProduct("test", 12.9, "description", 10, 2.4);
        });
    }

    @Test
    void updateProduct() throws ProductNotFoundException, ProductIsDeletedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        productController.updateProduct(1, new Product("test updateProduct()", 12.9, "description", 10, new ProductState_Launch(), 2.4));
        assertEquals("", outContent.toString());
    }

    @Test
    void addProductView() {
        System.setIn(new ByteArrayInputStream("test\n1.2\ndescription\n10\n1.2\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        productController.addProductView();


        assertEquals("""
                Please enter the product name:\s
                Please enter the product price:\s
                Please enter the product description:\s
                Please enter the product in stock quantity:\s
                Please enter the product weight:\s
                Product with same name already exists!
                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void filterViewWithSortSelect1() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n1\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.filterView();
        }catch (Exception e){

        }

        assertEquals("""
                Enter product name:\s
                Enter min price:\s
                Enter max price:\s
                Enter sort type:\s
                1. Sort by name in ascending
                2. Sort by name in descending
                3. Sort by price in ascending
                4. Sort by price in descending
                5. Sort by create date in ascending
                6. Sort by create date in descending
                 No.|           Name|   Price|                             Description|    Status
                   2|       productC|    14.9|                             description|    Launch
                   3|productDiscontinued|    14.9|                             description|Discontinued
                   4|           test|     1.0|                                       1|Discontinued
                   5|           test|     1.0|                                       1|    Launch
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));

    }

    @Test
    void filterViewWithSortSelect2() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n2\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.filterView();
        }catch (Exception e){

        }

        assertEquals("""
                Enter product name:\s
                Enter min price:\s
                Enter max price:\s
                Enter sort type:\s
                1. Sort by name in ascending
                2. Sort by name in descending
                3. Sort by price in ascending
                4. Sort by price in descending
                5. Sort by create date in ascending
                6. Sort by create date in descending
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|Discontinued
                   2|           test|     1.0|                                       1|    Launch
                   4|productDiscontinued|    14.9|                             description|Discontinued
                   5|       productC|    14.9|                             description|    Launch
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));

    }

    @Test
    void filterViewWithSortSelect3() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n3\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.filterView();
        }catch (Exception e){

        }

        assertEquals("""
                Enter product name:\s
                Enter min price:\s
                Enter max price:\s
                Enter sort type:\s
                1. Sort by name in ascending
                2. Sort by name in descending
                3. Sort by price in ascending
                4. Sort by price in descending
                5. Sort by create date in ascending
                6. Sort by create date in descending
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|Discontinued
                   2|           test|     1.0|                                       1|    Launch
                   5|       productC|    14.9|                             description|    Launch
                   6|productDiscontinued|    14.9|                             description|Discontinued
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));

    }

    @Test
    void filterViewWithSortSelect4() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n4\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.filterView();
        }catch (Exception e){

        }

        assertEquals("""
                Enter product name:\s
                Enter min price:\s
                Enter max price:\s
                Enter sort type:\s
                1. Sort by name in ascending
                2. Sort by name in descending
                3. Sort by price in ascending
                4. Sort by price in descending
                5. Sort by create date in ascending
                6. Sort by create date in descending
                 No.|           Name|   Price|                             Description|    Status
                   1|       productC|    14.9|                             description|    Launch
                   2|productDiscontinued|    14.9|                             description|Discontinued
                   3|           test|     1.0|                                       1|Discontinued
                   4|           test|     1.0|                                       1|    Launch
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));

    }

    @Test
    void filterViewWithSortSelect5() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n5\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.filterView();
        }catch (Exception e){

        }

        assertEquals("""
                Enter product name:\s
                Enter min price:\s
                Enter max price:\s
                Enter sort type:\s
                1. Sort by name in ascending
                2. Sort by name in descending
                3. Sort by price in ascending
                4. Sort by price in descending
                5. Sort by create date in ascending
                6. Sort by create date in descending
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|Discontinued
                   2|           test|     1.0|                                       1|    Launch
                   3|       productC|    14.9|                             description|    Launch
                   5|productDiscontinued|    14.9|                             description|Discontinued
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));


    }

    @Test
    void filterViewWithSortSelect6() {
System.setIn(new ByteArrayInputStream("\n0\n999\n6\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.filterView();
        }catch (Exception e){

        }
        System.err.print(outContent.toString());
        String expect = "Enter product name: \r\n"
        		+ "Enter min price: \r\n"
        		+ "Enter max price: \r\n"
        		+ "Enter sort type: \r\n"
        		+ "1. Sort by name in ascending\r\n"
        		+ "2. Sort by name in descending\r\n"
        		+ "3. Sort by price in ascending\r\n"
        		+ "4. Sort by price in descending\r\n"
        		+ "5. Sort by create date in ascending\r\n"
        		+ "6. Sort by create date in descending\r\n"
        		+ " No.|           Name|   Price|                             Description|    Status\r\n"
        		+ "   2|productDiscontinued|    14.9|                             description|Discontinued\r\n"
        		+ "   4|           test|     1.0|                                       1|Discontinued\r\n"
        		+ "   5|           test|     1.0|                                       1|    Launch\r\n"
        		+ "   6|       productC|    14.9|                             description|    Launch\r\n"
        		+ "(No) Edit Product\r\n"
        		+ "(+) Add Product\r\n"
        		+ "(b) Back\r\n"
        		+ "Please enter your choice: \r\n"
        		+ "";
        assertTrue(expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "").equals(outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "")));

    }

    @Test
    void filterViewWithSortSelect7() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n7\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.filterView();
        }catch (Exception e){

        }

        assertEquals("""
                Enter product name:\s
                Enter min price:\s
                Enter max price:\s
                Enter sort type:\s
                1. Sort by name in ascending
                2. Sort by name in descending
                3. Sort by price in ascending
                4. Sort by price in descending
                5. Sort by create date in ascending
                6. Sort by create date in descending
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|Discontinued
                   2|           test|     1.0|                                       1|    Launch
                   3|       productC|    14.9|                             description|    Launch
                   5|productDiscontinued|    14.9|                             description|Discontinued
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));

    }

    @Test
    void filterViewWithSortSelect8() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n8\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.filterView();
        }catch (Exception e){

        }

        assertEquals("""
                Enter product name:\s
                Enter min price:\s
                Enter max price:\s
                Enter sort type:\s
                1. Sort by name in ascending
                2. Sort by name in descending
                3. Sort by price in ascending
                4. Sort by price in descending
                5. Sort by create date in ascending
                6. Sort by create date in descending
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|Discontinued
                   2|           test|     1.0|                                       1|    Launch
                   3|       productC|    14.9|                             description|    Launch
                   5|productDiscontinued|    14.9|                             description|Discontinued
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));

    }



    @Test
    void customerFilterViewWithSortSelect1() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n1\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.customerFilterView();
        }catch (Exception e){

        }

        assertEquals("""
                Enter product name:\s
                Enter min price:\s
                Enter max price:\s
                Enter sort type:\s
                1. Sort by name in ascending
                2. Sort by name in descending
                3. Sort by price in ascending
                4. Sort by price in descending
                5. Sort by create date in ascending
                6. Sort by create date in descending
                 No.|           Name|   Price|                             Description|    Status
                   2|       productC|    14.9|                             description|    Launch
                   3|productDiscontinued|    14.9|                             description|Discontinued
                   4|           test|     1.0|                                       1|Discontinued
                   5|           test|     1.0|                                       1|    Launch
                (No) Add To Cart
                (b) Back
                Please enter your choice:\s
                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void customerFilterViewWithSortSelect2() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n2\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.customerFilterView();
        }catch (Exception e){

        }

        assertEquals("""
                Enter product name:\s
                Enter min price:\s
                Enter max price:\s
                Enter sort type:\s
                1. Sort by name in ascending
                2. Sort by name in descending
                3. Sort by price in ascending
                4. Sort by price in descending
                5. Sort by create date in ascending
                6. Sort by create date in descending
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|Discontinued
                   2|           test|     1.0|                                       1|    Launch
                   4|productDiscontinued|    14.9|                             description|Discontinued
                   5|       productC|    14.9|                             description|    Launch
                (No) Add To Cart
                (b) Back
                Please enter your choice:\s
                                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void customerFilterViewWithSortSelect3() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n3\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.customerFilterView();
        }catch (Exception e){

        }

        assertEquals("""
                Enter product name:\s
                Enter min price:\s
                Enter max price:\s
                Enter sort type:\s
                1. Sort by name in ascending
                2. Sort by name in descending
                3. Sort by price in ascending
                4. Sort by price in descending
                5. Sort by create date in ascending
                6. Sort by create date in descending
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|Discontinued
                   2|           test|     1.0|                                       1|    Launch
                   5|       productC|    14.9|                             description|    Launch
                   6|productDiscontinued|    14.9|                             description|Discontinued
                (No) Add To Cart
                (b) Back
                Please enter your choice:\s
                                                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void customerFilterViewWithSortSelect4() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n4\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.customerFilterView();
        }catch (Exception e){

        }

        assertEquals("""
                Enter product name:\s
                Enter min price:\s
                Enter max price:\s
                Enter sort type:\s
                1. Sort by name in ascending
                2. Sort by name in descending
                3. Sort by price in ascending
                4. Sort by price in descending
                5. Sort by create date in ascending
                6. Sort by create date in descending
                 No.|           Name|   Price|                             Description|    Status
                   1|       productC|    14.9|                             description|    Launch
                   2|productDiscontinued|    14.9|                             description|Discontinued
                   3|           test|     1.0|                                       1|Discontinued
                   4|           test|     1.0|                                       1|    Launch
                (No) Add To Cart
                (b) Back
                Please enter your choice:\s
                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void customerFilterViewWithSortSelect5() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n5\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.customerFilterView();
        }catch (Exception e){

        }

        assertEquals("""
                Enter product name:\s
                Enter min price:\s
                Enter max price:\s
                Enter sort type:\s
                1. Sort by name in ascending
                2. Sort by name in descending
                3. Sort by price in ascending
                4. Sort by price in descending
                5. Sort by create date in ascending
                6. Sort by create date in descending
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|Discontinued
                   2|           test|     1.0|                                       1|    Launch
                   3|       productC|    14.9|                             description|    Launch
                   5|productDiscontinued|    14.9|                             description|Discontinued
                (No) Add To Cart
                (b) Back
                Please enter your choice:\s
                                                                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void customerFilterViewWithSortSelect6() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n6\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.customerFilterView();
        }catch (Exception e){

        }
        String expect = "Enter product name: \r\n"
        		+ "Enter min price: \r\n"
        		+ "Enter max price: \r\n"
        		+ "Enter sort type: \r\n"
        		+ "1. Sort by name in ascending\r\n"
        		+ "2. Sort by name in descending\r\n"
        		+ "3. Sort by price in ascending\r\n"
        		+ "4. Sort by price in descending\r\n"
        		+ "5. Sort by create date in ascending\r\n"
        		+ "6. Sort by create date in descending\r\n"
        		+ " No.|           Name|   Price|                             Description|    Status\r\n"
        		+ "   2|productDiscontinued|    14.9|                             description|Discontinued\r\n"
        		+ "   4|           test|     1.0|                                       1|Discontinued\r\n"
        		+ "   5|           test|     1.0|                                       1|    Launch\r\n"
        		+ "   6|       productC|    14.9|                             description|    Launch\r\n"
        		+ "(No) Add To Cart\r\n"
        		+ "(b) Back\r\n"
        		+ "Please enter your choice:";
        assertTrue(outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "").equals(expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "")));
    }

    @Test
    void customerFilterViewWithSortSelect7() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n7\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.customerFilterView();
        }catch (Exception e){

        }

        assertEquals("""
                Enter product name:\s
                Enter min price:\s
                Enter max price:\s
                Enter sort type:\s
                1. Sort by name in ascending
                2. Sort by name in descending
                3. Sort by price in ascending
                4. Sort by price in descending
                5. Sort by create date in ascending
                6. Sort by create date in descending
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|Discontinued
                   2|           test|     1.0|                                       1|    Launch
                   3|       productC|    14.9|                             description|    Launch
                   5|productDiscontinued|    14.9|                             description|Discontinued
                (No) Add To Cart
                (b) Back
                Please enter your choice:\s
                                                                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void customerFilterViewWithSortSelect8() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n8\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.customerFilterView();
        }catch (Exception e){

        }

        assertEquals("""
                Enter product name:\s
                Enter min price:\s
                Enter max price:\s
                Enter sort type:\s
                1. Sort by name in ascending
                2. Sort by name in descending
                3. Sort by price in ascending
                4. Sort by price in descending
                5. Sort by create date in ascending
                6. Sort by create date in descending
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|Discontinued
                   2|           test|     1.0|                                       1|    Launch
                   3|       productC|    14.9|                             description|    Launch
                   5|productDiscontinued|    14.9|                             description|Discontinued
                (No) Add To Cart
                (b) Back
                Please enter your choice:\s
                                                                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void displayAdminProductListWithUpdateProductAsLaunchedAsNextStep() {
        System.setIn(new ByteArrayInputStream("1\ntest\n1\n1\n2\n1\n1\n".getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.displayAdminProductList("test", 0, 100, new ProductSortByNameDesc());
        }catch (Exception e){

        }

        assertEquals("""
                 No.|           Name|   Price|                             Description|    Status
                   1|test updateProduct()|    12.9|                             description|    Launch
                   3|           test|    12.9|                             description|    Launch
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                Update Product
                Please enter updated product name:\s
                Please enter updated product price:\s
                Please enter updated product description:\s
                Please enter updated product weight:\s
                Please enter updated product quantity:\s
                Please enter updated product status:\s
                1. Launched
                2. Discontinued
                3. Delete
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|    Launch
                   2|           test|     1.0|                                       1|    Launch
                   4|           test|    12.9|                             description|    Launch
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void displayAdminProductListWithUpdateProductAsDiscontinuedAsNextStep() {
        System.setIn(new ByteArrayInputStream("1\ntest\n1\n1\n2\n1\n2\n".getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.displayAdminProductList("test", 0, 100, new ProductSortByNameDesc());
        }catch (Exception e){

        }

        assertEquals("""
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|    Launch
                   2|           test|     1.0|                                       1|    Launch
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                Update Product
                Please enter updated product name:\s
                Please enter updated product price:\s
                Please enter updated product description:\s
                Please enter updated product weight:\s
                Please enter updated product quantity:\s
                Please enter updated product status:\s
                1. Launched
                2. Discontinued
                3. Delete
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|Discontinued
                   2|           test|     1.0|                                       1|    Launch
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void displayAdminProductListWithUpdateProductAsDeletedAsNextStep() {
        System.setIn(new ByteArrayInputStream("3\ntest\n1\n1\n2\n1\n3\n".getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.displayAdminProductList("test", 0, 100, new ProductSortByNameDesc());
        }catch (Exception e){

        }

        assertEquals("""
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|    Launch
                   2|           test|     1.0|                                       1|    Launch
                   3|           test|    12.9|                             description|    Launch
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                Update Product
                Please enter updated product name:\s
                Please enter updated product price:\s
                Please enter updated product description:\s
                Please enter updated product weight:\s
                Please enter updated product quantity:\s
                Please enter updated product status:\s
                1. Launched
                2. Discontinued
                3. Delete
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|    Launch
                   2|           test|     1.0|                                       1|    Launch
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                                                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void displayAdminProductListWithUpdateProductAsStatusInvalidChoiceAsNextStep() {
        System.setIn(new ByteArrayInputStream("3\ntest\n1\n1\n2\n1\n4\n".getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.displayAdminProductList("test", 0, 100, new ProductSortByNameDesc());
        }catch (Exception e){

        }

        assertEquals("""
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|    Launch
                   2|           test|     1.0|                                       1|    Launch
                   4|           test|    12.9|                             description|    Launch
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                Update Product
                Please enter updated product name:\s
                Please enter updated product price:\s
                Please enter updated product description:\s
                Please enter updated product weight:\s
                Please enter updated product quantity:\s
                Please enter updated product status:\s
                1. Launched
                2. Discontinued
                3. Delete
                Invalid choice!
                Update Product
                Please enter updated product name:\s
                Please enter updated product price:\s
                                                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void displayAdminProductListWithUpdateProductInvalidChoiceAsNextStep() {
        System.setIn(new ByteArrayInputStream("9999\n".getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.displayAdminProductList("test", 0, 100, new ProductSortByNameDesc());
        }catch (Exception e){

        }

        assertEquals("""
                 No.|           Name|   Price|                             Description|    Status
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                Invalid choice!
                 No.|           Name|   Price|                             Description|    Status
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }


    @Test
    void displayAdminProductListWithUpdateDeletedProductAsNextStep() {
        System.setIn(new ByteArrayInputStream("2\ntest\n1\n1\n2\n1\n2\n".getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.displayAdminProductList("product", 0, 100, new ProductSortByNameDesc());
        }catch (Exception e){

        }

        assertEquals("""
                 No.|           Name|   Price|                             Description|    Status
                   1|productDiscontinued|    14.9|                             description|Discontinued
                   3|       productC|    14.9|                             description|    Launch
                   4|       productA|    12.9|                             description|    Launch
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                Update Product
                Please enter updated product name:\s
                Please enter updated product price:\s
                Please enter updated product description:\s
                Please enter updated product weight:\s
                Please enter updated product quantity:\s
                Please enter updated product status:\s
                1. Launched
                2. Discontinued
                3. Delete
                Product is deleted
                                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void displayAdminProductListWithInvalidChoiceAsNextStep() {
        System.setIn(new ByteArrayInputStream("99999\ntest\n1\n1\n2\n1\n2\n".getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.displayAdminProductList("product", 0, 100, new ProductSortByNameDesc());
        }catch (Exception e){

        }

        assertEquals("""
                 No.|           Name|   Price|                             Description|    Status
                   1|productDiscontinued|    14.9|                             description|Discontinued
                   2|       productC|    14.9|                             description|    Launch
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                Invalid choice!
                 No.|           Name|   Price|                             Description|    Status
                   1|productDiscontinued|    14.9|                             description|Discontinued
                   2|       productC|    14.9|                             description|    Launch
                (No) Edit Product
                (+) Add Product
                (b) Back
                Please enter your choice:\s
                                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void displayCustomerProductListWithAddProductToCartAsNextStep() {
        System.setIn(new ByteArrayInputStream("1\n1\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.displayCustomerProductList("", 0, 100, new ProductSortByNameDesc());

        }catch (Exception e){

        }
        assertEquals("""
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|    Launch
                   2|           test|     1.0|                                       1|    Launch
                   3|           test|    12.9|                             description|    Launch
                   4|productDiscontinued|    14.9|                             description|Discontinued
                   5|       productC|    14.9|                             description|    Launch
                (No) Add To Cart
                (b) Back
                Please enter your choice:\s
                How many do you want to buy?
                ------------------------------------------------
                Result:
                Success!
                ------------------------------------------------
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|    Launch
                   2|           test|     1.0|                                       1|    Launch
                   3|           test|    12.9|                             description|    Launch
                   4|productDiscontinued|    14.9|                             description|Discontinued
                   5|       productC|    14.9|                             description|    Launch
                (No) Add To Cart
                (b) Back
                Please enter your choice:\s
                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void displayCustomerProductListWithInvalidChoicetAsNextStep() {
        System.setIn(new ByteArrayInputStream("999999\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.displayCustomerProductList("", 0, 100, new ProductSortByNameDesc());

        }catch (Exception e){

        }
        assertEquals("""
                 No.|           Name|   Price|                             Description|    Status
                   1|test updateProduct()|    12.9|                             description|    Launch
                   2|           test|    12.9|                             description|    Launch
                   3|productDiscontinued|    14.9|                             description|Discontinued
                   5|       productC|    14.9|                             description|    Launch
                   6|       productA|    12.9|                             description|    Launch
                (No) Add To Cart
                (b) Back
                Please enter your choice:\s
                Invalid choice!
                 No.|           Name|   Price|                             Description|    Status
                   1|test updateProduct()|    12.9|                             description|    Launch
                   2|           test|    12.9|                             description|    Launch
                   3|productDiscontinued|    14.9|                             description|Discontinued
                   5|       productC|    14.9|                             description|    Launch
                   6|       productA|    12.9|                             description|    Launch
                (No) Add To Cart
                (b) Back
                Please enter your choice:\s
                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void displayCustomerProductListWithAddProductToCartFailAsNextStep() {
        System.setIn(new ByteArrayInputStream("1\n9999\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.displayCustomerProductList("", 0, 100, new ProductSortByNameDesc());

        }catch (Exception e){

        }
        assertEquals("""
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|Discontinued
                   2|           test|     1.0|                                       1|    Launch
                   4|productDiscontinued|    14.9|                             description|Discontinued
                   5|       productC|    14.9|                             description|    Launch
                (No) Add To Cart
                (b) Back
                Please enter your choice:\s
                How many do you want to buy?
                ------------------------------------------------
                Result:
                This product is out of stock!
                ------------------------------------------------
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|Discontinued
                   2|           test|     1.0|                                       1|    Launch
                   4|productDiscontinued|    14.9|                             description|Discontinued
                   5|       productC|    14.9|                             description|    Launch
                (No) Add To Cart
                (b) Back
                Please enter your choice:\s
                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }


    @Test
    void displayCustomerProductListWithInvalidChoiceAsNextStep() {
        System.setIn(new ByteArrayInputStream("3\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ProductController productController = new ProductController();
        try{
            productController.displayCustomerProductList("", 0, 100, new ProductSortByNameDesc());

        }catch (Exception e){

        }
        assertEquals("""
                 No.|           Name|   Price|                             Description|    Status
                   1|           test|     1.0|                                       1|    Launch
                   2|           test|     1.0|                                       1|    Launch
                   4|productDiscontinued|    14.9|                             description|Discontinued
                   5|       productC|    14.9|                             description|    Launch
                (No) Add To Cart
                (b) Back
                Please enter your choice:\s
                How many do you want to buy?
                                                                                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }
}
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeControllerTest {

    @Test
    void showCustomerHome() {
        System.setIn(new ByteArrayInputStream("4\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        HomeController homeController = new HomeController();
        homeController.showCustomerHome();
        assertEquals("""
                Welcome to the customer home page
                1. View products
                2. View cart
                3. View orders
                4. Logout
                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void showAdminHome() {
        //.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "")
        System.setIn(new ByteArrayInputStream("6\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        HomeController homeController = new HomeController();
        homeController.showAdminHome();
        assertEquals("""
                Welcome to Admin page
                1. Coupon Management
                2. Product Management
                3. Order Management
                4. Delivery Management
                5. Report
                6. Exit
                Please enter your choice:\s""".replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));

    }

    @Test
    void showCouponView() {
        System.setIn(new ByteArrayInputStream("5\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        HomeController homeController = new HomeController();
        homeController.showCouponView();
       assertEquals("""
               Coupon Management Page
               1. View Coupon List
               2. Create a Coupon
               3. Edit a Coupon
               4. Delete a Coupon
               5. Return to main menu
               Please input the number in front for your action:
               """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void showProductView() {
        System.setIn(new ByteArrayInputStream("\n0\n999\n1\nb\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        HomeController homeController = new HomeController();
        homeController.showProductView();
        assertEquals("Enterproductname:Enterminprice:Entermaxprice:Entersorttype:1.Sortbynameinascending2.Sortbynameindescending3.Sortbypriceinascending4.Sortbypriceindescending5.Sortbycreatedateinascending6.SortbycreatedateindescendingNo.|Name|Price|Description|Status1|nike|2.0|nike|Launch2|product|2.0|product|Launch(No)EditProduct(+)AddProduct(b)BackPleaseenteryourchoice:", outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void showOrderView() {
        System.setIn(new ByteArrayInputStream("4\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        HomeController homeController = new HomeController();
        homeController.showOrderView();
        assertEquals("""
                Welcome to Admin Order Management panel
                1. View order list
                2. Update order status
                3. Delete order
                4. Return
                Please select function:\s
                                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void showDeliveryView() {
        System.setIn(new ByteArrayInputStream("8\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        HomeController homeController = new HomeController();
        homeController.showDeliveryView();
        assertEquals("""
                Delivery Management Page
                1. Modify Information of Delivery Items
                2. Display Delivery Information by Delivery ID
                3. Edit the price of Delivery
                4. Delete Delivery Item
                5. View All Delivery Items
                6. Add Available Delivery Zone
                7. Remove Existing Delivery Zone
                8. Return
                Please enter your choice:\s
                """.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""), outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }

    @Test
    void showReportView() {
        System.setIn(new ByteArrayInputStream("5\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        HomeController homeController = new HomeController();
        homeController.showReportView();
        assertEquals("Date|Sum2022-12-05|111.000000", outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }
}
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {
    @Test
    void getTransactionID() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        assertEquals("100005", order.getTransactionID());
    }

    @Test
    void setTransactionID() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        order.setTransactionID("123");
        assertEquals("123", order.getTransactionID());
    }

    @Test
    void setUser() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        User user = new User("a","a", new Customer());
        order.setUser(user);
        assertEquals(user, order.getUser());
    }

    @Test
    void getUser() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        User user = new User("a","a", new Customer());
        order.setUser(user);
        assertEquals(user, order.getUser());
    }

    @Test
    void getProductList() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        assertEquals(productList, order.getProductList());
    }

    @Test
    void setProductList() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        ArrayList<CartItem> productList2 = new ArrayList<CartItem>();
        order.setProductList(productList2);
        assertEquals(productList2, order.getProductList());
    }

    @Test
    void getCouponUsed() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        Coupon coupon = new Coupon("a", new Date(), new Date(), "Available", 0, 0,0,"test");
        order.setCouponUsed(coupon);
        assertEquals(coupon, order.getCouponUsed());
    }

    @Test
    void setCouponUsed() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        Coupon coupon = new Coupon("a", new Date(), new Date(), "Available", 0, 0,0,"test");
        order.setCouponUsed(coupon);
        assertEquals(coupon, order.getCouponUsed());
    }

    @Test
    void getDeliveryDetails() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        Delivery delivery = new Delivery("a", "a", "a", "a", 30);
        order.setDeliveryDetails(delivery);
        assertEquals(delivery, order.getDeliveryDetails());
    }

    @Test
    void setDeliveryDetails() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        Delivery delivery = new Delivery("a", "a", "a", "a", 30);
        order.setDeliveryDetails(delivery);
        assertEquals(delivery, order.getDeliveryDetails());
    }

    @Test
    void getTotalPrice() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        order.setTotalPrice(0);
        assertEquals("0.0", order.getTotalPrice());
    }

    @Test
    void setTotalPrice() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        order.setTotalPrice(0);
        assertEquals("0.0", order.getTotalPrice());
    }

    @Test
    void getStatus() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        order.setStatus(new OrderState_Pending());
        assertEquals("Pending", order.getStatus().toString());
    }

    @Test
    void setStatus() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        order.setStatus(new OrderState_Pending());
        assertEquals("Pending", order.getStatus().toString());
    }

    @Test
    void getRemarks() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        order.setRemarks("test");
        assertEquals("test", order.getRemarks());
    }

    @Test
    void getTotalPriceDouble() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        order.setTotalPrice(0);
        assertEquals(0, order.getTotalPriceDouble());
    }

    @Test
    void setRemarks() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        order.setRemarks("test");
        assertEquals("test", order.getRemarks());
    }

    @Test
    void getOrderDate() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        order.setOrderDate("2020-12-12");
        assertEquals("2020-12-12", order.getOrderDate());
    }

    @Test
    void getOrderDateYYYYMMDD() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        order.setOrderDate("2020-12-12");
        assertEquals("2020-12-12", order.getOrderDateYYYYMMDD());
    }

    @Test
    void setOrderDate() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        order.setOrderDate("2020-12-12");
        assertEquals("2020-12-12", order.getOrderDate());
    }

    @Test
    void retrieveProductName() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();
        Order order = new Order(productList, null, 0, null);
        assertEquals("", order.retrieveProductName());
    }

    @Test
    void retrieveTwoProductName() {
        ArrayList<CartItem> productList = new ArrayList<CartItem>();

        productList.add(new CartItem(new Product("test", 12,"12",12,new ProductState_Launch(), 12), 1));
        productList.add(new CartItem(new Product("test1", 12,"12",12,new ProductState_Launch(), 12), 1));

        Order order = new Order(productList, null, 0, null);
        assertEquals(" test test1", order.retrieveProductName());
    }
}

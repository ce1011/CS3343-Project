import java.util.ArrayList;
import java.util.Date;

public class Coupon {
    private String couponName;
    private Date startDate;
    private Date endDate;
    private boolean useOnce;
    private int useQuota;
    private double minimumUsagePrice;
    private double discountValue; //can update
    private String type; // fixedPrice, percentage
    private boolean status; //can update

    public Coupon(String couponName, Date startDate, Date endDate, boolean useOnce, int useQuota, double discountValue,double minimumUsagePrice, String type, boolean status) {
        this.couponName = couponName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.useOnce = useOnce;
        this.useQuota = useQuota;
        this.discountValue = discountValue;
        this.minimumUsagePrice = minimumUsagePrice;
        this.type = type;
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }
    
    public double getDiscountValue() {
        return this.discountValue;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponName(){
        return couponName;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean getUseOnce() {
        return this.useOnce;
    }

    public void setUseOnce(boolean useOnce) {
        this.useOnce = useOnce;
    }

    public int getUseQuota() {
        return this.useQuota;
    }

    public void setUseQuota(int useQuota) {
        this.useQuota = useQuota;
    }

    public double getMinimumUsagePrice() {
        return this.minimumUsagePrice;
    }

    public void setMinimumUsagePrice(double minimumUsagePrice) {
        this.minimumUsagePrice = minimumUsagePrice;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

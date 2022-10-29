import java.util.Date;

public class Product {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInStockQuantity() {
        return inStockQuantity;
    }

    public void setInStockQuantity(int inStockQuantity) {
        this.inStockQuantity = inStockQuantity;
    }

    public ProductState getState() {
        return state;
    }

    public void setState(ProductState state) throws ProductIsDeletedException {
        try{
            if (!getState().toString().equals(new ProductState_Deleted().toString())) {
                this.state = state;
            } else {
                throw new ProductIsDeletedException();
            }
        } catch (ProductIsDeletedException e){
            throw new ProductIsDeletedException();
        }

    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    private String name;
    private double price;
    private String description;
    private int inStockQuantity;
    private ProductState state;
    private double weight;
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }


    public Product(String name, double price, String description, int inStockQuantity, ProductState state, double weight) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.inStockQuantity = inStockQuantity;
        this.state = state;
        this.weight = weight;

        this.createDate = new Date();
    }

}

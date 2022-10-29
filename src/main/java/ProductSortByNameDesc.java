public class ProductSortByNameDesc extends ProductSortType {


    @Override
    public int compare(Product p1, Product p2) {
        return p2.getName().compareTo(p1.getName());
    }
}


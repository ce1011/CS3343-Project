public class ProductSortByNameAsc extends ProductSortType {
    @Override
    public String toString() {
        return "Name Ascending";
    }

    @Override
    public int compare(Product p1, Product p2) {
        return p1.getName().compareTo(p2.getName());
    }
}


public class ProductSortByCreateDateAsc extends ProductSortType {
    @Override
    public String toString() {
        return "Create Date Ascending";
    }

    @Override
    public int compare(Product p1, Product p2) {
        return p1.getCreateDate().compareTo(p2.getCreateDate());
    }
}

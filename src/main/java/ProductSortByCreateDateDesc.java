public class ProductSortByCreateDateDesc extends ProductSortType {
    @Override
    public String toString() {
        return "Create Date Descending";
    }

    @Override
    public int compare(Product p1, Product p2) {
        return p2.getCreateDate().compareTo(p1.getCreateDate());
    }

}

public class ProductSortByCreateDateAsc extends ProductSortType {
    @Override
    public int compare(Product p1, Product p2) {
        return p1.getCreateDate().compareTo(p2.getCreateDate());
    }
}

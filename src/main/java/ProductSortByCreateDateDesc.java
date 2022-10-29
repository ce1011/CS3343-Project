public class ProductSortByCreateDateDesc extends ProductSortType {

    @Override
    public int compare(Product p1, Product p2) {
        return p2.getCreateDate().compareTo(p1.getCreateDate());
    }

}

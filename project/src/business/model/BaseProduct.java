package business.model;

import business.model.MenuItem;

public class BaseProduct extends MenuItem {

    public BaseProduct(int id, String title, float rating, int calories, int protein, int fat, int sodium, double price) {
        super(id, title, rating, calories, protein, fat, sodium, price);
    }

    @Override
    public double compuntePrice() {
        return getPrice();
    }


}

package business.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class representing a composite product composed of several base products, all being stored in a list given as
 * an attribute
 */
public class CompositeProduct extends MenuItem {

    private ArrayList<MenuItem> menuProducts;

    public CompositeProduct( int id, String title, float rating, ArrayList<MenuItem> menuProducts) {
        super(id, title, rating);
        this.menuProducts = menuProducts;
    }

    public CompositeProduct(int id, String title, float rating, int calories, int protein, int fat, int sodium, double price, ArrayList<MenuItem> menuProducts) {
        super(id, title, rating, calories, protein, fat, sodium, price);
        this.menuProducts = menuProducts;
    }

    @Override
    public double compuntePrice() {

       double price=0;
        Iterator<MenuItem> myIterator = menuProducts.iterator();
        while(myIterator.hasNext())
        {
            MenuItem curentItem = myIterator.next();
            price+=curentItem.getPrice();
        }
        setPrice(price);
        return price;

    }

    /**
     * This method computes the final details of the order that is composed of several items. The Nutritive values
     * and the final price represents the sum of all the products that this Composite Product has
     */
    public void computeMenuDetails(){

        int calories=0, protein=0, fat=0, sodium=0;
        int[] values = new int[4];

        for(MenuItem m: this.menuProducts){
            if(m != null) {
                calories += m.getCalories();
                protein += m.getProtein();
                fat += m.getFat();
                sodium += m.getSodium();
            }
        }
        setCalories(values[0]);
        setProtein(values[1]);
        setFat(values[2]);
        setSodium(values[3]);

    }

    public void setAllDetails(){

    }

    public void addProduct( MenuItem m){
        menuProducts.add(m);
    }

    public void removeProduct( MenuItem m){
        menuProducts.remove(m);
    }


}

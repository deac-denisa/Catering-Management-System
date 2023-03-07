package business.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * This class represents the Product composed of the given fields
 */
public abstract class MenuItem implements Serializable {

    private int id;
    private String  title;
    private float rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private double price;

    /**
     * Method that computes the price of th order
     * @return the computed price
     */
    public abstract double compuntePrice();

    public MenuItem(int id, String title, float rating, int calories, int protein, int fat, int sodium, double price) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public MenuItem(int id, String title, float rating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.calories = 0;
        this.protein = 0;
        this.fat = 0;
        this.sodium = 0;
        this.price = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * method that sets all the attributes of a product to the ones of the given one
     * @param menuItem - product having the new attributes
     */
    public void setNewAttributes( MenuItem menuItem){
        this.title = menuItem.getTitle();
        this.rating = menuItem.getRating();
        this.calories = menuItem.getCalories();
        this.protein = menuItem.getProtein();
        this.fat = menuItem.getFat();
        this.sodium = menuItem.getSodium();
        this.price = menuItem.getPrice();
    }


    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }
}

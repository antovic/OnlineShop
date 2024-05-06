package vuk.antovic.onlineshop;

import android.graphics.drawable.Drawable;

public class ItemModel {
    private Drawable image;
    private String name;
    private int price;
    private String category;

    public ItemModel(Drawable image, String name, int price, String category) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int cost) {
        this.price = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

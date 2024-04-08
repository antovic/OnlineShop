package vuk.antovic.onlineshop;

import android.graphics.drawable.Drawable;

public class ItemModel {
    private Drawable image;
    private String name;
    private int cost;

    public ItemModel(Drawable image, String name, int cost) {
        this.image = image;
        this.name = name;
        this.cost = cost;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}

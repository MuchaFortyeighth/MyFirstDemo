package com.ask.food.vo;

/**
 * Created by xxxx on 2018/8/30.
 */
public class CartItem {

    private Food food;
    //数量
    private int quantity;

    private int subtotal;

    public CartItem(Food food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSubtotal() {
        return (this.getFood().getFoodPrice()*this.quantity);
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}

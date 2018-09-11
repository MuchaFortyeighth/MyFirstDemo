package com.ask.food.vo;

import java.io.Serializable;

/**
 * Created by xxxx on 2018/8/29.
 */
public class Food implements Serializable {
    private Integer foodId;
    private String foodName;
    private String remark;
    private Integer foodPrice;
    private String description;
    private String foodImage;

    public Food() {
    }

    public Food(Integer foodId, String foodName, String remark, Integer foodPrice, String description, String foodImage) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.remark = remark;
        this.foodPrice = foodPrice;
        this.description = description;
        this.foodImage = foodImage;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", remark='" + remark + '\'' +
                ", foodPrice=" + foodPrice +
                ", description='" + description + '\'' +
                ", foodImage='" + foodImage + '\'' +
                '}';
    }
}

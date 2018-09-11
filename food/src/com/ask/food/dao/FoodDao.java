package com.ask.food.dao;

import com.ask.food.vo.Food;
import org.apache.commons.dbutils.QueryRunner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxxx on 2018/8/29.
 */
public class FoodDao {


    QueryRunner runner = new QueryRunner(true);
    public List<Food> findAll(){
        List<Food> foods = new ArrayList<>();

        try (Connection conn = com.ask.food.dao.JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * from FOODINFO")
        ){

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                foods.add(new Food(rs.getInt("foodId"),
                            rs.getString("foodName"),
                            rs.getString("remark"),
                            rs.getInt("foodPrice"),
                            rs.getString("description"),
                            rs.getString("foodImage")
                ));
            }

        }catch (SQLException e){
            System.err.println("查询所有食物实体失败：" + e.getMessage());
        }
        return foods;
    }

    public Food findById(Integer foodId) {
        Food food = null;
        try (Connection conn = com.ask.food.dao.JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * from FOODINFO WHERE FOODID = ?")
        ){
            ps.setObject(1,foodId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                 food = (new Food(rs.getInt("foodId"),
                        rs.getString("foodName"),
                        rs.getString("remark"),
                        rs.getInt("foodPrice"),
                        rs.getString("description"),
                        rs.getString("foodImage")
                ));
                return food;
            }

        }catch (SQLException e){
            System.err.println("查询所有食物实体失败：" + e.getMessage());
        }
        return food;
    }



//    public static void main(String[] args) {
//        List<Food> foods = new FoodDao().findAll();
//        for (Food food : foods) {
//            System.out.println(food);
//        }
//
//        Food food = new FoodDao().findById(1);
//        System.out.println(food);
//    }
}

package com.ask.food.contoller;


import com.ask.food.dao.FoodDao;
import com.ask.food.vo.CartItem;
import com.ask.food.vo.Food;
import com.ask.web.mvc.annotation.Controller;
import com.ask.web.mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author reeves on 2018/8/29 10:05
 */
@Controller
public class FoodController {

    @RequestMapping("/foods/list")
    public String list(HttpServletRequest request){
        List<Food> foodList = new FoodDao().findAll();
        request.setAttribute("foodList", foodList);
        //request forward
        return "/main.jsp";
    }

    @RequestMapping("/foods/view")
    public String view(Food food, HttpServletRequest request){
        Food f = new FoodDao().findById(food.getFoodId());
        request.setAttribute("food", f);
        //request forward
        return "/foodDetail.jsp";
    }

    @RequestMapping("/foods/addtoCart")
    public String addToCart(Food food, HttpSession session){
        Food f = new FoodDao().findById(food.getFoodId());

        //2. 加入到购物车
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>)session.getAttribute("cart");

        // 2.1 购物车不存在: 如第一次将商品加入到购物车时
        if(cart == null){
            //创建购物车
            cart = new LinkedHashMap();
            cart.put(f.getFoodId(), new CartItem(f, 1));

        }else{
            // 2.2 当购物车已存

            // 2.2.1 当前加入购物车的商品在购物车中是否存在？ 存在的
            if(cart.containsKey(f.getFoodId())){
                CartItem cartItem = cart.get(f.getFoodId());
                cartItem.setQuantity(cartItem.getQuantity()+1);

                //覆盖原来项
                cart.put(f.getFoodId(), cartItem);

            }else {
                //2.2.2 购物车中不存在的
                cart.put(f.getFoodId(), new CartItem(f, 1));
            }
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart.jsp";
    }


}

package com.ask.food.contoller;



import com.ask.food.dao.FoodDao;
import com.ask.food.dao.UserDao;
import com.ask.food.vo.Food;
import com.ask.food.vo.User;
import com.ask.web.mvc.annotation.Controller;
import com.ask.web.mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 张建平 on 2018/8/29 10:05
 */
@Controller
public class UserController {

    @RequestMapping("/user/login")
    public String login(User _user, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        System.out.println(">>>>>" + _user);

        //return "/main.jsp";
        //重定向
        User user = new UserDao().login(_user.getUserName(),_user.getPassword());
        if (user!=null){
            session.setAttribute("logUser", user);
//            List<Food> foodList = new FoodDao().findAll();
//            session.setAttribute("foodList", foodList);
//            return "redirect:/main.jsp";
            return "redirect:/foods/list.do";
        }else {
            return "redirect:/login.jsp";
        }
    }


    @RequestMapping("/user/reg")
    public User reg(User user, HttpServletResponse response){
        System.out.println(">>>>>" + user);

//        response.getWriter().write("");
        return null;
    }


}

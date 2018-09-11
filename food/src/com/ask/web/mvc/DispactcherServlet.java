package com.ask.web.mvc;

import com.google.gson.GsonBuilder;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 DispatcherServlet核心控制器，负责处理所有用户的请求,请求路径模式：*.do
 DispatcherServlet职责：

 1) 把客户端请求的路径uri解析出来, Browser:  user/login.do  -----> /user/login
    request.getRequestUri()

 2) 通过uri从mappingMap中取到一个ControllerMapping对象，其包目标控制器类和目标方法实例。

 3) 再从ControllerMapping得到控制器类和方法对象。并反射创建Controller Class实例。

 4) 解析目标方法中的参数个数及参数类型，以便于随后调用方法时给这些参数传值。

 5) 创建一个与参数类型相同大小的数组来存参数值

 6) 利用apache beanutils组件库，将请求的参数: request.gerParameterMap();
 组装（复制）到controller对象的属性上
 BeanUtils.populate(actionInstance,   parametersMap );

 7)  反射调用调用控制器方法, 方法返回一个字符串用作页导航或返回void用于AJAX请求：
 Object targetPath = MethodUtils.invokeMethod(controller, handleMethod.getName(), parameter);

 被调用的方法大至如下：
     void | String  XxxController.login(BookUsers user [, request, response, session] ){
         1. 转发：return  "/food/showlist.do";
         2. 重定向：return  "redirect:/login.jsp";
     }

 8) DispatcherServlet 根据Controller的方法返回导航字符串进行页面导航，默认为转发，若返回的字符串以“redirect:”开头则表示重定向。
    request.getRequestDispatcher("url").forward(request, response);
    response.sendRedirect("url");

 * @author 张建平 on 2018/8/29 9:45
 */
@WebServlet(value = "*.do")
public class DispactcherServlet extends javax.servlet.http.HttpServlet {
    /*
     * 对于Servlet（单例）的实例属性的操作是非线程安全的，如果你需要定义一个Servlet的实例属性。
     * 那么必须保证对该 属性的操作是可并发访问的。
     */
    Map<String, ControllerMapping> controllerMappingMap;

    /**
     * 生命周期的初始化阶段
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init(ServletConfig config)");
        controllerMappingMap = new Configuration().config();
    }


    /**
     * 生命周期的销毁阶段
     */
    @Override
    public void destroy() {
        System.out.println("destroy().....在tomcat销毁此Servlet实例时，先调用" +
                "destroy()那么这样开发人员将有机会去释放一些系统资源。");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

        // 解析用户请求的路径
        String requestURI = request.getRequestURI();
        requestURI = StringUtils.substringBetween(requestURI, request.getContextPath(), ".do");
        System.out.println(requestURI);

        try {
            //检查请求的路径是否正确
            // Map<"/user/login", ControllerMapping>
            if(controllerMappingMap.containsKey(requestURI)){
                ControllerMapping mapping = controllerMappingMap.get(requestURI);

                //取得目标类及目标方法
                Class<?> controllerClass = mapping.getControllerClass();
                Method handleMethod = mapping.getHandleMethod();

                //实例化控制器实例
                Object controller = controllerClass.newInstance();

                //反射方法上的参数个数及参数类型
                Class<?>[] parameterTypes = handleMethod.getParameterTypes();

                //确定目标方法的参数类型入参数个数
                Object[] paramValues = new Object[parameterTypes.length];
                //String login(BookUsers user, HttpServletRequest request, HttpServletResponse response, HttpSession session)
                for (int i = 0; i < parameterTypes.length; i++) {
                    if(ClassUtils.isAssignable(parameterTypes[i], HttpServletRequest.class)){
                        paramValues[i] = request;

                    }else if(ClassUtils.isAssignable(parameterTypes[i], HttpServletResponse.class)){
                        paramValues[i] = response;

                    }else if(ClassUtils.isAssignable(parameterTypes[i], HttpSession.class)){
                        paramValues[i] = request.getSession();

                    }else{
                        //此时该参数应该是一个JavaBean类型
                        Object pojo = parameterTypes[i].newInstance();

                        //得到请求里所有的参数：Map<参数名, value>
                        Map<String, String[]> parameterMap = request.getParameterMap();
                        //beanutils会自动将map里的key与bean的属性名进行反射赋值
                        BeanUtils.populate(pojo, parameterMap);
                        paramValues[i] = pojo;
                    }
                }

                //调用目标方法，并将方法所需的参数传递过去
                //等价于下面的写法：handleMethod.invoke(controller, paramValues);
                Object returnValue = MethodUtils.invokeMethod(controller, handleMethod.getName(), paramValues);

                if(returnValue != null && returnValue instanceof String){ //方法返回的是一个字符串类
                   /*
                      1. 转发："/food/showlist.do";
                      2. 重定向："redirect:/login.jsp";
                    */
                    String path = returnValue.toString();
                    if(StringUtils.startsWith(path,"redirect:")){
                        //重定向
                        response.sendRedirect(request.getContextPath() + StringUtils.substringAfter(path,"redirect:"));
                    }else {
                        //转发
                        request.getRequestDispatcher(path).forward(request, response);
                    }
                }else if(returnValue != null && !(returnValue instanceof String)){
                    //返回的是一个bean，即ajax请求，并将该bean转换成json
                    String json = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd HH:mm:ss")
                            .setPrettyPrinting()
                            .create()
                            .toJson(returnValue);
                    response.getWriter().write(json);
                }

            }else {
                throw new RuntimeException("非法请求，path=" + requestURI);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}

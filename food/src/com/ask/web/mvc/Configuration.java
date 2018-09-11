package com.ask.web.mvc;

import com.ask.web.mvc.annotation.Controller;
import com.ask.web.mvc.annotation.RequestMapping;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author 张建平 on 2018/8/29 11:36
 */
public class Configuration {

    private String getControllerPackageName(String key){
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        return bundle.getString(key);
    }

    public Map<String, com.ask.web.mvc.ControllerMapping> config(){
        //可并发访问的集合
        Map<String, com.ask.web.mvc.ControllerMapping> controllerMappingMap = Collections.synchronizedMap(new HashMap<>());

        String controllerPackageName = getControllerPackageName("controller.package");
        //System.out.println(controllerPackageName);

        try {
            // com/ask/food/contoller
            String path = StringUtils.replaceAll(controllerPackageName, "\\.", "/");

            URI uri = com.ask.web.mvc.Configuration.class.getResource("/" + path).toURI();
            File ctrlDir = new File(uri);

            // 实现FilenameFilter 中的boolean accept(File dir, String name)方法
            //筛选出控制器包中的类
            String[] ctrlFileNames = ctrlDir.list((dir, name) -> {
                return name.endsWith(".class");
            });
            //System.out.println(Arrays.toString(ctrlFileNames));

            for (String ctrlFileName : ctrlFileNames) {
                //全限定类名=包中 + 类名
                String ctrlClassName = controllerPackageName + "." + StringUtils.substringBefore(ctrlFileName,".class");

                //动态加载类
                Class<?> ctrlClass = ClassUtils.getClass(ctrlClassName, true);
                //仅当类上注解了Controller注解
                if (ctrlClass.isAnnotationPresent(Controller.class)) {
                    //获取所有注解了 RequestMapping 的方法
                    Method[] handleMethods = MethodUtils.getMethodsWithAnnotation(ctrlClass, RequestMapping.class);
                    for (Method handleMethod : handleMethods) {
                        RequestMapping annotation = handleMethod.getAnnotation(RequestMapping.class);
                        // map<"/user/login", ControllerMapping>
                        com.ask.web.mvc.ControllerMapping mapping = new ControllerMapping(ctrlClass, handleMethod);
                        controllerMappingMap.put(annotation.value(), mapping);

                        System.out.println("加载>>> " + mapping);
                    }
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return controllerMappingMap;
    }









    public static void main(String[] args) {
        new com.ask.web.mvc.Configuration().config();
    }
}

package com.sx.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luozicheng
 * Created by Administrator on 2018/12/11.
 */
@WebServlet(name = "SessionServlet")
public class SessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        System.out.println(id);
        //session.invalidate();//销毁session数据  例如清空购物车
        String[] names = {"手电筒","手机","电视","冰箱"};
        String name = names[Integer.parseInt(id) - 1];
        Map<String,Integer> map  = (Map) request.getSession().getAttribute("cart");
        //第一次进入创建sessin
        if (map==null) {
            map  =new HashMap(4);
            map.put(name,Integer.parseInt(id));
        }else{
            if (map.containsKey(name)) {
                //获取之前存的值
                int num =  map.get(name);
                map.put(name,num+1);
            }else{
                map.put(name,1);
            }
        }
        //把数据存回session
        request.getSession().setAttribute("cart",map);
        response.getWriter().write("<a href='/jsp/product.jsp'>继续购物</a>" +
                " <a href='/jsp/cart.jsp'>结算</a>");
    }
}

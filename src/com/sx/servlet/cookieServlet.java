package com.sx.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/12/11.
 */
@WebServlet(name = "cookieServlet")
public class cookieServlet extends HttpServlet {
    /**
     * 实现记录用户浏览商品
     * 1、判断是否是第一次浏览
     * 	* 得到所有的cookie，判断cookie里面是否有名称是his的cookie，如果有不是第一次
     * 2、如果是第一次，得到id值，把值放到cookie里面，把cookie返回到浏览器
     * 3、如果不是第一次，得到当前商品的id，得到前一次回写到浏览器的id值，在前一次的基础之上追加新的id值
     * 	* 比如值 1,2，追加值 1,2,3
     *  * 判断当前浏览的商品，在cookie里面是否存在相同的商品的id，如果存在，不进行追加的操作
     *  * 把值放到cookie里面，把cookie返回到浏览器
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Cookie[] cookies = request.getCookies();
        Cookie cookie =getCookie(cookies,"sx");
        if (cookie==null){
            cookie =new Cookie("sx",id);
            System.out.println("cookitID ==" + id);
            cookie.setMaxAge(3600);//cookie持久化时间
            cookie.setPath("/");//cookie持久化影响路径
            response.addCookie(cookie);
        }else{
            String value = cookie.getValue();
            System.out.println(value +"==== old");
            String name = cookie.getName();
            if (!name.contains(value)) {
                cookie.setValue(id +"," + value);
                cookie.setMaxAge(3600);//cookie持久化时间
                cookie.setPath("/");//cookie持久化影响路径
                response.addCookie(cookie);
            }
        }
        response.sendRedirect("/jsp/cookie.jsp");

    }

    public   Cookie getCookie(Cookie[] cookies, String last) {

        if(cookies==null){
            return null;
        }

        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (name.equals(last)) {
                return cookie;
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

package com.sx.servlet;

import com.sx.login.TestLogin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by luozicheng on 2018/7/19.
 */
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        //super.doGet(req, resp);
        getContextDevices(resp);
        //getConfigDevices();
        getProperties();
        //转发
        String address ="/1.jsp";
        setRetransminssion(req, resp, address);

    }

    private void setRetransminssion(HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException {
        String str ="四夕水易";
        getServletContext().setAttribute("data",str);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(address);
        requestDispatcher.forward(req,resp);
    }

    private void getProperties() throws IOException {
        Properties properties =new Properties();
        InputStream inputStream = getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");
        properties.load(inputStream);
        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String url = (String) enumeration.nextElement();
            String values = properties.getProperty(url);
            System.out.println("url==" + url + "------values==" +values);
        }
    }

    private void getConfigDevices() {
        Enumeration<String> initParameterNames = getServletConfig().getInitParameterNames();
        while(initParameterNames.hasMoreElements()){
            String name = initParameterNames.nextElement();
            String value = getServletConfig().getInitParameter(name);
            System.out.println(name +"==name   "   +value+ "==value");
        }
    }

    private void getContextDevices(HttpServletResponse resp) throws IOException {
        resp.getWriter().println("hello world");
        Enumeration<String> initParameterNames = getServletContext().getInitParameterNames();//获取所有的初始化参数
        while (initParameterNames.hasMoreElements()){
            String name = initParameterNames.nextElement();
            String value = getServletContext().getInitParameter(name);
            System.out.println("NAME ==" +name +" ---value ===" + value);
        }
        System.out.println("Sevlet " + this.getServletName() + "has stopped");
        String charset = getServletContext().getInitParameter("charset"); //获取字符编码
        String url = getServletContext().getInitParameter("url");//获取数据库的连接信息
        String username = getServletContext().getInitParameter("username");
        String password = getServletContext().getInitParameter("password");
        System.out.println("charset====" + charset +"--- url==" + url + "---username ==" + username + "-- password==" +password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("http post ");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        int login = TestLogin.login(username, password);
        PrintWriter writer = resp.getWriter();
        if(login ==200){
            writer.write(username+"登录成功");
        }else{
            writer.write("登录失败");
        }
        writer.flush();
        writer.close();
       /* if(username.equals("zhangtisheng")  && password.equals("mamama")){
          *//*  PrintWriter writer = resp.getWriter();
            writer.write("张体胜登录成功");
            writer.flush();
            writer.close();*//*

        }*/
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Servlet " + this.getServletName() + "has started");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //获取config对象来获取xml的一些配置信息
      //  mServletConfig =config;
    }

}

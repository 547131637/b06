package com.sx.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2018/8/24.
 */
@WebServlet(name = "requestServlet")
public class  requestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.log("luozicheng--doPost" );
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.log("luozicheng -- doGet");
        // 访问路径http://localhost:8080/day06/servlet/RequestDemo1?name=aaa
        System.out.println("11"+request.getRequestURI());  ///request
        //http://localhost:8080/request
        // name=lzc
        System.out.println(request.getQueryString());
        // 获取客户端ip
        System.out.println(request.getRemoteAddr());
        // 获取客户端主机名，这个主机名没有在DNS上注册的话还是获取ip
        System.out.println(request.getRemoteHost());
        // 获取客户端浏览器的端口
        System.out.println(request.getRemotePort());
        // 获取web服务器的ip
        System.out.println(request.getLocalAddr());
        // 获取web服务器的主机名，没有在DNS上注册还是获取ip
        System.out.println(request.getLocalName());
        // 获取请求方式
        System.out.println(request.getMethod());
        execute(request,response);

    }
    private void execute(HttpServletRequest request , HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            //response.setContentType("text/html;charset=UTF-8");//设置文档格式以及编码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuffer requestURL = request.getRequestURL();//获取URL
        String method = request.getMethod();//获取请求方式
        String param = request.getParameter("param");//参数
        String remoteAddr = request.getRemoteAddr();//客户ip
        String localAddr = request.getLocalAddr();//web ip
        String queryString = request.getQueryString();//name
        PrintWriter writer;
        try {
            response.setContentType("text/html");
            writer = response.getWriter();
            writer.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01    Transitional//EN\">");
            writer.println("<HTML>");
            writer.println("  <HEAD><TITLE>RquestSevlet</TITLE></HEAD>");
            writer.println("  <BODY>");
            writer.println("  以"+method + "方式访问的该页面。读取到的param参数为：" + param + "<br/>");
          //  writer.println(" <form action>");
          //  writer.println(" <form action>");
          //  writer.println(" <form action>");
            writer.println("  </BODY>");
            writer.println("<HTML>");
            writer.flush();
            writer.close();
            this.log("close");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected long getLastModified(HttpServletRequest req) {
        this.log("luozicheng--getLastModified");
        return super.getLastModified(req);
    }
}

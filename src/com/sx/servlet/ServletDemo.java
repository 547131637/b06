package com.sx.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2018/7/26.
 */
@WebServlet(name = "ServletDemo")
public class ServletDemo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置指定码表打开，不然汉字会乱码
        response.setContentType("text/html;charset=UTF-8");
       /* response.getWriter().write("中国红");*/
        //获取资源路径
        String realPath = getServletContext().getRealPath("/1.png");
        String filename = realPath.substring(realPath.lastIndexOf("\\") + 1);//substring的特性要+1
        System.out.println("realPath ==" + realPath  + "filename ==" + filename);
        response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(filename, "UTF-8") );
        System.out.println("" +realPath);
        FileInputStream fileinputStream =new FileInputStream(realPath);
        OutputStream outputStream = response.getOutputStream();//IllegalStateException ：getWriter() has already been called for this response 不能和getWriter()方法同时使用
        int len =0;
        byte [] buffer =new byte[1024];
        while ((len=fileinputStream.read(buffer))>0){
            outputStream.write(buffer,0,len);
        }

        fileinputStream.close();
        outputStream.close();
    }

}

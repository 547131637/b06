package com.sx.servlet;


import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;


/**
 * @author luozicheng
 * Created by luozicheng on 2018/12/19.
 */
@WebServlet(name = "DownloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取项目路径
        String contextPath = getServletContext().getRealPath("/team/1.zip");
        System.out.println(contextPath);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int strIndex =contextPath.lastIndexOf("\\");
        String fileName = contextPath.substring(strIndex+1);
        //得到当前请求的浏览器类型 ，使用头 User-Agent
        String agent = request.getHeader("User-Agent");
        if(agent.contains("Firefox")) {//如果是火狐浏览器
            //base64编码
            fileName = "=?UTF-8?B?"+
                    new BASE64Encoder().encode(fileName.getBytes("utf-8"))+"?=";
        } else {
            //url编码
            fileName = URLEncoder.encode(fileName, "UTF-8");
        }
        //设置文件的类型
        String mimeType = getServletContext().getMimeType(fileName);
        response.setContentType(mimeType);

        InputStream in =new FileInputStream(contextPath);
        ServletOutputStream outputStream = response.getOutputStream();
        int len =-1;
        byte [] arr =new byte[1024];
        while ((len=in.read(arr))!=-1){
            outputStream.write(arr,0,len);
        }
        in.close();
        arr.clone();
    }
}

package com.sx.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sx.bean.DeviceBean;
import com.sx.login.deviceJDBC;
import com.sx.utils.Constant;
import javafx.scene.input.DataFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.sun.xml.internal.ws.api.message.Packet.Status.Response;

/**
 * @author luozicheng
 * Created by Administrator on 2018/12/10.
 */
@WebServlet(name = "DeviceServlet")
public class DeviceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding(Constant.CODE);
        request.setCharacterEncoding(Constant.CODE);
/*
        String address ="/jdbc.jsp";
        setRetransminssion(request, response, address);*/
        BufferedReader reader = request.getReader();
        // 接收用户端传来的JSON字符串(body体里的数据)
        String readerStr = "";
        String line;
        while ((line = reader.readLine()) != null){
            readerStr = readerStr.concat(line);
        }
        DeviceBean deviceBean =new DeviceBean();

// 使用阿里的fastjson jar包处理json数据(这里是用map进行接收的,你也可以定义vo层容器类接收)
        HashMap map = JSONObject.parseObject(readerStr, HashMap.class);
        deviceBean.setDeviceID((String) map.get("DeviceID"));
        deviceBean.setStepCount((String) map.get("StepCount"));
        deviceBean.setSignalIntensity((String) map.get("SignalIntensity"));
        deviceBean.setLongitude((String) map.get("Longitude"));
        deviceBean.setLatitude((String) map.get("Latitude"));
        String format = getDate();
        deviceBean.setUploadTime(format);
        deviceBean.setUpdateTime(format);

        response.setContentType(Constant.CONTENT_TYPE_JSON);
        response.setStatus(HttpServletResponse.SC_OK);
/*
        String resjson = JSONObject.toJSONString(deviceBean);// 将返回的数据json序列化
        response.getWriter().println(resjson);// 以流的形式写回客户端,被客户端ajax接收解析;*/
        // 获取客户端ip
        System.out.println(request.getRemoteAddr());
        // 获取客户端主机名，这个主机名没有在DNS上注册的话还是获取ip
        System.out.println(request.getRemoteHost());
        // 获取客户端浏览器的端口
        System.out.println(request.getRemotePort());
        // 获取url
        System.out.println(request.getRequestURL());
        int i = deviceJDBC.savaData(deviceBean.getDeviceID(), deviceBean.getStepCount(), deviceBean.getSignalIntensity(), deviceBean.getUploadTime(), deviceBean.getUpdateTime(),deviceBean.getLongitude(),deviceBean.getLatitude());
        if(i == Constant.SUCCESS){
            response.setStatus(i);
        }else{
            response.setStatus(i);
        }
    }
    private String getDate() {
        Date now = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        return ft.format(now);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // doPost(request,response);
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("luozicheng" + requestURL);
        int  id =0;

       /* String[] devices = requestURL.toString().split("http://127.0.0.1:8080/xhs/b06/device");
        if (devices.length==2){
            id=Integer.parseInt(requestURL.toString().split("http://127.0.0.1:8080/xhs/b06/device/")[1]);
        }*/
        response.setCharacterEncoding(Constant.CODE);
        response.setContentType(Constant.CONTENT_TYPE_JSON);
        response.setStatus(HttpServletResponse.SC_OK);

        ArrayList<DeviceBean> arrayList = deviceJDBC.selectData(id);
        // 将返回的数据json序列化
        String resjson = JSONObject.toJSONString(arrayList);
        System.out.println(resjson);
        // 以流的形式写回客户端,被客户端ajax接收解析;
        response.getWriter().println(resjson);
    }
    private void setRetransminssion(HttpServletRequest req, HttpServletResponse resp, String address) throws ServletException, IOException {
        String str ="四夕水易";
        getServletContext().setAttribute("data",str);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(address);
        requestDispatcher.forward(req,resp);
    }
}

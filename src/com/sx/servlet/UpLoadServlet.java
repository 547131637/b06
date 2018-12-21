package com.sx.servlet;

import com.sx.utils.Constant;
import org.apache.commons.fileupload.FileItemHeaders;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

/**
 * @author luozicheng
 * Created by Administrator on 2018/12/21.
 */
@WebServlet(name = "UpLoadServlet")
public class UpLoadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory diskFileItemFactory =new DiskFileItemFactory();
        ServletFileUpload servletFileUpload =new ServletFileUpload(diskFileItemFactory);
        //设置编码
        servletFileUpload.setHeaderEncoding(Constant.CODE);
        //设置上传单个文件大小
        servletFileUpload.setFileSizeMax(1024*1024*50);
        try {
            List<DiskFileItem> list = servletFileUpload.parseRequest(request);
            for (DiskFileItem diskFileItem : list) {
                //判断是否为普通数据
                if (diskFileItem.isFormField()) {
                    String name = diskFileItem.getName();
                    FileItemHeaders headers = diskFileItem.getHeaders();
                    String contentType = diskFileItem.getContentType();
                    String fieldName = diskFileItem.getFieldName();
                    String string = diskFileItem.getString("UTF-8");
                    System.out.println(name+"-----------------------------------");
                    System.out.println(headers);
                    System.out.println(contentType);
                    System.out.println(fieldName);
                    System.out.println(string + "------------------------------------");
                }else{
                    System.out.println("非普通数据" + "------------------------------------");
                    //非普通数据 //获取当前项目的路径
                    String realPath = getServletContext().getRealPath("/team");
                    //在某些浏览器里面，得到的是带路径名称  c:\aa\1.txt
                    String filename = diskFileItem.getName();
                    //判断是否带 \，如果带 \进行截取
                    int lens = filename.lastIndexOf("\\");
                    if(lens != -1) {
                        //截取
                        filename = filename.substring(lens+1);
                    }
                    //在文件名称里面添加随机的唯一的值
                    String uuid = UUID.randomUUID().toString();
                    //uuid_filename
                    filename = uuid+"_"+filename;
                    InputStream inputStream = diskFileItem.getInputStream();
                    OutputStream outputStream =new FileOutputStream(realPath +"/" + filename);
                    int len =0;
                    byte [] array =new byte[1024];
                    while ((len=inputStream.read(array))!=-1){
                        outputStream.write(array,0,len);
                    }
                    inputStream.close();
                    outputStream.close();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

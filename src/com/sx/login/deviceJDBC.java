package com.sx.login;

import com.sx.bean.DeviceBean;
import com.sx.utils.MyJdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/12/10.
 */
public class deviceJDBC {
    public  static int savaData(String deviceID, String stepCount, String signalIntensity, String uploadTime, String updateTime,String Longitude,String Latitude){
        Connection connection =null;
        PreparedStatement preparedStatement = null;

        try {
            //String  sql ="insert into devices(DeviceID,StepCount,SignalIntensity,UploadTime,UpdateTime) values('b08','86','9876','2018/09/27/19:43:21','2018/09/27/19:43:21')";
            connection = MyJdbcUtils.getConnection();
            String sql="insert into devices(DeviceID,StepCount,SignalIntensity,Longitude,Latitude,UploadTime,UpdateTime) values(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,deviceID);
            preparedStatement.setString(2,stepCount);
            preparedStatement.setString(3,signalIntensity);
            preparedStatement.setString(4,Longitude);
            preparedStatement.setString(5,Latitude);
            preparedStatement.setString(6,uploadTime);
            preparedStatement.setString(7,updateTime);
            int i = preparedStatement.executeUpdate();
            if(i>0){
               return 200;
            }else{
                return 404;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 404;
        }finally {
            MyJdbcUtils.clearConn(connection,preparedStatement,null);
        }
    }
    public static ArrayList<DeviceBean>  selectData(int id){
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        DeviceBean deviceBean;
        ArrayList<DeviceBean> arrayList =new ArrayList<>();
        try {
            connection = MyJdbcUtils.getConnection();
            String sql ="";
            if(id ==0){
                 sql="select *  from devices";
            }else{
                 sql="select *  from devices  where id =" + id;
            }

            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                deviceBean =new DeviceBean();
                deviceBean.setId(resultSet.getString("Id"));
                deviceBean.setDeviceID(resultSet.getString("DeviceID"));
                deviceBean.setStepCount(resultSet.getString("StepCount"));
                deviceBean.setSignalIntensity(resultSet.getString("SignalIntensity"));
                deviceBean.setLongitude(resultSet.getString("Longitude"));
                deviceBean.setLatitude(resultSet.getString("Latitude"));
                deviceBean.setUploadTime(resultSet.getString("UploadTime"));
                deviceBean.setUpdateTime(resultSet.getString("UpdateTime"));
                arrayList.add(deviceBean);
            }
            return arrayList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            MyJdbcUtils.clearConn(connection,preparedStatement,null);
        }
    }
}

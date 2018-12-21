<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/26
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/b06/device" method="post">
            用户名：<input type="text" name="DeviceID"  id="usernameid"/><br/>
            密码：<input type="text" name="StepCount"  id="passwordid" /><br/>
            密码：<input type="text" name="SignalIntensity"  id="signal" /><br/>
            <input type="submit" value="提交"  onclick ="checkInformation"/></br>
        </form>
        <script type="text/javascript">
            function checkInformation(){
                var username =document.getElementById(usernameid).value;
                var password =document.getElementById(passwordid).value;
                var signal =document.getElementById(signal).value;
            }
        </script>
    </body>
</html>

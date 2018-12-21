<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/26
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page errorPage="1.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>

    </head>
    <body>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <h2>${msg}</h2>
            用户名：<input type="text" name="username"  id="usernameid"/><br/>
            密码：<input type="password" name="password"  id="passwordid" /><br/>
            验证码：<input type="text" name="checkcode"   id="verification" />
            <img src="${pageContext.request.contextPath}/in" onclick="changeImage(this)" alt="换一张" style="cursor:hand"><br/>
            <input type="submit" value="提交"  onclick ="checkInformation"/></br>
        </form>
        <%--img.src = img.src;代表this.src = /day06/servlet/ResponseDemo3，但是这么写是不可以的，还是有缓存的问题，需要加一个时间数 --%>
        <script type="text/javascript">
            function changeImage(img) {
                img.src = img.src + "?" + new Date().getTime();
            }
            function checkInformation(){
                var username =document.getElementById(usernameid).value;
                var password =document.getElementById(passwordid).value;
                var verification =document.getElementById(verification).value;
                if(username !==""){
                    alert("用户名不能为空");
                }
                if(password !==""){
                    alert("密码不能为空");
                }
                if(verification !==""){
                    alert("验证码不能为空");
                }
            }
        </script>
    </body>
</html>

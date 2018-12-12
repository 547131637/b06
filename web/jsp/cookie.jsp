<%@ page import="com.sx.utils.MyJdbcUtils" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/12
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>cookie</title>
</head>
<body>
    <img src="/img/1.jpg" /><a href="/cookie?id=1">手电筒</a>
    <img src="/img/2.jpg" /><a href="/cookie?id=2">手机</a>
    <img src="/img/3.jpg" /><a href="/cookie?id=3">电视</a>
    <img src="/img/4.jpg" /><a href="/cookie?id=4">冰箱</a>

    <h2> 浏览商品记录</h2>

    <% Cookie[] cookies = request.getCookies();
        Cookie c2 =null;
        for (Cookie cookie : cookies) {
            if (cookie.equals("sx")){
                c2 =cookie;
            }
        }
        if (c2==null) {
     %>
    <h2>没有浏览记录</h2>
    <%
        }else{
            String value = c2.getValue();
            //显示商品的图片和商品的名称
            String[] names = {"手电筒","手机","电视","冰箱"};
            String[] ids = value.split(",");
            for(String id1:ids) {
                String name = names[Integer.parseInt(id1) - 1];
    %>
            <img src="<img src=/img/><%=id1%>"/><%=name%>
    <%
            }
        }
    %>
</body>
</html>
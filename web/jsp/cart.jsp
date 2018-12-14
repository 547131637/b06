<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/12
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>cart</title>
</head>
<body>
    <h1>结算页面</h1>
    <% Map<String,Integer> cart = (Map) request.getSession().getAttribute("cart");
        if (cart==null) {
    %>
        <h2>购物车没有商品</h2>
    <%
        }else{
            Set<String> set = cart.keySet();
            for (String s : set) {
                int num = cart.get(s);
                %>
                名称：<%=s%> 数量：<%=num%>
                <%
            }
        }
    %>
</body>
</html>

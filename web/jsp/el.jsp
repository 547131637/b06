<%--
  Created by IntelliJ IDEA.
  User: luozicheng
  Date: 2018/12/20
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java"   errorPage="404.jsp" isErrorPage="true"  pageEncoding="UTF-8" %>
<%@ page import="com.sx.bean.DeviceBean" %>
<%@include file="1.jsp"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@taglib prefix="jstl" tagdir="c" %> --%>
<html>

<head>
    <%-- jsp的注释  --%>
        
    <title>js标签</title>
</head>

<body>
<%-- JSP的动作标签   --%>
<jsp:useBean id="DeviceBean" class="com.sx.bean.DeviceBean"/>
<jsp:setProperty property="deviceID" name="b06"/>
<jsp:getProperty property="deviceID" name="DeviceBean"/>
<% ArrayList<com.sx.bean.DeviceBean> arrayList =new ArrayList();
    arrayList.add(new DeviceBean());
    arrayList.add(new DeviceBean());
%>

<c:if  test="${ a ==10}">
    <h1> a ==10</h1>
</c:if>

<%-- 增强for arr是集合--%>
<c:forEach var="a" items="arr" >
${a};
</c:forEach>

</body>
</html>
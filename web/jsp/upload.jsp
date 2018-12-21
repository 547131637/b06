<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/20
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>

</head>
<body>

    <form action="/upload" method="post" enctype="multipart/form-data"  id="form1">
        <div id="div1">
            <input type="text" name="name"/>
            <br/>
                <input type="file" name="filename"/>
                <input type="submit" name="download"  value="上传"/>
            <input type="button" name="add" value="添加" id="add" onclick="addItems();">
        </div>
    </form>

</body>
<script type="text/javascript">
    function addItems(){
       var div3= document.getElementById("form1");
       //累加控件
       div3.innerHTML +="<div> <input type='file' name='filename'/>  <input type='button'  value='删除' onclick='removeItems(this);'> </div>";
    }
    function removeItems(who) {
        //获取删除按钮的父控件--div
        var div1 =who.parentNode;
        //获得div控件的父控件---form
        var div_item=div1.parentNode;
        //通过form删除div控件
        div_item.removeChild(div1);
    }

</script>
</html>

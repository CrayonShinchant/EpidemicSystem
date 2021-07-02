<%--
  Created by IntelliJ IDEA.
  User: 53219
  Date: 2021/6/30
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆界面</title>
</head>
<body>
<h1>登录系统</h1>
<form action="${pageContext.request.contextPath}/user/login" method="post">
    账号：<input type="text" name="account" placeholder="请输入用户账号"><br>
    密码：<input type="password" name="password" placeholder="请输入用户密码"><br>
    <input type="submit" value="登录">
    <div>${msg}</div>
</form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: hzwangwei
  Date: 2017/11/29
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/admin/doResetPassword" method="post">
    <table>
        <tr>
            <td>用户名:</td>
            <td>${userName}</td>
        </tr>
        <tr>
            <td>原密码:</td>
            <td><input type="text" name="oldPassword"></td>
        </tr>
        <tr>
            <td>新密码:</td>
            <td><input type="text" name="newPassword"></td>
        </tr>
        <tr>
            <td><input type="reset" value="重置"></td>
            <td><input type="submit" value="提交"></td>
        </tr>
    </table>
    <input type="hidden" name="token" value="${token}"/>
</form>
</body>
</html>

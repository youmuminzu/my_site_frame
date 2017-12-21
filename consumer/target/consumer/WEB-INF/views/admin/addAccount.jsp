<%--
  Created by IntelliJ IDEA.
  User: hzwangwei
  Date: 2017/12/19
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/admin/doAddAccount" id="mainForm">
    登录账号:<input name="userName" type="text" id="userName">(只能字母、数字、下划线的组合)<br>
    登录密码:<input type="password" name="password" id="password"><br>
    确认密码:<input type="password" name="confirmPassword" id="confirmPassword"><br>
    选择角色:<select name="role">
    <c:forEach  items="${roleList}" var="roleItem">
        <option value="${roleItem.key}">${roleItem.value.role}</option>
    </c:forEach>

</select><br>
    <input type="hidden" name="token" value="${token}">
    <button type="button" onclick="check();">提交</button> &nbsp;&nbsp;<input type="reset" value="重置">
</form>
</body>
<script>
    function check() {
        var userName = document.getElementById("userName").value;
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;
        if(userName == null || userName == undefined || userName == "") {
            alert("账号不能为空");
            return false;
        }
        if(password == null || password == undefined || password == "") {
            alert("密码不能为空");
            return false;
        }
        if(password != confirmPassword) {
            alert("两次输入密码必须一致");
            return false;
        }
        document.getElementById("mainForm").submit();

    }
</script>
</html>

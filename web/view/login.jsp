<%--
  Created by IntelliJ IDEA.
  User: krzysiek
  Date: 23.01.15
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script>
        <%@ include file="/resources/javascript/login.js"%>
    </script>


</head>
<body>
    Login:

    <form name="loginForm" id="loginForm" method="POST" action="/login" onSubmit="return validateForm(event);">
        <table id="formTable">
            <tr>
                <td><input type="text" name="username" id="username"/><br> </td>
                <td id="usernameError" class="error"></td>
            </tr>
            <tr>
                <td><input type="password" name="password" id="password"/> </td>
                <td id="passwordError" class="error"></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Login">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>

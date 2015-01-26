<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

    <style>
      <%@ include file="/resources/css/bootstrap.css" %>
      #formRegister{
        width: 450px;
        margin-left: auto;
        margin-right: auto;
      }
    </style>


    <script>
      <%@ include file="/resources/javascript/register.js"%>
    </script>

</head>
<body>

<div id="mainPage">

  <nav class="navbar navbar-default navbar-static-top">
    <div id="header">
      <div id="loginLogout">
        <a href="/" class="btn btn-default navbar-btn">Main Page</a>
        <c:choose>
          <c:when test="${sessionScope.user !=null}">
            <a href="/contacts" class="btn btn-default navbar-btn">My Contacts</a>
            <a href="/logout" class="btn btn-default navbar-btn">Hello <c:out value="${sessionScope.user.getLogin()}"/>, logout </a>
          </c:when>
          <c:otherwise>
            <a href="/login" class="btn btn-default navbar-btn">Login </a>
            <a href="/register" class="btn btn-default navbar-btn">Register </a>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </nav>



  <div id="formRegister" class="jumbotron">
    <form name="registerForm" id="registerForm" method="POST" action="/register" onSubmit="return validateForm(event);">
      <table id="formTable" >
        <tr>
          <td><input type="text" name="username" id="username"/><br> </td>
          <td id="usernameError" class="error"></td>
        </tr>
        <tr>
          <td><input type="password" name="password" id="password"/> </td>
          <td id="passwordError" class="error"></td>
        </tr>
        <tr>
          <td><input type="password" name="password2" id="password2"/> </td>
          <td id="passwordError2" class="error"></td>
        </tr>
        <tr>
          <td>
            <input type="submit" value="Register">
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>

</body>
</html>


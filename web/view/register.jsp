<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script>
    <%@ include file="/resources/javascript/register.js"%>
  </script>


</head>
<body>
Register:

<form name="registerForm" id="registerForm" method="POST" action="/register" onSubmit="return validateForm(event);">
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
</body>
</html>


<%--
  Created by IntelliJ IDEA.
  User: krzysiek
  Date: 23.01.15
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

      <style>
          <%@ include file="/resources/css/main.css" %>
      </style>

      <script>
        <%@ include file="/resources/javascript/fle.js"%>
      </script>

  </head>
  <body>

  <div id="mainPage">
      path <c:out value="${param.contextPath}"></c:out> path
      <%--Hello, users test -> <a href="${request.getContextPath()}/users">Users</a>   --%>
      Hello, users test -> <a href="/users">Users</a>
      Hello, contacts test -> <a href="/contacts">contacts</a>

  </div>


  </body>
</html>

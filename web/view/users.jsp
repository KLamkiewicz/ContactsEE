<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>

  <style>
    <%@ include file="/resources/css/bootstrap.css" %>
  </style>

</head>
<body>



<div id="mainPage">



  <div id="users">
    <c:forEach var="o" items="${osoby}">
      <c:out value="${o.getImie()}"/>
    </c:forEach>
  </div>

  Home page test -> <a href="/">Home page</a>

</div>


</body>
</html>

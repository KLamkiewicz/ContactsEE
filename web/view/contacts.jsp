<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <%--<style>--%>
    <%--<%@ include file="/resources/css/main.css" %>--%>
  <%--</style>--%>

  <script>
    <%@ include file="/resources/javascript/contacts.js"%>
  </script>
</head>
<body>

<div id="mainPage">

  <div id="contacts">
  <c:forEach var="c" items="${contacts}">
    <div data-id="${c.getOsobaId()}" id="contact">
      <c:out value="${c.getImie()}"/>
      <c:out value="${c.getNazwisko()}"/>
      <button onclick="deleteContact(${c.getOsobaId()})">Delete</button>
    </div>
  </c:forEach>

  </div>
  Logout <a href="/logout">Logo </a>
  Hello, main page -> <a href="/">Main page</a>

</div>


</body>
</html>

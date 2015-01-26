<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

      <style>
          <%@ include file="/resources/css/bootstrap.css" %>
      </style>

      <style>
          <%@ include file="/resources/css/main.css" %>
      </style>

      <script>
        <%@ include file="/resources/javascript/main.js"%>
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

      <div id="contacts">
          <c:forEach var="c" items="${contacts}">
              <div data-id="${c.getOsobaId()}" id="contact">
                  <ul class="list-group">
                      <div id="contactID">
                          <li class="list-group-item list-group-item-success">  <c:out value="${c.getImie()}"/>  <c:out value="${c.getNazwisko()}"/></li>
                      </div>
                      <div data-hid="${c.getOsobaId()}" id="contactHidden">
                          <li class="list-group-item">   <c:out value="${c.getEmail()}"/> </li>
                          <li class="list-group-item"> <c:out value="${c.getTelefon()}"/> </li>
                          <li class="list-group-item">  <c:out value="${c.getDob()}"/> </li>
                      </div>
                  </ul>
              </div>
          </c:forEach>
      </div>

      <%--Hello, users test -> <a href="/users">Users</a>--%>
      <%--Hello, contacts test -> <a href="/contacts">contacts</a>--%>

  </div>


  </body>
</html>

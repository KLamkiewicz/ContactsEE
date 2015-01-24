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
        <%@ include file="/resources/javascript/main.js"%>
      </script>

  </head>
  <body>

  <div id="mainPage">

      <div id="header">

          <div id="loginLogout">
          <c:choose>
              <c:when test="${sessionScope.user !=null}">
                  <a href="/contacts">My Contacts</a>
                  <a href="/logout">Hello <c:out value="${sessionScope.user.getLogin()}"/>, logout </a>
              </c:when>
              <c:otherwise>
                  <a href="/login">Login </a>
              </c:otherwise>
          </c:choose>
          </div>
      </div>

      <div id="contacts">
          <c:forEach var="c" items="${contacts}">
              <div data-id="${c.getOsobaId()}" id="contact">
                  <div id="contactID">
                      <c:out value="${c.getOsobaId()}"/>
                  </div>
                  <div data-hid="${c.getOsobaId()}" id="contactHidden">
                      <c:out value="${c.getImie()}"/>
                      <c:out value="${c.getNazwisko()}"/>
                  </div>
              </div>
          </c:forEach>
      </div>

      <%--Hello, users test -> <a href="/users">Users</a>--%>
      <%--Hello, contacts test -> <a href="/contacts">contacts</a>--%>

  </div>


  </body>
</html>

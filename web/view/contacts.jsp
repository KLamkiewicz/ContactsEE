<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <style>
    <%@ include file="/resources/css/contacts.css" %>
  </style>

  <script>
    <%@ include file="/resources/javascript/contacts.js"%>
  </script>
</head>
<body>

<div id="mainPage">


  <div id="newContactA">
    <div id="newContactFormStart">
        <button onclick="createForm()">Add Contact</button>
    </div>
    <div id="newContactForm">
      <form name="contactForm" id="contactForm" method="POST" action="/contacts" onSubmit="return createContact(event);">
        <table id="contactFormTable">
          <tr>
            <td><input type="text" name="username" id="username"/><br> </td>
            <td id="usernameError" class="error"></td>
          </tr>
          <tr>
            <td><input type="password" name="password" id="password"/> </td>
            <td id="passwordError" class="error"></td>
          </tr>
          <tr>
            <td id="authError" class="error"></td>
          </tr>
          <tr>
            <td>
              <input type="submit" value="Create contact">
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>


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

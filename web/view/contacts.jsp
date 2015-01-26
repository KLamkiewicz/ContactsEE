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
    <%@ include file="/resources/css/contacts.css" %>
  </style>

  <script>
    <%@ include file="/resources/javascript/contacts.js"%>
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

  <div id="newContactA">
    <div id="newContactFormStart">
        <button onclick="createForm()">Add Contact</button>
    </div>
    <div id="newContactForm">
      <form name="contactForm" id="contactForm" method="POST" action="/contacts" onSubmit="return createContact(event);">
        <table id="contactFormTable">
          <tr>
            <td>Name: <input type="text" name="name" id="name"/><br> </td>
            <td id="nameError" class="error"></td>
          </tr>
          <tr>
            <td>Surname: <input type="text" name="surname" id="surname"/><br> </td>
            <td id="surnameError" class="error"></td>
          </tr>
          <tr>
            <td>Email: <input type="text" name="email" id="email"/><br> </td>
            <td id="emailError" class="error"></td>
          </tr>
          <tr>
            <td>Phone: <input type="text" name="phone" id="phone"/><br> </td>
            <td id="phoneError" class="error"></td>
          </tr>
          <tr>
            <td>Date of Birth: <input type="text" name="birth" id="birth"/><br> </td>
            <td id="birthError" class="error"></td>
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
      <ul class="list-group">
        <div id="contactID">
          <li class="list-group-item list-group-item-success">
            <c:out value="${c.getImie()}"/>  <c:out value="${c.getNazwisko()}"/>

            <div id="formControl">
              <form action="/editContacts" method="GET">
                <input type="hidden" name="contactId" value="${c.getOsobaId()}"/>
                <input type="hidden" name="imie" value="${c.getImie()}"/>
                <input type="hidden" name="nazwisko" value="${c.getNazwisko()}"/>
                <input type="hidden" name="email" value="${c.getEmail()}"/>
                <input type="hidden" name="telefon" value="${c.getTelefon()}"/>
                <input type="hidden" name="dob" value="${c.getDob()}"/>
                <input type="submit" value="Edit">
              </form>
              <button onclick="deleteContact(${c.getOsobaId()})">Delete</button>
            </div>
          </li>
        </div>

        <div data-hid="${c.getOsobaId()}" id="contactHidden">
          <li class="list-group-item">  <c:out value="${c.getEmail()}"/></li>
          <li class="list-group-item"> <c:out value="${c.getTelefon()}"/></li>
          <li class="list-group-item"> <c:out value="${c.getDob()}"/> </li>
        </div>
      </ul>

    </div>
  </c:forEach>
  </div>


</div>


</body>
</html>

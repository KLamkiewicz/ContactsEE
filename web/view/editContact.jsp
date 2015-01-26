<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

    <style>
      <%@ include file="/resources/css/edit.css" %>
    </style>

    <script>
      <%@ include file="/resources/javascript/edit.js"%>
    </script>
</head>
<body>

Edit:

<form name="editContactForm" id="editContactForm" method="POST" action="/editContacts" onSubmit="return validateEdit(event);">
    <table id="contactFormTable">
        <tr>
            <td>Name: <input type="text" name="imie" id="imie" value="${imie}"/><br> </td>
            <td id="nameError" class="error"></td>
        </tr>
        <tr>
            <td>Surname: <input type="text" name="nazwisko" id="nazwisko" value="${nazwisko}"/><br> </td>
            <td id="surnameError" class="error"></td>
        </tr>
        <tr>
            <td>Email: <input type="text" name="email" id="email" value="${email}"/><br> </td>
            <td id="emailError" class="error"></td>
        </tr>
        <tr>
            <td>Phone: <input type="text" name="telefon" id="telefon" value="${telefon}"/><br> </td>
            <td id="phoneError" class="error"></td>
        </tr>
        <tr>
            <td>Date of Birth: <input type="text" name="dob" id="dob" value="${dob}"/><br> </td>
            <td id="birthError" class="error"></td>
        </tr>
        <tr>
            <td>Name: <input type="hidden" type="text" name="contactId" id="contactId" value="${contactId}"/><br> </td>
            <td id="nonimp" class="error"></td>
        </tr>

        <tr>
            <td id="authError" class="error"></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Edit contact">
            </td>
        </tr>
    </table>
</form>
</body>
</html>

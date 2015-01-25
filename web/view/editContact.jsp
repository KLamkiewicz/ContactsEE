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

Hello

<c:out value="${wwws}"/>

</body>
</html>

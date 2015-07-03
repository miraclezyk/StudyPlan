<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<c:if test="${not empty sessionScope.SPRING_SECURITY_SAVED_REQUEST_KEY}">
<p>Please log in to access the page you requested.</p>
</c:if>

<form name="login" action="<c:url value='/j_spring_security_check' />" method="post">
<table>
  <tr>
    <th>Username:</th>
    <td><input type="text" name="j_username"/></td>
  </tr>
  <tr>
    <th>Password:</th>
    <td><input type="password" name="j_password"/></td>
  </tr>
  <tr>
    <td colspan="2">
      <input type="submit" name="login" value="Login" />
    </td>
  </tr>
</table>
</form>
</body>
</html>
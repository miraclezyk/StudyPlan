<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>advisorPage</title>
</head>
<body>
<p><a href="<c:url value='/j_spring_security_logout' />">Logout</a></p>
<p>Welcome,${advisor.username }!</p>
<form action="advisorPage" method="post">
	Search Student:
	<input type="text" name="info">
	<input type="submit" value="search"/>
</form>
tip: two students in the database <br/>
     100002 John Doe  jdoe1@gmail.com <br/>
     100003 Jane Doe  jdoe2@gmail.com
</body>
</html>
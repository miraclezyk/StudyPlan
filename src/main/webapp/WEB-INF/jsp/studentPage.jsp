<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>StudentPage</title>
</head>
<body>
<p><a href="<c:url value='/j_spring_security_logout' />">Logout</a></p>
<p>Welcome,${user.username }!</p>
<form action="main">
	Go to see your plan:
	<input type="submit" value="Go"/>
</form><br/>
<a href="modify">Modify your information</a>
</body>
</html>
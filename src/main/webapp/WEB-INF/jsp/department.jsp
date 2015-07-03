<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Department</title>
</head>
<body>
<p><a href="<c:url value='/j_spring_security_logout' />">Logout</a></p>
<form action="department" method="post">
${department.name }
	<select name="planId">
		<c:forEach var="plan" items="${department.plans }">
			<option value="${plan.id }">${plan.name }</option>
		</c:forEach>	
	</select>
	<input type="submit" value="Browse"/>
</form><br/>
Add A Plan:
<form action="addPlan" method="post">
	Plan Name:<input type="text" name="planName"/><br/>
	<input type="submit" value="Add"/>
</form><br/>
Designate a plan to be the official plan:
<form action="designate">
	<select name="planId">
		<c:forEach var="plan" items="${department.plans }">
			<option value="${plan.id }">${plan.name }</option>
		</c:forEach>	
	</select>
	<input type="submit" value="Done"/>
</form><br/>
Current Official Plan:
${department.currentPlan.name }<br/>
</body>
</html>
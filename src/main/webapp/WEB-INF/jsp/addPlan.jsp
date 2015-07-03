<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add A Plan</title>
</head>
<body>
<form action="addPlan" method="post">
	<select name="departmentId">
		<c:forEach var="department" items="${departments }">
			<option value="${department.id }">${department.name }</option>
		</c:forEach>	
	</select><br/>
	Plan Name:<input type="text" name="planName"/><br/>
	<input type="submit" value="Confirm"/>
</form>
</body>
</html>
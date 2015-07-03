<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>advisorPage</title>
<style type="text/css">
table,th,td{
	border: 1px solid black;
	border-collapse: collapse;
	padding: 10px;
}
</style>
</head>
<body>
<p><a href="<c:url value='/j_spring_security_logout' />">Logout</a></p>
<form action="advisor">
	Search Results:
	<c:if test="${empty users }">no results found.</c:if>
	<c:if test="${!empty users }">
		<table>
			<c:forEach var="student" items="${users }">
				<tr>
					<td>${student.firstName } ${student.lastName }</td>
					<td><a href="detail?username=${student.username }">plan details</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</form>
</body>
</html>
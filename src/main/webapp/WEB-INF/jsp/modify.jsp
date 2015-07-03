<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify</title>
<script>
function checkPassword(input){
	if(!input.value.match("^[A-Za-z0-9]{4,}$")){
		alert("a password must consist of at least 4 characters and include both letters and numbers");
	}
}
</script>
</head>
<body>
<p><a href="<c:url value='/j_spring_security_logout' />">Logout</a></p>

<form action="modify" method="post">
	<p>Change your major:<select name="departmentId">
		<c:forEach var="department" items="${departments }">
			<c:if test="${department.id==user.major.id }">
				<option value="${department.id }" selected>${department.name }</option>
			</c:if>
			<c:if test="${department.id!=user.major.id }">
				<option value="${department.id }">${department.name }</option>
			</c:if>
		</c:forEach>
	</select><br/></p>
	<p>Change your password:<input type="password" name="password" value="${user.password }" onblur="checkPassword(this)"/></p>
	<input type="submit" value="done"/>
</form>

</body>
</html>
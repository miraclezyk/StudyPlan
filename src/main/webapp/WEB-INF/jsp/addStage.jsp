<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add A Stage</title>
<style type="text/css">
table {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 10px;
}
</style>
</head>
<body>

<form:form modelAttribute="stage">
<table>
	<tr>
		<th>Stage:</th>
		<td><form:input path="name"/></td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:right"><input type="submit" value="Add"/></td>
	</tr>	
</table>
</form:form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit A Checkpoint</title>
<style type="text/css">
table {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 10px;
}
</style>
</head>
<body>
<form action="editRunway" method="post">
<table>
			<tr>
				<th>Runway:</th>
			<tr>
				<th>Name:</th>
				<td><input type="text" name="runwayNewName" value="${runwayName }">
					<input type="hidden" name="runwayIndex" value="${runwayId }">
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: right">
				<input type="submit" value="Confirm" /></td>
			</tr>
		</table>
</form>
</body>
</html>
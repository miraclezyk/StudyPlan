<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add A Checkpoint</title>
<style type="text/css">
table {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 10px;
}
</style>
</head>
<body>
<form:form modelAttribute="checkpoint">
<table>
			<tr>
				<th>Runway:</th>
				<td><select name="runwayId">
						<c:forEach var="x" begin="1" end="${plan.runways.size() }">
							<option value="${plan.runways[x-1].id }">${plan.runways[x-1].name }</option>
						</c:forEach>					
				</select></td>
			</tr>
			<tr>
				<th>Stage:</th>
				<td><select name="stageId">
						<c:forEach var="y" begin="1" end="${plan.stages.size() }">
							<option value="${plan.stages[y-1].id }">${plan.stages[y-1].name }</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>Check Point:</th>
				<td><form:input path="description"/></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: right"><input type="submit"
					value="Add" /></td>
			</tr>
		</table>
</form:form>

</body>
</html>
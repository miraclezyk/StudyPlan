<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<form action="editCheckpoint" method="post">
<table>
			<tr>
				<th>Runway:</th>
				<td><select name="runwayIndex">
						<c:forEach var="x" begin="1" end="${plan.runways.size() }">
							<c:choose>
								<c:when test="${plan.runways[x-1].id==runwayId }">
									<option value="${plan.runways[x-1].id }" selected>${plan.runways[x-1].name }</option>
								</c:when>
								<c:otherwise>
									<option value="${plan.runways[x-1].id }">${plan.runways[x-1].name }</option>
								</c:otherwise>
							</c:choose>	
						</c:forEach>					
				</select></td>
			</tr>
			<tr>
				<th>Stage:</th>
				<td><select name="stageIndex">
						<c:forEach var="y" begin="1" end="${plan.stages.size() }">
							<c:choose>
								<c:when test="${plan.stages[y-1].id==stageId }">
									<option value="${plan.stages[y-1].id }" selected>${plan.stages[y-1].name }</option>
								</c:when>
								<c:otherwise>
									<option value="${plan.stages[y-1].id }">${plan.stages[y-1].name }</option>
								</c:otherwise>
							</c:choose>	
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>Check Point:</th>
				<td><input type="text" name="checkpointDes" value="${checkpoint }">
					<input type="hidden" name="checkpointIndex" value="${checkpointId }">
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: right">
				<input type="submit" name="action" value="Delete" />
				<input type="submit" name="action" value="Confirm" /></td>
			</tr>
		</table>
</form>
</body>
</html>
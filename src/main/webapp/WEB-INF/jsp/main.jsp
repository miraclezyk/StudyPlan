
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Page</title>
<style type="text/css">
table {
	font-family: verdana, arial, sans-serif;
	font-size: 12px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
$(function(){
	tableSort();
});

function update(checkpointId){
	$.ajax({
		type:"POST",
		url:"update",
		data:{"checkpointId":checkpointId}
	});
};

function tableSort()
{
	var tbody = $('#content');
	var rows = tbody.children();
	var selectedRow;

	rows.mousedown(function(){
		selectedRow = this;
		tbody.css('cursor', 'move');
		return false;
	});
	rows.mousemove(function(){
		return false;
	});

	rows.mouseup(function(){				
		if(selectedRow)
		{
			if(selectedRow != this && selectedRow.sectionRowIndex < this.sectionRowIndex)
			{
				$.ajax({
					type:"POST",
					url:"order",
					data:{"orginIndex":selectedRow.sectionRowIndex,"preIndex":this.sectionRowIndex,"direction":"down"}
				});
				$(this).after($(selectedRow)).removeClass('mouseOver');
				
			}else if(selectedRow != this && selectedRow.sectionRowIndex > this.sectionRowIndex){
				$.ajax({
					type:"POST",
					url:"order",
					data:{"orginIndex":selectedRow.sectionRowIndex,"preIndex":this.sectionRowIndex,"direction":"up"}
				});
				$(this).before($(selectedRow)).removeClass('mouseOver');
			}
			tbody.css('cursor', 'default');
			selectedRow = null;						
		}								
	});

	rows.hover(
		function(){					
			if(selectedRow && selectedRow != this)
			{
				$(this).addClass('mouseOver');					
			}					
		},
		function(){
			if(selectedRow && selectedRow != this)
			{
				$(this).removeClass('mouseOver');
			}
		}
	);
			
	tbody.mouseover(function(event){
		event.stopPropagation();
	});	
	$('#contain').mouseover(function(event){
		if($(event.relatedTarget).parents('#content'))
		{
			tbody.css('cursor', 'default');
			selectedRow = null;
		}
	});
}


</script>
</head>
<body>
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<a href="department?departmentId=${department.id }">Previous Page</a>
		<a href="<c:url value='/j_spring_security_logout' />">Logout</a>
		<h2>${plan.name }</h2>
		<a href="addStage">Add A Stage</a> |
		<a href="addRunway">Add A Runway</a> |
		<a href="addCheckpoint">Add A Check Point</a>

		<div id='contain'>
			<table>
				<tr>
					<th></th>
					<c:forEach var="x" begin="1" end="${plan.runways.size() }">
						<th>${plan.runways[x-1].name }<a
							href="editRunway?runwayId=${plan.runways[x-1].id }">[Edit]</a>
						</th>
					</c:forEach>
				</tr>

				<tbody id='content'>
					<c:forEach var="y" begin="1" end="${plan.stages.size() }">
						<tr>
							<th>${plan.stages[y-1].name }<a
								href="editStage?stageId=${plan.stages[y-1].id }">[Edit]</a>
							</th>
							<c:forEach var="z" begin="1" end="${plan.runways.size() }">
								<td><c:forEach var="cell" items="${plan.cells }">
										<c:if
											test="${cell.stage.id==plan.stages[y-1].id && cell.runway.id==plan.runways[z-1].id }">
											<c:forEach var="checkpoint" items="${cell.checkpoints }">
										${checkpoint.description }
										<a
													href="editCheckpoint?stageId=${plan.stages[y-1].id }&runwayId=${plan.runways[z-1].id }&checkpointId=${checkpoint.id }">[Edit]</a>
												<br>
											</c:forEach>
										</c:if>
									</c:forEach></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</security:authorize>
	<security:authorize
		access="hasRole('ROLE_ADVISOR') or hasRole('ROLE_STUDENT')">
		<c:if test="${empty advisor }">
			<a href="studentPage">Previous Page</a>
		</c:if>
		<c:if test="${!empty advisor }">
			<a href="advisorPage">Previous Page</a>
		</c:if>
		<a href="<c:url value='/j_spring_security_logout' />">Logout</a>
		<h2>${plan.name }</h2>
		<table>
			<tr>
				<th></th>
				<c:forEach var="x" begin="1" end="${plan.runways.size() }">
					<th>${plan.runways[x-1].name }</th>
				</c:forEach>
			</tr>

			<c:forEach var="y" begin="1" end="${plan.stages.size() }">
				<tr>
					<th>${plan.stages[y-1].name }</th>
					<c:forEach var="z" begin="1" end="${plan.runways.size() }">
						<td><c:forEach var="cell" items="${plan.cells }">
								<c:if
									test="${cell.stage.id==plan.stages[y-1].id && cell.runway.id==plan.runways[z-1].id }">
									<c:forEach var="checkpoint" items="${cell.checkpoints }">
										<c:set var="on" value="false"></c:set>
										<c:forEach var="ck" items="${user.checkpoints }">
											<c:if test="${ck.id==checkpoint.id }">
												<input type="checkbox"
													onclick="javascript:update(${checkpoint.id})" checked />
												<c:set var="on" value="true"></c:set>
											</c:if>
										</c:forEach>
										<c:if test="${on==false}">
											<input type="checkbox"
												onclick="javascript:update(${checkpoint.id})" />
										</c:if>	
										${checkpoint.description }<br>
									</c:forEach>
								</c:if>
							</c:forEach></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</security:authorize>
</body>
</html>
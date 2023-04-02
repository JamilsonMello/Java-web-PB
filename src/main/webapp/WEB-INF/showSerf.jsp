<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
    <%@ page isErrorPage="true"  %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="d-flex flex-row align-items-center bg-success text-white">
	<div class="d-flex flex-column w-auto">
	
		<h4 class="text-danger">Logged In:</h4>
		<h4>${userLog.firstName} ${userLog.lastName}</h4>
		<a class="btn btn-info" href="/logout">Logout</a>
	</div>

<div class="d-flex justify-content-center flex-column align-items-center col-11 w-75">
<h2>${project.title}'s Info</h2>
<a class="btn btn-dark mb-1" href="/dashboard">Home</a>

<%--Edit ------------- Delete--%>
<c:if test="${project.owners.contains(userLog)}">

	<div class="container d-flex flex-row justify-content-center">
		<a href="/edit/serf/${project.id}" class="btn btn-warning">Edit</a>
		<button onclick="location.href=`/delete/serf/${project.id}`" class="btn btn-danger">Delete</button>
	</div>
</c:if>

	<hr/>
</div>
</div>

<div class="container d-flex flex-row">
<div class="container d-flex flex-column justify-content-center align-items-center">
	<table class="table table-striped mt-2">
		<tbody>
			<tr>
				<th scope="row">Title:</th>
				<td>${project.title}</<td>
			</tr>
			<tr>
				<th scope="row">Due Date:</th>
				<td><fmt:formatDate value="${project.date}" pattern="MMM dd, yyyy"/><td>
			</tr>
			<tr>
				<th scope="row">Description:</th>	
				<td>${project.description}</td>
			</tr>
			
			<c:forEach var="x" items="${project.owners}">
			<tr>
				<th scope="row">Owner:</th>	
				<td><a href="/user/show/${x.id}">${x.firstName} ${x.lastName}</a></td>
			</tr>
			</c:forEach>
			
		</tbody>
	</table>
	
	

	<a href="/newSideForm/${project.id}">Add a task</a>
	



</div>
</div>

<div class="container d-flex flex-column justify-content-center align-items-center">
<%--Show Sides--%>
<h3>Tasks:</h3>
 	<ol>
		<c:forEach items="${project.tasks}" var="x">
		<li>${x.taskName}</li>
		</c:forEach>
	</ol>

</div>



</body>
</html>
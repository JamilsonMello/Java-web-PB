<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
    <%@ page isErrorPage="true"  %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
	<div class="d-flex flex-row align-items-center bg-success text-white">
	<div class="d-flex flex-column w-auto">
	
		<h4 class="text-danger">Logged In:</h4>
		<h4>${userLog.firstName} ${userLog.lastName}</h4>
		<a class="btn btn-info mb-1" href="/logout">Logout</a>
	</div>

<div class="d-flex justify-content-center flex-column align-items-center col-11 w-75">
<h2>Add Something</h2>
<a class="btn btn-dark mb-1" href="/dashboard">Home</a>

</div>
</div>
	
	
	<div class="container d-flex flex-column justify-content-center align-items-center">
		<h1>Add a task for ${main.title}</h1>
		<form:form class='card p-3 bg-light' action="/addTask/" method="POST" modelAttribute="task">
		
			<div class="form-group">
				<form:label path="taskName">Task:</form:label>
				<form:input path="taskName" />
				<br/>
				<form:errors path="taskName" class="text-danger" />
			</div>

 		 	<form:input type="hidden" value="${main.id}" path="mainOwner" />
		 	<form:input type="hidden" value="${userLog.id}" path="userTask" />
		 	
		 	<input type="hidden" name="main" value="${main.id}"/>
			
	
			<button>Create</button>
	
		</form:form>
		
		<c:forEach var="x" items="${main.tasks}">
		    <p>${x.userTask.firstName} Added: ${x.taskName}</p>

		    
		</c:forEach>

	</div>
</body>
</html>
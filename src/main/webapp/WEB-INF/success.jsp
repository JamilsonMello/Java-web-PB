<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
    <%@ page isErrorPage="true"  %>
	<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Success</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div class="container d-flex justify-content-center align-items-center">
			<div class="card">
				<div class="card-header">
					Cadastro realizado com sucesso!
				</div>
				<div class="card-body">
					<p>Nome: ${newUser.firstName} ${newUser.lastName}</p>
					<p>Email: ${newUser.email}</p>
					<a href="/">Ir para a página de Login</a>
				</div>
			</div>
		</div>
	</body>
	</html>
	
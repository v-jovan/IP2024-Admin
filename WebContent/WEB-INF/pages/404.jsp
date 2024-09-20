<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="favicon.ico?" type="image/x-icon" />
<title>404 - Page Not Found</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.container {
	text-align: center;
	margin-top: 10%;
}
</style>
</head>
<body>
	<div class="container">
		<h1 class="display-1">404</h1>
		<p class="lead">Ufffff. Tražena stranica trenutno ne postoji.
			Vidimo se kad se kreira!</p>
		<a href="${pageContext.request.contextPath}/dashboard"
			class="btn btn-secondary">Početna</a>
	</div>
</body>
</html>

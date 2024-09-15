<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="ip.project.dto.CategoryDTO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ažuriranje kategorije</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="d-flex">
		<jsp:include page="/WEB-INF/components/sidebar.jsp" />

		<div class="container flex-grow-1 mt-5">
			<h1>Ažuriranje kategorije</h1>


			<%
			CategoryDTO category = (CategoryDTO) request.getAttribute("category");
			%>

			<form action="<%=request.getContextPath()%>/update-category"
				method="post">
				<input type="hidden" name="id" value="<%=category.getId()%>">

				<div class="my-3">
					<label for="name" class="form-label">Naziv kategorije:</label> <input
						type="text" class="form-control" id="name" name="name"
						value="<%=category.getName()%>" required>
				</div>

				<div class="mb-3">
					<label for="description" class="form-label">Opis
						kategorije:</label>
					<textarea class="form-control" id="description" name="description"><%=category.getDescription()%></textarea>
				</div>
				<div class="d-flex justify-content-end  mt-4">
					<button type="submit" class="btn btn-secondary">Ažuriraj
						kategoriju</button>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

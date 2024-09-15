<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="ip.project.dto.CategoryDTO"%>
<%@ page import="ip.project.dto.AttributeDTO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ažuriranje atributa</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="d-flex">
		<jsp:include page="/WEB-INF/components/sidebar.jsp" />

		<div class="container flex-grow-1 mt-5">
			<h1>Ažuriranje atributa</h1>

			<%
			AttributeDTO attribute = (AttributeDTO) request.getAttribute("attribute");
			List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");
			%>

			<form action="<%=request.getContextPath()%>/update-attribute"
				method="post">
				<input type="hidden" name="id" value="<%=attribute.getId()%>">

				<div class="my-4">
					<label for="name" class="form-label">Naziv atributa:</label> <input
						type="text" class="form-control" id="name" name="name"
						value="<%=attribute.getName()%>" required>
				</div>

				<div class="mb-4">
					<label for="description" class="form-label">Opis atributa:</label>
					<textarea class="form-control" id="description" name="description"
						required><%=attribute.getDescription()%></textarea>
				</div>

				<div class="mb-4">
					<label for="category" class="form-label">Kategorija:</label> <select
						class="form-select" id="category" name="categoryId" required>
						<%
						for (CategoryDTO category : categories) {
						%>
						<option value="<%=category.getId()%>"
							<%=category.getId().equals(attribute.getCategory().getId()) ? "selected" : ""%>>
							<%=category.getName()%>
						</option>
						<%
						}
						%>
					</select>
				</div>

				<div class="d-flex justify-content-end mt-4">
					<button type="submit" class="btn btn-secondary">Ažuriraj
						atribut</button>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

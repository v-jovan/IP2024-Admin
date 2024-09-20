<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="ip.project.dto.AttributeDTO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="favicon.ico?" type="image/x-icon" />
<title>Kreiranje vrijednosti atributa</title>
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
			<h1>Kreiranje vrijednosti atributa</h1>

			<form action="<%=request.getContextPath()%>/create-attribute-value"
				method="post">
				<div class="my-4">
					<label for="attribute" class="form-label">Atribut:</label> <select
						class="form-select" id="attribute" name="attributeId" required>
						<%
						List<AttributeDTO> attributes = (List<AttributeDTO>) request.getAttribute("attributes");
						for (AttributeDTO attribute : attributes) {
						%>
						<option value="<%=attribute.getId()%>"><%=attribute.getName()%></option>
						<%
						}
						%>
					</select>
				</div>
				<div class="mb-4">
					<input placeholder="Vrijednosti atributa" type="text"
						class="form-control" id="name" name="name" required>
				</div>

				<div class="d-flex justify-content-end mt-4">
					<button type="submit" class="btn btn-secondary">+ Kreiraj
						vrijednost atributa</button>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

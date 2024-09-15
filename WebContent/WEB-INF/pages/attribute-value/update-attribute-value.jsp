<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="ip.project.dto.AttributeValueDTO"%>
<%@ page import="ip.project.dto.AttributeDTO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ažuriranje vrijednosti atributa</title>
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
			<h1>Ažuriranje vrijednosti atributa</h1>

			<%
			AttributeValueDTO attributeValue = (AttributeValueDTO) request.getAttribute("attributeValue");
			List<AttributeDTO> attributes = (List<AttributeDTO>) request.getAttribute("attributes");
			%>

			<form action="<%=request.getContextPath()%>/update-attribute-value"
				method="post">
				<input type="hidden" name="id" value="<%=attributeValue.getId()%>">

				<div class="my-4">
					<label for="attribute" class="form-label">Atribut:</label> <select
						class="form-select" id="attribute" name="attributeId" required>
						<%
						for (AttributeDTO attribute : attributes) {
						%>
						<option value="<%=attribute.getId()%>"
							<%=attribute.getId().equals(attributeValue.getAttribute().getId()) ? "selected" : ""%>>
							<%=attribute.getName()%>
						</option>
						<%
						}
						%>
					</select>
				</div>

				<div class="mb-4">
					<label for="name" class="form-label">Vrijednost atributa:</label> <input
						type="text" class="form-control" id="name" name="name"
						value="<%=attributeValue.getName()%>" required>
				</div>



				<div class="d-flex justify-content-end mt-4">
					<button type="submit" class="btn btn-secondary">Ažuriraj
						vrijednost atributa</button>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

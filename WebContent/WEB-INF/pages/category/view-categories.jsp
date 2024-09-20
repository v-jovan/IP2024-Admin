<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="ip.project.dto.CategoryDTO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="favicon.ico?" type="image/x-icon" />
<title>Pregled kategorija</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css"
	rel="stylesheet">

</head>
<body>
	<div class="d-flex">
		<jsp:include page="/WEB-INF/components/sidebar.jsp" />

		<div class="container flex-grow-1 mt-5">
			<h1>Pregled kategorija</h1>

			<table class="table table-striped mt-5">
				<thead>
					<tr>
						<th>ID</th>
						<th>Naziv</th>
						<th>Opis</th>
						<th>Akcije</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<CategoryDTO> categories = null;
					try {
						categories = (List<CategoryDTO>) request.getAttribute("categories");
					} catch (ClassCastException e) {
						e.printStackTrace();
					}

					if (categories != null) {
						for (CategoryDTO category : categories) {
					%>
					<tr>
						<td><%=category.getId()%></td>
						<td><%=category.getName()%></td>
						<td><%=category.getDescription()%></td>
						<td><a
							href="<%=request.getContextPath()%>/update-category?id=<%=category.getId()%>"
							class="btn btn-sm btn-warning"> <i class="bi bi-pencil"
								style="color: black;"> </i>
						</a>
							<button class="btn btn-sm btn-danger"
								onclick="showDeleteModal('<%=request.getContextPath()%>/delete-category?id=<%=category.getId()%>');">
								<i class="bi bi-trash" style="color: white;"></i>
							</button></td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="4" class="text-center">Nema dostupnih
							kategorija.</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>

			<jsp:include page="/WEB-INF/components/confirmDeleteModal.jsp">
				<jsp:param name="title" value="Potvrda brisanja" />
				<jsp:param name="message"
					value="Da li ste sigurni da želite obrisati ovu kategoriju?" />
				<jsp:param name="confirmUrl" value="" />
			</jsp:include>
			<a href="<%=request.getContextPath()%>/create-category"
				class="btn btn-secondary mt-3">+ Dodaj kategoriju</a>

		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		function showDeleteModal(deleteUrl) {
			document.getElementById('confirmDeleteBtn').setAttribute('href',
					deleteUrl);
			var modal = new bootstrap.Modal(document
					.getElementById('confirmDeleteModal'));
			modal.show();
		}
	</script>

</body>
</html>

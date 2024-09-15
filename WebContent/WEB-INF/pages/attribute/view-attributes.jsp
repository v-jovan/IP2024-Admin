<%@page import="ip.project.dto.AttributeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="ip.project.dto.AttributeDTO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pregled atributa</title>
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
			<h1>Pregled atributa</h1>

			<table class="table table-striped mt-5">
				<thead>
					<tr>
						<th>ID</th>
						<th>Kategorija</th>
						<th>Naziv</th>
						<th>Opis</th>
						<th>Akcije</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<AttributeDTO> attributes = null;
					try {
						attributes = (List<AttributeDTO>) request.getAttribute("attributes");
					} catch (ClassCastException e) {
						e.printStackTrace();
					}

					if (attributes != null) {
						for (AttributeDTO attribute : attributes) {
					%>
					<tr>
						<td><%=attribute.getId()%></td>
						<td><%=attribute.getCategory() != null ? attribute.getCategory().getName() : "N/A"%></td>
						<td><%=attribute.getName()%></td>
						<td><%=attribute.getDescription()%></td>
						<td><a
							href="<%=request.getContextPath()%>/update-attribute?id=<%=attribute.getId()%>"
							class="btn btn-sm btn-warning"> <i class="bi bi-pencil"
								style="color: black;"> </i>
						</a>
							<button class="btn btn-sm btn-danger"
								onclick="showDeleteModal('<%=request.getContextPath()%>/delete-attribute?id=<%=attribute.getId()%>');">
								<i class="bi bi-trash" style="color: white;"></i>
							</button></td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="5" class="text-center">Nema dostupnih atributa.</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>

			<jsp:include page="/WEB-INF/components/confirmDeleteModal.jsp">
				<jsp:param name="title" value="Potvrda brisanja" />
				<jsp:param name="message"
					value="Da li ste sigurni da želite obrisati ovaj atribut?" />
				<jsp:param name="confirmUrl" value="" />
			</jsp:include>
			<a href="<%=request.getContextPath()%>/create-attribute"
				class="btn btn-secondary mt-3">+ Dodaj atribut</a>

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

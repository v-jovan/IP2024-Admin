<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="ip.project.dto.AttributeValueDTO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="favicon.ico?" type="image/x-icon" />
<title>Pregled vrijednosti atributa</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css"
	rel="stylesheet">

<style type="text/css">
.pagination .page-link {
	background: #6C757D;
	color: white;
}

.page-item.active .page-link {
	background: white;
	border-color: #6C757D;
	color: #6C757D;
}
</style>
</head>
<body>
	<div class="d-flex">
		<jsp:include page="/WEB-INF/components/sidebar.jsp" />

		<div class="container flex-grow-1 mt-5">
			<h1>Pregled vrijednosti atributa</h1>

			<table class="table table-striped mt-5">
				<thead>
					<tr>
						<th>ID</th>
						<th>Atribut</th>
						<th>Naziv</th>
						<th>Akcije</th>
					</tr>
				</thead>
				<tbody id="tableBody">
					<%
					List<AttributeValueDTO> attributeValues = null;
					try {
						attributeValues = (List<AttributeValueDTO>) request.getAttribute("attributeValues");
					} catch (ClassCastException e) {
						e.printStackTrace();
					}

					if (attributeValues != null) {
						for (AttributeValueDTO attributeValue : attributeValues) {
					%>
					<tr>
						<td><%=attributeValue.getId()%></td>
						<td><%=attributeValue.getAttribute() != null ? attributeValue.getAttribute().getName() : "N/A"%></td>
						<td><%=attributeValue.getName()%></td>
						<td><a
							href="<%=request.getContextPath()%>/update-attribute-value?id=<%=attributeValue.getId()%>"
							class="btn btn-sm btn-warning"> <i class="bi bi-pencil"
								style="color: black;"> </i>
						</a>
							<button class="btn btn-sm btn-danger"
								onclick="showDeleteModal('<%=request.getContextPath()%>/delete-attribute-value?id=<%=attributeValue.getId()%>');">
								<i class="bi bi-trash" style="color: white;"></i>
							</button></td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="5" class="text-center">Nema dostupnih
							vrijednosti atributa.</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>

			<nav>
				<ul class="pagination justify-content-center pagination-sm"
					id="pagination">
				</ul>
			</nav>

			<jsp:include page="/WEB-INF/components/confirmDeleteModal.jsp">
				<jsp:param name="title" value="Potvrda brisanja" />
				<jsp:param name="message"
					value="Da li ste sigurni da želite obrisati ovu vrijednost atributa?" />
				<jsp:param name="confirmUrl" value="" />
			</jsp:include>
			<a href="<%=request.getContextPath()%>/create-attribute-value"
				class="btn btn-secondary mt-3">+ Dodaj vrijednost atributa</a>

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
		
		// custom pagination with bootstrap elements
		const rowsPerPage = 10;
		const rows = document.querySelectorAll('#tableBody tr');
		let currentPage = 1;

		function displayRows(page) {
			const start = (page - 1) * rowsPerPage;
			const end = start + rowsPerPage;

			rows.forEach((row, index) => {
				row.style.display = (index >= start && index < end) ? '' : 'none';
			});

			currentPage = page;
			renderPagination();
		}

		function renderPagination() {
			const pageCount = Math.ceil(rows.length / rowsPerPage);
			const pagination = document.getElementById('pagination');
			pagination.innerHTML = '';

			for (let i = 1; i <= pageCount; i++) {
				const li = document.createElement('li');
				li.className = 'page-item ' + (i === currentPage ? 'active' : '');
				li.innerHTML = '<a class="page-link" href="#">' + i + '</a>';
				li.addEventListener('click', () => displayRows(i));
				pagination.appendChild(li);
			}
		}

		displayRows(1);
		
	</script>
</body>
</html>

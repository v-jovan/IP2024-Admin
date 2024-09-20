<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="ip.project.dto.LogDTO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pregled logova</title>
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

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
%>
<body>
	<div class="d-flex">
		<jsp:include page="/WEB-INF/components/sidebar.jsp" />

		<div class="container flex-grow-1 mt-5">
			<h1>Pregled logova korisnika</h1>

			<table class="table table-striped mt-5">
				<thead>
					<tr>
						<th>ID</th>
						<th>Korisnik</th>
						<th>Akcija</th>
						<th>Datum i vrijeme</th>
					</tr>
				</thead>
				<tbody id="tableBody">
					<%
					List<LogDTO> logs = null;
					try {
						logs = (List<LogDTO>) request.getAttribute("logs");
					} catch (ClassCastException e) {
						e.printStackTrace();
					}

					if (logs != null && !logs.isEmpty()) {
						String formattedTimestamp = null;
						for (LogDTO log : logs) {
							if (log.getTimestamp() != null) {
						formattedTimestamp = sdf.format(log.getTimestamp());
							}
					%>
					<tr>
						<td><%=log.getId()%></td>
						<td><%=log.getUser()%></td>
						<td><%=log.getAction()%></td>
						<td><%=formattedTimestamp%></td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="4" class="text-center"><i>Nema dostupnih
								logova.</i></td>
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

		    const maxPagesToShow = 5;
		    let startPage = Math.max(1, currentPage - Math.floor(maxPagesToShow / 2));
		    let endPage = Math.min(pageCount, startPage + maxPagesToShow - 1);

		    const prevLi = document.createElement('li');
		    prevLi.className = 'page-item ' + (currentPage === 1 ? 'disabled' : '');
		    prevLi.innerHTML = '<a class="page-link" href="#"><</a>';
		    prevLi.addEventListener('click', () => {
		        if (currentPage > 1) {
		            displayRows(currentPage - 1);
		        }
		    });
		    pagination.appendChild(prevLi);

		    for (let i = startPage; i <= endPage; i++) {
		        const li = document.createElement('li');
		        li.className = 'page-item ' + (i === currentPage ? 'active' : '');
		        li.innerHTML = '<a class="page-link" href="#">' + i + '</a>';
		        li.addEventListener('click', () => displayRows(i));
		        pagination.appendChild(li);
		    }

		    const nextLi = document.createElement('li');
		    nextLi.className = 'page-item ' + (currentPage === pageCount ? 'disabled' : '');
		    nextLi.innerHTML = '<a class="page-link" href="#">></a>';
		    nextLi.addEventListener('click', () => {
		        if (currentPage < pageCount) {
		            displayRows(currentPage + 1);
		        }
		    });
		    pagination.appendChild(nextLi);
		}


		displayRows(1);
		
	</script>
</body>
</html>

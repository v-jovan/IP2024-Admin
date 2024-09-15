<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Kreiranje kategorije</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>
	<div class="d-flex">
		<jsp:include page="/WEB-INF/components/sidebar.jsp" />

		<div class="container flex-grow-1 mt-5">
			<h1>Kreiranje kategorije</h1>
			<form action="<%=request.getContextPath()%>/create-category"
				method="post">
				<div class="my-4">
					<input placeholder="Naziv kategorije" type="text"
						class="form-control" id="name" name="name" required>
				</div>
				<div class="mb-4">
					<textarea placeholder="Opis kategorije" class="form-control"
						id="description" name="description"></textarea>
				</div>
				<div class="d-flex justify-content-end  mt-4">
					<button type="submit" class="btn btn-secondary">+ Kreiraj
						kategoriju</button>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

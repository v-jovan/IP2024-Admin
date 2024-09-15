<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="ip.project.dto.CityDTO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Kreiranje korisnika</title>
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
			<h1>Kreiranje korisnika</h1>

			<%
			String errorMessage = (String) request.getAttribute("errorMessage");
			if (errorMessage != null) {
			%>
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">
				<%=errorMessage%>
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
			<%
			}
			%>

			<form action="<%=request.getContextPath()%>/create-user"
				method="post">
				<div class="my-4">
					<input placeholder="Korisnicko ime" type="text"
						class="form-control" id="username" name="username" required>
				</div>
				<div class="mb-4">
					<input placeholder="Lozinka" type="password" class="form-control"
						id="password" name="password" required>
				</div>
				<div class="mb-4">
					<input placeholder="E-Mail" type="email" class="form-control"
						id="email" name="email" required>
				</div>
				<div class="mb-4">
					<input placeholder="Ime" type="text" class="form-control"
						id="first_name" name="first_name" required>
				</div>
				<div class="mb-4">
					<input placeholder="Prezime" type="text" class="form-control"
						id="last_name" name="last_name" required>
				</div>
				<div class="mb-4">
					<textarea placeholder="Biografija" class="form-control"
						id="biography" name="biography"></textarea>
				</div>
				<div class="mb-4">
					<label for="role" class="form-label">Uloga:</label> <select
						class="form-select" id="role" name="role" required>
						<option value="ADMIN">Administrator</option>
						<option value="INSTRUCTOR">Savjetnik</option>
						<option value="USER">Korisnik</option>
					</select>
				</div>
				<div class="mb-4">
					<label for="city_id" class="form-label">Grad</label> <select
						class="form-select" id="city_id" name="city_id" required>
						<%
						List<CityDTO> cities = (List<CityDTO>) request.getAttribute("cities");
						for (CityDTO city : cities) {
						%>
						<option value="<%=city.getId()%>"><%=city.getName()%></option>
						<%
						}
						%>
					</select>
				</div>
				<div class="d-flex justify-content-end  mt-4">
					<button type="submit" class="btn btn-secondary">+ Kreiraj
						korisnika</button>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

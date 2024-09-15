<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="ip.project.dto.CityDTO"%>
<%@ page import="ip.project.dto.UserDTO"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ažuriranje korisnika</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="d-flex">
		<jsp:include page="/WEB-INF/components/sidebar.jsp" />

		<div class="container flex-grow-1 mt-5">
			<h1>Ažuriranje korisnika</h1>

			<%
			if (request.getAttribute("errorMessage") != null) {
			%>
			<div class="alert alert-danger" role="alert">
				<%=request.getAttribute("errorMessage")%>
			</div>
			<%
			}
			%>

			<%
			UserDTO user = (UserDTO) request.getAttribute("user");
			List<CityDTO> cities = (List<CityDTO>) request.getAttribute("cities");
			%>

			<form action="<%=request.getContextPath()%>/update-user"
				method="post">
				<input type="hidden" name="id" value="<%=user.getId()%>">

				<div class="my-3">
					<label for="username" class="form-label">Korisničko ime:</label> <input
						type="text" class="form-control" id="username" name="username"
						value="<%=user.getUsername()%>" required>
				</div>

				<div class="mb-3">
					<label for="password" class="form-label">Nova lozinka:</label> <input
						type="password" class="form-control" id="password" name="password"
						placeholder="Ostavite prazno ako ne želite mijenjati lozinku">

				</div>

				<div class="mb-3">
					<label for="email" class="form-label">E-Mail:</label> <input
						type="email" class="form-control" id="email" name="email"
						value="<%=user.getEmail()%>" required>
				</div>

				<div class="mb-3">
					<label for="first_name" class="form-label">Ime:</label> <input
						type="text" class="form-control" id="first_name" name="first_name"
						value="<%=user.getFirstName()%>" required>
				</div>

				<div class="mb-3">
					<label for="last_name" class="form-label">Prezime:</label> <input
						type="text" class="form-control" id="last_name" name="last_name"
						value="<%=user.getLastName()%>" required>
				</div>

				<div class="mb-3">
					<label for="biography" class="form-label">Biografija:</label>
					<textarea class="form-control" id="biography" name="biography"><%=user.getBiography()%></textarea>
				</div>

				<div class="mb-3">
					<label for="role" class="form-label">Uloga:</label> <select
						class="form-select" id="role" name="role" required>
						<option value="ADMIN"
							<%=user.getRole().equals("ADMIN") ? "selected" : ""%>>Administrator</option>
						<option value="USER"
							<%=user.getRole().equals("USER") ? "selected" : ""%>>Korisnik</option>
					</select>
				</div>

				<div class="mb-3">
					<label for="city_id" class="form-label">Grad:</label> <select
						class="form-select" id="city_id" name="city_id" required>
						<%
						for (CityDTO city : cities) {
						%>
						<option value="<%=city.getId()%>"
							<%=city.getId() == user.getCityId() ? "selected" : ""%>>
							<%=city.getName()%>
						</option>
						<%
						}
						%>
					</select>
					<div class="d-flex justify-content-end  mt-4">
						<button type="submit" class="btn btn-secondary">Ažuriraj
							korisnika</button>
					</div>
				</div>


			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

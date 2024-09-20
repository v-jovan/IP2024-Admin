<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="svg" href="/assets/logo.svg">
<title>Prijava administratora</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
.full-height {
	height: 100vh;
}

.login-logo {
	height: 5rem;
	width: auto;
	margin: 1rem
}

.center-logo {
	display: flex;
	justify-content: center;
	margin-bottom: 1.5rem;
}
</style>

</head>
<body>
	<div
		class="container d-flex align-items-center justify-content-center full-height">
		<div class="row w-100">
			<div class="col-md-6 offset-md-3 col-lg-4 offset-lg-4">
				<div class="card shadow-sm">
					<div class="card-body">
						<div class="center-logo">
							<img src="${pageContext.request.contextPath}/assets/logo.svg"
								class="login-logo" alt="Logo">
						</div>
						<h5 class="mb-5 text-center">Administratorski panel</h5>

						<%
						Boolean notification = (Boolean) session.getAttribute("notification");
						if (notification != null && notification) {
						%>
						<div class="alert alert-danger text-center" role="alert">Pogrešni
							kredencijali, pokušajte ponovo!</div>
						<%
						session.setAttribute("notification", false);
						}
						%>

						<form action="login" method="post">
							<div class="mb-4">
								<input placeholder="Username" type="text" class="form-control"
									id="username" name="username" required>
							</div>
							<div class="mb-5">
								<input placeholder="Password" type="password"
									class="form-control" id="password" name="password" required>
							</div>
							<div class="d-grid mb-3">
								<button type="submit" class="btn btn-secondary">Prijava</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

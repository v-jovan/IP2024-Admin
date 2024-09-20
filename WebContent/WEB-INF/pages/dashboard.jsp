<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dashboard</title>
<link rel="shortcut icon" href="favicon.ico?" type="image/x-icon" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
.dashboard-content {
	display: flex;
	flex-direction: column;
	justify-content: center;
	gap: 5rem;
	align-items: center;
	height: 100vh;
	text-align: center;
	opacity: 0.5;
	max-width: 40rem;
}
</style>

</head>
<body>
	<div class="d-flex">
		<jsp:include page="/WEB-INF/components/sidebar.jsp" />
		<div
			class="flex-grow-1 d-flex justify-content-center align-items-center">
			<div class="container dashboard-content">
				<h1>
					Dobrodošli na <b>Administratorski kontrolni panel</b>!
				</h1>
				<br>
				<p>Ovo je početna stranica gde možete upravljati korisnicima,
					kategorijama, atributima i vrijednostima atributa. Dostupna je i
					mogućnost pregleda logova.</p>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

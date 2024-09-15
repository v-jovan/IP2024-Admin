<%@ page import="ip.project.dto.UserDTO"%>
<style>
.sidebar {
	width: 18rem;
	background-color: #f8f9fa;
	height: 100vh;
	padding: 1rem;
}

.sidebar .nav-link {
	color: #000;
	border-radius: 1.5rem;
	transition: background-color 0.3s ease;
}

.sidebar .nav-link:hover {
	background-color: #e2e6ea;
	font-weight: bold;
}

.sidebar .nav-link.active {
	background-color: #e2e6ea;
}

.sidebar .logo-container {
	display: flex;
	justify-content: center;
}

.logo {
	height: 5rem;
	width: auto;
}

.user-info {
	margin-top: auto;
	margin-bottom: 3rem;
	text-align: center;
}

.logout-link {
	color: #007bff;
	cursor: pointer;
	text-decoration: none;
}

.logout-link:hover {
	text-decoration: underline;
}
</style>

<style>
.sidebar {
	width: 18rem;
	background-color: #f8f9fa;
	height: 100vh;
	padding: 1rem;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

.sidebar .nav-link {
	color: #000;
	border-radius: 1.5rem;
	transition: background-color 0.3s ease;
}

.sidebar .nav-link:hover {
	background-color: #e2e6ea;
	font-weight: bold;
}

.sidebar .nav-link.active {
	background-color: #e2e6ea;
}

.sidebar .logo-container {
	display: flex;
	justify-content: center;
}

.logo {
	height: 5rem;
	width: auto;
}

.user-info {
	margin-top: auto;
	margin-bottom: 3rem;
	text-align: center;
}

.logout-link {
	color: #007bff;
	cursor: pointer;
	text-decoration: none;
}

.logout-link:hover {
	text-decoration: underline;
}
</style>

<div class="d-flex flex-column flex-shrink-0 sidebar">
	<div>
		<div class="logo-container">
			<img src="${pageContext.request.contextPath}/assets/logo.svg"
				class="logo my-2" alt="Logo">
		</div>
		<hr>
		<ul class="nav nav-pills flex-column gap-2 mb-auto">
			<li><a href="${pageContext.request.contextPath}/view-users"
				class="nav-link">Korisnici</a></li>
			<li><a href="${pageContext.request.contextPath}/view-categories"
				class="nav-link">Kategorije</a></li>
			<li><a href="${pageContext.request.contextPath}/view-attributes"
				class="nav-link">Atributi</a></li>
			<li><a
				href="${pageContext.request.contextPath}/view-attribute-values"
				class="nav-link">Vrijednosti Atributa</a></li>
			<li><a href="${pageContext.request.contextPath}/view-logs"
				class="nav-link">Logovi</a></li>
		</ul>
	</div>
	<div class="user-info">
		<%
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user != null) {
		%>
		<p>
			<strong><%=user.getFullname()%></strong>
		</p>
		[<a href="${pageContext.request.contextPath}/logout"
			class="logout-link">Odjava</a>]
		<%
		}
		%>
	</div>
</div>


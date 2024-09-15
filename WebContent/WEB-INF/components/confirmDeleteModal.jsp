<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String title = request.getParameter("title");
String message = request.getParameter("message");
String confirmUrl = request.getParameter("confirmUrl");
%>

<div class="modal fade" id="confirmDeleteModal" tabindex="-1"
	aria-labelledby="confirmDeleteLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="confirmDeleteLabel"><%=title%></h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body"><%=message%></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Otkaži</button>
				<a href="<%=confirmUrl%>" id="confirmDeleteBtn"
					class="btn btn-danger">Obriši</a>
			</div>
		</div>
	</div>
</div>

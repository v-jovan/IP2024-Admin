package ip.project.controller.user;

import ip.project.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));

		userDAO.deleteUser(userId);

		response.sendRedirect(request.getContextPath() + "/view-users");
	}
}

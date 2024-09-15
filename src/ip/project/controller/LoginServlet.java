package ip.project.controller;

import ip.project.dto.UserDTO;
import ip.project.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 6078848547625747982L;
	private UserDAO userDAO;

	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			response.sendRedirect(request.getContextPath() + "/dashboard");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserDTO user = userDAO.authenticate(username, password);

		if (user != null && "ADMIN".equals(user.getRole())) {
			request.getSession().setAttribute("notification", false);
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/dashboard");
		} else {
			request.getSession().setAttribute("notification", true);
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
		}
	}
}

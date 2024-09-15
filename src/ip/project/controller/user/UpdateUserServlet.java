package ip.project.controller.user;

import ip.project.dao.CityDAO;
import ip.project.dao.UserDAO;
import ip.project.dto.CityDTO;
import ip.project.dto.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/update-user")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private CityDAO cityDAO;

	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO();
		cityDAO = new CityDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));

		UserDTO user = userDAO.getUserById(userId);
		request.setAttribute("user", user);

		List<CityDTO> cities = cityDAO.getAllCities();
		request.setAttribute("cities", cities);

		request.getRequestDispatcher("/WEB-INF/pages/user/update-user.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String role = request.getParameter("role");
		int cityId = Integer.parseInt(request.getParameter("city_id"));
		String biography = request.getParameter("biography");

		String password = request.getParameter("password");
		boolean updatePassword = (password != null && !password.isEmpty());

		UserDTO user = new UserDTO(id, email, username, firstName, lastName, role, cityId, biography);
		if (updatePassword) {
			user.setPassword(password);
		}

		try {
			userDAO.updateUser(user, updatePassword);
			response.sendRedirect(request.getContextPath() + "/view-users");
		} catch (SQLException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("user", user);
			List<CityDTO> cities = cityDAO.getAllCities();
			request.setAttribute("cities", cities);
			request.getRequestDispatcher("/WEB-INF/pages/user/update-user.jsp").forward(request, response);
		}
	}
}

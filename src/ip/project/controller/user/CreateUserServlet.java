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
import java.util.List;

@WebServlet("/create-user")
public class CreateUserServlet extends HttpServlet {
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
		List<CityDTO> cities = cityDAO.getAllCities();
		request.setAttribute("cities", cities);

		request.getRequestDispatcher("/WEB-INF/pages/user/create-user.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String role = request.getParameter("role");
		String biography = request.getParameter("biography");
		int cityId = Integer.parseInt(request.getParameter("city_id"));

		UserDTO newUser = new UserDTO(username, password, role);
		newUser.setEmail(email);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setBiography(biography);
		newUser.setCityId(cityId);

		try {
			userDAO.createUser(newUser);
			response.sendRedirect(request.getContextPath() + "/view-users");
		} catch (Exception e) {
			List<CityDTO> cities = cityDAO.getAllCities();
			request.setAttribute("cities", cities);
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/pages/user/create-user.jsp").forward(request, response);
		}
	}

}

package ip.project.controller.category;

import java.io.IOException;
import java.sql.SQLException;

import ip.project.dao.CategoryDAO;
import ip.project.dto.CategoryDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update-category")
public class UpdateCategoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;

	@Override
	public void init() throws ServletException {
		categoryDAO = new CategoryDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));

		CategoryDTO category = categoryDAO.getCategoryById(categoryId);
		request.setAttribute("category", category);

		request.getRequestDispatcher("/WEB-INF/pages/category/update-category.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");

		CategoryDTO category = new CategoryDTO(id, name, description);
		try {
			categoryDAO.updateCategory(category);
			response.sendRedirect(request.getContextPath() + "/view-categories");
		} catch (SQLException e) {
			request.setAttribute("category", category);
			request.getRequestDispatcher("/WEB-INF/pages/category/update-category.jsp").forward(request, response);
		}
	}

}

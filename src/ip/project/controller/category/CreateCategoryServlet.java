package ip.project.controller.category;

import java.io.IOException;

import ip.project.dao.CategoryDAO;
import ip.project.dto.CategoryDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/create-category")
public class CreateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;

	@Override
	public void init() throws ServletException {
		categoryDAO = new CategoryDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/category/create-category.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String description = request.getParameter("description");

		CategoryDTO newCategory = new CategoryDTO(name, description);

		try {
			categoryDAO.createCategory(newCategory);
			response.sendRedirect(request.getContextPath() + "/view-categories");
		} catch (Exception e) {
			request.getRequestDispatcher("/WEB-INF/pages/category/create-category.jsp").forward(request, response);
		}
	}
}

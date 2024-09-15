package ip.project.controller.category;

import java.io.IOException;
import java.util.List;

import ip.project.dao.CategoryDAO;
import ip.project.dto.CategoryDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view-categories")
public class ViewCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;

	@Override
	public void init() throws ServletException {
		categoryDAO = new CategoryDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<CategoryDTO> categories = categoryDAO.getAllCategories();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/WEB-INF/pages/category/view-categories.jsp").forward(request, response);
	}

}

package ip.project.controller.category;

import java.io.IOException;

import ip.project.dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete-category")
public class DeleteCategoryServlet extends HttpServlet {
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

		categoryDAO.deleteCategory(categoryId);

		response.sendRedirect(request.getContextPath() + "/view-categories");
	}
}

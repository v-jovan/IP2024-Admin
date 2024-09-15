package ip.project.controller.attribute;

import java.io.IOException;
import java.util.List;

import ip.project.dao.AttributeDAO;
import ip.project.dao.CategoryDAO;
import ip.project.dto.AttributeDTO;
import ip.project.dto.CategoryDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/create-attribute")
public class CreateAttributeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AttributeDAO attributeDAO;
	private CategoryDAO categoryDAO;

	@Override
	public void init() throws ServletException {
		attributeDAO = new AttributeDAO();
		categoryDAO = new CategoryDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<CategoryDTO> categories = categoryDAO.getAllCategories();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/WEB-INF/pages/attribute/create-attribute.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));

		CategoryDTO category = categoryDAO.getCategoryById(categoryId);
		AttributeDTO newAttribute = new AttributeDTO(category, name, description);

		try {
			attributeDAO.createAttribute(newAttribute);
			response.sendRedirect(request.getContextPath() + "/view-attributes");
		} catch (Exception e) {
			request.getRequestDispatcher("/WEB-INF/pages/attribute/create-attribute.jsp").forward(request, response);
		}
	}
}

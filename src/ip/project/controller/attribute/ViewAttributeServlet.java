package ip.project.controller.attribute;

import java.io.IOException;
import java.util.List;

import ip.project.dao.AttributeDAO;
import ip.project.dto.AttributeDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view-attributes")
public class ViewAttributeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AttributeDAO attributeDAO;

	@Override
	public void init() throws ServletException {
		attributeDAO = new AttributeDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<AttributeDTO> attributes = attributeDAO.getAllAttributes();
		request.setAttribute("attributes", attributes);
		request.getRequestDispatcher("/WEB-INF/pages/attribute/view-attributes.jsp").forward(request, response);
	}
}

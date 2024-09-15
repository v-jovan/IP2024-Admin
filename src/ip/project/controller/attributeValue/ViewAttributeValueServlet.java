package ip.project.controller.attributeValue;

import java.io.IOException;
import java.util.List;

import ip.project.dao.AttributeValueDAO;
import ip.project.dto.AttributeValueDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view-attribute-values")
public class ViewAttributeValueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AttributeValueDAO attributeValueDAO;

	@Override
	public void init() throws ServletException {
		attributeValueDAO = new AttributeValueDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<AttributeValueDTO> attributeValues = attributeValueDAO.getAllAttributeValues();
		request.setAttribute("attributeValues", attributeValues);
		request.getRequestDispatcher("/WEB-INF/pages/attribute-value/view-attribute-values.jsp").forward(request,
				response);
	}
}

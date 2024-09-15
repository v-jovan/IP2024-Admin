package ip.project.controller.attributeValue;

import java.io.IOException;
import java.util.List;

import ip.project.dao.AttributeDAO;
import ip.project.dao.AttributeValueDAO;
import ip.project.dto.AttributeDTO;
import ip.project.dto.AttributeValueDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/create-attribute-value")
public class CreateAttributeValueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AttributeDAO attributeDAO;
	private AttributeValueDAO attributeValueDAO;

	@Override
	public void init() throws ServletException {
		attributeDAO = new AttributeDAO();
		attributeValueDAO = new AttributeValueDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<AttributeDTO> attributes = attributeDAO.getAllAttributes();
		request.setAttribute("attributes", attributes);
		request.getRequestDispatcher("/WEB-INF/pages/attribute-value/create-attribute-value.jsp").forward(request,
				response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		int attributeId = Integer.parseInt(request.getParameter("attributeId"));

		AttributeDTO attribute = attributeDAO.getAttributeById(attributeId);
		AttributeValueDTO newAttributeValue = new AttributeValueDTO(attribute, name);

		try {
			attributeValueDAO.createAttributeValue(newAttributeValue);
			response.sendRedirect(request.getContextPath() + "/view-attribute-values");
		} catch (Exception e) {
			request.getRequestDispatcher("/WEB-INF/pages/attribute-value/create-attribute-value.jsp").forward(request,
					response);
		}
	}
}

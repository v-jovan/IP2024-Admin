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

@WebServlet("/update-attribute-value")
public class UpdateAttributeValueServlet extends HttpServlet {
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
		int attributeValueId = Integer.parseInt(request.getParameter("id"));
		AttributeValueDTO attributeValue = attributeValueDAO.getAttributeValueById(attributeValueId);
		List<AttributeDTO> attributes = attributeDAO.getAllAttributes();

		request.setAttribute("attributeValue", attributeValue);
		request.setAttribute("attributes", attributes);
		request.getRequestDispatcher("/WEB-INF/pages/attribute-value/update-attribute-value.jsp").forward(request,
				response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int attributeId = Integer.parseInt(request.getParameter("attributeId"));

		AttributeDTO attribute = attributeDAO.getAttributeById(attributeId);
		AttributeValueDTO updatedAttributeValue = new AttributeValueDTO(id, attribute, name);

		try {
			attributeValueDAO.updateAttributeValue(updatedAttributeValue);
			response.sendRedirect(request.getContextPath() + "/view-attribute-values");
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/pages/attribute-value/update-attribute-value.jsp").forward(request,
					response);
		}
	}
}

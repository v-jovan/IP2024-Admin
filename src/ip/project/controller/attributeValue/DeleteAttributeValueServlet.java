package ip.project.controller.attributeValue;

import java.io.IOException;

import ip.project.dao.AttributeValueDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete-attribute-value")
public class DeleteAttributeValueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AttributeValueDAO attributeValueDAO;

	@Override
	public void init() throws ServletException {
		attributeValueDAO = new AttributeValueDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int attributeValueId = Integer.parseInt(request.getParameter("id"));

		attributeValueDAO.deleteAttributeValue(attributeValueId);

		response.sendRedirect(request.getContextPath() + "/view-attribute-values");
	}
}

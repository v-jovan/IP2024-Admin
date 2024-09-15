package ip.project.controller.attribute;

import java.io.IOException;

import ip.project.dao.AttributeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete-attribute")
public class DeleteAttributeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AttributeDAO attributeDAO;

	@Override
	public void init() throws ServletException {
		attributeDAO = new AttributeDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int attributeId = Integer.parseInt(request.getParameter("id"));

		attributeDAO.deleteAttribute(attributeId);

		response.sendRedirect(request.getContextPath() + "/view-attributes");
	}

}

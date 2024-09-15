package ip.project.controller.log;

import java.io.IOException;
import java.util.List;

import ip.project.dao.LogDAO;
import ip.project.dto.LogDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view-logs")
public class ViewLogServlet extends HttpServlet {
	private static final long serialVersionUID = 3460360963861931908L;
	private LogDAO logDAO;

	@Override
	public void init() throws ServletException {
		logDAO = new LogDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<LogDTO> logs = logDAO.getAllLogs();
		request.setAttribute("logs", logs);
		request.getRequestDispatcher("/WEB-INF/pages/log/view-logs.jsp").forward(request, response);
	}
}

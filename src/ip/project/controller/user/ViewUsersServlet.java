package ip.project.controller.user;

import ip.project.dao.UserDAO;
import ip.project.dto.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/view-users")
public class ViewUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserDTO> users = userDAO.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/user/view-users.jsp").forward(request, response);
    }
}

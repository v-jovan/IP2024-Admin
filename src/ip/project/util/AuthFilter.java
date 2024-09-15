package ip.project.util;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		httpResponse.setHeader("Pragma", "no-cache");
		httpResponse.setDateHeader("Expires", 0);

		HttpSession session = httpRequest.getSession(false);

		boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

		String loginURI = httpRequest.getContextPath() + "/login";

		boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
		boolean isStaticResource = httpRequest.getRequestURI().startsWith(httpRequest.getContextPath() + "/assets");

		if (!isLoggedIn && !isLoginRequest && !isStaticResource) {
			httpResponse.sendRedirect(loginURI);
		} else {
			chain.doFilter(request, response);
		}
	}
}

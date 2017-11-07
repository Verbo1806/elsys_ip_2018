package org.elsys.ip.servlet.filter;

import org.elsys.ip.servlet.models.User;
import org.elsys.ip.servlet.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	private static List<User> users = new ArrayList<>();

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();

		String username = request.getParameter("name");
		String password = request.getParameter("password");


		UserService userService = new UserService();
		User userToLogIn = userService.getByName(username);

		boolean authorized = false;

		if(userToLogIn == null) {
			authorized = false;
		}
		else{
			authorized = userToLogIn.getPass().equals(password);
		}

		Cookie userCookie = null;
		Cookie[] CookieArr = ((HttpServletRequest) request).getCookies();

		if (CookieArr != null && CookieArr.length >= 0) {

			userCookie = Arrays.stream(CookieArr).filter(cookie->cookie.getName().equals("Token")).findAny().orElse(null);
		}

		// check username and password (can be hardcoded, can use the userService)
		// add if the person is logged in to a cookie (Google it), so that we do not check at every page

		if (userCookie != null) {

			chain.doFilter(request, response);
		}
		else if (authorized) {

			userCookie = new Cookie("Token", String.valueOf(userToLogIn));
			userCookie.setMaxAge(60 * 60);
			((HttpServletResponse) response).addCookie(userCookie);

			chain.doFilter(request, response);
		}
		else {
			request.setAttribute("error", "Wrong username or password!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}



}

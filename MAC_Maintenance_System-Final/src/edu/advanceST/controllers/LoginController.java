package edu.advanceST.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.advanceST.data.LoginDAO;
import edu.advanceST.model.Credentials;
import edu.advanceST.model.CredentialsError;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("login") != null
				&& (boolean) request.getSession().getAttribute("login")) {
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean isLoggedIn = false;
		Object object = getCredentials(request);
		if (object instanceof Credentials) {
			Credentials credentials = (Credentials) object;
			//boolean isValid = new LoginDAO().checkUser(credentials, true, false);
			String role = new LoginDAO().getRole(credentials.getUsername());
			if (!role.isEmpty()) {
				isLoggedIn = true;
				session.setAttribute("role", role);
				session.setAttribute("username", credentials.getUsername());
				session.setAttribute("login", isLoggedIn);
				getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
			} else {
				request.setAttribute("genericError", "username or password does not exists");
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}  else {
			CredentialsError error = (CredentialsError) object;
			request.setAttribute("error", error);
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	private Object getCredentials(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		CredentialsError error = Credentials.validate(username, password, true, false);
		if (error.getUsernameError().isEmpty() && error.getPasswordError().isEmpty()) {
			return new Credentials(username, password);
		} else {
			if(error.getUsernameError().equalsIgnoreCase("username already exists")) {
				return new Credentials(username, password);
			}
			return error;
		}
	}

}

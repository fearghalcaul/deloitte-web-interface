package com.servletjsp.web.servlet.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Map<String, String> loginDetails;
    public void init() throws ServletException {
    	
    	loginDetails = new HashMap<String, String>();
    	loginDetails.put("test", "password");
    	loginDetails.put("test1", "password1");
    }
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (password.equalsIgnoreCase(loginDetails.get(username))) {
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.getRequestDispatcher("/first").forward(request, response);	
		} else {
			request.setAttribute("error", "Invalid Username/Password");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	}
}

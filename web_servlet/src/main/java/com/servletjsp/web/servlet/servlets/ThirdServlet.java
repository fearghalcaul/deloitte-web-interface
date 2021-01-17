package com.servletjsp.web.servlet.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servletjsp.web.servlet.utils.H2JDBCUtils;

@WebServlet(urlPatterns = "/delete")
public class ThirdServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static List<String> tasks;
	
    public void init() throws ServletException {
    	 tasks = new ArrayList<>();
    }
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String task = request.getParameter("task");
		String username = request.getParameter("username");

		String deleteTableSQL = "delete from users where username = \'" + username + "\' AND tasks =\'" + task + "\'";
		
		try (Connection connection = H2JDBCUtils.getConnection()){
            Statement statement = connection.createStatement();
            statement.execute(deleteTableSQL);
            
		} catch (SQLException e) {
            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
		
		request.getRequestDispatcher("FirstServlet").forward(request, response);
	}
}

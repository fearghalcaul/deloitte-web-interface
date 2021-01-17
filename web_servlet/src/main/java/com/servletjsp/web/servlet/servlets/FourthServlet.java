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

@WebServlet(urlPatterns = "/update")
public class FourthServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public void init() throws ServletException {
    }
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String checkbox = request.getParameter("checkbox");
		String username = request.getParameter("username");
		String description = request.getParameter("description");

		if (checkbox == null) {
			checkbox = "off";
		} else {
			checkbox = "on";
		}

		String UPDATE_USERS_SQL = "update users set checkbox = ? where username = ? AND tasks = ?;";
		
		try (Connection connection = H2JDBCUtils.getConnection()){
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);
            preparedStatement.setString(1, checkbox);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, description);

            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
            
            System.out.println(preparedStatement);
            
		} catch (SQLException e) {
            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
		
		request.getRequestDispatcher("FirstServlet").forward(request, response);
	}
}

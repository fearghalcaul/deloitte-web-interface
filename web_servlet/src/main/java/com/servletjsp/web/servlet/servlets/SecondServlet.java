package com.servletjsp.web.servlet.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servletjsp.web.servlet.utils.H2JDBCUtils;

@WebServlet(urlPatterns = "/add")
public class SecondServlet extends HttpServlet {
	
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
		
		LocalDate date = LocalDate.now();

		String COUNT = "SELECT COUNT(tasks) from users";
		
		String INSERT_USERS_SQL = "INSERT INTO users" +
		        "  (id, username, tasks, date, checkbox) VALUES " +
		        " (?, ?, ?, ?, ?);";
		
		try (Connection connection = H2JDBCUtils.getConnection()){
			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(COUNT);
			rs.next();
			//Moving the cursor to the last row
			int index = rs.getInt("count(tasks)");

	        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
	        preparedStatement.setInt(1, index);
	        preparedStatement.setString(2, username);
	        preparedStatement.setString(3, task);
	        preparedStatement.setString(4, date.toString());
	        preparedStatement.setString(5, "off");
	        System.out.println(preparedStatement);
	        preparedStatement.executeUpdate();
	        
		} catch (SQLException e) {
            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
		
		request.setAttribute("date", date.toString());
		
		request.getRequestDispatcher("FirstServlet").forward(request, response);
	}
}

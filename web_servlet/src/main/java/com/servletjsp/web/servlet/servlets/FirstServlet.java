package com.servletjsp.web.servlet.servlets;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servletjsp.web.servlet.pojos.Task;
import com.servletjsp.web.servlet.utils.H2JDBCUtils;

@WebServlet(urlPatterns = "/first")
public class FirstServlet extends HttpServlet {

	private static List<Task> tasks;

    public void init() throws ServletException {

    	String createTableSQL = "create table users (\r\n" + "  id  int(1) primary key,\r\n" +
    							"username  varchar(10),\r\n" + 
    							"tasks varchar(20),\r\n" + 
    							"date varchar(20),\r\n"+
    							"checkbox varchar(20),\r\n);";
    	
    	String dropTable = "Drop table users";
    	
        try (Connection connection = H2JDBCUtils.getConnection(); Statement statement = connection.createStatement();) {

        	statement.execute(dropTable);
            statement.execute(createTableSQL);

        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		tasks = new ArrayList<>();
		
		String QUERY = "select * from users where username=?";
		String username = request.getParameter("username");
		
        try (Connection connection = H2JDBCUtils.getConnection()){

        	PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	
            	String description = rs.getString("tasks");
            	String date = rs.getString("date");
            	String checkbox = rs.getString("checkbox");
            	Task t = new Task().setDescription(description).setDate(date).setChecked(checkbox).build();
                tasks.add(t);
            }

        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }

		request.setAttribute("tasks", tasks);
		request.getRequestDispatcher("/WEB-INF/views/first.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

package com.Phase2Project1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/studentServlet")
public class studentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private Connection connection;  
   
    
    public void init(ServletConfig config) {
    	try {
    		ServletContext context =config.getServletContext();
    		String dburl=context.getInitParameter("dburl");
    		String dbuser=context.getInitParameter("dbuser");
    		String dbpassword=context.getInitParameter("dbpassword");
    		Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String lastname = request.getParameter("lastname");
		String firstname = request.getParameter("firstname");
		String id = request.getParameter("id");
		
		
	
		try (Statement  statement =connection.createStatement();
				){
			
			 
			  int result =statement.executeUpdate("insert into student values ('" +lastname +"','"+firstname+"','"+id+"')");
			PrintWriter out =response.getWriter();
			if(result>0) {
				out.println("<h1>User Created in DB</h1>");
			}else {
				out.println("<h1>Error Creating user</h1>");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
   public void destroy() {
	   try {if(connection!=null) {
		
			connection.close();
		}} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
   }


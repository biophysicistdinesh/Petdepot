package com.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter pw = res.getWriter();
	        //set Content type
	        res.setContentType("text/hmtl");
	        //read the form values
	        String name = req.getParameter("name");
	        String password = req.getParameter("password");
	        String INSERT_QUERY="select * from user where name=? and password=?";

	        //load the jdbc driver
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        //create the connection
	        try {
	        Connection con = DriverManager.getConnection("jdbc:mysql:///firstdb","root","16@Uph204");
	                PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
	            //set the values
	            ps.setString(1, name);
	            
	            ps.setString(2, password);

	            //execute the query
	            ResultSet rs = ps.executeQuery();

	            if(rs.next()) {
	               HttpSession session = req.getSession();
	               session.setAttribute("name",name);
	               String city=rs.getString("city");
	               session.setAttribute("city",city);
	               String no=rs.getString("mobile");
	               session.setAttribute("no", no);
	               res.sendRedirect("pp.jsp");
	            }else {
	               res.sendRedirect("mainpage.html");
	            }
	        }
	        
				catch(Exception e)
				{
					e.printStackTrace();
				}

	        //close the stream
	        //pw.close();
	    }
}
	



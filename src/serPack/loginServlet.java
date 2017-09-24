package serPack;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 6154033604837732282L;
    public loginServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		String user =request.getParameter("email");
		String pass =request.getParameter("pass");
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			PreparedStatement ps=c.prepareStatement("Select * from user_login where id=? and password=?");
			ps.setString(1, user);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			boolean b=rs.next();
			if(b){
				String u=rs.getString(1);
				String p=rs.getString(2);
				if(user.equals(u) && pass.equals(p)){
					RequestDispatcher rs1=	request.getRequestDispatcher("itemServlet");
					rs1.forward(request,response);
					request.getSession(true).setAttribute("username", user);
					response.sendRedirect("itemServlet");
				}else{
					out.print("<script Language= Javascript>alert('Please enter valid username & password! ')</script>");
					RequestDispatcher rs1=	request.getRequestDispatcher("index.html");
					rs1.include(request, response);
				}
			}else{
				out.print("<script Language= Javascript>alert('Please enter valid username & password!')</script>");
				RequestDispatcher rs1=	request.getRequestDispatcher("index.html");
				rs1.include(request, response);
			}
			}catch(Exception e){
			out.println(e);
		}
	}

}

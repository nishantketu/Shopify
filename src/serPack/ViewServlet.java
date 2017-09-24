package serPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 6154033604837732282L;
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		if(request.getSession(false) != null)
		{
		  response.setContentType("text/html");
		  PrintWriter out=response.getWriter();
		  out.print("<h1><center><font color=red>Welcome to Shopify</font></center></h1>");
		  out.print("<hr color=green>");  
		  HttpSession session=request.getSession();
		  List<Item> list=(List)session.getAttribute("cart");
		  Iterator<Item> itr=list.iterator();
		  out.println("<a href=\"itemServlet\">HomePage</a>");
		  out.print("<center>"); 
		  out.print("<br>");
		  
		  out.print("<table>");
		  out.print("<tr><td><font color=green><h2>Your Cart contains following items</h2></font></td></tr>");
		  out.print("</table>");
		  out.print("<br>");
		  out.println("<table border=2>");
		  out.println("<tr><td><b>S.No</b></td><td><b>Item Name</b></td><td><b>Cost</b></td><td><b>Action</b></td></tr>");
	      int i=1;
	      
		  while(itr.hasNext())
	      {  
              Item it=itr.next();
               out.println("<tr><td>"+ i++ 
            		   +"</td><td>"+it.getName()
            		   +"</td><td>"+it.getCost()
            		   +"</td><td><a href=\"DeleteServlet?id="+it.getId()+"\"><b>Delete</b></a></td></tr>"); 
	      }
	     out.println("</table>"); 
	     out.println("</center>"); 
		}
		else{
			response.sendRedirect("index.html?message="+"error");
		}
	}
}
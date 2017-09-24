package serPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 6154033604837732282L;
    public void doGet(HttpServletRequest request,HttpServletResponse response)
    throws IOException
    {
    	if(request.getSession(false) != null)
		{
    	response.setContentType("text/html");
    	PrintWriter out=response.getWriter();
    	out.print("<h1><center><font color=red>Welcome to Shopify</font></center></h1>");
		out.print("<hr color=green>");
		
    	String id=request.getParameter("id");
    	int id1=Integer.parseInt(id);
    	 	
    	ServletContext  ctx=getServletConfig().getServletContext();
    	List list=(List)ctx.getAttribute("itemlist");
    	Iterator itr=list.iterator();
    	
    	
    	while(itr.hasNext())
	      {  
    		 Item it=(Item)itr.next();
    		 int Id1=it.getId();
    		  if(id1==Id1)
    		  {    
    		            out.println(it.getId());
    			        out.println(it.getName());
    			        out.println(it.getCost());
    			
    			  if(request.getSession().getAttribute("cart") == null)
    			  {
    				  ArrayList<Item>  alcart=new ArrayList<Item>();
    				  alcart.add(it);
    				  request.getSession().setAttribute("cart",alcart);
    			  }
    			  else{
    				  ArrayList<Item> alcart = (ArrayList<Item>)request.getSession().getAttribute("cart");
    				  alcart.add(it);
    				  request.getSession().setAttribute("cart",alcart);
    			  }
    			  
    			  out.println("<hr color='green'>");
    			  out.println("<center>");
    			  out.println("<table>");
    			  out.println("<tr><td><b><font color=green>Your item is added to the cart</font></b></td></tr>");
    			  out.println("<tr><td><a href=\"ViewServlet\">View Cart</a></td></tr>");
    			  out.println("<tr><td><a href=\"itemServlet\">Continue</a></td></tr>");
    			  out.println("</table>");
    			  out.println("</center>");
    			  break;
    		  } 
	      }
		} else{
			response.sendRedirect("index.html?message="+"error");
    			}
    }
}

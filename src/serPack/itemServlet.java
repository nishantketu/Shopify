package serPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import serPack.ConnectionProvider;
import serPack.Item;

public class itemServlet extends HttpServlet {


    public itemServlet() {
        super();
    }
    private static final long serialVersionUID = 6154033604837732282L;
	ServletContext  ctx=null;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try{
		Connection con=ConnectionProvider.getConnection();
		 Statement  stmt=(Statement) con.createStatement();
		  ResultSet  rset=stmt.executeQuery("select * from ITEM");		    
		  ArrayList<Item> al=new ArrayList<Item>();
		    
		     while(rset.next()) 
			 {   
		    	 Item item=new Item();
		    	 item.setId(rset.getInt(1));
		    	 item.setName(rset.getString(2));
		    	 item.setCost(rset.getInt(3));
		    	 al.add(item);
			 }
		     
		   
		     ctx=getServletConfig().getServletContext();
		     ctx.setAttribute("itemlist",al);  
		     con.close();
		}
		catch(Exception exception){
			
	System.out.print(exception);
			exception.printStackTrace();
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		try{		
			//System.out.println(request.getSession(false));
			
		if(request.getSession(true) != null)
		{
		  response.setContentType("text/html");
		  PrintWriter  out=response.getWriter();
		  out.print("<center>");
		  out.print("<h1><font color=red>Welcome to Shopify</font></h1>");
		  out.print("<hr color=green>");
		  
		  out.println("<p align='right'><a href=\"ViewServlet\">View Cart</a></p>");
		     @SuppressWarnings("unchecked")
			List<Item> list=(List<Item>)ctx.getAttribute("itemlist");
		     Iterator<Item> itr=list.iterator();
		      
		     out.print("<center>"); 
		     out.print("<br>");
		     
		     out.println("<table>");
		      while(itr.hasNext())
		      {  
	           Item it=(Item)itr.next();
	           out.println("<tr><td><b>Item Name :</b></td><td>");
			   out.println(it.getName());
			   out.println("</td></tr>");
			   out.println("<tr><td><b> Item Cost :</b></td><td>");
			   out.println(it.getCost());
			   out.println("</td></tr>");
			   out.println("<tr><td></td><td>");
			   out.println("<a href=\"CartServlet?id="+it.getId()+"\"><b>Add to Cart</b></a>");
			   out.println("</td><td></td></tr>");
			    
			  } 
		       out.println("</table>"); 
			   out.println("</center>"); 
			  
		      out.close();
		     }
		else{
			response.sendRedirect("index.html?message="+"error");
		}
		}
		    catch(Exception e)
		     {
			      e.printStackTrace();
		     }
	}

}

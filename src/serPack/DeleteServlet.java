package serPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

public class DeleteServlet extends HttpServlet {
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
		
		String id=request.getParameter("id");
		
		List<Item> items = (List<Item>)request.getSession().getAttribute("cart");
		Iterator<Item> iterator = items.iterator();
		
		while(iterator.hasNext()){
			Item item = iterator.next();
			if(item.getId() == Integer.parseInt(id)){
				System.out.println(items.remove(item));
				request.getSession().setAttribute("cart", items);
				break;
			}
			
		}
		out.print("<h3><center>Selected item is deleted from the card  " +id +"</center><h3>");
		request.getRequestDispatcher("/ViewServlet").forward(request, response);
		
	} else{
		response.sendRedirect("index.html?message="+"error");
	}
		
	}

}

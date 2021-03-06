package BillingSystem;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CustomerManager custManager=new CustomerManager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hello");
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
		
			int quantity=0, itemId=0;
			  HttpSession session=request.getSession(false);  
		        if(session!=null){  
		        	
		        
		        	itemId=Integer.parseInt(request.getParameter("submit"));
		        	
		        	quantity=Integer.parseInt(request.getParameter(request.getParameter("submit")));
			
		
				int avaiableQuantity=custManager.getAvaiableQuantity(itemId);
			String userName=(String)session.getAttribute("user");  
			if(quantity>avaiableQuantity) {
				request.setAttribute("message", "Quantity entered greater than available unable to add to cart");
				request.getRequestDispatcher("/UserOperation.jsp").include(request, response); 
			}
			else if(quantity>0) {
			custManager.cartUpdate(itemId,userName,quantity);
			request.setAttribute("message", "Successfully Added to Cart");
			request.getRequestDispatcher("/UserOperation.jsp").include(request, response); }
			else
			{
				request.setAttribute("message", "Quantity should be grater than 0");
				request.getRequestDispatcher("/UserOperation.jsp").include(request, response); ;
			}
			}
			
	}

}

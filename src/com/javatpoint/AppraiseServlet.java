package com.javatpoint;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AppraiseServlet
 */ 
@WebServlet("/AppraiseServlet")
public class AppraiseServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		//use this for getSections() as input para
		//String name=request.getParameter("name");
		HttpSession session=request.getSession(false);  
        if(session!=null)
        {  
        	if(session.getAttribute("name")!=null)
        	{
        	String name=(String)session.getAttribute("name");  
	        
			AppraiseBean abean=new AppraiseBean();
			SaveAppraiseBean sbean=new SaveAppraiseBean();
			abean.setQuery();
			request.setAttribute("abean",abean);
			request.setAttribute("sbean",sbean);
			RequestDispatcher rd=request.getRequestDispatcher("appraise.jsp");
			rd.forward(request, response);
        	}
        	else request.getRequestDispatcher("index.jsp").forward(request, response);  
		}
        else
        {
        	out.print("Please login first");  
            request.getRequestDispatcher("index.jsp").forward(request, response);  
        }
        out.close();
	}
}

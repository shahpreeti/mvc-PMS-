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
		HttpSession session=request.getSession(false);  
		int source=0;
		
        if(session!=null)
        {  
        	if(session.getAttribute("name")!=null)
	        {
				AppraiseBean abean=new AppraiseBean();
				SaveAppraiseBean sbean=new SaveAppraiseBean();
				source=(int) session.getAttribute("source");
				abean.setQuery();
				if(source==2)
				{
					int id=Integer.parseInt(request.getParameter("sub_apprempid"));
						abean.setApprempid(id);
						abean.setPhaseid(2);
				}
				else if(source==1)
				{
				int apprempid=(int) session.getAttribute("appr_empid");
				abean.setApprempid(apprempid);
				abean.setPhaseid(1);
				}
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

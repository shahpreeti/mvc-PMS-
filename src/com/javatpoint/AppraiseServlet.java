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
        		session.setAttribute("sub_apprempid","0");
				AppraiseBean abean=new AppraiseBean();
				SaveAppraiseBean sbean=new SaveAppraiseBean();
				source=(int) session.getAttribute("source");
				abean.setQuery();
				if(source==2)
				{
					System.out.println("emtered in supervisor loop");
					SubordinatesBean subbean=new SubordinatesBean();
					int empid=(int) session.getAttribute("emp_id");
					System.out.println(empid);
					subbean.setParam(empid);
					subbean.setSubordinates_list();
					String[][] sublist=subbean.getSubordinates_list();
					int len=sublist.length;
					String action = request.getParameter("action");
					for(int i=0;i<len;i++)
					{
					if (sublist[i][4].equals(action)) {
						abean.setApprempid(Integer.parseInt(sublist[i][0]));
						abean.setPhaseid(2);
						abean.setSub_apprempid(Integer.parseInt(sublist[i][0]));
						System.out.println("id "+session.getAttribute("sub_apprempid"));
						i=len;
						} 
					}
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

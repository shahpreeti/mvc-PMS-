package com.javatpoint;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Subordi_FormServlet")
public class Subordi_FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		SubFormBean subformbean=new SubFormBean();
		SaveSubFormBean savesubformbean=new SaveSubFormBean();
		AppraiseBean apbean=new AppraiseBean();
		
		subformbean.setQuery();
		apbean.setQuery();
		request.setAttribute("savesubformbean", savesubformbean);
    	request.setAttribute("subformbean",subformbean);
    	request.setAttribute("apbean", apbean);
    	RequestDispatcher rd=request.getRequestDispatcher("SubForm.jsp");
    	rd.forward(request, response);
	}

}

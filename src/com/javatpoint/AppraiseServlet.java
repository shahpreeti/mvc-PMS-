package com.javatpoint;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		AppraiseBean abean=new AppraiseBean();
		abean.setQuery();
		request.setAttribute("abean",abean);
		RequestDispatcher rd=request.getRequestDispatcher("appraise.jsp");
		rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
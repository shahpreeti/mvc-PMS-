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
 * Servlet implementation class SaveAppraiseServlet
 */
@WebServlet("/SaveAppraiseServlet")
public class SaveAppraiseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		SaveAppraiseBean sbean=new SaveAppraiseBean();
		String action = request.getParameter("action");
		HttpSession session=request.getSession(false); 
		String name=(String)session.getAttribute("name");  
		int apprempid=(int) session.getAttribute("appr_empid");
		AppraiseBean abean=new AppraiseBean();
		abean.setQuery();
		String[][] formdata=abean.getAllForms(apprempid);
		String[] secnames=abean.getSections(apprempid);
		int len=secnames.length;
		int k=0;
		for(int i=0;i<len;i++)
		{
			String[][] secform=abean.getForm(i);
			int seclen=secform.length;
			for(int j=0;j<seclen;j++)
			{
				String para1="t"+i+""+j;
				String para2="tr"+i+""+j;
				formdata[k][3]=request.getParameter(para1);
				if(formdata[k][9].equals("Y"))
					formdata[k][4]=request.getParameter(para2);
				k++;
			}
		}
		sbean.setSelfappr(formdata);
		if ("Save".equals(action)) {
			sbean.setAppraiseStatus(1);
			sbean.saveForm();
			abean.setUpdatedForms(formdata);
			
			request.setAttribute("sbean",sbean);
			request.setAttribute("abean", abean);
			RequestDispatcher rd=request.getRequestDispatcher("appraise.jsp");
			rd.forward(request, response);
			
		} else if ("Submit".equals(action)) {
			try {
		    sbean.submitForm();}
			catch(Exception e)
			{
				System.out.println(e);
			}
		    sbean.getCount();
		    if(sbean.getCount()!=0)
		    {
		    	sbean.setAppraiseStatus(2); 
		    	abean.setUpdatedForms(formdata);
		    	request.setAttribute("abean",abean);
		    	request.setAttribute("sbean",sbean);
		    	RequestDispatcher rd=request.getRequestDispatcher("appraise.jsp");
		    	rd.forward(request, response);
		    }
		    else
		    {
		    	sbean.setAppraiseStatus(3); 
		    	abean.setUpdatedForms(formdata);
		    	request.setAttribute("abean",abean);
		    	request.setAttribute("sbean",sbean);
				RequestDispatcher rd=request.getRequestDispatcher("appraise-success.jsp");
				rd.forward(request, response);
		    }
		}
		
				
	}

}

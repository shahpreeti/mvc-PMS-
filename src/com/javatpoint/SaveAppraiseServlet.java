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
		AppraiseBean abean=new AppraiseBean();
		HttpSession session=request.getSession(false); 
		int source=(int)session.getAttribute("source"); 
		String action = request.getParameter("action");
		int empid,apprempid;
		if(source==1)
		{
			apprempid=(int) session.getAttribute("appr_empid");
			abean.setApprempid(apprempid);
			abean.setPhaseid(1);
		}
		if(source==2)
		{
			System.out.println("emtered in supervisor loop");
			SubordinatesBean subbean=new SubordinatesBean();
			empid=(int) session.getAttribute("emp_id");
			System.out.println(empid);
			subbean.setParam(empid);
			subbean.setSubordinates_list();
			String[][] sublist=subbean.getSubordinates_list();
			int len=sublist.length;
			
			for(int i=0;i<len;i++)
			{
			if (sublist[i][4].equals(action)) {
				abean.setApprempid(Integer.parseInt(sublist[i][0]));
				abean.setPhaseid(2);
				i=len;
				} 
			}
		}
		abean.setQuery();
		String[][] formdata=abean.getAllForms();
		String[] secnames=abean.getSections();
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

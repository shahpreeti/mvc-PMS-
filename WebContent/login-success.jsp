<%@page import="com.javatpoint.LoginBean2"%>
<%@page import="java.util.*"%>
<style type="text/css">
  <%@include file="WEB-INF/styles/mystyle1.css" %>
</style>
<div id="div2">
<p id="company">third(i)</p>
<p id="slogan">Information. Intelligence. Insight.</p>
</div><br><br>
<div>
<p id ="pms">Performance Management System</p>
</div>

<p>You are successfully logged in!</p>

<%

LoginBean2 bean=(LoginBean2)request.getAttribute("bean");
out.print("Welcome to Third(i) PMS, "+bean.getName());
session.setAttribute("name", bean.getName());
out.println(session.getAttribute("name"));
%>
<br>
<br>
<ul>
       <% 
       String[][] menu1=bean.getMenu();
       for(int i=0;i<10;i++)
       {
    	   if(menu1[i][0]!=null)
    	   {
    	   %>
    		<li><a href=<%=menu1[i][1] %>><%=menu1[i][0] %></a></li>
           <%
           }
    	}%> 
           
</ul>
                
                
                
                
                
                
                
                
                
                
                
                
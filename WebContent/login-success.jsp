<%@page import="com.javatpoint.LoginBean2"%>
<%@page import="java.util.*"%>
<%@ include file="design-page.jsp" %>
<style type="text/css">
<%@include file="WEB-INF/styles/mystyle1.css" %>
</style>


<%

LoginBean2 bean=(LoginBean2)request.getAttribute("bean");
String name=(String) session.getAttribute("name");
out.print(session.getAttribute("name"));
bean.setMenu(name);
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
</body>    
                           
                
                
                
                
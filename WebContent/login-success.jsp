<%@page import="com.javatpoint.LoginBean2"%>
<%@page import="java.util.*"%>
<style type="text/css">
  <%@include file="WEB-INF/styles/mystyle1.css" %>
</style>

<%

LoginBean2 bean=(LoginBean2)request.getAttribute("bean");
session.setAttribute("name", bean.getName());

String user=session.getAttribute("name").toString();

%>
<div id="div2">
<p id="company">third(i)</p>
<p id="slogan">Information. Intelligence. Insight. </p>

<button id="logout" style="float:right" onclick="sessionOut('<%=user%>')">Logout</button> 
</div><br><br>
<div>
<p id ="pms">Performance Management System</p>
</div>

<%out.println("this is your home page "+user); %>
<div id="test"></div>
<p>You are successfully logged in! </p>



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
                
<script type="text/javascript">
function sessionOut(user)
{
	
	//var username=user;
	document.getElementById("test").innerHTML=user+"  manual";
	window.location = "index.jsp";
	
	
}
</script>               
                
                
                
                
                
                
                
                
                
                
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
<ul>
       <% Map<String,String> menu=bean.getMenu_item();
        Iterator<Map.Entry<String,String>> itr=menu.entrySet().iterator();
       	while(itr.hasNext())
        {
           	Map.Entry<String, String> entry = itr.next();
           	System.out.println(entry.getKey()+" "+entry.getValue());
        %>
 		<li><a href="AppraiseServlet"><%=entry.getKey() %></a></li>
        <%}%> 
</ul>
                
                
                
                
                
                
                
                
                
                
                
                
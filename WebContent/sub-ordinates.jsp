<html>
<%@ include file="design-page.jsp" %>
<%@page import="com.javatpoint.SubordinatesBean"%>
<style type="text/css">
  <%@include file="WEB-INF/styles/dropdownlist.css" %>
</style>

<%out.print("Select any subordinate ");
out.print(session.getAttribute("name"));
SubordinatesBean subbean=(SubordinatesBean)request.getAttribute("subbean");
String[][] sub_list=subbean.getSubordinates_list();
int len=sub_list.length;
out.print(len);

String text="Select here >>";
%>
<br>
<br>
<div class="dropdown">
  <button class="dropbtn"><%=text %> </button>
  <div class="dropdown-content">
  <% for(int i=0;i<len;i++)
  {
  	String emp="Preeti";
  %>
    <a href="Subordi_FormServlet" onclick="<%session.setAttribute("subname"+i,emp);%>"><%=sub_list[i][4] %></a>
  <%}%>
  </div>
</div>
<div>
<br>
<p id="test"></p>
</div>
<script>
function openForm(emp)
{
	document.getElementById("test").innerHTML =emp;
}
</script>
</html>
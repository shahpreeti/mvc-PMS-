<html>
<%@ include file="design-page.jsp" %>
<%@page import="com.javatpoint.SubordinatesBean"%>
<style type="text/css">
  <%@include file="WEB-INF/styles/dropdownlist.css" %>
  <%@include file="WEB-INF/styles/SubList.css" %>
</style>
<form name="form2" action="AppraiseServlet" >
<%out.print("Select any subordinate ");
out.print(session.getAttribute("name"));
SubordinatesBean subbean=(SubordinatesBean)request.getAttribute("subbean");
String[][] sub_list=subbean.getSubordinates_list();
request.setAttribute("subbean", subbean);
int source=2;
session.setAttribute("source",2);
int len=sub_list.length;
%>

<div id="sublist">
<table>
<%

for(int i=0;i<len;i++)
  {
	String id=sub_list[i][0];
  %><tr>
	<td ><%=id %></td>
	
	<td><input type="submit" id="<%=id%>>" name="action" value="<%=sub_list[i][4]%>"></td>
	</tr>
  <%} %>

</table>
</div>
<div>
<br>
<p id="test">
<%out.print("apprempid is  "+session.getAttribute("sub_apprempid")); %>
</p>
</div>
</form>
<script>
function openForm(emp)
{
	document.getElementById("test").innerHTML =emp;
}
</script>
</html>
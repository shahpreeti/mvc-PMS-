<html>
<%@page import="com.javatpoint.AppraiseBean"%>
<style type="text/css">
  <%@include file="WEB-INF/styles/mystyle1.css" %>
  <%@include file="WEB-INF/styles/RatingStyle.css" %>
  <%@include file="WEB-INF/styles/AppraisalTabs.css" %>
</style>
<div id="div2">
<p id="company">third(i)</p>
<p id="slogan">Information. Intelligence. Insight.</p>
</div><br><br>
<div>
<p id ="pms">Performance Management System</p>
</div>
<body>
<%
out.print("Welcome to self appraisal ");
out.print(session.getAttribute("name"));

AppraiseBean bean=(AppraiseBean)request.getAttribute("bean");
String[] ar=bean.getSections();
out.println(ar[0]);
int len=ar.length;
int i=0;
%>
<div class="tab">
<%for(i=0;i<len;i++)
  {%>
		<button id="<%=ar[i]%>" class="tablinks" onclick="loadForm('<%=ar[i]%>')" ><%=ar[i] %></button>
  <%}%>
</div>
<div id="formsections" >
<p>Load Section</p>
</div>


<script>

function loadForm(section)
{

	
	document.getElementById("formsections").innerHTML = "Form "+section;
	document.getElementById("formsections").style.display="block";
	
	document.getElementById(section).style.backgroundColor="#b3b5b1";
	//document.getElementByClassName("tab").style.backgroundColor="#FF0000";
}
</script>

</body>

</html>
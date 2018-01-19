<html>
 
<%@page import="com.javatpoint.AppraiseBean"%>

<style type="text/css">
  <%@include file="WEB-INF/styles/mystyle1.css" %>
  <%@include file="WEB-INF/styles/RatingStyle.css" %>
  <%@include file="WEB-INF/styles/AppraisalTabs.css" %>
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
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

AppraiseBean abean=(AppraiseBean)request.getAttribute("abean");
String[] secname=abean.getSections(session.getAttribute("name").toString());
int len=secname.length;
int i=0,j=0,k=0;
%>
<div class="tab">
<%for(i=0;i<len;i++)
  {%>
		<button id="<%=secname[i]%>" class="tablinks" onclick="loadForm('<%=i%>')" ><%=secname[i] %></button>
  <%}%>

</div>

 <br>
<%for(j=0;j<len;j++)
{
%>
	<div class="formsection" id="formsection<%=j%>" >
	<%
		String[][] sectionform=abean.getForms(session.getAttribute("name").toString(),j);
		int slen=sectionform.length;
		%><label>displaying section<%=j+1 %></label><br><%
		if(sectionform[0][9].equals("Y"))
		{
			for(int t=0;t<sectionform.length;t++)
			{
			
				String idtext="t"+j+t;
				String c1=sectionform[t][5];
				String c2=sectionform[t][6];
				String c3=sectionform[t][7];				
				%><label id="<%=sectionform[t][2]%>" ><%=sectionform[t][2] %></label>
			  	<textarea rows="4" cols="40" id="<%=idtext%>" onfocus="loadCriteria('<%=c1%>','<%=c2%>','<%=c3%>')"><%=sectionform[t][3] %></textarea>
			  	<%
				for(int s=1;s<=5;s++)
				{ 
					String idrate="rate"+j+t;
					String idbutton="b"+s+idrate;
					%>					
				  	<button class="ratebutton" id="<%=idbutton%>" onclick="countRate(<%=idrate%>,<%=s%>)"><img src="starUnfilled.png" ></button>
				<%}%>
			  	<label id="rate<%=j%><%=t%>" ><%=sectionform[t][4] %></label>
			  	<br>
				<%
			}
		}
		else
			{
				for(int t=0;t<sectionform.length;t++)
				{
					String idtext="t"+j+t;
					%><label id="<%=sectionform[t][2]%>" ><%=sectionform[t][2] %></label><br><br>
			  		<textarea rows="5" cols="100" id="<%=idtext%>"><%=sectionform[t][3] %></textarea>
					<br><br>
			  	<%
				}
			}
		%>
	</div>
<%} %>

<div id ="formsections">
</div>
<div id="indicator">
<table>
<tr>
<td>
<label><b>Previous Indicator</b></label>
</td>
<td>
<label><b>Current Indicator</b></label><br>
</td>
<td>
<label><b>Next Indicator</b></label>
</td>
</tr>
<tr>
<td>
<label id=l1 class=pi></label>
</td>
<td>
<label id=l2 class =pi></label>
</td>
<td>
<label id=l3 class=pi></label>
</td>
</tr>
</table>
</div>
<script type="text/javascript" src="scriptappraise.js"></script>

</body>
</html>
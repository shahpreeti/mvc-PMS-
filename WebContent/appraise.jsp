<html>
<%@page import="com.javatpoint.AppraiseBean"%>
<%@page import="com.javatpoint.SaveAppraiseBean"%>
<style type="text/css">
  <%@include file="WEB-INF/styles/mystyle1.css" %>
  <%@include file="WEB-INF/styles/RatingStyle.css" %>
  <%@include file="WEB-INF/styles/AppraisalTabs.css" %>
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script type="text/javascript" src="scriptappraise.js"></script>
<body onload="bodyLoad()">
<div id="div2">
<p id="company"><a href="HomePageServlet" style="text-decoration: none; color:white">third(i)</a></p>
<p id="slogan">Information. Intelligence. Insight.</p>
<a href="LogoutServlet" style="float:right">Logout</a>
</div><br><br>
<div>
<p id ="pms">Performance Management System</p>
</div>

<%
out.print("Welcome to the appraisal form ");
out.print(session.getAttribute("name"));
SaveAppraiseBean sbean=(SaveAppraiseBean)request.getAttribute("sbean");
AppraiseBean abean=(AppraiseBean)request.getAttribute("abean");
AppraiseBean abean2=(AppraiseBean)request.getAttribute("abean2");
int apprempid=abean.getApprempid();
out.print("\n\nDisplaying form of employee "+abean.getApprempid());
String[] secname=abean.getSections();
String[][] allforms=abean.getAllForms();
int len=secname.length;
int totalrows=allforms.length;
int totalcols=allforms[0].length;
int i=0,j=0,k=0;
String status=sbean.getAppraiseStatus();
int apprstatus=Integer.parseInt(allforms[0][8]);
Integer source=(Integer) session.getAttribute("source");
if(source==1){}
else
{
String[][] allforms1=abean2.getAllForms();
String[] secname2=abean2.getSections();
}
%>
<div><%=status %></div>
<div class="tab">
<%for(i=0;i<len;i++)
  {%>
		<button id="Section Name<%=i%>" class="tablinks" onclick="loadForm('<%=i%>')" name="Section Name<%=i%>"><%=secname[i] %></button>
  <%}%>

</div>
<form name="form1" action="SaveAppraiseServlet?sub_apprempid=<%=apprempid %>" method="post">
 <br>
<%for(j=0;j<len;j++)
{
%>
	<div class="formsection" id="formsection<%=j%>" onload="editStatus(j)" >
	<%
		String[][] sectionform=abean.getForm(j);
		String[][] sectionform2=abean.getForm(j);
		if(source==1){}
		else{
			sectionform2=abean2.getForm(j);}
		int slen=sectionform.length;
		%><label>displaying section<%=j+1 %></label><br><%
		if(sectionform[0][9].equals("Y"))
		{
			%><table>
			<%for(int t=0;t<sectionform.length;t++)
			{
				
				String idtext="t"+j+t;
				String elementdisable,btndisable;
				elementdisable = sectionform[t][8].equals("2")?"readonly":"";
				btndisable = sectionform[t][8].equals("2")?"disabled":"";
				%>
				<tr>
				<td><label id="<%=sectionform[t][2]%>" ><%=sectionform[t][2] %></label></td>
				<td><textarea class="review" rows="4" cols="40" id="<%=idtext%>" <%=elementdisable%>  onfocus="loadCriteria('<%=sectionform[t][15] %>')" name="<%=idtext%>"><%=sectionform[t][3] %></textarea></td>
			  	<td>
			  	<%String idtb="tr"+j+t;
			  	String ratestr;
			  	if(sectionform[t][4].equals(""))
			  		ratestr="0";
			  	else
			  		ratestr=sectionform[t][4];
			  	int rating=Integer.parseInt(ratestr);
			  	for(int s=1;s<=5;s++)
				{ 
			  		String rateimg;
			  		if(rating>=s)
			  			rateimg="star.png";
			  		else 
			  			rateimg="starUnfilled.png";
					String idrate="rate"+j+t;
					String idbutton="b"+s+idrate;
					%>					
				  	<button type="button" class="ratebutton" id="<%=idbutton%>" onclick="countRate(<%=idrate%>,<%=s%>,<%=idtb%>)" <%=btndisable%>><img src=<%=rateimg %>></button>
				<%}%>
			  	<label id="rate<%=j%><%=t%>"><%=sectionform[t][4] %></label>
				<input type="hidden" id="<%=idtb %>" name="<%=idtb %>" value="<%=ratestr %>" readonly>
			  	</td><%if(source==1){%></tr><%}else{%>
			  	<td><button type="button" onclick="loadSelfForm('self<%=sectionform2[t][2]%>')" class="loadselfdata"><img src="scroll_button_down_up.png"></button></td></tr>
			  	<tr id="self<%=sectionform2[t][2]%>" class="selfdata">
			  	<td ></td>
			  	<td ><label ><%=sectionform2[t][3] %></label></td>
			  	<td ><label><%=sectionform2[t][4] %> star</label></td>
			  	</tr>
			  	<%}
			}
			%></table>
			<table id="performanceTable">
				<tr>
				<td width="300"><label><b>Previous Indicator</b></label></td>
				<td width="300"><label><b>Current Indicator</b></label></td>
				<td width="300"><label><b>Next Indicator</b></label></td>
				</tr>
				<%for(int t=0;t<sectionform.length;t++)
				{
					String c1=sectionform[t][5];
					String c2=sectionform[t][6];
					String c3=sectionform[t][7];%>
					<tr></tr>
					<tr id="<%=sectionform[t][15]%>" class="indicator_row">
					<td width="300"><label id=l1 class=pi><%=c1 %></label></td>
					<td width="300"><label id=l2 class =pi><%=c2 %></label></td>
					<td width="300"><label id=l3 class=pi><%=c3 %></label></td>
					</tr>
					
				<%}%></table>
<%		}
		else
			{
				for(int t=0;t<sectionform.length;t++)
				{
					String elementdisable;
					elementdisable = sectionform[t][8].equals("2")?"readonly":"";
					String idtext="t"+j+t;
					%>
					<label id="<%=sectionform[t][2]%>" ><%=sectionform[t][2] %></label><br><br>
			  		<textarea rows="5" cols="100" id="<%=idtext%>" name="<%=idtext%>" <%=elementdisable%> ><%=sectionform[t][3] %></textarea>
					<%if(source==1){%><br><br><%}else{%>
					<button type="button" onclick="loadSelfForm2('self<%=sectionform2[t][2]%>')" class="loadselfdata"><img src="scroll_button_down_up.png"></button>
					<br><label id="self<%=sectionform2[t][2]%>" class="selfdata"><%=sectionform2[t][3] %></label>
					<br><br>
			  	<%
					}
				}
			}
		%>
	</div>
<%} %>

<div id ="formsections">

</div>

<div id="submission">
<input type="submit" id="savebtn" name="action" value="Save">
<input type="submit" id="subbtn" name="action" value="Submit">
</div>
</form></body>
<script type="text/javascript" >
document.getElementById("Section Name0").style.backgroundColor = "#ddd";
function bodyLoad() {

	 var s=<%=allforms[0][8]%>;
   if(s==2)
   	{
   		var btn1 = document.getElementById("subbtn");
   		btn1.disabled = true;
   		btn1 = document.getElementById("savebtn");
   		btn1.disabled = true;
   	}
   if(<%=source%>==1)
   	{
		    var elements1 = document.getElementsByClassName("loadselfdata");
			for(var i = 0, length = elements1.length; i < length; i++) 
			{
				document.getElementById(elements1[i].id).style.display='none';	
			}
   	}
}

</script>

</html>
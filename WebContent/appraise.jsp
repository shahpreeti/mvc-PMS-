<html>
<%@page import="com.javatpoint.AppraiseBean"%>
<%@page import="com.javatpoint.SaveAppraiseBean"%>
<style type="text/css">
  <%@include file="WEB-INF/styles/mystyle1.css" %>
  <%@include file="WEB-INF/styles/RatingStyle.css" %>
  <%@include file="WEB-INF/styles/AppraisalTabs.css" %>
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.js"></script>

<div id="div2">
<p id="company"><a href="HomePageServlet" style="text-decoration: none; color:white">third(i)</a></p>
<p id="slogan">Information. Intelligence. Insight.</p>
<a href="LogoutServlet" style="float:right">Logout</a>
</div><br><br>
<div>
<p id ="pms">Performance Management System</p>
</div>

<%
out.print("Welcome to self appraisal form ");
out.print(session.getAttribute("name"));
SaveAppraiseBean sbean=(SaveAppraiseBean)request.getAttribute("sbean");
AppraiseBean abean=(AppraiseBean)request.getAttribute("abean");
String[] secname=abean.getSections(session.getAttribute("name").toString());
String[][] allforms=abean.getAllForms(session.getAttribute("name").toString());
int len=secname.length;
int totalrows=allforms.length;
int i=0,j=0,k=0;
String status=sbean.getAppraiseStatus();

%>
<div><%=status %></div>
<div class="tab">
<%for(i=0;i<len;i++)
  {%>
		<button id="Section Name<%=i%>" class="tablinks" onclick="loadForm('<%=i%>')" name="Section Name<%=i%>"><%=secname[i] %></button>
  <%}%>

</div>
<form name="form1" action="SaveAppraiseServlet" method="post">
 <br>
<%for(j=0;j<len;j++)
{
%>
	<div class="formsection" id="formsection<%=j%>" >
	<%
		String[][] sectionform=abean.getForm(j);
		int slen=sectionform.length;
		%><label>displaying section<%=j+1 %></label><br><%
		if(sectionform[0][9].equals("Y"))
		{
			%><table>
			<%for(int t=0;t<sectionform.length;t++)
			{
			
				String idtext="t"+j+t;
				String c1=sectionform[t][5];
				String c2=sectionform[t][6];
				String c3=sectionform[t][7];	
				
				%>
				<tr>
				<td><label id="<%=sectionform[t][2]%>" ><%=sectionform[t][2] %></label></td>
				<td><textarea rows="4" cols="40" id="<%=idtext%>" onfocus="loadCriteria()" name="<%=idtext%>"><%=sectionform[t][3] %></textarea></td>
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
			  		else rateimg="starUnfilled.png";
					String idrate="rate"+j+t;
					String idbutton="b"+s+idrate;
					%>					
				  	<button class="ratebutton" id="<%=idbutton%>" onclick="countRate(<%=idrate%>,<%=s%>,<%=idtb%>)"><img src=<%=rateimg %>></button>
				<%}%>
			  	<label id="rate<%=j%><%=t%>"><%=sectionform[t][4] %></label>
				<input type="hidden" id="<%=idtb %>" name="<%=idtb %>" value="<%=ratestr %>" readonly>
			  	</td></tr>
			  	
				<%
			}
			%></table><%
		}
		else
			{
				for(int t=0;t<sectionform.length;t++)
				{
					String idtext="t"+j+t;
					%><label id="<%=sectionform[t][2]%>" ><%=sectionform[t][2] %></label><br><br>
			  		<textarea rows="5" cols="100" id="<%=idtext%>" name="<%=idtext%>"><%=sectionform[t][3] %></textarea>
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
<td><label><b>Previous Indicator</b></label></td>
<td><label><b>Current Indicator</b></label><br></td>
<td><label><b>Next Indicator</b></label></td>
</tr>
<tr>
<td><label id=l1 class=pi></label></td>
<td><label id=l2 class =pi></label></td>
<td><label id=l3 class=pi></label></td>
</tr>
</table>
</div>
<div id="submission">
<input type="submit" name="action" value="Save">
<input type="submit" name="action" value="Submit">
</div>
</form></body>
<script type="text/javascript" >
document.getElementById("Section Name0").style.backgroundColor = "#ddd";

function countRate(r_id,star,trid)
{
	
	var labelid=document.getElementById(r_id.id);
	var tr_id=document.getElementById(trid.id);
	document.getElementById(labelid.id).innerHTML=star;
	document.getElementById(tr_id.id).value=star;
	
	for(var i=1;i<=star;i++)
		{	var b_id="b"+i+labelid.id;
			document.getElementById(b_id).innerHTML ='<img src="star.png" />'; 
		
		}
	for(var i=5;i>star;i--)
	{	var b_id="b"+i+labelid.id;
		document.getElementById(b_id).innerHTML ='<img src="starUnfilled.png" />'; 
	
	}
	
}
function loadForm(section)
{
	var s="formsection"+section;	
	var elements = document.getElementsByClassName("formsection");
	
    for(var i = 0, length = elements.length; i < length; i++) {
      if(elements[i].id==s)
          elements[i].style.display = 'block';
      else
    	  elements[i].style.display = 'none';
      
    }
    var elements1 = document.getElementsByClassName("tablinks");
      var num=Number(section);
      for(var i = 0, length = elements1.length; i < length; i++) {
    	
          if(elements1[i].id=="Section Name"+num)
        	  {
        	  	if(i==length-1)
            	  document.getElementById("submission").style.display='block';
        	  	else
        	      document.getElementById("submission").style.display='none';
        	  	elements1[i].style.backgroundColor = "#ddd";
        	  }
          else
        	  elements1[i].style.backgroundColor = "#f1f1f1";
          }	
      document.getElementById("indicator").style.display='none';
      
}
function loadCriteria()
{
	var c1,c2,c3;
	//document.getElementById("l1").innerHTML =c1;
	//document.getElementById("l2").innerHTML =c2;
	//document.getElementById("l3").innerHTML =c3;
	document.getElementById("indicator").style.display='block';
}

</script>

</html>
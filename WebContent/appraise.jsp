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

AppraiseBean bean=(AppraiseBean)request.getAttribute("bean");
String[] ar=bean.getSections();
int len=ar.length;
int i=0,j=0,k=0;
%>
<div class="tab">
<%for(i=0;i<len;i++)
  {%>
		<button id="<%=ar[i]%>" class="tablinks" onclick="loadForm('<%=i%>')" ><%=ar[i] %></button>
  <%}%>

</div>

 <br>
<%for(j=0;j<len;j++)
{%>
	<div class="formsection" id="formsection<%=j%>" >
	<%
		String[][] sectionform=bean.getForms(j);
		int slen=sectionform.length;
		%><label>displaying section<%=j+1 %></label><br><%
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
		<%}%>
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

<script>

document.getElementById("Section Name1").style.backgroundColor = "#ddd";

function countRate(r_id,star)
{
	
	var labelid=document.getElementById(r_id.id);
	document.getElementById(labelid.id).innerHTML=star;
	
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
      var num=Number(section)+1;
      for(var i = 0, length = elements1.length; i < length; i++) {
    	
          if(elements1[i].id=="Section Name"+num)
              elements1[i].style.backgroundColor = "#ddd";
          else
        	  elements1[i].style.backgroundColor = "#f1f1f1";
          }	
}
function loadCriteria(c1,c2,c3)
{
	document.getElementById("l1").innerHTML =c1;
	document.getElementById("l2").innerHTML =c2;
	document.getElementById("l3").innerHTML =c3;
	

}
</script>

</body>

</html>
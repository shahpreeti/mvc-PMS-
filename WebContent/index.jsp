
<style type="text/css">
  <%@include file="WEB-INF/styles/mystyle1.css" %>
</style>
<form action="ControllerServlet" method="post">
<div id="div2">
<p id="company">third(i)</p>
<p id="slogan">Information. Intelligence. Insight.</p>
</div><br><br>
<div>
<p id ="pms">Performance Management System</p>
</div>
<div id="div3"></div>
 
<div id="div1">
<p>Login with us</p>
<table>
<tr>
<td>id</td>
<td><input type="text" name ="name"></td>
</tr>
<tr>
<td>password</td>
<td><input type="password" name ="password"></td>
</tr>
<tr><br></tr>
<tr>
<td></td>
<td><button id="logbutton" input type = "submit" value="login">LOGIN</button></td>
</tr>
</table>
</div>
<%session.setAttribute("name","wronguser");%>
</form>


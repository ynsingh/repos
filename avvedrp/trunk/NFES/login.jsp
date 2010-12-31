
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">
<HEAD profile=http://gmpg.org/xfn/11>
<TITLE>Staff Profile Login</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">

<!--<link href="jsp/signfront.css" media="screen, projection" rel="stylesheet" type="text/css">
<LINK media=screen href="jsp/style.css" type=text/css rel=stylesheet>-->

<link href="css/style.css" rel="stylesheet" type="text/css"/>

<META content="MSHTML 6.00.2900.5694" name=GENERATOR>


<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
function doLogin(){
	document.forms[0].submit();
}
<!--Added by ManuSoman to prevent Special Symbol Entry in UserName Field-->
<!--This fuction allows only alphanumerical characters,undescore,and dot in UserName field--> 
var r={'special':/[^a-zA-Z0-9_.]/g};
var c={'special':/(46)/g};
var d={'special':/(95)/g};
function valid(o,w)
{
  o.value = o.value.replace(c[w],'.');
  o.value = o.value.replace(d[w],'_');
  o.value = o.value.replace(r[w],'');
}
 
<!--End of Added by ManuSoman-->

</script>
</HEAD>

<BODY  onload="document.f.j_username.focus();" >

<form name="f" action="<c:url value='j_spring_security_check'/>" method="POST">
		<table frame="box">
		<fieldset>
		
			<legend>Log in</legend>
			<%
			if ((request.getParameter("login_error") != "")&&(request.getParameter("login_error") != null)) {%>
			 	<div class="errorlogin">			
					Your login attempt was not successful, try again.
				</div>		             
			<%  } 		 
			%>
		

			<table >						
			</tr>
			<tr>
			<td><label for="j_username">User Name</label></td>
 			<td><input type="text" name='j_username'    value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td>

			</tr>
			<tr>
			<td><label for="password">Password</label></td>
			<td><input type="password" name='j_password'></td>
			</tr>
			
			<tr>			
			<td colspan="2"  align="right">
			<!--<div align="left" style="padding-right:48px;">-->
			<input type="button" align="Right" onClick="doLogin();"  width="10" value="Login" />
			<!--</div>-->
			</td>
			<tr>			
			<tr>
			<td colspan="2"><hr></td>	
			</tr>
			
			<td>			
			<!--<label for="remember_me" style="padding: 0;"> &nbsp;&nbsp;&nbsp; &nbsp; Remember me?</label>
			<input type="checkbox" id="remember_me"  name="remember_me" style="position: relative; top: 3px; margin: 0; "/>
			-->
			<a href="ForgotPassword.jsp">Forgot Password?</a>
			</td>			
			
			<td align="right"><img src="../images/StaffReg.gif">	<a href="Account.jsp">Staff Registration</a></td>			
			</tr>
			</table>			

			
			
			<br />
	
				


		</fieldset>
		
	</form>
	<div>
	  
	  <!--<c:if test="${not empty param.login_error}">
	    <div>			
	     Your login attempt was not successful, try again.<br/>
	     Reason: <c:out value="${fn:replace(SPRING_SECURITY_LAST_EXCEPTION.message, 'Bad credentials', 'Your User Name or Password entered is incorrect.')}"/>
	    </div>			
       </c:if>-->
	   
		
	   
    </div>
</BODY>
</HTML>






	

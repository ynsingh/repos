<!-- 
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 * Author: Devendra Singhal
-->
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
  <head>
  		<script src='dwr/util.js'></script>
		<script src='dwr/engine.js'></script>
		<script src='dwr/interface/ShowMessage.js'></script>
 		
  <script type="text/javascript">
  window.history.forward();
	 function onLoginClick(){
		var uname=dwr.util.getValue("username");
		var pass=dwr.util.getValue("password");		
		if(uname==null || uname==""){
			alert("Please Enter Username");
			return false;
		}
		else if(pass==null || pass==""){
			alert("Please Enter Password");
			return false;
		}	
		else{
			return true;			
		}					
	}
</script>
    <title>Online OMR Evaluation System Login</title>
  </head>
  <body>      
  	<table width="100%">
  		<tr>
  			<td><jsp:include page="header.jsp"></jsp:include></td>
  		</tr>
  	</table>  	
 <hr> <hr color="#466dbd" style="height:15px"><br/><br/><br/><br/>
  <table align="right">
  	<tr><td>
  	<html:form action="/LoginAction">
  		<table align="right" cellspacing="5" cellpadding="3" bgcolor="#466dbd" height="150" width="300">
  			<tr><th colspan="2" align="center"><b><u>Welcome</u></b></th></tr>
  			<tr>
  				<td width="20%"><b>UserName:</b></td>
  				<td width="80%"><input type="text" id="username"  name="username" size="20"></td>
  			</tr>
  			<tr>
  				<td><b>Password:</b></td>
  				<td><input type="Password" id="password" name="password" size="20"></td>
  			</tr>
  			<tr>
  				<td><input type="submit" value="Login" name="submit" onclick="return onLoginClick()"></td>
  				<td></td>
  			</tr>    				
  		</table> 
  		</html:form>	
  		</td></tr>
  		<tr><td>		
  		<table align="right" width="300">
  			<tr>
  				<td>
  					<%String loginDetail=(String)request.getAttribute("loginDetail");
						if(loginDetail!=null){ %>
							<font color="red" size="2"><%=loginDetail %></font>
						<%}
					 %>
  				</td>
  			</tr>	
  		</table>
  		</td></tr>  
  	</table>
		
</body>
</html>

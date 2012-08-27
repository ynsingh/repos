<head>

<title>Login</title>
<style type='text/css' media='screen'>
body {
text-align: center;
font-family:Arial, Helvetica, sans-serif;
font-size: 73%;
background-image:url(../images/themesky/bg.jpg);
background-repeat: repeat-x;
background-color: #d5e5ed;
}


/* Login Bar Start */
.loginLink{
	margin: 0 auto; 
	width:847px;
	height:30px;
	background-color: #b9dcef;
	font-size:14px;
	text-align:right;
	font-weight:bold;
	color:#007500;
	}
.loginLink span { 
  text-align:right;
  padding-right: 15px;
  }
	
.loginLink span A:link { color:#01518e; font-weight:bold; text-decoration: none}
.loginLink span A:visited { color:#01518e; font-weight:bold; text-decoration: none}
.loginLink span A:active { color:#01518e; font-weight:bold; text-decoration: none}
.loginLink span A:hover { color:#01518e; font-weight:bold; text-decoration: none}
/* Login Bar Start */

/* Banner Start */
.banner {
	margin: 0 auto; 
	width:847px;
	height:158px;
	background-color: #b9dcef;
	background-image: url(../images/themesky/indexnew_02.jpg);
	background-repeat: no-repeat;  
	}
/* Banner end */

/* login Start */
.login {
	margin: 0 auto; 
	width:847px;
	height:260px;
	text-align: right;
	background-color: #b9dcef;
	background-image: url(../images/themesky/login_03.jpg);
	background-repeat: no-repeat;  
	}
.loginUserPass table {
    position: relative;
	float: right;
	right:80px;
	top: 30px;
	width:36%;
	height:18%;
	color: #01518e;
	font-size:95%;
	font-family: verdana, Helvetica, sans-serif;
	font-weight: bold;
	letter-spacing: 1px;
	text-align:left;
	
	}
/* login end */
/* dashboardBar Start */
.dashboardBar {
	margin: 0 auto; 
	width:847px;
	height:48px;
	background-color: #b9dcef;
	background-image: url(../images/themesky/indexnew_04.jpg);
	background-repeat: no-repeat;  
	}
.dashboardBar span { 
  text-align:right;
  
  }
/* dashboardBar end */
  .errors{ font-weight: bold;
           font-family:Arial, Helvetica, sans-serif;
		   padding:0px 0px 8px; 0px;
	       font-size:12px;color:red}
</style>

<g:javascript src="jquery.js"/>
<script type="text/javascript">
        function validateForm(thisform)
        {
          if ($.trim($('#user_login').val())=='') {alert("Please enter Username");$('#user_login').focus();return false;}
		   var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;   /* For Email Address*/
		   if ($.trim($('#user_login').val())!='')
            {
             if(reg.test($.trim($('#user_login').val())) == false) 
			 {
			    alert("Please enter a valid Email Address for Username");$('#user_login').select();return false;
			 }
		    }	 
					  
          if ($.trim($('#user_pass').val())=='') {alert("Please enter Password");$('#user_pass').focus();return false;}
          thisform.submit();
        }
     /*
	    function Redirect()
        {
          window.location="../register/index";
        }
		*/
		
		
		
	function Redirect()
	{
		var val = document.getElementById('language').value;
		window.location="auth?lang="+val;		
		return true;
	}
	
 </script>


</head>





<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<!-- Login Bar Start-->
<div class="loginLink">&nbsp;</div>
<!-- Login Bar end-->

<!-- Banner Start-->
<div class="banner"></div>
<!-- Banner end-->

<!-- Login Start-->
<div class="login">
<div class="loginUserPass">

 <form action='${postUrl}' method='POST' id='loginform' name="loginform">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
   <g:if test='${flash.message}'>
   <tr>
      <th width="49%" scope="col" colspan="2">	 
		<div class='errors'  align="center">${flash.message}</div>	 
	</th>
      </tr> 
  </g:if>
	<tr>
      <th width="49%" height="40" scope="col"><g:message code="default.language.label"/>:</th>
      <th width="51%" scope="col"><select id="language" onchange="Redirect()" style="font-size: 105%;">
	       <g:if test="${session.lang == ''}">
	             <option id="1" value="" selected ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
	 			
           </g:if>
	       <g:if test="${session.lang == 'en'}">
	             <option id="1" value="en"  selected><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi"><g:message code="default.lang.Hindi.label"/></option>
	 			 
           </g:if>
	  			  <g:if test="${session.lang == 'ml'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" selected ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi"><g:message code="default.lang.Hindi.label"/></option>
	 			
           </g:if>
             <g:if test="${session.lang == 'hi'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" selected><g:message code="default.lang.Hindi.label"/></option>
	 			 
           </g:if>
           <g:if test="${session.lang == 'tl'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
	 			       
	 		</g:if>
           <g:if test="${session.lang == 'it'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
	 						 
           </g:if>
           
           <g:if test="${session.lang == 'es'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
	 			 
           </g:if>
           <g:if test="${session.lang == 'fr'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
	 			 			 
           </g:if>
           <g:if test="${session.lang == 'ru'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
	 			 			 
           </g:if>
           <g:if test="${session.lang == 'zh'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
	 			 	 			 
           </g:if>
			</select>
     </th>
      </tr>
    <tr>
      <th width="49%" height="40" scope="col"><g:message code="default.username.label"/></th>
      <th width="51%" scope="col">
      <input type='text' class='input' name='j_username' id='user_login'
					 value='${request.remoteUser}'size="20"  onkeypress="if(event.keyCode=='13')return 	validateForm(document.loginform);" />
     </th>
      </tr>
    <tr>
      <th height="40" scope="row"><g:message code="default.password.label"/></th>
      <td>
       	<input type='password' class='input' name='j_password' id='user_pass' size="20" onkeypress="if(event.keyCode=='13')return validateForm(document.loginform);" />
    </td>
      </tr>
    <tr>
      <th height="25" scope="row">&nbsp;</th>
      <td><input type='button' value='<g:message code="default.login.label"/>' id="wp-submit" class="formbutton" onclick="return validateForm(document.loginform)" /></td>
      </tr>
    <tr>
   	 	<td>
		<br />&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="${createLink(action:'index',controller:'register')}" id="wp-submit"  class="button-primary"><g:message code="default.register.label"/>!</td>
      <td>  
	  <br />&nbsp;	 	
		<a href="${createLink(action:'index',controller:'forgotPassword')}" id="wp-submit"  class="button-primary"><g:message code="default.forgot.label"/></a>
      </td>
      </tr>
      <tr>
   	 <td height="30" colspan="2">  
   	 		<a href="${createLink(action:'brihaspatiLogin',controller:'login')}" id="wp-submit"  class="button-primary"><g:message code="default.BrihaspatiRemoteAuthentication.label"/></a>
      </td>
      </tr>
      
      <th  scope="row">&nbsp;</th>
      <td align="right"><font size="1" face="Times">
      </td>
      </tr>   
  </table>
     </form>
</div>
	
	
</div>
<!-- Login end-->

<!-- dashboardBar Start-->
<div class="dashboardBar">
<br>
<label style="text-align: center;font: bold 9px Verdana;color: #104d6b;">Developed by Amrita University under ERP, NME ICT, MHRD</label>
</div>


</body>

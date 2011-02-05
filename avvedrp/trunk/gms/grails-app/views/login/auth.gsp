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
	right:90px;
	top: 30px;
	width:28%;
	height:20%;
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
</style>


<script language="JavaScript" type="text/javascript">

	function breakout_of_frame()
	{
	  // see http://www.thesitewizard.com/archive/framebreak.shtml
	  // for an explanation of this script and how to use it on your
	  // own website
	  if (top.location != location) {
	    top.location.href = document.location.href ;
	  }
	}
	

	function Redirect()
	{
		var val = document.getElementById('language').value;
		window.location="auth?lang="+val;
		var index = document.getElementById('language').selectedIndex ; 
		
		document.getElementById('language').options[index].text;
		return true;
	}
</script>
</head>





<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="breakout_of_frame()">

<!-- Login Bar Start-->
<div class="loginLink">
<g:if test='${flash.message}'>
				
			<div class='login_message'  align="center">${flash.message}</div>
			</g:if>
</div>
<!-- Login Bar end-->

<!-- Banner Start-->
<div class="banner"></div>
<!-- Banner end-->

<!-- Login Start-->
<div class="login">
<div class="loginUserPass">


  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <form action='${postUrl}' method='POST' id='loginForm' class='cssform'>
    <tr>
	    <td height="42">
	    <label for="language"><g:message code="default.language.label"/>:</label>
	    </td>
       <td>
	       <select id="language" onchange="Redirect()" style="font-size: 105%;">
	       <g:if test="${session.lang == ''}">
	             <option id="1" value="en" selected ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
	 			 <option id="4" value="tl" ><g:message code="default.lang.Tamil.label"/></option>
	 			 <option id="5" value="it" ><g:message code="default.lang.Italian.label"/></option>
	 			 <option id="6" value="es" ><g:message code="default.lang.Spanish.label"/></option>
	 			 <option id="7" value="fr" ><g:message code="default.lang.French.label"/></option>
	 			 <option id="8" value="ru" ><g:message code="default.lang.Russian.label"/></option>
	 			 <option id="9" value="zh" ><g:message code="default.lang.Chinese.label"/></option>
           </g:if>
	       <g:if test="${session.lang == 'en'}">
	             <option id="1" value="en"  selected><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi"><g:message code="default.lang.Hindi.label"/></option>
	 			 <option id="4" value="tl" ><g:message code="default.lang.Tamil.label"/></option>
	 			 <option id="5" value="it" ><g:message code="default.lang.Italian.label"/></option>
	 			 <option id="6" value="es" ><g:message code="default.lang.Spanish.label"/></option>
	 			 <option id="7" value="fr" ><g:message code="default.lang.French.label"/></option>
	 			 <option id="8" value="ru" ><g:message code="default.lang.Russian.label"/></option>
	 			 <option id="9" value="zh" ><g:message code="default.lang.Chinese.label"/></option>
           </g:if>
	  			  <g:if test="${session.lang == 'ml'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" selected ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi"><g:message code="default.lang.Hindi.label"/></option>
	 			 <option id="4" value="tl" ><g:message code="default.lang.Tamil.label"/></option>
	 			 <option id="5" value="it" ><g:message code="default.lang.Italian.label"/></option>
	 			 <option id="6" value="es" ><g:message code="default.lang.Spanish.label"/></option>
	 			 <option id="7" value="fr" ><g:message code="default.lang.French.label"/></option>
	 			 <option id="8" value="ru" ><g:message code="default.lang.Russian.label"/></option>
	 			 <option id="9" value="zh" ><g:message code="default.lang.Chinese.label"/></option>
           </g:if>
             <g:if test="${session.lang == 'hi'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" selected><g:message code="default.lang.Hindi.label"/></option>
	 			 <option id="4" value="tl" ><g:message code="default.lang.Tamil.label"/></option>
	 			 <option id="5" value="it" ><g:message code="default.lang.Italian.label"/></option>
	 			 <option id="6" value="es" ><g:message code="default.lang.Spanish.label"/></option>
	 			 <option id="7" value="fr" ><g:message code="default.lang.French.label"/></option>
	 			 <option id="8" value="ru" ><g:message code="default.lang.Russian.label"/></option>
	 			 <option id="9" value="zh" ><g:message code="default.lang.Chinese.label"/></option>
           </g:if>
           <g:if test="${session.lang == 'tl'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
	 			 <option id="4" value="tl" selected><g:message code="default.lang.Tamil.label"/></option>
	 			 <option id="5" value="it" ><g:message code="default.lang.Italian.label"/></option>
	 			 <option id="6" value="es" ><g:message code="default.lang.Spanish.label"/></option>
	 			 <option id="7" value="fr" ><g:message code="default.lang.French.label"/></option>
	 			 <option id="8" value="ru" ><g:message code="default.lang.Russian.label"/></option>
	 			 <option id="9" value="zh" ><g:message code="default.lang.Chinese.label"/></option>           
	 		</g:if>
           <g:if test="${session.lang == 'it'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
	 			 <option id="4" value="tl" ><g:message code="default.lang.Tamil.label"/></option>
	 			 <option id="5" value="it" selected><g:message code="default.lang.Italian.label"/></option>
	 			 <option id="6" value="es" ><g:message code="default.lang.Spanish.label"/></option>
	 			 <option id="7" value="fr" ><g:message code="default.lang.French.label"/></option>
	 			 <option id="8" value="ru" ><g:message code="default.lang.Russian.label"/></option>
	 			 <option id="9" value="zh" ><g:message code="default.lang.Chinese.label"/></option>	 			 
           </g:if>
           
           <g:if test="${session.lang == 'es'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
	 			 <option id="4" value="tl" ><g:message code="default.lang.Tamil.label"/></option>
	 			 <option id="5" value="it" ><g:message code="default.lang.Italian.label"/></option>
	 			 <option id="6" value="es" selected><g:message code="default.lang.Spanish.label"/></option>
	 			 <option id="7" value="fr" ><g:message code="default.lang.French.label"/></option>
	 			 <option id="8" value="ru" ><g:message code="default.lang.Russian.label"/></option>
	 			 <option id="9" value="zh" ><g:message code="default.lang.Chinese.label"/></option>	 			 
           </g:if>
           <g:if test="${session.lang == 'fr'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
	 			 <option id="4" value="tl" ><g:message code="default.lang.Tamil.label"/></option>
	 			 <option id="5" value="it" ><g:message code="default.lang.Italian.label"/></option>
	 			 <option id="6" value="es" ><g:message code="default.lang.Spanish.label"/></option>
	 			 <option id="7" value="fr" selected><g:message code="default.lang.French.label"/></option>
	 			 <option id="8" value="ru" ><g:message code="default.lang.Russian.label"/></option>
	 			 <option id="9" value="zh" ><g:message code="default.lang.Chinese.label"/></option>	 			 
           </g:if>
           <g:if test="${session.lang == 'ru'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
	 			 <option id="4" value="tl" ><g:message code="default.lang.Tamil.label"/></option>
	 			 <option id="5" value="it" ><g:message code="default.lang.Italian.label"/></option>
	 			 <option id="6" value="es" ><g:message code="default.lang.Spanish.label"/></option>
	 			 <option id="7" value="fr" ><g:message code="default.lang.French.label"/></option>
	 			 <option id="8" value="ru" selected><g:message code="default.lang.Russian.label"/></option>
	 			 <option id="9" value="zh" ><g:message code="default.lang.Chinese.label"/></option>	 			 
           </g:if>
           <g:if test="${session.lang == 'zh'}">
	             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
	 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
	 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
	 			 <option id="4" value="tl" ><g:message code="default.lang.Tamil.label"/></option>
	 			 <option id="5" value="it" ><g:message code="default.lang.Italian.label"/></option>
	 			 <option id="6" value="es" ><g:message code="default.lang.Spanish.label"/></option>
	 			 <option id="7" value="fr" ><g:message code="default.lang.French.label"/></option>
	 			 <option id="8" value="ru" ><g:message code="default.lang.Russian.label"/></option>
	 			 <option id="9" value="zh" selected><g:message code="default.lang.Chinese.label"/></option>	 			 
           </g:if>
			</select>
        </td>
    </tr>
    <tr>
      <th width="49%" height="42" scope="col"><g:message code="default.UserName.label"/>:</th>
      <th width="51%" scope="col">
      
         <input type="text" size="16" name='j_username' id='j_username' value='${request.remoteUser}' />
     </th>
      </tr>
    <tr>
      <th height="42" scope="row"><g:message code="default.Password.label"/>:</th>
      <td>
       <input type='password' size="16" name='j_password' id='j_password' />
    </td>
      </tr>
    <tr>
      <th height="30" scope="row">&nbsp;</th>
      <td><input type='submit' value='<g:message code="default.login.label"/>' /></td>
      </tr>
    <tr>
   	 	<td>
  	 	 <g:link style="font-size:95%;font-weight: normal;text-decoration:none;color: #7D053F;" onmouseover="this.style.textDecoration ='underline';" onmouseout="this.style.textDecoration='none';" controller="user" action="newUserCreate"><g:message code="default.Register.label"/> </g:link>
      </td>
      <td>
  	 	 <g:link style="font-size:95%;font-weight: normal;text-decoration:none;color: #7D053F;" onmouseover="this.style.textDecoration ='underline';" onmouseout="this.style.textDecoration='none';" controller="user" action="forgotPassword"><g:message code="default.Forgotyourpassword.head"/> </g:link>
  	 	 
      </td>
      
      </tr>
      <tr>
      <td colspan="2">
      &nbsp;
      </td>
      </tr>
      <tr>
      <td colspan="2">
      <g:link style="font-size:95%;font-weight: normal;text-decoration:none;color: #7D053F;" onmouseover="this.style.textDecoration ='underline';" onmouseout="this.style.textDecoration='none';" controller="proposal" action="uploadProposalApplication"><g:message code="default.UploadProposal.head"/> </g:link>
      </td>
      </tr>
    <tr>
      <th  scope="row">&nbsp;</th>
      <td align="right"><font size="1" face="Times">
      </td>
      </tr>
      </form>
  </table>
  
</div>
	
	
</div>
<!-- Login end-->

<!-- dashboardBar Start-->
<div class="dashboardBar">
<br>
<label style="text-align: center;font: bold 9px Verdana;color: #104d6b;">Developed by Amrita University under ERP, NME ICT, MHRD</label>
</div>
<!-- dashboardBar end-->

<script type='text/javascript'>
<!--
(function(){
	document.forms['loginForm'].elements['j_username'].focus();
})();
// -->
</script>

</body>

<html dir= ${session.orientation}>
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
	top: 20px;
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
#innerTable {
    position: relative;
    padding-right:5px;
    margin-top:0cm;
	float: right;
	right:18px;
	top: 0px;
	width:95%;
	height:18%;
	color: #01518e;
	font-size:95%;
	font-family: verdana, Helvetica, sans-serif;
	font-weight: bold;
	letter-spacing: 1px;
	text-align:left;
	
	}
#innerTableSec {
     position: relative;
    padding-right:4px;
    margin-top:0cm;
	float: right;
	right:18px;
	top: 0px;
	width:95%;
	height:28%;
	color: #01518e;
	font-size:95%;
	font-family: verdana, Helvetica, sans-serif;
	font-weight: bold;
	letter-spacing: 1px;
	text-align:left;
	
	}

</style>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="breakout_of_frame()">

<div class="openid-loginbox">

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

<table class="innertable" border="0">
<tr>
		  <th  scope="row">&nbsp;</th>
		  <td align="right"><font size="1" face="Times">
		  </td>
</tr>
<tr>
		    <td height="30" width="109px" style="font-size:95%;">
		    <label for="language"><g:message code="default.language.label"/>:</label>
		    </td>
	       <td>
		       <select id="language" onchange="Redirect()" style="font-size: 105%;padding-right:22px;">
		       <g:if test="${session.lang == ''}">
		             <option id="1" value="" selected ><g:message code="default.lang.English.label"/></option>
		 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
		 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
		 			 <option id="4" value="tl" ><g:message code="default.lang.Tamil.label"/></option>
		 			 <option id="5" value="it" ><g:message code="default.lang.Italian.label"/></option>
		 			 <option id="6" value="es" ><g:message code="default.lang.Spanish.label"/></option>
		 			 <option id="7" value="fr" ><g:message code="default.lang.French.label"/></option>
		 			 <option id="8" value="ru" ><g:message code="default.lang.Russian.label"/></option>
		 			 <option id="9" value="zh" ><g:message code="default.lang.Chinese.label"/></option>
		 			 <option id="10" value="ar" ><g:message code="default.lang.Arabic.label"/></option>
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
		 			 <option id="10" value="ar" ><g:message code="default.lang.Arabic.label"/></option>
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
		 			 <option id="10" value="ar" ><g:message code="default.lang.Arabic.label"/></option>
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
		 			 <option id="10" value="ar" ><g:message code="default.lang.Arabic.label"/></option>
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
		 			 <option id="10" value="ar" ><g:message code="default.lang.Arabic.label"/></option>           
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
		 			 <option id="10" value="ar" ><g:message code="default.lang.Arabic.label"/></option>	 			 
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
		 			 <option id="10" value="ar" ><g:message code="default.lang.Arabic.label"/></option>	 			 
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
		 			 <option id="10" value="ar" ><g:message code="default.lang.Arabic.label"/></option> 			 
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
		 			 <option id="10" value="ar" ><g:message code="default.lang.Arabic.label"/></option>	 			 
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
		 			 <option id="10" value="ar" ><g:message code="default.lang.Arabic.label"/></option>	 			 
	           </g:if>
	           <g:if test="${session.lang == 'ar'}">
	                 
		             <option id="1" value="en"  ><g:message code="default.lang.English.label"/></option>
		 			 <option id="2" value="ml" ><g:message code="default.lang.Malayalam.label"/></option>
		 			 <option id="3" value="hi" ><g:message code="default.lang.Hindi.label"/></option>
		 			 <option id="4" value="tl" ><g:message code="default.lang.Tamil.label"/></option>
		 			 <option id="5" value="it" ><g:message code="default.lang.Italian.label"/></option>
		 			 <option id="6" value="es" ><g:message code="default.lang.Spanish.label"/></option>
		 			 <option id="7" value="fr" ><g:message code="default.lang.French.label"/></option>
		 			 <option id="8" value="ru" ><g:message code="default.lang.Russian.label"/></option>
		 			 <option id="9" value="zh" ><g:message code="default.lang.Chinese.label"/></option>
		 			 <option id="10" value="ar" selected><g:message code="default.lang.Arabic.label"/></option>	 			 
	           </g:if>
				</select>
	        </td>
</tr>
<tr>
			<td colspan="2" height="15%" style="padding:0px;display: none;" id='openidLogin'>

				<form action='${openIdPostUrl}' method='POST' autocomplete='off' name='openIdLoginForm'>
					<table id="innerTable" border="0" width="100%">
						
						<tr>
							<td  height="30" width="40px" scope="col"><g:message code="default.OpenID.label" />:</td>
							<td scope="col" style="width:159px;"><input type="text" name="${openidIdentifier}" class="openid-identifier" size="16"/></td>					</tr>
						<tr>
		      				<td height="30" width="109px" scope="row">&nbsp;</th>
		      				
		      				<td height="30" width="20px"><input type='submit' value='<g:message code="default.login.label"/>' /><input type="checkbox" id="toggle" onclick='toggleForms()'/>
								<label for='toggle'>Use OpenID</label>
					        </td>
	      			    </tr>
					</table>
				</form>
				
			  </td>
              
              <td colspan="2" height="15%" style="padding:0px;" id='formLogin'>
			
				<form action='${daoPostUrl}' method='POST' autocomplete='off'>
						<table border="0" id="innerTableSec">
							 <tr>
							    <td height="30" width="109px" style="font-size:95%;">
							    	<label for="language"><g:message code="default.UserName.label"/>:</label>
							    </td>
								<td>
		 					         <input type="text" size="16" name='j_username' id='j_username' value='${request.remoteUser}' />
							    </td>
						      </tr>
						      <tr>
						       <td height="30" width="109px" style="font-size:95%;">
							    	<label for="language"><g:message code="default.Password.label"/>:</label>
							    </td>
								<td>
		 					        <input type='password' size="16" name='j_password' id='j_password' />
							    </td>
							  </tr>
							  <tr>
							      <td>&nbsp;</td>
							      <td><input type='submit' value='<g:message code="default.login.label"/>' />
							     
									<input type="checkbox" id="toggle" onclick='toggleForms()'/>
									<label for='toggle'>Use OpenID</label>
								  </td>
							      
						      </tr>
		            </table>
			  </form>

			</td>
</tr>
<tr>
   	 	<td height="10">
  	 	 <g:link style="font-size:95%;font-weight: normal;text-decoration:none;color: #7D053F;" onmouseover="this.style.textDecoration ='underline';" onmouseout="this.style.textDecoration='none';" controller="user" action="newUserCreate"><g:message code="default.Register.label"/> </g:link>
      </td>
      <td height="10">
  	 	 <g:link style="font-size:95%;font-weight: normal;text-decoration:none;color: #7D053F;" onmouseover="this.style.textDecoration ='underline';" onmouseout="this.style.textDecoration='none';" controller="user" action="forgotPassword"><g:message code="default.Forgotyourpassword.head"/> </g:link>
  	 	 
      </td>
      
      </tr>
      <tr>
      <td colspan="2" height="2">
      
      </td>
      </tr>
      <tr>
      <td colspan="2" height="10">
      <g:link style="font-size:95%;font-weight: normal;text-decoration:none;color: #7D053F;" onmouseover="this.style.textDecoration ='underline';" onmouseout="this.style.textDecoration='none';" controller="proposal" action="notificationList"><g:message code="default.UploadProposal.head"/> </g:link>
      </td>
      </tr>
    <tr>
      <th  scope="row">&nbsp;</th>
      <td align="right"><font size="1" face="Times">
      </td>
</tr>
				
</table>
</div>

<script>
function breakout_of_frame()
{

 if(self.name == "selenium_myiframe" || self.name == "selenium_main"){
	// do nothing in case of selenium both in normal mode and multiwindow mode
}else{
	if (parent != self && parent.name != "selenium") {
	    top.location=self.document.location;
	}

	if ( opener ) {
	  if (opener.top.location != self.document.location ) {
	       top.location=self.document.location;
     	    self.close();
	   }
	}            
}

  // see http://www.thesitewizard.com/archive/framebreak.shtml
  // for an explanation of this script and how to use it on your
  // own website
 
}


function Redirect()
{
	var val = document.getElementById('language').value;
	window.location="auth?lang="+val;
	var index = document.getElementById('language').selectedIndex ; 
	
	document.getElementById('language').options[index].text;
	return true;
}

(function() { document.forms['openIdLoginForm'].elements['openid_identifier'].focus(); })();

var openid = true;

function toggleForms() {
	if (openid) {
		document.getElementById('openidLogin').style.display = '';
		document.getElementById('formLogin').style.display = 'none';
	}
	else {
		document.getElementById('openidLogin').style.display = 'none';
		document.getElementById('formLogin').style.display = '';
	}
	openid = !openid;
}
</script>
</body>
</html>

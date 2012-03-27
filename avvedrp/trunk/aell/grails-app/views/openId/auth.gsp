<html>
<head>
	<meta name='layout' content='main'/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>${grailsApplication.config.page_title}</title> 
<link rel="shortcut icon" type="image/x-icon" href="${hostname}/images/favicon.ico">
<link rel="stylesheet" type="text/css"  href="${hostname}/css/home_styles.css">
<link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
	<title><g:message code="springSecurity.login.title"/></title>
	<style type='text/css' media='screen'>
	#login {
		margin: 15px 0px;
		padding: 0px;
		text-align: center;
	}

	#login .inner {
		width: 340px;
		padding-bottom: 6px;
		margin: 60px auto;
		text-align: left;
		border: 1px solid #aab;
		background-color: #f0f0fa;
		-moz-box-shadow: 2px 2px 2px #eee;
		-webkit-box-shadow: 2px 2px 2px #eee;
		-khtml-box-shadow: 2px 2px 2px #eee;
		box-shadow: 2px 2px 2px #eee;
	}

	#login .inner .fheader {
		padding: 18px 26px 14px 26px;
		background-color: #f7f7ff;
		margin: 0px 0 14px 0;
		color: #2e3741;
		font-size: 18px;
		font-weight: bold;
	}

	#login .inner .cssform p {
		clear: left;
		margin: 0;
		padding: 4px 0 3px 0;
		padding-left: 105px;
		margin-bottom: 20px;
		height: 1%;
	}

	#login .inner .cssform input[type='text'] {
		width: 120px;
	}

	#login .inner .cssform label {
		font-weight: bold;
		float: left;
		text-align: right;
		margin-left: -105px;
		width: 110px;
		padding-top: 3px;
		padding-right: 10px;
	}

	#login #remember_me_holder {
		padding-left: 120px;
	}

	#login #submit {
		margin-left: 15px;
	}

	#login #remember_me_holder label {
		float: none;
		margin-left: 0;
		text-align: left;
		width: 200px
	}

	#login .inner .login_message {
		padding: 6px 25px 20px 25px;
		color: #c33;
	}

	#login .inner .text_ {
		width: 120px;
	}

	#login .inner .chk {
		height: 12px;
	}
	</style>
</head>

<body background="${hostname}/images/bg_tile.jpg" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  >
<table id="Table_01" width="1027" border="0" cellpadding="0" cellspacing="0">
    <tr>
		<td colspan="16">
	         <img  align ="left" style="height:105px;margin-left:0%;" src="${hostname}/images/header-left.jpg"/>
			 <img style="height:105px;width:18.5%;margin-right:-480px;"  src="${hostname}/images/header-tile.jpg"  alt="">
			 <img src="${hostname}/images/header-right.jpg" width="432"  align ="right" style="height:105px;"/>
	</tr>
	<tr>
		<td height="30px" style="background:url(${hostname}/images/top_sec_without_black.jpg)">
		<ul id="menuTop">
		</ul>
		</td>
  </tr>
</table>
<table border="0"  cellpadding="0" cellspacing="0" height="500px" width="1027" bgcolor="white" >
 <tr valign="top">
   <td><img src="${hostname}/images/mainpage_blue_slice1_05.jpg" width="40" height="500px" alt="">
   </td>
   <td>
   <table width="500"  style="margin-top:150px;border: 1px solid #6481AF;" border="1" cellspacing=0 >
       <tr style="background-color: #EDEDED;">
           <td colspan=2 style="background-color:#6481AF; font-size: 13px; color:#ffffff; border: 0px solid #eeeeee;padding: 3px 25px 1px 15px;"><b>Please Login</b>
           </td>
       </tr>
       <tr>    
            <td style="background-color:#EDEDED; font-size: 13px; color:black; border: 1px solid #eeeeee; border-left: 1px solid #eeeeee; padding: 3px 25px 1px 15px;">
               <div style="width:50%"><br/>
                 <p align="right" class="openid-loginbox-useopenid">
					<input type="checkbox" id="toggle" checked='checked' onclick="parent.location='${hostname}/login/auth'"/>
					<label for='toggle'>Use OpenID</label>
				 </p>
                 <form action='${openIdPostUrl}' method='POST' name='openIdLoginForm' class='cssform' autocomplete='off'>
			     <br/>
			     <p>
				    <label for='username'>OpenID:</label>
				    <input type='text' name="${openidIdentifier}" class="openid-identifier"/>
			     </p>
			     <g:if test='${persistentRememberMe}'>
			       <p><br/>
				      <!-- <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
				      <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label> -->
			        </p>
			     </g:if>
			     <br/>
			     <p class="openid-submit" align="center">
				      <input  style="width: 30%;" type='submit' value='Log in'/>
			     </p>
		        </form>
         </div>
     </td>
   </tr>
 </table>
 </td>
 <td>
   <img align="right" src="${hostname}/images/mainpage_blue_slice1_08.jpg" width="45" height="500px" alt="">
 </td>
 </tr>
</table>
</body>
</html>

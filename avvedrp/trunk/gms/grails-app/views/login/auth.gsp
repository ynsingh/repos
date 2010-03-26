<head>

<title>Login</title>
<style type='text/css' media='screen'>
body {
text-align: center;
font-family:Arial, Helvetica, sans-serif;
font-size: 12px;
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
	text-align:right;
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
	top: 45px;
	width:232px;
	height:164px;
	color: #01518e;
	font-size:15px;
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
</head>


<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

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
      <th width="49%" height="51" scope="col">User Name: </th>
      <th width="51%" scope="col">
      
         <input type="text" size="16" name='j_username' id='j_username' value='${request.remoteUser}' />
     </th>
      </tr>
    <tr>
      <th height="53" scope="row">Password : </th>
      <td>
       <input type='password' size="16" name='j_password' id='j_password' />
    </td>
      </tr>
    <tr>
      <th height="52" scope="row">&nbsp;</th>
      <td><input type='submit' value='Login' /></td>
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

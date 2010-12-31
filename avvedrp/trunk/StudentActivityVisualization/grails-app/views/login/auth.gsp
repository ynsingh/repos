<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<title>Login</title>
<style>

.login{   width: 829px; height: 437px; 
 background-image:url(../LoginBG.jpg); 
background-repeat: no-repeat;
margin:5em auto;
font:11px "Lucida Grande",Verdana,Arial,"Bitstream Vera Sans",sans-serif;

}

.login form p{margin-bottom:0; }


.loginConatiner{
	width:250px;
	float:right;
	padding-top:130px;
	padding-right:20px;
	
	}



form{ 

padding:16px 16px 50px 16px;
font-weight:normal;
-moz-border-radius:11px;
-khtml-border-radius:11px;
-webkit-border-radius:11px;
border-radius:5px;
background:#fff;
border:1px solid #e5e5e5;
-moz-box-shadow:rgba(200,200,200,1) 0 4px 18px;-webkit-box-shadow:rgba(200,200,200,1) 0 4px 18px;-khtml-box-shadow:rgba(200,200,200,1) 0 4px 18px;
box-shadow:rgba(200,200,200,1) 0 4px 18px;

}
form .forgetmenot{font-weight:normal;float:left;
margin-bottom:0;}
label{color:#777;font-size:13px;}

form .forgetmenot label{font-size:11px;line-height:19px;}

form .submit,.alignright{float:right;}form p{margin-bottom:24px;}


#nav{text-shadow:rgba(255,255,255,1) 0 1px 0;}
#nav{margin:0 0 0 8px;padding:16px; float:right; color:#333333;}
#nav a, a:hover {color:#333333;}

#user_pass,#user_login,#user_email{font-size:20px;width:97%;padding:3px;margin-top:2px;margin-right:6px;margin-bottom:16px;border:1px solid #e5e5e5;background:#fbfbfb;}

.button-primary{
font-family:"Lucida Grande",Verdana,Arial,"Bitstream Vera Sans",sans-serif;
padding:3px 10px;
border:none;
font-size:12px;
border-width:1px;
border-style:solid;
-moz-border-radius:11px;
-khtml-border-radius:11px;
-webkit-border-radius:11px;
border-radius:11px;
cursor:pointer;
text-decoration:none;
}


input{color:#555;}

.clear{clear:both;}

.errors{ font-weight: bold;color:red}

</style>
<script type="text/javascript" src="../jquery-1.3.1.js"></script>
<script type="text/javascript">
        function validateForm(thisform)
        {
          if ($.trim($('#user_login').val())=='') {alert("Please enter Username");$('#user_login').focus();return false;}
          if ($.trim($('#user_pass').val())=='') {alert("Please enter Password");$('#user_pass').focus();return false;}
          thisform.submit();
        }
        function Redirect()
        {
          window.location="../register/index";
        }
 </script>



</head>

<body bgcolor="#e4e4e4">

<div  class="login">
<div class="loginConatiner">

  <g:if test="${flash.message}">
  <div class="errors">${flash.message}</div>
  </g:if>

<form action='${postUrl}' method='POST' id='loginform' name="loginform">
	<p> 
		<label>Username
		<input type='text' class='input' name='j_username' id='user_login' value='${request.remoteUser}'size="20"  onkeypress="if(event.keyCode=='13')return validateForm(document.loginform);" />
		  
	    </label> 
	</p> 
	<p> 
		<label>Password
		<input type='password' class='input' name='j_password' id='user_pass' size="20" onkeypress="if(event.keyCode=='13')return validateForm(document.loginform);" />
		</label> 
	</p> 
	
	<p class="submit"> 
	      <input type='button' value='Login' id="wp-submit" class="button-primary" onclick="return validateForm(document.loginform)" />
              &nbsp;&nbsp;
              <input type='button' value='Register' id="wp-submit" class="button-primary"  onclick="return Redirect()"/>
              <!-- <a href="${createLink(action:'index',controller:'register')}" id="wp-submit"  class="button-primary">Register</a> -->
	</p> 
    
    
    

</form> 






</div>

</div>



</body>



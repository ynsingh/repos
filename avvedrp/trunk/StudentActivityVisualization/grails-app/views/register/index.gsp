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

.loginConatiner {
    float: right;
    padding-right: 20px;
    padding-top: 100px;
    width: 380px;
}

.dialog
{
  padding-left: 25px;
}
.buttons
{
  padding: 5px 0px 0px 230px;
}

form{

padding:16px 16px 20px 16px;
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

#login .inner .login_message {color:red;}
input{color:#555;}

.clear{clear:both;}
.errors{ font-weight: bold;color:red}
</style>
<script type="text/javascript" src="../jquery-1.3.1.js"></script>
<script>
        function validateForm(thisform)
        {
                      var errorstr = '';
                      var msgstr = "Sorry, we cannot complete your request.\nKindly provide us the missing or incorrect information enclosed below.\n\n";
                      var i=0;
                      var flag=0;
                      var fcs_fld=new Array();
                      var illegalChars = /\W/; /* For User name validtion*/
                      var illegalChars1 = /[\W_]/;  /* For Password validtion*/
                      var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;   /* For Email Address*/


                      if ($.trim($('#username').val())=='') { errorstr += "* Please enter Username.\n";  fcs_fld[i]='username'; i++;}
                      if ($.trim($('#username').val())!='')
                      {
                       if(reg.test($.trim($('#username').val())) == false) { errorstr += "* Please provide valid Email Address for Username.\n";   fcs_fld[i]='username'; i++;}
                      }
                      if ($.trim($('#passwd').val())=='')  { errorstr += "* Please enter Password.\n";  fcs_fld[i]='passwd'; i++;}
                      if ($.trim($('#passwd').val())!='')
                      {
                         if ($.trim($('#passwd').val()).length < 6) { errorstr += "* Password should contain atleast 6 characters.\n";   fcs_fld[i]='passwd'; i++;}
                      }
                      if ($.trim($('#passwd').val()).length >= 6 && $.trim($('#passwd').val())!='') {
                      if ($.trim($('#repasswd').val())=='') { errorstr += "* Please Confirm Password.\n";   fcs_fld[i]='repasswd'; i++;}
                      }
                      if ($.trim($('#repasswd').val())!='' ) {
                      if ($.trim($('#passwd').val()) != $.trim($('#repasswd').val())) { errorstr += "- Password Mismatch.\n";  fcs_fld[i]='repasswd'; i++;}
                      }
                      if ($.trim($('#institute').val())=='') { errorstr += "* Please select the Institute.\n";  fcs_fld[i]='institute'; i++;}
                      if ($.trim($('#ucode').val())=='') { errorstr += "* Please enter the Member Code.\n";  fcs_fld[i]='ucode'; i++;}
                      if ($.trim($('#captcha').val())=='') { errorstr += "* Please enter the Captcha code.\n";  fcs_fld[i]='captcha'; i++;}

              if (errorstr != '')
              {
                      msgstr = msgstr + errorstr;
                      alert(msgstr);
                      $('#'+fcs_fld[0]+'').focus();$('#'+fcs_fld[0]+'').select();
                      return false;
              }
              else
              {
                      thisform.submit();
                      return true;
              }
        }

         function Redirect()
        {
          window.location="../login/auth";
        }
</script>

</head>

<body bgcolor="#e4e4e4">

<div  class="login">
<div class="loginConatiner">
<div class="body">
  <h2><font color="#958707">New Registration</font></h2>
		<g:form action="save" name="register">
		<div class="dialog">
                <g:if test="${flash.message}">
		<div class="errors">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${person}">
		<div class="errors">
	        <g:renderErrors bean="${person}" as="list" />
		</div>
		</g:hasErrors>
                <br />
		<table>
                <tbody>

			<tr class='prop'>
				<td valign='top' class='name'><label for='username'>Username:<font color="red">*</font></label></td>
				<td valign='top' class='value ${hasErrors(bean:person,field:'username','errors')}'>
					<input type="text" name='username' id='username' value="${person?.username?.encodeAsHTML()}"/>
				</td>
			</tr>

			

			<tr class='prop'>
				<td valign='top' class='name'><label for='passwd'>Password:<font color="red">*</font></label></td>
				<td valign='top' class='value ${hasErrors(bean:person,field:'passwd','errors')}'>
					<input type="password" name='passwd'  id='passwd' value=""/>
				</td>
			</tr>

			<tr class='prop'>
				<td valign='top' class='name'><label for='enabled'>Confirm Password:<font color="red">*</font></label></td>
				<td valign='top' class='value ${hasErrors(bean:person,field:'passwd','errors')}'>
					<input type="password" name='repasswd' id='repasswd' value=""/>
				</td>
			</tr>

                          <tr class='prop'>
				<td valign='bottom' class='name'><label for='code'>Institute:<font color="red">*</font> </label></td>
				<td valign='top' class='name'>
					 <g:select optionKey="id" optionValue="name" from="${Institute.list()}" id="institute" name="institute" id="institute" noSelection="['':'-------- select --------']" value="${siteId}"  style="width:145px;"  ></g:select>
				</td>
			</tr>

                       <tr class='prop'>
				<td valign='top' class='name'><label for='user code'>Member Code:<font color="red">*</font></label></td>
				<td valign='top' class='name'>
					<input type="text" name='ucode' id='ucode' value=""/>
				</td>
			</tr>


			<tr class='prop'>
				<td valign='bottom' class='name'><label for='code'>Enter Code: <font color="red">*</font></label></td>
				<td valign='top' class='name'>
					<input type="text" name="captcha" id="captcha" size="8"/>
					<img src="${createLink(controller:'captcha', action:'index')}" align="absmiddle"/>
				</td>
			</tr>

                       <tr class='prop'>
				<td colspan="2">&nbsp;</td>
			</tr>
                        <tr class='prop'>
				<td valign='bottom' class='name'>&nbsp;</td>
				<td valign='top' class='name'>
				  <input type='button' value='Login' id="wp-submit" class="button-primary"  onclick="return Redirect()"/> 
                                  &nbsp;&nbsp;<input type='button' value='Register' id="wp-submit" class="button-primary"  onclick="return validateForm(document.register)"/>
				</td>
			</tr>
		</tbody>
		</table>
		</div>		
		</g:form>
	</div>

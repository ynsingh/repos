<?php 
extract($_POST);
session_start();
include_once('config.php');
include('defined_constants.php');
include('common_model_functions.php');
include('execute_query.php');

?>
<!--<link href="<?php echo getThemeCss('main.css'); ?>" rel="stylesheet" type="text/css">-->
<script type="text/javascript">
document.getElementById("msg").innerHTML="";
function closewindow() {
	window.close()
	}
//not using this function now
function redirAfterLogin() {
	window.opener.location.href = 'index.php';
	window.close();
}
function redirctToHome() {
	window.opener.location.href = 'index.php';
	window.close();
}
function maximiseWindow(redPath)
{
	//window.moveTo(0, 0);
  	//window.resizeTo(screen.availWidth,screen.availHeight);
	displayMessage('Redirecting... Please wait.');
	window.location.href = redPath;
}
function displayMessage(msg)
{
	document.getElementById("msg").innerHTML=msg;
}
function setOriginalSize()
{
	//window.moveTo(180, 130);
  	//window.resizeTo(1000,430);
}
function validate()
{
	var username=document.getElementById('username');
	var password=document.getElementById('password');
	if(username.value=="")
	{
		alert("Please provide username.");
			username.focus();
			return(false);
	}
	if(password.value=="")
	{
		alert("Please provide password.");
			password.focus();
			return(false);
	}
	return true;
}
</script>
<div style="font-family:Verdana, Arial, Helvetica, sans-serif; font-size:14px"><b>Sign In using</b></div>
<form id="form1" name="form1" method="post"  onsubmit="return validate();">
  <table width="432" height="156" border="0" align="center" class="noBorderTable">
  <!--<tr class="textHead2">
    <td align="right">Username: </td>
    <td colspan="2"><input name="username" type="text" id="username" size="30" /></td>
  </tr>
  <tr>
    <td align="right">Password:</td>
    <td colspan="2"><input name="password" type="password" id="password" size="30" /></td>
  </tr>
  <tr> 
  	  <td colspan="0" align="center" width="21%">&nbsp;&nbsp;&nbsp;&nbsp;</td>   
    <td colspan="0" align="left" width="28%"><input type="submit" name="submit" id="submit" value="&nbsp;Login&nbsp;"/></td>
    <td colspan="0" align="center" width="51%">&nbsp;&nbsp;</td>
  </tr>-->
  <tr  height="80px">
    <td colspan="2" align="center">
    	<b><a href="javascript:maximiseWindow('<?php echo ROOT_URL."admin/oauth/?openid_mode=checkid_setup&openid_identifier=google.com/accounts/o8/id"; ?>')"><img src="images/google_button.png" alt="" border="0" /></b></a>
    </td>
    <td colspan="2" align="center" >    
    	<img src="images/yahoo_blur.png" alt="image"  border="0" />
    </td>
  </tr>
  <tr  height="80px">
  	<td colspan="2" align="center" >    
    	<img src="images/facebook_blur.png" alt="image"  border="0" />
    </td>
    <td colspan="2" align="center" >    
    	<img src="images/myspace_blur.png" alt="image"  border="0" />
    </td>
  </tr>
   <tr  height="80px">
  	<td colspan="2" align="center" >    
    	<img src="images/openid_blur.png" alt="image"  border="0" />
    </td>
    <td colspan="2" align="center" >    
    	<img src="images/twitter_blur.png" alt="image"  border="0" />
    </td>
  </tr>
  </table>
 <div id="msg" style="font-family:Verdana, Arial, Helvetica, sans-serif; font-size:12px"></div>
<?php
//$_SESSION['sessUserRoleName']='admin';
/*if($_SESSION['sessUserRoleId']==ROLE_ID_ADMIN)
{?>
<script language="javascript">
redirAfterLogin();
</script>
<?php }*/
//from oauth provider
//$_GET['email_id']="ammamukesh@gmail.com";
if($_GET['action']=="cancelled")
{
	$dispMsg=$_GET['msg'];
	echo "<script language=\"javascript\">displayMessage('$dispMsg')</script>";
}
if($_SESSION['sessEmail'])
{ 
	$_SESSION['sessFullName']=$_GET['fst'].' '.$_GET['lst'];	
	$userId=getUserIdFromUserName($_SESSION['sessEmail']);
	if($userId)
	{	
		$userRoleId=getUserRoleId($userId);
		$_SESSION['sesssUserId']=$userId;
		//$userRoleName=getUserRole($userId);
		$_SESSION['sessUserRoleId']=$userRoleId;
		//$_SESSION['sessUserRoleName']=$userRoleName;
		if(($_SESSION['sessUserRoleId']==ROLE_ID_ADMIN) || ($_SESSION['sessUserRoleId']==ROLE_ID_UNI_ADMIN))
		{	//
			echo "<script language=\"javascript\">
			redirAfterLogin();
			</script>";					
		}
		else
		{
			echo "<script language=\"javascript\">
			redirctToHome();
			</script>";
			//echo $msg='Invalid username or password, please try again';
		}
		exit();
	}
	else
	{
		insertUserCredentials($_SESSION['sessEmail'] ,$_SESSION['sessEmail'],UNIVERSITY_ID);
		echo "<script language=\"javascript\">
		redirctToHome();
		</script>";
		//echo $msg='Invalid username or password, please try again';
	}
}
//
/*if($submit)
{
	
	$userRoleName=AuthenticateUserRole($username, $password);
	if($userRoleName=='admin')
	{
		$_SESSION['sessUserRoleName']=$userRoleName;
		?>
        <script language="javascript">
		redirAfterLogin();
        </script>
        <?php		
	}
	else
	{
		echo $msg='Invalid username or password, please try again';
	}

}*/
?>
</form>



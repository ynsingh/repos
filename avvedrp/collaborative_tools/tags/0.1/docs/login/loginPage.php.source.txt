<style type="text/css"> 
#blanket {
   background-color:#111;
   opacity: 0.65;
   position:absolute;
   z-index: 9001; /*ooveeerrrr nine thoussaaaannnd*/
   top:0px;
   left:0px;
   width:100%;
}
#popUpDiv {
	position:absolute;
	background-color:#eeeeee;
	width:300px;
	height:300px;
	z-index: 9002; /*ooveeerrrr nine thoussaaaannnd*/
}
</style>
<div id="blanket" style="display:none;"></div> 
	<div id="popUpDiv" style="display:none;height: 150px; width: 250px; left: 0px; top: 0px;"> 
    <form id="form1" name="form1" method="post"  onsubmit="return validate();">
    <div style="float: left; text-align: left;background:#99CCFF; height:19px;width:230px;">&nbsp;<b>Admin Login</b></div>
   <!-- <div style="display:inline-block; background:#0099FF; width:154px;height:20px">&nbsp;</div>-->
	<div style="float: right; text-align: right;background:#99CCFF; height:19px"><a href="#" onclick="popup('popUpDiv')"><img src="<?php echo getThemeImage('close.gif')?>" border="0" height="18" width="20" /></a></div>
        <br />
        <br />
        <table style="padding-left:10px; padding-right:10px" border="0">
        <tr class="textHead2">
        <td><b>Username</b></td>
        <td><input type="text" name="username" id="username" /></td>
        </tr>
        <tr>
        <td><b>Password</b></td>
        <td><input type="password" name="password" id="password" /></td>
        </tr>
        <tr>
        <td></td>
        <td align="center"><input type="submit" name="submit" id="submit" value="Login" style="width:50px"/> 
        <input type="button" name="cancel" id="cancel" value="Cancel" onclick="popup('popUpDiv')" style="width:50px"/></td>
        </tr>
        <tr><td colspan="2"><div id="msgDiv"></div></td></tr>
        </table>
        
        </form>
		
	</div>	
 <?php 
extract($_POST);
if($submit)
{
	
	$userRoleName=AuthenticateUserRole($username, $password);
	if($userRoleName=='admin')
	{
		$_SESSION['sessUserRoleName']=$userRoleName;
		header("Location:?pg=HOME");
		?>
        
        <!--<script language="javascript">
		redirAfterLogin();
        window.close()
        </script>-->
<?php
	}

	else
	{
		?><script language="javascript"> 
		var msg="Invalid username or password, please try again";
		popup('popUpDiv');
		displayMsg('msgDiv', msg);</script>
		<?php
		//$_SESSION['msg']='Invalid username or password, please try again';
		?><!--<script language="javascript">  popup('popUpDiv')</script>-->
	}

	 



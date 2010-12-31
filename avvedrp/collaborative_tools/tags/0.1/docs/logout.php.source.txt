<?php 
include('config.php');
include('common_model_functions.php');
//
//echo "Current DIrectory:".getRootUrl();
//print_r($_COOKIE);
session_start();
if (isset($_COOKIE[session_name()]))
{
	setcookie(session_name(), '', time()-42000, '/');
	
}
//
foreach ($_COOKIE as $i => $value)
{
	setcookie($i, '', time()-42000, '/');
	//echo $i;
}
//echo "<br>after clear cookie:";
//print_r($_COOKIE);
session_unset();
session_destroy();

//echo "Loaction:".getRootUrl();
//header("Location:".getRootUrl());
?>
<script language="javascript">
alert("You are suceessfully logged out");
//window.location.href='http://www.google.co.in/accounts/ClearSID?continue=http://www.google.co.in/accounts/Logout';
window.location.href='<?php echo ROOT_URL;?>';
</script>




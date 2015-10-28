<?session_start();
	if(!isset($_SESSION['loginuserid']))
		{
		header("location: index.php");
		}
	else
		{
		session_unregister("uid");
		session_unregister("pword");
		session_unset();
		session_destroy();
		header("location: index.php");
		}
?>

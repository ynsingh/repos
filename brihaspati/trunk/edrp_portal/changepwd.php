<html>
<head>
<title>Update a Record in MySQL Database</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

<?php
if ($_POST["new_pwd"] == $_POST["con_pwd"]) 
{
if(isset($_POST['update']))
{
$dbhost = 'localhost';
$dbuser = 'arvind';
$dbpass = 'arvind';
$conn = mysql_connect($dbhost, $dbuser, $dbpass);
if(! $conn )
{
  die('Could not connect: ' . mysql_error());
}

$old_pwd = $_POST['old_pwd'];
$old_pwd = md5($old_pwd);
$new_pwd = $_POST['new_pwd'];
$new_pwd = md5($new_pwd);

$sql = "UPDATE member ".
       "SET password = '$new_pwd'".
       "WHERE password = '$old_pwd'" ;

mysql_select_db('simple_login');
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not update data: ' . mysql_error());
}
header("Location:changepwd.php?errorMssg=".urlencode("Password Updated successfully"));
mysql_close($conn);
}
else
{
		$errorMssg = $_GET['errorMssg'];
                echo "<form method=\"post\" action=\"$_PHP_SELF\" class=\"login\">";
                echo "<p>";
                echo "<label for=\"old_pwd\">Old-Pwd</label>";
                echo "<input type=\"text\" name=\"old_pwd\" >";
                echo "</p>";

                echo "<p>";
                echo "<label for=\"new_pwd\">New-Pwd</label>";
                echo "<input type=\"password\" name=\"new_pwd\" >";
                echo  "</p>";
                echo "<p>";
                echo "<label for=\"con_pwd\">Confirm-Pwd</label>";
                echo "<input type=\"password\" name=\"con_pwd\" >";
                echo  "</p>";

                echo "<p class=\"login-submit\">";
                echo "<button type=\"submit\"value=\"update\" name=\"update\" class=\"login-button\">update</button>";
                echo "</p>";
		echo "<p class=\"forgot-password\"><a href=\"admin.php\">Go to LoginPage</a></p>";
		echo "<p class=\"forgot-password\">$errorMssg <p>";
                echo "</form>";

}
}
else{
header("Location:changepwd.php?errorMssg=".urlencode("Please enter same Password in New-Pwd and Confirm-Pwd Boxes"));
}
?>
</body>
</html>

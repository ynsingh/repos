	<html>
	<head>
	  <meta charset="utf-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	  <title>Admin Login</title>
	  <link rel="stylesheet" href="css/style.css">
	</head>
	<body>
	<?php

		$errorMssg = $_GET['errorMssg'];
		echo "<form method=\"post\" action=\"index.php\" class=\"login\">";
		echo "<p>";
		echo "<label for=\"login\">Username</label>";
		echo "<input type=\"text\" name=\"username\" >";
		echo "</p>";

		echo "<p>";
		echo "<label for=\"password\">Password:</label>";
		echo "<input type=\"password\" name=\"password\" >";
		echo  "</p>";
	
		echo "<p class=\"login-submit\">";
		echo "<button type=\"submit\"value=\"login\" name=\"login\" class=\"login-button\">Login</button>";
		echo "</p>";

		echo "<p class=\"forgot-password\"><a href=\"changepwd.php\">Change your password</a></p>";
		echo "<p class=\"forgot-password\">$errorMssg <p>";
		echo "</form>";
		?>
  
	</body>
	</html>

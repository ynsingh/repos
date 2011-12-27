<?php
  /**
   * This is the default layout
   */
?>
<html>
	<head>
		<title>GIS for ERP</title>
		<script lang="javascript" type="text/javascript" src="<?=HTTP_ROOT_DIR?>js/jquery.js"></script>
		<script lang="javascript" type="text/javascript" src="<?=HTTP_ROOT_DIR?>js/jquery.notifyBar.js"></script>
		<link href="<?=HTTP_ROOT_DIR?>css/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="<?=HTTP_ROOT_DIR?>css/jquery.notifyBar.css" type="text/css" media="screen"  />
		<?=isset($message)?$message:'';//If there is any message, display it?>
	</head>
	<body>
		<header>
			<h1><a href="<?=url('','')?>">GIS for ERP</a></h1>
			Educational Resource Planning
			<ul id="links">
				<?if($this->loggedInUser):?>
					<li><a href="<?=url('institute','register')?>">Institute Registration</a></li>
					<li><a href="<?=url('institute','edit')?>">Institute Editing</a></li>
					<li><a href="<?=url('visualize','')?>">Visualize</a></li>
					<li><a href="<?=url('user','logout')?>">Logout</a></li>
				<?else:?>
					<li><a href="http://202.141.40.216:8081/openid/register">User Registration</a></li>
					<li><a href="<?=url('visualize','')?>">Visualize</a></li>
					<li><a href="<?=url('user','login')?>">Login</a></li>
				<?endif;?>
			</ul>
		</header>
		<content>
			<?=$content?>
		</content>
		<footer>
			Copyright 2011 - IIT Roorkee.
		</footer>
	</body>
</html>
 

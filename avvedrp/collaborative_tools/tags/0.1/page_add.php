<?php
$bsub=$_GET['bsub'];
switch ($bsub)
	{
		case 'project':
			include('project.php');
			break;
		case 'msg':
			include('msg.php');
			break;
		case 'remote_trigger':
			include('remote_trigger.php');
			break;
		case 'search':
			include('search.php');
			break;	
		default:
			echo "Invalid url.";
			break;		
	}
?>    
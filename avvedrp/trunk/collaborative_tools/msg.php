<?php



$msg=$_GET['msg'];
if($msg=='remote')
{
	$rootUrl=ROOT_URL;
	$expId=71;
	$url_redirect=getExperimentUrl($rootUrl, $expId, $expContentType=1);
	
	unset($_SESSION['sessJobId']);
	
	echo "The scheduled time for this remote trigger experiment has expired";
	echo "<br/>You will be automatically redirected to the experiment page in few seconds. If not please click here.";
	
	header("Refresh: 5;URL=$url_redirect");
}
else
{
	echo "Some unexpected error occurred, please try again.";
}
?>
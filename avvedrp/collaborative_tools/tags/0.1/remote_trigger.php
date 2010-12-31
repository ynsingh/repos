<?php 

session_start();
$userToken=$_GET['token'];
if($userToken)
{
	$srartTime=date("Y-m-d H:i:s");	
	$tokenDetails=getTokenDetails($srartTime, $userToken);	
	$jobId=$tokenDetails[0];
	$expId=$tokenDetails[1];
	$equipmentId=$tokenDetails[2];
	$jobStatus=$tokenDetails[3];
	if(count($tokenDetails)>0)
	{
		
		if($jobStatus=='R')
		{
			updatEquipmentStatus($jobId,'S');
			$_SESSION['sessJobId']=$jobId;
			$expUrl=getExperimentUrl(ROOT_URL,$expId, SIMULATOR_ID);
			header("location: $expUrl");
		}
		else
		{
			$msg= "This experiment schedule has been already taken or expired";
		}
	}
	else
	{
		$msg= "You have no experiment scheduled at this time.";
	}
}
else
{
	$msg="Invalid attempt";
}

?>
<div><b><?php echo $msg?></b></div>
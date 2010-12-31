<?php 
/**
   * This is an include file to selecte subject based on user. Admistrator can select university, so as to view subjects in different university. Rest of the users can view the subjects in that university, in which the user is assigned with.
   * @author Sreejith S R
   * @version 24-11-2010
*/
if($_SESSION['sessUserRoleId']==ROLE_ID_ADMIN)
{
	if($_SESSION['sess_selUniversityId'])
	{
		$allSubject=getAllSubject($_SESSION['sess_selUniversityId']);
	}
	else
	{
			$allSubject=getAllSubject(UNIVERSITY_ID);
	}
}
elseif($_SESSION['sessUserRoleId']==ROLE_ID_UNI_ADMIN)
{
	
	if($userId)
	{
		$userDetails=getUserDetails($userId);
		$universityId=$userDetails[2];
		
	}
	if($universityId)
	{
		$allSubject=getAllSubject($universityId);	
	}
}
elseif($userId!="")
{
	$allSubject=getUserSubject($userId);	
}
else
{
	$allSubject="";
}
?>
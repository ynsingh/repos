<?php
getHeader();
echo "<link href=\"".getThemeCss('tabs.css')."\" rel=\"stylesheet\" type=\"text/css\">";
echo "<div align=\"left\" style=\"position: relative; margin:auto; width: 1024px; background-color:#e3f2fc\">";
?>
<table width="100%">
<tr><td width="50%" align="left" valign="top"><?php displayBreadcum($arrItems,$arrLink,$breadcumStartText,$seperator); ?></td>
<td width="45%" align="right"><?php displayWelcomeMessage($_SESSION['sessFullName'],$_SESSION['sessUserRoleId']); ?> </td>
<td width="5%" align="left"><?php displayLogOutButton(); ?></td></tr>
</table>
<?php

echo "<br>";
displayHeadText(getExperimentName($_GET['sim']));
$contentDetails=getAllContentItems($_GET['sim']);
$arrContentItem=array();
$arrContentLink=array();
$arrContentImage=array();
//
for($i=0;$i<count($contentDetails);$i++)
{
	$arrContentItem[$i]=$contentDetails[$i][1];
	if(getExperimentItemName($_GET['cnt'])==$arrContentItem[$i])
	{
		$currentItem=$i;
	}
	$arrContentLink[$i]=$simLink."&cnt=".$contentDetails[$i][0];
	$arrContentImage[$i]=getMenuIcons('content_'.$contentDetails[$i][0]);
}
//
$contentDetails=getContentIdAndStatus($_GET['cnt'], $_GET['sim']);
$externalType=$contentDetails[0][1];
$externalLink="";
if($_GET['cnt']==SIMULATOR_ID and getExperimentTypeId($_GET['sim'])==REMOTETRIGGER_ID)
{
	include('remote_trigger_include.php');
}	
else
{
	if($contentDetails[0][1]=='Y')//external status
	{
		$externalLink=getContentLink($contentDetails[0][0]);
	}
	else
	{
	//
	$contentText=htmlspecialchars_decode(getContentDescription($contentDetails[0][0]));
	}
}	
//
//
displayTabEnvironment($arrContentItem,$arrContentLink,$arrContentImage,$currentItem,$contentText,$externalLink);
//
echo "</div>";
getFooter();
?>
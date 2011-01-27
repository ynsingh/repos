<form name="form1" id="form1" method="post" action="" >
<?php 
	extract($_POST);
	$expDuration=getRemoteDuration($_GET['sim']);
	$currSrartTime=date("Y-m-d H:i:s");//'2010-10-21 16:00:00';
	$currEndTime=date("Y-m-d H:i:s",strtotime("+".$expDuration." minutes"));
	$currTime=date("Y-m-d H:i:s");	
	if($_SESSION['sessJobId'])
	{
		$jobDetails=getJobDetails($currTime, $_SESSION['sessJobId']);
		$jobExpId=$jobDetails[0];
		$jobEquipmentId=$jobDetails[1];
		$jobExpStatus=$jobDetails[2];	
		$jobStartTime=$jobDetails[3];	
		$jobEndTime=$jobDetails[4];	
				
	}	
	if($_SESSION['sesssUserId'])
	{
		$userDetails=getUserDetails($_SESSION['sesssUserId']);
		$userEmail=$userDetails[1];
	}	
	else
	{
		$userEmail=$email;
	}
/////////	
	if($submit)
	{
		$srartTimeDB=date("Y-m-d H:i:s",strtotime($srartTime));
		$srartTimeStamp=strtotime($srartTime);
		$endTimeStamp=$srartTimeStamp+($expDurationUser*60);
		$endTimeDB=date("d-M-Y H:iA",$endTimeStamp);
		$equipmentAvailableToReserve=getFreeEquipment($srartTimeDB, $endTimeDB, $_GET['sim']);
		if($equipmentAvailableToReserve=='NULL')
		{
			$flagDisplayTable=1;
			$msg= "Sorry, the equipment is not available during this time period.";
		}
		else
		{
			if($email=="" or $srartTimeStamp=="")
			{
				$flagDisplayTable=1;
				$msg= "Email id and start time is needed to schedule the experiment for you";
			}
			else
			{
				if($expDurationUser>2*$expDuration)
				{
					$flagDisplayTable=1;
					$msg= "Experiment duration can not be greater than twice the orginal value.";
				}
				else
				{
				
				$authorization_token=md5($userEmail.time());
				$subject="Experiment Link";
				$mailMsg="Hello,<br/><br/> Your slot for the experiment has been successfully reserved. Please use the following link to access the equipment between ".$srartTime." and ".$endTimeDB.".<br/><br/>".ROOT_URL."?pg=bindex&bsub=remote_trigger&token=".$authorization_token."<br/><br/>Regards,<br/>".PROJECT_NAME." Team";	
				//$headers="";
				$fromEmail=PROJECT_EMAILID;
				//Referrer
				//echo $mailMsg;
				$headers  = 'MIME-Version: 1.0' . "\r\n";
				$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
				// Additional headers
				$headers .= "From: ".PROJECT_NAME."<$fromEmail>" . "\r\n";
				$headers .= "Reply-To: ".PROJECT_NAME."<$fromEmail>". "\r\n";
				$headers .= "To: <$email>" . "\r\n";				
				//$headers .= 'Cc: birthdayarchive@example.com' . "\r\n";
				//$headers .= 'Bcc: birthdaycheck@example.com' . "\r\n";
				$mailSend=mail($email, $subject, $mailMsg, $headers);
				$msg1="<div class=\"textHead1\">Message:</div><br/><br/><div class=\"text\">Congratulations! The requested experiment has been scheduled for you. An email has been send to your email address '".$email."'. Click on the specified link in the email during the allotted time period to access the equipment.<br/><br/> If this email is not seen in your inbox, please check the spam folder and take a few seconds to add our email to safe list.<br/><br/>".PROJECT_NAME." Team</div>";			
				insertRemoteTriggerSchedule($equipmentAvailableToReserve, $email, $srartTimeDB, $endTimeDB,$authorization_token, 'R');
				//$_SESSION['sessJobId']=$dbObject->getLastInsertId();
				}
			}
			//send email
		}
			
	}
	elseif($jobExpId==$_GET['sim'])
	{
		//if direcetd from email link. 
		$externalLink=getEquipmentUrl($jobEquipmentId);
	}
	else
	{
		
		$equipmentAvailable= getFreeEquipment($currSrartTime, $currEndTime, $_GET['sim']);
		if($equipmentAvailable=='NULL')
		{
					$flagDisplayTable=1;					
					//if experimet is not available right now. Option to schedule		
							
		}		
		else
		{
			//if experimt is available right now.
			$authorization_token=md5($userEmail."|".time());
			insertRemoteTriggerSchedule($equipmentAvailable, $userEmail, $currSrartTime, $currEndTime,$authorization_token, 'S');
			$_SESSION['sessJobId']=$dbObject->getLastInsertId();
			$externalLink=getEquipmentUrl($equipmentAvailable);
		}	
	}
///////	
	if($flagDisplayTable)
	{
		$durCnt=floor($expDuration/15);
		$arrayValue=$durCnt*15;
		$expDurationArray=array("0"=>$expDuration);
		for($k=0; $k<$durCnt; $k++)
		{		
			$arrayValue=$arrayValue+15;
			array_push($expDurationArray, $arrayValue);
		}
		for($j=0; $j<count($expDurationArray); $j++)
		{
		 	$option.="<option value=\"$expDurationArray[$j] \" >$expDurationArray[$j] </option>";
		}
			
		$contentText="<br />
			<p class=\"textHeadTabs\">".EXP_SCH."<br /><div class=\"tinyTextRedTabs\" >".MSG1."</div></p>
					<form name=\"scheduleForm\" method=\"post\" onsubmit=\"this.form.submit\">
					
					
					
					<div align=\"center\" style=\"width:100%; height:250px; overflow:scroll\">	
					<table border=\"0\" align=\"center\" width=\"75%\">
					<tr>
					<td width=\"3%\" style=\"background-color:#E26568\">&nbsp;</td>
					<td class=\"text\"> - Busy</td>
					</tr>
					</table><br>
					<table align=\"center\" width=\"750px\" class=\"myTable\">					
					<tr class=\"textRptBlueHead\" onmouseover=\"this.style.backgroundColor='#CCE3FF';\">
						<td width=\"15%\" align=\"center\">Date:</td>
						<td>10-12-2010</td>
						<td>11-12-2010</td>
						<td>12-12-2010</td>
						<td>13-12-2010</td>
						<td>14-12-2010</td>
						<td>15-12-2010</td>
						<td>16-12-2010</td>
					</tr>	
					<tr class=\"textRptBlueHead\" onmouseover=\"this.style.backgroundColor='#CCE3FF';\">
						<td width=\"15%\" class=\"textRptBlueHead\">Time</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>	 
					<tr>
						<td class=\"text\">00.00 - 01.00</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>	
					<tr>
						<td class=\"text\">01.00 - 02.00</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">02.00 - 03.00</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">03.00 - 04.00</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">04.00 - 05.00</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td style=\"background-color:#E26568\">&nbsp;</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">05.00 - 06.00</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td style=\"background-color:#E26568\">&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">06.00 - 07.00</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">07.00 - 08.00</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td style=\"background-color:#E26568\">&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">08.00 - 09.00</td>
						<td>&nbsp;</td><td style=\"background-color:#E26568\">&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">09.00 - 10.00</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td style=\"background-color:#E26568\">&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">10.00 - 11.00</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td >&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">11.00 - 12.00</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">12.00 - 13.00</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">13.00 - 14.00</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td style=\"background-color:#E26568\">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">14.00 - 15.00</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">15.00 - 16.00</td>
						<td >&nbsp;</td><td style=\"background-color:#E26568\">&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">16.00 - 17.00</td>
						<td style=\"background-color:#E26568\" >&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td style=\"background-color:#E26568\">&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">17.00 - 18.00</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">18.00 - 19.00</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td style=\"background-color:#E26568\">&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">19.00 - 20.00</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td style=\"background-color:#E26568\">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">20.00 - 21.00</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">21.00 - 22.00</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">22.00 - 23.00</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">23.00 - 24.00</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						 <td class=\"text\">24.00 - 00.00</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td style=\"background-color:#E26568\">&nbsp;</td><td>&nbsp;</td>
						<td >&nbsp;</td><td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					</table>
					</div>
					
							<table  border=\"0\" width=\"100%\" >
							<tr align=\"center\">
							<td><br /><br />
								<table class=\"myTableTabs\" border=\"1\" width=\"45%\" >								
									<tr class=\"textBlueHeadTabs\"><td  colspan=\"2\">Schedule the experiment</td></tr>
									<tr>
										<td align=\"left\" class=\"text\" bgcolor=\"\" width=\"17%\">".START_TIME."</td>
										<td align=\"left\" class=\"text\">
											<input id=\"srartTime\" name=\"srartTime\" type=\"text\" size=\"25%\" readonly=\"true\" value=\"$srartTime\"><a href=\"javascript:NewCssCal('srartTime','ddmmmyyyy','arrow',true,12)\">
											<img src=\"images/cal.gif\" width=\"16\" height=\"16\" alt=\"Pick a date\"></a><br /> 
										</td>
									</tr>
									<tr>
										<td class=\"text\">".EXP_DURATION."</td>
										<td class=\"text\"> 
										<select name=\"expDurationUser\" size=\"1\" id=\"expDurationUser\">
										$option;
										</select> Minutes
										</td>
									</tr>
									<tr>
										<td align=\"left\" class=\"text\">".EMAIL."</td>
										<td class=\"text\"><input type=\"text\" id=\"email\" name=\"email\" value=\"$userEmail\" size=\"25%\" \"/></td>										
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align=\"left\" class=\"text\" ><input type=\"submit\" name=\"submit\" id=\"submit\" value=\"Submit\" onclick=\"return checkSchedule();\" /></td>
									</tr>
								</table>							
							</td></tr> 
							</table>
							</form>";
	}
	if($_SESSION['sessJobId'])
	{
		$jobDetails=getJobDetails($currTime, $_SESSION['sessJobId']);
		$jobExpId=$jobDetails[0];
		$jobEquipmentId=$jobDetails[1];
		$jobExpStatus=$jobDetails[2];	
		$jobStartTime=$jobDetails[3];	
		$jobEndTime=$jobDetails[4];	
		$currExpDuration=strtotime($jobEndTime)-time();
		header("Refresh: $currExpDuration;URL=?pg=bindex&bsub=msg&msg=remote&expId=".$_GET['sim']."");//if experient is alloted, setting the time out of that experiment
		
	}
	$contentText.= "<div border=\"1\" align=\"left\" >$msg1</div>";
	$contentText.= "<div border=\"1\" align=\"center\" class=\"text\">$msg</div>";
	
?>
</form>

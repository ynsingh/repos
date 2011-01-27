<?php
/**
	* This files provides the user interface for Remote Trigger
	* @author Vidya Mohan
	* @version 01-12-2010
*/
?>
<script language="javascript">

function remoteTriggerValidation() 
{	 	
     var duration = document.getElementById('exptDuration');
	 var eqmtnum = document.getElementById('equipmentNum');
	 var arrUrl = document.getElementsByName('equipmentURL[]');
     var tomatch= /http:\/\/[A-Za-z0-9\.-]{1,}\.[A-Za-z]{2}/
	 var arrNewUrl = new Array();
	 if(duration.value=="" || duration.value==null)
	 {
		 alert("Enter the duration");
		 duration.focus();
		 return false;
	 }
	 if(eqmtnum.value=="" || eqmtnum.value==null)
	 {
		 alert("Enter the number of equipment(s)");
		 eqmtnum.focus();
		 return false;
	 }
	 for(k=0; k<arrUrl.length; k++)
	 {		 
		 if (!tomatch.test(arrUrl[k].value))
		 {			 
				alert("Enter a valid URL");
				arrUrl[k].focus();
				return false; 
						 
		}
	 }
	 /*for(m=0;m<arrUrl.length;m++){
		arrNewUrl[m] = arrUrl[m].value.replace('');		
	 }*/
	 for(i=0; i<arrUrl.length; i++)
	 {		
		for(j=i+1; j<arrUrl.length; j++)
		{			
			if(arrUrl[i].value == arrUrl[j].value)
			{
				alert("Enter different URL");
				arrUrl[j].focus();
				return false;
			}
		}
	}
}

</script>
<?php
include('../execute_query.php');
include('../dbmanage.php');
include('../common_model_functions.php');
extract($_POST);
//
$experimentId = "50";
//

if($remote_submit=='Save')
{
	$remoteURL = getRemoteTriggerURL();
	for($k=0;$k<count($remoteURL);$k++){
		$trigger[] = $remoteURL[$k][0];
	}
	foreach($equipmentURL as $value){
		$urlArray[] = trim($value," ");
	}
	$matchUrl = array_intersect_key(array_flip($trigger), array_flip($urlArray));
	$cntUrl = count($equipmentURL);	
	if(count($matchUrl)==0) 
	{
		if($exptDuration!="" and $equipmentNum!="" and $cntUrl!=0) 
		{
			for($j=0;$j<$cntUrl;$j++){
				addRemoteEquipmentDetails($experimentId,$urlArray[$j]);
			} 
			addRemoteTriggerDetails($experimentId,$exptDuration);
			$msg = "Saved";
		}
	}
	else
	{	
		$matchFlip = array_flip($matchUrl);
		foreach($matchFlip as $match){
			$matchArray[] = $match;
		}
	}
}
//
$remoteExpName = getRemoteTriggerExperimentName($experimentId);
?>
<form name="form1" id="form1" method="post" action="">
<br /><br /><div align="center" style="color:#069"><b>Remote Trigger</b></div><br />
<table border="1" align="center" bordercolor="#99CCFF" width="32%">	
<tr>
<td class="text" width="40%">Experiment Name:</td><td class="text"><?php echo $remoteExpName; ?></td></tr>
<td class="text" width="40%">Experiment Duration:</td><td><input type="text" name="exptDuration"  id="exptDuration" size="15" value="<?php echo $exptDuration; ?>"/></td></tr>
<td class="text" width="40%">Number of Equipment(s):</td><td><input name="equipmentNum" value="<?php echo $equipmentNum; ?>" type="text"  id="equipmentNum" size="15" maxlength="5" 
onchange="this.form.submit();" /></td></tr>
<td class="text" width="40%">URL:</td><td>
<?php
if($equipmentNum==0)
{
	for($i=0;$i<1;$i++)
	{
?>
	<input type="text"  name="equipmentURL[]" id="equipmentURL[]" size="30" value="<?php echo $equipmentURL[$i]; ?>"/>
<?php   
	}
}
else if($equipmentNum!=0)
{	
	for($i=0;$i<$equipmentNum;$i++)
	{
	?>
     	<input type="text"  name="equipmentURL[]" id="equipmentURL[]" size="30" value="<?php echo $equipmentURL[$i]; ?>"/>
	<?php
	}
}
?>
</td></tr>
<?php
if($equipmentNum!=0)
{
?>
<tr><td colspan="3" align="center">
<input type="submit" value="Save" id="remote_submit" name="remote_submit" style="width:50px" onclick="return remoteTriggerValidation();" />&nbsp;&nbsp;
<input type="button" value="Cancel" id="cancel" name="cancel" onclick=""  style="width:51px" />
</td></tr>    
<?php
}
else
{
?>
    <tr><td colspan="3" align="center"><input type="submit" value="Save" id="remote_submit" name="remote_submit" style="width:50px" disabled="disabled"/>&nbsp;&nbsp;
    <input type="button" value="Cancel" id="cancel" name="cancel" onclick=""  style="width:51px" disabled="disabled" />
    </td></tr>    
<?php
}
?>
</table><br />
<?php
$msgCount = count($matchArray);
if($msgCount!=0)
{
?>
    <div align="center" style="color:#C00" class="text"><?php echo "Sorry, the following url(s) already exist. Please Re Enter."; ?></div>
    <?php
        for($n=0;$n<$msgCount;$n++)
        { 	 			
        ?>
            <div align="center" style="color:#C00" class="text"><?php echo $matchArray[$n]; ?></div>
        <?php
        }
}
?>

<div align="center" style="color:#C00" class="text"><?php echo $msg; ?></div>

</form>








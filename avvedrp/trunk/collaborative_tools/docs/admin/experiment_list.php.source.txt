<?php 
/**
	* This files List the experiments under a subtopic and provides option to add, delete experiment and edit existing experiment name and description
	* @author Sreejith S R 
	* @version 30-08-2010
*/?>
<script language="javascript"> 
function deleteConfirm(formName,id,name)
{
	
	var answer = confirm ("This will delete the selected experiment. Are you sure, you want to proceed?")
	if(answer)
	{
		document.form1.deleteData.value=id;
		document.form1.submit();			
	}
	
	
}

</script>
<?php 
/**
	* This files list the experiment name and description under the selected sub-topic.
	* @author Sreejith S R
	* @version 25-08-2010
*/
$allTopic="";
//Getting all active subject from avl_subject_master
//$allSubject=getAllSubject();
include('get_subject_include.php');


//
extract($_POST);
if ($subjectId=='') 
{
	$subjectId=$_GET['subject']; 
}
if ($topicId=='') 
{
	$topicId=$_GET['topicId']; 
}
if ($subTopicId=='') 
{
	$subTopicId=$_GET['subTopicId']; 
}
$expEditId=$_GET['expEditId'];
//
if($addExp=='Add New')		
{
//Add New Experiment
	$checkExperiment=checkExperimentName($expName, $subTopicId);//returns true if the experiment exist under the same subtopic
	if(!$checkExperiment)
	{
		insertExperimentMaster($expName, $expDesc, $expType, 'A');
		$newExpId=mysql_insert_id() ;
		insertExperimentSubtopicRelation($newExpId, $subTopicId);
		insertContentDetails($newExpId);
		header("Location: ?pg=EDIT_EXP&subject=$subjectId&topicId=$topicId&subTopicId=$subTopicId&exp=$newExpId");
	}
	else
	{
		$msg= "Experiment already exist under this subtopic";
		
	}
}
else
{
	$msg="";
}
if($edit_submit=='Save')
{
	
	updateExp($expEditId, $editedName, $editedDesc);
}
if($deleteData)
{
	deleteExp($deleteData);
}
//
?>

<form name="form1" id="form1" method="post" action="#">
<table border="1" width="100%" style="vertical-align:top" class="noBorderTable">
    <tr ><td  align="center" colspan="4">&nbsp;</td></tr>
    <tr class="textHead2">
      <td colspan="3" width="15%"><?php echo SEL_SUBJECT?></td>
        <td>
        <select style="width:252px" name="subjectId" size="1" id="subjectId" onChange="this.form.submit()">
        <?php 
            //displaying subject selection drop down
            for($i=0; $i<count($allSubject); $i++){
                $selsubId="";
                if($allSubject[$i][0]==$subjectId)
                    {
                        $selsubId='selected';
                    } 
                    else
                    {$selsubId='';}?>
        <option <?php echo $selsubId?> value="<?php echo $allSubject[$i][0] ?>" ><?php echo $allSubject[$i][1] ?></option>
        <?php }?>    
        </select>
        </td>
    </tr>
 <?php 
    //while acessing the page from side menu, selecting the 1st subject to display topic
    if($subjectId=='')
    {
    $subjectId=$allSubject[0][0];
    }
    $allTopic=array();
	if($subjectId)
	{
    	$allTopic=getAllTopics($subjectId);
	
	
	
?>
    <tr class="textHead2">
        <td colspan="3"><?php echo SEL_TOPIC?></td>
        <td>
        <select style="width:252px" name="topicId" size="1" id="topicId" onChange="this.form.submit()">
        <?php 
            //displaying topic selection drop down
            for($j=0; $j<count($allTopic); $j++){
                $seltopicId="";						
                if($allTopic[$j][0]==$topicId)
                    {
                        $seltopicId='selected';
                    } 
                    else
                    {$seltopicId='';}?>
        <option <?php echo $seltopicId?>  value="<?php echo $allTopic[$j][0] ?>" ><?php echo $allTopic[$j][1] ?></option>
        <?php }
		}?>    
        </select>
        </td>
    </tr>
<?php 

		
//if changing the subject only, selecting 1st topic from topic list
if($_SESSION['sess_subject']!=$subjectId)
{
    $_SESSION['sess_subject']="";
    $topicId=$allTopic[0][0];
}
$_SESSION['sess_subject']=$subjectId;
//while acessing the page from side menu, selecting the 1st topic to display subtopic
if($topicId=='')
{
    $topicId=$allTopic[0][0];
}

if($topicId)
$allSubTopic=getAllSubTopics($topicId);
?>
  <!-- -->
    <!--<tr  class="textHead2">
        <td colspan="3"><?php echo SEL_SUB_TOPIC?></td>
        <td>
        <select style="width:252px" name="subTopicId" size="1" id="subTopicId" onChange="this.form.submit()">
        <?php 
            //displaying topic selection drop down
            $j=0;
            for($j=0; $j<count($allSubTopic); $j++){
                $selsubTopicId="";						
                if($allSubTopic[$j][0]==$subTopicId)
                    {
                        $selsubTopicId='selected';
                    } 
                    else
                    {$selsubTopicId='';}?>
        <option <?php echo $selsubTopicId?>  value="<?php echo $allSubTopic[$j][0] ?>" ><?php echo $allSubTopic[$j][1] ?></option>
        <?php }?>    
        </select>
        <?php 
        //
        if($_SESSION['sess_topic']!=$topicId)
        {
            $_SESSION['sess_topic']="";
            $subTopicId=$allsubTopic[0][0];
        }
        $_SESSION['sess_topic']=$topicId;
        if($subTopicId=='')
        {
            $subTopicId=$allSubTopic[0][0];
        }
        ?>
        </td>
    </tr>-->
 
<!-- -->       
</table>	<br />




<table><tr><td><input type="hidden" name="deleteData" id="deleteData"/></td></tr></table>
<table width="100%" border="1" class="myTable">
  <?php

//
if($subTopicId)
{	
    $allExperiment=getExperimentDetails($subTopicId);
}	
    //
    $k=0;
    ?>
  <tr class="textRptBlueHead" bgcolor="#CCE3FF" onmouseover="this.style.backgroundColor='#CCE3FF';">
    <td width="5%"><?php echo SERIAL_NO;?></td>
    <td width="25%"><?php echo EXPERIMENT."&nbsp;".NAME;?></td>
    <td ><?php echo EXPERIMENT."&nbsp;".DESCR;?></td>
    <td width="10%"><?php echo TYPE;?></td>
    <td width="5%"><?php echo EDIT;?></td>
    <td width="7%"><?php echo DELETE;?></td>
  </tr>
  <?php
    for($k=0;$k<count($allExperiment);$k++){
    ?>
  <tr  class="text">
    <td align="center"><?php echo $k+1; ?></td>
    <td><a href="?pg=EDIT_EXP&subject=<?php echo $subjectId?>&topicId=<?php echo $topicId?>&subTopicId=<?php echo $subTopicId?>&exp=<?php echo $allExperiment[$k][0]?>"><?php echo $allExperiment[$k][1]?></a></td>
    <td><?php echo $allExperiment[$k][2]?></td>
    <td><?php echo $allExperiment[$k][4]?></td>
    <td align="center"><?php if($editExpPrivilege)
	{
?>
        <a href="?pg=EXPERIMENT&expEditId=<?php echo $allExperiment[$k][0]?>&subject=<?php echo $subjectId?>&topicId=<?php echo $topicId?>&subTopicId=<?php echo $subTopicId?>&t=<?php echo time();?>#2"> <img src="<?php echo getThemeImage('Edit.gif')?>" title="Edit" alt="Edit" border="0"  width="20" height="20" /> </a>
        <?php 
	}else
	{
?>
        <img style="opacity: .4;" src="<?php echo getThemeImage('Edit.gif')?>" title="Edit" alt="Edit" border="0"  width="20" height="20" />
        <?php
	}
?>
    </td>
    <td align="center" ><?php
	if($deleteExpPrivilege)
	{
?>
        <a href="javascript:deleteConfirm(this,'<?php echo $allExperiment[$k][0]?>');"> <img src="<?php echo getThemeImage('Delete.gif')?>" title="Delete Experiment"  width="20" height="20"/> </a>
        <?php
	}
	else
	{
?>
        <img style="opacity: .4;" src="<?php echo getThemeImage('Delete.gif')?>" title="Delete Experiment"  width="20" height="20"/>
        <?php            
	}
?>
    </td>
  </tr>
  <?php 
    } 			 
    ?>
</table>
<br />



<table border="0" align="" width="100%">
 	<?php 
	if($addExpPrivilege and $subTopicId)
	{
	?>
    	<tr align="left">
           	<td class="textHead1">
			<a href="?pg=EXPERIMENT&subject=<?php echo $subjectId?>&topicId=<?php echo $topicId?>&subTopicId=<?php echo $subTopicId?>&addNewExp=1&addNew=1&t=<?php echo time();?>#2"> <img src="<?php echo getThemeImage('add.gif')?>" width="16" height="16" /> Add new <?php echo EXPERIMENT?></a>
       		</td>
    	</tr>
	    <tr>
        	<td>    
        	<a name="2" id="2"></a>    
			<?php 
            $page_link="?pg=EXPERIMENT&subject=$subjectId&topicId=$topicId&subTopicId=$subTopicId";
            if($_GET['addNewExp']==1 and count($_POST)<1)
            {
                if($subTopicId)
                {
                $experimentType=getExperimentType();
                ?>     	
						  
				<table border="0" width="60%" style="border:dotted">
					<tr class="textHead1"><td colspan="2" align="center"><?php echo ADD_EXP?> </td></tr>
			
					<tr class="textHead2">
					<td width="20%">Experiment Name</td>
					<td><input type="text" name="expName" id="expName" style="width:295px" value="<?php echo $expName; ?>" /></td>
					</tr>
					<tr class="textHead2">
					<td width="35%">Experiment Description</td>           
					<td><textarea name="expDesc" id="expDesc" rows="5" cols="35"><?php echo $expDesc;?></textarea> </td>
					</tr>
					<tr class="textHead2">
					<td width="20%">Experiment Type</td>           
					<td>
				   <select style="width:252px" name="expType" size="1" id="expType" >
					<?php for($i=0;$i<count($experimentType);$i++)
							{?>
							<option value="<?php echo $experimentType[$i][0]?>" ><?php echo $experimentType[$i][1]?></option>
					<?php   }?>        
					</select>
					</td>
				</tr>
				<tr><td colspan="2" align="center"><input type="submit" value="Add New" id="addExp" name="addExp" style="width:80px" onclick="return checkAddExp();" />&nbsp;&nbsp;<input type="button" value="Cancel" id="cancel" name="cancel"  onclick="window.location.href='<?php echo $page_link?>'" style="width:50px"/></td>
				</tr><br />
                </table>
			
			<?php } 
                
        	}
		
	}
	if($editExpPrivilege)
	{
	?>
        <table  width="100%">
            <tr>
                <td align="center">
                <?php
                
                if($expEditId!="" and count($_POST)<1)
                {
                $getDetails=getExpDetails($expEditId);
                //
                include('edit_option.php');
                
                }?>
                <div class="textRed"><?php echo $msg?></div>
                <br /><br />
                </td>
            </tr>
        </table>
<?php
	}
?>
        </td>
    </tr>
</table>


</form>	

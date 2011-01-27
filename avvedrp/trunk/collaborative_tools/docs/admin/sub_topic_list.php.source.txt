<?php 
/**
	* This files list the subtopics under a topic and provide option to delete, add and edit subtopic name and description.
	* @author Sreejith S R 
	* @version 30-08-2010
*/?>
<script language="javascript"> 
function deleteConfirm(formName,id,name)
{
	
	var answer = confirm ("This will delete the Sub-topic '"+name+"' .Are you sure, you want to proceed?")
	if(answer)
	{
		document.form1.deleteData.value=id;
		document.form1.submit();			
	}
	
	
}

</script>
<?php 
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
$subTopicEditId=$_GET['subTopicEditId'];
//
if($edit_submit=='Save')
{
	
	updateSubTopic($subTopicEditId, $editedName, $editedDesc);
}
if($addSubmit=='Add' and $addName!="")
{
	$subTopicExist=subTopicExist($topicId, $addName);
	if(!$subTopicExist)
	{
		addSubTopic($topicId, $addName, $addDesc);
	}
	else
	{
		$msg="This Sub-topic already exist in this Topic";
	}	
}
//
if($deleteData)
{
	deleteSubTopic($deleteData);
}
//
?>

<form name="form1" id="form1" method="post" action="">
<table border="0" width="100%" class="noBorderTable">
<tr><td colspan="2">&nbsp;</td></tr>
<tr class="textHead2">
    <td width="15%"><?php echo SEL_SUBJECT?></td>
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
    <td><?php echo SEL_TOPIC?></td>
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
</table>	<br />

<table border="0"><tr><td><input type="hidden" name="deleteData" id="deleteData"/></td></tr></table>
<table border="0" width="100%" class="myTable">
<tr class="textRptBlueHead" onmouseover="this.style.backgroundColor='#CCE3FF';">       
    <td width="5%" ><?php echo SERIAL_NO;?></td>
    <td width="25%" ><?php echo NAME;?></td>
    <td ><?php echo DESCRIPTION;?></td>
    <td width="5%"><?php echo EDIT;?></td>
    <td width="7%"><?php echo DELETE;?></td>        
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
{
    $allSubTopic=getAllSubTopics($topicId);
    //
    for($k=0;$k<count($allSubTopic);$k++){
    ?>
        <tr class="text">
            <td align="center"><?php echo $k+1;?></td>
            <td>
                <a href="?pg=EXPERIMENT&subject=<?php echo $subjectId?>&topicId=<?php echo $topicId?>&subTopicId=<?php echo $allSubTopic[$k][0]?>"><?php echo $allSubTopic[$k][1]?>
                </a>
            </td>
            <td ><?php echo $allSubTopic[$k][2]?></td>
            <td align="center">
            <?php 
			if($editSubTopicPrivilege)
			{
			?>
                <a href="?pg=SUB_TOPIC&subTopicEditId=<?php echo $allSubTopic[$k][0]?>&subject=<?php echo $subjectId; ?>&topicId=<?php echo $topicId?>&t=<?php echo time();?>#2">
            <img src="<?php echo getThemeImage('Edit.gif')?>" title="Edit" alt="Edit"  border="0" width="20" height="20">
                </a>
             <?php
			}else
			{
			 ?>
             <img style="opacity: .4;" src="<?php echo getThemeImage('Edit.gif')?>" title="Edit" alt="Edit"  border="0" width="20" height="20">
             <?php
			}
			 ?>
            </td>
            <td align="center">
            <?php 
			if($editSubTopicPrivilege)
			{
			?>
                <a href="javascript:deleteConfirm(this,'<?php echo $allSubTopic[$k][0]?>','<?php echo $allSubTopic[$k][1]?>');"><img src="<?php echo getThemeImage('Delete.gif')?>"  title="Delete Sub-Topic" width="20" height="20"/>           
                </a>
            <?php 
			}
			else
			{
			?>
            <img style="opacity: .4;" src="<?php echo getThemeImage('Delete.gif')?>"  title="Delete Sub-Topic" width="20" height="20"/>     
            <?php 
			}
			?>
            </td> 
        </tr>
     <?php } 
 } else {
 $msg="No sub topics found.";
 }
 ?>
</table> 





<br />
<a name="2" id="2"></a>
<table border="0" align="" width="100%">
<?php
if($addSubTopicPrivilege and $topicId)
{
	
	?>
	<tr align="left">
		<td class="textHead1">
	
	   
	   <a href="?pg=SUB_TOPIC&subject=<?php echo $subjectId?>&topicId=<?php echo $topicId?>&addNew=1&t=<?php echo time();?>#2"> <img src="<?php echo getThemeImage('add.gif')?>" width="16" height="16" /> Add new <?php echo SUB_TOPIC?></a>
	   </td>
	</tr>
	<tr align="center">   
	   <td>
		<?php 
		$page_link="?pg=SUB_TOPIC&subject=$subjectId&topicId=$topicId";
		 if($_GET['addNew']==1 and count($_POST)<1)
		{
			
			include('add_option.php');
			
		}
		?>
		<div style="color:#FF0000"><?php echo $msg;?></div>
		</td>
	</tr>
	<?php
	
}
?>
<tr align="center">
    <td>
         <?php if($subTopicEditId!="" and count($_POST)<1)
            {
            $getDetails=getSubTopicDetails($subTopicEditId);
            include('edit_option.php');
          
            }	?>
    </td>
</tr>
</table>
</form>	
         
      

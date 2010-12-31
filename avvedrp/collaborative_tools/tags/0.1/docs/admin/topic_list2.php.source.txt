<?php 
/**
	* This files List the topic under a subject and provides option to add new topic and edit existing topic details.
	* @author Sreejith S R 
	* @version 30-08-2010
*/
?>
<script language="javascript"> 
function deleteConfirm(formName,topicId,topicName)
{
	
	var answer = confirm ("This will delete the topic '"+topicName+"' .Are you sure, you want to proceed?")
	if(answer)
	{
		document.form1.deleteData.value=topicId;
		document.form1.submit();			
	}
	
	
}

</script>
<link href="<?php echo getThemeCss('main.css'); ?>" rel="stylesheet" type="text/css">
<?php

////$allSubject=getAllSubject();
include('get_subject_include.php');
extract($_POST);
//
if($subjectId=='')
{
	$subjectId=$_GET['subject']; 
}

$topicEditId=$_GET['topicEditId'];
//
//
if($edit_submit=='Save' and $topicEditId!="" and $editTopicPrivilege)
{
	
	updateTopic($topicEditId, $editedName, $editedDesc);
	$msg="Saved";
}
//
if($addSubmit=='Add' and $addName!="" and $addTopicPrivilege)
{
	$topicExist=topicExist($subjectId, $addName);
	if(!$topicExist)
	{
	
		addTopic($subjectId, $addName, $addDesc);
		$topic_id=$dbObject->getLastInsertId();
		addSubTopic($topic_id, $addName, $addDesc);
		$msg="New topic created";
	}
	else
	{
		$msg="This topic already exist in this subject";
	}
}

if($deleteData and $deleteTopicPrivilege)
{
deleteTopic($deleteData);
}
?>

<form name="form1" id="form1" method="post" action="#" >
    <table border="1" width="100%" class="noBorderTable">
    <tr><td colspan="4" >&nbsp;</td></tr>
    <tr  class="textHead2">
        <td width="15%"><?php echo SEL_SUBJECT;?></td>
        <td colspan="3">
        <select style="width:252px" name="subjectId" size="1" id="subjectId" onChange="this.form.submit()">
        <?php for($i=0; $i<count($allSubject); $i++){
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
    </table>	<br />
    <table border="0"><tr><td><input type="hidden" name="deleteData" id="deleteData"/></td></tr></table>
    <table border="1" width="100%" class="myTable">
    <tr class="textRptBlueHead" onmouseover="this.style.backgroundColor='#CCE3FF';" >  
        <td width="5%"><?php echo SERIAL_NO;?></td>
        <td width="25%"><?php echo NAME;?></td>
        <td ><?php echo DESCRIPTION;?></td>
		
       
        	<td width="5%"><?php echo EDIT;?></td>
        
      
       		<td width="7%"><?php echo DELETE;?></td>
       
    </tr>
    <?php 
        if($subjectId=='')
        {
        $subjectId=$allSubject[0][0];
        }
        $allTopic="";
		if($subjectId)
		{
        $allTopic=getAllTopics($subjectId);
        for($j=0;$j<count($allTopic);$j++)
			{
				$allSubTopic=getAllSubTopics($allTopic[$j][0]);
			?>
				<tr class="text">
				<td align="center"><?php echo $j+1 ?></td>
				<td><a href="?pg=EXPERIMENT&subject=<?php echo $subjectId?>&topicId=<?php echo $allTopic[$j][0]?>&subTopicId=<?php echo $allSubTopic[0][0]?>"><?php echo $allTopic[$j][1]?></a></td>
				<td ><?php echo $allTopic[$j][2]?></td>
				
				<td align="center">
				<?php if($editTopicPrivilege)
				{ 
				?>
				<a href="?pg=TOPIC&topicEditId=<?php echo $allTopic[$j][0]?>&subject=<?php echo $subjectId; ?>&t=<?php echo time();?>#2">
				<img src="<?php echo getThemeImage('Edit.gif')?>" title="Edit" width="20" height="20"/> </a>
				<?php	}		
				else{	
				?> 
				<img style="opacity: .4;" src="<?php echo getThemeImage('Edit.gif')?>" title="No privilage" width="20" height="20"/>
				<?php 
				}
				?>        
				
				</td>
				
				<td align="center">
				<?php
				if($deleteTopicPrivilege)
				{
				?>
				<a href="javascript:deleteConfirm(this,'<?php echo $allTopic[$j][0]?>','<?php echo $allTopic[$j][1]?>');"><img src="<?php echo getThemeImage('Delete.gif')?>" title="Delete Topic" width="20" height="20"/>     
				<?php 
				}else{
				?>
				<img style="opacity: .4;" src="<?php echo getThemeImage('Delete.gif')?>" title="Delete Topic" width="20" height="20"/>     
				<?php
				}
				?>
				</a>
				</td>
				
				</tr>
	<?php 
			 }
		}	 
?>
    </table>
    <br />
    <a name="2" id="2"></a>
    <table border="0" align="" width="100%">    
    <?php
	if($addTopicPrivilege and $subjectId)
	{
		?>
			<tr align="left">
				<td class="textHead1">
				<a href="?pg=TOPIC&subject=<?php echo $subjectId?>&topicId=<?php echo $topicId?>&addNew=1&t=<?php echo time();?>#2"> <img src="<?php echo getThemeImage('add.gif')?>" width="16" height="16" /> Add new <?php echo TOPIC?></a>       
				</td>
			</tr>
			
			<tr align="center">   
			   <td>
				<?php 
				$page_link="?pg=TOPIC&subject=$subjectId";
				 if($_GET['addNew']==1 and count($_POST)<1 )
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
            <?php  
				
            
            if($topicEditId!="" and count($_POST)<1 and $editTopicPrivilege)
                {
                $getDetails=getTopicDetails($topicEditId);
                include('edit_option.php');
                //
                }?>	
        </td>
    </tr>
    </table>
</form>




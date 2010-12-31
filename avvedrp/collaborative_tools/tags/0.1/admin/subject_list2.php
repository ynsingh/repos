<?php 
/**
	* This files list all the subject and provied link to list the tpoic under that subject.
	* @author Sreejith S R 
	* @version 30-08-2010
*/
?>
<script language="javascript"> 

function deleteConfirm(formName,subjectId,subjectName)
{
	
	var answer = confirm ("This will delete the Subject '"+subjectName+"' .Are you sure, you want to proceed?")
	if(answer)
	{
		document.form1.deleteData.value=subjectId;
		document.form1.submit();			
	}
	
	
}

</script>

<?php
//getting all active subjects from database
//$allSubject=getAllSubject();

extract($_POST);
if($selUniversityId=='')
{
	$selUniversityId=$_SESSION['sess_selUniversityId']; 
}
$allUniversity=getUniversityDetails();
?>
<form name="form1" id="form1" method="post" action="" >
<br />
<?php
if($_SESSION['sessUserRoleId']==ROLE_ID_ADMIN)
{
	
?>
<table border="0" width="100%" class="noBorderTable">
  <tr class="textHead2">
    <td width="197">Select University</td>
    <td width="1110"><select style="width:252px" name="selUniversityId" size="1" id="selUniversityId" onChange="this.form.submit()">
      <?php
	   
	 	for($i=0;$i<count($allUniversity);$i++)
			{
			if($allUniversity[$i][0]==$selUniversityId)
                    {
                        $selUniId='selected';
                    } 
                    else
                    {$selUniId='';}
			
	?>
      <option <?php echo $selUniId;?> value="<?php echo $allUniversity[$i][0]; ?>"><?php echo $allUniversity[$i][1]; ?></option>
      <?php } ?>
    </select></td>
  </tr>
</table><br />

<?php
}

if($_POST['selUniversityId'])
{
	$_SESSION['sess_selUniversityId']=$selUniversityId;
	
}
if($_SESSION['sess_selUniversityId']=="")
{
	$selUniversityId=$allUniversity[0][0];	
	$_SESSION['sess_selUniversityId']=$selUniversityId;
}
//
$subEditId = $_GET['subEditId'];	

if($edit_submit=='Save' and $subEditId!="" and $editSubPrivilege)
{
		
	updateSubject($subEditId, $editedName, $editedDesc);
	$msg="Saved";
}
//
//$userUniversityId = getUserUniversityId($userId);

if($addSubmit=='Add' and $addName!="" and $addSubPrivilege)
{
	if($_SESSION['sessUserRoleId']==ROLE_ID_ADMIN)
	{
		$addToUniversityId=	$_SESSION['sess_selUniversityId'];
	}
	else
	{
		$addToUniversityId=	getUserUniversityId($userId);
	}
	
	$subjectNameExist = subjectExist($addToUniversityId,$addName);
 
 	if(count($subjectNameExist)!=0)
	{
		$msg="This subject already exist in this university";
	}
	else
	{
		addSubject($addToUniversityId, $addName, $addDesc);
		$newSubject=$dbObject->getLastInsertId();
		addUserSubject($userId, $newSubject);
		$msg="New subject created";
	}
}

if($deleteData and $deleteSubPrivilege)
{
	deleteSubject($deleteData);
}
 //
include('get_subject_include.php');




?>
<form name="form1" id="form1" method="post" action="#" >
<table border="0"><tr><td><input type="hidden" name="deleteData" id="deleteData"/></td></tr></table>
<table width="100%" border="1" class="myTable">	
<tr class="textRptBlueHead" onmouseover="this.style.backgroundColor='#CCE3FF';" bgcolor="#CCE3FF">       
    <td width="5%" ><?php echo SERIAL_NO;?></td>
    <td width="25%" ><?php echo NAME;?></td>
    <td ><?php echo DESCRIPTION;?></td>
    <td width="5%"><?php echo EDIT;?></td>
    <td width="6%"><?php echo DELETE;?></td>        
</tr>	

<?php for($i=0; $i<count($allSubject); $i++){ ?>      
<tr class="text">
	<td align="center"><?php echo $i+1;?></td>
	<td><a href="?pg=TOPIC&subject=<?php echo $allSubject[$i][0] ?>"><?php echo $allSubject[$i][1] ?></a> </td>
    <td><?php echo $allSubject[$i][2];?></td>
    <td align="center">
    <?php 
	if($editSubPrivilege)
	{ 
	?>    
    <a href="?pg=SUBJECT&subEditId=<?php echo $allSubject[$i][0]; ?>&selUniversityId=<?php echo $selUniversityId;?>	" ><img src="<?php echo getThemeImage('Edit.gif')?>" title="Edit" width="20" height="20"> </a>
    <?php	
	}		
	else{	
	?> 
    <img style="opacity: .4;" src="<?php echo getThemeImage('Edit.gif')?>" title="No privilage" width="20" height="20"/>
    <?php 
	}
	?>   
    </td>
    <td align="center">
    <?php	
		if($deleteSubPrivilege)		
		{
			
		?>
    <a href="javascript:deleteConfirm(this,'<?php echo $allSubject[$i][0]?>','<?php echo $allSubject[$i][1]?>');"><img src="<?php echo getThemeImage('Delete.gif')?>" title="Delete Subject" width="20" height="20"/>  
     <?php 
		}else{
		?>
        <img style="opacity: .4;" src="<?php echo getThemeImage('Delete.gif')?>" title="No privilage" width="20" height="20"/>     
        <?php
		}
		?>
       </a>
    </td> 
</tr>
<?php }?>
           
</table>
<br />
<table width="100%">
   <?php
	if($addSubPrivilege)
	{
	?>
    <tr align="left">
        <td class="textHead1">
        <a href="?pg=SUBJECT&subject=<?php echo $subjectId; ?>&topicId=<?php echo $topicId; ?>&addNew=1&t=<?php echo time();?>#2"> <img src="<?php echo getThemeImage('add.gif')?>" width="16" height="16" /> Add new <?php echo SUBJECT?></a>       
        </td>
    </tr>
    
    <tr align="center">   
       <td>
        <?php 
        $page_link="?pg=SUBJECT&subject=$subjectId";
         if($_GET['addNew']==1 and count($_POST)<1 )
        {
            
           include('add_option.php');
            
        }
        ?>
        <div style="color:#FF0000" align="center"><?php echo $msg;?></div>
        </td>
    </tr>
    <?php
	}
	?>
     <tr align="center">
        <td>
            <?php 	
					
            if($subEditId!="" and count($_POST)<1 and $editSubPrivilege)
                {
                	$getDetails=getSubjectDetails($subEditId);
                	include('edit_option.php');
                //
                }?>	
        </td>
    </tr>
</table>
<?php

//

//


?>

</form>
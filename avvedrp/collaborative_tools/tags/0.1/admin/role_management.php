<?php 
/**
	* This files list all user details under a paticular university.
	* @author Vidya Mohan 
	* @version 22-11-2010
*/
?>

<?php
extract($_POST);
//
$userUniversityId = getUserUniversityId($userId);
$universityUserDetails = getUniversityUserDetails($userUniversityId);
//update code.
if($edit_submit=='Save' and $editUserPrivilege)
{		
	$userEditId = $_GET['userEditId'];	
	updateUserRole($userEditId, $userRole);
	if(count($subjectChecked)==0){
		deleteUserSubject($userEditId);
	}
	if(count($subjectChecked)>0){
		deleteUserSubject($userEditId);
		foreach($subjectChecked as $subjectId){			
			insertUserSubject($userEditId, $subjectId);
		}
	}	
	$msg="Saved";
}
//
if($editUserPrivilege){
?>
<form name="form1" action="#2" method="post" id="form1">
<br />
<table width="100%" border="0" class="myTable">
    <tr class="textRptBlueHead" onmouseover="this.style.backgroundColor='#CCE3FF';" bgcolor="#CCE3FF">
	<td width="4%"><?php echo SERIAL_NO;?></td>
        <td width="26%" ><?php echo USER_NAME;?></td>
        <td width="25%" ><?php echo EMAIL;?></td>
        <td width="16%" ><?php echo SUBJECT;?></td>
         <td width="15%" ><?php echo USER_ROLE;?></td>
        <td width="7%"><?php echo EDIT;?></td>
        <td width="7%"><?php echo DELETE;?></td>  
	</tr>
      <?php
	  if(count($universityUserDetails)!=0)
	  {
      for($i=0; $i<count($universityUserDetails); $i++)
      { 
      ?>
      <tr class="text">
        <td align="center"><?php echo $i+1;?></td>
        <td><?php echo $universityUserDetails[$i][1]; ?></td>
        <td><?php echo $universityUserDetails[$i][2]; ?></td>
        <td><?php 
        $userSubjectDetails = getUserSubjectDetails($universityUserDetails[$i][0]);
		if(count($userSubjectDetails)!=0){
			for($j=0;$j<count($userSubjectDetails);$j++)
			{  
				echo $userSubjectDetails[$j][1]."<br>";
			} 
		}
		else
		{
			echo "--";
		}
        ?> 
        </td>  
        <?php 
        $userRoleDetails = getUserRoleDetails($universityUserDetails[$i][0]);	
        ?>
        <td><?php 
		if($userRoleDetails){
			echo $userRoleDetails[0][1]; 
		}
		else
		{
			echo "--";
		}			
		?></td>
          
        <td align="center">
        <?php 
		if($editUserPrivilege)
		{ 
		?>
        <a href="?pg=ROLE_MANAGE&userEditId=<?php echo $universityUserDetails[$i][0]; ?>&t=<?php echo time();?>#2"><img  src="<?php echo getThemeImage('Edit.gif')?>" 
        title="Edit" width="20" height="20">
        </a>
        <?php 
		}
		else 
		{
		?>
        <img style="opacity: .4;" src="<?php echo getThemeImage('Edit.gif')?>" title="No privilage" width="20" height="20"/>
        <?php
		}
		?>
        </td>
        <td align="center"> 
        <?php
        if($deleteUserPrivilege){
		?>
        <img style="opacity: .4;" src="<?php echo getThemeImage('Delete.gif')?>" title="Delete" width="20" height="20"/> 
        <?php
		}
		else
		{
		?>
        <img style="opacity: .4;" src="<?php echo getThemeImage('Delete.gif')?>" title="No privilage" width="20" height="20"/> 
        <?php
		}
		?>
        </td>
    </tr>
  	<?php
  	} //for loop
	} // end id
  ?>
  
</table>
<br /><div style="color:#FF0000" align="center"><?php echo $msg;?></div><br />
<?php 	
//edit part
$userEditId = $_GET['userEditId'];
if($userEditId!="" and $editUserPrivilege and !$edit_submit)
{			 	
	$userDetails = getUserDetails($userEditId);
	$userRoleDetails = getUserRoleDetails($userEditId);
	if($_POST['userRole']=="")
	{
		$userRole=$userRoleDetails[0][0];	
	}
	$userSubjectName = getUserSubjectDetails($userEditId);			
	$allRoleName = gerAllRoleName();	
	$allSubName = getAllSubject($userUniversityId);
			
	for($n=0;$n<count($userSubjectName);$n++)
	{
		$userSubjectId[] = $userSubjectName[$n][0];
	}			
?>       
<br />
<a name="2" id="2"></a>
<table width="100%">
    <tr>
    <td align="center">
    <table border="0" width="50%" height="194" style="border:dotted" >
        <tr class="textHead1"><td width="25%" colspan="2" align="center">Edit</td>
        <tr class="text"><td width="32%">User Name: </td>
        <td width="68%" ><?php echo $userDetails[0];?></td>
        </tr>
        <tr class="text">
        <td>Email id:</td>
        <td><?php echo $userDetails[1];?></td>
        </tr>
        <tr class="text">
        <td>User Role:</td>
        <td><select name="userRole" id="userRole" onchange="this.form.submit()">
        <?php
        for($k=0;$k<count($allRoleName);$k++){
			if($userRole!=""){
				if($allRoleName[$k][0]==$userRole)
				{
					$roleSelected="selected";	
				}
				else
				{
					$roleSelected="";	
				}
			}
			else
			{
				$roleSelected="";
			}
        ?>
        <option <?php echo $roleSelected; ?> value='<?php echo $allRoleName[$k][0]; ?>' ><?php echo $allRoleName[$k][1]; ?></option>     
        <?php 
            } 		
        ?>
        </select>
        </td>
        </tr> 
        <?php		
		if($userRole==ROLE_ID_HOD || $userRole==ROLE_ID_TEACHER || $userRole==ROLE_ID_REVIEWER)
		{	
		?>
        <tr class="text">
        <td>Subject:</td>
        <td>
		<?php		
        for($m=0;$m<count($allSubName);$m++)
        {				
            if(count($userSubjectId)!=0){
				if(in_array($allSubName[$m][0], $userSubjectId))
				{
					$subjectSelected="checked=\"checked\"";
				}
				else
				{
					$subjectSelected="";	
				}
			}
			else{
				$subjectSelected="";	
			}
        ?>
        <input type="checkbox" name="subjectChecked[]" value="<?php echo $allSubName[$m][0]; ?>" <?php echo $subjectSelected; ?> /> <?php echo $allSubName[$m][1]; ?><br />
        <?php 
        } //for close		
        ?>                   
        </select></td>
        </tr>  
        <?php
		} //if close
		?>
        <tr class="text">
        <td colspan="2" align="center"><input type="submit" value="Save" id="edit_submit" name="edit_submit" style="width:50px" />&nbsp;&nbsp;
        <input type="button" value="Cancel" id="cancel" name="cancel" onclick="window.location.href='?pg=ROLE_MANAGE'"  style="width:50px" /></td>
        </tr>            
        </table>
    </td>
    </tr>
</table>
<br />
<?php
} //if close
?>
</form>
<?php
} //main if close
else
{ 
	$msg1 = "You are not allowed to view this page!!";
	?>
	<br /><div class="text" align="center"><?php echo $msg1; ?></div>		
<?php
}
?>

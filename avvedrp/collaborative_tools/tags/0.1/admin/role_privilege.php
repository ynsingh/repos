<br/>
<?php 
extract($_POST);
// For saving the privileges which we want
if($edit_privilege == 'Submit') 
{
	$delete_privilege=deleteRolePrivilege($selRoleId);
    if($checkPrivilege) 
	{   
		$prevCount=count($checkPrivilege);
        for($k=0;$k<$prevCount;$k++) 
		{
            if($k==($prevCount-1)) 
			{
                $add_privilege.= "(". $selRoleId . ",". $checkPrivilege[$k].");";
            }
			else
			{
                $add_privilege.= "(". $selRoleId . ",". $checkPrivilege[$k]."), ";
            }	
        }
		addRolePrivileges($selRoleId, $add_privilege);
    }
}
// Getting role details from here
$role_details=gerAllRoleName();
if($selRoleId=='') 
{
	$selRoleId=$role_details[0][0];
}
?>
<form id="form1" name="form1" method="post" action="">
    <table border="0" align="left"> 
        <tr class="textHead2">
            <td align="left" width="40%">Select Role</td>
            <td><select name="selRoleId" id="selRoleId" onChange="this.form.submit()">
            <?php
            for($i=0;$i<count($role_details);$i++)
            {
				$role_name=$role_details[$i][1];
				$role_id=$role_details[$i][0];
				if($role_id==$selRoleId)
				{
					$selectedId = "selected";
				}
				else
				{
					$selectedId='';
				}
				?>
				<option <?php echo $selectedId;?> value="<?php echo $role_id; ?>"><?php echo $role_name;?> </option>
				<?php 
            }
            ?>    
            </select>
            </td>
        </tr>
    </table>
    <br/>
    <br/>
    <table border="0" width="100%" class="myTable"> 
        <tr class="textRptBlueHead" onmouseover="this.style.backgroundColor='#CCE3FF';" bgcolor="#CCE3FF">
            <td width="5%" align="left"> Sl No </td>
            <td align="left"> Privileges </td>
        </tr>
        <?php
        // Getting privilege details from here.
        $privilege=getPrivilegeDetails();
        // Getting privilege details of corresponding role from here.
        $privilege_details=getPrivilegeDetailsOfRole($selRoleId);
        for($j=0;$j<count($privilege);$j++)
        {
			$privilege_id=$privilege[$j][0];
			$privilege_name=$privilege[$j][1];
			$privilegeIdRole[]=$privilege_details[$j][0];
        	?>        
            <tr class="text">
            <td align="left"><?php echo $j+1; ?> </td> 
            <td align="left">
        	<?php
        	// Checking the checkbox.
        	if(count($privilegeIdRole)!="")
        	{
        		if (in_array($privilege_id,$privilegeIdRole)) 
        		{ 		
        			$check = "checked"; 
        		} 
        		else 
        		{
        			$check="";
        		} 
        	}
        	else
        	{
        		$check="";
        	}
        	?>
            <input type="checkbox" name="checkPrivilege[]" id="checkPrivilege[]" value="<?php echo $privilege_id; ?>" <?php echo $check; ?> /> <?php echo $privilege_name;?>
            </td>
            </tr>
        <?php
        }
        ?>
        <td colspan="2" align="left"><input type="submit" name="edit_privilege" id="edit_privilege" value="Submit" /> </td>
    </table>
</form>


<?php 
/**
	* This files provides the user interface to edit new topic, subtopic and experiment. This page is included in topic_list2.php, sub_topic_list.php and experiment_list.php
	* @author Sreejith S R 
	* @version 30-08-2010
*/?>
    <table border="0" width="50%" style="border:dotted">
    	<tr class="textHead1"><td width="25%" colspan="2" align="center">Edit</td>
        <tr class="text"><td width="20%">Edit Name: </td>
        <td >
        <input name="editedName" id="editedName" type="text"  value="<?php echo $getDetails[0][0];?>" style="width:300px"  maxlength="100"/></td>
        </tr>
        <tr class="text">
        <td>Edit Description</td>
        <td> <textarea name="editedDesc" id="editedDesc" rows="5" cols="35"><?php echo $getDetails[0][1];?></textarea></td>
        </tr>
        <tr class="text">
        <td colspan="2" align="center"><input type="submit" value="Save" id="edit_submit" name="edit_submit"  onclick="return checkEdit();" style="width:50px"/>&nbsp;&nbsp;<input type="button" value="Cancel" id="cancel" name="cancel"  onclick="window.location.href='<?php echo $page_link?>'" style="width:50px"/></td>
        </tr>
        
</table>

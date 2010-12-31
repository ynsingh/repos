<?php 
/**
	* This files provides the user interface to add new topic and subtopic. This page is included in topic_list2.php and sub_topic_list.php
	* @author Sreejith S R 
	* @version 30-08-2010
*/
?>
<!--<form name="form4" method="post" action="#">-->
    <table border="0" width="50%" align="center" style="border:dotted">
    <tr class="textHead1"><td width="25%" colspan="2" align="center">Add</td></tr>
    <tr class="text">
    <td>Name</td><td width="50%"><input type="text" name="addName" id="addName" style="width:99%" maxlength="100"/>
    </tr>
    <tr class="text">
    <td>Description</td><td><textarea name="addDesc" id="addDesc" rows="5" cols="40%"></textarea></td>
    </tr>
    <tr class="text">
    <td colspan="2" align="center"><input type="submit" value="Add" id="addSubmit" name="addSubmit"  onclick="return checkAdd();" style="width:50px"/>&nbsp;&nbsp;<input type="button" value="Cancel" id="cancel" name="cancel"  onclick="window.location.href='<?php echo $page_link?>'" style="width:50px"/></td>
    </tr>
    </table>
<!--</form>-->
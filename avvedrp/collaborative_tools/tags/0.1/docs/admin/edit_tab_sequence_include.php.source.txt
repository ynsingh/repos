<?php
/**
	* This files provides the user interface to edit tab sequence
	* @author Vidya Mohan
	* @version 29-11-2010
*/
?>

<?php
$contentTypeDetails = getAllContentItems($experimentId);
$arrContent=count($contentTypeDetails);
?>

<div align="center" class="textHead1">Re Arrange Tabs</div>
<table border="0" align="center">	
<tr >
<td class="textHead2">Tab Name</td>
<?php
$i=0;
$j=0;
for($i=1; $i<=$arrContent;$i++)
{
	$displayArray[]=$i;
}
for($j=0;$j<$arrContent;$j++)
{	
?>
<td class="text" bgcolor="#666666" style="color:#FFF"><?php echo $contentTypeDetails[$j][1]; ?></td>
<input type="hidden" name="tabSeqContentId[]" id="tabSeqContentId[]" value="<?php echo $contentTypeDetails[$j][2]; ?>" />
<?php
}
?>
</tr>
<tr >
<td  class="textHead2">Order</td>
<?php
//displaying all tab name in an experiment
for($j=0;$j<$arrContent;$j++)
{
?>
<td align="center">
<select name="selTab[]" id="selTab[]">
<?php
	//displaying the current tab sequence number
    for($k=0;$k<$arrContent;$k++)
    {
        if($j==$k)
        {
            $selected = "selected";
        }
        else
        {
            $selected = "";
        }
?>
<option <?php echo $selected; ?> value="<?php echo $displayArray[$k]; ?>" ><?php echo $displayArray[$k]; ?></option>
<?php
 }
?>
</select></td>
<?php
 }
?>
</tr>
</table><br />
<div align="center"><input type="submit" value="." id="edit_tabSequence" name="edit_tabSequence"  onclick="return checkSequenceRepeat();" style="background-image:url(<?php echo getThemeImage('save.png')?>);  width:50px; height:23px; background-color:Transparent; border:none; color:Transparent; "/>&nbsp;&nbsp;
<input type="button" value="." id="cancel" border="none" name="cancel" onclick="window.location.href='?pg=EDIT_EXP&subject=<?php echo $subjectId; ?>&topicId=<?php echo $topicId; ?>&subTopicId=<?php echo $subTopicId; ?>&exp=<?php echo $experimentId; ?>'" style="background-image:url(<?php echo getThemeImage('cancel.png')?>);  width:50px; height:23px; background-color:Transparent; border:none; color:Transparent; "/>

</div>         
<br /><div align="center" class="text"><?php echo "<b>".$msg."</b>"; ?></div>



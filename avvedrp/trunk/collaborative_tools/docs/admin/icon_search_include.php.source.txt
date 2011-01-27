<?php 
/**
	* This is an include file in edit_experiment.php, which is used to choose the new tab icon.
	* @author Sreejith S R
	* @version 06-12-2010
*/?>

<?php
//getting the tab icons from theme
$files = glob(THEME_ROOT.$_SESSION['sess_current_theme']."/images/tab_icon_image/*.png");

?>
<table border="0">
<tr>
<?php
for ($i=1; $i<=count($files); $i++) 
{
	
	$imagePath = $files[$i-1];
	$fileName = basename($imagePath);
?>
	<td>
    <img width="30px" height="30px"  src="<?php echo $imagePath?>" onclick="show_image('<?php echo $imagePath?>');" />
    
	</td>
	<?php 
	if($i%5==0 and $i!=0) {
		echo "</tr>";
        echo "<tr>";
	
    } ?>
	
<?php
}?>

</tr>
</table>





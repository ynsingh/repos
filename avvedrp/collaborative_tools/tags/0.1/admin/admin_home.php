<?php 
/**
	* This files shows the admin home page
	* @author Sreejith S R 
	* @version 30-08-2010
*/?>

<table width="100%" border="0">
<br />
<br />
<br />
<br />

<tr class="textHead1">
	<td width="50%" align="center">
    <div align="center"><a href="?pg=SUBJECT"><img src="<?php echo getThemeImage('admin-icon-om.png');?>" /></a></div>
    <a href="?pg=SUBJECT">Content Manager</a></td>
    
    <td align="center"><div align="center">
<?php if($_SESSION['sessUserRoleId']==ROLE_ID_ADMIN)
		{    
?>		
    <a href="?pg=THEME_MGTM"><img src="<?php echo getThemeImage('admin-icon-om.png');?>" /></a></div>
    <a href="?pg=THEME_MGTM">Portal Admin</a>
<?php }
?>
    </td>
    
</tr>
</table>
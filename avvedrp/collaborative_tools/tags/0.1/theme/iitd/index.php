<?php
session_start();
getHeader();
echo "<div align=\"left\" style=\"position: relative; margin:auto; width: 1024px; background-color:#e3f2fc\">";
?>
<table width="100%" border="0">
<tr><td width="50%" align="left" valign="top"><?php displayBreadcum($arrItems,$arrLink,$breadcumStartText,$seperator); ?></td>
<td width="45%" align="right"><?php displayWelcomeMessage($_SESSION['sessFullName'],$_SESSION['sessUserRoleId']); ?> </td>
<td width="5%" align="left"><?php displayLogOutButton(); ?></td></tr>
</table>
<?php



 include('theme_page_select_include.php');
if(($_SESSION['sessUserRoleId']==ROLE_ID_ADMIN)|| ($_SESSION['sessUserRoleId']==ROLE_ID_UNI_ADMIN) || ($_SESSION['sessUserRoleId']==ROLE_ID_HOD) || ($_SESSION['sessUserRoleId']==ROLE_ID_TEACHER) and $_GET['pg']!="")
{

?>
<div align="center">
	<table width="98%" border="0" cellspacing="5px" align="center">
	<tr>
	<td width="18%" valign="top" bgcolor="#FFFFFF"><?php include('admin/admin_menu.php'); ?></td>
	<td width="82%" valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" height="100%">
	  <tr>
		<td width="1%" bgcolor="#FFCC00">&nbsp;</td>
		<td width="" bgcolor="#FFFF99" class="tblAdminTemplate"><?php echo $sub_head; ?> </td>
	  </tr>
	  <tr>
		<td colspan="2" valign="top" ><?php if($admin_pg!="")
		include($admin_pg); ?></td>
	  </tr>
	</table></td>
	</tr>
	</table>
</div>
<?php }
else
{?>
    <div align="center">
		<?php if($admin_pg!="")
        include($admin_pg); ?>
    </div>
<?php }
echo "</div>";
getFooter();
?>

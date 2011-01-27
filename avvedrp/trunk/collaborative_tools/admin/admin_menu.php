<?php 
/**
	* This files display the side menu of the admin
	* @author Mukesh Kumar M
	* @version 30-08-2010
*/?>
<?php 
$usertype_access = array(ROLE_ID_ADMIN, ROLE_ID_UNI_ADMIN, ROLE_ID_HOD, ROLE_ID_TEACHER);
checkUserPageAccess($usertype_access);
$userPrivileges=array();
$userId=$_SESSION['sesssUserId'];
$userPrivilegeDetails=getUserprivilege($userId);
for($i=0;$i<count($userPrivilegeDetails);$i++)
{
	$userPrivileges[]=$userPrivilegeDetails[$i][0]	;
	
}
//Subject Add,Edit,Delete Privilege checking
if(in_array(PRIVI_ADD_SUBJECT,$userPrivileges))
$addSubPrivilege =1;
if(in_array(PRIVI_EDIT_SUBJECT,$userPrivileges))
$editSubPrivilege =1;
if(in_array(PRIVI_DELETE_SUBJECT,$userPrivileges))
$deleteSubPrivilege =1;

//Topic Add,Edit,Delete Privilege checking
if(in_array(PRIVI_ADD_TOPIC,$userPrivileges))
$addTopicPrivilege =1;
if(in_array(PRIVI_EDIT_TOPIC,$userPrivileges))
$editTopicPrivilege =1;
if(in_array(PRIVI_EDIT_TOPIC,$userPrivileges))
$deleteTopicPrivilege =1;

//Subtopic Add,Edit,Delete Privilege checking
if(in_array(PRIVI_ADD_SUBTOPIC,$userPrivileges))
$addSubTopicPrivilege =1;
if(in_array(PRIVI_EDIT_SUBTOPIC,$userPrivileges))
$editSubTopicPrivilege =1;
if(in_array(PRIVI_EDIT_SUBTOPIC,$userPrivileges))
$deleteSubTopicPrivilege =1;

//Experiment Add,Edit,Delete Privilege checking
if(in_array(PRIVI_ADD_EXP,$userPrivileges))
$addExpPrivilege =1;
if(in_array(PRIVI_EDIT_EXP,$userPrivileges))
$editExpPrivilege =1;
if(in_array(PRIVI_DELETE_EXP,$userPrivileges))
$deleteExpPrivilege =1;

//User's [Role and Subject] Edit,Delete Privilege checking
if(in_array(PRIVI_EDIT_USERDETAILS,$userPrivileges))
$editUserPrivilege =1;
if(in_array(PRIVI_DELETE_USERDETAILS,$userPrivileges))
$deleteUserPrivilege =1;

?>
<style  type="text/css">
.textSideMenu {
	font-family: Verdana, Arial, Helvetica, Bitstream Vera Sans;
	font-size: 14px;
	/*padding-right: 15px;*/
	padding-left: 5px;
	/*text-align: justify;*/
	font-weight: bold;
	text-transform: capitalize;
	color:#CC3399;
	padding-bottom:5px;
		
	}
</style>
<!--<table width="140"  border="0" bgcolor="#F5FCFF">
    <tr ><td class="textSideMenu" onmouseover="this.style.backgroundColor='#befb60';"  onmouseout="this.style.backgroundColor='#F5FCFF';"><a href="?pg=<?php echo 'SUBJECT'?>"><?php echo SUBJECT?></a></td></tr>
    <tr ><td class="textSideMenu" onmouseover="this.style.backgroundColor='#befb60';"  onmouseout="this.style.backgroundColor='#F5FCFF';"><a href="?pg=<?php echo 'TOPIC'?>"><?php echo TOPIC?></a></td></tr>
    <tr ><td class="textSideMenu" onmouseover="this.style.backgroundColor='#befb60';"  onmouseout="this.style.backgroundColor='#F5FCFF';"><a href="?pg=<?php echo 'SUB_TOPIC';?>"><?php echo SUB_TOPIC;?></a></td></tr>
    <tr ><td class="textSideMenu" onmouseover="this.style.backgroundColor='#befb60';"  onmouseout="this.style.backgroundColor='#F5FCFF';"> <a href="?pg=<?php echo 'EXPERIMENT'?>"><?php echo EXPERIMENT?></a></td></tr>
    <tr ><td class="textSideMenu" onmouseover="this.style.backgroundColor='#befb60';"  onmouseout="this.style.backgroundColor='#F5FCFF';"><a href="?pg=<?php echo 'EDIT_EXP'?>"><?php echo EDIT_EXP?></a></td></tr>
    <tr ><td class="textSideMenu" onmouseover="this.style.backgroundColor='#befb60';"  onmouseout="this.style.backgroundColor='#F5FCFF';"> <a href="?pg=<?php echo 'THEME_MGTM'?>"><?php echo THEME_MGTM?></a></td></tr>
    <tr><td><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /></td></tr>
</table>-->
<?php 
include("admin/tree/dhtmlgoodies_tree.class.php");
$tree = new dhtmlgoodies_tree(getThemeImage('expand.gif'));
$tree->addToArray(102,"Content Manager",0,"","",getThemeImage('ico_admin.png'));
$tree->addToArray(13,"Subject",102,"?pg=SUBJECT","",getThemeImage('subject.png'));
$tree->addToArray(14,"Topic",102,"?pg=TOPIC","",getThemeImage('topic.png'));
/*$tree->addToArray(15,"Sub Topic",12,"?pg=SUB_TOPIC","",getThemeImage('subtopic.png'));*/
$tree->addToArray(16,"Experiment",102,"?pg=EXPERIMENT","",getThemeImage('experiment.png'));
$tree->addToArray(17,"Edit Experiment",102,"?pg=EDIT_EXP","",getThemeImage('edit.png'));
//
if($_SESSION['sessUserRoleId']==ROLE_ID_ADMIN || $_SESSION['sessUserRoleId']==ROLE_ID_UNI_ADMIN)
{
$tree->addToArray(101,"Portal Admin",0,"","",getThemeImage('ico_admin.png'));
$tree->addToArray(7,THEME_MGTM,101,"?pg=THEME_MGTM","",getThemeImage('theme.png'));
$tree->addToArray(18,ROLE_MANAGE,101,"?pg=ROLE_MANAGE","",getThemeImage('role_manage.png'));
$tree->addToArray(2,ROLE_PRIVILEGE,101,"?pg=ROLE_PRIVILEGE","",getThemeImage('accessibility.png'));
//$tree->addToArray(18,"Accessibility",1,"","",getThemeImage('accessibility.png'));
$tree->addToArray(3,AUTHORIZATION,101,"?pg=AUTHORIZATION","",getThemeImage('authorization.png'));
//$tree->addToArray(4,"Modules",1,"","",getThemeImage('module.png'));
$tree->addToArray(5,SERVER_STAT,101,"?pg=SERVER_STAT","",getThemeImage('server.png'));
$tree->addToArray(6,SETTINGS,101,"?pg=SETTINGS","",getThemeImage('settings.png'));
//$tree->addToArray(8,"Scheduling",1,"","",getThemeImage('shedule.png'));
$tree->addToArray(9,MANAGE_DATABASE,101,"?pg=MANAGE_DATABASE","",getThemeImage('database.png'));
//$tree->addToArray(10,"Audio & Video",1,"","",getThemeImage('audiovideo.png'));
//$tree->addToArray(11,"Remote Trigger",1,"","",getThemeImage('remote.png'));
$tree->addToArray(103,REPORTS,0,"","",getThemeImage('ico_admin.png'));
$tree->addToArray(25,"Click Stream",103,"?pg=CLICK_STREAM","",getThemeImage('clickstream.png'));
$tree->addToArray(26,"Academic Report",103,"?pg=ACADEMIC_REPORT","",getThemeImage('report_icon.png'));
$tree->addToArray(27,AUDIT_LOGS,103,"?pg=AUDIT_LOGS","",getThemeImage('doc_icon.png'));
}
//

//
?>
<div style="width:140; background-color:#FFF">
<?php
$tree->writeCSS();
$tree->writeJavascript();
$tree->drawTree();
?>
</div>



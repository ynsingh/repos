<?php
/**
   * This is an include file which is included inside theme page st_experiment.php. If we need to add a new page, we need to define it here to get acess.
   * @author Sreejith S R
   * @version 05-08-2010
*/

if($_GET['pg']!="")
{
	if(($_SESSION['sessUserRoleId']==ROLE_ID_ADMIN)|| ($_SESSION['sessUserRoleId']==ROLE_ID_UNI_ADMIN) || ($_SESSION['sessUserRoleId']==ROLE_ID_HOD) || ($_SESSION['sessUserRoleId']==ROLE_ID_TEACHER))
	{
		//admin pages only
		$text=TITLE." ".getUserRoleName($_SESSION['sessUserRoleId'])." ".DASHBOARD;
		displayHeadText($text);
		
		switch ($pageName)
		{
			case SUBJECT:			
				$admin_pg='admin/subject_list2.php';
				$sub_head=SUBJECT;
				break;
			case TOPIC:
				$admin_pg='admin/topic_list2.php';
				$sub_head=TOPIC;
				break;
			case SUB_TOPIC:
				$admin_pg='admin/sub_topic_list.php';
				$sub_head=SUB_TOPIC;
				break;
			case EXPERIMENT:
				$sub_head=EXPERIMENT;
				$admin_pg='admin/experiment_list.php';
				break;
			case EDIT_EXP:
				$sub_head=EDIT_EXP;
				$admin_pg='admin/edit_experiment.php';
				break;
			case THEME_MGTM:
				$sub_head=SUBHEAD_THEME;
				$admin_pg='admin/theme_mgtm.php';
				break;
			case ROLE_MANAGE:
				$sub_head=ROLE_MANAGE;
				$admin_pg='admin/role_management.php';
				break;	
			case ROLE_PRIVILEGE:
				$sub_head=ROLE_PRIVILEGE;
				$admin_pg='admin/role_privilege.php';
				break;	
			case AUTHORIZATION:
				$sub_head=AUTHORIZATION." (Mock up)";
				$admin_pg='admin/authorization.php';
				break;	
			case SERVER_STAT:
				$sub_head=SERVER_STAT." (Mock up)";
				$admin_pg='admin/server_stat.php';
				break;
			case SETTINGS:
				$sub_head=SETTINGS." (Mock up)";
				$admin_pg='admin/settings.php';
				break;				
			case MANAGE_DATABASE:
				$sub_head=MANAGE_DATABASE." (Mock up)";
				$admin_pg='admin/manage_database.php';
				break;				
			case CLICK_STREAM:
				$sub_head=CLICK_STREAM." (Mock up)";
				$admin_pg='admin/click_stream.php';
				break;	
			case ACADEMIC_REPORT:
				$sub_head=ACADEMIC_REPORT." (Mock up)";
				$admin_pg='admin/academic_report.php';
				break;
			case AUDIT_LOGS:
				$sub_head=AUDIT_LOGS." (Mock up)";
				$admin_pg='admin/audit_logs.php';
				break;
			default:
				$sub_head=getUserRoleName($_SESSION['sessUserRoleId'])." ".HOME2;
				$admin_pg='admin/admin_home.php';
				break;	
				
		}
	}
	if($admin_pg=="")
	{
		switch ($pageName)
		{//msg pages and others, new pages can be included here.
			case 'Project':
				$admin_pg='project.php';
				break;	
			case 'msg':
				$admin_pg='msg.php';
				break;	
			default:
				echo "<div style=\"min-height:300px;\" align=\"center\" class=\"text\">".NOPERMISSION1."</div>";
				break;		
		}
	}
	
	
	
}
else
{//experiment pages
	switch ($pageName)
	{
		case 'topic':
			displayHeadText(getSubName($_GET['sub']));
			echo "<br>";
			displayTopicMenu($_GET['sub']);
			break;
		case 'lab':
			displayHeadText(getTopicName($_GET['brch']));
			echo "<br>";
			displayExperimentMenu($_GET['sub'],$_GET['brch']);
			break;
		case 'st_experiment':
			break;					
		default:
			include('home.php');
			break;	
	}
}
?>

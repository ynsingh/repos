<?php
session_start();
ob_start();
include('config.php');
include('defined_constants.php');
if($_GET['ln']!="")
{
	$_SESSION['sess_ln']=$_GET['ln'];
}
//
//error_reporting(E_ALL);
ini_set('display_errors', '1');
include('lan/'.LANG.'.php');
include('execute_query.php');
include('common_model_functions.php');
include('common_view_functions.php');
include('theme_apply.php');
//include(THEME_ROOT.$_SESSION['sess_current_theme'].'/lang.php');
include('url_direct.php');
//
/*$db_object->sql="select * from avl_experiment_branch_rel";
$db_object->msg="selection from avl_experiment_branch_rel";
$arr_result=$db_object->querySelectReturn('DB');
print_r($arr_result);*/
//UniversityDetails($university_id);
/*
$flg_lab="vlab";
$title="";
$keywords="";
$description="";
?>
<?php
include('ExecuteQuery.php');
@include('head.php');
?>	
<?php
//@include('sidebar.php');
?>	
<?php
@include('home.php');
?>	
<?php
@include('foot.php'); */?>
<script type="text/javascript" src="js/javascript_functions.js"></script>
<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<?php /*include('login/login_page.php');*/?>
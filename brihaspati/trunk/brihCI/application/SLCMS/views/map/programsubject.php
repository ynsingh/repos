<!--
    @name add_program_subject_paper.php
    @author Sharad Singh(sharad23nov.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');

echo "<html>";
echo "<head>";
echo "<title>".'IGNTU - Program Subject List'."</title>";
    $this->load->view('template/header');
   
//    $this->load->view('template/menu');
?>
   <!-- <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/message.css">-->
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    <style>
    .abc{
        width:265px;
    }
    </style>

<?php
echo "</head>";
echo "<body>";
?>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->


<?php
/*
    echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
    echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
    echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
    echo "Map";
    echo "<span  style=\"padding: 8px 8px 8px 20px;\"> ";
    echo anchor('map/programsubject/', "Map Program with Subject", array('title' => 'Add Detail' , 'class' => 'top_parent'));
    echo "<span  style=\"padding: 8px 8px 8px 20px;\"> ";
    echo "|";
    echo "<span  style=\"padding: 8px 8px 8px 20px;\">";
    echo "Program with Subject";
    echo "</span>";
    echo "</td>";
    echo "</tr>";
    echo "</table>";
    echo"</br>";
*/
?>

<div>
<table style="width:100%">
<tr><td>
<?php
echo "<td align=\"left\" width=\"33%\">"; 
echo anchor('map/addprogramsubject/', " Add Subject Paper" ,array('title' => 'Subject List' , 'class' => 'top_parent'));
echo "</td>";
echo "<td align=\"center\" width=\"34%\">";
echo "<b>Subject Paper Details</b>";
echo "</td>";
echo "<td align=\"right\" width=\"33%\">";
$help_uri = site_url()."/help/helpdoc#ViewSubjectPaperList";
echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";

?>
</td></tr>
</table>
</div>

    <table width="100%">
    <tr><td>
    <div  style="">

    <?php echo validation_errors('<div style="margin-left:2%;" class="isa_warning>','</div>');?>
    <!--?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?-->

     <?php if(isset($_SESSION['success'])){?>
       <div class="isa_success"><?php echo $_SESSION['success'];?></div>
<?php    }
    if(isset($_SESSION['error']))
    {
?>        <div class="isa_error">"<?php echo $_SESSION['error'];?> </div>
<?php
    }
    echo "</td></tr>";
    echo "</table>";
echo "</center>";
    echo "<div align=\"center\";>";
    //echo "<div style=\"margin-left:10px;\">";

/* Form */
?>
<div class="scroller_sub_page">
<?php
    echo "<table border=0  style=\"padding: 8px 8px 8px 20px;\" class=\"TFTable\">";
    echo "<thead><tr align=\"left\"><th>Sr.No.</th><th>Degree</th><th>Branch </th><th>Academic Year </th><th>Subject Name</th><th>Paper No</th><th>Paper Name</th><th>Paper Code</th><th>Paper Short Name</th><th>Available Action</th></tr></thead>";
$this->load->model('Map_model',"mapmodel");
$srno = 0;

if( count($paperrecords) ) {
	$pre="p";
foreach($paperrecords as $row)
{
    $subject_name = $this->mapmodel->get_subjectname($row->subp_sub_id);
    $program_name = $this->mapmodel->get_programname($row->subp_degree);
      
    $srno = $srno + 1;
  
	?>
	<?php
	if(!($pre==$row->subp_subtype)){
		echo "<tr><td colspan=10 style=\"text-align:center; font-weight:bold;\">"; echo $row->subp_subtype; echo"</td></tr>";
	} 
	$pre=$row->subp_subtype;
	  echo "<tr align=\"center\">";
    echo "<td>"; echo $srno; echo"</td>";
    //echo "<td>"; echo $row->subp_prgcat; echo"</td>";
    echo "<td>"; echo $program_name; echo"</td>";
    echo "<td>"; echo $this->commodel->get_listspfic1('program','prg_branch ','prg_id',$row->subp_degree)->prg_branch ; echo"</td>";
    echo "<td>"; echo $row->subp_acadyear; echo"</td>";
    echo "<td>"; echo $subject_name; echo"</td>";
 //   echo "<td>"; echo $row->subp_subtype; echo"</td>";
    echo "<td>"; echo $row->subp_paperno; echo"</td>";
    echo "<td>"; echo $row->subp_name; echo"</td>";
    echo "<td>"; echo $row->subp_code; echo"</td>";
    echo "<td>"; echo $row->subp_short; echo"</td>";
    echo "<td>"; echo anchor('map/editprogramsubject/' .$row->subp_id  , "Edit", array('title' => 'Edit Subject Program', 'class' => 'red-link')); echo "&nbsp;&nbsp;&nbsp;"; //echo anchor('map/deleteprogramsubject/' .$row->subp_id ,"Delete", array('title' => 'Delete Subject', 'class' => 'red-link')); echo"</td>";
    echo "</tr>";
} }
   else{ 
    echo "<tr>";
         echo "<td colspan= \"11\" align=\"center\"> No Records found...!</td>";
    echo "</tr>";
   }


    echo "</table>";
   echo "</div>";
/* Form */
    echo"</body>";
    echo "<div align=\"center\">";  
    $this->load->view('template/footer');
echo "</div>";
echo "</html>";
?>

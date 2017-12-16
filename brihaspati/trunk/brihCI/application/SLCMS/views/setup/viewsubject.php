<!--
    @name subject.php
    @author Sharad Singh(sharad23nov.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');

echo "<html>";
echo"<title>View Subject</title>";
echo "<head>";
    $this->load->view('template/header');
//    $this->load->view('template/menu');?>
    <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/tablestyle.css">
<?php
echo "</head>";
echo "<body>";
/*    echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
    echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
    echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
    echo "Map";
    echo "<span  style=\"padding: 8px 8px 8px 20px;\"> ";
    echo "|";
    echo anchor('setup/subject/', "Add Subject", array('title' => 'Add Detail' , 'class' => 'top_parent'));
    echo "<span  style=\"padding: 8px 8px 8px 20px;\"> ";
    echo "|";
    echo "<span  style=\"padding: 8px 8px 8px 20px;\">";
    echo "Subject List";
    echo "</span>";
    echo "</td>";
    echo "</tr>";
    echo "</table>";
    echo"</br>";
*/
?>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
<table width= "100%">
<tr><td>
<?php 
echo "<td align=\"left\" width=\"33%\">";
echo anchor('setup/subject/', "Add Subject " ,array('title' => 'Add Subject' , 'class' => 'top_parent'));
echo "</td>";
echo "<td align=\"center\" width=\"34%\">";
echo "<b>Subject Details</b>";
echo "</td>";
echo "<td align=\"right\" width=\"33%\">";
$help_uri = site_url()."/help/helpdoc#ViewSubjectDetail";
echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
echo "</td>";
?>
</td></tr>
</table>
    <table width="100%">
    <div>
    <?php echo validation_errors('<div style="margin-left:2%;" class="isa_warning>','</div>');?>
    <?php echo form_error('<div style="margin-left:2%;" class="isa_error">','</div>');?>
     <?php if(isset($_SESSION['success'])){?>
       <div class="isa_success"><?php echo str_replace("%20"," ",$_SESSION['success']);?></div>
<?php    }
    if(isset($_SESSION['error']))
    {
?>        <div class="isa_success">"<?php echo str_replace("%20"," ",$_SESSION['error']);?> </div>
<?php
    }
    echo "</div>";
    echo "</table>";;
    
/* form data */
         echo "<table border=0  class=\"TFtable\">";
         echo "<thead><tr><th>Sr. No </th><th>Program Name</th><th>Semester/Year</th><th>Subject Type</th><th>Subject Name</th><th>Subject Code </th><th>Subject Short Name</th><th>Subject Description</th><th>Subject Credit</th><th>Subject Extention</th><th>Action</th></tr></thead>";

	 $srno = 0;
	 if( count($subjectlists) ):
    foreach($subjectlists as $subjectlist)
    {
        $srno = $srno + 1;
        echo "<td>"; echo $srno; echo"</td>";
	echo "<td>"; 
	if(!empty($subjectlist->sub_program)){
	echo $this->common_model->get_listspfic1('program','prg_name','prg_id',$subjectlist->sub_program)->prg_name;
	echo " ( " .$this->common_model->get_listspfic1('program','prg_branch','prg_id',$subjectlist->sub_program)->prg_branch;
	echo " ) ";
	}
	echo "</td>";
        echo "<td>"; echo $subjectlist->sub_semester; echo"</td>";
        echo "<td>"; echo $subjectlist->sub_subtype; echo"</td>";
        echo "<td>"; echo $subjectlist->sub_name; echo"</td>";
        echo "<td>"; echo $subjectlist->sub_code; echo"</td>";
        echo "<td>"; echo $subjectlist->sub_short; echo"</td>";
        echo "<td>"; echo $subjectlist->sub_desc; echo"</td>";
        echo "<td>"; echo $subjectlist->sub_ext1; echo"</td>";
        echo "<td>"; echo $subjectlist->sub_ext2; echo"</td>";
	echo "<td>"; echo anchor('setup/editsubject/' . $subjectlist->sub_id , "Edit", array('title' => 'Edit Subject', 'class' => 'red-link')); echo "&nbsp;&nbsp;&nbsp;"; //echo anchor('setup/deletesubject/' . $subjectlist->sub_id ."/".$subjectlist->sub_name, "Delete", array('title' => 'Delete Subject', 'class' => 'red-link','onclick' => "return confirm('Are you sure you want to delete this record')")); 
	echo"</td>";
//        echo "<td>"; echo anchor('setup/editsubject/' . $subjectlist->sub_id , "Edit", array('title' => 'Edit Subject', 'class' => 'red-link')); echo "&nbsp;&nbsp;&nbsp;"; echo anchor('setup/deletesubject/' . $subjectlist->sub_id ."/".$subjectlist->sub_name, "Delete"); echo"</td>";

        echo "</tr>";
    }
      else :
        echo "<tr>";
        echo "<td colspan= \"17\" align=\"center\"> No Records found...!</td>";
	echo "</tr>";	 
	 endif;
    echo "</table>";        

/* form data */

echo"</body>";
    $this->load->view('template/footer');
echo "</div>";
echo "</html>";
?>

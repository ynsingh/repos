<!--
    @name subject.php
    @author Sharad Singh(sharad23nov.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');

echo "<html>";
echo "<head>";

    $this->load->view('template/header');
    echo "<h1>"; 
    echo "Welcome "; echo$this->session->userdata('username'); 
    echo"</h1>";
    $this->load->view('template/menu');
?>
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
 <?php   

                    echo "<table style=\"padding: 8px 8px 8px 20px;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdoc#ViewSubjectDetail";
                    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;font-size:20px;margin-right:-837%;margin-top:-2%\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>

<br>
<div align="left">
<table style="margin-left:10px;">
<tr><td>
<?php echo anchor('setup/subject/', "Add Subject " ,array('title' => 'Add Subject' , 'class' => 'top_parent'));?>
</td></tr>
</table>
</div>

    <table>
    <tr colspan=2><td>
    <div  style="margin-left:10px;">

    <?php echo validation_errors('<div style="margin-left:30px;" class="isa_warning>','</div>');?>
    <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
     <?php if(isset($_SESSION['success'])){?>
       <div style="margin-left:30px" class="isa_success"><?php echo str_replace("%20"," ",$_SESSION['success']);?></div>
<?php    }
    if(isset($_SESSION['error']))
    {
?>        <div style="margin-left:30px"; class="isa_success">"<?php echo str_replace("%20"," ",$_SESSION['error']);?> </div>
<?php
    }
    echo "</div>";
    echo "</td></tr>";
    echo "</table>";
    echo "<div style=\"margin-left:30px;\">";

/* form data */

         echo "<table border=0 cellpadding=13 style=\"padding: 8px 8px 8px 20px;\" class=\"TFtable\">";
         echo "<thead><tr align=\"center\"><th>Sr. No </th><th>Subject Name</th><th>Subject Code </th><th>Subject Short Name</th><th>Subject Description</th><th>Subject Extention</th><th>Subject Extention</th><th>Action</th><th></th></tr></thead>";

    $srno = 0;
    foreach($subjectlists as $subjectlist)
    {
        $srno = $srno + 1;
        echo "<tr align=\"center\">";
        echo "<td>"; echo $srno; echo"</td>";
        echo "<td>"; echo $subjectlist->sub_name; echo"</td>";
        echo "<td>"; echo $subjectlist->sub_code; echo"</td>";
        echo "<td>"; echo $subjectlist->sub_short; echo"</td>";
        echo "<td>"; echo $subjectlist->sub_desc; echo"</td>";
        echo "<td>"; echo $subjectlist->sub_ext1; echo"</td>";
        echo "<td>"; echo $subjectlist->sub_ext2; echo"</td>";
        echo "<td>"; echo anchor('setup/editsubject/' . $subjectlist->sub_id , "Edit", array('title' => 'Edit Subject', 'class' => 'red-link')); echo "&nbsp;&nbsp;&nbsp;"; echo anchor('setup/deletesubject/' . $subjectlist->sub_id ."/".$subjectlist->sub_name, "Delete", array('title' => 'Delete Subject', 'class' => 'red-link')); echo"</td>";
//        echo "<td>"; echo anchor('setup/editsubject/' . $subjectlist->sub_id , "Edit", array('title' => 'Edit Subject', 'class' => 'red-link')); echo "&nbsp;&nbsp;&nbsp;"; echo anchor('setup/deletesubject/' . $subjectlist->sub_id ."/".$subjectlist->sub_name, "Delete"); echo"</td>";

        echo "</tr>";
    }    
    echo "</table>";        

/* form data */

    echo "</div>";
echo"</body>";
echo "<div align=\"center\">";  
    $this->load->view('template/footer');
echo "</div>";
echo "</html>";
?>

<!--
    @name subject.php
    @author Sharad Singh(sharad23nov.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');

echo "<html>";
echo"<title>Add Subject</title>";
echo "<head>";

    $this->load->view('template/header');
    echo "<h1>"; 
    echo "Welcome "; echo$this->session->userdata('username'); 
    echo"</h1>";
    $this->load->view('template/stumenu'); 
    //$this->load->view('template/menu');
?>
<?php
echo "</head>";
echo "<body>";
/*
    echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
    echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
    echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
    echo "Map";
    echo "<span  style=\"padding: 8px 8px 8px 20px;\"> ";
    echo "|";
    echo anchor('setup/viewsubject/', "View Subject", array('title' => 'Subject Detail' , 'class' => 'top_parent'));
    echo "<span  style=\"padding: 8px 8px 8px 20px;\"> ";
    echo "|";
    echo "<span  style=\"padding: 8px 8px 8px 20px;\">";
    echo "Add Subject";
    echo "</span>";
    echo "</td>";
    echo "</tr>";
    echo "</table>";
    echo"</br>";
*/
?>
<br>
<table style="margin-left:9px;">
<tr colspan="2"><td>
<div>
<?php echo anchor('setup/viewsubject/', "Subject List" ,array('title' => 'Subject List' , 'class' => 'top_parent'));
$help_uri = site_url()."/help/helpdoc#Subject";
echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:80%\">Click for Help</b></a>";
?>
</td></tr>
</table>
</div>

    <table>
    <tr colspan=2><td>
    <div  style="margin-left:30px;width:1700px;">

    <?php echo validation_errors('<div style="margin-left:30px;" class="isa_warning>','</div>');?>
    <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>

     <?php if(isset($_SESSION['success'])){?>
       <div style="margin-left:30px" class="isa_success"><?php echo $_SESSION['success'];?></div>
<?php    }
    if(isset($_SESSION['error']))
    {
?>        <div style="margin-left:30px"; class="isa_success">"<?php echo $_SESSION['error'];?> </div>
<?php
    }

    echo "</td></tr>";
    echo "</table>";
    echo "<div style=\"margin-left:30px;\">";

    /* Form */
/*
echo $stid;
echo $acadyear;
echo $semester;
*/
    echo "<table>";
    //echo form_open('studenthome/studentsubject/'.$acadyear."/".$stid."/".$semester);
    echo form_open('studenthome/studentsubject/');
    echo "<p>";
    echo "<tr><td>";
    echo form_label('Student Name', 'studname');
    echo"</td><td>";
    echo $compname;
    echo "</td><td>";echo form_error('compname');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Enrollment No', 'enrollno');
    echo"</td><td>";
    echo $enrollno;
    echo "</td><td>";
    echo form_error('enrollno');  
    echo"</td><td>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Roll No', 'rollno');
    echo"</td><td>";
    echo $rollno;
    echo "</td><td>";echo form_error('rollno');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Academic Year', 'acadyear');
    echo"</td><td>";
    echo $acadyear;
    echo "</td><td>";echo form_error('rollno');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Semester', 'semester');
    echo"</td><td>";
    echo $semester;
    echo "</td><td>";echo form_error('rollno');echo"</td></tr>";
    echo "</p>";



    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject1', 'subjectlist1');
    echo"</td><td>";
    echo form_dropdown('subjectlist1',$subject_list,'','class="abc"');
    //echo form_input();
    echo "</td><td>";echo form_error('subdesc');echo"</td>";
//    echo "<td>". img(array('src' => base_url() . "images/add.png", 'border' => '0', 'alt' => 'Add Subject', 'class' => 'addrow')) ;    echo  "</td>";
    echo "</tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject2', 'subjectlist2');
    echo"</td><td>";
    echo form_dropdown('subjectlist2',$subject_list,'','class="abc"');
    echo "</td><td>";echo form_error('subdesc');echo"</td>";
    echo "</tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject3', 'subjectlist3');
    echo"</td><td>";
    echo form_dropdown('subjectlist3',$subject_list,'','class="abc"');
    echo "</td><td>";echo form_error('subdesc');echo"</td>";
    echo "</tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject4', 'subjectlist4');
    echo"</td><td>";
    echo form_dropdown('subjectlist4',$subject_list,'','class="abc"');
    echo "</td><td>";echo form_error('subdesc');echo"</td>";
    echo "</tr>";
    echo "</p>";
    echo "<p>";

    echo "<tr><td>";
    echo form_label('Subject5', 'subjectlist5');
    echo"</td><td>";
    echo form_dropdown('subjectlist5',$subject_list,'','class="abc"');
    echo "</td><td>";echo form_error('subdesc');echo"</td>";
    echo "</tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject6', 'subjectlist6');
    echo"</td><td>";
    echo form_dropdown('subjectlist6',$subject_list,'','class="abc"');
    echo "</td><td>";echo form_error('subdesc');echo"</td>";
    echo "</tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject7', 'subjectlist7');
    echo"</td><td>";
    echo form_dropdown('subjectlist7',$subject_list,'','class="abc"');
    echo "</td><td>";echo form_error('subdesc');echo"</td>";
    echo "</tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject8', 'subjectlist8');
    echo"</td><td>";
    echo form_dropdown('subjectlist8',$subject_list,'','class="abc"');
    echo "</td><td>";echo form_error('subdesc');echo"</td>";
    echo "</tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject9', 'subjectlist9');
    echo"</td><td>";
    echo form_dropdown('subjectlist9',$subject_list,'','class="abc"');
    echo "</td><td>";echo form_error('subdesc');echo"</td>";
    echo "</tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject10', 'subjectlist10');
    echo"</td><td>";
    echo form_dropdown('subjectlist10',$subject_list,'','class="abc"');
    echo "</td><td>";echo form_error('subdesc');echo"</td>";
    echo "</tr>";
    echo "</p>";


    echo "<p>";
    echo "<tr><td>";
//    echo form_hidden('acdayear1', $acadyear);
//    echo form_hidden('semester1', $semester);
//    echo form_hidden('stid1', $stid);
    echo form_submit('submit', 'Submit');
    //echo form_button('submit', 'Submit');
    echo"</td></tr>";
    echo "</p>";

    echo form_close();
    echo "</table>";
    /* Form */
echo"</body>";
echo "<div align=\"center\">";  
    $this->load->view('template/footer');
echo "</div>";
echo "</html>";
?>

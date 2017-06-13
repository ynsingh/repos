<!--
    @name add_program_subject_paper.php
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
<!--<link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/message.css">-->
    <style>
    .abc{
        width:265px;
    }
    </style>

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
<br>
<div align="left">
<table style="margin-left:10px;">
<tr><td>
<?php echo anchor('map/programsubject/', " Subject Paper List" ,array('title' => 'Subject List' , 'class' => 'top_parent'));?>
</td></tr>
</table>
</div>
<table>
    <tr colspan="2"><td>
    <div align="left" style="margin-left:30px;width:1700px;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
        <?php
        };
        if(isset($_SESSION['error']))
        {
        ?> <div style="margin-left:30px"; class="isa_success">"<?php echo $_SESSION['error'];?> </div>
        <?php
        }
        ?>
    </div>
    </td></tr>
</table>

<?php
    echo "<div align=\"left\";>";
    //echo "<div style=\"margin-left:10px;\">";

/* Form */

    $subtype = array('Under Graduate'=>'Under Graduate','Post Graduate'=>'Post Graduate','Research'=>'Research','Diploma Course'=>'Diploma Course');    
    $acadyear = array();
    for($i = 2016; $i < date("Y")+10; $i++)
    {
        $j=$i+1;
        $temp =  $i. "-" .$j;
        $acadyear[$temp ] = $temp;
    }

    echo "<table style= \"margin-left:30px;\">";
    echo form_open('map/addprogramsubject');

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Degree','degree');
    echo "</td><td>";
    echo form_dropdown('degree',$program,'','class="abc"');
    //echo "</td><td>";
    //echo form_error('subjectcode');
    //echo"</td><td>"; echo "Optional";
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Academic Year','acadyear');
    echo "</td><td>";
    echo form_dropdown('acadyear',$acadyear,'','class="abc"');
    echo "</td>";
    //echo form_error('subjectcode');
    //echo"</td><td>"; echo "Optional";
    echo "</tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Name', 'subjectname');
    echo "</td><td>";
    echo form_dropdown('subjectname', $subject,'','class="abc"');
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Paper Category','subjecttype');
    echo "</td><td>";
    echo form_dropdown('subjecttype',$subtype,'','class="abc"');
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Paper No','subjectno');
    echo "</td><td>";
    echo form_input($subjectno);    
    echo "</td><td>";
    //echo form_error('subjectno');
    echo"</td><td>"; echo "Example : 1, Numeric values only ";
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Paper Name','papername');
    echo "</td><td>";
    echo form_input($papername);        
    echo "</td><td>";
    //echo form_error('papername');
    echo"</td><td>"; echo "Example : Physics";
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Paper Code','subjectcode');
    echo "</td><td>";
    echo form_input($subjectcode);
    echo "</td><td>";
    //echo form_error('subjectcode');
    echo"</td><td>"; echo "Example : Phy01";
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Paper Short Name','subjectshrname');
    echo "</td><td>";
    echo form_input($subjectshrname);
    //echo "</td><td>";
    //echo form_error('subjectcode');
    //echo"</td><td>"; echo "Optional";
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Paper Description','subjectdesc');
    echo "</td><td>";
    echo form_input($subjectdesc);
    //echo "</td><td>";
    //echo form_error('subjectcode');
    //echo"</td><td>"; echo "Optional";
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_submit('submit', 'Submit');
    echo form_reset('reset','Clear');
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

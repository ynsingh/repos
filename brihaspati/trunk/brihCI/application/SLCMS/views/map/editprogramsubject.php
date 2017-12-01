<!--
    @name add_program_subject_paper.php
    @author Sharad Singh(sharad23nov.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');

echo "<html>";
echo "<head>";

    $this->load->view('template/header');
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
echo "<body>";?>
<p>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
</p>
<?php
	echo "<script>";
        function goBack() {
         window.history.back();
        }
        echo "</script>";
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

<div >
<table style="width:100%;">
<tr><td>
<?php echo anchor('map/programsubject/', " Subject Paper List" ,array('title' => 'Subject List' , 'class' => 'top_parent'));
           $help_uri = site_url()."/help/helpdoc#ViewStudyCenterandProgramwithSeat";
           echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;\">Click for Help</b></a>";
?>
</td></tr>
</table>
</div>
<table>
    <tr><td>
    <div align="left" style="margin-left:2%;">
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="margin-left:2%;" class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
        <?php
        };
        if(isset($_SESSION['error']))
        {
        ?> <div style="margin-left:2%"; class="isa_success">"<?php echo $_SESSION['error'];?> </div>
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
/*
    $subtype = array('UG'=>'UG','PG'=>'PG','R'=>'R','DIP'=>'DIP');    
    $acadyear = array();
    for($i = 2016; $i < date("Y")+10; $i++)
    {
        $j=$i+1;
        $temp =  $i. "-" .$j;
        $acadyear[$temp ] = $temp;
    }
*/
    echo "<table style= \"margin-left:2%;\">";
    echo form_open('map/editprogramsubject/'.$paperid);

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Program Category','papercat');
    echo "</td><td>";
    echo form_input($papercat);
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Department','paperdept');
    echo "</td><td>";
    echo form_input($paperdept);
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Degree','degree');
    echo "</td><td>";
    echo form_input($degree);
    //echo "</td><td>";
    //echo form_error('subjectcode');
    //echo"</td><td>"; echo "Optional";
    echo "</td></tr>";
    echo "</p>";
    echo "<p>";
    echo "<tr><td>";
    echo form_label('Semester / Yearly','papersem');
    echo "</td><td>";
    echo form_input($papersem);
    echo "</td></tr>";
    echo "</p>";
/*
    echo "<p>";
    echo "<tr><td>";
    echo form_label('Branch','prgbranch');
    echo "</td><td>";
    echo form_input($prgbranch);    
    echo "</td><td>";
    //echo form_error('subjectno');
    echo"</td><td>"; echo "Example : Physics, Computer Science & Engineering ";
    echo "</td></tr>";
    echo "</p>";
 */
    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Name', 'subjectname');
    echo "</td><td>";
    echo form_input($subjectname);
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Type','papertype');
    echo "</td><td>";
    echo form_input($papertype);
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Academic Year','acadyear');
    echo "</td><td>";
    echo form_input($acadyear);
    echo "</td>";
    //echo form_error('subjectcode');
    //echo"</td><td>"; echo "Optional";
    echo "</tr>";
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
    echo"<td>";
    echo form_submit('submit', 'Update');
    echo form_close();
    echo "<button onclick=\"goBack()\" >Back</button>";
    //echo form_reset('reset','Clear');
   // echo form_button('submit', 'Submit');
    echo"</td></tr>";
    echo "</p>";
    echo"</td>";
    echo "</table>";
/* Form */
    echo"</body>";
    echo "<div align=\"center\">";  
    $this->load->view('template/footer');
echo "</div>";
echo "</html>";
?>

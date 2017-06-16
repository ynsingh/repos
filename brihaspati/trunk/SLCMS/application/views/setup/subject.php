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
    $this->load->view('template/menu');
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

    echo "<table>";
    echo form_open('setup/subject');
    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Name', 'subname');
    echo"</td><td>";
    echo form_input($subname);
    echo "</td><td>";echo form_error('subname');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Code', 'subcode');
    echo"</td><td>";
    echo form_input($subcode);
    echo "</td><td>";
    echo form_error('subcode');  
    echo"</td><td>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Short', 'subshort');
    echo"</td><td>";
    echo form_input($subshort);
    echo "</td><td>";echo form_error('subshort');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Description', 'subdesc');
    echo"</td><td>";
    echo form_input($subdesc);
    echo "</td><td>";echo form_error('subdesc');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Ext1', 'subext1');
    echo"</td><td>";
    echo form_input($subext1);
    echo "</td><td>";echo form_error('subext1');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Ext2', 'subext2');
    echo"</td><td>";
    echo form_input($subext2);
    echo "</td><td>";echo form_error('subext2');echo"</td></tr>";
    echo "</p>";

  //  echo "<p>";
    echo "<tr><td>";
    echo"<td>";
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

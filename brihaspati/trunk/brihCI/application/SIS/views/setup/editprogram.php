<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<head>
</head>
<body>
<title>Edit Program</title>
<script>
        function goBack() {
        window.history.back();
        }
    </script>
<div>
<?php $this->load->view('template/header.php');?>

</div>
<?php
/*    echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
    echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
    echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
    echo "Setup";
    echo "<span  style='padding: 8px 8px 8px 20px;'> ";
    echo "|";
    echo anchor('setup/editprogram/', "Update Program", array('title' => 'Update Program' , 'class' => 'top_parent'));
    echo "<span  style='padding: 8px 8px 8px 20px;'> ";
    echo "|";
    echo "<span  style='padding: 8px 8px 8px 20px;'>";
    echo anchor('setup/viewprogram/', "View Program", array('title' => 'View Program' , 'class' => 'top_parent'));
    //echo "View Programs";
    echo "</span>";
    echo "</td>";
    echo "</tr>";
    echo "</table>";
*/
?>

<br>
<div align="left">
<table style="margin-left:2%;">
<tr><td>
<?php echo anchor('setup/viewprogram/', "Program List" ,array('title' => 'Program List' , 'class' => 'top_parent'));?>
</td></tr>
</table>
</div>


<div align='left' style="margin-left:2%;">
<!--    <form action="setup/editprogram" method="POST">-->
<?php
    echo "<table>";
    echo validation_errors();
    echo form_open('setup/editprogram/'. $prgid);
    echo "<p>";
    echo "<tr><td>";
    echo form_label('Campus', 'prgcampus');
    echo"</td><td>";
    echo form_input($prgcampus);
    echo "</td>";
    echo "</p>";
    echo "<p>";
    echo "<tr><td>";
    echo form_label('Department', 'prgdepartment');
    echo"</td><td>";
    echo form_input($prgdepartment);
    echo "</td>";
    echo "</p>";
    echo "<p>";
    echo "<tr><td>";
    echo form_label('Program Category', 'prgcat');
    echo"</td><td>";
    echo form_input($prgcat);
    echo "</td>";
    echo "</p>";


    echo "<p>";
    echo "<tr><td>";
    echo form_label('Program Pattern', 'prgpattern');
    echo"</td><td>";
    echo form_input($prgpattern);
    echo "</td>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Program Name', 'prgname');
    echo"</td><td>";
    echo form_input($prgname);
    echo "</td>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Program Branch', 'prgbranch');
    echo"</td><td>";
    echo form_input($prgbranch);
    echo "</td>";
    echo "</p>";


    echo "<p>";
    echo "<tr><td>";
    echo form_label('Program Code', 'prgcode');
    echo"</td><td>";
    echo form_input($prgcode);
    echo "</td>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Program Short', 'prgshort');
    echo"</td><td>";
    echo form_input($prgshort);
    echo "</td>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Program Description', 'prgdesc');
    echo"</td><td>";
    echo form_input($prgdesc);
    echo "</td>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Program Credit', 'prgcredit');
    echo"</td><td>";
    echo form_input($prgcredit);
    echo "</td>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Seat Available', 'prgseat');
    echo"</td><td>";
    echo form_input($prgseat);
    echo "</td>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Program Min Time', 'prgmintime');
    echo"</td><td>";
    echo form_input($prgmintime);
    echo "</td>";
    echo "<td>";
    echo "in Years";
    echo "</td>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Program Max Time', 'prgmaxtime');
    echo"</td><td>";    
    echo form_input($prgmaxtime);
    echo "</td>";
     echo "<td>";
    echo "in Years";
    echo "</td>";
    echo "</p>";

  /*  echo "<p>";
    echo "<tr><td>";
    echo form_label('Program Creator Id', 'prgcrtid');
    echo"</td><td>";
    echo form_input($prgcreatorid);
    echo "</td>";
    echo "</p>";*/

    echo "<p>";
    echo "<tr>";
    echo "<td> </td>";
    echo "<td>";
    echo form_hidden('prg_id', $prgid);
   // echo"<td>";
    echo form_submit('submit', 'Update');
    echo form_close();
    echo "<button onclick=\"goBack()\" >Go Back</button>";

    echo "</td>";
    echo "</tr>";
    echo "</p>";
    echo "</table>";
?>
    
</div>    
<div align="left">
<?php $this->load->view('template/footer.php');?>
</div>
</body>
</html>


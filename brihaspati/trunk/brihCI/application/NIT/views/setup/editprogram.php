<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<head>
<?php $this->load->view('template/header.php');?>
<!--h1>Welcome <?//= $this->session->userdata('username')?> </h1-->
<?php //$this->load->view('template/menu.php');
?>
</head>
<title>Edit Program</title>
<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	
<body>
<script>
        function goBack() {
        window.history.back();
        }
    </script>
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
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
	<table width="100%">
	 <tr>
	<?php //echo anchor('setup/viewprogram/', "Program List" ,array('title' => 'Program List' , 'class' => 'top_parent'));
	echo "<td align=\"center\" width=\"100%\">";
        echo "<b>Update Program and Seat Details</b>";
        echo "</td>";
	?>
	  </tr>
	</table>
		    <table width="100%">
		      <tr><td>
		        <div>
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    <?php echo form_error('<div  class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                    <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>

                    <?php
                    };
                    ?>
                </div>
       </tr></td>
</table>

<!--    <form action="setup/editprogram" method="POST">-->
<?php
    echo "<table>";
//    echo validation_errors();
    echo form_open('setup/editprogram/'. $prgid);
   /* echo "<p>";
    echo "<tr><td>";
    echo form_label('Institute Name', 'prgcampus');
    echo"</td><td>";
    echo form_input($prgcampus);
    echo "</td>";
    echo "</p>";
	*/
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
    
<div>
<?php $this->load->view('template/footer.php');?>
</div>
</body>
</html>


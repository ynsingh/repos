<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<?php $this->load->view('template/header.php');?>
 <h1>Welcome <?= $this->session->userdata('username') ?></h1>
 <?php $this->load->view('template/menu.php');?>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/message.css">
</head>

<body>
<div style="margin-left:30px;">
    <?//php echo form_open('setup/program');?>
</div>
<?php
    echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
    echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
    echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
    echo "Setup";
    echo "<span  style='padding: 8px 8px 8px 20px;'> ";
    echo "|";
    echo anchor('setup/viewprogram/', "View Program", array('title' => 'View Program' , 'class' => 'top_parent'));
    echo "<span  style='padding: 8px 8px 8px 20px;'> ";
    echo "|";
    echo "<span  style='padding: 8px 8px 8px 20px;'>";
    echo "Add Program";
//    echo "View Programs";
    echo "</span>";
    echo "</td>";
    echo "</tr>";
    echo "</table>";
?>

<table> 
            <tr colspan="2"><td>    
                <div align="left" style="margin-left:30px;width:1700px;">
       
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                    <?php
                    };
                    ?>
               
                </div>
            </td></tr>  
</table>
<div style="margin-left:30px;">
    <form action="<?php echo site_url('setup/program');?>" method="POST" class="form-inline">
    <table style="margin-left:30px;">
    <tr><td>
        <label for="prgcat">Program Category</label></td>
        <td><input type="text" name="prgcat"/>
        </td><td><?php echo form_error('prgcat')?>
	</td><td>Example : UG, PG, R, Dip etc
    </td></tr> 
    <tr><td>
        <label for="prgname">Program Name</label></td>
        <td><input type="text" name="prgname" />
        </td><td><?php echo form_error('prgname')?>
	</td><td>Example : Batchlor of Art, Master of Art etc  
    </td></tr>
    <tr><td>
        <label for="prgbranch">Program Branch</label></td>
        <td><input type="text" name="prgbranch" />
        </td><td><?php echo form_error('prgbranch')?>
	</td><td>Example : Physics, Math  etc
    </td></tr>
    <tr><td>
        <label for="prgseat">Total Seat</label></td>
        <td><input type="text" name="prgseat" />
        </td><td><?php echo form_error('prgseat')?>
    </td></tr>

    <tr><td>
        <label for="prgcode">Program Code</label></td>
        <td><input type="text" name="prgcode" />
        </td><td><?php echo form_error('prgcode')?>
    </td></tr>
    <tr><td>
        <label for="prgshort">Program Short</label></td>
        <td><input type="text" name="prgshort" />
        </td><td><?php echo form_error('prgshort')?>
    </td></tr>
    <tr><td>
        <label for="prgdesc">Program Description</label></td>
        <td><input type="text" name="prgdesc" />
        </td><td><?php echo form_error('prgdesc')?>
    </td></tr>
    <tr><td>
        <label for="prgmintime">Program Min Time</label></td>
        <td><input type="text" name="prgmintime" />
        </td><td><?php echo form_error('prgmintime')?>
    </td></tr>
    <tr><td>
        <label for="prgmaxtime">Program Max Time</label></td>
        <td><input type="text" name="prgmaxtime" />
        </td><td><?php echo form_error('prgmaxtime')?>
    </td></tr>
<!--    <tr><td>
        <label for="prgcrtid">Program Creator Id</label></td>
        <td><input type="text" name="prgcrtid" />
        </td><td><?php echo form_error('prgcrtid')?>
    </td></tr>
-->
<!--    <tr><td>
        <input type="submit" name="submit" value="Add Program">
    </td></tr>
-->
    <tr>
    <td colspan="2">   
        <button name="program" >Submit</button>
        <button name="reset" >Reset</button>
    </td>
    </tr>
    </table>
</div>
    </form>

    <?php echo form_close();	?>
</body>
<?php $this->load->view('template/footer.php');?>
</html>

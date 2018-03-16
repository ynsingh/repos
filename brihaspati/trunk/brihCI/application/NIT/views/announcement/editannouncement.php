<!--@name editannouncement.php
    @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit Exam Center</title>
<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	
    <head>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<//?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>

        <?php $this->load->view('template/header'); ?>
        <!--h1>Welcome <?//= $this->session->userdata('username') ?>  </h1-->
        <?php //$this->load->view('template/menu');?>
    </head>
    <body>
 <script>
        function goBack() {
        window.history.back();
        }
    </script>
<script>
$( function() {
           $( "#StartDate,#EndDate" ).datepicker({
                dateFormat: 'yy-mm-dd',
                minDate: 0
                });
                });
</script>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
 <table width="100%">
            <tr>
		    <?php //echo anchor('announcement/viewannouncement/', "View Announcement" ,array('title' => 'View Announcement' , 'class' => 'top_parent'));
		    echo "<td align=\"center\" width=\"100%\">";
                    echo "<b>Update Announcement Details</b>";
                    echo "</td>";
		?>
	  </tr>
</table>
	        <table width="100%">
                <tr><td>
                <div>
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?>

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
              </td>
          </tr>
        </table>
<table>
<?php
           echo form_open ('announcement/editannouncement/' . $id);
 		echo "<tr>";
                echo "<td>";
		echo form_label('Announcement Component Name', '');
                echo "</td>";
                echo "<td>";
		$anoucname=$anou_cname['value'];
			echo "<select name=\"anou_cname\"class=\"my_dropdown\" style=\"width:100%;\">";
			echo "<option value=$anoucname class=\"dropdown-item\">$anoucname</option>";
			echo "<option value=\"disabled selected\">------Select Announcement Component Name------</option>";
			echo "<option value=\"SLCMS\" class=\"dropdown-item\">SLCMS</option>";
                        echo "<option value=\"HRM\" class=\"dropdown-item\">HRM</option>";
                        echo "<option value=\"SIS\" class=\"dropdown-item\">SIS</option>";
		echo " </select>";
		echo "</td>";
               echo "<td>";

                echo "</td>";
                echo "</tr>";
		echo "<tr>";
		echo "<td>";
                echo form_label('Announcement Type', '');
                echo "</td>";

               echo "<td>";
                $annotype=$anou_type['value'];
                    echo "<select name=\"anou_type\"class=\"my_dropdown\" style=\"width:100%;\">";
                    echo "<option value=$annotype class=\"dropdown-item\">$annotype</option>";
                    echo "<option value=\"disabled selected\">------Select Announcement Type------</option>";
                    echo "<option value=\"Addmision\" class=\"dropdown-item\">Addmision</option>";
                    echo "<option value=\"Acadmic\" class=\"dropdown-item\">Acadmic</option>";
                    echo "<option value=\"Exam\" class=\"dropdown-item\" >Exam</option>";
		    echo "<option value=\"Rent\" class=\"dropdown-item\" >Rent</option>";
		    echo "<option value=\"General\" class=\"dropdown-item\" >General</option>";
                    echo "</select>";
		echo "</td>";
               echo "<td>";
                echo "</td>";
                echo "</tr>";
  echo "<tr>";
                echo "<td>";
                echo form_label('Announcement Title', 'anou_title');
                echo "</td>";
                echo "<td>";
                echo form_input($anou_title);
                echo "</td>";
                echo "</tr>";
		 echo "<tr>";
                echo "<td>";
                echo form_label('Announcement Description', 'anou_description');
                echo "</td>";
                echo "<td>";
                echo form_input($anou_description);
                echo "</td>";
                echo "</tr>";
?>
 <tr>
   <td><label for="anou_publishdate" class="control-label">Start Date:</label></td>
   <td><input type="text" name="anou_publishdate" id="StartDate" value=<?php echo $anou_publishdate['value'];?> class="form-control" size="40" required="required"/><br>
   <td><?php echo form_error('anou_publishdate')?></td>
   </td>
   </tr>
   <tr>
   <td><label for="anou_expdate" class="control-label">Close Date:</label></td>
   <td><input type="text" name="anou_expdate" id="EndDate"  value=<?php echo $anou_expdate['value'];?> class="form-control" size="40" required="required"/><br>
   <td><?php echo form_error('anou_expdate')?></td>
   </td>
   </tr>
<?php
echo "<tr>";
                echo "<td>";
                echo form_label('Announcement Remark', 'anou_remark');
                echo "</td>";
                echo "<td>";
                echo form_input($anou_remark);
                echo "</td>";
                echo "</tr>";

 echo "<tr>";
                echo "<td>";
                echo "</td>";
                echo "<td>";
                echo form_hidden('id', $id);
                echo form_submit('submit', 'Update');
                echo form_close();
                echo "<button onclick=\"goBack()\" >Back</button>";
                echo "</td>";
                echo "</tr>";

        ?>

        </table>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



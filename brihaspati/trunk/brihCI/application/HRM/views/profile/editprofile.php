<!--@name editprofile.php
    @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit Profile</title>
    <head>    
        	<?php $this->load->view('template/header'); ?>
            	<h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
		<?php
                        if($this->session->userdata('id_role') == 1){
                                $this->load->view('template/menu');
                        }
                        if($this->session->userdata('id_role') == 2){
                                $this->load->view('template/facultymenu');
                        }
                        if($this->session->userdata('id_role') == 3){
                                $this->load->view('template/stumenu');
                        }
                ?>
    </head>
    <body>
<script>
        function goBack() {
        window.history.back();
        }
</script>

 	<table style="margin-left:30px;">
            <tr colspan="2"><td>
                <div style="width:1700px;">
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                </div> </br>
        </td></tr>
        </table>
 	<table style="padding: 8px 8px 8px 30px;">

         <?php
	echo form_open('profile/editprofile/');

		echo "<tr>";
                echo "<td>";
                echo form_label('First Name', 'firstname');
                echo "</td>";
                echo "<td>";
                echo form_input($firstname);
                echo "</td>";
            	echo "</tr>";

	        echo "<tr>";
                echo "<td>";
                echo form_label('Last Name', 'lastname');
                echo "</td>";
                echo "<td>";
                echo form_input($lastname);
                echo "</td>";
                echo "</tr>";
 
                echo "<tr>";
                echo "<td>";
                echo form_label('Address', 'address');
                echo "</td>";
                echo "<td>";
                echo form_input($address);
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Secondary Email ID', 'secmail');
                echo "</td>";
                echo "<td>";
                echo form_input($secmail);
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Mobile No.', 'mobile');
                echo "</td>";
                echo "<td>";
                echo form_input($mobile);
                echo "</td>";
                echo "</tr>";

		echo "<td>";
                echo form_hidden('id', $id);
                echo"<td>";
                echo form_submit('submit', 'Update');

                echo form_close();
                echo "<button onclick=\"goBack()\" >Back</button>";
                echo "</td>";
                echo "</tr>";
                echo"</td>";
                ?>
 </table>
  </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

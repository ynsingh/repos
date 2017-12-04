<!---@name editcontact.php                                                                                                                                                            
    @author Rekha Devi Pal (rekha20july@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit Contact</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
            <!--h1>Welcome <?= $this->session->userdata('username') ?>  </h1-->
        <?php $this->load->view('template/menu');?>
    </head>
    <body>
    <table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
    <script>
        function goBack() {
        window.history.back();
        }
    </script>


        <!--<//?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            //echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo anchor('setup/displayrole/', "View Role Details", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "Edit role";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>--!>
        <table width="100%">
                <tr><td>
                        <?php echo anchor('setup/displaycontact/', "View Contact Us" ,array('title' => ' Contact Us Detail ' , 'class' => 'top_parent'));?>
                        <div>
                        <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                        <?php echo form_error('<div class="isa_error">','</div>');?>
                        <?php if(isset($_SESSION['success'])){?>
                                <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                        <?php }; ?>
                        </div> </br>
                </td></tr>
        </table>
        <table>
        <?php
                echo form_open('setup/editcontact/'. $ascu_id);
                echo "<tr>";
                echo "<td>";
                echo form_label('Name : ', 'ascu_name');
                echo "</td>";
                echo "<td>";
                echo form_input($ascu_name);
                echo "</td>";
		echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Email Id : ', 'ascu_emailid');
                echo "</td>";
                echo "<td>";
                echo form_input($ascu_emailid);
                echo "</td>";
                echo "</tr>";
              
                echo "<tr>";
                echo "<td>";
                echo form_label('Mobile No : ', 'ascu_phoneno');
                echo "</td>";
                echo "</td>";
                echo "<td>";
                echo form_input($ascu_phoneno);
                echo "</td>";
                echo "</tr>";
              
                echo "<tr>";
                echo "<td>";
                echo form_label('Regarding: ', 'ascu_regards');
                echo "</td>";
                echo "</td>";
                echo "<td>";
                echo form_input($ascu_regards);
                echo "</td>";
                echo "</tr>";               
 
                echo "<tr>";
                echo "<td>";
                echo "</td>";
                echo "<td>";
                echo form_hidden('ascu_id', $ascu_id);
                echo form_submit('submit', 'Update');
                echo " ";
                echo form_close();
                echo "<button onclick=\"goBack()\" >Back</button>";
                echo "</td>";
                echo "</tr>";
 ?>
       </table>
</body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>                                                                                                                      
               





              

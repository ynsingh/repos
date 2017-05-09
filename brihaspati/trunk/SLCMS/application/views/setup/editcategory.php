<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name editcategory.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/message.css">
  
    </head>
    <body>
        <?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo anchor('setup/displaycategory/', "View Category", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "Edit Category";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>
 
       <table>
            <tr colspan="2"><td>
                <div style="margin-left:30px;width:1700px;">
                    <?php echo validation_errors('<div style="margin-left:30px;" class="isa_warning">','</div>');?>
                    <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                        <?php if(isset($_SESSION['err_message'])){?>
                          <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                        <?php
                    };
		?>	  

                </div></br> 
            </td></tr>
        </table>

        <table style="padding: 8px 8px 8px 30px;">  
 
        <?php

            echo form_open('setup/editcategory/' . $cat_id);
       
            echo "<tr>";
                echo "<td>";
                    echo form_label('Category Name', 'cname');
                echo "</td>";
                echo "<td>";
                    echo form_input($cname);
                echo "</td>";
                echo "<td>";
                    echo "Example: Scheduled Tribe";
                echo "</td>";
            echo "</tr>";

            //echo "<p>";
            echo "<tr>";
                echo "<td>";
                   echo form_label('Category Code', 'ccode');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($ccode);
                echo "</td>";
                echo "<td>";
                   
                echo "</td>";
            echo "</tr>";
        
            echo "<tr>";
                echo "<td>";
                    echo form_label('Category Short Name', 'csname');
                //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($csname);
                echo "</td>";
                echo "<td>";
                    echo " Example : ST";
                echo "</td>";
            echo "</tr>";
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('Category Description', 'cdesc');
                echo "</td>";
                echo "<td>";
                    //echo "<br />";
                    echo form_input($cdesc);
                echo "</td>";
                echo "<td>";
                    
                echo "</td>";
            echo "</tr>";
            //echo "</p>";
        
            // echo "<p>";
        
            echo "<tr>";
                echo "<td>";
                    echo form_hidden('cat_id', $cat_id);
                    echo form_submit('submit', 'Update');
                    echo " ";
        
                    echo anchor('setup/displaycategory', 'Back', 'Back to Display Category page');
                    // echo "</p>";
                echo "</td>";
            echo "</tr>";

            echo form_close();
        ?>
 
        </table>   
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>


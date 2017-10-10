  
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name editdepartment.php 
  @author Raju Kamal(kamalraju8@gmail.com)
 -->
  
<html>
  <head>    
    <title>Edit Department</title>
        <?php $this->load->view('template/header'); ?>
        <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
      
    </head>
    <body>
<script>
        function goBack() {
        window.history.back();
        }
    </script>

<!--
        <?php
           echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            //echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo anchor('setup/dispdepartment/', "View Department", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
          //  echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
           // echo "Edit Department";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>-->
        <table width="100%">
    <div style="margin-left:4%; width:100%;">
      <!-- <?php echo anchor('','Update Department',array('title'=>'Edit Detail')); ?>-->
      
     <style="margin-left:4%;"> 
            <tr><td>    
                <div style="margin-left:4%;width:100%;">
                    <?php echo validation_errors('<div style="margin-left:4%;" class="isa_warning">','</div>');?>
                    <?php echo form_error('<div style="margin-left:4%;" class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div style="margin-left:4%;" class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                </div>  
            </td></tr>  
        </table>    
        <table style="padding: 8px 8px 8px 50px;">  
 
        <?php

           echo form_open('setup/editdepartment/' . $id);
            echo "<tr>";
                echo "<td>";
                echo form_label('University Code', 'deptorgcode');
                echo "</td>";
                echo "<td>";
 //             	$whval= $deptorgcode['value'];
   //             $whname['value']= $this->common_model->get_listspfic1('org_profile','org_name','org_code',$whval)->org_name;
     //           echo form_input($whname);
                echo form_input($deptorgcode);

                echo "</td>";
                echo "<td>";
                    
                echo "</td>";
            echo "</tr>";
           echo "<tr>";
                echo "<td>";
                echo form_label('Campus Code', 'deptsccode');
                echo "</td>";
                echo "<td>";
                 echo form_input($deptsccode);
                 echo "</td>";
                 echo "<td>";
                 echo "</td>";
                 echo "</tr>";
                
               echo "</td>";
            echo "</tr>";
           echo "<tr>";
                echo "<td>";
                echo form_label('Authorities Name', 'authorities');
                echo "</td>";
                echo "<td>";
                 echo form_input($authorities);
                 echo "</td>";
                 echo "<td>";
                 echo "</td>";
                 echo "</tr>";
                
       
            echo "<tr>";
                echo "<td>";
                echo form_label('School/Faculty Code', 'deptschoolcode');
                echo "</td>";
                echo "<td>";
                echo form_input($deptschoolcode);
                echo "</td>";
                echo "<td>";
                    echo "Example: Sbs";
                echo "</td>";
            echo "</tr>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('School/Faculty Name', 'deptschoolname');
                    
                echo "</td>";
                echo "<td>";
                    echo form_input($deptschoolname);
                echo "</td>";
                echo "<td>";
                    echo "Example :School of basic science ";
                echo "</td>";
            echo "</tr>";
        
            echo "<tr>";
                echo "<td>";
                    echo form_label('Department Code','deptcode');
                
                echo "</td>";
                echo "<td>";
                    echo form_input($deptcode);
                echo "</td>";
                echo "<td>";
                    echo "Example : Phy";
                echo "</td>";
            echo "</tr>";
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('Department Name', 'deptname');
                echo "</td>";
                echo "<td>";
                   
                    echo form_input($deptname);
                echo "</td>";
                echo "<td>";
                    echo " Example :  Department of physics";
                echo "</td>";
            echo "</tr>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('Department Nick Name', 'deptshort');
         
                echo "</td>";
                echo "<td>";
                    echo form_input($deptshort);
                echo "</td>";
                echo "<td>";
                    echo " Example :Phy";
                echo "</td>";
            echo "</tr>";
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('Department Description', 'deptdescription');
                echo "</td>";
                echo "<td>";
                echo form_input($deptdescription);
                echo "</td>";
                echo "<td>";
                echo " Example : Physics Department";
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


	

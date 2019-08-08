
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name editdepartment.php 
  @author Raju Kamal(kamalraju8@gmail.com)
  @Modification: Om Prakash(omprakashkgp@gmail.com) Dec-2017
 -->

<html>
  <head>    
    <title>Edit Department</title>
        <?php $this->load->view('template/header'); ?>
        

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
        <table width="70%">
	     <tr><td align=left;>
                <?php echo anchor('setup/dispdepartment','Department List',array('title'=>'View Detail','class' => 'top_parent'  ));
                ?>
                
             </td></tr>

      <!-- <?php echo anchor('','Update Department',array('title'=>'Edit Detail')); ?>-->
            <tr><td>
               <div align="left" style="margin-left:0%;width:90%;">
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    <?php echo form_error('<div  class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div  class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                     if(isset($_SESSION['err_message'])){?>
                        <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>

                    <?php
                    };
                    ?>
                </div>
            </td></tr>
        </table>
        <table>

        <?php

           echo form_open('setup/editdepartment/' . $id);
            echo "<tr>";
                echo "<td>";
                echo form_label('University Name', 'deptorgcode');
                echo "</td>";
                echo "<td>";
 //                     $whval= $deptorgcode['value'];
   //             $whname['value']= $this->common_model->get_listspfic1('org_profile','org_name','org_code',$whval)->org_name;
     //           echo form_input($whname);
                echo form_input($deptorgcode);

                echo "</td>";
                echo "<td>";

                echo "</td>";
            echo "</tr>";
           echo "<tr>";
                echo "<td>";
                echo form_label('Campus Name', 'deptsccode');
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
                //echo form_input($authorities); ?>
		<select name="authorities" id="authorities" class="my_dropdown" style="width:100%;">
		<option value="<?php echo  $this->lgnmodel->get_listspfic1('authorities','id','name', $authorities["value"])->id; ?>" > <?php echo $authorities["value"]; ?></option> 
		 <?php foreach($this->authresult as $datas): ?>
                    <option value="<?php echo $datas->id ; ?>"><?php echo $datas->name ; ?></option>
                 <?php endforeach; ?>
		</select>	
                <?php echo "</td>"; 
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
                    echo form_label('Department Email', 'deptemail');
                echo "</td>";
                echo "<td>";

                    echo form_input($deptmail);
                echo "</td>";
                echo "<td>";
                    echo " Example :  ee@iitk.ac.in";
                echo "</td>";
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
<p>&nbsp;</p>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



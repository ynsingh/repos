<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name viewfull_profile.php
@ author sumit saxena[sumitsesaxena@gmail.com]
 -->
<html>
<title>View Faculty list</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <style type="text/css" media="print">
            @page {
                size: auto;   /* auto is the initial value */
                margin:0;  /* this affects the margin in the printer settings */
            }
        </style>
<?php $current="deptexam"; ?>

        <script>
      
            function printDiv(printme) {
                var printContents = document.getElementById(printme).innerHTML; 
                var originalContents = document.body.innerHTML;      
                document.body.innerHTML = "<html><head><title></title></head><body><img src='<?php echo base_url(); ?>uploads/logo/logotanuvas.jpeg' alt='logo' align='left' style='width:70%;height:100px;'>"+" <div style='width:70%;height:100%;'> " + printContents + "</div>"+"</body>";
                // document.body.style.fontSize = "x-small";
                //document.body.style. = "x-small";
                window.print();  
                // document.body.style.fontSize = "initial";
                document.body.innerHTML = originalContents;
            }
		 function goBack() {
        		window.history.go(-2);
	        }

        </script>

    </head>
    <body>
    <table style="width:100%;">
        <tr>
            <td>
                <img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')" style='width:30px;height:30px;' title="Click for print" >  
                
            </td> 
	 <td align=right>
        <a href="#" onclick="goBack()"><img src='<?php echo base_url(); ?>uploads/icons/back1.png' title="Back"></a>
	</td>
        </tr>
    </table>        
    <div id="printme">   
      
<table style="width:100%;" border=0>
    <div align="left">
            
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                
	        <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>
                    <div  class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>    
                  
        </div>
 
<tr>
<td valign="top" width=170>

		<?php include 'profiletab.php'; ?>
	   
</td>
<?php     
	$hdept=$this->sismodel->get_listspfic1('user_role_type','deptid','userid',$this->session->userdata('id_user'))->deptid; 
	$roleid=$this->session->userdata('id_role');
?>
<td valign="top">		
		 <table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                        <tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Departmental Exam Passed Details</b></td>
                            <td align="right">
                                <?php
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
                                        echo anchor("empmgmt/add_deptexamdata/{$emp_id}"," Add ",array('title' => ' Add Departmental Exam Data' , 'class' => 'red-link'));
                                }
                                ?>

                            </td>
                        <tr>
                </table>
                <table class="TFtable" align="center">
                    <thead>
                        <tr>
                            <th>Department Exam</th>
                            <th>Date of Passing</th>
                            <th ></th>
                        </tr>
                    </thead>
                    <tbody>

                        <?php if( !empty($deptexamdata) ):  ?>
                            <?php foreach($deptexamdata as $record){;
?>
                            <tr>
                                <td>
<?php
                                echo $record->sdep_examname;
                                ?>
                                </td>
                                <td>
                                    <?php
                                    echo $record->sdep_passdate;
                                    ?>
                               </td>
                                <td>
                                <?php
                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
                                //              echo anchor("empmgmt/edit_deptexamdata/{$record->empsd_id}","Edit",array('title' => ' Edit Departmental Exam Data' , 'class' => 'red-link'));
                                        }
                                ?>
                                </td>
                            </tr>
                        <?php }; ?>
                        <?php else : ?>
                            <td colspan= "13" align="center"> No Records found...!</td>
                        <?php endif;?>
                    </tbody>
                </table>

		<table style="width:100%;">
        	<tr>
        	<td align=right>
		<a href="#" onclick="goBack()"><img src='<?php echo base_url(); ?>uploads/icons/back1.png' title="Back"></a>
        	</td>
        	</tr>
    		</table>
	<br>
</td>
</tr>


</table>
       
   </div>      
 <p> &nbsp; </p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>
</html>


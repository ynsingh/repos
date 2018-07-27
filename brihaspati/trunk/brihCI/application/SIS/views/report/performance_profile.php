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
<?php $current="perform"; ?>

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
//	$hdept=$this->sismodel->get_listspfic1('user_role_type','deptid','userid',$this->session->userdata('id_user'))->deptid; 
//	$roleid=$this->session->userdata('id_role');
	$roleid=$this->session->userdata('id_role');
        if($roleid == 5){
                $hdept=$this->sismodel->get_listspfic1('user_role_type','deptid','userid',$this->session->userdata('id_user'))->deptid;
                $hempcode=$this->sismodel->get_listspfic1('hod_list','hl_empcode','hl_userid',$this->session->userdata('id_user'))->hl_empcode;
                $hempid=$this->sismodel->get_listspfic1('employee_master','emp_id','emp_code',$hempcode)->emp_id;
        }
	
?>
<td valign="top">
                <table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Performance Details</b></td>
                            <td colspan="5" align="right">
                            <?php
				 $uname=$this->session->userdata('username');
                                $rest = substr($uname, -21);
	//			if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
//				   if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid))){
				if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid)&&(!(in_array($emp_id, $uoempid))))||(($this->session->userdata('username') == 'ro@tanuvas.org.in') && (in_array($emp_id, $uoempid)))||(($rest == 'office@tanuvas.org.in') && (in_array($emp_id, $hodempid)))){

                                	if(count($performancedata)){
                                    		echo anchor("empmgmt/editextstaffpro/{$emp_id}"," Edit ",array('title' => ' Edit Performance Data' , 'class' => 'red-link'));  
                                	}
                                    	else{
                                    		echo anchor("empmgmt/extstaffpro/{$emp_id}"," Add ",array('title' => ' Add Performance Data' , 'class' => 'red-link'));
                                	}    
				}
                            ?>
                        </td>
                        
                    </tr>
                
		</table>
                <table class="TFtable">
                    
                    <?php if(count($performancedata)):;?>
                    <tr style=" background-color:gray;width:100%;"><td colspan="5"><b>Awards and Medals : </b></td></tr>
                    <tr>
                        <th>Description</th>
                        <th colspan="5"> Number of Medals</th>
                        
                        <tbody>
                            <tr>
                                <td><?php echo "International";?></td>
                                <td colspan="5"><?php echo $performancedata->spd_int_award;?></td>
                            </tr>
                            <tr>
                                <td><?php echo "National";?></td>
                                <td colspan="5"><?php echo $performancedata->spd_nat_award;?></td>
                            </tr> 
                            <tr>
                                <td><?php echo "University";?></td>
                                <td colspan="5"><?php echo $performancedata->spd_uni_award;?></td>
                            </tr>   
                        </tbody>     
                    </tr>
                    <tr style=" background-color:gray;width:100%;"><td colspan="5"><b>Publications : </b></td></tr>
                    <tr>
                        <th>Description</th>
                        <th> National</th>
                        <th colspan="4"> International</th>
                        <tbody>
                            <tr>
                                <td><?php echo "Research";?></td>
                                <td><?php echo $performancedata->spd_res_pub_nat;?></td>
                                <td colspan="3"><?php echo $performancedata->spd_res_pub_int;?></td>
                            </tr>
                            <tr>
                                <td><?php echo "Popular";?></td>
                                <td><?php echo $performancedata->spd_pop_pub_nat;?></td>
                                <td colspan="3"><?php echo $performancedata->spd_pop_pub_int; ?></td>
                            </tr> 
                            <tr>
                                <td><?php echo "Presentation";?></td>
                                <td><?php echo $performancedata->spd_pre_pub_nat; ?></td>
                                <td colspan="3"><?php echo $performancedata->spd_pre_pub_int; ?></td>
                            </tr>   
                        </tbody>     
                    </tr>
                    <tr style=" background-color:gray;width:100%;"><td colspan="5"><b>Project handled : </b></td></tr>
                    <tr>
                        <th>Number of Projects handled</th>
                        <th colspan="4"> Fund outlay</th>
                       
                        <tbody>
                            <tr>
                                <td><?php echo $performancedata->spd_noof_project; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_fund_outly_ofproject; ?></td>
                            </tr>
                           
                        </tbody>     
                    </tr>
                    <tr style=" background-color:gray;width:100%;"><td colspan="5"><b>Training attended (Seminar / Symposium / Workshop etc.) : </b></td></tr>
                    <tr>
                        <th></th>
                        <th colspan="4"> Number of Trainings attended</th>
                       
                        <tbody>
                            <tr>
                                <td><?php echo "National"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_tr_att_nat; ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "International"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_tr_att_int; ?></td>
                            </tr>
                        </tbody>     
                    </tr>
                    <tr style=" background-color:gray;width:100%;"><td colspan="5"><b>Training conducted (Seminar / Symposium / Workshop etc.) : </b></td></tr>
                    <tr>
                        <th></th>
                        <th colspan="4"> Number of Trainings conducted</th>
                       
                        <tbody>
                            <tr>
                                <td><?php echo "National"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_tr_con_nat; ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "International"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_tr_con_int; ?></td>
                            </tr>
                        </tbody>     
                    </tr>
                    <tr style=" background-color:gray;width:100%;"><td colspan="5"><b>Students Guided : </b></td></tr>
                    <tr>
                        <th></th>
                        <th colspan="4"> Number of students guided</th>
                       
                        <tbody>
                            <tr>
                                <td><?php echo "MVSc"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_mvsc_stu_guided; ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "Phd"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_phd_stu_guided; ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "Others"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_others_stu_guided; ?></td>
                            </tr>
                        </tbody>     
                    </tr>
                    <tr style=" background-color:gray;width:100%;"><td colspan="5"><b>Guest lecture delivered : </b></td></tr>
                    <tr>
                        <tbody>
                            <tr>
                                <td><?php echo "Number of Guest lecture delivered"; ?></td>
                                <td colspan="4"><?php echo $performancedata->spd_no_ofguestlecture; ?></td>
                            </tr>
                        </tbody>     
                    </tr>
                    <tr></tr>
                    <tr><td><b>File Name</b></td>
                        <td colspan="4">
			<?php  if (!empty($performancedata->spd_per_filename)){ ?>
                            <a href="<?php echo base_url().'uploads/SIS/perfattachment/'.$performancedata->spd_per_filename ; ?>"
                               target="_blank" type="application/octet-stream" download="<?php echo $performancedata->spd_per_filename ?>">Download the pdf</a>
			<?php } 
				else{
                                echo " No attachment found";
                                }
			?>
                        </td>
                    </tr>
                    <?php else : ?>
                    <td colspan= "7" align="center"> No Records found...!</td>
                    <?php endif;?>
                    
		</table>
		<?php //include 'other_profile_perticulars.php'; ?>
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


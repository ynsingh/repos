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
<?php $current="leave"; ?>
        <script>
      
            function printDiv(printme) {
                var printContents = document.getElementById(printme).innerHTML; 
                var originalContents = document.body.innerHTML;      
                document.body.innerHTML = "<html><head><title></title></head><body><img src='<?php echo base_url(); ?>uploads/logo/logo1.png' alt='logo' align='left' style='width:70%;height:100px;'>"+" <div style='width:70%;height:100%;'> " + printContents + "</div>"+"</body>";
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
 
<tr><td>
<?php
        include  'ptab.php';
?>
</td>
<!--<td valign="top" width=170>

		<?php //include 'profiletab.php'; ?>
	   
</td >-->
<?php     
//	$hdept=$this->sismodel->get_listspfic1('user_role_type','deptid','userid',$this->session->userdata('id_user'))->deptid; 
//	$roleid=$this->session->userdata('id_role');
?>
<td valign="top">		
		<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                        <tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Leave Particulars</b></td>
                            <td align="right">
                                <?php
				 $uname=$this->session->userdata('username');
                                $rest = substr($uname, -21);
     //                           if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
//				if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid)&&(!(in_array($emp_id, $uoempid))))||(($this->session->userdata('username') == 'ro@tanuvas.org.in') && (in_array($emp_id, $uoempid)))||(($rest == 'office@tanuvas.org.in') && (in_array($emp_id, $hodempid)))){
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                                      echo anchor("empmgmt/add_leavepertdata/{$emp_id}"," Add ",array('title' => ' Add Leave Data' , 'class' => 'red-link'));
                                }
                                ?>

                            </td>
                        <tr>
                </table>
                <table class="TFtable" align="center">
                    <thead>
                        <tr>
                            <th>Nature of Leave</th>
                            <th>Max Limit</th>
			    <th> Year </th>
			    <th> From Date -To Date</th>
                            <th>Availed Leave</th>
                            <th>Balance Leave</th>
                            <th>Attachment</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody>

                        <?php if( !empty($leavedata) ):  ?>
                            <?php foreach($leavedata as $record){;
?>
                            <tr>
                                <td>

                                <?php 
//				echo $this->session->userdata('id_user');
				$ltype=$record->la_type;
				echo $this->sismodel->get_listspfic1('leave_type_master','lt_name','lt_id',$ltype)->lt_name;
				//$sc=$this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $record->empsd_campuscode)->sc_name;
                                //"&nbsp;"."(".$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id', $record->empsd_campuscode)->sc_code.")";
                                // if ($record->empsd_ucoid != 0) $uo=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $record->empsd_ucoid)->name;
                                // if ($record->empsd_deptid != 0)$dept=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $record->empsd_deptid)->dept_name;
                                // $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->empsd_schemeid)->sd_name;
                                // $ddo=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$record->empsd_ddoid)->ddo_name;
                               // echo "<b>Campus-: </b>".$sc."<br/> "."<b>UO-: </b>".$uo."<br/> "."<b>Dept-: </b>".$dept."<br/> "."<b>Scheme-: </b>".$schme."</br> "."<b>DDO-: </b>".$ddo;
                                ?>
                                </td>
                                <td>
                                    <?php

					$lmval= $this->sismodel->get_listspfic1('leave_type_master','lt_value','lt_id',$ltype)->lt_value;
					echo $lmval;
//                                    $desig=$this->commodel->get_listspfic1('designation','desig_name','desig_code',$record->empsd_desigcode)->desig_name;
  //                                  $showagpost=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id', $record->empsd_shagpstid)->desig_name;
    //                                $group=$record->empsd_group;
      //                              $worktype=$record->empsd_worktype;
        //                            echo "<b>Designation-: </b>".$desig."<br/> "."<b>Show Again Post-: </b>".$showagpost."<br/> "."<b>Group-: </b>".$group."<br/> "."<b>Worktype-: </b>".$worktype;
                                    ?>
                               </td>
				<td>
					<?php  echo $record->la_year ?>
				</td>
				<td>
<?php					echo $record->granted_la_from_date ." - ". $record->granted_la_to_date; ?>
				<td>
                                    <?php
					$lval= $record->la_taken;
					echo $lval;
/*					$whdata=array('la_userid' =>$emp_id, 'la_type'=>$ltype);
					$lval= $this->sismodel->get_sumofvalue('leave_apply','la_taken',$whdata);					
					foreach($lval as $row){
						$lfval=$row->la_taken;
					}
					echo $lfval;
*/
                              //      $pbname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$record->empsd_pbid)->sgm_name;
                                //    $pbmax=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$record->empsd_pbid)->sgm_max;
                                  //  $pbmin=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$record->empsd_pbid)->sgm_min;
                                    //$pbgp= $this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$record->empsd_pbid)->sgm_gradepay;
//                                    $dateofagp=implode('-', array_reverse(explode('-', $record->empsd_pbdate)));
  //                                  $gradepay=$record->empsd_gradepay;
    //                                $level=$record->empsd_level;
      //                              echo "<b>Pay Band-: </b>".$pbname."(".$pbmin."-".$pbmax.")".$pbgp."<br>"."<b>Grade Pay-: </b>".$gradepay."<br>"."<b>Level-: </b>" .$level."<br>"."<b>Date of AGP-: </b>".$dateofagp; ?>
                                </td>
                                <td>
                                    <?php
					$lfval=0;
					$laid=$record->la_id;
					$whdata=array('la_userid' =>$emp_id, 'la_type'=>$ltype,'la_id <=' =>$laid); 
					$ltval= $this->sismodel->get_sumofvalue('leave_apply','la_taken',$whdata);                                       
                                        foreach($ltval as $row){
                                                $lfval=$row->la_taken;
                                        }

					$bal = $lmval - $lfval;
					echo $bal;
//					echo "<b>From-: </b>".$dojoin."<br>"."<b>To-: </b>".$dorelve;
					?>
                                </td>
				<td>
				<?php
					if(!empty($record->la_upfile)){
				?>
				<a href="<?php echo base_url().'uploads/SIS/empleave/'.$record->la_upfile ; ?>"
                               target="_blank" type="application/octet-stream" download="<?php echo $record->la_upfile ?>">Download the pdf</a>
                        <?php }
                                else{
                                echo " No attachment found";
                                }
                        ?>

				</td>
                                <td>
                                <?php
//                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
				//if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid)&&(!(in_array($emp_id, $uoempid))))||(($this->session->userdata('username') == 'ro@tanuvas.org.in') && (in_array($emp_id, $uoempid)))||(($rest == 'office@tanuvas.org.in') && (in_array($emp_id, $hodempid)))){
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                                             echo anchor("empmgmt/edit_leavepertdata/{$record->la_id}","Edit",array('title' => ' Edit Leave Data' , 'class' => 'red-link'));
						echo " | ";
                                             echo anchor("empmgmt/delete_leavepertdata/{$record->la_id}","Delete",array('title' => ' Delete Leave Data' , 'class' => 'red-link'));
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


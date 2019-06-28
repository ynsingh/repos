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
<?php $current="workorder"; ?>

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
	<?php
        include  'ptab.php';
?>
<!--<td valign="top" width=170>

		<?php //include 'profiletab.php'; ?>
	   
</td>-->
<?php     
//	$hdept=$this->sismodel->get_listspfic1('user_role_type','deptid','userid',$this->session->userdata('id_user'))->deptid; 
//	$roleid=$this->session->userdata('id_role');
//	$roleid=$this->session->userdata('id_role');
//        if($roleid == 5){
  //              $hdept=$this->sismodel->get_listspfic1('user_role_type','deptid','userid',$this->session->userdata('id_user'))->deptid;
    //            $hempcode=$this->sismodel->get_listspfic1('hod_list','hl_empcode','hl_userid',$this->session->userdata('id_user'))->hl_empcode;
      //          $hempid=$this->sismodel->get_listspfic1('employee_master','emp_id','emp_code',$hempcode)->emp_id;
       // }
?>
<td valign="top">		
		<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                        <tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Working Arrangement Particulars</b></td>
                            <td align="right">
                                <?php
					 $uname=$this->session->userdata('username');
                                $rest = substr($uname, -21);
  //                              if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
				
//				if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid))){
//				if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid)&&(!(in_array($emp_id, $uoempid))))||(($this->session->userdata('username') == 'ro@tanuvas.org.in') && (in_array($emp_id, $uoempid)))||(($rest == 'office@tanuvas.org.in') && (in_array($emp_id, $hodempid)))){
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                                      echo anchor("empmgmt/add_workarrangdata/{$emp_id}"," Add ",array('title' => ' Add Working Arrangement Data' , 'class' => 'red-link'));
                                }
                                ?>

                            </td>
                        <tr>
                </table>
                <table class="TFtable" align="center">
                    <thead>
                        <tr>
                            <th>Parent Details</th>
                            <th>Working Details</th>
			    <th>From - To Date </th>
                            <th ></th>
                        </tr>
                    </thead>
                    <tbody>

                        <?php if( !empty($workarrangdata) ):  ?>
                            <?php foreach($workarrangdata as $record){;
?>
                            <tr>
                                <td>

                        <?php
                                $sc=$this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $record->swap_ocampus)->sc_name;
                                "&nbsp;"."(".$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id', $record->swap_ocampus)->sc_code.")";
                                 if ($record->swap_ouo != 0) $uo=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $record->swap_ouo)->name;
                                 if ($record->swap_odept != 0)$dept=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $record->swap_odept)->dept_name;
//                               $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->empsd_schemeid)->sd_name;
//                               $ddo=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$record->empsd_ddoid)->ddo_name; 
                                 echo "<b>Campus-: </b>".$sc."<br/> "."<b>UO-: </b>".$uo."<br/> "."<b>Dept-: </b>".$dept;
                                 //."<br/> "."<b>Scheme-: </b>".$schme."</br> "."<b>DDO-: </b>".$ddo;
                                ?>
                                </td>
                                <td>
                                    <?php 
				$sc1='';$uo1='';$dept1='';
				if($record->swap_wcampus !=0) $sc1=$this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $record->swap_wcampus)->sc_name. "&nbsp;"."(".$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id', $record->swap_wcampus)->sc_code.")";
                                if($record->swap_wuo != 0) $uo1=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $record->swap_wuo)->name;
                                if($record->swap_wdept != 0)$dept1=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $record->swap_wdept)->dept_name;
//                               $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->empsd_schemeid)->sd_name;
//                               $ddo=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$record->empsd_ddoid)->ddo_name; 
                                 echo "<b>Campus-: </b>".$sc1."<br/> "."<b>UO-: </b>".$uo1."<br/> "."<b>Dept-: </b>".$dept1;
                                 //."<br/> "."<b>Scheme-: </b>".$schme."</br> "."<b>DDO-: </b>".$ddo;
                                    ?>
                               </td>
				<td>
<?php					echo $record->swap_fromdate ."&nbsp;&nbsp;".$record->swap_fsession." - ".$record->swap_todate."&nbsp;&nbsp;".$record->swap_tsession ?>
				</td>
                                <td>
                                <?php
                               // if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
//				if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid))){
				//if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid)&&(!(in_array($emp_id, $uoempid))))||(($this->session->userdata('username') == 'ro@tanuvas.org.in') && (in_array($emp_id, $uoempid)))||(($rest == 'office@tanuvas.org.in') && (in_array($emp_id, $hodempid)))){
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
//                                                echo anchor("empmgmt/edit_workarrangdata/{$record->empsd_id}","Edit",array('title' => ' Edit Working Arrangement Data' , 'class' => 'red-link'));
//					echo "<br><br>";
					echo anchor("empmgmt/delete_workorderprofile/{$record->swap_id}", "Delete",array('title' => ' Delete Work Order Data' , 'class' => 'red-link'));
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


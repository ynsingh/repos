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
<?php $current="service"; ?>
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

<?php
//        $roleid=$this->session->userdata('id_role');
  //      if($roleid == 5){
    //            $hdept=$this->sismodel->get_listspfic1('user_role_type','deptid','userid',$this->session->userdata('id_user'))->deptid;
      //          $hempcode=$this->sismodel->get_listspfic1('hod_list','hl_empcode','hl_userid',$this->session->userdata('id_user'))->hl_empcode;
        //        $hempid=$this->sismodel->get_listspfic1('employee_master','emp_id','emp_code',$hempcode)->emp_id;
    //    }
      //  $uname=$this->session->userdata('username');
        //$rest = substr($uname, -21);
    //    if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid)&&(!(in_array($emp_id, $uoempid))))||(($this->session->userdata('username') == 'ro@tanuvas.org.in') && (in_array($emp_id, $uoempid)))||(($rest == 'office@tanuvas.org.in') && (in_array($emp_id, $hodempid)))){

      //          include 'eprofiletab.php'; 
        //        echo "</tr><tr>";

      //  }else{
?>
<!--<td valign="top" width=170>

                <?php //include 'profiletab.php'; ?>

</td>-->
<?php //} ?>

<!-- <td valign="top" width=170>
<?php	
//	if($roleid == 4){
//		include 'empprofiletab.php';
//	}else{
//		include 'profiletab.php'; 
//	}
?>
	   
</td> -->
<?php     
//	$roleid=$this->session->userdata('id_role');
//	if($roleid == 5){
//		$hdept=$this->sismodel->get_listspfic1('user_role_type','deptid','userid',$this->session->userdata('id_user'))->deptid; 
//		$hempcode=$this->sismodel->get_listspfic1('hod_list','hl_empcode','hl_userid',$this->session->userdata('id_user'))->hl_empcode; 
//		$hempid=$this->sismodel->get_listspfic1('employee_master','emp_id','emp_code',$hempcode)->emp_id; 
//	}
?>
<td valign="top">		
		<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Service Particulars</b></td>
                            <td align="right">
                                <?php 
				$uname=$this->session->userdata('username');
				$rest = substr($uname, -21);

/*				$flagffs=false;
                                 $flagcppm=false;
                                 $flagro=false;
                                 $flaguooff =false;
                                 $flaghod=false;
                                if(($this->session->userdata('username') == 'deanffsoffice@tanuvas.org.in')&&(!(in_array($emp_id, $uoempid)))){
                                        $flagffs=true;
                                }
                                if(($this->session->userdata('username') == 'deancppmoffice@tanuvas.org.in')&&(!(in_array($emp_id, $uoempid)))){
                                        $flagcppm=true;
                                }
                                if(($this->session->userdata('username') == 'ro@tanuvas.org.in') && (in_array($emp_id, $uoempid))){
                                        $flagro=true;
                                }
                                if(($rest == 'office@tanuvas.org.in') && (in_array($emp_id, $hodempid))&&(!(in_array($emp_id, $uoempid)))){
                                        $flaguooff =true;
                                }
                                if(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid)&&(!(in_array($emp_id, $uoempid)))){
                                        $flaghod=true;
                                }

  */                              if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){

//                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
//				if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid)&&(!(in_array($emp_id, $uoempid))))||(($this->session->userdata('username') == 'ro@tanuvas.org.in') && (in_array($emp_id, $uoempid)))||(($rest == 'office@tanuvas.org.in') && (in_array($emp_id, $hodempid)))){

					echo anchor("empmgmt/add_servicedata/{$emp_id}"," Add ",array('title' => ' Add Service Data' , 'class' => 'red-link'));
				}
				?>
				
                            </td>  
                        <tr>
		</table>
		<table class="TFtable" align="center">
                    <thead>
                        <tr>
                            <th>Campus Details</th>
                            <th>Designation Details</th>
                            <th>AGP/Grade Pay Details</th>
                            <th>From/To</th>
                            <th colspan="2">Total service (YY/MM/DD)</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <?php if( !empty($servicedata) ):  ?>
                            <?php foreach($servicedata as $record){;
//print_r($record);
//die;
?>
                            <tr>
                                <td>
                                    
                                    <!--?php
                                    $cname=$this->commodel->get_listspfic1('study_center','sc_name','sc_code',$record->empsd_campuscode)->sc_name;
                                    echo $cname."&nbsp;"."(".$record->empsd_campuscode.")";
                                    ?-->
				<?php $sc=$this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $record->empsd_campuscode)->sc_name; 
				"&nbsp;"."(".$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id', $record->empsd_campuscode)->sc_code.")";
				 if ($record->empsd_ucoid != 0) {$uo=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $record->empsd_ucoid)->name; }else{ $uo='';}
                                 if ($record->empsd_deptid != 0) {$dept=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $record->empsd_deptid)->dept_name;}else{$dept='';} 
				  if ( $record->empsd_schemeid != 0) {$schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->empsd_schemeid)->sd_name;}else{ $schme='';}
				  if ( $record->empsd_ddoid != 0) {$ddo=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$record->empsd_ddoid)->ddo_name; }else{ $ddo='';}
				echo "<b>Campus-: </b>".$sc."<br/> "."<b>UO-: </b>".$uo."<br/> "."<b>Dept-: </b>".$dept."<br/> "."<b>Scheme-: </b>".$schme."</br> "."<b>DDO-: </b>".$ddo;
                                ?>
				</td>
                                <td>
                                    <?php 
				    $desig=$this->commodel->get_listspfic1('designation','desig_name','desig_code',$record->empsd_desigcode)->desig_name; 
				    $showagpost=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id', $record->empsd_shagpstid)->desig_name;
				    $group=$record->empsd_group;
				    $worktype=$record->empsd_worktype;
				    echo "<b>Designation-: </b>".$desig."<br/> "."<b>Show Again Post-: </b>".$showagpost."<br/> "."<b>Group-: </b>".$group."<br/> "."<b>Worktype-: </b>".$worktype;
                                    ?>
                               </td>
<td> 
                                    <?php
                                    $pbname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$record->empsd_pbid)->sgm_name; 
                                    $pbmax=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$record->empsd_pbid)->sgm_max;
                                    $pbmin=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$record->empsd_pbid)->sgm_min;
                                    $pbgp= $this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$record->empsd_pbid)->sgm_gradepay;
				    $dateofagp=implode('-', array_reverse(explode('-', $record->empsd_pbdate))); 
				    $gradepay=$record->empsd_gradepay;
                                    $level=$record->empsd_level;
                                    echo "<b>Pay Band-: </b>".$pbname."(".$pbmin."-".$pbmax.")".$pbgp."<br>"."<b>Grade Pay-: </b>".$gradepay."<br>"."<b>Level-: </b>" .$level."<br>"."<b>Date of AGP-: </b>".$dateofagp; ?>
                                </td>
                                <td>
                                    <?php  $dojoin=implode('-', array_reverse(explode('-', $record->empsd_dojoin))); ?>
                                    <?php  $dorelve=implode('-', array_reverse(explode('-', $record->empsd_dorelev))); ?>
				    <?php echo "<b>From-: </b>".$dojoin ." ".$record->empsd_fsession."<br>"."<b>To-: </b>".$dorelve ." " .$record->empsd_tsession;?>
                                </td>
                                <td>
                                    <?php 
                                    $date1 = new DateTime($record->empsd_dojoin);
                                    $date2 = new DateTime($record->empsd_dorelev);
                                    $diff = $date1->diff($date2);
                                    echo "<b>&nbsp;&nbsp;".$diff->y . "&nbsp;&nbsp;&nbsp; " . $diff->m."&nbsp;&nbsp;&nbsp; ".$diff->d." "
                                    ;?>
                                </td>
                                <td>
                                <?php 
//                                if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
				//   if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid))){
			//	if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid)&&(!(in_array($emp_id, $uoempid))))||(($this->session->userdata('username') == 'ro@tanuvas.org.in') && (in_array($emp_id, $uoempid)))||(($rest == 'office@tanuvas.org.in') && (in_array($emp_id, $hodempid)))){
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){

						echo anchor("empmgmt/edit_servicedata/{$record->empsd_id}","Edit",array('title' => ' Edit Service Data' , 'class' => 'red-link'));
						echo "  | ";
						echo anchor("empmgmt/delete_serviceprofile/{$record->empsd_id}","Delete",array('title' => ' Delete Service Data' , 'class' => 'red-link'));
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


<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name viewfull_profile.php
@ author sumit saxena[sumitsesaxena@gmail.com]
 -->
<html>
<title>View Performance Profile</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <style type="text/css" media="print">
            @page {
                size: auto;   /* auto is the initial value */
                margin:0;  /* this affects the margin in the printer settings */
            }
        </style>
	<style>
	.tab {
                overflow: hidden;
                border: 1px solid #ccc;
                //  background-color: #f1f1f1;
                 background-color:#2a8fcf;
        }
	.tab-links li {
                margin:0px 5px;
                margin-left:40px;
                float:left;
                list-style:none;

        }
	.tab-links a {
                background-color: inherit;
                float: left;
                border: none;
                outline: none;
                cursor: pointer;
                padding: 6px 0px;
                transition: 0.3s;
                font-size: 16px;
            }

            .tab-links a:hover {
                //background:#a7cce5;
                text-decoration:none;
            }
	 .tab.active {
                display:block;
            }
            .tabcolor{
                text-decoration:none;
                color:black;
                font-weight:bold;

            }


	.bgli{
                background-color:whitesmoke;
        }
        .bgli2:hover{
                background-color:whitesmoke;
        }
	</style>
	<?php $current="perform"; ?>

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
	<table style="margin-left:0%;border:1px solid gray;width:100%;">
		<div class="tab">
                <ul class="tab-links">

                <?php if($this->uri->segment(3)== 'publication'):?>
                <li class="bgli"><?php echo anchor('report/performance_profile/publication/'.$emp_id, "Publication",array('title' => 'Publication','id'=>'tab1','class' => 'top_parent','class' => 'tabcolor')); ?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('report/performance_profile/publication/'.$emp_id, "Publication",array('title' => 'Publication','id'=>'tab1','class' => 'top_parent')); ?></li>
                <?php endif ?>
                <?php if($this->uri->segment(3)== 'trainingattend'):?>
                <li class="bgli"><?php echo anchor('report/performance_profile/trainingattend/'.$emp_id,"Training Attended",array('title' => 'Training Attended','id'=>'tab2','class' => 'top_parent','class' => 'tabcolor')) ;?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('report/performance_profile/trainingattend/'.$emp_id,"Training Attended",array('title' => 'Training Attended','id'=>'tab2','class' => 'top_parent')) ;?></li>
                <?php endif ?>
                <?php if($this->uri->segment(3)== 'trainingorgna'):?>
                <li class="bgli"><?php echo anchor('report/performance_profile/trainingorgna/'.$emp_id,"Training Oraganised",array('title' => 'Training Oraganised','id'=>'tab3','class' => 'top_parent','class' => 'tabcolor')) ;?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('report/performance_profile/trainingorgna/'.$emp_id,"Training Oraganised",array('title' => 'Training Oraganised','id'=>'tab3','class' => 'top_parent')) ;?></li>
                <?php endif ?>
                <?php if($this->uri->segment(3)== 'awards'):?>
                <li class="bgli"><?php echo anchor('report/performance_profile/awards/'.$emp_id,"Awards",array('title' => 'Awards','id'=>'tab4','class' => 'top_parent','class' => 'tabcolor')) ;?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('report/performance_profile/awards/'.$emp_id,"Awards",array('title' => 'Awards','id'=>'tab4','class' => 'top_parent')) ;?></li>
                <?php endif ?>
                <?php if($this->uri->segment(3)== 'projects'):?>
                <li class="bgli"><?php echo anchor('report/performance_profile/projects/'.$emp_id,"Projects",array('title' => 'Projects','id'=>'tab5','class' => 'top_parent','class' => 'tabcolor')) ;?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('report/performance_profile/projects/'.$emp_id,"Projects",array('title' => 'Projects','id'=>'tab5','class' => 'top_parent')) ;?></li>
                <?php endif ?>
		<?php if($this->uri->segment(3)== 'stuguided'):?>
                <li class="bgli"><?php echo anchor('report/performance_profile/stuguided/'.$emp_id,"Student Guided",array('title' => 'Student Guided','id'=>'tab6','class' => 'top_parent','class' => 'tabcolor')) ;?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('report/performance_profile/stuguided/'.$emp_id,"Student Guided",array('title' => 'Student Guided','id'=>'tab6','class' => 'top_parent')) ;?></li>
                <?php endif ?>
		<?php if($this->uri->segment(3)== 'guestlect'):?>
                <li class="bgli"><?php echo anchor('report/performance_profile/guestlect/'.$emp_id,"Guest Lectures Delivered",array('title' => 'Guest Lectures Delivered','id'=>'tab7','class' => 'top_parent','class' => 'tabcolor')) ;?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('report/performance_profile/guestlect/'.$emp_id,"Guest Lectures Delivered",array('title' => 'Guest Lectures Delivered','id'=>'tab7','class' => 'top_parent')) ;?></li>
                <?php endif ?>
		<?php if($this->uri->segment(3)== 'patents'):?>
                <li class="bgli"><?php echo anchor('report/performance_profile/patents/'.$emp_id,"Patents",array('title' => 'Patents','id'=>'tab8','class' => 'top_parent','class' => 'tabcolor')) ;?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('report/performance_profile/patents/'.$emp_id,"Patents",array('title' => 'Patents','id'=>'tab8','class' => 'top_parent')) ;?></li>
                <?php endif ?>
		<?php if($this->uri->segment(3)== 'abs'):?>
                <li class="bgli"><?php echo anchor('report/performance_profile/abs/'.$emp_id,"Abstract",array('title' => 'Abstract','id'=>'tab9','class' => 'top_parent','class' => 'tabcolor')) ;?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('report/performance_profile/abs/'.$emp_id,"Abstract",array('title' => 'Abstract','id'=>'tab9','class' => 'top_parent')) ;?></li>
                <?php endif ?>

                </ul>
                </div>
	</table>
<br>
</td>
<td valign="top">

        <?php if($this->uri->segment(3)== 'publication'):?>
                <table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Performance Details-Publications</b></td>
                            <td colspan="5" align="right">
                            <?php
				 $uname=$this->session->userdata('username');
                                $rest = substr($uname, -21);
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                                	echo anchor("empmgmt/add_pubdata/{$emp_id}"," Add ",array('title' => ' Add Publication Data' , 'class' => 'red-link'));  
				}
                            ?>
                        </td>
                    </tr>
		</table>
                <table class="TFtable">
                    	<?php 
			$srno=1;
			if(count($emppubdata)):;?>
		    	<tr>
			<th>Sr. No.</th>
			<th>Publication Type/ Title/ Authors/ Author Type</th>
			<th>Journal/Book Name/ Month/ Year/ Venue</th>
			<th>ISSN/ ISBN/ Volume/ Issue/ Page No.</th>
			<th>Journal Metric/ Metric Value/ Pulisher/ Publication Level/ Language</th>
			<th> Available Action</th>
			</tr>
			<?php
//      spbd_chapno     spbd_pageno     spbd_progrmname         spbd_bookname   spbd_bookchapternme //need to be removed from database later
//			spbd_empid 	spbd_pubtype 	spbd_title 	spbd_authors 	spbd_authortype 	spbd_journalname 	spbd_month 	spbd_year 	spbd_issnno 	spbd_chapno 	spbd_pageno 	spbd_metrictype 	spbd_metricvalue 	spbd_publevel 	spbd_progrmname 	spbd_progrmvenue 	spbd_bookname 	spbd_bookchapternme 	spbd_publishername 	spbd_language 	spbd_creatorname 	spbd_creationdate 
                        foreach ($emppubdata as $pubres){
                        echo "<tr>";
                        echo "<td>";  echo $srno++ ; echo "</td>";
                        echo "<td>";
			if($pubres->spbd_pubtype == "Journals" ){
				$pubtype= "Research Papers published in Journals";
			}
			if($pubres->spbd_pubtype == "Conference"){
				$pubtype="Research Papers presented  in seminar/Workshop/conference etc";
			}
			if($pubres->spbd_pubtype == "Book"){
				$pubtype="Book";
			}
			if($pubres->spbd_pubtype == "Chapter"){
				$pubtype="Book Chapter";
			}
			if($pubres->spbd_pubtype == "Articles"){
				$pubtype="Popular Articles";
			}
			if($pubres->spbd_pubtype == "Review"){
				$pubtype="Review Articles";
			}
			if($pubres->spbd_pubtype == "Notes"){
				$pubtype="Research Notes/Research Short Notes";
			}
			if($pubres->spbd_pubtype == "Monograph"){
				$pubtype="Monograph/Manual";
			}
			if($pubres->spbd_authortype == "FA" ){
				$authtype="First Author";
			}
			if($pubres->spbd_authortype == "SA"){
				$authtype="Second Author";
			}
			if($pubres->spbd_authortype == "Others"){
				$authtype="Others";
			}
                        echo $pubtype." <br>".$pubres->spbd_title."".$pubres->spbd_bookchapternme."<br>".$pubres->spbd_authors."<br>".$authtype;
                        echo "</td>";
                        echo "<td>";
                        echo $pubres->spbd_journalname."" .$pubres->spbd_bookname."".$pubres->spbd_progrmname ."<br>".$pubres->spbd_month." - ".$pubres->spbd_year."<br>".$pubres->spbd_progrmvenue;
                        echo "</td>";
                        echo "<td>";
                        echo $pubres->spbd_issnno."<br> ".$pubres->spbd_chapno."<br>".$pubres->spbd_pageno;
                        echo "</td>";
                        echo "<td>";
                        echo $pubres->spbd_metrictype." ".$pubres->spbd_metricvalue." ". $pubres->spbd_publishername ."<br>".$pubres->spbd_publevel."<br>".$pubres->spbd_language;
                        echo "</td>";
                        echo "<td>";
                        if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                        //      echo anchor("empmgmt/edit_stadata/{$pubres->spbd_id}","Edit",array('title' => ' Edit Publication  Data' , 'class' => 'red-link'));
                                echo " <br><br> ";
                                echo anchor("empmgmt/delete_pubdata/{$pubres->spbd_id}","Delete",array('title' => ' Delete Publication Data' , 'class' => 'red-link'));
                        }
                        echo "</td>";
                        echo "</tr>";
                        }
                        else : ?>
                        <tr><td colspan= "6" align="center"> No Records found...!</td></tr>
                        <?php endif;?>
		</table>
        <?php endif ?>
        <?php if($this->uri->segment(3)== 'trainingattend'):?>
                <table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Performance Details-Training Attended</b></td>
                            <td colspan="5" align="right">
                            <?php
				$uname=$this->session->userdata('username');
                                $rest = substr($uname, -21);
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                              		echo anchor("empmgmt/add_stadata/{$emp_id}"," Add ",array('title' => ' Add Staff Training Attended Data' , 'class' => 'red-link'));
				}
                            ?>
                        </td>
                    </tr>
		</table>
                <table class="TFtable">
                    	<?php 
			$srno=1;
			if(count($empstadata)):;?>
		    	<tr>
			<th>Sr.NO.</th>
			<th>Program Type/Level/Title</th>
			<th>Program Duration/Venue</th>
			<th>Program Organised/Sponsored By</th>
			<th> Available Actions </th> 
			</tr>
			<?php 
			foreach ($empstadata as $stares){
		    	echo "<tr>";
			echo "<td>";  echo $srno++ ; echo "</td>";
			echo "<td>";
			echo $stares->sta_prgtype." ".$stares->sta_prgsubtype."<br>".$stares->sta_prglevel."<br>".$stares->sta_prgtitle;
			echo "</td>";
			echo "<td>";
 			echo $stares->sta_prgduration ." Days<br>".$stares->sta_prgfrmdate." - ".$stares->sta_prgtodate."<br>".$stares->sta_prgvenue;
			echo "</td>";
			echo "<td>";
 			echo $stares->sta_prgorganisedby."<br>".$stares->sta_sponceredby ;
			echo "</td>";
			echo "<td>";
			if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                        //	echo anchor("empmgmt/edit_stadata/{$stares->sta_id}","Edit",array('title' => ' Edit STA  Data' , 'class' => 'red-link'));
                                echo " <br><br> ";
                                echo anchor("empmgmt/delete_stadata/{$stares->sta_id}","Delete",array('title' => ' Delete STA Data' , 'class' => 'red-link'));
                        }
			echo "</td>";
			echo "</tr>";
			}
                    	else : ?>
                    	<tr><td colspan= "5" align="center"> No Records found...!</td></tr>
                    	<?php endif;?>
		</table>
        <?php endif ?>
        <?php if($this->uri->segment(3)== 'trainingorgna'):?>
                <table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Performance Details-Training Organised</b></td>
                            <td colspan="5" align="right">
                            <?php
				$uname=$this->session->userdata('username');
                                $rest = substr($uname, -21);
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                                	echo anchor("empmgmt/add_stodata/{$emp_id}"," Add ",array('title' => ' Add Staff Training Organised Data' , 'class' => 'red-link'));
				}
                            ?>
                        </td>
                    </tr>
		</table>
                <table class="TFtable">
                    	<?php 
			$srno=1;
			if(count($empstodata)):;?>
		    	<tr>
			<th>Sr.NO.</th>
			<th>Program Type/Level/Title</th>
			<th>Program Duration/Venue</th>
			<th>Program Capacity/Participant</th>
			<th>Program Organised/Sponsored By</th>
			<th> Available Actions </th> 
			</tr>
			<?php 
			foreach ($empstodata as $stores){
		    	echo "<tr>";
			echo "<td>";  echo $srno++ ; echo "</td>";
			echo "<td>";
			echo $stores->sto_prgtype." ".$stores->sto_prgsubtype."<br>".$stores->sto_prglevel."<br>".$stores->sto_prgtitle;
			echo "</td>";
			echo "<td>";
 			echo $stores->sto_prgduration ." Days<br>".$stores->sto_prgfrmdate." - ".$stores->sto_prgtodate."<br>".$stores->sto_prgvenue;
			echo "</td>";
			echo "<td>";
			echo $stores->sto_capacity."<br>".$stores->sto_participantno."<br>".$stores->sto_participantnature;
			echo "</td>";
			echo "<td>";
 			echo $stores->sto_prgorganisedby."<br>".$stores->sto_sponceredby ;
			echo "</td>";
			echo "<td>";
			if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                        //	echo anchor("empmgmt/edit_stodata/{$stores->sto_id}","Edit",array('title' => ' Edit STO  Data' , 'class' => 'red-link'));
                                echo " <br><br> ";
                                echo anchor("empmgmt/delete_stodata/{$stores->sto_id}","Delete",array('title' => ' Delete STO Data' , 'class' => 'red-link'));
                        }
			echo "</td>";
			echo "</tr>";
			}
                    	else : ?>
                    	<tr><td colspan= "6" align="center"> No Records found...!</td></tr>
                    	<?php endif;?>
		</table>
        <?php endif ?>
        <?php if($this->uri->segment(3)== 'awards'):?>
                <table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Performance Details-Awards</b></td>
                            <td colspan="5" align="right">
                            <?php
				 $uname=$this->session->userdata('username');
                                $rest = substr($uname, -21);
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                                	echo anchor("empmgmt/add_awarddata/{$emp_id}"," Add ",array('title' => ' Add Award Data' , 'class' => 'red-link'));
				}
                            ?>
                        </td>
                    </tr>
		</table>
                <table class="TFtable">
			<?php $srno=1;
                    	 if(count($empawarddata)):;?>
		    	<tr>
			<th>Sr.NO.</th>
			<th>Award Title/Type</th>
			<th>Awarded By/Year</th>
			<th>Remark</th>
			<th>Available Actions </th> 
			</tr>
			<?php
                        foreach ($empawarddata as $awardres){
                        echo "<tr>";
                        echo "<td>";  echo $srno++ ; echo "</td>";
                        echo "<td>";
                        echo $awardres->spad_awardtitle."<br>".$awardres->spad_awardtype;
                        echo "</td>";
                        echo "<td>";
                        echo $awardres->spad_awardby ."<br>".$awardres->spad_year;
                        echo "</td>";
                        echo "<td>";
                        echo $awardres->spad_details ;
                        echo "</td>";
                        echo "<td>";
                        if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                        //      echo anchor("empmgmt/edit_stodata/{$stores->sto_id}","Edit",array('title' => ' Edit STO  Data' , 'class' => 'red-link'));
                                echo " <br><br> ";
                                echo anchor("empmgmt/delete_awarddata/{$awardres->spad_id}","Delete",array('title' => ' Delete Award Data' , 'class' => 'red-link'));
                        }
                        echo "</td>";
                        echo "</tr>";
                        }
                        else : ?>
                        <tr><td colspan= "6" align="center"> No Records found...!</td></tr>
                        <?php endif;?>

		</table>
        <?php endif ?>
        <?php if($this->uri->segment(3)== 'projects'):?>
                <table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Performance Details-Projects</b></td>
                            <td colspan="5" align="right">
                            <?php
				 $uname=$this->session->userdata('username');
                                $rest = substr($uname, -21);
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                                    	echo anchor("empmgmt/add_projdata/{$emp_id}"," Add ",array('title' => ' Add Project Data' , 'class' => 'red-link'));
				}
                            ?>
                        </td>
                    </tr>
		</table>
                <table class="TFtable">
			<?php $srno=1;
                    	 if(count($empprojdata)):;?>
		    	<tr>
			<th>Sr.NO.</th>
			<th>Project Title/Role</th>
			<th>Funding Agency Name /Type</th>
			<th>Budget</th>
			<th>Duration / From - To</th>
			<th>Remarks</th>
			<th>Available Actions </th> 
			<th></th>
			</tr>
			<?php
                        foreach ($empprojdata as $projres){
                        echo "<tr>";
                        echo "<td>";  echo $srno++ ; echo "</td>";
                        echo "<td>";
                        echo $projres->sppd_ptitle."<br>".$projres->sppd_prole;
                        echo "</td>";
                        echo "<td>";
                        echo $projres->sppd_pfundagency ."<br>".$projres->sppd_agendytype;
                        echo "</td>";
                        echo "<td>";
                        echo $projres->sppd_budget ." L <br>";
                        echo "</td>";
                        echo "<td>";
                        echo $projres->sppd_duration ." Years <br>".$projres->sppd_fromdate." - ".$projres->sppd_todate;
                        echo "</td>";
                        echo "<td>";
                        echo $projres->sppd_remark;
                        echo "</td>";
                        echo "<td>";
                        if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                        //      echo anchor("empmgmt/edit_stodata/{$stores->sto_id}","Edit",array('title' => ' Edit STO  Data' , 'class' => 'red-link'));
                                echo " <br><br> ";
                                echo anchor("empmgmt/delete_projdata/{$projres->sppd_id}","Delete",array('title' => ' Delete Project Data' , 'class' => 'red-link'));
                        }
                        echo "</td>";
                        echo "</tr>";
                        }

                    	else : ?>
                    	<tr><td colspan= "7" align="center"> No Records found...!</td></tr>
                    	<?php endif;?>
		</table>
        <?php endif ?>
	<?php if($this->uri->segment(3)== 'stuguided'):?>
                <table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Performance Details-Student Guided</b></td>
                            <td colspan="5" align="right">
                            <?php
				 $uname=$this->session->userdata('username');
                                $rest = substr($uname, -21);
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                                	if(count($performancedata)){
  //                                  		echo anchor("empmgmt/editextstaffpro/{$emp_id}"," Edit ",array('title' => ' Edit Performance Data' , 'class' => 'red-link'));  
                                	}
                                    	else{
//                                    		echo anchor("empmgmt/extstaffpro/{$emp_id}"," Add ",array('title' => ' Add Performance Data' , 'class' => 'red-link'));
                                	}    
				}
                            ?>
                        </td>
                    </tr>
		</table>
                <table class="TFtable">
                    	<?php if(count($emppubdata)):;?>
		    	<tr>
			<th></th>
			<th></th>
			</tr>
		    	<tr>
			<td></td>
			<td></td>
			</tr>
			
                    	<?php else : ?>
                    	<tr><td colspan= "7" align="center"> No Records found...!</td></tr>
                    	<?php endif;?>
		</table>
        <?php endif ?>
	<?php if($this->uri->segment(3)== 'guestlect'):?>
                <table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Performance Details-Guest Lecture Delivered</b></td>
                            <td colspan="5" align="right">
                            <?php
				 $uname=$this->session->userdata('username');
                                $rest = substr($uname, -21);
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                                	if(count($performancedata)){
    //                                		echo anchor("empmgmt/editextstaffpro/{$emp_id}"," Edit ",array('title' => ' Edit Performance Data' , 'class' => 'red-link'));  
                                	}
                                    	else{
      //                              		echo anchor("empmgmt/extstaffpro/{$emp_id}"," Add ",array('title' => ' Add Performance Data' , 'class' => 'red-link'));
                                	}    
				}
                            ?>
                        </td>
                    </tr>
		</table>
                <table class="TFtable">
                    	<?php if(count($emppubdata)):;?>
		    	<tr>
			<th></th>
			<th></th>
			</tr>
		    	<tr>
			<td></td>
			<td></td>
			</tr>
			
                    	<?php else : ?>
                    	<tr><td colspan= "7" align="center"> No Records found...!</td></tr>
                    	<?php endif;?>
		</table>
        <?php endif ?>
	<?php if($this->uri->segment(3)== 'patents'):?>
                <table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Performance Details-Patents</b></td>
                            <td colspan="5" align="right">
                            <?php
				 $uname=$this->session->userdata('username');
                                $rest = substr($uname, -21);
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                                	if(count($performancedata)){
//                                    		echo anchor("empmgmt/editextstaffpro/{$emp_id}"," Edit ",array('title' => ' Edit Performance Data' , 'class' => 'red-link'));  
                                	}
                                    	else{
  //                                  		echo anchor("empmgmt/extstaffpro/{$emp_id}"," Add ",array('title' => ' Add Performance Data' , 'class' => 'red-link'));
                                	}    
				}
                            ?>
                        </td>
                    </tr>
		</table>
                <table class="TFtable">
                    	<?php if(count($emppubdata)):;?>
		    	<tr>
			<th></th>
			<th></th>
			</tr>
		    	<tr>
			<td></td>
			<td></td>
			</tr>
			
                    	<?php else : ?>
                    	<tr><td colspan= "7" align="center"> No Records found...!</td></tr>
                    	<?php endif;?>
		</table>
        <?php endif ?>
	<?php if($this->uri->segment(3)== 'abs'):?>
                <table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Performance Details-Abstract</b></td>
                            <td colspan="5" align="right">
                            <?php
				 $uname=$this->session->userdata('username');
                                $rest = substr($uname, -21);
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
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
                        <th > Number of Awards</th>
                        <th colspan="4"> Number of Medals</th>
                        
                        <tbody>
                            <tr>
                                <td><?php echo "International";?></td>
                                <td ><?php echo $performancedata->spd_int_award;?></td>
                                <td colspan="5"><?php echo $performancedata->spd_int_medal;?></td>
                            </tr>
                            <tr>
                                <td><?php echo "National";?></td>
                                <td ><?php echo $performancedata->spd_nat_award;?></td>
                                <td colspan="5"><?php echo $performancedata->spd_nat_medal;?></td>
                            </tr> 
                            <tr>
                                <td><?php echo "University";?></td>
                                <td ><?php echo $performancedata->spd_uni_award;?></td>
                                <td colspan="5"><?php echo $performancedata->spd_uni_medal;?></td>
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
	<?php endif ?>

	<br>
</td>
</tr>
</table>
       
   </div>      
 <p> &nbsp; </p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>
</html>


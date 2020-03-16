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
                <li class="bgli"><?php echo anchor('report/performance_profile/trainingorgna/'.$emp_id,"Training Organised",array('title' => 'Training Organised','id'=>'tab3','class' => 'top_parent','class' => 'tabcolor')) ;?></li>
                <?php else :?>
                <li class="bgli2"><?php echo anchor('report/performance_profile/trainingorgna/'.$emp_id,"Training Organised",array('title' => 'Training Organised','id'=>'tab3','class' => 'top_parent')) ;?></li>
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
			if(count($emppubdata)): ;?>
		    	<tr>
			<th>Sr. No.</th>
			<th>Publication Type<br> Title/Chapter Name<br> Authors<br> Author Type</th>
			<th>Journal Name<br>Book Name<br> Month - Year<br> Venue</th>
			<th>ISSN/ ISBN/ Volume/ Issue<br> Page No.</th>
			<th>Journal Metric/ Metric Value/<br> Pulisher/ Publication Level<br> Language</th>
			<th> Available Action</th>
			</tr>
			<?php
//      spbd_chapno     spbd_pageno     spbd_progrmname         spbd_bookname   spbd_bookchapternme //need to be removed from database later
//			spbd_empid 	spbd_pubtype 	spbd_title 	spbd_authors 	spbd_authortype 	spbd_journalname 	spbd_month 	spbd_year 	spbd_issnno 	spbd_chapno 	spbd_pageno 	spbd_metrictype 	spbd_metricvalue 	spbd_publevel 	spbd_progrmname 	spbd_progrmvenue 	spbd_bookname 	spbd_bookchapternme 	spbd_publishername 	spbd_language 	spbd_creatorname 	spbd_creationdate 
                        foreach ($emppubdata as $pubres){
                        echo "<tr>";
                        echo "<td>";  echo $srno++ ; echo "</td>";
                        echo "<td>";
			if($pubres->spbd_pubtype == "CJournals" ){
				$pubtype= "Research Papers published in Journals";
			}
			if($pubres->spbd_pubtype == "DConference"){
				$pubtype="Research Papers presented  in seminar/Workshop/conference etc";
			}
			if($pubres->spbd_pubtype == "ABook"){
				$pubtype="Book";
			}
			if($pubres->spbd_pubtype == "BChapter"){
				$pubtype="Book Chapter";
			}
			if($pubres->spbd_pubtype == "EArticles"){
				$pubtype="Popular Articles";
			}
			if($pubres->spbd_pubtype == "FReview"){
				$pubtype="Review Articles";
			}
			if($pubres->spbd_pubtype == "GNotes"){
				$pubtype="Research Notes/Research Short Notes";
			}
			if($pubres->spbd_pubtype == "HMonograph"){
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
                        echo $pubres->spbd_metrictype." ".$pubres->spbd_metricvalue."<br> ". $pubres->spbd_publishername ." - ".$pubres->spbd_publevel."<br>".$pubres->spbd_language;
                        echo "</td>";
                        echo "<td>";
                        if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                                echo anchor("empmgmt/edit_pubdata/{$pubres->spbd_id}","Edit",array('title' => ' Edit Publication  Data' , 'class' => 'red-link'));
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
			<th>Program Type<br>Level<br>Title</th>
			<th>Program Duration<br>From Date - To Date<br>Venue</th>
			<th>Program Organised<br>Sponsored By</th>
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
                        	echo anchor("empmgmt/update_stadata/{$stares->sta_id}","Edit",array('title' => ' Edit STA  Data' , 'class' => 'red-link'));
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
			<th>Program Type<br>Level<br>Title</th>
			<th>Program Duration<br>From Date - To Date<br>Venue</th>
			<th>Program Capacity<br>Participant<br>Nature</th>
			<th>Program Organised<br>Sponsored By</th>
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
                        	echo anchor("empmgmt/update_stodata/{$stores->sto_id}","Edit",array('title' => ' Edit STO  Data' , 'class' => 'red-link'));
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
			<th>Award Title<br>Type</th>
			<th>Awarded By<br>Year</th>
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
                              echo anchor("empmgmt/update_awarddata/{$awardres->spad_id}","Edit",array('title' => ' Edit Award  Data' , 'class' => 'red-link'));
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
			<th>Project Title<br>Role</th>
			<th>Funding Agency  <br>Type</th>
			<th>Budget (in Lakhs)</th>
			<th>Duration <br> From - To</th>
			<th>Remarks</th>
			<th> Actions </th> 
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
                        echo $projres->sppd_budget ."  <br>";
                        echo "</td>";
                        echo "<td>";
                        echo $projres->sppd_duration ." Years <br>".$projres->sppd_fromdate." - ".$projres->sppd_todate;
                        echo "</td>";
                        echo "<td>";
                        echo $projres->sppd_remark;
                        echo "</td>";
                        echo "<td>";
                        if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                              	echo anchor("empmgmt/update_projdata/{$projres->sppd_id}","Edit",array('title' => ' Edit Project  Data' , 'class' => 'red-link'));
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
                                	echo anchor("empmgmt/add_stuguidata/{$emp_id}","Add",array('title' => 'Add Student Guide Data','class'=>'red-link'));
				}
                            ?>
                        </td>
                    </tr>
		</table>
                <table class="TFtable">
                    	<?php if(count($empstuguidata)):?>
		    	<tr>
			<th>Sr.NO.</th>
			<th>Student Name<br>College/University</th>
			<th>Degree-Year<br>Discipline</th>
			<th>Role</th>
			<th>Actions</th>
			</tr>
			<?php
			$srno=1;
			//print_r($empstuguidata);
                        foreach ($empstuguidata as $stuguires){
                        echo "<tr>";
                        echo "<td>";  echo $srno++ ; echo "</td>";
                        echo "<td>";
			echo $stuguires->spsgd_sname."<br>".$stuguires->spsgd_scollege;
			echo "</td>";
			echo "<td>";
			echo $stuguires->spsgd_sdegree." - ".$stuguires->spsgd_syear."<br>".$stuguires->spsgd_sdiscipine; 
			echo "</td>";
			echo "<td>";
			echo $stuguires->spsgd_role;
			echo "</td>";
			echo "<td>";

                        if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                              echo anchor("empmgmt/update_stuguidata/{$stuguires->spsgd_id}","Edit",array('title' => ' Edit Student Guided  Data' , 'class' => 'red-link'));
                                echo " <br><br> ";
                                echo anchor("empmgmt/delete_stuguidata/{$stuguires->spsgd_id}","Delete",array('title' => ' Delete Student Guided Data' , 'class' => 'red-link'));
                        }
			echo "</td>";
			echo "</tr>";
		}	
                    	 else : ?>
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
                                    		echo anchor("empmgmt/add_guestlectdata/{$emp_id}"," Add ",array('title' => ' Add Guest lecture Data' , 'class' => 'red-link'));
				}
                            ?>
                        </td>
                    </tr>
		</table>
                <table class="TFtable">
                    	<?php if(count($empguestlectdata)):;?>
		    	<tr>
			<th>Sr.NO.</th>
			<th>Guest Lecture Title</th>
			<th>Month - Year</th>
			<th>Delivered Details</th>
			<th>Actions</th>
			</tr>
			<?php
                        $srno=1;
                        //print_r($empguestlectdata);
                        foreach ($empguestlectdata as $guestlectres){
                        echo "<tr>";
                        echo "<td>";  echo $srno++ ; echo "</td>";
                        echo "<td>";
			echo $guestlectres->spgld_gtitle;
			echo "</td>";
                        echo "<td>";
			echo $guestlectres->spgld_month ." - ".$guestlectres->spgld_year;
			echo "</td>";
                        echo "<td>";
			echo $guestlectres->spgld_details;
			echo "</td>";
                        echo "<td>";

                        if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                                echo anchor("empmgmt/update_guestlectdata/{$guestlectres->spgld_id}","Edit",array('title' => ' Edit Guest Lecture  Data' , 'class' => 'red-link'));
                                echo " <br><br> ";
                                echo anchor("empmgmt/delete_guestlectdata/{$guestlectres->spgld_id}","Delete",array('title' => ' Delete Guest Lecture Data' , 'class' => 'red-link'));
                        }
                        echo "</td>";
                        echo "</tr>";
                	}
                    	 else : ?>
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
	<?php if($this->uri->segment(3)== 'abs'): ?>
                <table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Performance Details-Abstract</b></td>
                            <td colspan="5" align="right">
                            <?php
				 $uname=$this->session->userdata('username');
                                $rest = substr($uname, -21);
//				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
  //                              	if(count($performancedata)){
                                    //		echo anchor("empmgmt/editextstaffpro/{$emp_id}"," Edit ",array('title' => ' Edit Performance Data' , 'class' => 'red-link'));  
      ///                          	}
    //                                	else{
                                    //		echo anchor("empmgmt/extstaffpro/{$emp_id}"," Add ",array('title' => ' Add Performance Data' , 'class' => 'red-link'));
         //                       	}    
		//		}
                            ?>
                        </td>
                    </tr>
		</table>

                <table class="TFtable">
                    <?php
                    
                    
                   // if(count($performancedata)):
                   // if(count($empawarddata)): ;?>
                                     
                    <tr style=" background-color:gray;width:100%;"><td colspan="6"><b>Publications : </b></td></tr>
                    <tr>
                        <th>Description</th>
                        <th> International</th>
                        <th> National</th>
                        <th> State</th>
                        <th colspan="6"> University</th>
                        <tbody>
                            <tr>
                                <td><?php echo "Book";?></td>
                                <td><?php 
                                    $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'International','spbd_pubtype'=>'ABook');    
                                    $intbook=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $intbook;
                                ?></td>
                                <td ><?php  
                                    $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'National','spbd_pubtype'=>'ABook');    
                                    $ntbook=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $ntbook;
                                ?></td>
                                <td ><?php 
                                    $whdata1 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'State','spbd_pubtype'=>'ABook');    
                                    $stbook=$this->sismodel->getcountabstract('staff_pub_data',$whdata1);
                                    echo $stbook;
                                
                                ?></td>
                                <td colspan="6"><?php 
                                    $whdata2 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'University','spbd_pubtype'=>'ABook');    
                                    $unibook=$this->sismodel->getcountabstract('staff_pub_data',$whdata2);
                                    //print_r($unibook);
                                    echo $unibook;
                                ?></td>
                               
                            </tr>
                            <tr>
                                <td><?php echo "Book Chapter";?></td>
                                <td><?php 
                                    $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'International','spbd_pubtype'=>'BChapter');    
                                    $intbchpter=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $intbook;
                                ?></td>
                                <td ><?php 
                                    $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'National','spbd_pubtype'=>'BChapter');    
                                    $ntbchpter=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $ntbchpter;
                                ?></td>
                                <td ><?php 
                                    $whdata1 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'State','spbd_pubtype'=>'BChapter');    
                                    $stbchpter=$this->sismodel->getcountabstract('staff_pub_data',$whdata1);
                                    echo $stbchpter;
                                ?></td>
                                <td colspan="6"><?php 
                                    $whdata2 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'University','spbd_pubtype'=>'BChapter');    
                                    $unibchpter=$this->sismodel->getcountabstract('staff_pub_data',$whdata2);
                                    //print_r($unibook);
                                    echo $unibchpter;
                                ?></td>
                                 
                            </tr> 
                            <tr>
                                <td><?php echo "Reaserch Papers Published in Journals";?></td>
                                <td><?php 
                                     $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'International','spbd_pubtype'=>'CJournals');    
                                    $intjournl=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $intjournl;
                                ?></td>
                                <td ><?php 
                                    $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'National','spbd_pubtype'=>'CJournals');    
                                    $ntjournl=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $ntjournl;
                                ?></td>
                                <td ><?php 
                                    $whdata1 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'State','spbd_pubtype'=>'CJournals');    
                                    $stjournl=$this->sismodel->getcountabstract('staff_pub_data',$whdata1);
                                    echo $stjournl; 
                                ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'University','spbd_pubtype'=>'CJournals');    
                                    $unijournl=$this->sismodel->getcountabstract('staff_pub_data',$whdata1);
                                    echo $unijournl;
                                ?></td>
                            </tr> 
                            <tr>
                                <td><?php echo "Reaserch Papers presented in Senimar/workshop/conference";?></td>
                                <td><?php 
                                    $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'International','spbd_pubtype'=>'DConference');    
                                    $intconfrn=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $intconfrn;
                                ?></td>
                                <td ><?php 
                                    $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'National','spbd_pubtype'=>'DConference');    
                                    $ntconfrn=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $ntconfrn;
                                ?></td>
                                <td ><?php 
                                    $whdata1 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'State','spbd_pubtype'=>'DConference');    
                                    $stconfrn=$this->sismodel->getcountabstract('staff_pub_data',$whdata1);
                                    echo $stconfrn; 
                                ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'University','spbd_pubtype'=>'DConference');    
                                    $uniconfrn=$this->sismodel->getcountabstract('staff_pub_data',$whdata1);
                                    echo $uniconfrn;
                                ?></td>
                                 
                            </tr>
                            <tr>
                                <td><?php echo "Popular Articles";?></td>
                                <td><?php 
                                    $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'International','spbd_pubtype'=>'EArticles');    
                                    $intarticl=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $intarticl;
                                ?></td>
                                <td ><?php 
                                    $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'National','spbd_pubtype'=>'EArticles');    
                                    $ntarticl=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $ntarticl;
                                ?></td>
                                <td ><?php 
                                    $whdata1 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'State','spbd_pubtype'=>'EArticles');    
                                    $starticl=$this->sismodel->getcountabstract('staff_pub_data',$whdata1);
                                    echo $starticl; 
                                ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'University','spbd_pubtype'=>'EArticles');    
                                    $uniarticl=$this->sismodel->getcountabstract('staff_pub_data',$whdata1);
                                    echo $uniarticl;
                                ?></td>
                               
                            </tr>
                            <tr>
                                <td><?php echo "Review Articles";?></td>
                                <td><?php 
                                    $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'International','spbd_pubtype'=>'FReview');    
                                    $intreviw=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $intreviw;
                                ?></td>
                                <td ><?php 
                                    $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'National','spbd_pubtype'=>'FReview');    
                                    $ntreviw=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $ntreviw;
                                ?></td>
                                <td ><?php 
                                    $whdata1 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'State','spbd_pubtype'=>'FReview');    
                                    $streviw=$this->sismodel->getcountabstract('staff_pub_data',$whdata1);
                                    echo $streviw; 
                                ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'University','spbd_pubtype'=>'FReview');    
                                    $unireviw=$this->sismodel->getcountabstract('staff_pub_data',$whdata1);
                                    echo $unireviw;
                                ?></td>  
                            </tr>
                            <tr>
                                <td><?php echo "Research Notes/ Reasearch Short Notes";?></td>
                                <td><?php 
                                    $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'International','spbd_pubtype'=>'GNotes');    
                                    $intnotes=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $intnotes;
                                ?></td>
                                <td ><?php 
                                    $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'National','spbd_pubtype'=>'GNotes');    
                                    $ntnotes=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $ntnotes;
                                ?></td>
                                <td ><?php 
                                    $whdata1 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'State','spbd_pubtype'=>'GNotes');    
                                    $stnotes=$this->sismodel->getcountabstract('staff_pub_data',$whdata1);
                                    echo $stnotes; 
                                ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'University','spbd_pubtype'=>'GNotes');    
                                    $uninotes=$this->sismodel->getcountabstract('staff_pub_data',$whdata1);
                                    echo $uninotes;
                                ?></td>  
                            </tr>
                            <tr>
                                <td><?php echo "Monograph/Manual";?></td>
                                <td><?php 
                                    $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'International','spbd_pubtype'=>'HMonograph');    
                                    $intmmgrp=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $intmmgrp;
                                ?></td>
                                <td ><?php 
                                    $whdata = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'National','spbd_pubtype'=>'HMonograph');    
                                    $ntmmgrp=$this->sismodel->getcountabstract('staff_pub_data',$whdata);
                                    echo $ntmmgrp;
                                ?></td>
                                <td ><?php 
                                    $whdata1 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'State','spbd_pubtype'=>'HMonograph');    
                                    $stmmgrp=$this->sismodel->getcountabstract('staff_pub_data',$whdata1);
                                    echo $stmmgrp; 
                                ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spbd_empid' =>$emp_id,'spbd_publevel'=>'University','spbd_pubtype'=>'HMonograph');    
                                    $unimmgrp=$this->sismodel->getcountabstract('staff_pub_data',$whdata1);
                                    echo $unimmgrp;
                                ?></td>
                            </tr>
                        </tbody>     
                    </tr>
                                       
                    <tr style=" background-color:gray;width:100%;"><td colspan="6"><b>Training attended (Number of Trainings attended) : </b></td></tr>
                    <tr>
                        <th>Description</th>
                        <th> International</th>
                        <th> National</th>
                        <th> State</th>
                        <th> University</th> 
                        <th> Others</th> 
                        <tbody>
                            <tr>
                                <td><?php echo "Training"; ?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'International','sta_prgtype'=>'Training');    
                                    $inttrain=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $inttrain;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'National','sta_prgtype'=>'Training');    
                                    $nttrain=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $nttrain;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'State','sta_prgtype'=>'Training');    
                                    $stattrain=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $stattrain;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'University','sta_prgtype'=>'Training');    
                                    $unitrain=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $unitrain;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'Others','sta_prgtype'=>'Training');    
                                    $othtrain=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $othtrain;
                                ;?></td>
                                
                            </tr>
                            <tr>
                                <td><?php echo "Symposium"; ?></td>
                                 <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'International','sta_prgtype'=>'Symposium');    
                                    $intsysm=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $intsysm;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'National','sta_prgtype'=>'Symposium');    
                                    $ntsysm=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $ntsysm;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'State','sta_prgtype'=>'Symposium');    
                                    $statsysm=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $statsysm;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'University','sta_prgtype'=>'Symposium');    
                                    $unisysm=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $unisysm;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'Others','sta_prgtype'=>'Symposium');    
                                    $othsysm=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $othsysm;
                                ;?></td>
                               
                            </tr>
                            <tr>
                               <td><?php echo "Conference"; ?></td>
                               <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'International','sta_prgtype'=>'Conference');    
                                    $intconf=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $intconf;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'National','sta_prgtype'=>'Conference');    
                                    $ntconf=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $ntconf;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'State','sta_prgtype'=>'Conference');    
                                    $statconf=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $statconf;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'University','sta_prgtype'=>'Conference');    
                                    $uniconf=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $uniconf;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'Others','sta_prgtype'=>'Conference');    
                                    $othconf=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $othconf;
                                ;?></td>
                          
                            </tr>
                            <tr>
                                <td><?php echo "Seminar"; ?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'International','sta_prgtype'=>'Seminar');    
                                    $intsem=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $intsem;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'National','sta_prgtype'=>'Seminar');    
                                    $ntsem=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $ntsem;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'State','sta_prgtype'=>'Seminar');    
                                    $statsem=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $statsem;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'University','sta_prgtype'=>'Seminar');    
                                    $unisem=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $unisem;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'Others','sta_prgtype'=>'Seminar');    
                                    $othsem=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $othsem;
                                ;?></td>
                                
                            </tr>
                            <tr>
                                <td><?php echo "Workshop"; ?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'International','sta_prgtype'=>'Workshop');    
                                    $intwor=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $intwor;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'National','sta_prgtype'=>'Workshop');    
                                    $ntwor=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $ntwor;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'State','sta_prgtype'=>'Workshop');    
                                    $statwor=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $statwor;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'University','sta_prgtype'=>'Workshop');    
                                    $uniwor=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $uniwor;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'Others','sta_prgtype'=>'Workshop');    
                                    $othwor=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $othwor;
                                ;?></td>
                                
                            </tr>
                            <tr>
                                <td><?php echo "Meeting"; ?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'International','sta_prgtype'=>'Meeting');    
                                    $intmet=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $intmet;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'National','sta_prgtype'=>'Meeting');    
                                    $ntmet=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $ntmet;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'State','sta_prgtype'=>'Meeting');    
                                    $statmet=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $statmet;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'University','sta_prgtype'=>'Meeting');    
                                    $unimet=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $unimet;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sta_empid' =>$emp_id,'sta_prglevel'=>'Others','sta_prgtype'=>'Meeting');    
                                    $othmet=$this->sismodel->getcountabstract('staff_training_attended',$whdata1);
                                    echo $othmet;
                                ;?></td>
                                
                            </tr>
                        </tbody>     
                    </tr>
                    
                    <tr style=" background-color:gray;width:100%;"><td colspan="6"><b>Training Organised(Number of Trainings Organised) : </b></td></tr>
                    <tr>
                        <th>Description</th>
                        <th> International</th>
                        <th> National</th>
                        <th> State</th>
                        <th> University</th> 
                        <th> Others</th> 
                       
                        <tbody>
                            <tr>
                             <td><?php echo "Training"; ?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'International','sto_prgtype'=>'Training');    
                                    $inttrain=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $inttrain;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'National','sto_prgtype'=>'Training');    
                                    $nttrain=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $nttrain;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'State','sto_prgtype'=>'Training');    
                                    $stattrain=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $stattrain;
                                ;?></td>
                                
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'University','sto_prgtype'=>'Training');    
                                    $unitrain=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $unitrain;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'Others','sto_prgtype'=>'Training');    
                                    $othtrain=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $othtrain;
                                ;?></td>
                            </tr>
                            <tr>
                             <td><?php echo "Symposium"; ?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'International','sto_prgtype'=>'Symposium');    
                                    $intsysm=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $intsysm;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'National','sto_prgtype'=>'Symposium');    
                                    $ntsysm=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $ntsysm;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'State','sto_prgtype'=>'Symposium');    
                                    $statsysm=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $statsysm;
                                ;?></td>
                                
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'University','sto_prgtype'=>'Symposium');    
                                    $unisysm=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $unisysm;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'Others','sto_prgtype'=>'Symposium');    
                                    $othsysm=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $othsysm;
                                ;?></td>
                            </tr>
                            <tr>
                             <td><?php echo "Conference"; ?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'International','sto_prgtype'=>'Conference');    
                                    $intconf=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $intconf;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'National','sto_prgtype'=>'Conference');    
                                    $ntconf=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $ntconf;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'State','sto_prgtype'=>'Conference');    
                                    $statconf=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $statconf;
                                ;?></td>
                                
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'University','sto_prgtype'=>'Conference');    
                                    $uniconf=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $uniconf;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'Others','sto_prgtype'=>'Conference');    
                                    $othconf=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $othconf;
                                ;?></td>
                            </tr>
                            <tr>
                            <td><?php echo "Seminar"; ?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'International','sto_prgtype'=>'Seminar');    
                                    $intsem=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $intsem;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'National','sto_prgtype'=>'Seminar');    
                                    $ntsem=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $ntsem;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'State','sto_prgtype'=>'Seminar');    
                                    $statsem=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $statsem;
                                ;?></td>
                                
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'University','sto_prgtype'=>'Seminar');    
                                    $unisem=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $unisem;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'Others','sto_prgtype'=>'Seminar');    
                                    $othsem=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $othsem;
                                ;?></td>
                            </tr>
                            <tr>
                            <td><?php echo "Workshop"; ?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'International','sto_prgtype'=>'Workshop');    
                                    $intwor=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $intwor;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'National','sto_prgtype'=>'Workshop');    
                                    $ntwor=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $ntwor;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'State','sto_prgtype'=>'Workshop');    
                                    $statwor=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $statwor;
                                ;?></td>
                                
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'University','sto_prgtype'=>'Workshop');    
                                    $uniwor=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $uniwor;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'Others','sto_prgtype'=>'Workshop');    
                                    $othwor=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $othwor;
                                ;?></td>
                            </tr>
                            <tr>
                            <td><?php echo "Meeting"; ?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'International','sto_prgtype'=>'Meeting');    
                                    $intmet=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $intmet;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'National','sto_prgtype'=>'Meeting');    
                                    $ntmet=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $ntmet;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'State','sto_prgtype'=>'Meeting');    
                                    $statmet=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $statmet;
                                ;?></td>
                                
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'University','sto_prgtype'=>'Meeting');    
                                    $unimet=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $unimet;
                                ;?></td>
                                <td><?php 
                                    $whdata1 = array ('sto_empid' =>$emp_id,'sto_prglevel'=>'Others','sto_prgtype'=>'Meeting');    
                                    $othmet=$this->sismodel->getcountabstract('staff_training_organised',$whdata1);
                                    echo $othmet;
                                ;?></td>
                            </tr>
                        </tbody>     
                    </tr>
                    <tr style=" background-color:gray;width:100%;"><td colspan="6"><b>Awards and Medals : </b></td></tr>
                    <tr>
                        <th>Description</th>
                        <th> Number of Awards</th>
                        <th colspan="6"> Number of Medals</th>
                        <?php //foreach($empawarddata as $awardrec){;?>
                        <tbody>
                            <tr>
                               
                                <td><?php echo "International";?></td>
                                <?php // if($awardrec->spad_awardby =='International'): ;?>
                                <?php
                                  //  $selfield='count(*)';
                                    $whdata = array ('spad_empid' => $emp_id,'spad_awardby'=>'International','spad_awardtype!='=>'Medal');    
                                    $intawrd=$this->sismodel->getcountabstract('staff_perform_award_data',$whdata);
                                                                       
                                 ;?>
                                <td ><?php echo $intawrd;?></td>
                                <td colspan="5"><?php
                                    $whdata = array ('spad_empid' => $emp_id,'spad_awardby'=>'International','spad_awardtype'=>'Medal');    
                                    $intmedl=$this->sismodel->getcountabstract('staff_perform_award_data',$whdata);
                                    echo $intmedl;?></td>
                             
                            </tr>
                            <tr>
                                <td><?php echo "National";?></td>
                                <td><?php
                                    //$selfield='count(*)';
                                    $whdata = array ('spad_empid' => $emp_id,'spad_awardby'=>'National','spad_awardtype!='=>'Medal');    
                                    $ntawrd=$this->sismodel->getcountabstract('staff_perform_award_data',$whdata);
                                    echo $ntawrd;
                                ;?></td>
                                <td colspan="5"><?php
                                    $whdata = array ('spad_empid' => $emp_id,'spad_awardby'=>'National','spad_awardtype'=>'Medal');    
                                    $ntmedl=$this->sismodel->getcountabstract('staff_perform_award_data',$whdata);
                                    echo $ntmedl;
                                //echo ;?></td>
                            </tr> 
                            <tr>
                                <td><?php echo "State";?></td>
                                <td ><?php 
                                    $whdata = array ('spad_empid' => $emp_id,'spad_awardby'=>'State','spad_awardtype!='=>'Medal');    
                                    $stawrd=$this->sismodel->getcountabstract('staff_perform_award_data',$whdata);
                                    echo $stawrd;
                                
                                    ?></td>
                                <td colspan="5"><?php 
                                    $whdata = array ('spad_empid' => $emp_id,'spad_awardby'=>'State','spad_awardtype'=>'Medal');    
                                    $stmedl=$this->sismodel->getcountabstract('staff_perform_award_data',$whdata);
                                    echo $stmedl;  ?></td>
                            </tr>  
                            <tr>
                                <td><?php echo "University";?></td>
                                <td ><?php 
                                    $whdata = array ('spad_empid' => $emp_id,'spad_awardby'=>'University','spad_awardtype!='=>'Medal');    
                                    $uniawrd=$this->sismodel->getcountabstract('staff_perform_award_data',$whdata);
                                    echo $uniawrd;
                                ?></td>
                                <td colspan="5"><?php 
                                    $whdata = array ('spad_empid' => $emp_id,'spad_awardby'=>'University','spad_awardtype'=>'Medal');    
                                    $unimedl=$this->sismodel->getcountabstract('staff_perform_award_data',$whdata);
                                    echo $unimedl; 
                                ?></td>
                            </tr> 
                            <tr>
                                <td><?php echo "College";?></td>
                                <td ><?php
                                    $whdata = array ('spad_empid' => $emp_id,'spad_awardby'=>'College','spad_awardtype!='=>'Medal');    
                                    $colawrd=$this->sismodel->getcountabstract('staff_perform_award_data',$whdata);
                                    echo $colawrd;
                                ?></td>
                                <td colspan="5"><?php 
                                    $whdata = array ('spad_empid' => $emp_id,'spad_awardby'=>'College','spad_awardtype'=>'Medal');    
                                    $colmedl=$this->sismodel->getcountabstract('staff_perform_award_data',$whdata);
                                    echo $colmedl; 
                                ?></td>
                            </tr> 
                            <tr>
                                <td><?php echo "Professional Body";?></td>
                                <td ><?php
                                    $whdata = array ('spad_empid' => $emp_id,'spad_awardby'=>'Professional Body','spad_awardtype!='=>'Medal');    
                                    $pbawrd=$this->sismodel->getcountabstract('staff_perform_award_data',$whdata);
                                    echo $pbawrd;
                                
                                    ?></td>
                                <td colspan="5"><?php 
                                    $whdata = array ('spad_empid' => $emp_id,'spad_awardby'=>'Professional Body','spad_awardtype'=>'Medal');    
                                    $pbmedl=$this->sismodel->getcountabstract('staff_perform_award_data',$whdata);
                                    echo $pbmedl;
                                ?></td>
                            </tr> 
                        </tbody>  
                        <?php //};?>
                    </tr>
                    <?php //endif ;?>
                    
                    <tr style=" background-color:gray;width:100%;"><td colspan="6"><b>Project handled : </b></td></tr>
                    <tr>
                        <th>Funding Agency</th>
                        <th>Number of Projects handled</th>
                        <th colspan="4"> Fund outlay (in Lakhs)</th>
                       
                        <tbody>
                            <tr>
                                <td><?php echo "DBT" ?></td>
                                <td><?php 
                                    $whdata1 = array ('sppd_empid' =>$emp_id,'sppd_agendytype'=>'DBT');    
                                    $dbtnop=$this->sismodel->getcountabstract('staff_perform_project_data',$whdata1);
                                    echo $dbtnop;
                                ?></td>
                                <td colspan="4"><?php 
                                    $selectfield='sppd_budget';
                                    $whdata=array('sppd_empid' =>$emp_id,'sppd_agendytype'=>'DBT');
                                    $dbtbudget=$this->sismodel->get_sumofvalue('staff_perform_project_data',$selectfield,$whdata);
                                    foreach($dbtbudget as $row){
                                                $ttbudget=$row->sppd_budget;
                                        }
                                    echo $ttbudget;
                                ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "ICAR" ?></td>
                                <td><?php 
                                    $whdata1 = array ('sppd_empid' =>$emp_id,'sppd_agendytype'=>'ICAR');    
                                    $icarnop=$this->sismodel->getcountabstract('staff_perform_project_data',$whdata1);
                                    echo $icarnop;
                                ?></td>
                                <td colspan="4"><?php 
                                    $selectfield='sppd_budget';
                                    $whdata=array('sppd_empid' =>$emp_id,'sppd_agendytype'=>'ICAR');
                                    $icarbudget=$this->sismodel->get_sumofvalue('staff_perform_project_data',$selectfield,$whdata);
                                    foreach($icarbudget as $row){
                                        $ttbudget=$row->sppd_budget;
                                    }
                                    echo $ttbudget;
                                   
                                ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "DST" ?></td>
                                <td><?php 
                                    $whdata1 = array ('sppd_empid' =>$emp_id,'sppd_agendytype'=>'DST');    
                                    $dstnop=$this->sismodel->getcountabstract('staff_perform_project_data',$whdata1);
                                    echo $dstnop;
                                ?></td>
                                <td colspan="4"><?php 
                                    $selectfield='sppd_budget';
                                    $whdata=array('sppd_empid' =>$emp_id,'sppd_agendytype'=>'DST');
                                    $dstbudget=$this->sismodel->get_sumofvalue('staff_perform_project_data',$selectfield,$whdata);
                                    foreach($dstbudget as $row){
                                        $ttbudget=$row->sppd_budget;
                                    }
                                    echo $ttbudget;
                                    
                                ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "GOI" ?></td>
                                <td><?php 
                                    $whdata1 = array ('sppd_empid' =>$emp_id,'sppd_agendytype'=>'GOI');    
                                    $goinop=$this->sismodel->getcountabstract('staff_perform_project_data',$whdata1);
                                    echo $goinop;
                                ?></td>
                                <td colspan="4"><?php 
                                    $selectfield='sppd_budget';
                                    $whdata=array('sppd_empid' =>$emp_id,'sppd_agendytype'=>'GOI');
                                    $goibudget=$this->sismodel->get_sumofvalue('staff_perform_project_data',$selectfield,$whdata);
                                    foreach($goibudget as $row){
                                        $ttbudget=$row->sppd_budget;
                                    }
                                    echo $ttbudget;
                                ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "ICMR" ?></td>
                                <td><?php 
                                    $whdata1 = array ('sppd_empid' =>$emp_id,'sppd_agendytype'=>'ICMR');    
                                    $icmrnop=$this->sismodel->getcountabstract('staff_perform_project_data',$whdata1);
                                    echo $dbtnop;
                                ?></td>
                                <td colspan="4"><?php 
                                    $selectfield='sppd_budget';
                                    $whdata=array('sppd_empid' =>$emp_id,'sppd_agendytype'=>'ICMR');
                                    $icmrbudget=$this->sismodel->get_sumofvalue('staff_perform_project_data',$selectfield,$whdata);
                                    foreach($icmrbudget as $row){
                                        $ttbudget=$row->sppd_budget;
                                    }
                                    echo $ttbudget;
                                ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "TNGOVT" ?></td>
                                <td><?php 
                                    $whdata1 = array ('sppd_empid' =>$emp_id,'sppd_agendytype'=>'TNGOVT');    
                                    $tngovtnop=$this->sismodel->getcountabstract('staff_perform_project_data',$whdata1);
                                    echo $tngovtnop;
                                ?></td>
                                <td colspan="4"><?php 
                                    $selectfield='sppd_budget';
                                    $whdata=array('sppd_empid' =>$emp_id,'sppd_agendytype'=>'TNGOVT');
                                    $tngovtbudget=$this->sismodel->get_sumofvalue('staff_perform_project_data',$selectfield,$whdata);
                                    foreach($tngovtbudget as $row){
                                        $ttbudget=$row->sppd_budget;
                                    }
                                    echo $ttbudget;
                                ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "TNSCST" ?></td>
                                <td><?php 
                                    $whdata1 = array ('sppd_empid' =>$emp_id,'sppd_agendytype'=>'TNSCST');    
                                    $tnscstnop=$this->sismodel->getcountabstract('staff_perform_project_data',$whdata1);
                                    echo $tnscstnop;
                                ?></td>
                                <td colspan="4"><?php 
                                    $selectfield='sppd_budget';
                                    $whdata=array('sppd_empid' =>$emp_id,'sppd_agendytype'=>'TNSCST');
                                    $tnscstbudget=$this->sismodel->get_sumofvalue('staff_perform_project_data',$selectfield,$whdata);
                                    foreach($tnscstbudget as $row){
                                        $ttbudget=$row->sppd_budget;
                                    }
                                    echo $ttbudget;
                                ?></td>
                            </tr>
                           
                        </tbody>     
                    </tr>
                    
                    <tr style=" background-color:gray;width:100%;"><td colspan="6"><b>Students Guided : </b></td></tr>
                    <tr>
                        <th>Degree</th>
                        <th colspan="6"> Number of students guided</th>
                       
                        <tbody>
                            <tr>
                                <td><?php echo "M.V.Sc"; ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spsgd_empid' =>$emp_id,'spsgd_sdegree'=>'M.V.Sc');    
                                    $mvsc=$this->sismodel->getcountabstract('staff_perform_stugui_data',$whdata1);
                                    echo $mvsc;
                                ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "M.Sc"; ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spsgd_empid' =>$emp_id,'spsgd_sdegree'=>'M.Sc');    
                                    $msc=$this->sismodel->getcountabstract('staff_perform_stugui_data',$whdata1);
                                    echo $msc;
                                ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "M.Tech"; ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spsgd_empid' =>$emp_id,'spsgd_sdegree'=>'M.Tech');    
                                    $mtech=$this->sismodel->getcountabstract('staff_perform_stugui_data',$whdata1);
                                    echo $mtech;
                                ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "M.Phil"; ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spsgd_empid' =>$emp_id,'spsgd_sdegree'=>'M.Phil');    
                                    $mphil=$this->sismodel->getcountabstract('staff_perform_stugui_data',$whdata1);
                                    echo $mphil;
                                ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "PhD"; ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spsgd_empid' =>$emp_id,'spsgd_sdegree'=>'PhD');    
                                    $phd=$this->sismodel->getcountabstract('staff_perform_stugui_data',$whdata1);
                                    echo $phd;
                                ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "PG Diploma"; ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spsgd_empid' =>$emp_id,'spsgd_sdegree'=>'PG Diploma');    
                                    $pgdiplma=$this->sismodel->getcountabstract('staff_perform_stugui_data',$whdata1);
                                    echo $pgdiplma;
                                ?></td>
                            </tr>
                            <tr>
                                <td><?php echo "Others"; ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spsgd_empid' =>$emp_id,'spsgd_sdegree'=>'Others');    
                                    $others=$this->sismodel->getcountabstract('staff_perform_stugui_data',$whdata1);
                                    echo $others;
                                ?></td>
                            </tr>
                        </tbody>     
                    </tr>
                    <tr style=" background-color:gray;width:100%;"><td colspan="6"><b>Guest lecture delivered : </b></td></tr>
                    <tr>
                        <tbody>
                            <tr>
                                <td><?php echo " Total Number of Guest lecture delivered"; ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spgld_empid' =>$emp_id);    
                                    $ttlnoguestlec=$this->sismodel->getcountabstract('staff_perform_guest_lect_data',$whdata1);
                                    echo $ttlnoguestlec;
                                ?></td>
                            </tr>
				<?php
                                    $selectfield='spgld_year';
                                    $whdata= array ('spgld_empid' =>$emp_id);    
                                    $whorder='spgld_year DESC';
                                    $lastyear=$this->sismodel->get_orderlistspficemorelimit('staff_perform_guest_lect_data',$selectfield,$whdata,$whorder,'2');
				if(!empty($lastyear[0]->spgld_year)){
				?>
				
                            <tr>
                                <td><?php 
                                   // print_r($lastyear);
                                                      
                                    echo $lastyear[0]->spgld_year;
                                ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spgld_empid' =>$emp_id,'spgld_year'=>$lastyear[0]->spgld_year);    
                                    $lastguestlec=$this->sismodel->getcountabstract('staff_perform_guest_lect_data',$whdata1);
                                    echo $lastguestlec;
                                ?></td>
                            </tr>
				<?php
				}
				if(!empty($lastyear[1]->spgld_year)){
				?>
                             <tr>
                                <td><?php 
                                    echo $lastyear[1]->spgld_year;
                                ?></td>
                                <td colspan="6"><?php 
                                    $whdata1 = array ('spgld_empid' =>$emp_id,'spgld_year'=>$lastyear[1]->spgld_year);    
                                    $prelastguestlec=$this->sismodel->getcountabstract('staff_perform_guest_lect_data',$whdata1);
                                    echo $prelastguestlec;
                                ?></td>
                            </tr>
			<?php } ?>
                        </tbody>     
                    </tr>
                    <tr></tr>
<!--                    <tr><td><b>File Name</b></td>
                        <td colspan="6">
			<?php  //if (!empty($performancedata->spd_per_filename)){ ?>
                            <a href="<?php //echo base_url().'uploads/SIS/perfattachment/'.$performancedata->spd_per_filename ; ?>"
                               target="_blank" type="application/octet-stream" download="<?php //echo $performancedata->spd_per_filename ?>">Download the pdf</a>
			<?php //} 
				//else{
                               // echo " No attachment found";
                               // }
			?>
                        </td>
                    </tr>
    -->                                 
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


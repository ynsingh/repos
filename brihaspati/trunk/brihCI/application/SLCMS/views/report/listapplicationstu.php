<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name listapplicationstu.php 
  @author sumit saxena (sumitsesaxena@gmail.com)
 -->
<html  moznomarginboxes mozdisallowselectionprint>
<title>View Faculty list</title>
    <head>    
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css"> 
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	<link rel="stylesheet" type="text/css" media="print" href="<?php echo base_url(); ?>assets/css/studentStepmedia.css" />	

	<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/style.css" />
	<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/menu.css" />
	<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url();?>assets/css/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery.datetimepicker.css"/>
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>

<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;height:35px;background-color:white;width:97%;}
input[type='email']{font-size:17px;height:30px;background-color:white;}


tr td{font-size:15px;}
thead tr th{background-color:#38B0DE;color:white;font-weight:bold;font-size:15px;}
select{width:100%;font-size:17px;height:30px;font-weight:bold;}

</style>

<script>
function myFunction() {
    window.print();
}

</script>

        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
<div id="header">
	<img src="<?php echo base_url(); ?>uploads/logo/logo2.jpg" alt="logo" style="height:100px;">
</div> 

	<?php //$this->load->view('template/header'); ?>
        <h1 id='foohide'>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php echo "<span id='foohide'>";
		$this->load->view('template/menu');
	      echo "</span>";	
	?>

  	<center>
	<h1>Filter Student Record</h1>
	<form action="<?php echo site_url('report/list_application');?>" method="POST">
	<table style="width:70%;" border=0>
		<tr><td>
		<table style="width:100%;" border=0 id="foohide">
		<tr>
			<td>
				<label>Programme</label></br>
				<select name="progcat" class="form-control" style="height:37px;font-size:18px;font-weight:bold;">
 					<option disabled selected>Select Program</option>
					<?php foreach($this->prgcatname as $row){?>
						<option value="<?php echo $row->prgcat_name;?>"><?php echo $row->prgcat_name;?></option>
					<?php }?>
				</select>  
			</td>
			<td>	
				<label for="nnumber">Applicant name</label></br>	
				<input type="text" name="appstuname" placeholder="Enter Your Name" />
			</td>
		
			<td>	
				<label for="nnumber">Email</label></br>	
				<input type="text" name="appstuemail" placeholder="Enter Your Email" >		
			</td>

			<td>	
				<label>Mobile/Phone no.</label></br>
				<input type="text" name="appstumobile" placeholder="Enter Mobile Number" MaxLength="12" pattern="/^+91(7\d|8\d|9\d)\d{9}$/"> 				
			</td>

			<td>	
				<label>Gender</label></br>
				<select name="appstugender" class="form-control" style="height:37px;font-size:18px;font-weight:bold;">
 					<option value=""disabled selected>Select Gender</option>
					<option value="Male">Male</option>
					<option value="Female">Female</option>			
				</select>  
					
			</td>

			<td>	
			 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.min.css">
  			  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.min.js" ></script>

			<label>Registration Date</label></br>
			<input type="text" value="" name="appsturegistration" id="datetimepicker" placeholder="Add Time and Date"/><br>
			<script src="<?php echo base_url();?>assets/js/jquery.datetimepicker.full.js"></script>
			<script>

				$.datetimepicker.setLocale('en');
				$('#datetimepicker').datetimepicker({
				dayOfWeekStart : 1,
				lang:'en',
				formatTime:'H:i',
				formatDate:'yy-mm-dd',
				});
				//step 5 for give minute duration
				$('#datetimepicker').datetimepicker({step:1});
			</script>
		</td>
		</tr>
		<tr>

		<td>	<label for="nnumber">Exam Center</label></br>
			<select name="appstuexamcenter" class="form-control" style="height:37px;font-size:18px;font-weight:bold;">

			<option selected="true" disabled="disabled" style="font-size:18px;">Select exam center</option>
					<?php foreach($this->examcenter as $row): ?>	
					<option value="<?php echo $row->eec_id;?>"><?php echo $row->eec_name; ?></option>
					<?php endforeach; ?>
			</select>  
		</td>

		<td>
			<label>Select religion</label></br>
			<select name="appstureligion" class="form-control"  style="height:37px;font-size:18px;font-weight:bold;">
				<option selected="true" disabled="disabled" style="font-size:18px;">Select Religion</option>
				<option value="HINDUISM">HINDUISM</option>
				<option value="ISLAM">ISLAM</option>
				<option value="CHRISTIANITY">CHRISTIANITY</option>
				<option value="SIKHISM">SIKHISM</option>
				<option value="BUDDHISM">BUDDHISM</option>
				<option value="JAINISM">JAINISM</option>
				<option value="ZOROASTRIANISM">ZOROASTRIANISM</option>
				<option value="judaism">JUDAISM</option>
	 		</select>
		</td>

		<td>	
			<label>Payment Type</label></br>
			<select name="appstupaytype" style="height:37px;font-size:18px;font-weight:bold;"  id="dbType">
				<option selected="true" disabled="disabled">Select Fees Type</option>
				<option value="Offline">Offline payment</option>
				<option value="online">Online payment</option>
			</select>
		</td>
		<td>	
			<label for="nnumber">Application no.</label></br>	
			<input type="text" name="appstuapplino" placeholder="Enter Application no"/>
		</td>

		<td>	
			<label for="nnumber">Branch Applied</label></br>	
			<select name="appstubranch" class="form-control" id="register_name" style="height:37px;font-size:18px;font-weight:bold;">
			<option  disabled selected>Select Branch Applied</option>
				<?php foreach($this->prgname as $data){?>
				<option value="<?php echo $data->prg_id;?>"><?php echo $data->prg_name.'('.$data->prg_branch.')'; ?></option>
				<?php }?>
	  		</select>
		</td>
		</tr>
		</table>
		</td>
		<td>	
		
		</td></tr>
		
	</table>
	<table>
		<tr>
		<td align=center >
			<label for="nnumber" style="visibility:hidden;">Branch Applied</label></br>
			<input type="submit" name="search" value="Search" style="width:100%;height:35px;font-size:18px;" id="b1">
		</td>

		</tr>
		</table>

	<table style="margin-left:30px;margin-top:50px;text-align:center;width:71%;" class="TFtable" border=0>
	<tr>
		<th>Sr. No.</th>
		<th>Applied Prgoram</th>
		<th>Registration Date</th>
		<th>Applicant Name</th>
		<th>Email</th>
		<th>Mobile</th>
		<!--<th>Paid</th>-->
		<th>Reference No.</th>
		<th>Branch Applied</th>
	</tr>
	<?php $count=1;
	if(!empty($this->getstudata)){
	foreach($this->getstudata as $row){
	$asmid = $row->asm_id;
	$asuserid = $row->asm_userid;
	?>
	<tr>
		<td><?php echo $count++;?></td>
		<!--<td><?php ?></td>-->
		<?php $prgcat = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_userid',$asuserid)->asm_coursename;?>
		<td><?php echo $this->commodel->get_listspfic1('program','prg_category','prg_id',$prgcat)->prg_category;?></td>	
		<td><?php echo $this->commodel->get_listspfic1('admissionstudent_enterencestep','step4_date','admission_masterid',$asmid)->step4_date;?></td>
		<td><?php echo $row->asm_fname;?></td>
		<td><?php echo $row->asm_email;?></td>
		<td><?php echo $row->asm_mobile;?></td>
		<!--<td><?php echo 'y';?></td>-->
		<td><?php echo $this->commodel->get_listspfic1('admissionstudent_fees','asfee_referenceno','asfee_amid',$asmid)->asfee_referenceno;?></td>
		<?php $progid=$row->asm_coursename;?>
		<td><?php 
			echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$progid)->prg_name."&nbsp;"."(".$this->commodel->get_listspfic1('program','prg_branch','prg_id',$progid)->prg_branch.")" ;
			?></td>
	</tr>
	 <?php } }?>	


<?php if(!empty($this->pay)){
	//$count=1;
	foreach($this->pay as $row){
				$userid = $this->commodel->get_listspfic1('admissionstudent_master','asm_userid','asm_id',$row->asfee_amid)->asm_userid;
				echo "<tr>";
				echo "<td>".$count++."</td>";
				echo "<td>";
				$prgcat = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$row->asfee_amid)->asm_coursename;
				echo $this->commodel->get_listspfic1('program','prg_category','prg_id',$prgcat)->prg_category;
				echo "</td>";
				echo "<td>";	
				//echo $veri=$this->commodel->get_listspfic1('admissionstudent_registration','asreg_verificationdate','asreg_id',$userid)->asreg_verificationdate;	
				echo $this->commodel->get_listspfic1('admissionstudent_enterencestep','step4_date','admission_masterid',$row->asfee_amid)->step4_date;
				echo "</td>";
				echo "<td>";
				echo $name=$this->commodel->get_listspfic1('admissionstudent_master','asm_fname','asm_id',$row->asfee_amid)->asm_fname;
				echo "</td>";
				echo "<td>";
				echo $email=$this->commodel->get_listspfic1('admissionstudent_master','asm_email','asm_id',$row->asfee_amid)->asm_email;
				echo "</td>";
				echo "<td>";
				echo $mobile=$this->commodel->get_listspfic1('admissionstudent_master','asm_mobile','asm_id',$row->asfee_amid)->asm_mobile;
				echo "</td>";
				echo "<td>";
				echo $refno = $row->asfee_referenceno;
				echo "</td>";
				echo "<td>";
				$prgid=$this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$row->asfee_amid)->asm_coursename;
				echo $prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name."&nbsp;"."(".$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.")";
				echo "</td>";
				echo "</tr>";
			}
	}
	


 if(!empty($this->regdate)){
	//$count=1;
	foreach($this->regdate as $row){
				$userid = $this->commodel->get_listspfic1('admissionstudent_master','asm_userid','asm_id',$row->admission_masterid)->asm_userid;
				echo "<tr>";
				echo "<td>".$count++."</td>";
				echo "<td>";
				$prgcat = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$row->admission_masterid)->asm_coursename;
				echo $this->commodel->get_listspfic1('program','prg_category','prg_id',$prgcat)->prg_category;
				echo "</td>";
				echo "<td>";	
				//echo $this->commodel->get_listspfic1('admissionstudent_registration','asreg_verificationdate','asreg_id',$userid)->asreg_verificationdate;	
				echo $this->commodel->get_listspfic1('admissionstudent_enterencestep','step4_date','admission_masterid',$row->admission_masterid)->step4_date;
				echo "</td>";
				echo "<td>";
				echo $this->commodel->get_listspfic1('admissionstudent_master','asm_fname','asm_id',$row->admission_masterid)->asm_fname;
				echo "</td>";
				echo "<td>";
				echo $this->commodel->get_listspfic1('admissionstudent_master','asm_email','asm_id',$row->admission_masterid)->asm_email;
				echo "</td>";
				echo "<td>";
				echo $this->commodel->get_listspfic1('admissionstudent_master','asm_mobile','asm_id',$row->admission_masterid)->asm_mobile;
				echo "</td>";
				echo "<td>";
				echo $this->commodel->get_listspfic1('admissionstudent_fees','asfee_referenceno','asfee_amid',$row->admission_masterid)->asfee_referenceno;;
				echo "</td>";
				echo "<td>";
				$prgid=$this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$row->admission_masterid)->asm_coursename;
				echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name."&nbsp;"."(".$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.")";
				echo "</td>";
				echo "</tr>";
			}
	}



/* program category through filter student record*/
	 if(!empty($this->getprgid)){
		//$count=1;
		foreach($this->getprgid as $row){
				@$asmid = $this->commodel->get_listspfic1('admissionstudent_master','asm_id','asm_coursename',$row->prg_id)->asm_id;
				
				//$userid = $this->commodel->get_listspfic1('admissionstudent_master','asm_userid','asm_id',$row->admission_masterid)->asm_userid;
				echo "<tr>";
				echo "<td>".$count++."</td>";
				echo "<td>";
				$prgcat = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$asmid)->asm_coursename;
				echo $this->commodel->get_listspfic1('program','prg_category','prg_id',$prgcat)->prg_category;
				echo "</td>";
				echo "<td>";	
				//echo $this->commodel->get_listspfic1('admissionstudent_registration','asreg_verificationdate','asreg_id',$asmid)->asreg_verificationdate;	
				echo $this->commodel->get_listspfic1('admissionstudent_enterencestep','step4_date','admission_masterid',$asmid)->step4_date;
				
				echo "</td>";
				echo "<td>";
				echo $this->commodel->get_listspfic1('admissionstudent_master','asm_fname','asm_id',$asmid)->asm_fname;
				echo "</td>";
				echo "<td>";
				echo $this->commodel->get_listspfic1('admissionstudent_master','asm_email','asm_id',$asmid)->asm_email;
				echo "</td>";
				echo "<td>";
				echo $this->commodel->get_listspfic1('admissionstudent_master','asm_mobile','asm_id',$asmid)->asm_mobile;
				echo "</td>";
				echo "<td>";
				echo $this->commodel->get_listspfic1('admissionstudent_fees','asfee_referenceno','asfee_amid',$asmid)->asfee_referenceno;
				echo "</td>";
				echo "<td>";
				$prgid = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$asmid)->asm_coursename;
				echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name."&nbsp;"."(".$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.")";
				echo "</td>";
				echo "</tr>";
			}
	}
?>

<tr id="b1"><td colspan=8>
 <input type="submit" value="Print" onclick="myFunction()" title="Click for print" style="font-size:18px;width:5%;" id="b1">
</td>
</tr>
	</table>
	</center>
	</form>

 </div><?php $this->load->view('template/footer'); ?></div>
    </body>
</html>


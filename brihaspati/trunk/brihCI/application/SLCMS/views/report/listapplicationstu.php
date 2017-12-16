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

	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
		

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
	<img src="<?php echo base_url(); ?>uploads/logo/logo2.jpg" alt="logo" style="height:100px;width:100%;">
</div> 

	<?php //$this->load->view('template/header'); ?>
        <?php echo "<span id='foohide'>";
		//$this->load->view('template/menu');
	      echo "</span>";	
	?>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
  	<center>
	<h1>Filter Student Record</h1>
	<form action="<?php echo site_url('report/list_application');?>" method="POST">
	<table style="width:100%;" border=0>
		<tr><td>
		<table style="width:100%;" border=0 id="foohide">
		<tr>
			<td>
				<label>Programe Category</label></br>
				<select name="progcat" class="form-control" style="height:37px;width:300px;font-size:18px;font-weight:bold;">
 					<option disabled selected>Select Program</option>
					<?php foreach($this->prgcatname as $row){?>
						<option value="<?php echo $row->prgcat_name;?>"><?php echo $row->prgcat_name;?></option>
					<?php }?>
				</select>  
			</td>
			<td>	
				<label for="nnumber">Applicant Name</label></br>	
				<input type="text" name="appstuname" placeholder="Enter Your Name" />
			</td>
		
			<td>	
				<label for="nnumber">Email</label></br>	
				<input type="text" name="appstuemail" placeholder="Enter Your Email" >		
			</td>

			<td>	
				<label>Mobile/Phone No.</label></br>
				<input type="text" name="appstumobile" placeholder="Enter Mobile Number" MaxLength="12" pattern="/^+91(7\d|8\d|9\d)\d{9}$/"> 				
			</td>

			<td>	
				<label>Gender</label></br>
				<select name="appstugender" class="form-control" style="height:37px;font-size:18px;font-weight:bold;">
 					<option value=""disabled selected>Select Gender</option>
					<option value="Male">Male</option>
					<option value="Female">Female</option>			
					<option value="Transgender">Transgender</option>			
				</select>  
					
			</td>
<!---
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
		</td>-->
		</tr>
		<tr>

		<td>	<label for="nnumber">Exam Center</label></br>
			<select name="appstuexamcenter" class="form-control" style="width:300px;height:37px;font-size:18px;font-weight:bold;">

			<option selected="true" disabled="disabled" style="font-size:18px;">Select Exam Center</option>
					<?php foreach($this->examcenter as $row): ?>	
					<option value="<?php echo $row->eec_id;?>"><?php echo $row->eec_name; ?></option>
					<?php endforeach; ?>
			</select>  
		</td>

		<td>
			<label>Select Religion</label></br>
			<select name="appstureligion" class="form-control"  style="height:37px;font-size:18px;font-weight:bold;">
				<option selected="true" disabled="disabled" style="font-size:18px;">Select Religion</option>
				<option value="HINDUISM">HINDUISM</option>
				<option value="ISLAM">ISLAM</option>
				<option value="CHRISTIANITY">CHRISTIANITY</option>
				<option value="SIKHISM">SIKHISM</option>
				<option value="BUDDHISM">BUDDHISM</option>
				<option value="JAINISM">JAINISM</option>
				<option value="ZOROASTRIANISM">ZOROASTRIANISM</option>
				<option value="JUDAISM">JUDAISM</option>
	 		</select>
		</td>

		<!--<td>	
			<label>Payment Type</label></br>
			<select name="appstupaytype" style="height:37px;font-size:18px;font-weight:bold;"  id="dbType">
				<option selected="true" disabled="disabled">Select Fees Type</option>
				<option value="Offline">Offline payment</option>
				<option value="online">Online payment</option>
			</select>
		</td>-->
		<td>	
			<label for="nnumber">Application No.</label></br>	
			<input type="text" name="appstuapplino" placeholder="Enter Application No"/>
		</td>

		<td>	
			<label for="nnumber">Program (Branch) Applied</label></br>	
			<select name="appstubranch" class="form-control" id="register_name" style="width:300px;height:37px;font-size:18px;font-weight:bold;">
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

<div style="scroll:auto;height:400px;">
	<table class="TFtable" border=0>
	<tr>
		<th>Sr. No.</th>
		<th>Program Category</th>
		<th>Program (Branch) </th>
		<th>Hall Ticket Number</th>
		<th>Applicant Name</th>
		<th>Email</th>
		<th>Mobile</th>
		<th>Registration Date</th>
		<th>Reference No.</th>
		<th>Download</th>
	</tr>
	<?php $count=1;
 	if((!empty($this->getprgid)) && (!empty($this->getstudata))){		
		foreach($this->getprgid as $row){
				$prgid = $row->prg_id;
				//echo $asmid = $this->commodel->get_listspfic1('admissionstudent_master','asm_id','asm_coursename',$prgid)->asm_id;
				$selectdata=array('asm_id');
				$record=array(
   					'asm_coursename'  => $prgid,
				);
       				$amsaterid = $this->commodel->get_listspficemore('admissionstudent_master',$selectdata,$record);
				//print_r($amsaterid);
				if(!empty($amsaterid)){
				foreach($amsaterid as $row){
				$asmid = $row->asm_id;
				foreach($this->getstudata as $row1){
				$asmid1 = $row1->asm_id; 
				if($asmid == $asmid1){	
				echo "<tr>";
				echo "<td>".$count++."</td>";
				echo "<td>";
				$prgcat = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$asmid)->asm_coursename;
				echo $this->commodel->get_listspfic1('program','prg_category','prg_id',$prgcat)->prg_category;
				echo "</td>";
				echo "<td>";
				$prgid = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$asmid)->asm_coursename;
				echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name."&nbsp;"."(".$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.")";
				echo "</td>";
				echo "<td>";
				echo $this->commodel->get_listspfic1('admissionstudent_master','asm_applicationno','asm_id',$asmid)->asm_applicationno;
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
				echo $this->commodel->get_listspfic1('admissionstudent_enterencestep','step4_date','admission_masterid',$asmid)->step4_date;
				echo "</td>";
				echo "<td>";
				echo $this->commodel->get_listspfic1('admissionstudent_fees','asfee_referenceno','asfee_amid',$asmid)->asfee_referenceno;
				echo "</td>";
				echo "<td>"; ?>	
				<a href="<?php echo site_url('report/filter_datadw/');echo $asmid;?>">Download</a>
				<?php echo "</td>";
				
				echo "</tr>";
	}}}}	}}

	if((!empty($this->getstudata)) && (empty($this->getprgid))){
	foreach($this->getstudata as $row){

	$asmid = $row->asm_id;
	?>
	<tr>
		<td><?php echo $count++;?></td>
		<?php $progid=$this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$asmid)->asm_coursename;?>
		<td><?php echo $this->commodel->get_listspfic1('program','prg_category','prg_id',$progid)->prg_category;?></td>	
		<td><?php 
			echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$progid)->prg_name."&nbsp;"."(".$this->commodel->get_listspfic1('program','prg_branch','prg_id',$progid)->prg_branch.")" ;
			?></td>
		
		<td><?php echo $this->commodel->get_listspfic1('admissionstudent_master','asm_applicationno','asm_id',$asmid)->asm_applicationno;?></td>
		<td><?php echo $this->commodel->get_listspfic1('admissionstudent_master','asm_fname','asm_id',$asmid)->asm_fname;?></td>
		<?php $prgcat = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$asmid)->asm_coursename;?>
		
		
		<td><?php echo $this->commodel->get_listspfic1('admissionstudent_master','asm_email','asm_id',$asmid)->asm_email;?></td>
		<td><?php echo $this->commodel->get_listspfic1('admissionstudent_master','asm_mobile','asm_id',$asmid)->asm_mobile;?></td>
		<td><?php echo $this->commodel->get_listspfic1('admissionstudent_enterencestep','step4_date','admission_masterid',$asmid)->step4_date;?></td>
		<td><?php echo $this->commodel->get_listspfic1('admissionstudent_fees','asfee_referenceno','asfee_amid',$asmid)->asfee_referenceno;?></td>
		<td><a href="<?php echo site_url('report/filter_datadw/');echo $asmid;?>">Download</a></td>
	</tr>
	 <?php } } //}}}}//else{ ?><!--<td colspan=10><center><b>No Record Found !!!!</b></center></td>--><?php //}?>	


<?php 
	/*if(!empty($this->pay)){
	foreach($this->pay as $row){
				$asmid = $this->commodel->get_listspfic1('admissionstudent_master','asm_id','asm_id',$row->asfee_amid)->asm_id;
				
				echo "<tr>";
				echo "<td>".$count++."</td>";
				echo "<td>";	
				$prgcat = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$asmid)->asm_coursename;
				echo $this->commodel->get_listspfic1('program','prg_category','prg_id',$prgcat)->prg_category;
				echo "</td>";
				echo "<td>";
				$prgid=$this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$asmid)->asm_coursename;
				echo $prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name."&nbsp;"."(".$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.")";
				echo "</td>";
				echo "<td>";
				echo $this->commodel->get_listspfic1('admissionstudent_master','asm_applicationno','asm_id',$asmid)->asm_applicationno;
				echo "</td>";	
				echo "<td>";
				echo $name=$this->commodel->get_listspfic1('admissionstudent_master','asm_fname','asm_id',$asmid)->asm_fname;
				echo "</td>";
				
				echo "<td>";
				echo $email=$this->commodel->get_listspfic1('admissionstudent_master','asm_email','asm_id',$asmid)->asm_email;
				echo "</td>";
				echo "<td>";
				echo $mobile=$this->commodel->get_listspfic1('admissionstudent_master','asm_mobile','asm_id',$asmid)->asm_mobile;
				echo "</td>";
				echo "<td>";	
				echo $this->commodel->get_listspfic1('admissionstudent_enterencestep','step4_date','admission_masterid',$asmid)->step4_date;
				echo "</td>";
				echo "<td>";
				echo $refno = $row->asfee_referenceno;
				echo "</td>";
				echo "<td>"; ?>	
				<a href="">Download</a>
				<?php echo "</td>";
				
				echo "</tr>";
			}
	}



 if(!empty($this->regdate)){

	foreach($this->regdate as $row){
				$asmid = $this->commodel->get_listspfic1('admissionstudent_master','asm_id','asm_id',$row->admission_masterid)->asm_id;
				echo "<tr>";
				echo "<td>".$count++."</td>";
				echo "<td>";
				$prgcat = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$asmid)->asm_coursename;
				echo $this->commodel->get_listspfic1('program','prg_category','prg_id',$prgcat)->prg_category;
				echo "</td>";
				echo "<td>";
				$prgid=$this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$asmid)->asm_coursename;
				echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name."&nbsp;"."(".$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.")";
				echo "</td>";
				echo "<td>";
				echo $this->commodel->get_listspfic1('admissionstudent_master','asm_applicationno','asm_id',$asmid)->asm_applicationno;
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
				echo $this->commodel->get_listspfic1('admissionstudent_enterencestep','step4_date','admission_masterid',$asmid)->step4_date;
				echo "</td>";
				echo "<td>";
				echo $this->commodel->get_listspfic1('admissionstudent_fees','asfee_referenceno','asfee_amid',$asmid)->asfee_referenceno;;
				echo "</td>";
				echo "<td>"; ?>	
				<a href="">Download</a>
				<?php echo "</td>";
				echo "</tr>";
			}
	}

*/

/* program category through filter student record*/
	 if((!empty($this->getprgid)) && (empty($this->getstudata))){
		//$count=1;
		foreach($this->getprgid as $row){
				$prgid = $row->prg_id;
				//echo $asmid = $this->commodel->get_listspfic1('admissionstudent_master','asm_id','asm_coursename',$prgid)->asm_id;
				$selectdata=array('asm_id');
				$record=array(
   					'asm_coursename'  => $prgid,
				);
       				$amsaterid = $this->commodel->get_listspficemore('admissionstudent_master',$selectdata,$record);
				//print_r($amsaterid);
				if(!empty($amsaterid)){
				foreach($amsaterid as $row){
				$asmid = $row->asm_id;
				//$userid = $this->commodel->get_listspfic1('admissionstudent_master','asm_userid','asm_id',$row->admission_masterid)->asm_userid;
				echo "<tr>";
				echo "<td>".$count++."</td>";
				echo "<td>";
				$prgcat = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$asmid)->asm_coursename;
				echo $this->commodel->get_listspfic1('program','prg_category','prg_id',$prgcat)->prg_category;
				echo "</td>";
				echo "<td>";
				$prgid = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$asmid)->asm_coursename;
				echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name."&nbsp;"."(".$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.")";
				echo "</td>";
				echo "<td>";
				echo $this->commodel->get_listspfic1('admissionstudent_master','asm_applicationno','asm_id',$asmid)->asm_applicationno;
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
				echo $this->commodel->get_listspfic1('admissionstudent_enterencestep','step4_date','admission_masterid',$asmid)->step4_date;
				echo "</td>";
				echo "<td>";
				echo $this->commodel->get_listspfic1('admissionstudent_fees','asfee_referenceno','asfee_amid',$asmid)->asfee_referenceno;
				echo "</td>";
				echo "<td>"; ?>	
				<a href="<?php echo site_url('report/filter_datadw/');echo $asmid;?>">Download</a>
				<?php echo "</td>";
				
				echo "</tr>";
			}}}
	}
?>

<tr id="b1"><td colspan=10><center>
 <input type="submit" value="Print" onclick="myFunction()" title="Click for print" style="font-size:18px;" id="b1">
</center></td>
</tr>
	</table>
</div>
	</center>
	</form>

 <div style="margin-top:80px;"><?php $this->load->view('template/footer'); ?></div>
    </body>
</html>


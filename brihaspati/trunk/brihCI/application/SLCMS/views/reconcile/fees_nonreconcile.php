<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name fees_nonreconcile.php 
  * @author Sumit Saxena(sumitsesaxena@gmail.com) *
 -->

<html>
<title>Fees Reconcile</title>
 <head>    
	<?php $this->load->view('template/header'); ?>
	<h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
	<?php $this->load->view('template/menu');?>
	
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">

 </head>    
   <body>

<?php
echo "<center>";

	if($this->session->flashdata('msg')){
echo "<div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}

echo "</center>";
?>
</br></br>
<center>
<table width="70%;" style="" >
	<tr><td style=""><a href="<?php echo site_url('reconcile/fees_nonreconcile');?>" style="font-weight:bold; margin-left:0px;"> Non Reconcile Fees
		<a href="<?php echo site_url('reconcile/fees_reconcile_complete');?>" style="font-weight:bold; margin-left:4%;" id="allf">All Fees
		<a href="<?php echo site_url('reconcile/fees_reconcile');?>" style="font-weight:bold; margin-left:5%;" id="reconcile">Reconcile Fees
	</td>
       	</tr>
</table>

   
</br>
<table style="width:70%;border:2px solid #a8a8a8;">
<thead style="background-color:#38B0DE;color:white;height:30px;font-size:22px;"><tr><th>Non Reconcile Fees Detail</th></tr></thead>
</table>
   <table class="TFtable" style="background-color:#38B0DE;border:2px solid #a8a8a8;">
	<thead >
	<tr>
<th>Sr. No.</th>
<th>Student name</th><th>Program(Branch)</th><th>Semester</th><th>Year</th><th>Fees Type</th><th>Fees Amount</th><th>Reference Number</th>
<th>Bank Name / Payment Method</th><th>Action</th>
	</tr>
	</thead>

	<tbody align="center">
		<form action="<?php echo site_url('reconcile/fees_nonreconcile');?>" method="POST" >
			<?php $count=1; foreach($this->stu_feedata as $row){ ?>
			   <tr>
				<td><?php echo $count++;?></td>
				<!---<td><input type="checkbox" name='fees_check<?php echo $row->sfee_id;?>' value="Y"></td>--->
				<?php 
					@$name    = $this->commodel->get_listspfic1("student_master","sm_fname","sm_id",$row->sfee_smid)->sm_fname;
					@$prgid   = $this->commodel->get_listspfic1("student_program","sp_programid","sp_id",$row->sfee_spid)->sp_programid;
    				      	@$sem     = $this->commodel->get_listspfic1("student_program","sp_semester","sp_id",$row->sfee_spid)->sp_semester;
				      	@$year    = $this->commodel->get_listspfic1("student_program","sp_acadyear","sp_id",$row->sfee_spid)->sp_acadyear;
				       	$prgrame  = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
				       	$branch   = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;
				?>
				<td><?php echo $name;?></td>
				<td><?php echo $prgrame.'('.$branch.')';?></td>
				<td><?php echo $sem;?></td>
				<td><?php echo $year;?></td>
				<td><?php echo $row->sfee_feename;?></td>
				<td><?php echo $row->sfee_feeamount;?></td>
				<td><?php echo $row->sfee_referenceno;?></td>
				<td><?php echo $row->sfee_bankname;echo ' '.'/'.' ';echo $row->sfee_paymentmethod;?></td>
				<td><a href="<?php echo site_url('reconcile/nonreconcile_fee/');echo $row->sfee_id;?>" title="Click to reconcile fees">Reconcile</a></td> 
			</tr>
			
			<?php }?>
			<!---<tr><td colspan=11 style="text-align:justify">
				<input type="submit" name="nreconcile<?php echo $row->sfee_id;?>" value="Reconcile" style="width:7%;height:30px;">
			</td></tr>---->
		</form>
	</tbody>

   </table>				
</center>     

  </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      

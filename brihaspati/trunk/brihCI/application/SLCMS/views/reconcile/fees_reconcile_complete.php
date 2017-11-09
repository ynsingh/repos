<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name fees_reconcile_complete.php 
  * @author Sumit Saxena(sumitsesaxena@gmail.com) *
 -->


<html>
<title>Fees Reconcile</title>
 <head>    
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
<script>

</script>
 </head>    
   <body>
	<?php $this->load->view('template/header'); ?>
	<h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
	<?php $this->load->view('template/menu');?>
	

<center>   
</br></br>
<table width="70%;" style="" >
	<tr><td style=""><a href="<?php echo site_url('reconcile/fees_nonreconcile');?>" style="font-weight:bold;"> Non Reconcile Fees
		<a href="<?php echo site_url('reconcile/fees_reconcile_complete');?>" style="font-weight:bold; margin-left:4%;" id="allf">All Fees
		<a href="<?php echo site_url('reconcile/fees_reconcile');?>" style="font-weight:bold; margin-left:5%;" id="reconcile">Reconcile Fees
	</td>
       	</tr>
</table>
</br>
<table style="width:70%;border:2px solid #a8a8a8;">
<thead style="background-color:#38B0DE;color:white;height:30px;font-size:22px;"><tr><th>
	<?php echo $this->message;?>
</th></tr></thead>
</table>

   <table class="TFtable" style="border:2px solid #a8a8a8;">
	
	<thead>
	<tr>
<th>Sr. No.</th>
<th>Student name</th><th>Program(Branch)</th><th>Semester</th><th>Year</th><th>Fees Type</th><th>Fees Amount</th><th>Reference Number</th><th>Bank Name / Payment Method</th><th>Reconciled</th>
	</tr>
	</thead>

	<tbody align="center">
		<form action="" method="POST">
			<?php $count=1; foreach($this->stu_feedata as $row){ ?>
			<tr>
				<?php 
					@$name   = $this->commodel->get_listspfic1("student_master","sm_fname","sm_id",$row->sfee_smid)->sm_fname;
					@$prgid   = $this->commodel->get_listspfic1("student_program","sp_programid","sp_id",$row->sfee_spid)->sp_programid;
    				      	@$sem     = $this->commodel->get_listspfic1("student_program","sp_semester","sp_id",$row->sfee_spid)->sp_semester;
				      	@$year    = $this->commodel->get_listspfic1("student_program","sp_acadyear","sp_id",$row->sfee_spid)->sp_acadyear;
				       	$prgrame = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
				       	$branch  = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;
				?>
				<td><?php echo $count++;?></td>
				<td><?php echo $name;?></td>
				<td><?php echo $prgrame.'('.$branch.')';?></td>
				<td><?php echo $sem;?></td>
				<td><?php echo $year;?></td>
				<td><?php echo $row->sfee_feename;?></td>
				<td><?php echo $row->sfee_feeamount;?></td>
				<td><?php echo $row->sfee_referenceno;?></td>
				<td><?php echo $row->sfee_bankname; echo ' '.'/'.' '; echo $row->sfee_paymentmethod;?></td>
				<td><?php echo $row->sfee_reconcilestatus;?></td>
			</tr>
			
			<?php }?>
		</form>
	</tbody>

   </table>				
</center>     

  </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      

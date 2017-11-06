<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name ent_feesnonreconcile.php 
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
echo "<div style='font-size:20px;text-align:left;background-color:#DFF2BF;width:70%;height:30px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}

echo "</center>";
?>

</br></br>

<center>  
<table width="70%;" style="margin-left:-5%;font-size:18px;">
	<tr><td style=""><a href="<?php echo site_url('enterenceadmin/viewentfeereconcile');?>" style="; margin-left:50px;">Entrance Admission Reconcile Fees
		<a href="<?php echo site_url('enterenceadmin/viewentfeereconcile_complete');?>" style=" margin-left:50px;">All Entrance Admission Reconcile Fees
		<a href="<?php echo site_url('enterenceadmin/fees_nonreconcile');?>" style=" margin-left:50px;" > Entrance Admission Non Reconcile Non
	</td>
	<td style=""></td>
	<td style=""></td>
	</tr>
</table> 
</br></br>
<table style="width:70%;border:2px solid #a8a8a8;">
<thead style="background-color:#38B0DE;color:white;height:30px;font-size:22px;"><tr><th align="left">Entrance Non-Reconcile Fees Detail</th></tr></thead>
</table>
   <table class="TFtable" style="border:2px solid #a8a8a8;">
	<thead >
	<tr>
<th style="text-align:left;">Sr. No.</th>
<th style="text-align:left;">Student name</th><th style="text-align:left;">Program(Branch)</th><th style="text-align:left;">Fees Amount</th><th style="text-align:left;">Reference Number</th><th style="text-align:left;">Bank Name / Payment Method</th><th style="text-align:left;">Reconciled</th>
	</tr>
	</thead>

	<tbody align="left">
		<form action="<?php echo site_url('enterenceadmin/nonreconcile_fee');?>" method="POST" >
			<?php $count=1; 
			
			foreach($this->stu_feedata as $row){ ?>
			   <tr>
				<td><?php echo $count++;?></td>
				
				<?php 
					@$name    = $this->commodel->get_listspfic1("admissionstudent_master","asm_fname","asm_id",$row->asfee_amid)->asm_fname;
					@$prgid   = $this->commodel->get_listspfic1("admissionstudent_master","asm_coursename","asm_id",$row->asfee_amid)->asm_coursename;
				      	$prgrame  = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
				       	$branch   = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;
				?>
				<td><?php echo $name;?></td>
				<td><?php echo $prgrame.'('.$branch.')';?></td>
				<td><?php echo $row->asfee_feeamount;?></td>
				<td><?php echo $row->asfee_referenceno;?></td>
				<td><?php echo $row->asfee_bankname;echo ' '.'/'.' ';echo $row->asfee_paymentmethod;?></td>
				<td><a href="<?php echo site_url('enterenceadmin/nonreconcile_fee/');echo $row->asfee_amid;?>" title="Click to reconcile fees">Reconcile</a></td> 
			</tr>
			
			<?php } ?>

			
		</form>
	</tbody>

   </table>				
</center>     

  </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      

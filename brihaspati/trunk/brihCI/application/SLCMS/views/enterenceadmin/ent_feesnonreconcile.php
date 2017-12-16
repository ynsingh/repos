<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name ent_feesnonreconcile.php 
  * @author Sumit Saxena(sumitsesaxena@gmail.com) *
 -->

<html>
<title>Fees Reconcile</title>
 <head>    
	<?php $this->load->view('template/header'); ?>
	
	<?php //$this->load->view('template/menu');?>
	
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">

 </head>    
   <body>


<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table> -->


<?php
echo "<center>";

	if($this->session->flashdata('msg')){
echo "<div style='font-size:20px;text-align:left;background-color:#DFF2BF;width:70%;height:30px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}

echo "</center>";
?>
  
<table width="100%;" style="font-size:18px;">
	<tr><td style=""><a href="<?php echo site_url('enterenceadmin/viewentfeereconcile');?>" style="">Entrance Admission Reconcile Fees
		<a href="<?php echo site_url('enterenceadmin/viewentfeereconcile_complete');?>" style=" margin-left:5%;">All Entrance Admission Reconcile Fees
		<a href="<?php echo site_url('enterenceadmin/fees_nonreconcile');?>" style=" margin-left:5%;" > Entrance Admission Non Reconcile 
	</td>
	
	</tr>
</table>
<table style="width:100%;border:2px solid #a8a8a8;">
<thead style="background-color:#38B0DE;color:white;height:30px;font-size:22px;"><tr><th align="left">Entrance Non-Reconcile Fees Detail</th></tr></thead>
</table>

<div style="overflow:auto;height:620px;">
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
</div><!------scroller div------>			
   

  </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      

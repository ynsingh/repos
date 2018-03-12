<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name ent_feesreconcile_complete.php 
  * @author Sumit Saxena(sumitsesaxena@gmail.com) *
    @author Neha Khullar(nehukhullar@gmail.com)
 -->


<html>
<title>Entrance Fees Reconcile</title>
 <head>    
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/style.css">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>

 </head>    
   <body>
<?php $this->load->view('template/header'); ?>
          
        <?php //$this->load->view('template/menu');?>

<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
	
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
	<tr><td style=""><a href="<?php echo site_url('enterenceadmin/viewentfeereconcile');?>" style="">Entrance Admission Reconcile  Fees
		<a href="<?php echo site_url('enterenceadmin/viewentfeereconcile_complete');?>" style=" margin-left:50px;">All Entrance Admission Reconcile Fees
		<a href="<?php echo site_url('enterenceadmin/fees_nonreconcile');?>" style=" margin-left:50px;" > Entrance Admission Non Reconcile
	</td>
	<td style=""></td>
	<td style=""></td>
	</tr>
</table>
<table style="width:100%;border:2px solid #a8a8a8;">
<thead style="background-color:#38B0DE;color:white;height:30px;font-size:22px;"><tr align="left"><th>
	<?php echo $this->message;?>
</th></tr></thead>
</table>
<div style="overflow:auto;height:620px;">
   <table class="TFtable" style="">
	
	<thead>
	<tr>
<th style="text-align:left;">Sr. No.</th>
<th style="text-align:left;">Student Name</th><th style="text-align:left;">Program(Branch)</th><th style="text-align:left;">Fees Amount</th><th style="text-align:left;">Reference Number</th><th style="text-align:left;">Bank Name / Payment Method</th><th style="text-align:left;">Reconciled</th>
	</tr>
	</thead>

	<tbody align="left">
		<form action="" method="POST">
			<?php $count=1; foreach($this->stu_feedata as $row){ ?>
			<tr>
				<?php 
					@$name    = $this->commodel->get_listspfic1("admissionstudent_master","asm_fname","asm_id",$row->asfee_amid)->asm_fname;
					@$prgid   = $this->commodel->get_listspfic1("admissionstudent_master","asm_coursename","asm_id",$row->asfee_amid)->asm_coursename;
				      	$prgrame  = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
				       	$branch   = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;
				?>
				<td><?php echo $count++;?></td>
				<td><?php echo $name;?></td>
				<td><?php echo $prgrame.'('.$branch.')';?></td>
				<td><?php echo $row->asfee_feeamount;?></td>
				<td><?php echo $row->asfee_referenceno;?></td>
				<td><?php echo $row->asfee_bankname; echo ' '.'/'.' '; echo $row->asfee_paymentmethod;?></td>
				<td><?php echo $row->asfee_reconcilestatus;?></td>
			</tr>
			
			<?php }?>
		</form>
	</tbody>

   </table>
</div>	<!------scroller div------>			
    

  </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      

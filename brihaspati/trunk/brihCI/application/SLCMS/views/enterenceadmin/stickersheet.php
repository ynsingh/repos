<!-------------------------------------------------------
    -- @name stickersheet.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html moznomarginboxes mozdisallowselectionprint>
<head>
<meta charset="utf-8">
	<title>IGNTU:Sticker sheet</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">
	<link rel="stylesheet" type="text/css" media="all" href="<?php echo base_url(); ?>assets/css/Studentsteps.css" />
<script>
function myFunction() {
    window.print();
}

</script>

</head>

<body>
<?php  // $thisPage2="studentaddDetail"; 
		$this->load->view('template/header'); ?>
	<h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
</br>
<center>


	<form action="<?php echo site_url('enterenceadmin/generatesticker'); ?>" method="POST">
	<table style="width:50%;" border=0>
		<tr>
			<td style="height:35px;font-size:18px;"> Generate Sticker Sheet</td>
			<td align=right>
			<label for="nnumber"></label></br>
			<select name="stiexamcenter" class="form-control" style="height:37px;font-size:18px;font-weight:bold;">

			<option selected="true" disabled="disabled" style="font-size:18px;">Select Entrance Exam Center</option>
					<?php foreach($this->examcenter as $row): ?>	
					<option value="<?php echo $row->eec_name;?>"><?php echo $row->eec_name; ?></option>
					<?php endforeach; ?>
			</select>
			</td>
			<td align=left><label for="nnumber" style="visibility:hidden;">Enterence Exam Center</label></br>
			<input type="submit" name="searchsticker" value="Submit" style="height:35px;font-size:18px;"></td>
		</tr>
		
	</table>
	</form>
	<table style="width:80%;margin-top:30px;" border=0>
	
	<tr>
		<?php 
			$year=date('Y');
			$i=0;
			if(!empty($this->examcenter)){
			foreach($this->examcenter as $row){
			$centerid = $row->eec_id;
			$cname = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$centerid)->eec_name;
		?>
				<td style="border:1px solid black;">
					<a href="<?php echo base_url('uploads/SLCMS/enterenceadmin_student/'.$year.'/sticker/'.$centerid.'Sticker'.'.pdf');?>" target=_blank style="font-size:20px;">
		<?php echo $cname;?>  Sticker List</br>
		<embed src="<?php echo base_url('uploads/SLCMS/enterenceadmin_student/'.$year.'/sticker/'.$centerid.'Sticker'.'.pdf');?>" type="application/pdf"   height="350px" width="100%">
		</a>	
				</td>	
			
			<?php 
			//}
			$i++;
			if($i%6 == 0){?>
			</tr>
			<tr>
			<?php } 
		} 	}
?>
	</tr>
	</table>
	
</center>

    <div align="center">  <?php $this->load->view('template/footer');?></div>
</body>
</html>

<!-------------------------------------------------------
    -- @name attendencesheet.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html moznomarginboxes mozdisallowselectionprint>
<head>
<meta charset="utf-8">
	<title>SLCMS:Attendence sheet</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
</head>
<?php  // $thisPage2="studentaddDetail"; 
		$this->load->view('template/header'); ?>
        <?php //$this->load->view('template/menu');?>
<body>
<!--
<p>
<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>
</p>
-->
<table align=center style="width:100%;">
<tr>
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div style="" class="isa_success">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <td class="isa_success"><?php echo $_SESSION['success'];?></td>
        <?php
    	 };
       	?>
</tr>	<tr class="isa_error">
        <?php if(isset($_SESSION['err_message'])){?>
             <td style=''><?php echo $_SESSION['err_message'];?></td>
        <?php
        };
	?>  
</tr>
   </table>	
   </table>		
<center>
<form action="<?php echo site_url('enterenceadmin/generateattendence'); ?>" method="POST">
	<table style="margin-top:-1%;" border=0>
		<tr>
			<td style="height:35px;font-size:18px;"  align=center><h2>Generate Attendance Sheet</h2></td>
			<td align=right valign="top">
			<label for="nnumber"></label></br>
			<select name="attexamcenter" class="form-control" style="height:37px;font-size:18px;font-weight:bold;">

			<option selected="true" disabled="disabled" style="font-size:18px;">Select Entrance Exam Center</option>
				<?php foreach($centerlist as $row): 
					 if(!empty($row->ca_centername)){
					$centerid = $row->ca_centername;
					$centername = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$centerid)->eec_name;
					$centercode = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_code','eec_id',$centerid)->eec_code;
				?>	
					<option value="<?php echo $centerid;?>"><?php echo $centername.'( '.$centercode.' )'; ?></option>
				<?php    }
					 endforeach; ?>
			</select>
			</td>
			<td align=left valign="top"><label for="nnumber" style="visibility:hidden;">Enterence Exam Center</label></br>
			<input type="submit" name="searchattendence" value="Submit" style="height:35px;font-size:18px;"></td>
		</tr>
		
	</table>
	</form>


<div class="scroller_sub_page">
<table style="width:100%;" border=0>
	
	<tr>
		<?php 
			$year=date('Y');
			$i=0;
			if(!empty($centerlist)){
			
				foreach($centerlist as $row){
					$centerid = $row->ca_centername;
					$cname = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$centerid)->eec_name;
					
		if((!empty($centerid)) && (!empty($row->ca_attendencesheetstatus))){ ?>
			<td style="border:0px solid black;">
				<?php //if(!empty($row->ca_attendencesheetstatus)){
					$attrurl = 'uploads/SLCMS/enterenceadmin_student/'.$year.'/attendence/'.$centerid.'.pdf';
					if(file_exists($attrurl)) {
				?>
						<a href="<?php echo base_url($attrurl);?>" target=_blank style="font-size:20px;">
		<?php echo $cname;?>  Attendance Sheet</br>
							<embed src="<?php echo base_url($attrurl);?>" type="application/pdf"   height="350px" width="100%">
						</a>
				<?php }//} ?>	
					
			</td>	
			<?php } 
					$i++;
					if($i%6 == 0){?>
						</tr>
						<tr>
				<?php 	} 
			} 	}
				?>
	</tr>
	</table>
</div><!------scroller div------>

</center>

<div align="center">  <?php $this->load->view('template/footer');?></div>
</body>
</html>

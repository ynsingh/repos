<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name viewfull_profile.php
@ author sumit saxena[sumitsesaxena@gmail.com]

 -->
<html>
<title>View Faculty list</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
<p>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
</p>

<table style="width:100%;" border=0>
<tr>
<td valign="top" width=170>

 <table border=1 class="TFtable">
		<tr>
			<?php if(!empty($photo)):?>
                            <td><img src="<?php echo base_url('uploads/SIS/empphoto/'.$photo);?>"  alt="" v:shapes="_x0000_i1025" style="width:100%;height:170px;"></td>
                         <?php else :?>
                            <td><img src="<?php echo base_url('uploads/SIS/empphoto/empdemopic.png');?>"  alt="" v:shapes="_x0000_i1025" style="width:100%;height:170px;"></td>
			 <?php endif;?>
			
			
		</tr>
		<tr>
			<td>Phone No. </br><?php echo $phone;?></td>
		</tr>
		<tr>	
			<td>E-mail Id </br> <?php echo $email;?></td>
		</tr>
		<tr></tr>
		<tr><td height=150></td><tr>
	    </table>
	   
</td>

<td>		
	
           <table class="TFtable" style="width:100%;">
			<tr>
				<td>Emp No. :</td>
				<td><?php echo $emp_no;?></td>
				<td>Date Of Appointment :</td>
			        <td><?php echo $dop;?></td>
			</tr>
			
			<tr>
				<td>Name :</td>
				<td><?php echo $emp_name;?></td>
				<td>Department :</td>
				<?php 
				$deptid = $dep;
				$depname = $this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name ;?>
				<td><?php echo $depname;?></td>
			</tr>
			<tr>
				<td>Designation :</td>
				<?php 
				$desigid = $desig;
				$depname = $this->commodel->get_listspfic1('designation','desig_name','desig_id',$desigid)->desig_name ;?>
				<td><?php echo $depname;?></td>
				<td>Date Of Regulariation :</td>
			        <td><?php //echo $dor;?></td>
			</tr>
			
			<tr>
				<td>Community :</td>
				<td><?php echo $commu;?></td>
				<td>Date Of Completion Of Probation :</td>
				<td><?php //echo $doprobe; ?></td>
			</tr>
			<tr>
				<td>Caste :</td>
				<td><?php echo $caste;?></td>
				<td>Religion :</td>
			        <td><?php echo $religion;?></td>
			</tr>
			
			<tr>
				<td>Nativity :</td>
				<td><?php echo $native;?></td>
				<td>Specialization :</td>
				<?php
				$specialid = $specialize;
				if(!empty($specialid)){
					$specialize = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$specialid)->sub_name;	
				}
				?>
				<td><?php echo $specialize;?></td>
			</tr>
			<tr>
				
				<td>To Present Post :</td>
			        <td><?php echo $post;?></td>
				<td>Qualification (PHD Completion):</td>
				<td><?php echo $quali;?></td>
			</tr>
			
			<tr>
				
				<td>Date Of Birth :</td>
				<td><?php echo $dob;?></td>
				<td>Date Of Retirement :</td>
				<td><?php echo $dor;?></td>
			</tr>
			<tr>
				<td>ASRR :</td>
				<td><?php echo $asrr;?></td>
				<td></td><td></td>
			</tr>
		</table>
		<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;"><td align=left colspan=4><b>Service Particulars</b></td><tr>
		</table>
		<table class="TFtable" style="width:100%;" align="center">
                    <thead>
                        <tr>
                            <th>Place Of Working</th>
                            <th>Designation</th>
                            <th>AGP</th>
                            <th>Date of AGP</th>
                            <th>From</th>
                            <th>To</th></tr>
                            <th>Total service year</th>
                            <th>Total service month</th>
                            <th>Total service day</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!--<?php if( count($records) ):  ?>
                            <?php foreach($records as $record){;?>-->
                            <tr>
				<!--<?php $powid = $scid;
				$place = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$powid)->sc_name ;
				?>
				<td><?php echo $place;?></td>
				<?php 
				$desigid = $desig;
				$depname = $this->commodel->get_listspfic1('designation','desig_name','desig_id',$desigid)->desig_name ;?>
				<td><?php echo $depname;?></td>
				<td><?php echo $dop;?></td>
				<td><?php echo 'Continue ......';?></td>-->
			</tr>
                        <!--    <?php }; ?>
                        <?php else : ?>
                            <td colspan= "13" align="center"> No Records found...!</td>
                        <?php endif;?>-->
                    </tbody>    
		</table>
</td>
</tr>

</table>

<div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>
</html>


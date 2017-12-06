<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Welcome </title>
	 <link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
         <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/helpdoc.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/style.css">
	 <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/tablestyle.css">	
<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<style>
		table,th, td{
   		 border:0px solid black;}
		
	</style>

</head>
<body style="border:0px solid black;">


<div>
	<div>
<?php $this->load->view('template/header'); ?>
<?php echo "<center>";

	if($this->session->flashdata('msg')){
echo "<div style='font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;'>";
	echo $this->session->flashdata('msg');
echo "<div>";	
}

echo "</center>"; ?>

<table style="width:100%;" >
            <tr colspan="2">
                <td>
                    <div align="left" style="">
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                   <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
                    <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success" style="font-size:18px;"><?php echo $_SESSION['success'];?></div>
                    <?php
                    };
                    ?>
                    <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error" style="font-size:18px;"><?php echo $_SESSION['err_message'];?></div>
                    <?php
                    };
                ?>
                </div>
            </td>
     </tr>
</table>

<div class="welcome"><h2>Welcome</h2></div>

<?php $this->load->view('enterence/enterence_head');?>

</br>
<table style="width:100%;border:0px solid black;" align=center border=0> 
	
	<tr>
		<td style="width:33%;" valign="top">
			<div align=left>
			<select style="height:37px;font-size:18px;font-weight:bold;width:80%;" id="menuhide">
 				<option  disabled selected>Study Centers</option>
				
				<option value="1" id="open-one">IGNTU, HQ, Amarkantak</option>
				<option value="2" id="open-two">Regional campus,manipur</option>
				
			</select>  
			</div>
<br>
			<DIV id="box-one" style="display:none;">
			<div id="cssmenu">
                				<ul>
							<li><a href="">ADMISSION NOTIFICATON</a></li>
								<?php 
								$cdate = date('Y-m-d H:i:s');
//								foreach($scrlist as $row){
									$scid = 1;
									$wharray = array('prg_scid'  =>  1);
									$prgcat = $this->commodel->get_distinctrecord('program','prg_category',$wharray);
									foreach($prgcat as $row){	
										$prgcatname = $row->prg_category;
										$selectfield=array('admop_prgname_branch');
										$data=array(
      											'admop_prgcat' => $prgcatname,
      											'admop_lastdate >=' => $cdate,
       										);
										$progid = $this->commodel->get_distinctrecord('admissionopen',$selectfield,$data);
									?>
        						 		<li class='has-sub'><a href=""><?php echo $prgcatname;?></a>
                      	  						    	<ul>
											<?php foreach($progid as $row){
												$id = $row->admop_prgname_branch;
												$pname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$id)->prg_name;
									
												?>
                        									<li><a href="<?php echo site_url('welcome/ginstruction/');echo $id;?>"><?php echo $pname ."(".$this->commodel->get_listspfic1('program','prg_branch','prg_id',$id)->prg_branch .")" ;?></a></li>
											<input type="hidden" value="<?php echo $id;?>" name="prgid">
											
										<?php }?>
                        							</ul>
										
									</li>
							<?php }
			//						}?>
						</ul>
               			 </div>
       			 </div>
        		</DIV>

		<DIV id="box-two" style="display:none;">
			<div id="cssmenu">
                				<ul>
							<li><a href="">ADMISSION NOTIFICATON</a></li> 
							
								<?php 
									$cdate = date('Y-m-d H:i:s');
									//foreach($this->prgcat as $pname){
										$pg = 'Post Graduate';
										//$prgcatpart = explode(',',$progname);
										$selectfield=array('admop_prgname_branch');
										$data=array(
      											//'admop_prgcat' => $progname,
											'admop_prgcat'  => $pg,
											//'admop_prgcat'  => 'Research',
      											'admop_lastdate >=' => $cdate,
       										);
										//print_r($data);
										$progid = $this->commodel->get_distinctrecord('admissionopen',$selectfield,$data);
										//print_r($progid);
								?>
        						 <li class='has-sub'><a href="">Post Graduate</a>
							 
                      	  				<ul>
								<?php foreach($progid as $row){
									$id = $row->admop_prgname_branch;
									$pname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$id)->prg_name;
									
							?>
                        						<li><a href="<?php echo site_url('welcome/ginstruction/');echo $id;?>"><?php echo $pname ."(".$this->commodel->get_listspfic1('program','prg_branch','prg_id',$id)->prg_branch .")" ;?></a></li>
									<input type="hidden" value="<?php echo $id;?>" name="prgid">
								<?php }?>
                        				</ul>
								<?php //}?>
							</li>
							<?php 
									$cdate = date('Y-m-d H:i:s');
									//foreach($this->prgcat as $pname){
										$re = 'Research';
										//$prgcatpart = explode(',',$progname);
										$selectfield=array('admop_prgname_branch');
										$data=array(
      											//'admop_prgcat' => $progname,
											'admop_prgcat'  => $re,
											//'admop_prgcat'  => 'Research',
      											'admop_lastdate >=' => $cdate,
       										);
										//print_r($data);
										$progid1 = $this->commodel->get_distinctrecord('admissionopen',$selectfield,$data);
										//print_r($progid);
								?>
							<li class='has-sub'><a href="">Research</a>
							 
                      	  				<ul>
								<?php foreach($progid1 as $row){
									$id1 = $row->admop_prgname_branch;
									$pname1 = $this->commodel->get_listspfic1('program','prg_name','prg_id',$id1)->prg_name;
									
							?>
                        						<li><a href="<?php echo site_url('welcome/ginstruction/');echo $id1;?>"><?php echo $pname1 ."(".$this->commodel->get_listspfic1('program','prg_branch','prg_id',$id1)->prg_branch .")" ;?></a></li>
									<input type="hidden" value="<?php echo $id1;?>" name="prgid">
								<?php }?>
                        				</ul>
								<?php //}?>
							</li>
						</ul>
               			 </div>
       			 </div>
        		</DIV>


		</td>
<script>
$('#menuhide').on('change', function () {
    if(this.value == "1"){
        $("#box-one").show();
    } else {
        $("#box-one").hide();
    }
if(this.value == "2"){
        $("#box-two").show();
    } else {
        $("#box-two").hide();
    }

});

</script>
		<td align=center width="34%" style="" valign="top">
		
		<div style="overflow:auto;height:300px;">
			<table style="width:100%" class="TFtable" >
				<tr style="background-color:#38B0DE;color:white;font-size:21px;">
				<td style="border:2px solid white;" align=center colspan=5>Announcement</td></tr>
				<?php
				$count =0;
				foreach($annoresult as $aname){
                                echo "<tr style=\"font-size:15px;\">";
				echo "<td>";
                                echo  "<b>".++$count."</b>" ; 
                                echo ".";
                                echo "&nbsp;&nbsp;";
                                echo"</td>";
                                echo "<td>";
                                echo  "<b>" .$aname->anou_title."</b>";
				echo "&nbsp;&nbsp;";                                
				echo"</td>";
				echo "<td>";
                                echo  $aname->anou_description;
				echo"</td>";
				?>
				<td><a href ="<?php echo base_url('uploads/announcement/'.$aname->anou_attachment);?>"target=_blank><?php echo $aname->anou_attachment;?></a></td>
                                <?php
 				echo "</tr>";
				}
				?>
</div>
	      </td>
	
</table>
	
		<!--<td align=right style="width:30%;" valign="top">
		<form action="<?= site_url('welcome') ?>" method="post">
        		<table>
			<tr>
			<td align=left>	
				<label>Username</label></br>	
        			<input type="text" name="username" size="33%" style="height:33px;"/>
			</td>
			</tr>
				<tr>
				<td align=left><label>Password</label></br>
        			<input type="password" name="password" size="33%" style="height:33px;" placeholder="********"/></td></tr>
				<tr>
				<td >
        			<button type="submit" style="width:30%" id="button"><b>Login</b></button>
		
		<!--		<a href="<?php echo site_url('Student/student_step0');?>" style="" title="Click to open student detail form">
				<input type="button" value="New Student" style="font-size:17px;width:58%;"></a></td></tr>
			<tr>
               			 <td>
				<a href="<?php echo site_url('forgotpassword/forgotpass');?>" style="" title="Forgot Password">
                		<input type="button" value="Forgot Password" style="font-size:17px;width:60%"></a></td>
			</tr>
				
			</table>
    			</form>	
		</td>--->
		<!--<td width=15></td> -->
		<td align=right style="width:33%;" valign="top">
			<table style="width:100%;">
				<tr>
					<td style="text-align:right;font-size:20px;">
					<a href="<?php echo base_url('uploads/SLCMS/Prospectus1718.pdf');?>" title="Download Your Prospectus" target=_blank>
					Prospectus
					<img src="<?php echo base_url(); ?>uploads/SLCMS/pdf-icon.png" alt="logo" style="height:25px;"></a></td>
				</tr>
				<tr height=15></tr>
				<tr>
					<td style="text-align:right;font-size:20px;"><a href="<?php echo base_url('uploads/SLCMS/Prospectus1718.pdf');?>" title="Download Your Exam Manual" target=_blank>
					Exam Manual
					<img src="<?php echo base_url(); ?>uploads/SLCMS/pdf-icon.png" alt="logo" style="height:25px;">
					
					</a></td>
				</tr>
				<tr height=15></tr>
				<tr>
					<td style="text-align:right;font-size:20px;"><a href="<?php echo base_url('uploads/SLCMS/Prospectus1718.pdf');?>" title="Download Online Form" target=_blank>
					Important Information To Fill The Online Form
					<img src="<?php echo base_url(); ?>uploads/SLCMS/pdf-icon.png" alt="logo" style="height:25px;"></a></td>
				</tr>
				<tr height=15></tr>
				<tr>
					<td style="text-align:right;font-size:20px;"><a href="<?php echo base_url('uploads/SLCMS/Prospectus1718.pdf');?>" title="Eligibility Crieteria" target=_blank>Eligibility Crieteria
					<img src="<?php echo base_url(); ?>uploads/SLCMS/pdf-icon.png" alt="logo" style="height:25px;"></a></td>	
				</tr>
			<table>
		<td>
	</tr>
	
</table>


<div>
<?php $this->load->view('template/footer'); ?>
</div>
</body>
</html>

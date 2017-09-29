<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Welcome </title>
	 <link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
       <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/helpdoc.css">
	<style>
		table,th, td{
   		 border: 0px solid black;
	}
	</style>
</head>
<body>


<div>
	<div id="body">
	<?php $this->load->view('template/header'); ?>
	<nav> 	<h1>Welcome  </h1></nav>
	<?php if(isset($_SESSION)) {
        	echo $this->session->flashdata('flash_data');
    	} ?>
<br>
	<?php $this->load->view('enterence/enterence_head');?>
 	<br><br>

	<center>
<table>
            <tr colspan="2">
                <td>
                    <div align="left" style="margin-left:30px;width:1189px;">
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                   <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
                    <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                    <?php
                    };
                    ?>
                    <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                    <?php
                    };
                ?>
                </div>
            </td>
     </tr>
</table>
    	<form action="<?= site_url('welcome') ?>" method="post">
        	<table>
		<tr><td>
        	<label for="username">Username</label>
		</td>
		<td>		
        	<input type="text" name="username" size="25%" />
		</td>
		</tr>
		<tr><td>
        	<label for="password">Password</label></td>
		<td>
        	<input type="password" name="password" size="25%" /></td></tr>
		<tr><td></td>
		<td>
        	<button type="submit" style="font-size:17px;width:40%;">Login</button>
		
		<a href="<?php echo site_url('Student/student_step0');?>" style="text-decoration:none;" title="Click to open student detail form">
		<input type="button" value="New Student" style="font-size:17px;width:58%;"></a></td></tr>
		<tr><td></td>
                <td><a href="<?php echo site_url('forgotpassword/forgotpass');?>" style="text-decoration:none;" title="Forgot Password">
                <input type="button" value="Forgot Password" style="font-size:17px;width:100%;"></a></td>
		</table>
    	</form>
</center>


	</div>
<div class="content" width="80" >
        <div class="sideleft">
        <div id="cssmenu" style="margin-top:30px;">
                <ul>

<li><a href="ADMISSION NOTIFICATION">ADMISSION NOTIFICATON</a></li>
<?php 
$cdate = date('Y-m-d H:i:s');
foreach($this->prgcat as $pname){
	$pid = $pname->prgcat_id;
	$selectfield=array('admop_prgname_branch');
	$data=array(
      		'admop_prgcat' => $pid,
      		'admop_lastdate >=' => $cdate,
       	);
	$progid = $this->commodel-> get_listspficemore('admissionopen',$selectfield,$data);
//	print_r($progid);
?>
         <li class='has-sub'><a href=""><?php echo $pname->prgcat_name;?></a>
                        <ul>
			<?php foreach($progid as $row){
					$id = $row->admop_prgname_branch;
					$pname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$id)->prg_name;
					//$progid = $this->commodel->get_listspfic1('program','prg_id','prg_category',$pid)->prg_id;
				?>
                        		<li><a href="<?php echo site_url('welcome/ginstruction/');echo $id;?>"><?php echo $pname ."(".$this->commodel->get_listspfic1('program','prg_branch','prg_id',$id)->prg_branch .")" ;?></a></li>
					<input type="hidden" value="<?php echo $id;?>" name="prgid">
			<?php }?>
                        </ul>
<?php }?>
	</li>
</ul>
                </div>
        </div>
</div>

<br></br>
<br></br>
<br>
<br></br>
<br></br>
<br></br>

<?php $this->load->view('template/footer'); ?>
	<!--<p class="footer">Page rendered in <strong>{elapsed_time}</strong> seconds. <?php echo  (ENVIRONMENT === 'development') ?  'CodeIgniter Version <strong>' . CI_VERSION . '</strong>' : '' ?></p>-->
</body>
</html>

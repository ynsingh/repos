<?php
defined('BASEPATH') OR exit('No direct script access allowed');
if(isset($this->session->userdata['firstName'])){
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Annant Gyan</title>
	
		<link href="<?php echo base_url('assets/css');?>/style.css" rel="stylesheet">
		<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<!--<link href="<?php //echo base_url('assets/css');?>/bootstrap.min1.css" rel="stylesheet">-->
	  	<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
      		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	


<script type="text/javascript">
	
!function($,n,e){var o=$();$.fn.dropdownHover=function(e){return"ontouchstart"in document?this:(o=o.add(this.parent()),this.each(function(){function t(e){o.find(":focus").blur(),h.instantlyCloseOthers===!0&&o.removeClass("open"),n.clearTimeout(c),i.addClass("open"),r.trigger(a)}var r=$(this),i=r.parent(),d={delay:100,instantlyCloseOthers:!0},s={delay:$(this).data("delay"),instantlyCloseOthers:$(this).data("close-others")},a="show.bs.dropdown",u="hide.bs.dropdown",h=$.extend(!0,{},d,e,s),c;i.hover(function(n){return i.hasClass("open")||r.is(n.target)?void t(n):!0},function(){c=n.setTimeout(function(){i.removeClass("open"),r.trigger(u)},h.delay)}),r.hover(function(n){return i.hasClass("open")||i.is(n.target)?void t(n):!0}),i.find(".dropdown-submenu").each(function(){var e=$(this),o;e.hover(function(){n.clearTimeout(o),e.children(".dropdown-menu").show(),e.siblings().children(".dropdown-menu").hide()},function(){var t=e.children(".dropdown-menu");o=n.setTimeout(function(){t.hide()},h.delay)})})}))},$(document).ready(function(){$('[data-hover="dropdown"]').dropdownHover()})}(jQuery,this);
</script>
	
</head>
<body>

<div class="fluid-container">
	<div class="row">
		<div class="col-md-12">
			<img src="<?php echo base_url('images');?>/logo.png" class="img-responsive center-block">
		</div>
	</div>
	
	<div class="row">
			<?php $this->load->view('template/admin_header.php'); ?>

	</div>	

</div>	

<div class="container">  
<?php echo validation_errors('<div class="alert-warning"  style="font-size: 18px;" align=left>','</div>');?>
        <?php echo form_error('<div class="">','</div>');?>
        <?php 
        if(!empty($_SESSION['success'])){   
        if(isset($_SESSION['success'])){?>
         <div class="alert alert-success" style="font-size: 18px;"><?php echo $_SESSION['success'];?></div>
         <?php
          } };
         ?>
    
        <?php 
        if(!empty($_SESSION['error'])){
        if(isset($_SESSION['error'])){?>
             <div class="alert alert-danger" style="font-size: 18px;"><?php echo $_SESSION['error'];?></div>
        <?php
        };
    }   
    ?>  
</div>  

<div class="container">
	<center><h2>View Student Answer Copy</h2></center>
	<div class="col-md-12" id='card'>
		 <form name="frm" id="frmid" action="<?php echo site_url('admin/anscopy')?>" method="POST" enctype="multipart/form-data">
		
		<table style="font-size:18px;"><tr>
	<!--		<td><a href="<?php //echo site_url('admin/viewexam');?>">View Exam</a></td><td> &nbsp;&nbsp;&nbsp;</td>  -->
			<?php
				echo "<td>".$this->commodel->get_listspfic1('courses','cou_name','cou_id',$cid)->cou_name." </td>";
				echo "<td>&nbsp;&nbsp;&nbsp; </td>";
				echo "<td>".$this->commodel->get_listspfic1('test','testname','testid',$tid)->testname;
				echo "  (  ".$this->commodel->get_listspfic1('test','testcode','testid',$tid)->testcode." ) </td>";
				echo "<td>&nbsp;&nbsp;&nbsp; </td>";
				echo "<td> ".$this->commodel->get_listspfic1('sign_up','su_name','su_id',$sid)->su_name."</td>";
				
			?>
		</tr></table>
		<table class="table table-bordered">
			<thead style="font-size: 18px;">
				<tr  class="info">
					<th>Sr. No.</th><th>Question</th><th>Student Answer</th><th>Correct Answer</th><th>Marks</th> <!--<th> Actions</th> -->
				</tr>
			</thead>
			<tbody>
				<?php 
				$i=1;
				$smarks=0;
				$correctques=0;
						if(!empty($quest_data)){
							foreach($quest_data as $row){
						?>
				<tr>
								<td><?php echo $i++;?></td>
								<td><?php echo $this->commodel->get_listspfic1('question','question','qid',$row->quid)->question;?></td>
								<td><?php 
								$stans =$row->stdanswer;
								echo $stans;
								echo "<br>";
								echo $this->commodel->get_listspfic1('question',$stans,'qid',$row->quid)->$stans;
								?></td>
								<?php $correctans= $this->commodel->get_listspfic1('question','correctanswer','qid',$row->quid)->correctanswer   ;?>
								<td><?php echo $correctans;
								echo "<br>";
								echo $this->commodel->get_listspfic1('question',$correctans,'qid',$row->quid)->$correctans;
								?>
								</td>
								<td><?php 
									if($row->stdanswer == $correctans){
										$mark= $this->commodel->get_listspfic1('question','marks','qid',$row->quid)->marks;
										$smarks=$smarks+$mark;
										$correctques++;
										echo $mark;
									}else{
										echo 0;
									}
								?></td>
							<!--	<td><?php 
									//send values in hidden format - suid, testid, crsid,marks, correctquestion 
									//echo anchor('admin/delete_quest/' . $row->qid , "Delete", array('title' => 'Delete Details' , 'class' => 'red-link','onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";
								?> 
								</td>-->

								
				</tr>
				<?php 		}
						
				?>
				<tr><td colspan=6 align="center">

				<input type="hidden" name="cid" value="<?php echo $cid;?>" >
			        <input type="hidden" name="tid" value="<?php echo $tid;?>" >
				<input type="hidden" name="suid" value="<?php echo $sid;?>" >
				<input type="hidden" name="smarks" value="<?php echo $smarks;?>" >
			        <input type="hidden" name="correctans" value="<?php echo $correctques;?>" >

				<input type="submit" name="verifyans" class="btn btn-success submit" value="Verify">
				</td></tr>
				<?php	}	else{ ?>
							<tr>
							<td colspan=10 align=center> No Records found</td>
							</tr>
					<?php
						} ?>

				
			</tbody>
		</table>
	</form>
	</div>
	
</div>
</br></br></br></br>
<?php $this->load->view('template/footer.php');?>
</body>
</html>
<?php }else{
$this->load->view('admin/admin_login');
}
?>

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

 <script>
	$(document).ready(function(){
		/*  */
		 $('#couid').on('change',function(){
                	var ccode = $(this).val();
	                //alert(sc_code);
        	        if(ccode == ''){
                	    $('#stuname').prop('disabled',true);
                   
	                }	
        	        else{
                	    $('#stuname').prop('disabled',false);
	                    $.ajax({
        	                url: "<?php echo base_url();?>index.php/jslist/getcstulist",
                	        type: "POST",
                        	data: {"scrsid" : ccode},
	                        dataType:"html",
        	                success:function(data){
//                	            alert("data==1="+data);
                        	    $('#stuname').html(data.replace(/^"|"$/g, ''));
                                                 
	                        },
        	                error:function(data){
                	            //alert("data in error==="+data);
                        	    alert("error occur..!!");
                 
	                        }
        	            });
                	}
		 }); 
		 /* */
	    });
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
	<center><h2>View Student Exam List</h2></center>
	<div class="col-md-12" id='card'>
		
<!--		
		<table style="font-size:18px;"><tr>
			<td><a href="<?php //echo site_url('admin/upload_fileview');?>">View Upload Content</a></td>
			<td> &nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><a href="<?php //echo site_url('admin/createexam');?>">Create Exam</a></td>
		</tr></table>
-->
		<form name="frm" id="frmid" action="<?php echo site_url('admin/displayresadm')?>" method="POST" enctype="multipart/form-data">
			<table style="font-size:18px;"><tr><td>
                        <label for="crsname" class="cols-sm-2 control-label">Course Name</label>
<!--                        <br> -->
                        <select name="couname"  id="couid" style="width:200px;" >
                                <option value="" selected="" disabled="">Select Course Name</option>
                                <?php if(!empty($coudata)){
                                        foreach($coudata as $row){
                                ?>
                                        <option value="<?php echo $row->cou_id;?>"><?php echo $row->cou_name ."( ".$row->cou_code ." )";?></option>
                                <?php }}?>
			</select>
			</td><td>
				&nbsp;
			</td><td>
                         <label for="stuname" class="cols-sm-2 control-label">Student Name</label>
<!--                        <br>-->
                        <select name="stuname"  id="stuname" style="width:200px;" >
                                <option value="" selected="" disabled="">Select Student Name</option>
			</select>
			</td><td>
				&nbsp;
			</td><td>
<!--			<br> -->
			<input type="submit" name="testres" class="btn btn-success submit" value="Submit">	
			</td></tr></table>
		</form>
		<table style="font-size:18px;"><tr>
		</tr></table>

		<?php	
				if(!empty($subid)){	
					echo 	"<table><thead style=\"font-size: 18px;\"><tr> <th><b>".
						$this->commodel->get_listspfic1('sign_up','su_name','su_id',$stuid)->su_name.
						"</b></th><th>&nbsp;&nbsp;&nbsp;&nbsp;</th><th><b>".
						$this->commodel->get_listspfic1('sign_up','su_emailid','su_id',$stuid)->su_emailid.
						"</b></th><th>&nbsp;&nbsp;&nbsp;&nbsp;</th><th><b>".
						$this->commodel->get_listspfic1('courses','cou_name','cou_id',$subid)->cou_name. 
						"</b></th></tr></thead></table>"; 
				}
		?>
		<table class="table table-bordered">
			<thead style="font-size: 18px;">
				<tr  class="info">
					<th>Sr. No.</th><th>Test Name</th>
					<th>Max Marks</th>
					<th>Marks Obtained</th>
					<th>Exam Status </th>
				</tr>
			</thead>
			<tbody>
				<?php 
						$i=1; $totm=0;$per =0;$tmm=0;
				//		print_r($studata);
						if(!empty($studata)){
							foreach($studata as $row){	
						//		print_r($row);
							
						?>
				<tr>
					
								<td><?php echo $i++;?></td>
								<td><?php echo $row['testname'] ." ( ". $row['testcode'] ." )";?></td>
								<td><?php echo $row['maxmarks'];?></td>
								<td><?php echo $row['stmarks'];?></td>
								<td><?php echo $row['stattemp']; ?></td>
								<?php 
								$totm = $totm + $row['stmarks'];
								$tmm = $tmm + $row['maxmarks'];
								?> 
							
								
				</tr>
<?php 		}
						//get the max marks
						// get the  percentage
						$per = ($totm * 100)/$tmm;
						echo	"<tr><td>Total Marks :</td><td>".$totm."</td><td> Percentage : </td><td>".$per ."%</td></tr>";
						}
						else{ ?>
							<tr>
							<td colspan=10 align=center> No Records found</td>
							</tr>
					<?php
						} ?>

				
			</tbody>
		</table>
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

<!-- @name editauthuser.php  @author Neha Khullar(nehukhullar@gmail.com)  -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head> 
	<title>Update Authority Details</title>
	<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	   
        <?php $this->load->view('template/header'); ?>
            <!--h1>Welcome <?//= $this->session->userdata('username') ?>  </h1-->
        <?php // $this->load->view('template/menu');?> 
	 <!--link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/stylecal.css"-->
 	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
</head>
<body>
<script>
$(document).ready(function(){
$("#StartDate").datepicker({
dateFormat: 'yy/mm/dd',
numberOfMonths: 1,
onSelect: function(selected) {
$("#EndDate").datepicker("option","minDate", selected)
}
});
$("#EndDate").datepicker({ 
dateFormat: 'yy/mm/dd',
numberOfMonths: 1,
onSelect: function(selected) {
$("#StartDate").datepicker("option","maxDate", selected)
}
}); 
});
</script>
	<script>
        function goBack() {
        window.history.back();
        }
        </script>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
        <table width="100%">
            <tr>
	 <?php
                    echo "<td align=\"center\" width=\"100%\">";
                    echo "<b>Update User with Authority Details</b>";
                    echo "</td>";
            ?>
     </tr>
</table>
	    <table width="100%">
            <tr><td>
                <div>
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?>
                    <?php if(isset($_SESSION['success'])){?>
                       <div  class="isa_success"><?php echo $_SESSION['success'];?></div>
                    <?php
                    };
                    ?>
                    <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                    <?php
                    };
                    ?>
         </div>
       </td></tr>
        </table>
     <table>
      <form action="<?php echo site_url('map/editauthuser/' .$id);?>" method="POST" class="form-inline">
           <tr>
           <td>Authority Name</td>
                <td>
                   <?php echo form_input($authority_id); ?>

               </td>
                 </td>
            </tr>
            <tr>
                <td>User Name</td>
                <td>
                  <?php echo form_input($user_id); ?>
                </td>
                <td><?php echo form_error('user_id')?></td>
            </tr>

               

 <tr>
   <td><label for="map_date" style="font-size:16px;" class="control-label">Map Date<font color='Red'>*</font></label></td>
   <td><input type="text" name="map_date" id="StartDate" value=<?php echo $map_date['value'];?> class="form-control" size="40" required="required" /><br>
   <td><?php echo form_error('map_date')?></td>
   </td>
   </tr>
   <tr>
   <td><label for="till_date" style="font-size:16px;" class="control-label">Till Date<font color='Red'>*</font></label></td>
   <td><input type="text" name="till_date" id="EndDate"  value=<?php echo $till_date['value'];?> class="form-control" size="40" required="required" /><br>
   <td><?php echo form_error('till_date')?></td>
   </td>
   </tr>




               <tr>
                <td>Authority Type</td>
                <td>
                  <?php echo form_input($authority_type); ?>
                </td>
                <td><?php echo form_error('authority_type')?></td>
            </tr>

                </td>
                <td><?php echo form_error('id')?></td>
               </td>
            </tr>
               <tr>
               <td></td>
                <td colspan="2">
                <button name="editauthuser" >Update</button>
            <?php echo form_hidden('id', $id);?>
        </form>
                 <?php echo "<button onclick=\"goBack()\" >Back</button>";?>
                </td>
            </tr>
        </table>
    </body>
    <div align="center"><?php $this->load->view('template/footer');?></div>
</html>




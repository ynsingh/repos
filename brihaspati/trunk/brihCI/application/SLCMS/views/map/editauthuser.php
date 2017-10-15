<!-- @name editauthuser.php  @author Neha Khullar(nehukhullar@gmail.com)  -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?> 


 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/stylecal.css">
 	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>

<script>$(document).ready(function(){
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






    </head>
    <body>
        <script>
        function goBack() {
        window.history.back();
        }
        </script>

        <table width="100%">
            <tr><td>
                <div style="margin-left:2%;">
                    <?php echo validation_errors('<div style="margin-left:2%;" class="isa_warning">','</div>');?>
                    <?php echo form_error('<div style="margin-left:2%;" class="isa_error">','</div>');?>
                    <?php if(isset($_SESSION['success'])){?>
                       <div style="margin-left:2%;" class="isa_success"><?php echo $_SESSION['success'];?></div>
                    <?php
                    };
                    ?>
                    <?php if(isset($_SESSION['err_message'])){?>
                    div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                    <?php
                    };
                    ?>
         </div> </br>
         </td></tr>
        </table>
    <!--<table style="margin-left:50px;">
    <tr><td align="left"> Edit Campus Program seat</td></tr> 
    </table><br/>-->
     <table style="margin-left:2%;">
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
   <td><label for="map_date" class="control-label">Map Date</label></td>
   <td><input type="text" name="map_date" id="StartDate" value=<?php echo $map_date['value'];?> class="form-control" size="40" /><br>
   <td><?php echo form_error('map_date')?></td>
   </td>
   </tr>
   <tr>
   <td><label for="till_date" class="control-label">Till Date</label></td>
   <td><input type="text" name="till_date" id="EndDate"  value=<?php echo $till_date['value'];?> class="form-control" size="40" /><br>
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
               <td></td>
                <td colspan="2">
                <button name="editauthuser" >Update</button>
                 <?php echo "<button onclick=\"goBack()\" >Back</button>";?>
                </td>
            </tr>
            <?php echo form_hidden('id', $id);?>
        </form>
        </table>
    </body>
    <div align="center"><?php $this->load->view('template/footer');?></div>
</html>




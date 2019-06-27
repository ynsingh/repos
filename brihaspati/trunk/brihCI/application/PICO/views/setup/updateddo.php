 <!--@name updateddo.php
    @author Om Prakash(omprakashkgp@gmail.com)
	and Modification according to TANUVAS in Dec-2017
 -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
   <head>    
        <?php $this->load->view('template/header'); ?>
           
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
   </head>
   <body>

	<script>
           function goBack() {
        	window.history.back();
        }
	</script>
    <script>
        function getdepartment(val){
                var val=val;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>sisindex.php/map/getdeptlist",
                data: {"campusname" : val},
                dataType:"html",
                success: function(data){
                $('#deptname').html(data.replace(/^"|"$/g, ''));
                }
             });
           }
 
        function getschemename(val){
                var val=val;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>sisindex.php/map/getdeptschemelist",
                data: {"deptid" : val},
                dataType:"html",
                success: function(data){
                $('#schemecode').html(data.replace(/^"|"$/g, ''));
                }
             });
           }
    </script>

   <table width="100%">
     <tr colspan="2"><td>
	  <?php
 		echo anchor('setup/listddo/', 'List of DDO', array('class' =>'top_parent'));
	  ?>
	   <div align='left' style='margin-left:0%;width:95%;'>
          <?php echo validation_errors('<div class="isa_warning">','</div>');?>
             <?php echo form_error('<div class="isa_error">','</div>');?>
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
           </td></tr>
     </table>
     <table>
     <form action="<?php echo site_url('setup/updateddo/' . $ddo_id);?>" method="POST" class="form-inline">
       <tr>
             <td> Campus Name </td>
             <td>
                <!-- <?php echo form_input($campusname); ?>-->
             <select required name="campusname" id="campusname" class="my_dropdown" style="width:300px;" onchange="getdepartment(this.value)">
             <option value="<?php echo $this->common_model->get_listspfic1('study_center', 'sc_id', 'sc_name', $campusname["value"])->sc_id ?>" ><?php echo $campusname["value"] ?> </option>
                <?php foreach($this->scresult as $dataspt): ?>
                <option value="<?php echo $dataspt->sc_id; ?>"><?php echo $dataspt->sc_name; ?></option>
                <?php endforeach; ?>
             </td>
       </tr>
             <td> Department Name</td>
             <td>
               <!--  <?php //echo form_input($deptname); ?> -->
               <select name="deptname" id="deptname" class="my_dropdown" style="width:300px;" onchange="getschemename(this.value)" >
               <option value="<?php echo $this->common_model->get_listspfic1('Department', 'dept_id', 'dept_name', $deptname["value"])->dept_id; ?>" > <?php echo $deptname["value"]?> </option>
             </td>
       </tr>
       <tr>
             <td> Scheme Name </td>
             <td>
                <!-- <?php //echo form_input($schemecode); ?>-->
               <select name="schemecode" id="schemecode" class="my_dropdown" style="width:300px;" >
               <option value="<?php echo $this->SIS_model->get_listspfic1('scheme_department', 'sd_id', 'sd_name', $schemecode["value"])->sd_id; ?>" ><?php echo $schemecode["value"]?></option>
             </td>
       </tr>  
             <td> DDO Code </td>
             <td>
                 <?php echo form_input($ddocode); ?>
             </td>
       </tr>
       <tr>	
             <td> DDO Name </td>
             <td>
                 <?php echo form_input($ddoname); ?>
             </td>
       </tr>
       <tr>
             <td> Remark </td>
             <td>
                <?php echo form_input($remark); ?>
             </td>
       </tr>
       <tr>  <td></td>
             <td>
	     <button name "submit" >Update</button>
       </form>
	     <?php echo "<button onclick=\"goBack()\" >Back</button>";?>
	     </td>	
       </tr>
	     <?php echo form_hidden( 'ddo_id', $ddo_id );?>
   </table>
   </body>
<p>&nbsp;</p>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
 </html>
 

 <!--@name sc.php
    @author Rekha Devi Pal (rekha20july@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
 <head>


     <?php $this->load->view('template/header'); ?>
     <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
     <?php $this->load->view('template/menu');?>

			          <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/stylecal.css">
                                  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.css">
      				  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-1.12.4.js" ></script>
      				  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
                                 <script>
                                $( function() {
                                $( "#datepicker" ).datepicker({dateFormat: 'yy/mm/dd'});
                                } );
                                </script>
                              <script>
                                $( function() {
                                $( "#datepicker1" ).datepicker({dateFormat: 'yy/mm/dd'});
                                } );
                              </script>
 <body>
     <table>
            <tr colspan="2"><td>
                <div align="left" style="margin-left:40px;">
                <?php echo anchor('setup/viewsc/', "View Study Center Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
                <div  style="width:2000px;">
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
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

            <tr>
                <div style="margin-left:30px;">
                <br/>
                    
                <form action="<?php echo site_url('setup/sc');?>" method="POST" class="form-inline">
                <table style="margin-left:30Px;">
                          <tr><td>
                        Choose your University:</td><td>
                        <select name="orgprofile" style="width:100%">
                        <option value=""disabled selected>---------Select university---------</option>
                        <?php foreach($this->uresult as $datas): ?>
                       <option value="<?php echo $datas->org_code; ?>"><?php echo $datas->org_name; ?></option>
                        <?php endforeach; ?>
                        </select>

	         	</td></tr>

                               <tr><td>
                                <tr>
                                <td><label for="institutecode" class="control-label">Campus Code:</label></td>
                                <td><input type="text" name="institutecode"  class="form-control" size="26" /><br></td>
                                <td><?php echo form_error('institutecode')?></td>
                                <td>
                                 Example: CU001,CU002,etc
                                </td>
                                </tr>

                                <tr>
                                <td><label for="name" class="control-label"> Campus Name:</label></td>
                                <td><input type="text" name="name"  class="form-control" size="26" /><br></td>
                                <td><?php echo form_error('name')?></td>
                                <td>
                                 Example: Regional Campus, Manipur, etc
                                </td>
 
                                </tr>
    
                                <tr>
                                <td><label for="nickname" class="control-label">Campus Nick Name:</label></td>
                                <td><input type="text" name="nickname"  class="form-control" size="26" /><br></td>
                                <td><?php echo form_error('nickname')?></td>
                                 <td>
                                 Example: IGNTU
                                </td>
   				</tr>
                                 
                               <tr>
                                <td><label for="address" class="control-label">Address:</label></td>
                                <td><input type="text" name="address"  class="form-control" size="26" /><br></td>
                                <td><?php echo form_error('address')?></td>
                                </tr>
                               <tr>
                                <td><label for="country" class="control-label">Country:</label></td>
                                <td><input type="text" name="country"  class="form-control" size="26" /><br></td>
                                <td><?php echo form_error('country')?></td>
                              <tr>
                                <td><label for="state" class="control-label">State:</label></td>
                                <td><input type="text" name="state"  class="form-control" size="26" /><br></td>
                                <td><?php echo form_error('state')?></td>
                                 
                                <tr>
                                <td><label for="city" class="control-label">City:</label></td>
                                <td><input type="text" name="city"  class="form-control" size="26" /><br></td>
                                <td><?php echo form_error('city')?></td>
                                </tr>
                                 
                                <tr>
                                <td><label for="district" class="control-label">District:</label></td>
                                <td><input type="text" name="district"  class="form-control" size="26" /><br></td>
                                <td><?php echo form_error('district')?></td>
                                                       
                              	<tr>
                                <td><label for="pincode" class="control-label">Pincode:</label></td>
                                <td><input type="text" name="pincode"  class="form-control" size="26" /><br></td>
                                <td><?php echo form_error('pincode')?></td>

				<tr>
                                <td><label for="phone" class="control-label">Phone:</label></td>
                                <td><input type="text" name="phone"  class="form-control" size="26" /><br></td>
                                <td><?php echo form_error('phone')?></td>
                                </tr>
             
				<tr>
                                <td><label for="fax" class="control-label">Fax:</label></td>
                                <td><input type="text" name="fax"  class="form-control" size="26" /><br></td>
                                <td><?php echo form_error('fax')?></td>
                                </tr>
 
				<tr>
                                <td><label for="status" class="control-label">Status:</label></td>
                                <td><input type="text" name="status"  class="form-control" size="26" /><br></td>
                                <td><?php echo form_error('status')?></td>
                                 <td>
                                 Example: Active
                                </td>

                                </tr> 
				
                                <tr>
                                <td><label for="startdate" class="control-label">Start Date:</label></td>
                                <td><input type="text" name="startdate" id="datepicker" class="form-control" size="26" /><br>
                                <td><?php echo form_error('startdate')?></td>
	                        </td>
                                </tr>

                                <tr>
                                <td><label for="closedate" class="control-label">Close Date:</label></td>
                                <td><input type="text" name="closedate" id="datepicker1" class="form-control" size="26" /><br>
                                <td><?php echo form_error('closedate')?></td>
                                </td>
                                </tr>

                                <tr>
                                <td><label for="website" class="control-label">Website:</label></td>
                                <td><input type="text" name="website"  class="form-control" size="26" /><br></td>
                                <td><?php echo form_error('website')?><td>
                                </tr>
 
				<tr>
                                <td><label for="incharge" class="control-label">Incharge:</label></td>
                                <td><input type="text" name="incharge"  class="form-control" size="26" /><br></td>
                                <td><?php echo form_error('incharge')?></td>
                                  <td>
                                 Example: Incharge Name
                                </td>
                                </tr>

				<tr>
                                <td><label for="mobile" class="control-label">Mobile:</label></td>
                                <td><input type="text" name="mobile"  class="form-control" size="26" /><br></td>
                                <td><?php echo form_error('mobile')?></td>
                                </tr>
                                
                                    <tr>
                                    <td colspan="2" style="margin-left:30px;">
					 <button name="sc" style="margin-left:175px;" name="submit" >Submit</button>
					 <button name="reset" >Reset</button>
					 </td>
                                      </tr>
				</body>
			</html>
		</table>
<div>
<?php $this->load->view('template/footer'); ?>
</div>

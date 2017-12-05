 <!--@name sc.php
    @author Rekha Devi Pal (rekha20july@gmail.com)
	abhay(kumar.abhay.4187@gmail.com) =>repopluate
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
 <head>
                                                 
                                  <!--link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/stylecal.css"-->
                                  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.css">
      				  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-1.12.4.js" ></script>
                                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
      				  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
          	                  <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>

<script>
$(document).ready(function(){
$("#StartDate").datepicker({
onSelect: function(value, ui) {
  console.log(ui.selectedYear)
  var today = new Date(), 
  dob = new Date(value), 
  age = 2017-ui.selectedYear;
  //$("#age").text(age);
                                },
                                //(set for show current month or current date)maxDate: '+0d',
                                
  changeMonth: true,
  changeYear: true,
  dateFormat: 'yy-mm-dd',
 // defaultDate: '1yr',
  yearRange: 'c-47:c+50',
});

$("#EndDate").datepicker({ 
onSelect: function(value, ui) {
 console.log(ui.selectedYear)
var today = new Date(), 
dob = new Date(value), 
age = 2017-ui.selectedYear;

//$("#age").text(age);
},
                                //(set for show current month or current date)maxDate: '+0d',
changeMonth: true,
changeYear: true,
dateFormat: 'yy-mm-dd',
//defaultDate: '1yr',
yearRange: 'c-47:c+50',
});
});
</script>

</head> 
 <body>
 <div id="body">
        <?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu'); ?>
</div>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>


     <table width="100%">
            <tr><td>
                <div>
                <?php echo anchor('setup/viewsc/', "View Study Center Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));
                echo "<td align=\"right\">";
                $help_uri = site_url()."/help/helpdoc#StudyCenter";
                echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                echo "</td>";
                ?>
                <div> 
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
                <div>
                <form action="<?php echo site_url('setup/sc');?>" method="POST" class="form-inline">
                 <table>
                          <tr><td>
                        Choose your University:</td><td>
                        <select name="orgprofile" style="width:70%">
                        <option value=""disabled selected>---------Select university---------</option>
                        <?php foreach($this->uresult as $datas): ?>
                       <option value="<?php echo $datas->org_code; ?>"><?php echo $datas->org_name; ?></option>
                        <?php endforeach; ?>
                        </select>

	         	</td></tr>
                 <tr>
             <td><label for="institutecode" class="control-label">Campus Code:</label></td>
             <td><input type="text" name="institutecode"  class="form-control" size="26"  value="<?php echo isset($_POST["institutecode"]) ? $_POST["institutecode"] : ''; ?>" /><br></td>
           <td><?php echo form_error('institutecode')?></td>
              <td>
								
                  Example: CU001,CU002,etc
                                </td>
                                </tr>

                                <tr>
                                <td><label for="name" class="control-label"> Campus Name:</label></td>
                                <td><input type="text" name="name"  class="form-control" size="26"  value="<?php echo isset($_POST["name"]) ? $_POST["name"] : ''; ?>" /><br></td>
                                <td><?php echo form_error('name')?></td>
                                <td>
                                 Example: Regional Campus, Manipur, etc
                                </td>
 
                                </tr>
    
                                <tr>
                                <td><label for="nickname" class="control-label">Campus Nick Name:</label></td>
                                <td><input type="text" name="nickname"  class="form-control" size="26"  value="<?php echo isset($_POST["nickname"]) ? $_POST["nickname"] : ''; ?>" /><br></td>
                                <td><?php echo form_error('nickname')?></td>
                                 <td>
                                 Example: IGNTU
                                </td>
   				</tr>
                                 
                               <tr>
                                <td><label for="address" class="control-label">Address:</label></td>
                                <td><input type="text" name="address"  class="form-control" size="26" value="<?php echo isset($_POST["address"]) ? $_POST["address"] : ''; ?>" /><br></td>
                                <td><?php echo form_error('address')?></td>
                                </tr>
			        <tr><td><label class="control-label">Country:</label></td> <td>
					<select name="country"  id="country_id">
					<option value="">Select Country</option>
					<?php foreach($this->cresult as $datas): ?>
                                	<option value="<?php echo $datas->id; ?>"><?php echo $datas->name; ?></option>
                        		<?php endforeach; ?>
				</select>
                                 
		
                                <tr>
                                <td><label class="control-label">State:</label></td> 
                                <td>                        
				<select style="height:35px;" name="state" id="stname" disabled="">
					<option value="">Select state</option>
				</select>
                                </td></tr>
                                 
                                <tr>
                                <td><label class="control-label">City:</label></td>                              
                                <td>
				<select style="height:35px;" name="city" id="citname" disabled="">
                                    <option value="">Select city</option>
                                </select>
                                 </tr></td>
                                                                                   
                                <tr>
                                <td><label for="district" class="control-label">District:</label></td>
                                <td><input type="text" name="district"  class="form-control" size="26"  value="<?php echo isset($_POST["district"]) ? $_POST["district"] : ''; ?>"  /><br>
								</td>
                                
												
								                                                      
                              	<tr>
                                <td><label for="pincode" class="control-label">Pincode:</label></td>		
                                <td><input type="text" name="pincode"  class="form-control" size="26"  value="<?php echo isset($_POST["pincode"]) ? $_POST["pincode"] : ''; ?>" /></td><br>
                                <td><?php echo form_error('pincode')?></td>
                                </tr>

				<tr>   
                                <td><label for="phone" class="control-label">Phone:</label></td>
                                <td><input type="text" name="phone"  class="form-control" size="26"  value="<?php echo isset($_POST["phone"]) ? $_POST["phone"] : ''; ?> "/><br></td>
                                <td><?php echo form_error('phone')?></td>
                                </tr>
             
				<tr>
                                <td><label for="fax" class="control-label">Fax:</label></td>
                                <td><input type="text" name="fax"  class="form-control" size="26"  value="<?php echo isset($_POST["fax"]) ? $_POST["fax"] : ''; ?>" /><br></td>
                                <td><?php echo form_error('fax')?></td>
                                </tr>
 
				<tr>
                                <td><label for="status" class="control-label">Status:</label></td>
                                <td><input type="text" name="status"  class="form-control" size="26"  value="<?php echo isset($_POST["status"]) ? $_POST["status"] : ''; ?>"/><br></td>
                                <td><?php echo form_error('status')?></td>
                                 <td>
                                 Example: Active
                                </td>

                                </tr> 
				
                                <tr>
                                <td><label for="startdate" class="control-label">Start Date:</label></td>
                                <td><input type="text" name="startdate" id="StartDate" class="form-control" size="26"  value="<?php echo isset($_POST["startdate"]) ? $_POST["startdate"] : ''; ?>"/><br>
			
		                        </td>
                                </tr>

                                <tr>
                                <td><label for="closedate" class="control-label">Close Date:</label></td>
                                <td><input type="text" name="closedate" id="EndDate" class="form-control" size="26"  value="<?php echo isset($_POST["closedate"]) ? $_POST["closedate"] : ''; ?>"/><br>
                                </td>
                                <td><?php echo form_error('closedate')?></td>
                                </tr>


                                <tr>
                                <td><label for="website" class="control-label">Website:</label></td>
                                <td><input type="text" name="website"  class="form-control" size="26"  value="<?php echo isset($_POST["website"]) ? $_POST["website"] : ''; ?>"/><br></td>
                                <td><?php echo form_error('website')?></td>
                                <td>
                                 Example: http://www.igntu.nic.in
                                </td>

                                </tr>
 
				<tr>
                                <td><label for="incharge" class="control-label">Incharge:</label></td>
                                <td><input type="text" name="incharge"  class="form-control" size="26"  value="<?php echo isset($_POST["incharge"]) ? $_POST["incharge"] : ''; ?>"/><br></td>
                                <td><?php echo form_error('incharge')?></td>
                                  <td>
                                 Example: Incharge Name
                                </td>
                                </tr>

				<tr>
                                <td><label for="mobile" class="control-label">Mobile:</label></td>
                                <td><input type="text" name="mobile"  class="form-control" size="26"  value="<?php echo isset($_POST["mobile"]) ? $_POST["mobile"] : ''; ?>"/><br></td>
                                <td><?php echo form_error('mobile')?></td>
                                </tr>
                                
                                    <tr>
                                    <td></td>
                                    <td colspan="2">
					 <button name="sc"  name="submit" >Submit</button>
					 <input type="reset" name="Reset" value="Clear"/>
					 </td>
                                      </tr>
		</table></div>
<p><br></p>
<div>
<?php $this->load->view('template/footer'); ?>
</div>
				</body>
			</html>

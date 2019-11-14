<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name editsc.php 
  @author Rekha Devi Pal(rekha20july@gmail.com)
 -->

<html>
  <head>    
    <title>Edit Department</title>
        <?php $this->load->view('template/header'); ?>
        <!--h1>Welcome <?//= $this->session->userdata('username') ?>  </h1-->
        <?php // $this->load->view('template/menu');?>


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
//  defaultDate: '1yr',
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

       $('#country_id').on('change',function(){
           var cid = $(this).val();
           if(cid == ''){
               $('#stname').prop('disabled',true);
               
           }
           else{
                 $('#stname').prop('disabled',false); 
               $.ajax({
                   url: "<?php echo base_url();?>slcmsindex.php/setup/get_state",
                   type: "POST",
                   data: {"cid" : cid},
                   dataType:"html",
                   success:function(data){
                      $('#stname').html(data.replace(/^"|"$/g, ''));
                       
                   },
                   error:function(data){
                       
                   }
               });
           }
       }); 


$('#stname').on('change',function(){
           var sid = $(this).val();
           if(sid == ''){
               $('#citname').prop('disabled',true);
               
           }
           else{
                 $('#citname').prop('disabled',false); 
               $.ajax({
                   url: "<?php echo base_url();?>slcmsindex.php/setup/get_city",
                   type: "POST",
                   data: {"sid" : sid},
                   dataType:"html",
                   success:function(data){
                      $('#citname').html(data.replace(/^"|"$/g, ''));
                       
                   },
                   error:function(data){
                       
                   }
               });
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
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
<table width="100%">
            <tr>
	     <?php
                    echo "<td align=\"center\" width=\"100%\">";
                    echo "<b>Update Study Center Details</b>";
                    echo "</td>";
            ?>
	<tr>
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
                        <div  class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                    <?php
                    };
                    ?>
                </div>
            </td>
	  </tr>
        </table>
        <table>
        <?php

           echo form_open('setup/editsc/' . $id);
                echo "<tr>";
                echo "<td>";
                echo form_label('University Name', 'orgprofile');
                echo "</td>";
                echo "<td>";
                echo form_input($orgprofile);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Campus Code', 'institutecode');
                echo "</td>";
                echo "<td>";
                echo form_input($institutecode);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

               echo "<tr>";
                echo "<td>";
                echo form_label('Campus Name', 'name');
                echo "</td>";
                echo "<td>";
                echo form_input($name);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Campus Nickname', 'nickname');
                echo "</td>";
                echo "<td>";
                echo form_input($nickname);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                 echo form_label('Address', 'address');
                echo "</td>";
                echo "<td>";
                echo form_input($address);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";
                
		?>
               
               <tr>
               <td><label class="control-label">Country:</label></td>
               <td>
                <select name="country"  id="country_id" style="width:100%">
		<?php //if();?>
               <option value="<?php echo $country['value'];?>"><?php echo$this->common_model->get_listspfic1('countries','name','id',$country['value'])->name ;?></option>;
                <?php foreach($this->cresult as $datas): ?>
                <option value="<?php echo $datas->id; ?>"><?php echo $datas->name; ?></option>
                <?php endforeach; ?>
                </select>
  		<tr><td><label class="control-label">State:</label></td><td>
                <select style="height:35px;width:100%" name="state" id="stname" disabled="">
                
                <option value="<?php echo $state['value'];?>"><?php echo$this->common_model->get_listspfic1('states','name','id',$state['value'])->name ;?></option>;
                </select>
                </tr></td>
                <tr><td><label class="control-label">City:</label></td><td>
	        <select style="height:35px;width:100%" name="city" id="citname" disabled="" >
                 <option value="<?php echo $city['value'];?>"><?php echo$this->common_model->get_listspfic1('cities','name','id',$city['value'])->name ;?></option>;
                </select>
                </tr></td>
               

		<?php
                echo "<tr>";
                echo "<td>";
                echo form_label('District', 'district');
                echo "</td>";
                echo "<td>";
                echo form_input($district);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Pincode', 'pincode');
                echo "</td>";
                echo "<td>";
                echo form_input($pincode);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";
                
                echo "<tr>";
                echo "<td>";
                echo form_label('Phone', 'phone');
                echo "</td>";
                echo "<td>";
                echo form_input($phone);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Fax', 'fax');
                echo "</td>";
                echo "<td>";
                echo form_input($fax);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";
               
                echo "<tr>";
                echo "<td>";
                echo form_label('Status', 'status');
                echo "</td>";
                echo "<td>";
                echo form_input($status);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";
                 
          ?>

 <tr>
   <td><label for="startdate" class="control-label">Start Date:</label></td>
   <td><input type="text" name="startdate" id="StartDate" value=<?php echo $startdate['value'];?> class="form-control" size="40" /><br>
   <td><?php echo form_error('startdate')?></td>
   </td>
   </tr>
   <tr>
   <td><label for="closedate" class="control-label">Close Date:</label></td>
   <td><input type="text" name="closedate" id="EndDate"  value=<?php echo $closedate['value'];?> class="form-control" size="40" /><br>
   <td><?php echo form_error('closedate')?></td>
   </td>
   </tr> 


<?php

                echo "<tr>";
                echo "<td>";
                echo form_label('Website', 'website');
                echo "</td>";
                echo "<td>";
                echo form_input($website);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Incharge', 'incharge');
                echo "</td>";
                echo "<td>";
                echo form_input($incharge);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";
                                  
                echo "<tr>";
                echo "<td>";
                echo form_label('Mobile', 'mobile');
                echo "</td>";
                echo "<td>";
                echo form_input($mobile);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

            
                echo "<tr>";
                echo "<td>";
                echo "</td>";
                echo "<td>";
                echo form_hidden('id', $id);
                echo form_submit('submit', 'Update');
                echo form_close();
                echo "<button onclick=\"goBack()\" >Back</button>";
                echo "</td>";
                echo "</tr>";
        ?>
        </table>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>


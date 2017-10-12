<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name addexamcenter.php 
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->

<html>
<title>Add Exam Center</title>
 <head>
     <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
     <?php $this->load->view('template/header'); ?>
     <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
     <?php $this->load->view('template/menu');?>
 </head>
 <body>
<script>
    $(document).ready(function(){
    $('#state_id').on('change',function(){
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
<script>
function calculate() {
		var myBox1 = document.getElementById('box1').value;	
		var myBox2 = document.getElementById('box2').value;
		var result = document.getElementById('result');	
		var myResult = myBox1 * myBox2;
		result.value = myResult;
	
	}
</script>
 <table width= "100%">
            <tr><td>
                <div align="left" style="margin-left:1%">
                <?php echo anchor('enterenceadmin/examcenter','Exam Center List',array('title'=>'View Detail','class' => 'top_parent'  ));
                //$help_uri = site_url()."/help/helpdoc#ProgramFees";
                //echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:68%\">Click for Help</b></a>";
                ?>
</tr></td>

                <div>
                <tr><td>
                <div align="left" style="margin-left:2%;width:90%;">

                <?php
                     echo validation_errors('<div class="isa_warning">','</div>');
                     echo form_error('<div style="margin-left:2%;" class="isa_error">','</div>');

                     if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                 <?php
                     };
                     if(isset($_SESSION['err_message'])){?>
                        <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>

                 <?php
                    };
                 ?>
                </div>
            </td></tr>
</table>
 <div>
    <form action="<?php echo site_url('enterenceadmin/addexamcenter');?>" method="POST" class="form-inline">
            <table style="margin-left:2%;">
            <tr>
                <td><label for="eec_code" class="control-label">Entrance Exam Center Code:</label></td>
                <td>
                <input type="text" name="eec_code"  class="form-control" size="30" /><br>
                </td>
            </tr>
 		<tr>
                <td><label for="eec_name" class="control-label">Entrance Exam Center Name:</label></td>
                <td>
                <input type="text" name="eec_name"  class="form-control" size="30" /><br>
                </td>
            </tr>
 		 <tr>
                 <td><label for="eec_address" class="control-label">Entrance Exam Center Address:</label></td>
                 <td><textarea rows= "" cols="44" name="eec_address" size="50" > </textarea></td>
                 </tr>
		<tr><td>State: </td><td>
                <select name="eec_state"  id="state_id">
                <option value="">Select State</option>
                <?php foreach($this->cresult as $datas): ?>
                <option value="<?php echo $datas->id; ?>"><?php echo $datas->name; ?></option>
                <?php endforeach; ?>
                </select>
                <tr><td>City: </td><td>
                <select style="height:35px;" name="eec_city" id="citname" disabled="">
                <option value="">Select city</option>
                </select>
		<tr>
                <td><label for="eec_incharge" class="control-label">Entrance Exam Center Incharge:</label></td>
                <td>
                <input type="text" name="eec_incharge"  class="form-control" size="30" /><br>
                </td>
            </tr>
		<tr>
                <td><label for="eec_noofroom" class="control-label">Entrance Exam Center Number of Room:</label></td>
                <td>
                <input id="box1" type="text" name="eec_noofroom"  class="form-control" size="30" oninput="calculate()" /><br>
                </td>
            </tr>
		<tr>
                <td><label for="eec_capacityinroom" class="control-label">Entrance Exam Center Capacity in Room:</label></td>
                <td>
                <input id="box2" type="text" name="eec_capacityinroom"  class="form-control" size="30" oninput="calculate()"/><br>
                </td>
            </tr>
		<tr>
                <td><label for="eec_totalcapacity" class="control-label">Entrance Exam Center Total Capacity:</label></td>
                <td>
                <input id="result" type="text" name="eec_totalcapacity"  class="form-control" size="30" readonly/><br>
                </td>
            </tr>
		<tr>
                <td><label for="eec_contactno" class="control-label">Entrance Exam Center Contact No:</label></td>
                <td>
                <input type="text" name="eec_contactno"  class="form-control" size="30" /><br>
                </td>
            </tr>
		<tr>
                <td><label for="eec_contactemail" class="control-label">Entrance Exam Center Contact Email:</label></td>
                <td>
                <input type="text" name="eec_contactemail"  class="form-control" size="30" /><br>
                </td>
            </tr>
		 <tr>
                <td></td><td>
                <button name="addexamcenter" >Add Exam Center</button>
                <button name="reset" >Clear</button>
                </td>
           </tr>
           </table>
    </form>
    </div>
    </tr>
    </table>
    </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>


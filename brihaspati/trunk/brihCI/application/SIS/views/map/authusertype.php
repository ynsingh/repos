<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name authusertype.php 
  @author Neha Khullar(nehukhullar@gmail.com)
 -->
<html>
<title>Add Authorities</title>
<head>
<title>Authorities</title>       
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/stylecal.css">
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
  defaultDate: '1yr',
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
defaultDate: '1yr',
yearRange: 'c-47:c+50',
});
});
</script>
<!--script>
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
</script-->

  <div id="body">
        <?php $this->load->view('template/header'); ?>
                <!--h1>Welcome <?= $this->session->userdata('username') ?>  </h1-->
        <?php $this->load->view('template/menu'); ?>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
</div>
<table width="100%">
                <tr colspan="2"><td>
                <?php echo anchor('map/viewauthuser',' Map Authority and User List',array('title'=>'View Detail','class' => 'top_parent'  ));
                //$help_uri = site_url()."/help/helpdoc#ProgramFees";
                //echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:72%\">Click for Help</b></a>";
                ?>
                <div>
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                    <?php
                    };
                    ?>

                </div>
            </td></tr>
</table>
     <form action="<?php echo site_url('map/authusertype');?>" method="POST" class="form-inline">
                <table>
                        <td> Authority Name: </td><td>
                        <select name="authorities" class="my_dropdown" style="width:100%;">
                        <option value=""disabled selected>---------Select authority ---------</option>
                        <?php foreach($this->authuserresult as $datas): ?>
                        <option value="<?php echo $datas->id;?>"><?php echo $datas->name; ?></option>
                        <?php endforeach; ?>
                        </select>
                        </td></tr>
                        <tr>
                                                
                        <td> User Name: </td><td>
                        <select name="edrpuser" class="my_dropdown" style="width:100%;">
                        <option value=""disabled selected>---------Select Name ---------</option>                        
                        <?php foreach($this->result as $datas): 
				$fnme=$this->loginmodel->get_listspfic1('userprofile', 'firstname', 'userid', $datas->id)->firstname ;
				$lmne=$this->loginmodel->get_listspfic1('userprofile', 'lastname', 'userid', $datas->id)->lastname;
				$nme=$fnme." " .$lmne;
				if(empty($nme)){
				$nme =$datas->username;
				}
				
			?>
                        <option value="<?php echo $datas->id; ?>"><?php echo $nme; ?></option>
                        <?php endforeach; ?>
                        </select>
                        </td></tr>
                        <tr>

			<tr>
                        <td> Authority Type: </td><td>
                        <select name="authority_type" style="width:100%;">
                        <option value=""disabled selected>----Select Type----</option>
			<option value="Full Time" class="dropdown-item">Full Time</option>
                        <option value="Acting" class="dropdown-item">Acting</option>
                        </select>
                        </td></tr>
                         <tr>
                        
                        <td><label>From Date:<font color='Red'>*</font></label></td>
                        <td><input type="text"placeholder="From Date" name="map_date" id="StartDate"  size="27" value="<?php echo isset($_POST["map_date"]) ? $_POST["map_date"] : ''; ?>" required="required"/><br> </td>
                        </tr>
                        <tr>

                        
                        
                        <td><label>Till Date:<font color='Red'>*</font></label></td>
                        <td><input type="text"placeholder="Till Date" name="till_date" id="EndDate"  size="27" value="<?php echo isset($_POST["map_date"]) ? $_POST["till_date"] : ''; ?>" required="required"/><br> </td>
                        </tr>
                        <tr>


                        <tr>
			<td></td>
                        <td colspan="2">
                        <button name="authusertype">Add Authorities </button>
			<input type="reset" name="Reset" value="Clear"/>
                        </td>
                        </tr>
                    </form>
                  </div>
            </tr>
        </table>
<p><br></p>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</head>
</html>





































                                      
                     








































                                                                                                                   
 


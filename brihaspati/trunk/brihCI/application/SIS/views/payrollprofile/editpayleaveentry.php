
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        <table width="100%"><tr><td>
        <?php
		$roleid=$this->session->userdata('id_role');
        	$uname=$this->session->userdata('username');
	        if(($roleid == 1)||($uname == 'rsection@tanuvas.org.in')){
        	        echo anchor('payrollprofile/viewpayleaveentry', "View Leave Entry" ,array('title' => 'View staff Leave Entry ' , 'class' => 'top_parent'));
        	}
            
            ?>
        </td></tr>
        </table>
        <div align="left" width="100%">
            
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                
	        <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>
                    <div  class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>    
                  
        </div>
              
<?php
		//set the month array
                    $cmonth= date('M');

                    $formattedMonthArray = array(
                         "1" => "Jan", "2" => "Feb", "3" => "Mar", "4" => "Apr",
                         "5" => "May", "6" => "Jun", "7" => "Jul", "8" => "Aug",
                        "9" => "Sep", "10" => "Oct", "11" => "Nov", "12" => "Dec",
                    );

                    // set start and end year range
                    $cyear= date("Y");
                    $yearArray = range(2015,  $cyear);
?>
 
            <?php echo form_open_multipart('payrollprofile/editpayleaveentry/'.$id);?>
		<table  width="100%" class="TFtable">
                <tr><thead><th style="background-color:#2a8fcf;text-align:left;height:40px;" colspan="4">&nbsp;&nbsp;Edit Payroll Leave Entry</th></thead></tr>
                </table>
                <table  width="100%" class="TFtable" >
<!--                        <tr><td colspan="5"><b><span style="color:#0099CC;">Personal Details</span></b></td></tr>-->
                        <tr>
                            <td> <b>Employee Name:</b><br><input type="text" id="empname" value="<?php echo $empname ; ?>" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Campus Name:</b><br><input type="text"  id="campus" value="<?php echo $empcamp ; ?>"  style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>UO:</b><br><input type="text"  id="uo" value="<?php echo $empuo ; ?>" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Department:</b><br><input type="text" id="dept" value="<?php echo $empdept ; ?>"style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                        </tr>
                        <tr>
                             <td> <b>Scheme:</b><br><input type="text" id="schm" value="<?php echo $empsch." ( ".$empschcode." )" ; ?>" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>DDO:</b><br><input type="text"  id="ddo" value="<?php echo $empddo ; ?>" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Working Type:</b><br><input type="text" id="wtype" value="<?php echo $empwtype ; ?>" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                            <td> <b>Designation:</b><br><input type="text" id="desig" value="<?php echo $empdesig ; ?>" style="text-decoration:none;border:0; word-break: break-all;width:300px;" readonly></td>
                        </tr>
                        <tr>
                            <td><b> PAL:</b><br><input type="text" name="pal" id="pal" value="<?php echo $emppal ; ?>" style="width:300px;"></td>
                            <td><b>EOL:</b><br><input type="text" name="eol" id="eol" value="<?php echo $empeol ; ?>" style="width:300px;"></td>
				<td><b>Select Month</b> <br>
                        <select name="month" id="month" style="width:300px;font-weight:bold;">
                            <option value="" disabled selected>--------Select Month ----------</option>
                            <?php
                                foreach ($formattedMonthArray as $month) {
                                    $selected = ($month == $cmonth) ? 'selected' : '';

                                    echo '<option  '.$selected.' value="'.$month.'">'.$month.'</option>';
                                }
				if(!empty($empmon)){
                            ?>
				
			    <option selected value="<?php echo $empmon ; ?>"><?php echo $empmon ; ?></option>
			<?php } ?>

                        </select>
                    </td>
                    <td><b>Select Year</b> <br>
                        <select name="year" id="year" style="width:300px;font-weight:bold;">
                            <option value="" disabled selected>--------Select Year ----------</option>
                            <?php 
                                foreach ($yearArray as $year) {
                                // if you want to select a particular year
                                $selected = ($year == $cyear) ? 'selected' : '';

                                echo '<option '.$selected.' value="'.$year.'">'.$year.'</option>';
                                }
				if(!empty($empyear)){
                            ?>
			    <option selected value="<?php echo $empyear ; ?>"><?php echo $empyear ; ?></option>
			<?php } ?>
                        </select>
                    </td>

                        </tr>
                        <tr>
                            <td colspan="6">   
                            <button name="epleaveent" id="epleaveent">Update</button>
                            <input type="reset" name="Reset" value="Clear"/>
                            </td>
                        </tr>
                    </table>
              <!--  </form> -->
            </body>
            <p>&nbsp;</p>
        <div><?php $this->load->view('template/footer'); ?></div>     
</html>    

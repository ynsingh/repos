    

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
                	echo anchor('payrollprofile/viewpaytransentry', "View Transfer Entry" ,array('title' => 'View staff Transfer Entry ' , 'class' => 'top_parent'));
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
 
                <table  width="100%" class="TFtable">
                <tr><thead><th style="background-color:#2a8fcf;text-align:left;height:40px;" colspan="4">&nbsp;&nbsp;Edit Payroll Transfer Entry</th></thead></tr>
                </table>
		<?php //print_r($hragrade); ?>
            <?php echo form_open_multipart('payrollprofile/editpaytransentry/'.$id);?>
                <table  width="100%" class="TFtable" >
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
                            <td><b>Days:</b><br><input type="text" name="days" id="days" value="<?php echo $empdays ; ?>" style="width:300px;"></td>
                            <td><b>HRA From:</b><br>
					<div> <select id="hrafrom" style="width:300px;" name="hrafrom" >
					<option selected="selected" disabled selected>--------HRA From-----</option>
					<?php foreach($hragrade as $camdata): ?>
						<option class="test" value="<?php echo $camdata->hg_gradeid; ?>"><?php echo $this->sismodel->get_listspfic1('hra_grade_city','hgc_gradename','hgc_id',$camdata->hg_gradeid)->hgc_gradename; ?></option>
                        		<?php endforeach; 
					if(!empty($emphrafrom)){
					?>
					<option selected value="<?php echo $emphrafrom ; ?>"><?php echo $emphrafrom ; ?></option>
		                        <?php } ?>

			
                    			</select></div>
			</td>
                        <td><b>HRA To:</b><br>
				<div> <select id="hrato" style="width:300px;" name="hrato" >
                                        <option selected="selected" disabled selected>--------HRA To-----</option>
                                        <?php foreach($hragrade as $camdata): ?>
                                                <option class="test" value="<?php echo $camdata->hg_gradeid; ?>"><?php echo $this->sismodel->get_listspfic1('hra_grade_city','hgc_gradename','hgc_id',$camdata->hg_gradeid)->hgc_gradename; ?></option>
                                        <?php endforeach; 
					if(!empty($emphrato)){
					?>
					<option selected value="<?php echo $emphrato ; ?>"><?php echo $emphrato ; ?></option>
		                        <?php } ?>

                                        </select></div>
			</td>
                            <td><b>Transit Days:</b><br><input type="text" name="transit" id="transit" value="<?php echo $emptransit ; ?>" style="width:300px;"></td>
                        </tr>
                        <tr>
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
			<td><b>CCA From:</b><br>
                                        <div> <select id="ccafrom" style="width:300px;" name="ccafrom" >
                                        <option selected="selected" disabled selected>--------CCA From-----</option>
                                        <?php foreach($ccagrade as $camdata): ?>
                                                <option class="test" value="<?php echo $camdata->cca_gradeid; ?>"><?php echo $this->sismodel->get_listspfic1('hra_grade_city','hgc_gradename','hgc_id',$camdata->cca_gradeid)->hgc_gradename; ?></option>
                                        <?php endforeach; 
					if(!empty($empccafrom)){
					?>
					<option selected value="<?php echo $empccafrom ; ?>"><?php echo $empccafrom ; ?></option>
		                        <?php } ?>

                                        </select></div>
                        </td>
                        <td><b>CCA To:</b><br>
                                <div> <select id="ccato" style="width:300px;" name="ccato" >
                                        <option selected="selected" disabled selected>--------CCA To-----</option>
                                        <?php foreach($ccagrade as $camdata): ?>
                                                <option class="test" value="<?php echo $camdata->cca_gradeid; ?>"><?php echo $this->sismodel->get_listspfic1('hra_grade_city','hgc_gradename','hgc_id',$candata->cca_gradeid)->hgc_gradename; ?></option>
                                        <?php endforeach; 
					if(!empty($empccato)){
					?>
					<option selected value="<?php echo $empccato ; ?>"><?php echo $empccato ; ?></option>
		                        <?php } ?>

                                        </select></div>
                        </td>

                        </tr>
                        <tr>
                            <td colspan="6">   
                            <button name="eptransent" id="ptransent">Update</button>
                            <input type="reset" name="Reset" value="Clear"/>
                            </td>
                        </tr>
                    </table>
              <!--  </form> -->
            </body>
            <p>&nbsp;</p>
        <div><?php $this->load->view('template/footer'); ?></div>     
</html>    


<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Unlock Salary Entry</title>
	<script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
<script>
/*
$(document).ready(function(){
		/****************************************** start of uofficer********************************/
/*                $('#wtype').on('change',function(){

            function verify(){
                var x=document.getElementById("wtype").value;
		var y=document.getElementById("uoff").value;
		var z=document.getElementById("strin").value;
		if(((x == 'null') && (y == 'null') && (z == 'null')) || ((x == '') && (y == '') && (z == ''))){
                //if((x == 'null' && y == 'null') || (x == '' && y == '')||(y == 'null')||(x == 'null')){
                    alert("Please use at least one option either select or type string for search !!");
                    return false;
                };


            }
*/
</script>
    <body>
            <?php $this->load->view('template/header'); ?>
           
        <table width="100%"><tr colspan="2">
        <?php 
        echo "<td align=\"left\" width=\"33%\">";
	$roleid=$this->session->userdata('id_role');
	$uname=$this->session->userdata('username');
        if(($roleid == 1)||($uname == 'rsection@tanuvas.org.in')||($roleid == 5)||($roleid == 14)){
//		echo anchor('payrollprofile/payleaveentry', "Add Leave Entry" ,array('title' => 'Add staff Leave Entry ' , 'class' => 'top_parent'));
	}
        echo "</td>";
        echo "<td align=\"center\" width=\"34%\">";
        echo "<b>Staff Salary Unlock Entry</b>";
        echo "</td>";
        echo "<td align=\"right\" width=\"33%\">";
	$help_uri = site_url()."/help/helpdoc#";
        echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
        echo "</td>";
        ?>
        <div>     
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
</tr>
        </table>
		<?php
                //set the month array
                $cmonth= date('M');
		$pmonth=date('M', strtotime('last month'));	

                //$formattedMonthArray = array(
                  //       "1" => "Jan", "2" => "Feb", "3" => "Mar", "4" => "Apr",
                    //     "5" => "May", "6" => "Jun", "7" => "Jul", "8" => "Aug",
                      //  "9" => "Sep", "10" => "Oct", "11" => "Nov", "12" => "Dec",
//                    );
                // set start and end year range
                $cyear= date("Y");
		$syear = $cyear ;			
		if($cmonth == "Jan"){
			$syear = $cyear-1 ;
		}
                $yearArray = range($syear,  $cyear); 
?>


<form action="<?php echo site_url('Setup3redesign/unlocksalary');?>" id="myForm" method="POST" class="form-inline">
          <table width="100%" border="0">
            <tr style="font-weight:bold;width:100%;">
		<td> Select Depratment<br>
                        <select name="dept" id="dept" style="width:200px;" required>
                            <?php
                                if(!empty($deptsel)){ ?>
                            <option value="<?php echo "";?>"><?php echo $deptsel;?></option>
<?php                           }else{ ?>
                            <option value="<?php echo "";?>"><?php echo "Please select department";?></option>
<?php                           }
                                foreach ($deptdata as $rec) {
                                    echo '<option  value="'.$rec->dept_id.'">'.$rec->dept_name.'</option>';
                                }
                            ?>
                        </select>

                   </td>

		 <td><b>Select Month</b> <br>
                        <select name="month" id="month" style="width:200px;font-weight:bold;">
                          <!--  <option value="" disabled selected>--------Select Month ----------</option> -->
                            <?php
                                    echo '<option selected value="'.$cmonth.'">'.$cmonth.'</option>';
                                    echo '<option  value="'.$pmonth .'">'.$pmonth.'</option>';
                               // foreach ($formattedMonthArray as $month) {
                                 //   $selected = ($month == $cmonth) ? 'selected' : '';

                                   // echo '<option  '.$selected.' value="'.$month.'">'.$month.'</option>';
                              //  }
				// if(!empty($cmon)){
                            ?>
<!--                            <option  value="<?php //echo $cmon ; ?>"><?php //echo $cmon ; ?></option> -->
			<?php // } ?>
                        </select>
                    </td>
                    <td><b>Select Year</b> <br>
                        <select name="year" id="year" style="width:200px;font-weight:bold;">
                            <option value="" disabled selected>--------Select Year ----------</option>
                            <?php
                                foreach ($yearArray as $year) {
                                // if you want to select a particular year
                                $selected = ($year == $cyear) ? 'selected' : '';

                                echo '<option '.$selected.' value="'.$year.'">'.$year.'</option>';
                                }
//				 if(!empty($cyer)){
                            ?>
<!--                            <option selected value="<?php //echo $cyer ; ?>"><?php //echo $cyer ; ?></option> -->
			<?php //} ?>
                        </select>
                    </td>

		<td>
			<br>
                    <input type="submit" name="unlocksal" id="unlocksal" value="Unlock Salary"/>
                </td>
            </tr>
        </table><br> 
        <div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
                <tr>
                    <th>Sr.No</th>
                    
                    <th>Department Name</th>
                    <th>Month</th>
                    <th>Year</th>
                    <th>Status</th>
                    <th>Action Date</th>
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;?>
              <?php if( count($ulksdata) ): 
		 ?>
                    <?php foreach($ulksdata as $record){ ?>
                        <tr>
<?php	//		print_r($record); 
?>
                            <td><?php echo $serial_no++; ?></td>
			    <td><?php 
                                    $dept=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->sld_deptid)->dept_name;
				      echo $dept;
                            ?>
                            </td>
                            <td><?php
				echo $record->sld_month;
                            ?></td>
                            <td>
                                <?php 
                                 echo $record->sld_year;
				?>
                            </td>
                            <td> <?php
				// echo $record->sld_status;
				if($record->sld_status == 'Y'){
					echo "Locked";
				}else{
					echo "Unlocked";
				}
		//		if(($roleid == 1)||($roleid == 14)){
                  //                      echo anchor("payrollprofile/deletepayleaves/{$record->sle_id}","Delete",array('title' => 'Delete Details' , 'class' => 'red-link'));
		//		}
                  //               if(($roleid == 1)||(($roleid == 5)&&($hdeptid == $record->sle_deptid ))||($roleid == 14)){
		//			echo " | ";
                  //                      echo anchor("payrollprofile/editpayleaveentry/{$record->sle_id}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link'));
                    //            }
                                ?></td>
                            <td>
                                <?php 
                                 echo $record->sld_creationdate;
				?>
                            </td>
                        </tr>
			<?php
			}//record dup end
			?>
                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
		</tbody>
        </table>
        </div><!------scroller div------>
		<br><br>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
        
    </body>    
</html>   

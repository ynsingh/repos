
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
	<script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
<script>
/*
$(document).ready(function(){
		/****************************************** start of uofficer********************************/
/*                $('#wtype').on('change',function(){
                    var workt = $(this).val();
                    //alert(sc_code);
                    if(workt == ''){
                        $('#uoff').prop('disabled',true);
                   
                    }
                    else{
                        $('#uoff').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/report/getspuolist",
                            type: "POST",
                            data: {"worktype" : workt},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#uoff').html(data.replace(/^"|"$/g, ''));
                                                 
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                }); 
                /******************************************end of uofficer********************************/

		/****************************************** start of deptarment********************************/
/*                $('#uoff').on('change',function(){
                    var wtcode = $('#wtype').val();
                    var uoid = $('#uoff').val();
                    //alert(sc_code);
                    var wrktypeuo = wtcode+","+uoid;
                    if(wtcode == ''){
                        $('#dept').prop('disabled',true);
                   
                    }
                    else{
                        $('#dept').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/report/getdeptlist",
                            type: "POST",
                            data: {"worktypeuo" : wrktypeuo},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#dept').html(data.replace(/^"|"$/g, ' '));
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                }); 
                /******************************************end of department********************************/
	/****************************************** start of designation*******************************/
/*                $('#dept').on('change',function(){
                    var wtcode = $('#wtype').val();
                    var uoid = $('#uoff').val();
                    var dept = $('#dept').val();
                    //alert(sc_code);
                    var wtuodept = wtcode+","+uoid+","+dept;
                    // alert(wtuodept);
                    if(dept == ''){
                        $('#post').prop('disabled',true);
                   
                    }
                    else{
                        $('#post').prop('disabled',false);
                        $.ajax({
                           // url: "<?php echo base_url();?>sisindex.php/staffmgmt/getcombdesiglist",
                            url: "<?php echo base_url();?>sisindex.php/report/getuodeptpostlist_sp",
                            type: "POST",
                            data: {"wtuodept" : wtuodept},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#post').html(data.replace(/^"|"$/g, ' '));
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                }); 

                /****************************************** start post********************************/
/*               // $('#wtype').on('change',function(){
                $('#dept').on('change',function(){
			var wtcode = $('#wtype').val();
                    var uoid = $('#uoff').val();
                    var dept = $('#dept').val();
                    //alert(sc_code);
                    var wtuodept = wtcode+","+uoid+","+dept;
                   // var workt = $(this).val();
                   //alert("post====="+workt);
                    if(dept == ''){
                        $('#desig').prop('disabled',true);

                    }
                    else{
                        $('#desig').prop('disabled',false);
                        $.ajax({
                            //url: "<?php echo base_url();?>sisindex.php/report/getdesiglist",
                            url: "<?php echo base_url();?>sisindex.php/report/getuodeptpostlist_sp",
                            type: "POST",
                            //data: {"worktype" : workt},
			    data: {"wtuodept" : wtuodept},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#desig').html(data.replace(/^"|"$/g, ''));

                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");

                            }
                        });
                    }
                });
            });

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
        if(($roleid == 1)||($uname == 'rsection@tanuvas.org.in')){
		echo anchor('payrollprofile/paytransentry', "Add Transfer Entry" ,array('title' => 'Add staff Transfer Entry ' , 'class' => 'top_parent'));
	}
        echo "</td>";
        echo "<td align=\"center\" width=\"34%\">";
        echo "<b>Staff Transfer Entry Payroll Profile Details</b>";
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

                    $formattedMonthArray = array(
                         "1" => "Jan", "2" => "Feb", "3" => "Mar", "4" => "Apr",
                         "5" => "May", "6" => "Jun", "7" => "Jul", "8" => "Aug",
                        "9" => "Sep", "10" => "Oct", "11" => "Nov", "12" => "Dec",
                    );

                    // set start and end year range
                    $cyear= date("Y");
                    $yearArray = range(2015,  $cyear);
?>

<form action="<?php //echo site_url('payrollprofile/viewpayleaveentry');?>" id="myForm" method="POST" class="form-inline">
          <table width="100%" border="0">
            <tr style="font-weight:bold;width:100%;">
		 <td><b>Select Month</b> <br>
                        <select name="month" id="month" style="width:300px;font-weight:bold;">
                            <option value="" disabled selected>--------Select Month ----------</option>
                            <?php
                                foreach ($formattedMonthArray as $month) {
                                    $selected = ($month == $cmonth) ? 'selected' : '';

                                    echo '<option  '.$selected.' value="'.$month.'">'.$month.'</option>';
                                }
                            ?>

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
                            ?>
                        </select>
                    </td>
		 <td>
                        <br>
                    <input type="submit" name="filter" id="crits" value="Search"/>
                </td>

            </tr>
        </table><br> 
        <div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
                <tr>
                    <th>Sr.No</th>
                    
                    <th>Employee Name</th>
                    <th>Days</th>
                    <th>HRA From</th>
                    <th>HRA To</th>
                    <th>CCA From</th>
                    <th>CCA To</th>
                    <th >Transit Days</th>
                    <th>Action</th>
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;?>
              <?php if( count($records) ): 
		 ?>
                    <?php foreach($records as $record){ ?>
                        <tr>
                            <td><?php echo $serial_no++; ?></td>
			    <td><?php 
                                    $ename=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$record->ste_empid)->emp_name;
					echo $ename;
                                    $ecode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$record->ste_empid)->emp_code;
                                    echo " ( "."PF No:".$ecode." )";
                                    $eddo=$this->sismodel->get_listspfic1('employee_master','emp_ddoid','emp_id',$record->ste_empid)->emp_ddoid;
			            $ddo=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$eddo)->ddo_name;
                                    echo "<br/> DDO:".$ddo;
                                    $edept=$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$record->ste_empid)->emp_dept_code;
				    $dept=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$edept)->dept_name;
                                    echo "<br/> Dept :".$dept;
                                    $eschid=$this->sismodel->get_listspfic1('employee_master','emp_schemeid','emp_id',$record->ste_empid)->emp_schemeid;
				    $schm=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$eschid)->sd_name;
                                    $schmcode=$this->sismodel->get_listspfic1('scheme_department','sd_code','sd_id',$eschid)->sd_code;
                                    echo "<br/> Scheme :".$schm . " ( ".$schmcode." )";
                            ?>
                            </td>
                            <td><?php
					echo $record->ste_days;
                            ?></td>
                            <td>
                                <?php 
                                 echo $record->ste_hrafrom;
				?>
                            </td>
                            <td>
                                <?php 
                                 echo $record->ste_hrato;
				?>
                            </td>
				<td>
                                <?php
                                 echo $record->ste_ccafrom;
                                ?>
                            </td>
                            <td>
                                <?php
                                 echo $record->ste_ccato;
                                ?>
                            </td>

                            <td>
                                <?php 
                                 echo $record->ste_transit;
				?>
                            </td>
                            <td> <?php
				if(($roleid == 1)){
					echo anchor("payrollprofile/deletepaytrans/{$record->ste_id}","Delete",array('title' => 'Delete Details' , 'class' => 'red-link'));
				}
                        //        if(($roleid == 1)||(($roleid == 5)&&($hdeptid == $record->emp_dept_code ))){
                               //         echo anchor("staffmgmt/editempprofile/{$record->emp_id}","View/Edit",array('title' => 'View/Edit Details' , 'class' => 'red-link'));
                          //      }
                                ?></td>
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


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
<!--
<form action="<?php //echo site_url('payrollprofile/viewpayleaveentry');?>" id="myForm" method="POST" class="form-inline">
          <table width="100%" border="0">
            <tr style="font-weight:bold;width:100%;">
                <td>  Select Working Type<br>
                    <select name="wtype" id="wtype" style="width:200px;">
				<?php //if  (!empty($this->wtyp)){ ?>
                        <option value="<?php //echo $this->wtyp; ?>" > <?php //echo $this->wtyp; ?></option>
                        <?php // }else{ ?>
                      <option value="" disabled selected>-- Select Working Type --</option>
                          <?php // } ?>
                      <option value="Teaching">Teaching</option>
                      <option value="Non Teaching"> Non Teaching</option>
                    </select>
		 </td>
               <td>  Select UO<br>
                    <select name="uoff" id="uoff" style="width:200px;">
                      <option value="" disabled selected>-- Select University officer--</option>
                    </select>
                </td>
                <td>  Select Department<br>
                    <select name="dept" id="dept" style="width:200px;">
                      <option value="" disabled selected>-- Select Department --</option>
                    </select>
                </td>
                <td>  Select Designation<br>
                    <select name="desig" id="desig" style="width:200px;">
                      <option value="" disabled selected>-- Select Designation --</option>
                    </select>
                </td>

                <td>  Select Shown Against Post<br>
                    <select name="post" id="post" style="width:200px;">
			<?php //if  ((!empty($this->desigm))&&($this->desigm != 'All')){ ?>
                        <option value="<?php //echo $this->desigm; ?>" > <?php //echo $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$this->desigm)->desig_name ." ( ". $this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$this->desigm)->desig_code ." )"; ?></option>
                        <?php // }else{ ?>
                      <option value="" disabled selected>-- Select Post --</option>
			 <?php  //} ?>
                    </select>
                </td>
		<td>
		 Search String<br>
                          <input type="text" name="strin" id="strin" style="width:100" placeholder="Enter String" value="<?php //echo isset($_POST["emp_name"]) ? $_POST["dept_name"] :  ''; ?>">

                    <input type="submit" name="filter" id="crits" value="Search"  onClick="return verify()"/>
                </td>
            </tr>
        </table><br> -->
        <div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
                <tr>
                    <th>Sr.No</th>
                    
                    <th>Employee Name</th>
                    <th>Days</th>
                    <th>HRA From</th>
                    <th>HRA To</th>
                    <th colspan=2>Transit</th>
<!--                    <th>Action</th>-->
                    
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
                                 echo $record->ste_transit;
				?>
                            </td>
                            <td> <?php
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

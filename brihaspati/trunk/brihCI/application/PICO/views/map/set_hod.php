<!--@name set_hod.php  @author Manorama Pal(palseema30@gmail.com)  -->

 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<//?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <script>
            $(document).ready(function(){
        
                $('#camp').on('change',function(){
                var sc_code = $(this).val();
                if(sc_code == ''){
                    $('#scid').prop('disabled',true);
                }
                else{
             
                    $('#scid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>picoindex.php/map/getdeptlist",
                        type: "POST",
                        data: {"campusname" : sc_code},
                        dataType:"html",
                        success:function(data){
                            $('#scid').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            alert("error occur..!!");
                        }
                    });
                }
            }); 
            
            /*****************get user list according to department************************/
            
            $('#scid').on('change',function(){
                var sc_code = $('#camp').val();
                var dept_code = $('#scid').val();
                var campdept = sc_code+","+dept_code;
                //alert(campdept);
                if(dept_code == ''){
                    $('#usrid').prop('disabled',true);
                }
                else{
             
                    $('#usrid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>picoindex.php/map/getempdetail",
                        type: "POST",
                        data: {"campdept" : campdept},
                        dataType:"html",
                        success:function(data){
           //                 alert("data==="+data);
                            $('#usrid').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            alert("error occur..!!");
                        }
                    });
                }
            }); 
            
            /*****************get user list according to department************************/
            var today = new Date();
            $('#Datefrom,#Dateto').datepicker({
                dateFormat: 'yy/mm/dd',
                autoclose:true,
                changeMonth: true,
                changeYear: true,
                yearRange: 'c-30:c+10',
               // endDate: "today",
                //maxDate: today
            }).on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });
        });
    </script>  
</head>    
    <body>
        <?php $this->load->view('template/header'); ?>
           
        <table width="100%"> 
            <tr><td>  
                <?php echo anchor('map/hodlist/', "HOD List ", array('title' => 'View Detail' , 'class' => 'top_parent'));?>
               <?php
                 echo "<td align=\"right\">";
                 $help_uri = site_url()."/help/helpdoc#hodlist";
                 echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                 echo "</td>";
                 ?>
                 <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                  <?php echo form_error('<div class="isa_error">','</div>');?>
                
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
            </td></tr>  
        </table>  
        <table>
            <form action="<?php echo site_url('map/sethod');?>" method="POST">
                <tr>
                    <td> Choose your Campus: <font color='Red'>*</font></td>
                    <td>
                        <select name="campus" id="camp" style="width:100%;" required="required">
                        <option value="">-------------Select Campus---------------</option>
                        <?php foreach($this->campus as $datas): ?>
                        <option value="<?php echo $datas->sc_id; ?>"><?php echo $datas->sc_name; ?></option>
                        <?php endforeach; ?>
                        </select>
                    </td>
                </tr>
		<tr>
                    <td>  Choose Your UO : <font color='Red'>*</font></td>
                    <td><select name="uo" id="uo" style="width:100%;" required="required">
                        <option value="">Select UO</option>
                         <?php foreach($this->uo as $udatas): ?>
                        <option value="<?php echo $udatas->ul_uocode; ?>"><?php echo $udatas->ul_uoname ." ( ". $udatas->ul_uocode." )"; ?></option>
                        <?php endforeach; ?>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>  Choose your Department: <font color='Red'>*</font></td>
                    <td>
                        <select name="deptname" id="scid" disabled="" style="width:100%;" required="required">
                        <option value="">select department</option>
                        </select>
                    </td>
                </tr>
                <tr>
                        
                    <td><label>PF No:</label></td>
                    <td>
                        <select name="usrname" id="usrid" disabled="" style="width:100%;">
                        <option value="">Select User</option>
                        </select>
                        <!--<input type="text"placeholder="PF No" name="pfno"  size="35" value="<?php //echo isset($_POST["pfno"]) ? $_POST["pfno"] : ''; ?>" required="required"/><br> -->
                    </td>
                </tr>
                <tr>
                        
                    <td><label>Email Id:<font color='Red'>*</font></label></td>
                    <td><input type="text"placeholder="Email Id" name="emailid"  size="45" value="<?php echo isset($_POST["emailid"]) ? $_POST["emailid"] : ''; ?>" required="required"/><br> </td>
                </tr>
                <tr>
                    <td><label for="Datefrom" style="font-size:15px;">Date From<font color='Red'></font></label></td>
                    <td><input type="text" name="DateFrom" id="Datefrom" value="<?php echo isset($_POST["DateFrom"]) ? $_POST["DateFrom"] : ''; ?>"  size="45" >
			<select name="jsession" style="width:140px;" id="jsession" required>
                                <option selected="selected" disabled selected>Select Session</option>
                                <option value="Forenoon">Forenoon</option>
                                <option value="Afternoon">Afternon</option>
                        </select>

                    </td>     
                         
                </tr>
                <tr>
                    <td><label for="Dateto" style="font-size:15px;">Date To<font color='Red'></font></label></td>
                    <td><input type="text" name="DateTo" id="Dateto" value="<?php echo isset($_POST["DateTo"]) ? $_POST["DateTo"] : ''; ?>"  size="45" >
			<select name="tsession" style="width:140px;" id="tsession" >
                                <option selected="selected" disabled selected>Select Session</option>
                                <option value="Forenoon">Forenoon</option>
                                <option value="Afternoon">Afternon</option>
                        </select>

                    </td>     
                         
                </tr>
                <tr>
                    <td>  Status: <font color='Red'>*</font></td>
                    <td>
                        <select name="status"  style="width:100%;" required="required">
                        <option value="">Select Status</option>
                        <option value="Fulltime">Fulltime</option>
                        <option value="Acting">Acting</option>
                        </select>
                    </td>
                </tr>
                <td></td>
                <td colspan="2">   
                    <button name="sethod">Submit</button>
                    <input type="reset" name="Reset" value="Clear"/>
                </td>
            
            </form>  
        </table>
     <p><br></p>
    </body> 
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

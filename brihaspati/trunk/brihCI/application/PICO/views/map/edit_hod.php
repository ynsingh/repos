
<!--@name edit_hod.php  @author Manorama Pal(palseema30@gmail.com) -->

 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Salary Head</title>
    <head>
        <?php $this->load->view('template/header'); ?>
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
                        url: "<?php echo base_url();?>sisindex.php/map/getdeptlist",
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
                        url: "<?php echo base_url();?>sisindex.php/map/getempdetail",
                        type: "POST",
                        data: {"campdept" : campdept},
                        dataType:"html",
                        success:function(data){
                        //    alert("data==="+data);
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
                yearRange: 'c-40:c+10',
               // endDate: "today",
                //maxDate: today
            }).on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });
        });
         function goBack() {
       // window.location="<?php echo site_url('setup3/salaryhead_list/');?>";
        window.history.back();
        }
    </script>  
    
    </head>
    <body>
    
        <table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo anchor('map/hodlist/', "HOD List" ,array('title' => 'HOD List ' , 'class' => 'top_parent'));
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Edit HOD Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";

                ?>
            </tr>
        </table>
        <table width="100%">
           <tr><td>
           <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                <?php
                if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
                    echo "<div  class=\"isa_success\">";
                    echo $_SESSION['success'];
                    echo "</div>";
                }
                if((isset($_SESSION['err_message'])) && (($_SESSION['err_message'])!='')){
                    echo "<div  class=\"isa_error\">";
                    echo $_SESSION['err_message'];
                    echo "</div>";
                }
                ;?>
            </div>
            </td></tr>
        </table>
        <div>
            <form id="myform" action="<?php echo site_url('map/edithod/'.$id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<?php echo  $id ; ?>">
            <table>
                <tr>
                    <td> Choose your Campus: <font color='Red'>*</font></td>
                    <td>
                        <select name="campus" id="camp" style="width:100%;" required="required">
                        <?php if(!empty($hoddata->hl_scid)):;?>
                        <option value="<?php echo $hoddata->hl_scid;?>">
                        <?php 
                            echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$hoddata->hl_scid)->sc_name;
                        ?>
                        </option>
                        <?php else:?>    
                        <option value="">-------------Select Campus---------------</option>
                        <?php endif;?>
                        <?php foreach($this->campus as $datas): ?>
                        <option value="<?php echo $datas->sc_id; ?>"><?php echo $datas->sc_name; ?></option>
                        <?php endforeach; ?>
                        </select>
                    </td>
                </tr>
		        <tr>
                    <td>  Choose Your UO : <font color='Red'>*</font></td>
                    <td><select name="uo" id="uo" style="width:100%;" required="required">
                        <?php if(!empty($hoddata->hl_uopid)):;?>
                        <option value="<?php echo $hoddata->hl_uopid;?>">
                        <?php
                            echo $this->loginmodel->get_listspfic1('authorities','name','priority',$hoddata->hl_uopid)->name;
                         ?>
                        </option>
                        <?php else:?>
                        <option value="">Select UO</option>
                        <?php endif;?>
			 <?php foreach($this->uo as $udatas): ?>
                        <option value="<?php echo $udatas->ul_uocode; ?>"><?php echo $udatas->ul_uoname  ." ( ". $udatas->ul_uocode." )"; ?></option>
                        <?php endforeach; ?>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>  Choose your Department: <font color='Red'>*</font></td>
                    <td>
                        <select name="deptname" id="scid" style="width:100%;" required="required">
                        <?php if(!empty($hoddata->hl_deptid)):;?>
                        <option value="<?php echo $hoddata->hl_deptid;?>"> 
                        <?php 
                            echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$hoddata->hl_deptid)->dept_name;
                         ?>
                        </option>
                        <?php else:?>    
                        <option value="">select department</option>
                        <?php endif;?>
                        </select>
                    </td>
                </tr>
                <tr>
                        
                    <td><label>PF No:</label></td>
                    <td>
                        <select name="usrname" id="usrid" style="width:100%;">
                        <?php if(!empty($hoddata->hl_empcode)):;?>
                        <option value="<?php echo $hoddata->hl_empcode;?>"> 
                        <?php 
                            echo $pfno=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_code',$hoddata->hl_empcode)->emp_name;
                        ?>
                        </option>
                        <?php else:?>     
                        <option value="">Select User</option>
                        <?php endif;?>
                        </select>
                        <!--<input type="text"placeholder="PF No" name="pfno"  size="35" value="<?php //echo isset($_POST["pfno"]) ? $_POST["pfno"] : ''; ?>" required="required"/><br> -->
                    </td>
                </tr>
                <tr>
                        
                    <td><label>Email Id:<font color='Red'>*</font></label></td>
                    <td><input type="text"placeholder="Email Id" name="emailid"  size="45" value="<?php echo $this->loginmodel->get_listspfic1('edrpuser','username','id',$hoddata->hl_userid)->username; ;?>"  readonly required="required"/><br> </td>
                </tr>
                <tr>
                    <td><label for="Datefrom" style="font-size:15px;">Date From<font color='Red'></font></label></td>
                    <td><input type="text" name="DateFrom" id="Datefrom" value="<?php echo $hoddata->hl_datefrom ;?>"   size="45" >
			<select name="jsession" style="width:140px;" id="jsession" required>
<?php				if(!empty($hoddata->hl_fromsession)){ ?>
                                <option value="<?php echo $hoddata->hl_fromsession;?>"><?php echo $hoddata->hl_fromsession;?></option>
<?php				} ?>
                                <option selected="selected" disabled selected>Select Session</option>
                                <option value="Forenoon">Forenoon</option>
                                <option value="Afternoon">Afternon</option>
                        </select>

                    </td>     
                         
                </tr>
                <tr>
                    <td><label for="Dateto" style="font-size:15px;">Date To<font color='Red'></font></label></td>
                    <td><input type="text" name="DateTo" id="Dateto"  value="<?php echo $hoddata->hl_dateto ;?>"   size="45" >
			<select name="tsession" style="width:140px;" id="tsession" >
<?php				if(!empty($hoddata->hl_tosession)){ ?>
                                <option value="<?php echo $hoddata->hl_tosession;?>"><?php echo $hoddata->hl_tosession;?></option>
<?php				} ?>
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
                        <?php if(!empty($hoddata->hl_status)):;?>
                        <option value="<?php echo $hoddata->hl_status;?>"> 
                        <?php 
                            echo $hoddata->hl_status;
                        ?>
                        </option>
                        <?php else:?>         
                            <option value="">Select Status</option>
                         <?php endif;?>    
                            <option value="Fulltime">Fulltime</option>
                            <option value="Acting">Acting</option>
                        </select>
                    </td>
                </tr>
                <td></td>
                <td colspan="2">   
                    <button name="edithod">Update</button>
                    <input type="reset" name="Reset" value="Clear"/>
                </td>
                        
            </table>
            </form>  
        </div>   
        
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>   
    </body>   
</html>    

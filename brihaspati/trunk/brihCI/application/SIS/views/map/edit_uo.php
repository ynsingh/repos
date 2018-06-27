<!--@name edit_uo.php  @author Shivani -->

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
            $(document).ready(function() {         
             $('#uo').on('change',function(){
                var sc_code = $('#camp').val();
                var uoc_id = $('#uo').val();
                var campuoc = sc_code+","+uoc_id;
                //alert("combid=="+combid);
                if(uoc_id == ''){
                    $('#scid').prop('disabled',true);
                }
                else{
             
                    $('#scid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getnewdeptlist",
                        type: "POST",
                        data: {"campuoc" : campuoc},
                        dataType:"html",
                        success:function(data){
                            
                            $('#scid').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            //alert("data in error==="+data);
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
            }); 
             
            /*****************get user list according to campus & department************************/
            
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
                yearRange: 'c-40:c+10',
               // endDate: "today",
                //maxDate: today
            }).on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });
/*****************************************validation for date of appiontment**************************************/
            $("#Dateto").on('change',function(){
                var dfrom = $('#Datefrom').val();
                var dto = $('#Dateto').val();
		 if(dto < dfrom )
                {
                    alert('Date to is less than Date from so please change it.');
                    var dto = ' ';
                    return $('#Dateto').val(dto);
                }
                                               
            });
		
        });
        
         function goBack() {
        window.history.back();
        }
    </script>  
    
    </head>
    <body>
    
        <table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo anchor('map/uolist/', "UO List" ,array('title' => 'UO List ' , 'class' => 'top_parent'));
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Edit UO Details</b>";
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
            <form id="myform" action="<?php echo site_url('map/edituo/'.$id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<?php echo  $id ; ?>">
            <table>
               <tr>
                    <td> Choose your Campus: </td>
                    <td>
                        <select name="campus" id="camp" style="width:100%;">
                        <?php if(!empty($uodata->hl_scid)):;?>
                        <option value="<?php echo $uodata->hl_scid;?>">
                        <?php 
                            echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$uodata->hl_scid)->sc_name;
                        ?>
                        </option>
                        <?php else:?>
                       <option selected="selected" disabled selected>------ Select Campus------</option>     
                        <!--option value="">-------------Select Campus---------------</option-->
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
                        <?php if(!empty($uodata->ul_authuoid)):;?>
                        <option value="<?php echo $uodata->ul_authuoid;?>">
                        <?php
                            echo $this->loginmodel->get_listspfic1('authorities','name','id',$uodata->ul_authuoid)->name;
                             echo " (Code: ";
                                      echo $this->loginmodel->get_listspfic1('authorities','code','id',$uodata->ul_authuoid)->code;
                                     // echo " ) ";
                                  echo " , Priority: ";
                                     echo $this->loginmodel->get_listspfic1('authorities','priority','id',$uodata->ul_authuoid)->priority;
                                      echo " ) ";
                                        ?></option>
			<?php else:?>
                       <option selected="selected" disabled selected>------ Select UO------</option>
                        <!--option value="">Select UO</option-->
                        <?php endif;?>
                        <?php foreach($this->authresult as $udatas): ?>
                        <option value="<?php echo $udatas->id; ?>"><?php echo $udatas->name ." (  Code: ". $udatas->code .", Priority: ".$udatas->priority." )"; 
                        ?></option>
			<?php endforeach; ?>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>  Choose your Department: <!--font color='Red'--></font></td>
                    <td>
                        <select name="deptname" id="scid" style="width:100%;">
                        <?php if(!empty($uodata->hl_deptid)):;?>
                        <option value="<?php echo $uodata->hl_deptid;?>"> 
                        <?php 
                            echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$uodata->hl_deptid)->dept_name;
                         ?>
                        </option>
                        <?php else:?> 
                       <option selected="selected" disabled selected>------ Select Department------</option>   
                        <!--option value="">select department</option-->
                        <?php endif;?>
                        </select>
                    </td>
                </tr>
                <tr>
                        
                    <td><label>PF No:</label></td>
                    <td>
                        <select name="usrname" id="usrid" style="width:100%;">
                        <?php if(!empty($uodata->ul_empcode)):;?>
          <!--get userid of this user--><?php //$userid=$this->loginmodel->get_listspfic1('edrpuser','id','username',$_POST['emailid'])->id; ?>
                        <option value="<?php echo $uodata->ul_empcode;?>"> 
                        <?php 
                            echo $this->sismodel->get_listspfic1('employee_master','emp_name','emp_code',$uodata->ul_empcode)->emp_name;
                             echo "(";
                          echo $uodata->ul_empcode;
                             echo")";
                        ?>
                        </option>
                        <?php else:?>
                        <option selected="selected" disabled selected>------ Select User------</option>     
                        <!--option value="">Select User</option-->
                        <?php endif;?>
                        </select>
                        <!--<input type="text"placeholder="PF No" name="pfno"  size="35" value="<?php //echo isset($_POST["pfno"]) ? $_POST["pfno"] : ''; ?>" required="required"/><br> -->
                    </td><td>(To select new pf, "again select the campus,uo & department")</td>
                </tr>
                <tr>
                        
                    <td><label>Email Id:<font color='Red'>*</font></label></td>
                    <td><input type="text"placeholder="Email Id" name="emailid"  size="45" value="<?php echo $this->loginmodel->get_listspfic1('edrpuser','username','id',$uodata->ul_userid)->username; ;?>" /><br> </td>
                </tr>
                <tr>
                    <td><label for="Datefrom" >Date From<font color='Red'></font></label></td>
                    <td><input type="text" name="DateFrom" id="Datefrom" value="<?php echo $uodata->ul_datefrom ;?>"   size="45" >
                    </td>     
                         
                </tr>
                <tr>
                    <td><label for="Dateto" >Date To<font color='Red'></font></label></td>
                    <td><input type="text" name="DateTo" id="Dateto"  value="<?php echo $uodata->ul_dateto ;?>"   size="45" >
                    </td>     
                         
                </tr>
                <tr>
                    <td>  Status: <font color='Red'>*</font></td>
                    <td>
                        <select name="status"  style="width:100%;" required="required">
                        <?php if(!empty($uodata->ul_status)):;?>
                        <option value="<?php echo $uodata->ul_status;?>"> 
                        <?php 
                            echo $uodata->ul_status;
                        ?>
                        </option>
                        <?php else:?>
                            <option selected="selected" disabled selected>------ Select Status------</option>         
                            <!--option value="">Select Status</option-->
                         <?php endif;?>    
                            <option value="Fulltime">Fulltime</option>
                            <option value="Acting">Acting</option>
                        </select>
                    </td>
                </tr>

                <td></td>
                <td colspan="2">   
                    <button name="edituo">Update</button>
                    <!--input type="reset" name="Reset" value="Clear"/-->
                     <button type="button" onclick="goBack();">Back</button>
                  </td>
                        
            </table>
            <!--/form-->  
        </div>   
        
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>   
    </body>   
</html>    



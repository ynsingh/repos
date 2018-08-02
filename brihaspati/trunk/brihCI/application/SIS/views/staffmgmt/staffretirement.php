<!--@filename staffretiement.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	
	 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>

        <script>
            $(document).ready(function(){
                /****************************************** start of uofficer********************************/
                $('#wtype').on('change',function(){
                    var workt = $(this).val();
                    //alert(sc_code);
                    if(workt == ''){
                        $('#uoff').prop('disabled',true);
                   
                    }
                    else{
                        $('#uoff').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/report/getuolist",
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
                $('#uoff').on('change',function(){
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
                $('#dept').on('change',function(){
                    var wtcode = $('#wtype').val();
                    var uoid = $('#uoff').val();
                    var dept = $('#dept').val();
                    //alert(sc_code);
                    var wtuodept = wtcode+","+uoid+","+dept;
                    // alert(wtuodept);
                    if(dept == ''){
                        $('#desig').prop('disabled',true);
                   
                    }
                    else{
                        $('#desig').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/staffmgmt/getcombdesiglist",
                            type: "POST",
                            data: {"wtuodept" : wtuodept},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#desig').html(data.replace(/^"|"$/g, ' '));
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                }); 
                /******************************************end of designation********************************/
                 /****************************************** start of employee list*******************************/
                $('#desig').on('change',function(){
                    var wtcode = $('#wtype').val();
                    var uoid = $('#uoff').val();
                    var dept = $('#dept').val();
                    var desigid = $('#desig').val();
                    var wtuodeptdesig = uoid+","+dept+","+wtcode+","+desigid;
                    if(desig == ''){
                        $('#emp').prop('disabled',true);
                   
                    }
                    else{
                        $('#emp').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/staffmgmt/getempnamelist2",
                            type: "POST",
                            data: {"uddempt" : wtuodeptdesig},
                            dataType:"html",
                            success:function(data){
                                var val=data.replace(/\"/g,"");;
                                $('#emp').html(data.replace(/^"|"$/g, ' '));
                                if(val.trim() === "novalue"){
                                    alert('Sorry, Employee list is not exists with selected  combination Try again with other combination ');
                                }
                            },
                            error:function(data){
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                }); 
                /******************************************end of employee list********************************/
           	var today = new Date();
            
            $('#Dateofaleaving').datepicker({
                dateFormat: 'yy/mm/dd',
                autoclose:true,
                changeMonth: true,
                changeYear: true,
                yearRange: 'c-70:c',
                endDate: "today",
                maxDate: today
            }).on('changeDate', function (ev) {
                $(this).datepicker('hide');
        });

	


            });
            function verify(){
                var x=document.getElementById("wtype").value;
                var y=document.getElementById("uoff").value;
                if((x == 'null' && y == 'null') || (x == '' && y == '')||(y == 'null')||(x == 'null')){
                    alert("please select at least any two combination for search !!");
                    return false;
                };
            }
            function verifyreason(){
                var x=document.getElementById("resret").value;
                var y=document.getElementById("Dateofaleaving").value;
                if((x == '')||(y == '')){
                    alert("either leaving option or date is not filled !!");
                    return false;
                };
            }
        </script>

    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
         <table width="100%"><tr colspan="2">
            <?php 
       
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
        </tr></table>
        <form action="<?php echo site_url('staffmgmt/staffretirement/'.$selempid);?>" id="myForm" method="POST" class="form-inline">
         <table width="100%" border="0">
            <tr style="font-weight:bold;">
                <td>  Select Type<br>
                    <select name="wtype" id="wtype" style="width:400px;"> 
                      <option value="" disabled selected>--------Select Working Type ----------</option>
                      <option value="Teaching">Teaching</option>
                      <option value="Non Teaching"> Non Teaching</option>
                       
                    </select> 
                                    
                </td> 
               <td>  Select UO<br>
                    <select name="uoff" id="uoff" style="width:400px;"> 
                      <option value="" disabled selected>-------- Select University officer------</option>  
                    </select> 
                </td> 
                <td>  Select Department<br>
                    <select name="dept" id="dept" style="width:400px;"> 
                      <option value="" disabled selected>-------- Select Department --------</option>  
                    </select> 
                    
                </td>
                </tr>
                <tr style="font-weight:bold;">
                <td> Select Designation<br>
                    <select name="desig" id="desig" style="width:400px;"> 
                      <option  value="" disabled selected>-------- Select Designation ------</option>  
                    </select> 
                    
                </td>
                <td> Select Employee  <br>
                    <select name="emp" id="emp" style="width:400px;"> 
                      <option selected="selected" disabled selected>---------Select Employee----------</option>  
                    </select> 
                    
                </td>
                <td><input type="submit" name="filter" id="crits" value="Search"  onClick="return verify()"/></td>
            </tr>
            </table>
            <?php if($search =='Search'):?>
            <div class="scroller_sub_page">
            <table  width="100%" class="TFtable">
            <tr>
            <thead>
                <tr>
                    <th></th>
                    <th>Employee Name</th>
                    <th>Details</th>
                    <th>Doj / Dor</th>
                    <th>Designation</th>
                    <th>E-Mail ID</th>
                    
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;?>
              <?php if( count($empret) ):  ?>
                    <?php foreach($empret as $record){ ?>
                        <tr>
                            <!--<td><//?php echo $serial_no++; ?></td>-->
                            <?php //$img=$record->emp_code;?>
                            <?php if(!empty($record->emp_photoname)):?>
                            <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/'.$record->emp_photoname);?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                            <?php else :?>
                            <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/empdemopic.png');?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                            <?php endif;?>
                            <td><?php echo $record->emp_name."<br/>" ."("."PF No:".$record->emp_code.")"; ?></td>
                            <td><?php
                                    $sc=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$record->emp_scid)->sc_name;
                                    $uo=$this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->emp_uocid)->name;
                                    $dept=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
                                    if(!empty($record->emp_schemeid)){
                                        $schme=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->emp_schemeid)->sd_name;
                                        
                                    }
                                    echo "<b>campus-: </b>".$sc."<br/> "."<b>uo-: </b>".$uo."<br/> "."<b>dept-: </b>".$dept."<br/> "."<b>scheme-: </b>".$schme;
                                ?>
                            </td>
                            <td>
                                <?php 
                                    $doj = date('d-m-Y H:i:s',strtotime($record->emp_doj)); 
                                    //$doj= date("d-m-y",  strtotime($record->emp_doj));
                                    $dor=$record->emp_dor;
                                    $newdor=date('d-m-Y H:i:s',  strtotime($dor));
                                    echo $doj ."<br/>". $newdor;
                                ?>
                            </td>
                            <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->emp_desig_code)->desig_name; ?></td>
                            <td><?php echo $record->emp_email; ?></td>
                        </tr>
                         <tr>
                    <td colspan="2" >Reason of Leaving :<br>
                      
                        <select name="resret" id="resret"> 
                        <option value="" disabled selected>-------- Select Reason------</option>
                        <option value="Dismissed">Dismiss</option>  
                        <option value="Expired" >Expire</option>  
                        <option value="Resigned">Resign</option>
                        <option value="VRS">VRS</option>
                        <option value="superannuation">superannuation</option>
                        </select> 
			</td><td>
	
			Remark :<br><textarea name="remark" rows="3" cols="60"  ></textarea> 
                	</td><td colspan="2">
		
			Date :<br>
			<input type="text" name="dateofleaving" value="" id="Dateofaleaving"  size="15" >
			
		</td><td>	
                        <button name="update" id="retire" onClick="return verifyreason()">Update</button>
                    </td>
                    <input type="hidden" name="selempid" value="<?php echo  $selempid ; ?>">          
                </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
		</tbody>
                </tr>
               
        </table> 
        <?php endif;?>   
       
       <!-- <div class="scroller_sub_page"> -->
       <table width="100%"><tr style=" background-color: graytext;">
           <td align="center"><b>List of Retired Staff</b> </td>  
       </tr></table>
        <table class="TFtable" >
            <thead>
                <tr>
                    <th>Sr.No</th>
                    <th></th>
                    <th>Employee Name</th>
                   <!-- <th>Campus Name</th>
                    <th>UO</th>
                    <th>Department</th>-->
                    <th>Details</th>
                    <th>Designation</th>
                    <th>Doj / Dor</th>
                    <!--<th>E-Mail ID</th> -->
                    <th>Reason</th>
                    
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;?>
              <?php if( count($records) ):  ?>
                    <?php foreach($records as $record){ ?>
                        <tr>
                            <td><?php echo $serial_no++; ?></td>
                            <?php //$img=$record->emp_code;?>
                            <?php $emphoto=$this->sismodel->get_listspfic1('employee_master','emp_photoname','emp_id',$record->sre_empid)->emp_photoname;
                            if(!empty($emphoto)):?>
                            <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/'.$emphoto);?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                            <?php else :?>
                            <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/empdemopic.png');?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                            <?php endif;?>
                            <td><?php
                                $empname=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$record->sre_empid)->emp_name;
                                echo $empname."<br/>" ."("."PF No:".$record->sre_empcode.")"."<br/>".$record->sre_empemailid;;
                            ?></td>
                            <td><?php
                            
                                $empscid=$this->sismodel->get_listspfic1('employee_master','emp_scid','emp_id',$record->sre_empid)->emp_scid;
                                $campus=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$empscid)->sc_name;
                                $empuoid=$this->sismodel->get_listspfic1('employee_master','emp_uocid','emp_id',$record->sre_empid)->emp_uocid;
                                $authority=$this->lgnmodel->get_listspfic1('authorities','name','id' ,$empuoid)->name;
                                $empdept=$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$record->sre_empid)->emp_dept_code;
                                $dept=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$empdept)->dept_name;
                                $empschm=$this->sismodel->get_listspfic1('employee_master','emp_schemeid','emp_id',$record->sre_empid)->emp_schemeid;
                                $schm=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$empschm)->sd_name;
                                echo "<b>campus : </b>".$campus."<br/> "."<b>uo : </b>".$authority."<br/> "."<b>dept : </b>".$dept."<br/> "."<b>scheme : </b>".$schm;
                            ?></td>
                            <td><?php
                                $empdesig=$this->sismodel->get_listspfic1('employee_master','emp_desig_code','emp_id',$record->sre_empid)->emp_desig_code;
                                echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$empdesig)->desig_name; ?></td>
                            <td>
                                <?php 
                                $doj = date('d-m-Y H:i:s',strtotime($record->sre_doj));
                                $dor = date('d-m-Y H:i:s',strtotime($record->sre_dor));
                                
                               echo  $doj . "<br/>" .$dor   ;
                                ?>
                            </td>
                            <td><?php
                                    
                            echo $record->sre_reason."<br/>"."Date : "."<font color=red>".date('d-m-Y H:i:s',strtotime($record->sre_reasondate))."</font>";
                            ?></td>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
		</tbody>
        </table>
        </div><!------scroller div------>
        </form>
        <p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
        
    </body>    
</html>   

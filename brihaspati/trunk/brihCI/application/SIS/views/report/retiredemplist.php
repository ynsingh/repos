<!--@filename retiredemplist.php  @author Manorama Pal(palseema30@gmail.com)-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Retired Employee List</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/multiselect/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/multiselect/bootstrap-multiselect.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap3.3.6/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap3.3.6/bootstrap-multiselect.js" ></script>
            
         <script type="text/javascript" src="<?php echo base_url();?>assets/js/jspdf.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jspdf.plugin.autotable.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/pdfps.js" ></script>
        
        
        <style type="text/css" media="print">
        </style>
        <script>
            $(document).ready(function(){
            
               $('#dept').multiselect({
                   
                   nonSelectedText: '----Select Department---',
                    buttonWidth:'200px'
                   // columns:1,
                   // placeholder: 'Select Department',
                   // search: true,
                   // selectAll: true
                });
               
                /****************************************** start of uofficer********************************/
                $('#wtype').on('change',function(){
                    var workt = $(this).val();
                   // alert('workt==='+workt);
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
                 //   alert("values==="+wrktypeuo);
                    if(wtcode == ''){
                        $('#dept').prop('disabled',true);
                   
                    }
                    else{
                        $('#dept').prop('disabled',false);
                         $('#dept').html('');
                        $('#dept').multiselect('rebuild');
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/report/getdeptlist_multisel",
                            type: "POST",
                            data: {"worktypeuo" : wrktypeuo},
                            dataType:"html",
                            success:function(data){
                                $('#dept').html(data);
                                $('#dept').multiselect('rebuild')    
    
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                }); 
                /******************************************end of department********************************/
             
            });
                
            function verify(){
                var x=document.getElementById("wtype").value;
                var y=document.getElementById("uoff").value;
		var z=document.getElementById("ftype").value;
		var w=document.getElementById("strin").value;

                if((w == 'null' && z == 'null') || (w == '' && z == '')||(w == 'null')||(z == 'null')){
                	if((x == 'null' && y == 'null') || (x == '' && y == '')||(y == 'null')||(x == 'null')){
                    		alert("please select at least any two combination for search !!");
                    		return false;
                	};
            	}
            }
        </script>
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        
        <form action="<?php echo site_url('report/retiredemplist');?>" id="myForm" method="POST" class="form-inline">
            <table width="100%" border="0">
                <tr style="font-weight:bold;">
                    <td>  Select Type <br/>
                        <select name="wtype" id="wtype" style="width:130px;" > 
                            <?php if(!empty($this->wtyp)){ ?>
                                <option value="<?php echo $this->wtyp; ?>" > <?php echo $this->wtyp; ?></option>
                            <?php  }else{ ?>

                                <option value="" disabled selected>Select Working Type</option>
                            <?php  } ?> 
                            <option value="Teaching">Teaching</option>
                            <option value="Non Teaching"> Non Teaching</option>
                        </select> 
                                    
                    </td> 
                    <td>  Select UO<br/>
                        <select name="uoff" id="uoff" style="width:200px;"> 
                            <?php if((!empty($this->uolt))&&($this->uolt != 'All')){ ?>
                                <option value="<?php echo $this->uolt; ?>" > <?php echo $this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$this->uolt)->name ." ( ". $this->lgnmodel->get_listspfic1('authorities', 'code', 'id',$this->uolt)->code ." )"; ?></option>
                            <?php  }else{ ?>
                            <option value="" disabled selected>-------- Select University officer------</option>  
                            <?php  } ?>
                        </select> 
                    </td> 
                    <td> Select Department<br/>
                        <select name="dept[]" id="dept" multiple style="width:200px;"> 
                        </select>
                  
                    </td>
                    <?php $cyear= date("Y");
                        $yearArray = range(1948,  $cyear);
                    ?>
                    <?php //echo "year===".$this->year;?>
                    <td>Select Year </br> 
                        <select name="year" id="year" style="width:150px;"> 
                            <?php if((!empty($this->year))&&($this->year != 'All')){ ?>
                                <option value="<?php echo $this->year; ?>" > <?php echo $this->year; ?></option>
                            <?php  }else{ ?>
                                <option value="" disabled selected>--------Select Year ----------</option>
                            <?php  } ?>    
                            <?php
                                foreach ($yearArray as $year) {
                                // if you want to select a particular year
                                //    $selected = ($year == $cyear) ? 'selected' : '';
                                    echo '<option value="'.$year.'">'.$year.'</option>';
                                }
                            ?>          
                        </select> 
                    </td>     
		<?php
			$monthArray = range(1, 12);
		?>
		<td>Select Month </br>
			<select name="month" id="month" style="width:150px;">
			<?php if((!empty($this->month))&&($this->month != 'All')){ ?>
                               <!-- <option value="<?php //echo $this->month; ?>" > <?php //echo $this->month; ?></option>-->
                                <option value="<?php echo $this->month; ?>" > <?php echo date("F", strtotime("2015-$this->month-01")); ?></option>
                        <?php  }else{ ?>
				<option value="" disabled selected>--Select Month --</option>
			<?php
			}
				foreach ($monthArray as $month) {
				        // padding the month with extra zero
				        $monthPadding = str_pad($month, 2, "0", STR_PAD_LEFT);
				        // you can use whatever year you want
				        // you can use 'M' or 'F' as per your month formatting preference
				        $fdate = date("F", strtotime("2015-$monthPadding-01"));
				        echo '<option value="'.$monthPadding.'">'.$fdate.'</option>';
				}
    			?>
			</select>
			</td>
			<td>
				 Select Search Field <br/>
                        <select name="ftype" id="ftype" style="width:130px;" >
                            <?php if(!empty($this->ftyp)){ ?>
                                <option value="<?php echo $this->ftyp; ?>" > <?php echo $this->ftyp; ?></option>
                            <?php  }else{ ?>

                                <option value="" disabled selected>Select Search Field</option>
                            <?php  } ?>
                            <option value="emp_name">Name</option>
                            <option value="emp_code"> UPF/CPS Number</option>
                        </select>
			</td>
			<td>
				Enter String <br>
				 <input type="text" name="strin" id="strin" style="width:100" placeholder="Enter String" value="<?php echo isset($_POST["empname"]) ? $_POST["emppf"] :  ''; ?>">
			</td>
                    <td><br><input type="submit" name="filter" id="crits" value="Search"  onClick="return verify()"/></td>
                </tr>    
            </table>
        </form>
        
        
            <table width="100%"><tr style=" background-color: graytext;"> 
               <td valign="top">
                <form action="<?php echo site_url('Pdfgen/rel/'.str_replace(' ','_',$this->wtyp).'/'.$this->uolt.'/'.$this->deptmt.'/'.$this->year.'/'.$this->month) ?>">
                
                <input type="submit" value="" style="width:30px; height:30px;float:right;padding:5px; margin-right:30px;background-image:url('<?php echo base_url(); ?>assets/sis/images/pdf.jpeg')" title="Click for pdf">     
                </form>    
                <div style="margin-left:600px;"><b>Retired Staff List</b></div>   
                </td></tr></table>
                <div class="scroller_sub_page">
                    <table class="TFtable" id="printme" >
                        <thead><tr>
                            <th>Sr.No</th>
                            <th></th>
                            <th>Employee Name</th>
                            <th>Details</th>
                            <th>Designation</th>
                            <th>Doj / Dor</th>
                            <!-- <th>Reason</th> -->
                            <th>Contact Details</th>
                   
                        </tr></thead>
                        <tbody>
                            <?php $serial_no = 1;?>
                            <?php if( count($records) ):  ?>
                            <?php foreach($records as $record){ ?>
                            <tr>
                                <td><?php echo $serial_no++; ?></td>
                                <?php //$img=$record->emp_code;?>
                                <?php $emphoto=$this->sismodel->get_listspfic1('employee_master','emp_photoname','emp_id',$record->emp_id)->emp_photoname;
                                if(!empty($emphoto)):?>
                                    <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/'.$emphoto);?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                                <?php else :?>
                                <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/empdemopic.png');?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                                <?php endif;?>
                                <td><?php
                                    $empname=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$record->emp_id)->emp_name;
				    echo anchor("report/viewfull_profile/{$record->emp_id}",$empname ,array('title' => 'View Employee Profile' , 'class' => 'red-link'));
                                //    echo $empname."<br/>" ."("."PF No:".$record->emp_code.")"."<br/>".$record->emp_email;;
                                    echo "<br/>" ."("."PF No:".$record->emp_code.")"."<br/>".$record->emp_email;;
                                ?></td>
                                <td><?php
                            
                                    $empscid=$this->sismodel->get_listspfic1('employee_master','emp_scid','emp_id',$record->emp_id)->emp_scid;
                                    $campus=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$empscid)->sc_name;
                                    $empuoid=$this->sismodel->get_listspfic1('employee_master','emp_uocid','emp_id',$record->emp_id)->emp_uocid;
                                    $authority=$this->lgnmodel->get_listspfic1('authorities','name','id' ,$empuoid)->name;
                                    $empdept=$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$record->emp_id)->emp_dept_code;
                                    $dept=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$empdept)->dept_name;
                                    $empschm=$this->sismodel->get_listspfic1('employee_master','emp_schemeid','emp_id',$record->emp_id)->emp_schemeid;
                                    $schm=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$empschm)->sd_name;
                                    $schmcode=$this->sismodel->get_listspfic1('scheme_department','sd_code','sd_id',$empschm)->sd_code;
                                    $subid=$this->sismodel->get_listspfic1('employee_master','emp_specialisationid','emp_id',$record->emp_id)->emp_specialisationid;
                                    if($subid >0){
                                        $subname=$this->commodel->get_listspfic1('subject','sub_name','sub_id',$subid)->sub_name;
                                        $subcode=$this->commodel->get_listspfic1('subject','sub_code','sub_id',$subid)->sub_code;
                                    }
                                    else{
                                        $subname='';
                                        $subcode='';
                                    }
                                    echo "<b>Campus : </b>".$campus."<br/> "."<b>Uo : </b>".$authority."<br/> "."<b>Dept : </b>".$dept."<br/> "."<b>Scheme : </b>".$schm
                                        ."(". $schmcode .")"."<br/>"."<b>Subject:</b>".$subname."(". $subcode .")";
                                ?></td>
                                <td><?php
                                    $empdesig=$this->sismodel->get_listspfic1('employee_master','emp_desig_code','emp_id',$record->emp_id)->emp_desig_code;
                                    $desig=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$empdesig)->desig_name; 
                                    $sap=$record->emp_post;
                                    $payband=$this->sismodel->get_listspfic1('employee_master','emp_salary_grade','emp_id',$record->emp_id)->emp_salary_grade;
                                    $pbname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$payband)->sgm_name;
                                    $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$payband)->sgm_max;
                                    $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$payband)->sgm_min;
                                    $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$payband)->sgm_gradepay;
                                    echo "<b>Designation:</b>".$desig."<br/>"."<b>Shown against post:</b>".$sap."<br/>"."<b>Payband:</b>".$pbname."(".$pay_min."-".$pay_max.")".$gardepay; 
                                    ?>
                                </td>
                                <td>
                                    <?php 
                                    $doj = date('d-m-Y H:i:s',strtotime($record->emp_doj));
                                    $dor = date('d-m-Y H:i:s',strtotime($record->emp_dor));
                                    $dob=$this->sismodel->get_listspfic1('employee_master','emp_dob','emp_id',$record->emp_id)->emp_dob;
                                    $dobnew=date('d-m-Y',strtotime($dob));
                                    echo  "<b>Dob:</b>".$dobnew."<br/>". "<b>Doj:</b>".$doj . "<br/>" ."<b>Dor:</b>"."<font color=green>".$dor."</font>"   ;
                                    ?>
                                </td>
                                <!--<td><?php
                                    
                                    //echo "<b>Reason:</b>".$record->sre_reason."<br/>"."<b>Date : </b>"."<font color=red>".date('d-m-Y H:i:s',strtotime($record->sre_reasondate))."</font>";
                                ?></td> -->
                                <td>
                                    <?php   
                                    $phoneno=$this->sismodel->get_listspfic1('employee_master','emp_phone','emp_id',$record->emp_id)->emp_phone; 
                                    $emailid=$this->sismodel->get_listspfic1('employee_master','emp_secndemail','emp_id',$record->emp_id)->emp_secndemail;
                                    $eadhar=$this->sismodel->get_listspfic1('employee_master','emp_aadhaar_no','emp_id',$record->emp_id)->emp_aadhaar_no; 
                                    echo "<b>Contact no:</b>".$phoneno ."<br/>"."<b>EmailId:</b>".$emailid."<br/>"."<b>e-Aadhar no:</b>".$this->sismodel->mask($eadhar,null,strlen($eadhar)-4);
                                    ?>
                                </td>
                            </tr>
                        <?php }; ?>
                        <?php else : ?>
                            <td colspan= "13" align="center"> No Records found...!</td>
                        <?php endif;?> 
                    </tbody>
                </table> 
            </div><!------scroller div------>
            <p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>    
    </body>        
</html>    

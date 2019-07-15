<!--@filename profilecompleteness.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com) 
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <style type="text/css" media="print">
        @page {
                size: auto;   /* auto is the initial value */
                margin:0;  /* this affects the margin in the printer settings */
            }
        </style>
	<style>
table {
  border-collapse: collapse;
}
</style>
        <script type="text/javascript">
             function printDiv(printme) {
                var printContents = document.getElementById(printme).innerHTML; 
                //alert("printContents==="+printContents);
                var originalContents = document.body.innerHTML;      
                document.body.innerHTML = "<html><head><title></title></head><body style='width:100%;'><img src='<?php echo base_url(); ?>uploads/logo/logotanuvas.jpeg' alt='logo' style='width:100%;height:100px;'>"+" <div style='width:100%;height:100%;'>  " + printContents + "</div>"+"</body>";
                window.print();     
                document.body.innerHTML = originalContents;
            }
            
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
                            //url: "<?php echo base_url();?>sisindex.php/report/getdeptlist",
                            url: "<?php echo base_url();?>sisindex.php/jslist/getdeptlist",
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
               
            });
            function verify(){
                    var x=document.getElementById("wtype").value;
                    var y=document.getElementById("uoff").value;
                    var z=document.getElementById("dept").value;
                   // alert("value==x==="+x+"\nvalue==y=="+y);
                  //  if((x == 'null' && y == 'null') || (x == '' && y == '')||(y == 'null')||(x == 'null')){
                    if((x == 'null' && y == 'null' && z == 'null') || (x == '' && y == '' && z == '')){
                        //alert("please select at least any two combination for search !!");
                        alert("please select at least any one for search !!");
                        return false;
                    };
            }
        </script>        
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        
        <form action="<?php echo site_url('report/profilecompleteness');?>" id="myForm" method="POST" class="form-inline">
         <table width="100%" border="0">
            <tr style="font-weight:bold;">
                <td>  Select Type<br>
                    <select name="wtype" id="wtype" style="width:250px;"> 
			<?php if(!empty($wtyp)){ ?>
                        <option value="<?php echo $wtyp; ?>" > <?php echo $wtyp; ?></option>
                        <?php  }else{ ?>

                      <option value="" >--------Select Working Type-------</option>
                       <?php  } ?> 
                      <option value="Teaching">Teaching</option>
                      <option value="Non Teaching"> Non Teaching</option>
                    </select> 
                                    
                </td> 
               <td>  Select UO<br>
                    <select name="uoff" id="uoff" style="width:270px;"> 
			 <?php if((!empty($uolt))&&($uolt != 'All')){ ?>
                        <option value="<?php echo $uolt; ?>" > <?php echo $this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$uolt)->name ." ( ". $this->lgnmodel->get_listspfic1('authorities', 'code', 'id',$uolt)->code ." )"; ?></option>
                        <?php  }else{ ?>

                      <option value="">-------- Select University officer------</option>  
		 <?php  } ?>
                    </select> 
                </td> 
                <td><div>  Select Department<br>
                  <!--  <select name="dept[]" id="dept" style="width:400px;"  title="You have to choose multiple subject by pressing Ctrl" multiple>  -->
                    <select name="dept" id="dept" style="width:400px;"   > 
			 <?php if((!empty($deptmt))&&($deptmt != 'All')){ ?>
                        <option value="<?php echo $deptmt; ?>" > <?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id' ,$deptmt)->dept_name ." ( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id' ,$deptmt)->dept_code ." )"; ?></option>
                        <?php  }else{ ?>

                      <option value="">-------------Select Department-----------</option>  
			 <?php  } ?>

                    </select> 
                    
                </td>
                <td>
			<br>
			<input type="submit" name="filter" id="crits" value="Search"  onClick="return verify()"/>
		</td>
            </tr>    
        </table>
            
        </form>
	<script>
                $('document').ready(function(){
                        $('#dept').multiselect({
                                columns:1,
                                placeholder: 'Select Department',
                                search: true,
                                selectAll: true
                        });
                });
        </script>
        <table width="100%"><tr style=" background-color: graytext;">
            <td valign='top'> 
                <img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  align="left" onclick="javascript:printDiv('printme')" style='width:30px; height:30px;float:right;padding:2px; margin-right:30px;'  title="Click for print" >
                <?php // if(!empty($wtype)||(!empty($uolt))||(!empty($deptmt))):?>
                 <form action="<?php echo site_url('Pdfgen/rpc/'.str_replace(' ','_',$wtyp).'/'.$uolt.'/'.$deptmt) ?>"> 
                  
                  <input type="submit" value="" style="width:30px; height:30px;float:right;padding:5px; margin-right:10px;background-image:url('<?php echo base_url(); ?>assets/sis/images/pdf.jpeg')" title="Click for pdf">     
                  <?php //endif ;?>
                </form>
                <div style="margin-left:500px;"><b> Profile Completeness Details</b></div>
                   
            </td> 
           
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
    <div id="printme" align="left" style="width:100%;">  
        <div class="scroller_sub_page"> 
        <table class="TFtable" style="border-width:2px;border-color:black;border-style:solid;">
            <thead>
                <tr>
                    <th style="border-width:2px;">Sr. No.</th>
                    <th style="border-width:2px;">Employee Name</th>
			<th style="border-width:2px;">Basic </th><th style="border-width:2px;">Academic </th>
			<th style="border-width:2px;">Technical </th><th style="border-width:2px;">	Promot- ional </th>
			<th style="border-width:2px;">	Service </th><th style="border-width:2px;">Addional Assign- ment </th>
			<th style="border-width:2px;">	Perfor- mance </th><th style="border-width:2px;">	Leave </th>
			<th style="border-width:2px;">	Deputation </th><th style="border-width:2px;">	Depart- mental Exam </th>
			<th style="border-width:2px;">	Working Arrange- ment </th><th style="border-width:2px;">	Recruit- ment </th>
			<th style="border-width:2px;">	Discip- linary Actions</th>
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;
		$ouoid = 0;
		$odid = 0;
		
               if( !empty($records) ):  ?>
                    <?php foreach($records as $record){
//                       print_r($record);
			if($ouoid !=$record->emp_uocid){
			echo "<tr>";
			echo "<td colspan=15 style=\"text-align:center;border-width:2px;\">";
			echo " <b> UO CONTROL : ";
			echo $this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->emp_uocid)->name;
			echo "</b></td>";
			echo "</tr>";
			$ouoid=$record->emp_uocid;
			}
			if($odid !=$record->emp_dept_code){
                        echo "<tr><td colspan=15 align=left style=\"border-width:2px;\"><b> Department : ";
			echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
			echo " ( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record->emp_dept_code)->dept_code ." )";
                        echo "</b></td></tr>";
			$odid = $record->emp_dept_code;
			$serial_no = 1;
                        }
			echo "<tr style=\"border-width:2px;\">";
			echo "<td style=\"border-width:2px;\">". $serial_no++ ." </td>";
			echo "<td style=\"border-width:2px;\">";
			echo $record->emp_name." ( "."PF No:".$record->emp_code." )" ;
			echo "</td>";

			echo "<td style=\"border-width:2px;\">"; 
			echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_academic_qualification",'saq_empid',$record->emp_id))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_technical_qualification",'stq_empid',$record->emp_id))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_promotionals_details",'spd_empid',$record->emp_id))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("employee_servicedetail",'empsd_empid',$record->emp_id))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("additional_assignments",'aa_empid',$record->emp_id))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("Staff_Performance_Data",'spd_empid',$record->emp_id))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("leave_apply",'la_userid',$record->emp_id))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_deputation_perticulars",'sdp_empcode',$record->emp_code))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_department_exam_perticulars",'sdep_empcode',$record->emp_code))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_working_arrangements_perticulars",'swap_empcode',$record->emp_code))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_recruitment_perticulars",'srp_empcode',$record->emp_code))
					echo "Yes";
			echo "</td>";

			echo "<td style=\"border-width:2px;\">";
				if($this->sismodel->isduplicate("staff_disciplinary_actions_perticulars",'sdap_empcode',$record->emp_code))
					echo "Yes";
			echo "</td>";

?>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "15" align="center"> No Records found...!</td>
                <?php endif;?>
                </tbody>
        </table>
        </div> <!------scroller div------>

        </div><!------print div------>
        
	<p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>

    </body>
</html>

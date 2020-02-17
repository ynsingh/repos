<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name Salary Report.php 
  @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
  @author Manorama Pal (palseema30@gmail.com)  
 -->
<html>
<title>View Salary Report</title>
    <head>    
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	<script>
           function verify(){
                var year=document.getElementById("year").value;
                var month=document.getElementById("month").value;
                if((year == " " && month ==" " )){
                    alert("please select month and year for load the salary !!");
                    return false;
                };
            }  
        </script>
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
	  <table width="100%"><tr colspan="2">
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
        </tr></table>
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

		<table width="100%" border="0">
                <form action="<?php echo site_url('payrollreport/salaryslipreport');?>" method="POST" enctype="multipart/form-data">
                <tr style="font-weight:bold;" >
                   <td> Select Department<br>
                        <select name="dept" id="dept" style="width:250px;" required>
                            <?php
                                if(!empty($deptsel)){ ?>
                            <option value="<?php echo "";?>"><?php echo $deptsel;?></option>
<?php                           }else{ ?>
                            <option value="<?php echo "";?>"><?php echo "Please select department";?></option>
<?php                           }
                                foreach ($combdata as $rec) {
                                    echo '<option  value="'.$rec->dept_id.'">'.$rec->dept_name.'</option>';
                                }
                            ?>
                        </select>

                   </td>
                    <td>Select Month <br>
                        <select name="month" id="month" style="width:250px;">
                            <?php if($selmonth!=$cmonth && (!empty($selmonth))):;?>
                            <option value="<?php echo $selmonth;?>"><?php echo $selmonth;?></option>
                            <?php else:;?>
                            <option value="<?php echo $selmonth;?>"><?php echo $selmonth;?></option> 
                            <?php endif;?>
                            <?php
                                foreach ($formattedMonthArray as $month) {
                                    echo '<option  value="'.$month.'">'.$month.'</option>';
                                }
                            ?>

                        </select>

                    </td>
		 <td>Select Year</br>


                        <select name="year" id="year" style="width:250px;" >
                            <?php if($selyear!=$cyear):;?>
                            <option value="<?php echo $selyear;?>"><?php echo $selyear;?></option>
                            <?php else:;?>
                            <option value="<?php echo $selyear;?>"><?php echo $selyear;?></option> 
                            <?php endif;?>
                            <?php
                                foreach ($yearArray as $year) {
                                    echo '<option value="'.$year.'">'.$year.'</option>';
                                }
                            ?>
                        </select>
                  </td>

                   <td>
                        <input type="submit" name="bulksal" id="bulksal" value="Search"/>

                    </td>
                                      
		</tr>
                <tr>
                     </form> 
                <!--    <td><?php // echo anchor("payrollreport/salaryslipreport",img(array('src'=>'assets/sis/images/pdf.jpeg','border'=>'0.1px','alt'=>'view ')),array('title' => 'save salary slip' , 'class' => 'red-link'));?></td> -->
                </tr>
	</table>

	<?php //include "../setup3/salaryslipcopynew.php"; ?>
    </body>
<p> &nbsp; </p>
</html>


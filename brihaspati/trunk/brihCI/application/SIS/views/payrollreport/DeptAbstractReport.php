<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name dept Abstract Report .php 
  @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 -->
<html>
<title>View Dept Abstract Report</title>
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
                <form action="<?php echo site_url('payrollreport/deptAbstract');?>" method="POST" enctype="multipart/form-data">
                <tr style="font-weight:bold;" >
                   <td> Select Depratment<br>
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

                   <td><br>
                        <input type="submit" name="deptabs" id="deptabs" value="Search"/>

                    </td>
                   </form>
		<td><br>
		<form action="<?php echo site_url('payrollreport/deptAbstract/pdf/'.$deptid.'/'.$selyear.'/'.$selmonth); 
			?>">
                <input type="submit" name="deptabs" value="" style="width:30px; height:30px;float:right;padding:2px; margin-right:10px;background-image:url('<?php echo base_url(); ?>assets/sis/images/pdf.jpeg')" title="Click for pdf">
            </form>
	</td>
		</tr>
	</table>
	<br><br>
	<?php
	$totamt=0;
	echo "<table class='TFtable'>";		
	echo "<tr>";
	echo "<td align=center>";
	//	echo INAME;
	echo "</td>";
	echo "</tr>";
	echo "<tr>";
	echo "<td style='text-align:center;'>";
		echo "ABSTRACT FOR THE MONTH OF ".$selmonth." " .$selyear;
		echo "<hr>";
	echo "</td>";
	echo "</tr>";
	if(!empty($deptid)){
		echo "<tr align=center>";
		echo "<td >";
		$schm=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_deptid',$deptid)->sd_name;
                $schmcode=$this->sismodel->get_listspfic1('scheme_department','sd_code','sd_deptid',$deptid)->sd_code;
		$deptsel=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptid)->dept_name;
		$ddocode =$this->sismodel->get_listspfic1('ddo','ddo_code','ddo_deptid',$deptid)->ddo_code;
		$ddonme =$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_deptid',$deptid)->ddo_name;
		echo "<b>Scheme :</b> ".$schm." ( ".$schmcode. " )&nbsp;&nbsp;&nbsp;&nbsp;<b> Department :</b> ".$deptsel. "&nbsp;&nbsp;&nbsp;&nbsp;<b> DDO :</b> ".$ddonme." ( ".$ddocode. " )";
		echo "<hr>";
		echo "</td>";
		echo "</tr>";
		echo "<tr>";
		echo "<td>";
		$itot=0;$dtot=0;$ltot=0;
		if(!empty($d1)){
			echo "<table align=center width='100%' border=1>";		
			echo "<tr>";
			echo "<td align=center>";
			echo "<b>";
			echo "Allowances";
			echo "</b>";
			echo "</td>";
			echo "<td align=center>";
			echo "<b>";
			echo "Deductions";
			echo "</b>";
			echo "</td>";
			echo "<td align=center>";
			echo "<b>";
			echo "Loans";
			echo "</b>";
			echo "</td>";
			echo "</tr>";
			echo "<tr>";
			echo "<td valign=top>";
		
			echo "<table>";		
			foreach($incomes as $irow){
			echo "<tr>";
			echo "<td>";
				echo "<b>".$irow->sh_name. "   </b> ";
			echo "</td>";
			echo "<td style='text-align:right;'>";
			 	echo  ${"d".$irow->sh_id};
				$itot=$itot +${"d".$irow->sh_id};
			echo "</td>";
			echo "</tr>";
			//	echo "<br>";
			}
			echo "</table>";		
			echo "</td>";
			echo "<td valign=top>";
			echo "<table>";		
			foreach($ded as $drow){
			echo "<tr>";
			echo "<td>";
				echo "<b>".$drow->sh_name. "   </b> ";
			echo "</td>";
			echo "<td style='text-align:right;'>";
 				echo  ${"d".$drow->sh_id};
				$dtot=$dtot +${"d".$drow->sh_id};
//				echo "<br>";
			echo "</td>";
			echo "</tr>";
			}
			echo "</table>";		
			echo "</td>";
			echo "<td valign=top>";
			echo "<table>";		
			foreach($loans as $lrow){
			echo "<tr>";
			echo "<td>";
				echo "<b>". $lrow->sh_name. "   </b> " ;
			echo "</td>";
			echo "<td style='text-align:right;'>";
				echo  ${"d".$lrow->sh_id};
				$ltot=$ltot +${"d".$lrow->sh_id};
//				echo "<br>";
			echo "</td>";
			echo "</tr>";
			}
			echo "</table>";		
			echo "</td>";
			echo "</tr>";
			echo "<tr>";
			echo "<td style='text-align:right;'>";
			echo $itot;
			echo "</td>";
			echo "<td style='text-align:right;'>";
			echo $dtot;
			echo "</td>";
			echo "<td style='text-align:right;'>";
			echo $ltot;
			echo "</td>";
			echo "</tr>";
			echo "</table>";
		}else{
			echo "<tr>";
			echo "<td>";
			echo "No Data exist.";
			echo "</td>";
			echo "</tr>";
		}
		echo "</td>";
		echo "</tr>";
		$totamt=$itot - $dtot - $ltot;
	}else{
		echo "<tr>";
		echo "<td>";
		echo "No Department selected. Please select the department.";
		echo "</td>";
		echo "</tr>";
	}
	echo "<tr>";
	echo "<td>";
		echo "<hr>";
		echo "<table width=100%>";
	echo "<tr>";
	echo "<td>";
		echo "<b>  No of Employee : </b>".$empcount;
	echo "</td>";
	echo "<td style='text-align:right;'>";
		echo "<b>Total :</b> ".$totamt;
	echo "</td>";
	echo "</tr>";
	echo "</table>";		
		echo "<hr>";
	echo "</td>";
	echo "</tr>";
	echo "</table>";		
	 ?>
    </body>
<p> &nbsp; </p>
</html>


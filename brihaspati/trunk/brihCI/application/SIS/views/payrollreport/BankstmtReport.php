<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name Bank Statement Report .php 
  @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 -->
<html>
<title>View DDO Bank Statement Report</title>
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
                <form action="<?php echo site_url('payrollreport/bankStmtReport');?>" method="POST" enctype="multipart/form-data">
                <tr style="font-weight:bold;" >
                   <td> Select DDO<br>
                        <select name="ddo" id="ddo" style="width:250px;" required>
                            <?php
                                if(!empty($ddosel)){ ?>
                            <option value="<?php echo "";?>"><?php echo $ddosel;?></option>
<?php                           }else{ ?>
                            <option value="<?php echo "";?>"><?php echo "Please select DDO";?></option>
<?php                           }
                                foreach ($combdata as $rec) {
                                    echo '<option  value="'.$rec->ddo_code.'">'.$rec->ddo_name.'</option>';
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
                        <input type="submit" name="ddoabs" id="ddoabs" value="Search"/>

                    </td>
                   </form>
		<td><br>
		<form action="<?php echo site_url('payrollreport/bankStmtReport/pdf/'.$ddocode.'/'.$selyear.'/'.$selmonth);
                        ?>">
                <input type="submit" name="ddoabs" value="" style="width:30px; height:30px;float:right;padding:2px; margin-right:10px;background-image:url('<?php echo base_url(); ?>assets/sis/images/pdf.jpeg')" title="Click for pdf">
            </form>
	
		</td>
		</tr>
	</table>
	<br><br>
	<?php
	$totamt=0;
	echo "<table class='TFtable'>";		
	echo "<tr>";
	echo "<td style='text-align:center;' colspan=6>";
		echo "BANK STATEMENT FOR THE MONTH OF ".$selmonth." " .$selyear;
		echo "<hr>";
	echo "</td>";
	echo "</tr>";
	echo "<tr>";
	echo "<td align=center>";
	echo "Sr. No.";
	echo "</td>";
	echo "<td align=center>";
	echo "Emp No";
	echo "</td>";
	echo "<td align=center>";
	echo "Name";
	echo "</td>";
	echo "<td align=center>";
	echo "Bank Name";
	echo "</td>";
	echo "<td align=center>";
	echo "Account Number";
	echo "</td>";
	echo "<td align=center>";
	echo "Amount";
	echo "</td>";
	echo "</tr>";
	if(!empty($ddosel)){
		echo "<tr align=center>";
		echo "<td colspan=6>";
		echo "<hr>";
		$ddocode =$this->sismodel->get_listspfic1('ddo','ddo_code','ddo_name',$ddosel)->ddo_code;
		echo "<b> DDO :</b> ".$ddosel." ( ".$ddocode. " )";
		echo "</td>";
		echo "</tr>";
		if(!empty($listd)){
			$i=1;$tdept=0;
			foreach($listd as $lrow){
				$ldept=$lrow['dept'];
				if($tdept != $ldept){
					
				echo "<tr>";
				echo "<td colspan=6>";
				echo "<hr>";
				echo "<b> Dept :</b> ".$this->commodel->get_listspfic1('Department','dept_name','dept_id',$ldept)->dept_name;
				echo "<hr>";
				echo "</td>";
				echo "</tr>";
				$tdept = $ldept;
				}
				echo "<tr>";
				echo "<td>";
				echo $i++;
//				echo $lrow['id'];
				echo "</td>";
				echo "<td>";
				echo $lrow['code'];
				echo "</td>";
				echo "<td>";
				echo $lrow['name'];
				echo "</td>";
				echo "<td>";
				echo $lrow['bankname'];
				echo "</td>";
				echo "<td>";
				echo $lrow['bankacc'];
				echo "</td>";
				echo "<td>";
				echo $lrow['amount'];
				$totamt=$totamt +$lrow['amount'];
				echo "</td>";
				echo "</tr>";
			}
		}else{
			echo "<tr>";
			echo "<td colspan=6>";
			echo "No Data exist.";
			echo "</td>";
			echo "</tr>";
		}
	}else{
		echo "<tr>";
		echo "<td colspan=6>";
		echo "No DDO selected. Please select the DDO.";
		echo "</td>";
		echo "</tr>";
	}
	echo "<tr>";
	echo "<td colspan=3>";
		echo "<hr>";
		echo "<b> No of Employee : </b>".$empcount;
		echo "<hr>";
	echo "</td>";
	echo "<td colspan=2 align=right>";
		echo "<hr>";
		echo "<b>Total :</b> ";
		echo "<hr>";
	echo "</td>";
	echo "<td>";
		echo "<hr>";
		echo $totamt;
		echo "<hr>";
	echo "</td>";
	echo "</tr>";
	echo "</table>";		
	 ?>
    </body>
<p> &nbsp; </p>
</html>


<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name dept Abstract Report .php 
  @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 -->
<html>
<title>View Dept Abstract Report PDF</title>
    <head>    
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
	<div>
	<?php
	$totamt=0;
	echo "<table class='TFtable'>";		
	echo "<tr>";
	echo "<td align=center>";
		echo INAME;
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
			echo "<td align=right>";
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
			echo "<td align=right>";
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
			echo "<td align=right>";
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
	</div>
    </body>
<p> &nbsp; </p>
</html>


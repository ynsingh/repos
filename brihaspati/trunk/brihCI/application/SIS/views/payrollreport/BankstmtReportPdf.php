<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name Bank Statement Report .php 
  @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 -->
<html>
<title>View DDO Bank Statement Report PDF</title>
    <head>    
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
	<div>
	<?php
	$totamt=0;
	echo "<table class='TFtable'>";		
	echo "<tr>";
	echo "<td align=center colspan=6>";
		echo INAME;
	echo "</td>";
	echo "</tr>";
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
	</div>
    </body>
<p> &nbsp; </p>
</html>


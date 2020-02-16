<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name ScheduleReport .php 
  @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 -->
<html>
<title>View DDO Schedule Report PDF</title>
    <head>    
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	<style type="text/css" media="all">
		@page {
			size: A4 portrait; /* can use also 'landscape' for orientation */
			margin: 1.0in;
			border: thin solid black;
			padding: 1em;
			
			@bottom-center {
				content: element(footer);
			}
			
			@top-center {
				content: element(header);
			}
		}
			
		#page-header {
			display: block;
			position: running(header);
		}
		
		#page-footer {
			display: block;
			position: running(footer);
		}
		
		.page-number:before {
			content: counter(page); 
		}
		
		.page-count:before {
			content: counter(pages);  
		}
	</style>
    </head>
    <body>
<!--
	 <div id="page-footer">
                <p>Page <span class="page-number"/> of <span class="page-count"/></p>
        </div>-->
	<?php
/*
	echo "<div id=\"page-header\">";
	echo "<p>";
	echo "<table class='TFtable'>";		
	echo "<tr>";
	echo "<td align=center colspan=4>";
		echo INAME;
	echo "</td>";
	echo "</tr>";
	echo "<tr align=center>";
	echo "<td colspan=4 style='text-align:center;'>";
	echo "<hr>";
	$ddocode =$this->sismodel->get_listspfic1('ddo','ddo_code','ddo_name',$ddosel)->ddo_code;
	echo "<b> DDO :</b> ".$ddosel." ( ".$ddocode. " )";
	echo "</td>";
	echo "</tr>";
	echo "</table>";		
	echo "</p>";
        echo "</div>";
*/
	echo "<div id=\"page-content\">";
	$totamt=0;$dtotamt=0;$ii=0;	
	if(!empty($ddosel)){
		if(!empty($listd)){
			$i=1;$tdept=0;$shn='';$ii=1;
			foreach($listd as $lrow){
				$ldept=$lrow['dept'];
				$lshn=$lrow['shid'];
                                if(($tdept != $ldept)||($shn != $lshn)){
					if($dtotamt>0){
						echo "<tr>";
						echo "<td colspan=4>";
						echo "<hr>";
						echo "</td>";
						echo "</tr>";
						echo "<tr>";
						echo "<td colspan=3>";
						//echo "<hr>";
						$i--;
						echo " <b>Total Employee :</b> ". $i;
						echo "</td>";
						echo "<td style='text-align:right;'>";
					//	echo "<hr>";
						echo "<b>Total Amount :<b>".$dtotamt;
						echo "</td>";
						echo "</tr>";
						$i++;
?>
					</table>
					</p>
					<p style="page-break-before:always;"></p>
					
	<!--				<div style="page-break-after:always;"/> -->
<?php
					}	
				}
				if($shn != $lshn){
					echo "<p>";
					echo "<table class='TFtable'>";		
	echo "<tr>";
	echo "<td align=center colspan=4>";
		echo INAME;
	echo "</td>";
	echo "</tr>";
	echo "<tr align=center>";
	echo "<td colspan=4 style='text-align:center;'>";
	echo "<hr>";
	$ddocode =$this->sismodel->get_listspfic1('ddo','ddo_code','ddo_name',$ddosel)->ddo_code;
	echo "<b> DDO :</b> ".$ddosel." ( ".$ddocode. " )";
	echo "</td>";
	echo "</tr>";
					echo "<tr>";
					echo "<td colspan=4 align=center>";
					echo "<hr><b>";
					echo $lrow['shnme']." SCHEDULE FOR THE MONTH OF ".$selmonth." " .$selyear; 
					echo "</b><hr>";
					echo "</td>";
					echo "</tr>";
					$shn=$lshn;
					$tdept=0;
					$i=1;
					echo "<tr>";
					echo "<td>";
					echo "Sr. No.";
					echo "</td>";
					echo "<td>";
					echo "Emp No";
					echo "</td>";
					echo "<td>";
					echo "Name";
					echo "</td>";
					echo "<td>";
					echo "Amount";
					echo "</td>";
					echo "</tr>";
				}
				if($tdept != $ldept){
					echo "<tr>";
					echo "<td colspan=4>";
					echo "<b> Dept :</b> ".$this->commodel->get_listspfic1('Department','dept_name','dept_id',$ldept)->dept_name;
					echo "<hr>";
					echo "</td>";
					echo "</tr>";
					$tdept = $ldept;
					$dtotamt=0;
				}
				echo "<tr>";
				echo "<td>";
				echo $i++;
				echo "</td>";
				echo "<td>";
				echo $lrow['code'];
				echo "</td>";
				echo "<td>";
				echo $lrow['name'];
				echo "</td>";
				echo "<td style='text-align:right;'>";
				echo $lrow['amount'];
				$totamt=$totamt +$lrow['amount'];
				$dtotamt=$dtotamt +$lrow['amount'];
				echo "</td>";
				echo "</tr>";
				$ii++;
			}
		}else{
			echo "<tr>";
			echo "<td colspan=4>";
			echo "No Data exist.";
			echo "</td>";
			echo "</tr>";
		}
	}else{
		echo "<tr>";
		echo "<td colspan=4>";
		echo "No DDO selected. Please select the DDO.";
		echo "</td>";
		echo "</tr>";
	}
	if($dtotamt>0){
		echo "<tr>";
                                                echo "<td colspan=4>";
                                                echo "<hr>";
                                                echo "</td>";
                                                echo "</tr>";
		echo "<tr>";
                echo "<td colspan=2>";
                $i--;
                echo " <b>Total Employee :</b> ". $i;
                echo "</td>";
                echo "<td style='text-align:right;' colspan=2>";
                echo "<b>Total Amount :<b>".$dtotamt;
                echo "</td>";
                echo "</tr>";
        }
	echo "<tr>";
	echo "<td colspan=2>";
		$ii--;
		echo "<b> No of Records : </b>".max( $ii,0) ." of ". $empcount;
	echo "</td>";
	echo "<td style='text-align:right;' colspan=2>";
		echo "<b>Grand Total :</b> ";
		echo $totamt;
	echo "</td>";
	echo "</tr>";
	echo "<tr>";
                echo "<td colspan=4>";
                echo "<hr>";
                echo "</td>";
                echo "</tr>";
	echo "</table>";		
	 ?>
</div>
    </body>
<p> &nbsp; </p>
</html>


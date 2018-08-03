<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');


	echo "</br>";

	echo "<table border=0  class=\"simple-table balance-sheet-table\">";

	echo "<thead><tr><th align=\"center\">S.No. </th>";
	echo "<th align=\"center\">Name</th>";
	echo "<th align=\"center\">Issued Type</th>";
	echo "<th align=\"center\">Issued Date</th><th align=\"center\">Issues By</th>"; 
	echo "</tr></thead>";
	
	$i=1;
	if(!empty($result)){
		foreach($result as $row){
			echo "<tr>";
			echo "<td>".$i . "</td>";
			if(!empty($row->sacunitno)){
				$partynme=$this->BGAS_model->get_listspfic1('addsecondparty','partyname','sacunit',$row->sacunitno)->partyname;
			}
			else{
				$partynme='';
			}
			echo "<td>". $partynme ."</td>";
			echo "<td>". $row->type ."</td>";
			echo "<td>". $row->date. "</td>";
			echo "<td>". $row->creatorid . "</td>";
			echo "</tr>";
			$i++;
		}
	}
	else{
		echo "<tr>";
		echo "<td colspan=5> No Record found </td>";
		echo "</tr>";
	}
	echo "</table>";

?>

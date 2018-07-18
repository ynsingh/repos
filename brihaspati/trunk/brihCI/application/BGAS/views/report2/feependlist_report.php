<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');


	echo "</br>";

	echo "<table border=0  class=\"simple-table balance-sheet-table\">";

	//echo "<thead><tr><th align=\"center\">S.No. </th><th align=\"center\">Party Name</th><th align=\"center\">PAN No.</th><th align=\"center\">VAT No.</th><th align=\"center\">Service Tax No.</th><th align=\"center\">Party ID</th>";
	echo "<thead><tr><th align=\"center\">S.No. </th>";
	echo "<th align=\"center\">Name</th>";
	echo "<th align=\"center\">Amount Due</th><th align=\"center\">Amount Deposit</th>"; 
	echo "<th align=\"center\">Balance</th>";
	echo "</tr></thead>";
	
//	$this->load->library('Paymentreceipt');
  //      $income = new Paymentreceipt();
	//    $income->tds_report();
	//    amount,dc,secunitid,ledger_code
	$i=1;
	if(!empty($result)){
		$stdpartid1="0";
		$dbamt=0;$cramt=0;$balamt=0;$totdbamt=0;  $totcramt=0;
		foreach($result as $row){
			echo "<tr>";
			echo "<td>".$i . "</td>";
			if(!empty($row['secunit'])){
				$partynme=$this->BGAS_model->get_listspfic1('addsecondparty','partyname','sacunit',$row['secunit'])->partyname;
			}
			else{
				$partynme='';
			}
			echo "<td>". $partynme ."</td>";
			echo "<td>". $row['dbamt']. "</td>";
			echo "<td>". $row['cramt'] . "</td>";
			$bal = $row['dbamt'] - $row['cramt'];
			echo "<td>". $bal . "</td>";
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

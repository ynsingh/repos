<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');


	echo "</br>";

	echo "<table border=0  class=\"simple-table balance-sheet-table\">";

	//echo "<thead><tr><th align=\"center\">S.No. </th><th align=\"center\">Party Name</th><th align=\"center\">PAN No.</th><th align=\"center\">VAT No.</th><th align=\"center\">Service Tax No.</th><th align=\"center\">Party ID</th>";
	echo "<thead><tr><th align=\"center\">S.No. </th>";
	echo "<th align=\"center\">Name</th>";
	echo "<th align=\"center\">Date</th>";
	echo "<th align=\"center\">Amount Due</th><th align=\"center\">Amount Deposit</th>"; 
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
			$stdpartid=$row->secunitid;
			if (($stdpartid1 != $stdpartid) && ($i != 1)){
				$stdpartid1 = $stdpartid;
				$balamt=$totdbamt -  $totcramt;
				echo "<tr><td colspan=3>Balance</td><td colspan=2>" .$balamt."</td></tr>";
				$totdbamt=0;$totcramt=0;$balamt=0;
				//echo "<td>". $dbamt. "</td>";
//				echo "<td>". $cramt . "</td>";
//				echo "<td>".$balamt  . "</td>";	
			}
			echo "<tr>";
			echo "<td>".$i . "</td>";
			echo "<td>". $this->BGAS_model->get_listspfic1('addsecondparty','partyname','sacunit',$row->secunitid)->partyname."</td>";
			echo "<td>".$row->update_date ."</td>";
			if($row->dc == "D"){
				$dbamt=$row->amount;
				echo "<td>". $dbamt. "</td>";
				$totdbamt=$totdbamt + $dbamt;
			}else{
			echo "<td> </td>";
			}
			if($row->dc == "C"){
				$cramt=$row->amount;
				echo "<td>". $cramt . "</td>";
				$totcramt=$totcramt + $row->amount;

			}else{
				echo "<td>0 </td>";
			}
			echo "</tr>";
			$i++;
		}
		$balamt=$totdbamt -  $totcramt;
		echo "<tr><td colspan=3>Balance</td><td colspan=2>" .$balamt."</td></tr>";
	}
	else{
		echo "<tr>";
		echo "<td> No Record found </td>";
		echo "</tr>";
	}

		
	echo "</table>";

?>

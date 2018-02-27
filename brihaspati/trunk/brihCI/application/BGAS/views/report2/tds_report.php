<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');


	echo "</br>";

	echo "<table border=0  class=\"simple-table balance-sheet-table\">";

	echo "<thead><tr><th align=\"center\">S.No. </th><th align=\"center\">Party Name</th><th align=\"center\">PAN No.</th><th align=\"center\">VAT No.</th><th align=\"center\">Service Tax No.</th><th align=\"center\">Party ID</th>";
	echo "<th align=\"center\">Date</th><th align=\"center\">Narration</th><th align=\"center\">Entry No.</th><th align=\"center\">TDS Amount</th></tr></thead>";
	
	$this->load->library('Paymentreceipt');
        $income = new Paymentreceipt();
        $income->tds_report();
		
	echo "</table>";

?>

<?php
	tcpdf();
	$obj_pdf = new TCPDF('P', PDF_UNIT, PDF_PAGE_FORMAT, true, 'UTF-8', false);
	$obj_pdf->SetCreator(PDF_CREATOR);
	$title = "PDF Report";
	$obj_pdf->SetTitle($title);
	$obj_pdf->setHeaderFont(Array(PDF_FONT_NAME_MAIN, '', PDF_FONT_SIZE_MAIN));
	$obj_pdf->setFooterFont(Array(PDF_FONT_NAME_DATA, '', PDF_FONT_SIZE_DATA));
	$obj_pdf->SetDefaultMonospacedFont('helvetica');
	$obj_pdf->SetHeaderMargin(PDF_MARGIN_HEADER);
	$obj_pdf->setPrintHeader(false);
	$obj_pdf->SetFooterMargin(PDF_MARGIN_FOOTER);
	$obj_pdf->setPrintFooter(false);
	$obj_pdf->SetMargins(PDF_MARGIN_LEFT, PDF_MARGIN_TOP, PDF_MARGIN_RIGHT);
	$obj_pdf->SetAutoPageBreak(TRUE, PDF_MARGIN_BOTTOM);
	$obj_pdf->SetFont('helvetica', '', 9);
	$obj_pdf->setFontSubsetting(false);
	$obj_pdf->AddPage();
	ob_start();


	?>
<?php
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title><?php $current_entry_type['name']; ?> Bill/Voucher Number <?php echo $entry_number; ?></title>

<style type="text/css">
	body {
		color:#000000;
		font:14px "Helvetica Neue","Lucida Grande","Helvetica Neue",Arial,sans-serif;
		margin:20px;
		padding:0;
	}
	
	#print-account-name {
		text-align:center;
		font-size:17px;
	}
	
	#print-account-address {
		text-align:center;
		font-size:14px;
	}
	
	#print-entry-type {
		text-align:center;
		font-size:15px;
	}
	
	table#print-entry-table {
		border:1px solid #000000;
		border-collapse: collapse;
		width:100%;
	}
	
	table#print-entry-table tr.tr-title {
		text-align:left;
		border:1px solid #000000;
		padding:5px 0 5px 2px;
	}
	
	table#print-entry-table tr.tr-title th {
		padding:5px 0 5px 5px;
		border:1px solid #000000;
	}
	
	table#print-entry-table td {
		padding:5px 0 5px 5px;
	}
	
	table#print-entry-table td.item {
		padding-right:35px;
		text-align:left;
	}
	
	table#print-entry-table td.last-item {
		padding-right:5px;
	}
	
	table#print-entry-table tr.tr-total {
		border-top: #000000;
		border-bottom:#0000ff;
		text-align:left;
	}
</style>

</head>
<body>
<?php

	echo"<font size =\"12\" align =\"center\">";
	echo"<br>";
	echo  $this->config->item('account_name');
	echo"<br>";
	echo $this->config->item('account_address');
	echo"<br>";
        echo"<br>";
	echo $current_entry_type['name'];
	echo "&nbsp;&nbsp;";echo"Entry";
	echo"</font>";
	echo"<br>";
	echo"<br>";
	echo"<br>";
	echo "Bill/Voucher Number : ".full_entry_number($entry_type_id, $entry_number);
	echo"<br>";
	echo"Forward Reference Id : ".$forward_ref_id;
	echo"<br>";
	echo"Backward Reference Id : ".$back_ref_id;
	echo"<br>";
	echo"Bill/Voucher Date : ".$entry_date;
	echo"<br>";
	echo"Vendor/Voucher Number : ".$vendor_voucher_number;

	echo"<br>";
	echo"<br>";

?>

        <table id="print-entry-table" align="center" cellpadding="2">
            <thead>
                <tr class="tr-title" align="center"><th><h4> Type</h4></th><th><h4> Ledger Account</h4></th><th><h4 > Dr Amount</h4></th><th><h4> Cr Amount</h4></th><th><h4> Party Name</h4></th><th><h4> Party Address</h4></th><th><h4> Fund</h4></th><th><h4> Income/Expense Type</h4></th></tr>
            </thead>
            <tbody>
                <?php
                $currency = $this->config->item('account_currency_symbol');
                $cheque = "";
                foreach ($ledger_data as $id => $row)
                {
                    echo "<tr class=\"tr-ledger\">";
                    echo"<td>" .$row['dc']."</td>";
                    if ($row['dc'] == "Dr")
                    {
                            echo "<td class=\"ledger-name item\">By " . $row['name'] . " </td>";
                    } else {
                            echo "<td class=\"ledger-name item\">To " . $row['name'] . " </td>";
                    }
                    if ($row['dc'] == "Dr")
                    {
                            echo "<td class=\"ledger-dr item\" align=\"center\"> " . $currency . " " . $row['amount'] . "</td>";
                            echo "<td class=\"ledger-cr last-item\"></td>";
                    } else {
                            echo "<td class=\"ledger-dr item\"></td>";
                            echo "<td class=\"ledger-cr last-item\" align=\"center\"> " . $currency . " " . $row['amount'] . "</td>";
                    }
                    echo"<td>".$row['secunitid']."</td>";
                    echo"<td>".$row['partyadd']."</td>";
                    echo"<td>".$row['fund_name']."</td>";
                    echo"<td>".$row['type']."</td>";
                    echo "</tr>";
                }
                echo "<tr class=\"tr-total\"><td></td><td class=\"total-name\" align=\"left\"> Total</td><td class=\"total-dr\" align=\"center\"> " . $currency . " " .  $entry_dr_total . "</td><td class=\"total-cr\" align=\"center\"> " . $currency . " " . $entry_cr_total . "</td></tr>";?>
        	</tbody>
        </table>
		<?php
        $cheque='';
        $this->db->select('name,bank_name,update_cheque_no')->from('cheque_print')->where('entry_no',$row['entry_id']);
        $ledger_q = $this->db->get();
        foreach($ledger_q->result() as $row)
        {
            $bank_name = $row->bank_name;
            $bank[] =$bank_name;
            $name= $row->name;
            $benif_name[] =$name;
            $cheque_no=$row->update_cheque_no;
            $cheque[] =$cheque_no;
        }
        $length=count($cheque);
        ?>
		<?php
		echo"<br>";
	    echo"<br>";
	    echo"<br>";
	    echo"<table>";

	    echo"<tr>";
		echo"<td>"."Narration : ".$entry_narration."</td>";
		echo"<td>"."Submitted By : ".$submitted_by."</td>";
		echo"</tr>";

		echo"<tr>";
		echo"<td>"."Verified By : ".$verified_by."</td>";
		echo"<td>"."Tag : "."</td>";
		echo"</tr>";

		echo"<tr>";
		echo"<td>"."Sanction Letter No. :".$sanc_letter_no."</td>";
		echo"<td>"."Sanction Letter Date :".$sanc_date."</td>";
		echo"</tr>";
		
		echo"<tr>";
		echo"<td>"."Sanction Letter Detail :".$sanc_type." ".$sanc_value."</td>";
		echo"</tr>";
		echo"</table>";
		?>
   		<?php
        if($ledger_q->num_rows() > 0){
            if( $cheque_no != NULL && $name != NULL)
	        {
                for($i=0; $i<$length; $i++)
                {
                    if($cheque[$i] != 1){
                  //  echo"<br>";
                    echo"Bank Name : " . $bank[$i] . "<br>";
                    echo"Beneficiary Name : " . $benif_name[$i] . "<br>";
                    echo"Cheque No : " . $cheque[$i] . "<br>";
                    }
                }
	        }
        }
        ?>

       
</body>
</html>


<?php
	$content = ob_get_contents();
	ob_end_clean();
	$obj_pdf->writeHTML($content, true, false, true, false, '');
	$obj_pdf->Output( $current_entry_type['name'].' entry_'.$entry_number.'.pdf', 'I');

?>

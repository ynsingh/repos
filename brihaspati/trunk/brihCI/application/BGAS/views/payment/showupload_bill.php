<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');?>

<?php
 /*       echo form_open('payment/showupload_bill');
        echo "<p>";
        echo form_label('Search By', 'search_by');
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_dropdown('search_by', $search_by, $search_by_active);
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_label('Text', 'text');
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_input($text);
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_submit('submit', 'Search');
        echo " ";
        echo "</p>";
        echo form_close();
*/?>


<table border=0 cellpadding=5 class="simple-table entry-view-table" width="100">
<thead>
        <tr>
            <th>Bill No</th>
            <th>Submit Date</th>
            <th>Submitter Id</th>
            <th>View Bill</th>
            <th>Expenses Type </th>
	    <th>Total Amount</th>
            <th>Decision</th>
	    <th>Approval Date</th>
	    <th>Approved Amount</th>
	    <th>Approved By</th>
	    <th>VC_Date</th>
	    <th>Bank Cash Account</th>
	    <th>Mode of Payment</th>
	    <th>Payment Status</th>
	    <th>Payment Date</th>
	     <th></th>
	    <th></th>
        </tr>
    </thead>
	<tbody>
		 <?php
			if(is_array( $bill_data)) {
			foreach( $bill_data as $row) {
	//		 foreach ($bill_data->result() as $row) {
				//print_r($bill_data);
                        	echo "<tr>";
                                echo "<td>".$row->bill_no."</td>";
                                echo "<td>".$row->submit_date."</td>";
                                echo "<td>".$row->submitted_by."</td>";
				//echo "<td>".anchor(('../uploads/Bills/'.$row->bill_name), $row->bill_name,$onclick)."</td>";
				$bill_array = explode('.', $row->bill_name);
        			$bill_ext = $bill_array[1];
        			if($bill_ext == "pdf")
           			    {
                			echo "<td class=\"thickbox\">".anchor(('../uploads/BGAS/Bills/'.$row->bill_name), $row->bill_name)."</td>";

          			    }
        			else{
               				echo "<td class=\"thickbox\">".anchor(('../uploads/BGAS/Bills/'.$row->bill_name), $row->bill_name,"class=\"thickbox\"")."</td>";
      				    }


		//		echo "<td class=\"thickbox\">".anchor(('../uploads/Bills/'.$row->bill_name), $row->bill_name,"class=\"thickbox\"")."</td>";
				echo "<td>".$row->expense_type."</td>";
				echo"<td>".$row->total_amount."</td>";
				echo "<td>".$row->decision."</td>";
				echo "<td>".$row->approval_date."</td>";
				echo "<td>".$row->approved_amount."</td>";
				echo "<td>".$row->approved_by."</td>";
				echo "<td>".$row->vc_date."</td>";
				echo "<td>".$row->bank_cash_account."</td>";
				echo "<td>".$row->mode_of_payment."</td>";
				echo "<td>".$row->payment_status."</td>";
				echo "<td>".$row->payment_date."</td>";

				if($row->decision=='HOLD'){
				 	echo "<td>" . anchor('payment/billapproval/' .  $row->bill_no , "Approve/Reject", array('title' => 'Approve,reject ' )) . "</td> ";
					//echo "<td>" . anchor('payment/voucherfilling/' .  $row->bill_no , "VoucherCreation", array('title' => 'VoucherCreation ' )) . " ";
					//echo "<td>" . anchor('payment/bill_printpreview/' .  $row->bill_no , "PrintPreview", array('title' => 'Bill Print Preview ' )) . "</td> ";
				}else if(($row->decision=='Approved') && ($row->vc_date=="1000-01-01 00:00:00")){
					//echo "<td>".VoucherCreation."</td>";
					echo "<td>" . anchor('payment/voucherfilling/' .  $row->bill_no , "VoucherCreation", array('title' => 'VoucherCreation ' )) . " ";
					//echo "<td>" . anchor('payment/bill_printpreview/' .  $row->bill_no , "PrintPreview", array('title' => 'Bill Print Preview ' )) . "</td> ";
				}
				else if(($row->decision=='Approved') && ($row->vc_date!="1000-01-01 00:00:00")){
					//echo "<td>".VoucherCreation."</td>";
					//echo "<td>" . anchor('payment/voucherfilling/' .  $row->bill_no , "VoucherCreation", array('title' => 'VoucherCreation ' )) . " ";
					echo "<td>" . anchor('payment/bill_printpreview/' .  $row->bill_no , "PrintPreview", array('title' => 'Bill Print Preview ' )) . "</td> ";
				}
				else if(($row->decision=='Rejected') && ($row->vc_date=="1000-01-01 00:00:00")){
                                        //echo "<td>".VoucherCreation."</td>";
                                        echo "<td>" . anchor('payment/reject_printpreview/' .  $row->bill_no , "PrintPreview", array('title' => 'PrintPreview ' )) . " ";
					}
				//else{
					//echo "<td>" . anchor('payment/voucherfilling/' .  $row->bill_no , "VoucherCreation", array('title' => 'VoucherCreation ' )) . " ";
					//echo "<td>" . anchor('payment/bill_printpreview/' .  $row->bill_no , "PrintPreview", array('title' => 'Bill Print Preview ' )) . "</td> ";
				//}
                                echo "</tr>";

                        }
			}
                        ?>

	</tbody>
</table>
	<div id="pagination-container"><?php echo $this->pagination->create_links(); ?></div>
	


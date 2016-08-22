<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');
//$this->load->model('User_model');
?>

<table border=0 cellpadding=5 class="simple-table entry-view-table" width="100">
<thead>
    <tr>
    <th>Bill No</th>
    <th>Purchase Order No</th>
    <th>Supplier Bill No</th>
    <th>Submit Date</th>
    <th>Submitted By</th>
    <th>Submitter ID</th>
    <th>Bill Name</th>
    <th>Expense Type</th>
    <th>Total Amount</th>
    <th>Narration</th>
    <th>Decision/Taken BY</th>
    <th>Current Location</th>
    <th>Entry ID</th>
    <th>VC Date</th>
    <th>Bank Cash Account</th>
    <th>Mode Of Payment</th>
    <th>Payment Status</th>
    <th>Payment Date</th>
    <th>Party ID</th>
    <th>Fund ID</th>
    <th>Expenditure Type</th>
    <th>Sanc Type</th>
    <th>Sanc Value</th>
    <th></th>
    <th></th>
    </tr>
</thead>
<tbody>
	<?php
	if(is_array( $bill_data)) {
		$i=1;
		foreach( $bill_data as $row)
		{
			if($i % 2 == 0)
                      		echo"<tr style=\"background-color:#87CEEB;\">";
              		else
                      		echo"<tr style=\"background-color:#AFEEEE;\">";
	//	echo "<tr>";
		if(($row->decision=='vchrcrn') && ($row->vc_date!="0000-00-00 00:00:00")){
 		echo "<td>" . anchor_popup('payment2/p2bill_printpreview/' .  $row->id , "<font color = #008000>".$row->id."</font>", array('title' => 'Bill Print Preview ', 'width' => '600', 'height' => '600')) . "</td>";}
		elseif($row->decision == 'Rejected'){
		echo "<td>" . anchor_popup('payment2/p2reject_printpreview/' .  $row->id , "<font color = #FF0000>".$row->id."</font>", array('title' => 'Bill Print Preview ', 'width' => '600', 'height' => '600')) . "</td>";}
	        else { echo "<td>".$row->id."</td>";}
                echo "<td>".$row->purchase_order_no."</td>";
                echo "<td>".$row->supplier_bill_no."</td>";
	        echo "<td>".$row->submit_date."</td>";
		$nameid1 = $row->submitted_by;
		$nameid = $this->User_model->get_user_name_femail($nameid1);
		if($nameid == '')
			echo "<td>".$row->submitted_by."</td>";
		else
		{
			echo "<td>";
			echo $nameid."<br>";
			echo $row->submitted_by;
			echo "</td>";
		}
		$idname1 = $row->submitter_id;
		$idname = $this->User_model->get_user_name_femail($idname1);
		if($idname == '')
			echo "<td>".$row->submitter_id."</td>";
		else
		{
			echo "<td>";
			echo $idname . "<br>";
			echo $row->submitter_id;
			echo "</td>";
		}
		$atts = array(
                	'width'      => '800',
                	'height'     => '600',
                	'scrollbars' => 'yes',
                	'status'     => 'yes',
                	'resizable'  => 'yes',
                	'screenx'    => '0',
                	'screeny'    => '0'
        	);
		$bill_array = explode('.', $row->bill_name);
		$bill_ext = $bill_array[1];
		if($bill_ext == "pdf")
			echo "<td class=\"thickbox\">".anchor_popup(('../uploads/Bills/'.$row->bill_name), $row->bill_name, $atts)."</td>";
		else
			echo "<td class=\"thickbox\">".anchor_popup(('../uploads/Bills/'.$row->bill_name), $row->bill_name,$atts,"class=\"thickbox\"")."</td>";
				    
			echo "<td>".$row->expense_type."</td>";
			echo "<td>".$row->total_amount."</td>";
                        echo "<td>".$row->narration."</td>";
			$this->db->select_max('id')->from('bill_approval_status')->where('bill_no',$row->id);
                        $maxbillid= $this->db->get();
                        foreach($maxbillid->result() as $row1)
                        {
                                $maxbill_id = $row1->id;
                                $this->db->select('forward_from')->from('bill_approval_status')->where('id',$maxbill_id);
                                $query_q = $this->db->get();
                                foreach($query_q->result() as $row2)
                                {
                                        $user_name = $row2->forward_from;
                                }
	                        

			}

			$username = $user_name;
			$username = $this->User_model->get_user_name_femail($username);
			$deci = $row->decision;
			$forward_to_1 = $row->current_location;
			$forward_to = $this->User_model->get_user_name_femail($forward_to_1);

			if($deci == "HOLD")
				echo "<td>".$row->decision."&nbsp"."TO"."&nbsp"."$forward_to"."</td>";
			elseif($deci == "vchrcrn")
				echo "<td>"."Voucher Created BY"."&nbsp"."$forward_to"."</td>";
			else
				echo "<td>".$row->decision."&nbsp"."BY"."&nbsp"."$username"."</td>";
			if($deci != "Rejected")
			{
				echo "<td>".$forward_to."</td>";
                        	echo "<td>".$row->entry_id."</td>";
                        	echo "<td>".$row->vc_date."</td>";
				echo "<td>".$row->bank_cash_account."</td>";
				echo "<td>".$row->mode_of_payment."</td>";
				echo "<td>".$row->payment_status."</td>";
				echo "<td>".$row->payment_date."</td>";
				echo "<td>".$row->party_id."</td>";
				echo "<td>".$row->fund_id."</td>";
				echo "<td>".$row->expenditure_type."</td>";
				echo "<td>".$row->sanc_type."</td>";
                        	echo "<td>".$row->sanc_value."</td>";
			}

			$user = $this->session->userdata('user_name');
  //              $this->db->select('bill_voucher_create.id as id, bill_voucher_create.submitted_by as submitted_by, bill_voucher_create.expense_type as expense_type, bill_voucher_create.total_amount as total_amount');
    //            $this->db->from('bill_approval_status')->join('bill_voucher_create', 'bill_approval_status.bill_no = bill_voucher_create.id')->where('status',NULL);
               // if ( ! check_access('administer'))
              //  {
          //              $this->db->where('forward_to',$user);
            //    }               
             //   $data['aut_q'] = $this->db->get(); 
			if(($forward_to_1 == $user)||(check_access('administer'))){
			if($row->decision=='HOLD')
			{
				echo "<td>" . anchor('payment2/p2billapproval/' .  $row->id , "Approve/Reject", array('title' => 'Approve,reject ' )) . "</td> ";
			}
			else if(($row->decision=='Approved') && ($row->vc_date=="0000-00-00 00:00:00"))
			{
                   		echo "<td>" . anchor('payment2/p2billapproval/' .  $row->id , "Approve/Reject", array('title' => 'Approve,reject ' )) . " ";
                        }
			else if(($row->decision=='vchrcrn') && ($row->vc_date=="0000-00-00 00:00:00")){
				echo "<td>" . anchor('payment2/p2voucherfilling/' .  $row->id , "VoucherCreation", array('title' => 'VoucherCreation ' )) . " ";
			}
			else if(($row->decision=='vchrcrn') && ($row->vc_date!="0000-00-00 00:00:00"))
			{
				echo "<td>" . anchor_popup('payment2/p2bill_printpreview/' .  $row->id , 'PrintPreview', array('title' => 'Bill Print Preview ', 'width' => '600', 'height' => '600')) . "</td>";
			}
		//	else if(($row->decision=='Rejected') && ($row->vc_date=="0000-00-00 00:00:00"))
			else if($row->decision=='Rejected') 
			{
		        echo "<td><td><td><td><td><td><td><td><td><td><td><td><td>" . anchor('payment2/p2reject_printpreview/' .  $row->id , "PrintPreview", array('title' => 'PrintPreview ' )) . " ";
			}}
		echo "</tr>";
		$i++;
		}
	}
?>

</tbody>
</table>
	<div id="pagination-container"><?php echo $this->pagination->create_links(); ?></div>

	


<?php
	/*GUI Modification(table alignment) and added new table Notifications by @RAHUL */
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
        echo "<tr valign=\"top\">";

        echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";

        echo "<tr>";
        echo "<td style=\"padding: 0px 8px 8px 20px;\">";
        echo "<table width=\"100%\" border=\"1\" style=\"color: black; border-collapse:collapse; border:1px solid #BBBBBB;\">";
        
	echo "<tr style=\"text-align:center; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px;\">";
        echo "Account Details";
        echo "</td>";
        echo "</tr>";

	echo "<td style=\"padding: 8px 8px 8px 20px;\">";
	echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
	
	echo "<tr>";
	echo "<td style=\"padding: 8px 8px 8px 20px;\">";
	echo $this->config->item('account_name');
        echo "</td>";
        echo "</tr>";
	
	echo "<td style=\"padding: 8px 8px 8px 20px;\">Welcome back,<strong>";
	if( check_access('administer'))
	{
        	echo $this->config->item('account_name');
	}
      	else
	{
      		echo $this->session->userdata('user_name');
	}
	echo "</strong></td>";
	
	echo "<tr>";
        echo "<td style=\"padding: 8px 8px 8px 20px;\">Account for Financial Year<strong>";
	echo " ".date_mysql_to_php_display($this->config->item('account_fy_start')) . " - " . date_mysql_to_php_display($this->config->item('account_fy_end'));
        echo "</strong></td></tr>";
	
	if ($this->config->item('account_locked') == 1)
	{
		echo "<tr>";
        	echo "<td>Account is currently <strong>locked</strong> to prevent any further modifications.</td>";
		echo "</tr>";
	}

	echo "</table>";
	echo "</td>";

	echo "</table>";
        echo "</td>";
        echo "</tr>";

        echo "<tr>";
        echo "<td style=\"padding: 8px 8px 8px 20px;\">";
        echo "<table width=\"100%\" border=\"1\" style=\"color: black; border-collapse:collapse; border:1px solid #BBBBBB;\">";
        echo "<tr style=\"text-align:center; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px;\">";
        echo "Bank and Cash accounts";
        echo "</td>";
        echo "</tr>";

	echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
     	if ($bank_cash_account)
     	{
        	echo "<table style=\"padding: 8px 8px 8px 20px;\">";
            	echo "<tbody>";
          	foreach ($bank_cash_account as $id => $row)
              	{
            		echo "<tr>";
             		echo "<td style=\"padding: 8px 8px 8px 20px;\">" . anchor('report/ledgerst/' . $row['id'], $row['name'], array('title' => $row['name'] . ' Statement')) . "</td>";
               		echo "<td style=\"padding: 8px 8px 8px 20px;\">" . convert_amount_dc($row['balance']) . "</td>";
                 	echo "</tr>";
          	}
         	echo "</tbody>";
         	echo "</table>";
     	}
     	else
  	{
    		echo "You have not created any bank or cash account";
      	}
	echo "</table";
	echo "</td>";
 
	echo "</table>";
        echo "</td>";
        echo "</tr>";

        echo "<tr>";
        echo "<td style=\"padding: 8px 8px 8px 20px;\">";
        echo "<table width=\"100%\" border=\"1\" style=\"color: black; border-collapse:collapse; border:1px solid #BBBBBB;\">";
        echo "<tr style=\"text-align:center; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px;\">";
	echo "Account Summary";
        echo "</td>";
        echo "</tr>";

	echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
	echo "<table style=\"padding: 8px 8px 8px 20px;\">";
        echo "<tbody>";
      	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">Assets Total</td><td>" . convert_amount_dc($asset_total) . "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">Liabilities Total</td><td>" . convert_amount_dc($liability_total) . "</td></tr>";
     	echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">Incomes Total</td><td>" . convert_amount_dc($income_total) . "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 20px;\">Expenses Total</td><td>" . convert_amount_dc($expense_total) . "</td></tr>";
     	echo "</tbody>";
	echo "</table>";
	echo "</td>";

        echo "</table>";
        echo "</td>";
        echo "</tr>";

        echo "</table>";
        echo "</td>";

        if(check_access('view log'))
        {
                echo "<td style=\"padding: 0px 8px 8px 20px;\">";
                echo "<table width=\"100%\" height=\"100%\" border=\"1\" style=\"color:black; border-collapse:collapse; border:1px solid #BBBBBB;\">";

                echo "<tr style=\"text-align:center; font-weight:bold; background-color:#66C1E6;\">";
                echo "<td style=\"padding: 8px 8px 8px 20px;\">";
                echo "Recent Activity";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
		echo "<div style=\"overflow-y:scroll; height:400px\">";
                if ($logs)
                {
                        echo "<ul style=\"margin-left:30px; padding:0px; list-style-type:disc; \">";
                        foreach ($logs->result() as $row)
                        {
                                echo "<li>" . $row->message_title . "</li>";
                        }
                        echo "</ul>";
                }
                else
                {
                        echo "No Recent Activity";
                }
                echo "</div>";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td style=\"padding:10px; border-top:1px solid #BBBBBB; \">";
                if ($logs)
                {
                        echo "<span>";
                        echo anchor("log", "more...", array('class' => 'anchor-link-a'));
                        echo "</span>";
                }
                echo "</td>";
                echo "</tr>";

                echo "</table>";
                echo "</td>";
        }
	
	if($aut_q->num_rows() > 0)
	{
        	echo "<td style=\"padding: 0px 8px 8px 20px;\">";
        	echo "<table width=\"100%\" border=\"1\" style=\"color: black; border-collapse:collapse; border:1px solid #BBBBBB;\">";
        	echo "<tr style=\"text-align:center; font-weight:bold; background-color:#66C1E6;\">";
        	echo "<td colspan=\"4\" style=\"padding: 8px 8px 8px 20px;\">";
        	echo "Notifications";
        	echo "</td>";
        	echo "</tr>";

		echo "<td>";
		echo "<div style=\"overflow-y:scroll; height:440px\">";
		echo "<table width=\"100%\" border=\"0\">";
        	echo "<tr style=\"background-color:#EEEEEE; text-align:center;\">";
		echo "<td style=\"padding: 8px 8px 8px 20px;\">";
        	echo "<b>Bill Number</b>";
        	echo "</td>";
        	echo "<td style=\"padding: 8px 8px 8px 20px;\">";
        	echo "<b>Submitted By</b>";
        	echo "</td>";
        	echo "<td style=\"padding: 8px 8px 8px 20px;\">";
        	echo "<b>Expense Type</b>";
        	echo "</td>";
        	echo "<td style=\"padding: 8px 8px 8px 20px;\">";
		echo "<b>Total Amount</b>";
        	echo "</td>";
        	echo "<td>";
        	echo "</td>";
        	echo "</tr>";

        	foreach ($aut_q->result() as $row)
        	{
			//$this->db->select_max('id')->from('bill_approval_status')->where('bill_no',$row->id);
			$this->db->select('decision')->from('bill_voucher_create')->where('id',$row->id);
        		$ma_xauth = $this->db->get();
        		foreach($ma_xauth->result() as $row_a1)
        		{
                		//$ma_xau_th = $row_a1->id;
                		//$this->db->select('status')->from('bill_approval_status')->where('id',$ma_xau_th);
                		//$ma_xim_auth = $this->db->get();
                		//$ma_xim_auth1 = $ma_xim_auth->row();
                		//$ma_xim_auth2 = $ma_xim_auth1->status;
                		$ma_xim_auth2 = $row_a1->decision;
        		}
			echo "<tr>";
			echo"<td style=\"padding: 8px 8px 8px 20px;\">";
                	echo  $row->id;
                	echo"</td>";
                	echo"<td style=\"padding: 8px 8px 8px 20px;\">";
                	echo  $row->submitted_by;
                	echo"</td>";
                	echo"<td style=\"padding: 8px 8px 8px 20px;\">";
                	echo  $row->expense_type;
                	echo"</td>";
                	echo"<td style=\"padding: 8px 8px 8px 20px;\">";
                	echo  $row->total_amount;
                	echo"</td>";
                	echo"<td style=\"padding: 8px 8px 8px 20px;\">";
			//if($ma_xim_auth2 == "voucherapprove")
			if($ma_xim_auth2 == "vchrcrn")
			{
				echo  anchor('payment2/p2voucherfilling/' .  $row->id , "Bill Pending For Voucher Creation", array('title' => 'VoucherCreation ' ));
			}
			else
			{
                		echo  anchor('payment2/p2billapproval/' .  $row->id , "Bill Pending For Your Action", array('title' => 'Approve,reject ' ));
			}
                	echo"</td>";
                	echo"</tr>";
        	}
		echo "</table>";
		echo "</td>";

        	echo "</table>";
        	echo "</td>";
	}

        echo "</tr>";
        echo "</table>";
?>

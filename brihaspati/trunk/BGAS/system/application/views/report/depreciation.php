<?php
	$this->load->model('Ledger_model');
	$Dep_method=0;
	if ( ! $print_preview)
	{
		echo "<p>";
		echo "<span id=\"tooltip-target-1\">";
		$inputpreferences   = array( 'name'        => 'commentflag',
		                     'id'          => 'commentflag',
		                     'value'       => 'N',
				     'checked'     => $budget_over,
				                        
		                     );
		
		echo form_checkbox($inputpreferences) . " Straight Line Method"; 
		echo "</span>";
		echo "<span id=\"tooltip-content-1\">Calculate Depreciation With Staight Line Method.</span>";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

		echo "<span id=\"tooltip-target-2\">";
		$inputpreferences1   = array( 'name'        => 'commentflag',
		                     'id'          => 'commentflag',
		                     'value'       => 'N',
		                     'checked'     => false,
		                     'disabled'    => 'disable',
		                    );
		echo form_checkbox($inputpreferences1 ) . " Double Decline Method";
		echo "</span>";
		echo "<span id=\"tooltip-content-2\">Calculate Depreciation With Double Decline Method.</span>";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "<span id=\"tooltip-target-3\">";
		 $inputpreferences2   = array( 'name'        => 'commentflag',
		                     'id'          => 'commentflag',
		                     'value'       => 'N',
		                     'checked'     => false,
		                     'disabled'    => 'disable',
		                    );
		echo form_checkbox($inputpreferences2) . " Sum-Of-Years-Digits Method";
		echo "</span>";
		echo "<span id=\"tooltip-content-3\">Calculate Depreciation With Sum-Of-Years-Digits Method.</span>";
		echo "</p>";
   	}
     	$gross_expense_total = 0;
	echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"80%\">";
	echo "<thead><tr><th>S.No</th><th>Asset Name</th><th>Date of Purchase</th><th>Total Cost</th><th>Dep.Amount</th><th>Current Value</th></tr></thead>";
	echo "<tbody>";
		 $i=1;
		 $account_code = $this->Budget_model->get_account_code('Fixed Assets');
		 $this->db->select('name');
                 $this->db->from('ledgers')->where('code LIKE', $account_code.'%');
                 $gross_expense_list_q = $this->db->get();
                 foreach($gross_expense_list_q->result() as $row)
                        {
                        	$name=$row->name;
		        	/*load database pico*/
                        	$logndb = $this->load->database('pico', TRUE);
                        	$this->logndb =& $logndb;
                        	$this->logndb->select('a.ERPMIM_ID, a.ERPMIM_Depreciation_Percentage, a.ERPMIM_Item_Brief_Desc, b.IRD_Rate, b.IR_Item_ID, b.IRD_WEF_Date');
                        	$this->logndb->from('erpm_item_master a, erpm_item_rate b')->where('a.ERPMIM_ID  = b.IR_Item_ID ')->where('a.ERPMIM_Item_Brief_Desc ',$name );
                        	$this->logndb->group_by("ERPMIM_Item_Brief_Desc");
                        	$user_data = $this->logndb->get();
				if($user_data->num_rows() > 0){
			        foreach($user_data->result() as $row1)
                                {
					
					 $ERPMIM_Item_Brief_Desc= $row1->ERPMIM_Item_Brief_Desc;
					 $IRD_WEF_Date=$row1->IRD_WEF_Date;
					 $IRD_Rate= $row1->IRD_Rate;
				
					 echo "<tr>";
					 echo "<td>" . $i . '.' . "</td>";
					 echo "<td>" . anchor('report/duplicate_entry/'. $ERPMIM_Item_Brief_Desc,$ERPMIM_Item_Brief_Desc , array('title' => $ERPMIM_Item_Brief_Desc . ' duplicate_entry', 'style' => 'color:#000000')) . "</td>";
				 	 echo "<td>" . $IRD_WEF_Date . "</td>";
					 $value =$this->Ledger_model->get_asset_amount($row1->ERPMIM_Item_Brief_Desc);
					 $my_values = explode('#',$value['key']);
					 echo "<td>" . $my_values[0]  . "</td>";
					 echo "<td>" . $my_values[1]  . "</td>";
					 echo "<td>" . $my_values[2]  . "</td>";
					 echo "</tr>";
					 $i++;
				}
				}
				$this->logndb->close();
		}
	echo "</tbody>";
        echo "</table>";
?>

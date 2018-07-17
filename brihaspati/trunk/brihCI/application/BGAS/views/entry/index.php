<?php
	/*GUI modification(table alignment) added by @RAHUL */
	echo "<table width=\"100%\" border=\"0\">";
        echo "<tr valign=\"top\">";

	echo "<td>";
	echo "<table width=\"100%\" border=\"0\">";

	echo "<td>";
	echo "<table width=\"100%\" border=\"0\">";
	echo "<tr>";
	echo "<li>" . anchor('entry/sort/'.$entry_sort , 'Sorting by Updatedate') . "</li>";
	echo "</tr>";
	echo "</table>";
	echo "</td>";

	echo "<tr>";
	echo "<td>";
        echo "<table width=\"100%\" border=\"0\">";
	echo "<tr>";
	echo form_open('entry/' . $entry_path);
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
	echo "</tr>";
        echo "</table>";
        echo "</td>";
	echo "</tr>";

 	echo "<tr style = \"text-align:center;\">";
        echo "<td>";
        echo "<table cellpadding=\"10\" width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse; border:1px solid #BBBBBB;\">";
	echo "<tr style=\"border-bottom: 1px solid #BBBBBB;\">";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Id";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Fwd Ref Id";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Bkwd Ref Id";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Date";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Update Date";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Voucher No";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Vendor/Voucher No";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Ledger Account";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Type";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "DR Amount";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "CR Amount";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Submitted By";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Verified By";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo "Payment/Receipt By";
	echo "</td>";
	echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
	echo " ";
	echo "</td>";
	echo "</tr>";

	setlocale(LC_MONETARY, 'en_IN');
	$check = 1;
	$i = 1;
 	foreach ($entry_data->result() as $row)
     	{
    		$status = $row->status;
    		$current_entry_type = entry_type_info($row->entry_type);
          	$value = '';
          	if($search != "name") 
		{
              		$value = $this->Ledger_model->get_entry_name($row->id, $row->entry_type);
             	}
             	else 
		{
            		$value = $this->Ledger_model->get_entry_name_match($row->id, $row->entry_type, $text['value']);
                     	if($value != NULL) 
			{
                     		$check++;
                     	}
            	}
       		if($value != NULL) 
		{
			if($i % 2 == 0)
                    	echo "<tr style=\"border-bottom: 1px solid #BBBBBB; background-color:#87CEEB\">";
                      	else
                   	echo "<tr style=\"border-bottom: 1px solid #BBBBBB; background-color:#AFEEEE\">";
              		$this->db->select('name,bank_name,ledger_id,update_cheque_no,paymentreceiptby')->from('cheque_print')->where('entry_no',$row->id);
                 	$ledger_q = $this->db->get();
                	$no_of_row=$ledger_q->num_rows();
                 	echo "<td style=\"text-align:center;\">" . $row->id . "</td>";
                 	echo "<td style=\"text-align:center;\">" . $row->forward_refrence_id . "</td>";
               		echo "<td style=\"text-align:center;\">" . $row->backward_refrence_id . "</td>";
                     	echo "<td style=\"text-align:center;\">" . date_mysql_to_php_display($row->date) . "</td>";
                    	echo "<td style=\"text-align:center;\">" . date_mysql_to_php_display($row->update_date) . "</td>";
                     	echo "<td style=\"text-align:center;\">" . anchor('entry/view/' . $current_entry_type['label'] . "/" . $row->id, full_entry_number($row->entry_type, $row->number), array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a')) . "</td>";
                   	echo"<td style=\"text-align:center;\">".$row->vendor_voucher_number."</td>";
                      	echo "<td style=\"text-align:center;\">";
                    	echo $this->Tag_model->show_entry_tag($row->tag_id);
                  	echo $value . "<br>";
                      	echo "Narration: " . $row->narration;
			echo "</td>";
			echo "<td style=\"text-align:center;\">" . $current_entry_type['name'] . "</td>";
                     	echo "<td style=\"text-align:center;\">" . money_format('%!i', $row->dr_total) . "</td>";
                      	echo "<td style=\"text-align:center;\">" . money_format('%!i', $row->cr_total) . "</td>";
                   	echo "<td style=\"text-align:center;\">" . $row->submitted_by . "</td>";
                   	echo "<td style=\"text-align:center;\">" ;
        		//echo "<td>" . anchor('entry/edit/' . $current_entry_type['label'] . "/" . $row->id , "Edit", array('title' => 'Edit ' . $current_entry_type['name'] . ' Entry', 'class' => 'red-link')) . " ";
        //         	echo " &nbsp;" . anchor('entry/delete/' . $current_entry_type['label'] . "/" . $row->id , img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Delete ' . $current_entry_type['name'] . ' Entry', 'class' => "confirmClick", 'title' => "Delete entry")), array('title' => 'Delete  ' . $current_entry_type['name'] . ' Entry')) . " ";
                	if($status != 3)
			{
				$nme1 = explode(",", $row->verified_by);
				$k = sizeof($nme1);
				for($j=0; $j<$k; $j++)
				{
					echo $nme1[$j];
					echo "<br>";
				}
                     		echo " &nbsp;" . anchor('entry/verify/' . $current_entry_type['label'] . "/" . $row->id,'Verify' ,  array('title' => 'Verify ' . $current_entry_type['name']. ' Entry', 'class' => 'anchor-link-a')) . " ";
                   	}
			else
			{
				$nme1 = explode(",", $row->verified_by);
				$k = sizeof($nme1);
				for($j=0; $j<$k; $j++)
				{
					echo $nme1[$j];
					echo "<br>";
				}
                    		//echo $row->verified_by. " ";
                 	}
                 	echo "<td style=\"text-align:center;\">" ;
                	if( $no_of_row == 1)
                  	{
                 		foreach($ledger_q->result() as $row1)
                       		{
                            		$bank_name = $row1->bank_name;
                                        $name= $row1->name;
                                        $ledger_id= $row1->ledger_id;
                                        $update_chequ_no=$row1->update_cheque_no;
                                   	$this->db->select('cheque_print_status, cheque_bounce_status, No_of_bounce_cheque')->from('cheque_print')->where('ledger_id', $ledger_id)->where('entry_no',$row->id);
                                 	$cheque_status = $this->db->get();
                                  	foreach($cheque_status->result() as $row2)
                               		{
                                		$cheque_print_status = $row2->cheque_print_status;
                                        	$cheque_bounce_status = $row2->cheque_bounce_status;
                                      		$No_of_bounce_cheque = $row2->No_of_bounce_cheque;
					}
                                   	//Print cheque initially.........
					if($cheque_print_status == 0 && $cheque_bounce_status == 0)
                                    	{
                                    		//echo anchor('entry/cheque/' .  $current_entry_type['label'] . "/" . $row->id, 'Cheque/DD/BT', array('title' => 'Print Cheque', 'width' => '600', 'height' => '600', 'class' => 'anchor-link-a'));
                                    		echo anchor('entry/cheque/' .  $current_entry_type['label'] . "/" . $row->id, $this->BGAS_model->getprtype($row1->paymentreceiptby), array('title' => 'Print Cheque', 'width' => '600', 'height' => '600', 'class' => 'anchor-link-a'));
                                            	echo"<br>";
                                   	}
                                	//Print cheque if bounced..........
                               		if($cheque_print_status == 1 && $cheque_bounce_status == 0 || $cheque_print_status == 1 && $cheque_bounce_status == 1)
                             		{
                               			echo anchor('entry/cheque_bounce/' .  $current_entry_type['label'] . "/" . $row->id, 'Cancle Cheque/DD/BT', array('title' => 'Print Cheque', 'width' => '600', 'height' => '600', 'class' => 'anchor-link-a'));
                                	}
               			}
			}
			elseif( $no_of_row > 1)
			{
                		foreach($ledger_q->result() as $row1)
                		{
                        		$ledger_id= $row1->ledger_id;
                        		$update_chequ_no=$row1->update_cheque_no;
                		}
                		if($ledger_id != NULL &&  $update_chequ_no != NULL)
                		{
                        		if($update_chequ_no != '0')
					{
                                		echo anchor('entry/cheque/' .  $current_entry_type['label'] . "/" . $row->id, $this->BGAS_model->getprtype($row1->paymentreceiptby), array('title' => 'Print Cheque', 'width' => '600', 'height' => '600', 'class' => 'anchor-link-a'));
                        		}
                		}
        		}
			else
			{
                      		echo "Cash";
        		}
                    	echo "<td style=\"text-align:center;\">" ;
			echo " &nbsp;" . anchor_popup('entry/printpreview/' . $current_entry_type['label'] . "/" . $row->id , img(array('src' => asset_url() . "assets/bgas/images/icons/print.png", 'border' => '0', 'alt' => 'Print ' . $current_entry_type['name'] . ' Entry')), array('title' => 'Print ' . $current_entry_type['name']. ' Entry', 'width' => '600', 'height' => '600')) . " ";
                     	echo " &nbsp;" . anchor_popup('entry/email/' . $current_entry_type['label'] . "/" . $row->id , img(array('src' => asset_url() . "assets/bgas/images/icons/email.png", 'border' => '0', 'alt' => 'Email ' . $current_entry_type['name'] . ' Entry')), array('title' => 'Email ' . $current_entry_type['name'] . ' Entry', 'width' => '500', 'height' => '300')) . " ";
                      	echo " &nbsp;" . anchor('entry/download/' . $current_entry_type['label'] . "/" . $row->id , img(array('src' => asset_url() . "assets/bgas/images/icons/save.png", 'border' => '0', 'alt' => 'Download ' . $current_entry_type['name'] . ' Entry', 'title' => "Download entry")), array('title' => 'Download  ' . $current_entry_type['name'] . ' Entry')) . " ";
                   	echo " &nbsp;" . anchor('entry/pdf/' . $current_entry_type['label'] . "/" . $row->id , img(array('src' => asset_url() . "assets/bgas/images/icons/pdf.jpeg", 'border' => '0', 'alt' => 'Download Pdf ' . $current_entry_type['name'] . ' Entry', 'title' => "Download entry in pdf")), array('title' => 'Download  ' . $current_entry_type['name'] . ' Entry')) . "</td>";
                  	echo "</tr>";
			$i++;
           	}
  	}
     	if($check == "1" && $search == "name")
	{
     		$this->messages->add($text['value'] . ' is not found.', 'error');
             	redirect('entry/' . $entry_path);
     	}

        echo "</table>";
        echo "</td>";
        echo "</tr>";

	echo "<td>";
        echo "<table width=\"100%\" border=\"0\">";
        echo "<tr>";
	if($search == "") 
	{
		echo $this->pagination->create_links();
	}
        echo "</tr>";
        echo "</table>";
        echo "</td>";


	echo "</table>";
	echo "</td>";

	echo "<td>";
        echo "<table id=\"tag-sidebar\"style=\"color: black; border-collapse:collapse; border:1px solid #BBBBBB;\">";

	echo "<td>";
        echo "<table width=\"100%\" border=\"0\">";
	echo "<tr>";
	$this->load->view('sidebar/tag', $tag_id);
	echo "</tr>";
        echo "</table>";
        echo "</td>";

	echo "</table>";
        echo "</td>";

	echo "</table>";
        echo "</td>";


?>

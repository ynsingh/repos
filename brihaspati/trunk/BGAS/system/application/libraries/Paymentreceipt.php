<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class paymentreceipt
{

	function Paymentreceipt()
        {
                return;
        }
		
	function payment_receipt($type, $acc, $database)
	{
		$i=0;
		$c=0;
		$sum=0;
		$total=0;
		$total1=0;
		$total2=0;
		$pre_total=0;
		$type_total = 0;
		$paymentlist2 = "";
		$receiptlist2="";
		$CI =& get_instance();
		$this->counter = $c;
		$CI->db->from('settings');
        	$detail = $CI->db->get();
        	foreach ($detail->result() as $row)
        	{
            		$date1 = $row->fy_start;
            		$date2 = $row->fy_end;
        	}
            		$fy_start=explode("-",$date1);
            		$fy_end=explode("-",$date2);

        		$curr_year = $fy_start[0] ."-" .$fy_end[0];
        		$prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
			$CI->load->model('Payment_model');
			$CI->load->library('session');
        		$date1 = $CI->session->userdata('date1');
        		$date2 = $CI->session->userdata('date2');
			$current_active_account = $CI->session->userdata('active_account');
			if($type == "Payment")
        		{
    				$CI->db->select('name,code,id')->from('groups')->where('parent_id <=',4)->where('parent_id !=',3)->where('parent_id !=',0);
            			$main = $CI->db->get();
    				$no_row = $main->num_rows();
            			$main_result= $main->result();
    				foreach($main_result as $row)
            			{
                			$name = $row->name;
                			$code =$row->code;
                			$ledg_id = $row->id;
                			$dr_sum = 0;
                			$cr_sum = 0;
                			$dr_sum_total= 0;
                			$cr_sum_total=0;
					if($name !=  'Depreciation' && $name !=  'Current Assets' && $name !=  'Committed Fund')
                			{
						$CI->db->from('ledgers')->where('code LIKE', $code . '%')->where('type !=', '1');
                				$child_ledger_q = $CI->db->get();
						foreach ($child_ledger_q->result() as $row)
                    				{
						$ledger_code = $row->code;
                        			$ledger_code = substr($ledger_code, 0, 2);
                        			if($ledger_code  != '40')
                        			{
                            			$CI->db->select_sum('amount', 'drtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $row->id)->where('entry_items.dc', 'D');
                            			$CI->db->where('date >=', $date1);
                            			$CI->db->where('date <=', $date2);
                            			$dr_total_q = $CI->db->get();
                            			if ($dr_total = $dr_total_q->row())
						{
                              			$dr_total=$dr_total->drtotal;
                            			}
                            			$total = float_ops($total, $dr_total, '+');
                        			}elseif($ledger_code == '40')
						{
                    				$CI->db->select('entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $row->id);		
                            			$CI->db->where('date >=', $date1);
            					$CI->db->where('date <=', $date2);
                            			$result11 =$CI->db->get();
                            			$entry_result = $result11->result();
                            			foreach($entry_result as $query_row)
                            			{
                                		$dc = $query_row->entry_items_dc;
                                		if($dc == "D"){
                                    		$dr_sum = $query_row->entry_items_amount;
                                    		$dr_sum_total = $dr_sum_total + $dr_sum;
                                		}else{
                                    		$cr_sum = $query_row->entry_items_amount;
                                    		$cr_sum_total = $cr_sum_total + $cr_sum;
                                		}
                            			}
                            			$total = $dr_sum_total - $cr_sum_total;
                        			}
						}
						if($acc == "view" && $database == "NULL" )
                    				{
						echo "<tr class=\"tr-group\">";
                				echo "<td class=\"td-group\">";
                				echo "&nbsp;" .  $name;
                				echo "</td>";		
                        			echo "<td align=\"right\">" . convert_amount_dc($total) . "</td>";
						//echo "</tr>";
			//code for writing xml... 
			$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
                        $file_name="Payment"."-".$current_active_account."-".$prev_year.".xml";
                        $tt=$acctpath."/".$file_name;
			if(file_exists($tt))
                        {
				$doc = new DomDocument();
            			$doc->formatOutput = true;
            			$doc->load($tt);
            			$xpath = new DomXPath($doc);

            			$xpath->query("/Payment/Payment_Name/Group_Name");
            			$xpath->query("/Payment/Payment_Name/Amount");
            			$xpath->query("/Payment/Payment_Name/Group_ID");

            			$paymentnode1 = $xpath->query("/Payment/Payment_Name/Group_Name");
            			$paymentnode2 = $xpath->query("/Payment/Payment_Name/Amount");
            			$paymentnode3 = $xpath->query("/Payment/Payment_Name/Group_ID");
            			$paymentlist1 = @$paymentnode1->item($i)->nodeValue;
            			$paymentlist2 = @$paymentnode2->item($i)->nodeValue;
            			$paymentlist3 = @$paymentnode3->item($i)->nodeValue;
			}//if xml...
				$type_total = $type_total + $paymentlist2;
				$i++;
				if($paymentlist2 == 0)
                        	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        	else
                        	echo "<td align=\"right\">" . convert_amount_dc($paymentlist2) . "</td>"; 
				echo "</tr>";
                    	}//if view
			
			if(($acc == "CF") && ($type == "Payment") && ($database != "NULL"))
                	{
                        $this->xml_creation($type,$ledg_id,$database,$name,$curr_year, $total);
                    	}
                    	$total1=float_ops($total1, $total, '+');
                    	$this->total=$total1;
                    	$total=0;
			$this->prev_total = $type_total;  
			}//if
		}//foreach
		}//if
		else
        	{
		$CI->db->select('name,code,id')->from('groups')->where('parent_id <=',3)->where('parent_id !=',0);
        	$main = $CI->db->get();
        	$no_row = $main->num_rows();
        	$main_result= $main->result();
        	foreach($main_result as $row)
            	{
            	$name = $row->name;
            	$code =$row->code;
                $ledg_id = $row->id;
                $dr_sum = 0;
                $cr_sum = 0;
                $dr_sum_total= 0;
                $cr_sum_total=0;
		if($name !=  'Depreciation' && $name !=  'Current Assets' && $name !=  'Committed Fund')
                {
			$CI->db->from('ledgers')->where('code LIKE', $code . '%')->where('type !=', '1');
            		$child_ledger_q = $CI->db->get();
            		foreach ($child_ledger_q->result() as $row)
                    	{
                        $ledger_code = $row->code;
                        $ledger_code = substr($ledger_code, 0, 2);
                        if($ledger_code != "30")
                        {
                    	$CI->db->select_sum('amount', 'crtotal')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $row->id)->where('entry_items.dc', 'C');
                    	$CI->db->where('date >=', $date1);
                        $CI->db->where('date <=', $date2);
                        $cr_total_q = $CI->db->get();
                        if ($cr_total = $cr_total_q->row())
			{
                        	$cr_total=$cr_total->crtotal;
                        }
                            $total = float_ops($total, $cr_total, '+');
                        }else{
                        $CI->db->select('entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc')->from('entry_items')->join('entries', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $row->id);
                       	$CI->db->where('date >=', $date1);
                    	$CI->db->where('date <=', $date2);
                        $result11 =$CI->db->get();
                        $entry_result = $result11->result();
                        foreach($entry_result as $query_row)
                        {
                        	$dc = $query_row->entry_items_dc;
                                if($dc == "D"){
                                    $dr_sum = $query_row->entry_items_amount;
                                    $dr_sum_total = $dr_sum_total + $dr_sum;
                                }else{
                                    $cr_sum = $query_row->entry_items_amount;
                                    $cr_sum_total = $cr_sum_total + $cr_sum;
                                }
                       	}
                            	$total = $cr_sum_total - $dr_sum_total;
                        }
                    	}
			if($acc == "view" && $database == "NULL" )
                    	{
                		echo "<tr class=\"tr-group\">";
    	        		echo "<td class=\"td-group\">";
            			echo "&nbsp;" .  $name;
            			echo "</td>";
            			echo "<td align=\"right\">" . convert_amount_dc(-$total) . "</td>";

			$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
                        $file_name="Receipt"."-".$current_active_account."-".$prev_year.".xml";
                        $tt=$acctpath."/".$file_name;
			if(file_exists($tt))
                        {
				$doc = new DomDocument();
            			$doc->formatOutput = true;
            			$doc->load($tt);
            			$xpath = new DomXPath($doc);

            			$xpath->query("/Receipt/Receipt_Name/Group_Name");
            			$xpath->query("/Receipt/Receipt_Name/Amount");
            			$xpath->query("/Payment/Payment_Name/Group_ID");

            			$receiptnode1 = $xpath->query("/Receipt/Receipt_Name/Group_Name");
            			$receiptnode2 = $xpath->query("/Receipt/Receipt_Name/Amount");
            			$receiptnode3 = $xpath->query("/Receipt/Receipt_Name/Group_ID");
            			$receiptlist1 = @$receiptnode1->item($i)->nodeValue;
            			$receiptlist2 = @$receiptnode2->item($i)->nodeValue;
            			$receiptlist3 = @$receiptnode3->item($i)->nodeValue;
			}//if xml..
				$type_total = $type_total + $receiptlist2;
				$i++;
				if($receiptlist2 == 0)
                        	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                        	else
                        	echo "<td align=\"right\">" . convert_amount_dc(-$receiptlist2) . "</td>";
				echo "</tr>";
			}//if view
			if(($acc == "CF") && ($type == "Receipt") && ($database != "NULL"))
                    	{
                    	$this->xml_creation($type,$ledg_id,$database,$name,$curr_year, $total);
                    	}
			$total2=float_ops($total2, $total, '+');
                    	$this->total=$total2;
                    	$total=0;
                    	$this->prev_total = $type_total; 
	            }//if
		    }//foreach
	    }//else  
    }
	/*function add_payment_receipt_sub_ledgers($code ,$type)
        {
		//echo"jkreuio".$code;
		$payment_total=0;
		$total=0;
                $CI =& get_instance();
                $CI->load->model('Ledger_model');
		$CI->load->model('Group_model');
		$CI->load->model('Payment_model');
                //$CI->db->from('ledgers')->where('type', '1')->where('code LIKE', $code . '%');
		$CI->db->from('ledgers')->where('code LIKE', $code . '%')->where('type !=', '1');
                $child_ledger_q = $CI->db->get();
                foreach ($child_ledger_q->result() as $row)
                {
			 $total = $CI->Payment_model->get_paymentreceipt_ledger_balance($row->id, $type, $code);
			if($total != NULL){
                        if($row->name != 'Transfer Account' && $row->name != 'Transit Income'){
			/* echo "<tr class=\"tr-ledger\">";
                                        echo "<td class=\"td-ledger\">";
                                        echo "&nbsp;" . anchor('report/ledgerst/' . $row->id, $row->name, array('title' => $row->name . ' Ledger Statement', 'style' => 'color:#000000'));
                                        echo "</td>";

                                        echo "<td align=\"right\">" . convert_amount_dc($total) . "</td>";
                                        //echo "<td align=\"right\">" . convert_amount_dc($data['total2']) . "</td>";
                                        echo "</tr>";*/
	/*			}

                        }
                }
		return $total;
        }*/
	
	function xml_creation($type,$ledg_id,$database,$name,$curr_year, $total)
	{
		$CI =& get_instance();
		if($name !=  'Depreciation' && $name !=  'Current Assets' && $name !=  'Committed Fund'){
		$type1 =$type."_Name";
        	$doc = new DOMDocument();
	        $doc->formatOutput = true;
		$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
	        $file_name="";
        	$file_name=$type."-".$database."-".$curr_year.".xml";
	        $tt=$acctpath."/".$file_name;
		if(file_exists($tt))
        	{	
        		$doc->preserveWhiteSpace = false;
        		$doc->load($tt);
        		$type = $doc->firstChild;
	        	$type1 = $doc->createElement($type1);
        		$group_name = $doc->createElement('Group_Name');
	        	$textNode = $doc->createTextNode($name);
        		$group_name->appendChild($textNode);
			$type1->appendChild($group_name);

        		$amount = $doc->createElement('Amount');
	       		$textNode2 = $doc->createTextNode($total);
	        	$amount->appendChild($textNode2);
		        $type1->appendChild($amount);

      			$group_id = $doc->createElement('Group_ID');
		        $textNode1 = $doc->createTextNode($ledg_id);
        		$group_id->appendChild($textNode1);
	        	$type1->appendChild($group_id);

        		$type->appendChild($type1);
		        $ttt=$doc->saveXML();
        		$handle = fopen($tt, "w");
	        	fwrite($handle, $ttt);
	        	fclose($handle);
        	}else{
        		$r = $doc->createElement( $type );
	        	$doc->appendChild( $r );
	        	$b = $doc->createElement( $type1 );
		
     			$group_name = $doc->createElement( "Group_Name" );
		        $group_name->appendChild($doc->createTextNode($name));
        		$b->appendChild( $group_name );

	        	$amount = $doc->createElement( "Amount");
	        	$amount->appendChild($doc->createTextNode($total));
			$b->appendChild( $amount );

	        	$group_id = $doc->createElement('Group_ID');
		        $textNode1 = $doc->createTextNode($ledg_id);
        		$group_id->appendChild($textNode1);
		        $b->appendChild( $group_id );
	
        		$r->appendChild( $b );

		        $doc->save($tt);
        		$doc->saveXML();

        	}
	}

	}

	function tds_report()
	{	
		$counter = 1;
		$CI =& get_instance();
		$CI->db->select('id,amount,dc,secunitid,entry_id')->from('entry_items')->where('ledger_id', '4')->where('dc', 'C');
        	$query_result = $CI->db->get();
        	$result = $query_result->result();
	     // 	print_r($result);
        	$no_rows = $query_result->num_rows();
        	foreach($result as $row)
        	{
                	$entry_items_id = $row->id;
                	$amount = $row->amount;
                	$dc = $row->dc;
                	$secunitid = $row->secunitid;
                	$entry_id = $row->entry_id;
                	$CI->db->select('partyname,pan,vat,staxnum,sacunit')->from('addsecondparty')->where('sacunit', $secunitid);
                	$party_result = $CI->db->get();
                	$p_detail = $party_result->result();
			foreach($p_detail as $row)
			{
				echo "<tr>";
				echo "<td align=\"center\">". $counter. "</td>";
				$counter++;
				echo "<td>". $row->partyname."</td>";
				echo "<td>". $row->pan."</td>";
				echo "<td>". $row->vat."</td>";
				echo "<td>". $row->staxnum."</td>";
				echo "<td>". $row->sacunit."</td>";
			}

                	$CI->db->select('date,narration')->from('entries')->where('id', $entry_id);
                	$entry_result = $CI->db->get();
                	$entry_detail = $entry_result->result();
			foreach($entry_detail as $row1)
			{
				$datetime = $row1->date;
				$date = date_mysql_to_php($datetime);
				echo "<td>".$date. "</td>";
                        	echo "<td>". $row1->narration ."</td>";
			}
			echo "<td align=\"center\">". $entry_items_id."</td>";
                        echo "<td align=\"center\">". $amount ."</td>";			
        	}	
	}

// added by @kanchan on date 2015 04 10
// This function send the mail to related user for every transation

        function send_mail($to,$subject,$message)
        {
		$CI =& get_instance();
		//$CI->load->library('general');
		$db1=$CI->load->database('login', TRUE);
		$db1->select('id,email_protocol,email_host,email_port,email_username,email_password')->from('emailSetting');
                $emaldata= $db1->get();
                foreach($emaldata->result() as $row){
                	$email_protocol = $row->email_protocol;
	                $email_host = $row->email_host;
        	        $email_port = $row->email_port;
                	$email_username = $row->email_username;
	                $email_password = $row->email_password;
                }
		$db1->close();
		//$CI->messages->add('email setting value as ' . $email_protocol.$email_host.$email_port.$email_username.$email_password . '.', 'success');
//		echo $email_protocol.$email_host.$email_port.$email_username.$email_password;
                 // Email configuration
                $config = Array(
                'protocol' => $email_protocol,
                'smtp_host' => $email_host,
                'smtp_port' => $email_port,
                'smtp_user' => $email_username, // change it to yours
                'smtp_pass' => $email_password, // change it to yours
                'mailtype' => 'html',
                'charset' => 'iso-8859-1',
                'wordwrap' => TRUE
                );
		$mail_messages = $CI->load->library('Email', $config);

        // Set your email information
		$CI->email->from($email_username,"brihspti");
                $CI->email->to($to);
		$CI->email->subject($subject);
                $CI->email->message($message);
                if(!($CI->email->send()))
		{
			$CI->messages->add('Your entry is created -' . $mail_messages);
			//$CI->messages->add('Please Set the correct Email Configuration Settings---' . 'error');
                        return FALSE;
			}
  		else{
			 $CI->messages->add('Your Mail Sucessfully send!---'. ' success');
			// redirect('admin/user/');
			 return TRUE;
  		}
        }//send_mail 
}
?>

<?php
	echo form_open('report2/sundry_debit_report/'. $party_id);
    	echo "<p>";
	echo form_label('Party Name', 'party_id');
     	echo " ";
     	echo "&nbsp;&nbsp;&nbsp;";
    	echo form_dropdown_secunit('party_id', $party_id);
      	echo " ";
 	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
   	echo form_submit('submit', 'Show');
       	echo "</p>";
    	echo form_close();

 	$this->load->library('pagination');
     	$page_count = (int)$this->uri->segment(4);
     	$page_count = $this->input->xss_clean($page_count);
    	if ( ! $page_count)
      		$page_count = "0";
 	$config['base_url'] = site_url('report2/sundry_debit_report/'.$party_id);
      	$pagination_counter = $this->config->item('row_count');
       	$config['num_links'] = 10;
      	$config['per_page'] = $pagination_counter;
       	$config['uri_segment'] = 4;
	if($party_id == 0)
	{
		$this->db->distinct();
              	$this->db->select('secunitid')->from('entry_items')->like('ledger_code','200308','after');
               	$sec_count = $this->db->get();
       		$config['total_rows'] = (int)$sec_count->num_rows;
	}
	else
       		$config['total_rows'] = (int)$this->db->from('entry_items')->where('secunitid',$party_id)->like('ledger_code','200308','after')->count_all_results();
      	$config['full_tag_open'] = '<ul id="pagination-flickr">';
      	$config['full_close_open'] = '</ul>';
      	$config['num_tag_open'] = '<li>';
    	$config['num_tag_close'] = '</li>';
    	$config['cur_tag_open'] = '<li class="active">';
      	$config['cur_tag_close'] = '</li>';
     	$config['next_link'] = 'Next &#187;';
     	$config['next_tag_open'] = '<li class="next">';
      	$config['next_tag_close'] = '</li>';
   	$config['prev_link'] = '&#171; Previous';
  	$config['prev_tag_open'] = '<li class="previous">';
       	$config['prev_tag_close'] = '</li>';
     	$config['first_link'] = 'First';
     	$config['first_tag_open'] = '<li class="first">';
     	$config['first_tag_close'] = '</li>';
      	$config['last_link'] = 'Last';
        $config['last_tag_open'] = '<li class="last">';
    	$config['last_tag_close'] = '</li>';
     	$this->pagination->initialize($config);

	if($total_party_row == 0)
	{
		echo "<table width=\"100%\" border=\"0\">";
                echo "<tr valign=\"top\">";

                echo "<td>";
                echo "<table width=\"100%\" border=\"0\">";

                echo "<tr style = \"text-align:center;\">";
                echo "<td style=\"padding: 15px 15px 15px 15px;background-color:#87CEEB;\">";
                echo "<h3>There is no Party in dropdown</h3>";
                echo "</td>";
                echo "</tr>";

                echo "</table>";
                echo "</td>";

                echo "</tr>";
                echo "</table>";
	}
	else
	{
		if($party_id == 0)
		{
			$this->db->select('entry_id')->from('entry_items')->like('ledger_code','200308','after');
     			$cred_rep = $this->db->get();
     			foreach($cred_rep->result() as $row)
      			{
        			$tot_row[] = $row->entry_id;
      			}
			if($cred_rep->num_rows == 0)
			{
				$tot_row[] = "";
				$this->db->from('entries')->where_in('id',$tot_row);
     				$cred_rep1 = $this->db->get();
				if( $cred_rep1->num_rows() < 1 )
                		{
					echo "<table width=\"100%\" border=\"0\">";
                			echo "<tr valign=\"top\">";

                			echo "<td>";
                			echo "<table width=\"100%\" border=\"0\">";

                			echo "<tr style = \"text-align:center;\">";
                			echo "<td style=\"padding: 15px 15px 15px 15px;background-color:#87CEEB;\">";
                			echo "<h3>There is no Sundry debit Report for any party</h3>";
                			echo "</td>";
                			echo "</tr>";
                
					echo "</table>";
                			echo "</td>";
                
					echo "</tr>";
                			echo "</table>";
				}
			}
			else
			{
				$end_uri = site_url()."/report2/sundry_debit_report/";
				$this->db->distinct();
				$this->db->select('secunitid')->from('entry_items')->like('ledger_code','200308','after');
				$this->db->limit($pagination_counter, $page_count);
     				$cred_rep_sec = $this->db->get();
                        	$j = 1;

				echo "<table width=\"100%\" border=\"0\">";
               			echo "<tr valign=\"top\">";

               			echo "<td>";
               			echo "<table width=\"100%\" border=\"0\">";

                		echo "<tr style = \"text-align:center;\">";
               			echo "<td>";
               			echo "<table cellpadding=\"10\" width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse; border:1px solid #BBBBBB;\">";
               			echo "<tr style=\"border-bottom: 1px solid #BBBBBB;\">";
               			echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
               			echo "<b>Party Name";
               			echo "</td>";
               			echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
               			echo "<b>Total Debit";
               			echo "</td>";
               			echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
               			echo "<b>Total Credit";
               			echo "</td>";
               			echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
               			echo "<b>Balance";
               			echo "</td>";
                		echo "</tr>";
				foreach($cred_rep_sec->result() as $row2)
                        	{
					$p_nme1 = $this->Secunit_model->get_secunitname($row2->secunitid);
	
                                	if($j % 2 == 0)
                                        	echo "<tr style=\"border-bottom: 1px solid #BBBBBB; background-color:#87CEEB\">";
                                	else
                                        	echo "<tr style=\"border-bottom: 1px solid #BBBBBB; background-color:#AFEEEE\">";
                                	echo "<td style=\"text-align:center;\"><a style=\"text-decoration: none;color:black;\" href=$end_uri"."$row2->secunitid>$p_nme1</a></td>";
					$total_cr = $this->Secunit_model->get_sundry_seccr_secdr_total($row2->secunitid,"C","200308");
					$total_dr = $this->Secunit_model->get_sundry_seccr_secdr_total($row2->secunitid,"D","200308");
					$balance = $total_dr-$total_cr;
                                	echo "<td style=\"text-align:center;\">" . $total_dr . "</td>";
                                	echo "<td style=\"text-align:center;\">" . $total_cr . "</td>";
					if($balance<0)
                                		echo "<td style=\"text-align:center;\">" . abs($balance) . " (CREDIT)</td>";
					elseif($balance>0)
                                		echo "<td style=\"text-align:center;\">" . $balance . " (DEBIT)</td>";
					else
                                		echo "<td style=\"text-align:center;\">" . $balance . "</td>";
                                	echo "</tr>";
                                	$j++;
                        	}

				echo "</table>";
                		echo "</td>";
                		echo "</tr>";

                		echo "</table>";
                		echo "</td>";

                		echo "</tr>";
                		echo "</table>";
			}
		}
		else
		{
			$this->db->select('entry_id')->from('entry_items')->where('secunitid',$party_id);
     			$this->db->like('ledger_code','200308','after');
     			$cred_rep = $this->db->get();
     			foreach($cred_rep->result() as $row)
      			{
        			$tot_row[] = $row->entry_id;
      			}
			if($cred_rep->num_rows == 0)
			{
				$tot_row[] = "";
				$p_nme = $this->Secunit_model->get_secunitname($party_id);
				$this->db->from('entries')->where_in('id',$tot_row);
     				$cred_rep1 = $this->db->get();
				if( $cred_rep1->num_rows() < 1 )
                		{
					echo "<table width=\"100%\" border=\"0\">";
                			echo "<tr valign=\"top\">";

                			echo "<td>";
                			echo "<table width=\"100%\" border=\"0\">";

                			echo "<tr style = \"text-align:center;\">";
                			echo "<td style=\"padding: 15px 15px 15px 15px;background-color:#87CEEB;\">";
                			echo "<h3>There is no Sundry debit Report for the party " . $p_nme . "</h3>";
                			echo "</td>";
                			echo "</tr>";
                
					echo "</table>";
                			echo "</td>";
                
					echo "</tr>";
                			echo "</table>";
				}
			}
			else
			{
				$this->db->from('entries')->where_in('id',$tot_row);
				$this->db->limit($pagination_counter, $page_count);
     				$cred_rep1 = $this->db->get();
				$i = 1;

				echo "<table width=\"100%\" border=\"0\">";
                		echo "<tr valign=\"top\">";

               			echo "<td>";
               			echo "<table width=\"100%\" border=\"0\">";

               			echo "<tr style = \"text-align:center;\">";
               			echo "<td>";
               			echo "<table cellpadding=\"10\" width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse; border:1px solid #BBBBBB;\">";
               			echo "<tr style=\"border-bottom: 1px solid #BBBBBB;\">";
               			echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
               			echo "<b>Date";
               			echo "</td>";
               			echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
               			echo "<b>Voucher No.";
               			echo "</td>";
               			echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
               			echo "<b>Vendor/Voucher No.";
               			echo "</td>";
               			echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
                		echo "<b>Ledger Name";
                		echo "</td>";
                		echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
                		echo "<b>Type";
                		echo "</td>";
                		echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
                		echo "<b>Dr Amount";
                		echo "</td>";
                		echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
                		echo "<b>Cr Amount";
                		echo "</td>";
                		echo "<td style=\"padding: 8px 8px 8px 20px;background-color:#EEEEEE;\">";
                		echo "<b>Submitted By";
                		echo "</td>";
                		echo "</tr>";
     				foreach($cred_rep1->result() as $row1)
      				{
					$current_entry_type = entry_type_info($row1->entry_type);
                			$value = '';
                			$value = $this->Ledger_model->get_entry_name($row1->id, $row1->entry_type);

					if($i % 2 == 0)
                        			echo "<tr style=\"border-bottom: 1px solid #BBBBBB; background-color:#87CEEB\">";
                        		else
                        			echo "<tr style=\"border-bottom: 1px solid #BBBBBB; background-color:#AFEEEE\">";
                        		echo "<td style=\"text-align:center;\">" . date_mysql_to_php_display($row1->date) . "</td>";
                        		echo "<td style=\"text-align:center;\">" . anchor('entry/view/' . $current_entry_type['label'] . "/" . $row1->id, full_entry_number($row1->entry_type, $row1->number), array('title' => 'View ' . $current_entry_type['name'] . ' Entry', 'class' => 'anchor-link-a')) . "</td>";
                        		echo "<td style=\"text-align:center;\">".$row1->vendor_voucher_number."</td>";
                        		echo "<td style=\"text-align:center;\">" . $value . "</td>";
                        		echo "<td style=\"text-align:center;\">" . $current_entry_type['name'] . "</td>";
                        		echo "<td style=\"text-align:center;\">" . money_format('%!i', $row1->dr_total) . "</td>";
                        		echo "<td style=\"text-align:center;\">" . money_format('%!i', $row1->cr_total) . "</td>";
                        		echo "<td style=\"text-align:center;\">" . $row1->submitted_by . "</td>";
					echo "</tr>";
                        		$i++;
				}

				echo "</table>";
                		echo "</td>";
                		echo "</tr>";

                		echo "</table>";
                		echo "</td>";

                		echo "</tr>";
                		echo "</table>";
			}
		}
	}
?>
<div id="pagination-container"><?php echo $this->pagination->create_links(); ?></div>

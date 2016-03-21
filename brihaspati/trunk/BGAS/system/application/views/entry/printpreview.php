<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Print - <?php 
 if ( ! defined('BASEPATH')) exit('No direct script access allowed');
echo $current_entry_type['name']; ?> Bill/Voucher Number <?php echo $entry_number; ?></title>
<?php echo link_tag(asset_url() . 'images/favicon.ico', 'shortcut icon', 'image/ico'); ?>
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/printentry.css">
</head>
<body>
<?php
	/*GUI modification added by @RAHUL */
	echo "<table width=\"100%\" align=\"center\" border=\"0\">";
        echo "<tr valign=\"top\">";

	echo "<td>";
	echo "<table width=\"100%\" align=\"center\" border=\"0\">";

	echo "<tr>";

	$odd_even = "odd";
	$fund = "";
	$entry_id = "";
	$type = "";
	$fund_id = "";
	$id ="";
    	$this->db->select('id, name')->from('settings');
    	$ins_id = $this->db->get();
    	foreach( $ins_id->result() as $row)
    	{
       		$row1 = $row->name;
    	}
    	$this->upload_path= realpath(BASEPATH.'../uploads/logo');
    	$file_list = get_filenames($this->upload_path);
    	if ($file_list)
    	{
        	foreach ($file_list as $row2)
        	{
                	$ext = substr(strrchr($row2, '.'), 1);
                	$my_values = explode('.',$row2);
                	if($my_values[0] == $row1)
                	{
				echo "<td align=\"center\">";
                		echo img(array('src' => base_url() . "uploads/logo/" . $row1.'.'.$ext));
                	}
        	}
    	}
	else
	{
		echo "<br/>";
		echo "<br/>";
	    	echo "<br/>";
	    	echo "<br/>";
		echo "<td>";
	    	echo "<p align=\"justify\">" . "&nbsp;" . $this->config->item('account_ins_name') . "</p>";
    	}
    	echo "<br>";
    	echo $this->config->item('account_name'); 
	echo"<br>";
	echo $this->config->item('account_address');
	echo "</td>"; 

	echo "<td align=\"center\">";
	echo "<strong>".$this->config->item('account_ins_name')."</strong>";
	echo "<br>";
	echo "<strong>Brihaspati General Accounting System</strong>";
	echo "<br>";
	echo "<strong>".$current_entry_type['name']." Entry<strong>";
	echo "</td>";

 	echo "<td align=\"center\">"; 
	echo "Financial year";
	echo "<br>"; 
	echo date_mysql_to_php_display($this->config->item('account_fy_start'));
	echo "-";
	echo date_mysql_to_php_display($this->config->item('account_fy_end'));
	echo "</td>";
	
	echo "</tr>";

	echo "</table>";
	echo "</td>";

	echo "<tr>";

	echo "<td>";
        echo "<table width=\"100%\" align=\"center\" border=\"0\">";

	echo "<tr valign=\"top\">";

	echo "<td>";
	echo "<b>".$current_entry_type['name']." Bill/Voucher Number</b> : "."<span class=\"value\">".full_entry_number($entry_type_id, $entry_number)."</span>";
     	echo " <br>";
      	echo "<b>".$current_entry_type['name']." Bill/Voucher Date</b> : "."<span class=\"value\">".$entry_date."</span>";
       	echo "<br>";
       	echo "<b>".$current_entry_type['name']." Vendor/Voucher Number</b> : "."<span class=\"value\">".$vendor_voucher_number."</span>";
        echo "<br>";
	echo "</td>";

	echo "<td>";
	echo "<b>".$current_entry_type['name']."Forward Reference Id</b> : "."<span class=\"value\">".$forward_ref_id."</span>";
        echo "<br>";
        echo "<b>".$current_entry_type['name']."Backward Reference Id</b> : "."<span class=\"value\">".$back_ref_id."</span>";
        echo "</td>";

	echo "</tr>";

	echo "</table>";
	echo "</td>";

	echo "</tr>";

	echo "<tr style = \"text-align:center;\">";

	echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse; border:1px solid #BBBBBB;\">";
        
	echo "<tr style=\"border-bottom: 1px solid #BBBBBB;\">";
        
	echo "<td style=\"background-color:#EEEEEE;\">";
        echo "<b>Type</b>";
        echo "</td>";
        echo "<td style=\"background-color:#EEEEEE;\">";
        echo "<b>Ledger Account</b>";
        echo "</td>";
        echo "<td style=\"background-color:#EEEEEE;\">";
        echo "<b>Dr Amount</b>";
        echo "</td>";
        echo "<td style=\"background-color:#EEEEEE;\">";
        echo "<b>Cr Amount</b>";
        echo "</td>";
        echo "<td style=\"background-color:#EEEEEE;\">";
        echo "<b>Party Name</b>";
        echo "</td>";
        echo "<td style=\"background-color:#EEEEEE;\">";
        echo "<b>Party Address</b>";
        echo "</td>";
        echo "<td style=\"background-color:#EEEEEE;\">";
        echo "<b>Fund</b>";
	echo "</td>";
        echo "<td style=\"background-color:#EEEEEE;\">";
        echo "<b>Income/Expense Type</b>";
        echo "</td>";

	echo "</tr>";

 	$currency = $this->config->item('account_currency_symbol');
        foreach ($ledger_q->result() as $row)
     	{
       		$id = $row->ledger_id;
             	$ledger_code = $this->Ledger_model->get_ledger_code($row->ledger_id);
               	$temp = $this->Ledger_model->isFund($ledger_code);
             	$entry_id = $row->id;
              	$dc = $row->dc;
             	if ($row->dc == "D")
             	{
               		if(!($temp))
                    	{
                       		$temp1 = $this->Ledger_model->isFundDeduct($row->ledger_id);
                            	if(!($temp1))
                           	{
                             		$query = $this->Ledger_model->get_type1($entry_id);
                                  	$my_values = explode('#',$query);
                                      	$type =$my_values[0];
                                   	$name =$my_values[1];
					echo "<tr style=\"border-bottom: 1px solid #BBBBBB;\">";
                                 	echo "<td>" . convert_dc($row->dc) . "</td>";
                                   	echo "<td>" . $this->Ledger_model->get_code($row->ledger_id).'.'.$this->Ledger_model->get_name($row->ledger_id) . "</td>";
                                   	echo "<td>Dr " . $row->amount . "</td>";
                                   	echo "<td></td>";
                                  	echo "<td> " . $this->Secunit_model->get_secunitname($row->secunitid) . "</td>";
                                 	echo "<td> " . $this->Secunit_model->get_secunitaddress($row->secunitid) . "</td>";
                                     	echo "<td> " . $name . "</td>";
                                   	echo "<td> " . $type . "</td>";
                        	}
                 	}
    		}
		else
		{
 			$type = $this->Ledger_model->get_type($row->ledger_id, $entry_id);
			echo "<tr style=\"border-bottom: 1px solid #BBBBBB;\">";
                    	echo "<td>" . convert_dc($row->dc) . "</td>";
                    	echo "<td>" . $this->Ledger_model->get_name($row->ledger_id) . "</td>";
                      	echo "<td></td>";
                     	echo "<td>Cr " . $row->amount . "</td>";
                    	echo "<td> " . $this->Secunit_model->get_secunitname($row->secunitid) . "</td>";
                   	echo "<td> " . $this->Secunit_model->get_secunitaddress($row->secunitid) . "</td>";
                    	echo "<td>"."</td>";
                      	echo "<td>".$type."</td>";
          	}
           	echo "</tr>";
             	$odd_even = ($odd_even == "odd") ? "even" : "odd";
      	}
     	$this->db->select('name,bank_name,ledger_id, update_cheque_no')->from('cheque_print')->where('entry_no',$row->entry_id);
   	$ledger_q = $this->db->get();
     	$no_of_row=$ledger_q->num_rows();
     	if($no_of_row > 0)
	{
        	foreach($ledger_q->result() as $row)
           	{
                 	$bank_name = $row->bank_name;
                     	$ledger_id = $row->ledger_id;
                      	$name= $row->name;
                   	$cheque_no= $row->update_cheque_no;
                   	$cheque[] =$cheque_no;
           	}
         	$length=count($cheque);
   	}

	echo "<tr>";

	echo "<td>";
	echo "Total";
	echo "</td>";
	echo "<td>";
	echo "</td>";
	echo "<td>";
	echo $currency . " " .  $entry_dr_total;
	echo "</td>" ;
	echo "<td>";
	echo $currency . " " . $entry_cr_total;
	echo "</td>";

	echo "</table>";
	echo "</td>";

	echo "</tr>";

	echo "<tr>";

	echo "<td>";
        echo "<table width=\"100%\" border=\"0\">";

	echo "<tr>";
	echo "<td>";
	echo "<b>Narration</b> : "."<span class=\"value\">".$entry_narration."</span>";
	echo "</td>";
	echo "</tr>";

	echo "</table>";
	echo "</td>";

	echo "</tr>";

	echo "<tr>";

        echo "<td>";
        echo "<table width=\"100%\" border=\"0\">";

        echo "<tr valign=\"top\">";

        echo "<td>";
	echo "<b>Submitted By</b> : "."<span class=\"value\">".$submitted_by."</span>";
        echo " <br>";
	echo "<b>Approved By</b> : "."<span class=\"value\">";
       	$this->db->select('id')->from('entries')->where('number',$entry_number);
       	$entry_approv = $this->db->get();
      	$entry_approv1 = $entry_approv->row();
     	$entry_approv_id = $entry_approv1->id;
      	$this->db->select('id')->from('bill_voucher_create')->where('entry_id',$entry_approv_id);
      	$ent_ry = $this->db->get();
      	$ent_ry1 = $ent_ry->row();
	if ($ent_ry->num_rows() > 0)
	{
      		$ent_ry2 = $ent_ry1->id;
    		$e_ntr = "Approved";
      		$this->db->select('authority_name')->from('bill_approval_status')->where('bill_no',$ent_ry2)->where('status',$e_ntr);
     		$ent_ry3 = $this->db->get();
     		if($ent_ry3->num_rows() > 0)
     		{
        		foreach($ent_ry3->result() as $row_3)
          		{
              			$e_ntr1 = $row_3->authority_name;
                     		$authnme = explode('/',$e_ntr1);
                    		$authnme1[] = $authnme[0].")";
              		}
         		foreach($authnme1 as $key => $value)
            		{
            			echo $value."&nbsp;&nbsp;&nbsp;";
                	}
    		}
       		else
      		{
         		echo " ";
      		}
	}
	else
	{
		echo " ";
	}
	echo "</span>";
        echo "<br>";
	echo "<b>Verified By</b> : "."<span class=\"value\">".$verified_by."</span>";
        echo "<br>";
	echo "<br>";
	echo "<b>Tag</b> : ";
	$cur_entry_tag = $this->Tag_model->show_entry_tag($cur_entry->tag_id);
	if ($cur_entry_tag == "")
		echo "(None)";
	else
		echo $cur_entry_tag;
	echo "<br>";
	if($ledger_q->num_rows() > 0)
	{
		if( $cheque_no != NULL && $name != NULL)
		{
			echo "<b>Beneficiary Name</b> : " . $name;
		}
	}

        echo "</td>";

        echo "<td>";
	echo "<b>Sanction Letter No.</b> : "."<span class=\"bold\">".$cur_entry->sanc_letter_no."</span>";
	echo "<br>";
        echo "<b>Sanction Letter Date</b> : "."<span class=\"bold\">";
     	$sanc_date  = $cur_entry->sanc_letter_date;
        $exp_date=explode(" ",$sanc_letter_date);
        if($exp_date[0] == "0000-00-00"){
                echo" ";
        }
        else{
                echo date_mysql_to_php($sanc_date);
        }
        echo "</span>";
        echo "<br>";
        echo "<b>Sanction  Detail</b> : "."<span class=\"bold\">";
        $sanc_type = $cur_entry->sanc_type;
        if($sanc_type != 'select'){
                $sanc_value = $cur_entry->sanc_value;
                if($sanc_value != ""){
                        echo $cur_entry->sanc_type."  - ".$cur_entry->sanc_value;
                }else{
                        echo $cur_entry->sanc_type;
                }
        }else{
                echo "";
        }
        echo "</span>";
        echo "<br>";
        echo "<br>";
	if($ledger_q->num_rows() > 0)
        {
                if( $cheque_no != NULL && $name != NULL)
                {
                        echo "<b>Bank Name</b> : " . $bank_name;
                        echo "<br>";
                        for($i=0; $i<$length; $i++)
                        {
                                if($cheque[$i] != 1)
                                {
                                        echo "<b>Cheque/DD/BT No</b> : " . $cheque[$i];
                                }
                        }
                }
        }

        echo "</td>";

        echo "</tr>";

        echo "</table>";
        echo "</td>";

        echo "</tr>";

	echo "</table>";
	echo "</tr>";

	echo "<br>";

?>
	<input class="hide-print" type="button" onClick="window.print()" value="Print Voucher">

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Email - <?php echo $current_entry_type['name']; ?> Bill/Voucher Number <?php echo $entry_number; ?></title>
</head>
<body>
<?php
	/*Alter the body of mail added by @RAHUL */
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
        $this->upload_path= realpath(BASEPATH.'../uploads/BGAS/logo');
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
				//$file_list1 = base_url()."uploads/logo/".$row1.'.'.$ext;
				//$imageData = file_get_contents($file_list1);
                        	//echo sprintf('<img src="data:image/png;base64,%s" />', base64_encode($imageData));
                                echo img(array('src' => base_url() . "uploads/BGAS/logo/" . $row1.'.'.$ext));
                        }
			$newrep_lace = str_replace('_', ' ', $my_values[0]);
                        if(($newrep_lace == $row1) && ($my_values[0] != $row1))
                        {
                                echo "<td align=\"center\">";
                                echo img(array('src' => base_url() . "uploads//BGAS/logo/" . $my_values[0].'.'.$ext));
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
        echo $current_entry_type['name']." Bill/Voucher Number : "."<span class=\"value\">".full_entry_number($entry_type_id, $entry_number)."</span>";
        echo " <br>";
        echo $current_entry_type['name']." Bill/Voucher Date : "."<span class=\"value\">".$entry_date."</span>";
        echo "<br>";
        echo $current_entry_type['name']." Vendor/Voucher Number : "."<span class=\"value\">".$vendor_voucher_number."</span>";
        echo "<br>";
        echo "</td>";

        echo "<td>";
        echo $current_entry_type['name']."Forward Reference Id : "."<span class=\"value\">".$forward_ref_id."</span>";
        echo "<br>";
        echo $current_entry_type['name']."Backward Reference Id : "."<span class=\"value\">".$back_ref_id."</span>";
        echo "<br>";
        echo $current_entry_type['name']."Purchase Order Number : "."<span class=\"value\">".$purchase_order_number."</span>";
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
        foreach ($ledger_data->result() as $row)
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
                                //if(!($temp1))
                                //{
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
                                //}
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
        echo "Narration : "."<span class=\"value\">".$entry_narration."</span>";
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
        echo "Submitted By : "."<span class=\"value\">".$submitted_by."</span>";
        echo " <br>";
        echo "Approved By : "."<span class=\"value\">";
       /* $this->db->select('id')->from('entries')->where('number',$entry_number);
        $entry_approv = $this->db->get();
        $entry_approv1 = $entry_approv->row();
        $entry_approv_id = $entry_approv1->id;*/
	$ent_r_id = $ent_ryid;
        $this->db->select('id')->from('bill_voucher_create')->where('entry_id',$ent_r_id);
        $ent_ry = $this->db->get();
        $ent_ry1 = $ent_ry->row();
	if ($ent_ry->num_rows() > 0)
	{
        	$ent_ry2 = $ent_ry1->id;
        	$e_ntr = "Approved";
        	$this->db->select('authority_name')->from('bill_approval_status')->where('bill_no',$ent_ry2)->where('status',$e_ntr);
        	//$this->db->select('authority_name')->from('bill_approval_status')->where('bill_no',$ent_ryid)->where('status',$e_ntr);
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
        //echo "Verified By : "."<span class=\"value\">".$verified_by."</span>";
	echo "Verified By : "."<span class=\"value\">";
        if($verified_by == "")
        {
                echo $verified_by;
        }
        else
        {
                $nme1 = explode(",", $verified_by);
                $i = sizeof($nme1);
                for($j=0; $j<$i; $j++)
                {
                        echo $nme1[$j];
                        echo "<br>";
                }
        }
        echo "</span>";
        echo "<br>";
        echo "<br>";
	echo "Tag :";
        $cur_entry_tag = $this->Tag_model->show_entry_tag($tag_id);
        if ($cur_entry_tag == "")
                echo "(None)";
        else
                echo $cur_entry_tag;
        echo "<br>";
        if($ledger_q->num_rows() > 0)
        {
                if( $cheque_no != NULL && $name != NULL)
                {
                        echo "Beneficiary Name :" . $name;
                }
        }

        echo "</td>";

        echo "<td>";
       // echo "Sanction Letter No. : "."<span class=\"bold\">".$cur_entry->sanc_letter_no."</span>";
        echo "Sanction Letter No. : "."<span class=\"bold\">".$sanc_letter_no."</span>";
        echo "<br>";
        echo "Sanction Letter Date : "."<span class=\"bold\">";
        $sanc_date  = $sanc_letter_date;
        $exp_date=explode(" ",$sanc_letter_date);
        if($exp_date[0] == "0000-00-00"){
                echo" ";
        }
        else{
                echo date_mysql_to_php($sanc_date);
        }
        echo "</span>";
        echo "<br>";
        echo "Sanction  Detail : "."<span class=\"bold\">";
        //$sanc_type = $cur_entry->sanc_type;
        if($sanc_type != 'select'){
              //$sanc_value = $cur_entry->sanc_value;
                if($sanc_value != ""){
                        echo $sanc_type."  - ".$sanc_value;
                }else{
                        echo $sanc_type;
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
                        echo "Bank Name :" . $bank_name;
                        echo "<br>";
                        for($i=0; $i<$length; $i++)
                        {
                                if($cheque[$i] != 1)
                                {
                                        echo "Cheque/DD/BT No :" . $cheque[$i];
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
?>
</body>
</html>

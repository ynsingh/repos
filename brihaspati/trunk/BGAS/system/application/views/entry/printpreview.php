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
	$odd_even = "odd";
	$fund = "";
	$entry_id = "";
	$type = "";
	$fund_id = "";
	$id ="";
    echo "<table border='0' cellpadding='3'  width=\"80%\">";
    echo "<tr><td align=\"left\">";
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
                echo img(array('src' => base_url() . "uploads/logo/" . $row1.'.'.$ext));
                }
        }
    }else{
	    echo "<br/>";
		echo "<br/>";
	    echo "<br/>";
	    echo "<br/>";
	    echo "<p align=\"justify\">" . "&nbsp;" . $this->config->item('account_ins_name') . "</p>";
    }
    echo "<br>";
    echo $this->config->item('account_name'); 
	echo"<br>";
	echo $this->config->item('account_address') . "</td>"; 
	echo "<td align=\"center\" class=\"bold\" >"."<h4>" . $this->config->item('account_ins_name')."</h4>"."<h2>Brihaspati General Accounting System</h2>";
	echo "<b>".$current_entry_type['name']." Voucher";
 	echo "<td align=\"right\">" . 'Financial year' . '<br>' . date_mysql_to_php_display($this->config->item('account_fy_start')); ?> - <?php echo date_mysql_to_php_display($this->config->item('account_fy_end')); ?><?php echo "</td></tr></table>";?>

	<br>
	 <table width="80%">
		<tr>
			<td width="80%">
				<?php echo $current_entry_type['name']; ?> Bill/Voucher Number : <span class="value"><?php echo full_entry_number($entry_type_id, $entry_number); ?></span>
				<br>
				<?php echo $current_entry_type['name']; ?> Bill/Voucher Date : <span class="value"><?php echo $entry_date; ?></span>
				<br>
				<?php echo $current_entry_type['name']; ?>Vendor/Voucher Number : <span class="value"><?php echo $vendor_voucher_number; ?></span>
			</td>
			<td width="80%">
				<?php echo $current_entry_type['name']; ?> Forward Reference Id : <span class="value"><?php echo $forward_ref_id; ?></span>
				<br>
        		<?php echo $current_entry_type['name']; ?> Backward Reference Id : <span class="value"><?php echo $back_ref_id; ?></span>
				<br>
			</td>

		</tr>
	</table>
	<br>
	<table id="print-entry-table" width="80%">
		<thead>
			<tr class="tr-title"><th>Type</th><th>Ledger Account</th><th>Dr Amount</th><th>Cr Amount</th><th>Secondary Unit</th><th>Party Address</th><th>Fund</th><th>Income/Expense Type</th></tr>
		</thead>
		<tbody>
		<?php
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
							echo "<tr class=\"tr-" . $odd_even . "\">";
				            echo "<td>" . convert_dc($row->dc) . "</td>";
						    echo "<td>" . $this->Ledger_model->get_name($row->ledger_id) . "</td>";
							echo "<td>Dr " . $row->amount . "</td>";
							echo "<td></td>";
							echo "<td> " . $this->Secunit_model->get_secunitname($row->secunitid) . "</td>";
							echo "<td> " . $this->Secunit_model->get_secunitaddress($row->secunitid) . "</td>";
							echo "<td> " . $name . "</td>";
							echo "<td> " . $type . "</td>";
						}	
				    }
				}else{
					
					$type = $this->Ledger_model->get_type($row->ledger_id, $entry_id);
					echo "<tr class=\"tr-" . $odd_even . "\">";
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
			echo "<tr class=\"tr-total\"><td></td><td class=\"total-name\">Total</td><td class=\"total-dr\">" . $currency . " " .  $entry_dr_total . "</td><td class=\"total-cr\">" . $currency . " " . $entry_cr_total . "</td></tr>";
			
		?>
	</tbody>
	</table>
	<br />
	<!--div id="print-entry-narration">Narration : <span class="value"><?php //echo $entry_narration; ?></span></div-->
	<table width="80%">
	<tr>
	<td>
		Narration : <span class="value"><?php echo $entry_narration; ?></span>
		<p>
		Tag :
		<?php
		$cur_entry_tag = $this->Tag_model->show_entry_tag($cur_entry->tag_id);
		if ($cur_entry_tag == "")
	        	echo "(None)";
		else
	        	echo $cur_entry_tag;
		?>
		</p>
		Submitted By : <span class="value"><?php echo $submitted_by; ?></span>
        <br>
        Verified By : <span class="value"><?php echo $verified_by; ?></span>
	</td>
	<td align=right>
	<p>Sanction Letter No. : <span class="bold"><?php echo $cur_entry->sanc_letter_no; ?></span></p>
	<p>Sanction Letter Date : <span class="bold">
	<?php
		 $sanc_date  = $cur_entry->sanc_letter_date;
        $exp_date=explode(" ",$sanc_letter_date);
        if($exp_date[0] == "0000-00-00"){
                echo" ";
        }
        else{
                echo date_mysql_to_php($sanc_date);
        }
 
	?>
	</span>
	</p>
	<p>Sanction  Detail : <span class="bold">
	<?php 
	//echo $cur_entry->sanc_value; 
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
	?>
	</span></p>

	<?php
		/*$cheque='';
		$this->db->select('name,bank_name,update_cheque_no')->from('cheque_print')->where('entry_no',$row['id']);
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
        if($ledger_q->num_rows() > 0){
			if( $cheque_no != NULL && $name != NULL)
            {
				for($i=0; $i<$length; $i++)
        		{
					if($cheque[$i] != 1)
					{
						echo "Bank Name :" . $bank[$i] . "</br>";
	        			echo "Beneficiary Name :" . $benif_name[$i] . "</br>";
	        			echo "Cheque No :" . $cheque[$i] . "</br>";
					}
				}
        	}
		}*/
    ?>
	</td>
	</tr>
	</table>
	<form>
	<input class="hide-print" type="button" onClick="window.print()" value="Print Voucher">
	</form>
</body>
</html>

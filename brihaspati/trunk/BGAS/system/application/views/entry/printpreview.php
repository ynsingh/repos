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
                echo "<table border='0' cellpadding='3'  width=\"600\">";
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
                }
                else{
                echo "<br/>";
		echo "<br/>";
                echo "<br/>";
                echo "<br/>";
                echo "<p align=\"justify\">" . "&nbsp;" . $this->config->item('account_ins_name') . "</p>";
                }
                echo $this->config->item('account_name'); 
		echo"<br>";
		echo $this->config->item('account_address') . "</td>"; 
		echo "<td align=\"center\" class=\"bold\" >"."<h4>" . $this->config->item('account_ins_name')."</h4>"."<h2>Brihaspati General Accounting System</h2>";
		echo "<b>".$current_entry_type['name'];?>
		Voucher
		<?php 
		echo "<td align=\"right\">" . 'Financial year' . '<br>' . date_mysql_to_php_display($this->config->item('account_fy_start')); ?> - <?php echo date_mysql_to_php_display($this->config->item('account_fy_end')); ?><?php echo "</td></tr></table>";?>

	 <!--div id="print-account-name"><span class="value"><?php //echo  $this->config->item('account_name'); ?></span></div>
        <div id="print-account-address"><span class="value"><?php //echo $this->config->item('account_address'); ?></span></div>
        <br />
        <div id="print-entry-type"><span class="value"><?php //echo $current_entry_type['name']; ?> Entry</span></div>
        <br />
        <div id="print-entry-number"><?php //echo $current_entry_type['name']; ?> Bill/Voucher Number : <span class="value"><?php //echo full_entry_number($entry_type_id, $entry_number); ?></span></div>
        <div id="print-entry-number"><?php //echo $current_entry_type['name']; ?> Forward Reference Id : <span class="value"><?php //echo $forward_ref_id; ?></span></div>
        <div id="print-entry-number"><?php //echo $current_entry_type['name']; ?> Backward Reference Id : <span class="value"><?php //echo $back_ref_id; ?></span></div>
        <div id="print-entry-number"><?php //echo $current_entry_type['name']; ?> Bill/Voucher Date : <span class="value"><?php //echo $entry_date; ?></span></div>
        <br /-->

	<br>
	 <table width="750">
	<tr>
	<td width="300">
	<?php echo $current_entry_type['name']; ?> Bill/Voucher Number : <span class="value"><?php echo full_entry_number($entry_type_id, $entry_number); ?></span>
	<br>
	<?php echo $current_entry_type['name']; ?> Bill/Voucher Date : <span class="value"><?php echo $entry_date; ?></span>
	</td><td width="300">
	<?php echo $current_entry_type['name']; ?> Forward Reference Id : <span class="value"><?php echo $forward_ref_id; ?></span>
	<br>
        <?php echo $current_entry_type['name']; ?> Backward Reference Id : <span class="value"><?php echo $back_ref_id; ?></span>
	<br /-->
	</td>
	</tr>
	</table>
	<br>
	<table id="print-entry-table" width="600">
		<thead>
			<tr class="tr-title"><th>Ledger Account</th><th>Dr Amount</th><th>Cr Amount</th></tr>
		</thead>
		<tbody>
		<?php
			$currency = $this->config->item('account_currency_symbol');

			foreach ($ledger_data as $id => $row)
			{
				echo "<tr class=\"tr-ledger\">";
				if ($row['dc'] == "D")
				{
					echo "<td class=\"ledger-name item\">By " . $row['name'] . "</td>";
				} else {
					echo "<td class=\"ledger-name item\">To " . $row['name'] . "</td>";
				}
				if ($row['dc'] == "D")
				{
					echo "<td class=\"ledger-dr item\">" . $currency . " " . $row['amount'] . "</td>";
					echo "<td class=\"ledger-cr last-item\"></td>";
				} else {
					echo "<td class=\"ledger-dr item\"></td>";
					echo "<td class=\"ledger-cr last-item\">" . $currency . " " . $row['amount'] . "</td>";
				}
				echo "</tr>";
			}
			echo "<tr class=\"tr-total\"><td class=\"total-name\">Total</td><td class=\"total-dr\">" . $currency . " " .  $entry_dr_total . "</td><td class=\"total-cr\">" . $currency . " " . $entry_cr_total . "</td></tr>";
			$cheque='';
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


		?>
	</tbody>
	</table>
	<br />
	<!--div id="print-entry-narration">Narration : <span class="value"><?php //echo $entry_narration; ?></span></div-->
	<table width="750">
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
	<!--div id="print-entry-narration">Submitted By : <span class="value"><?php //echo $submitted_by; ?></span></div>
	<br>
        <div id="print-entry-narration">Verified By : <span class="value"><?php //echo $verified_by; ?></span></div>
	<div id="print-entry-narration">Submitted By : <span class="value"><?php //echo $submitted_by; ?></span></div>
        <br>
        <div id="print-entry-narration">Verified By : <span class="value"><?php //echo $verified_by; ?></span></div-->
	Submitted By : <span class="value"><?php echo $submitted_by; ?></span>
        <br>
        Verified By : <span class="value"><?php echo $verified_by; ?></span>
	</td><td>

	<p>Sanction Letter No. : <span class="bold"><?php echo $cur_entry->sanc_letter_no; ?></span></p>
	<p>Sanction Letter Date : <span class="bold"><?php echo date_mysql_to_php($cur_entry->sanc_letter_date); ?></span></p>
	<p>Sanction Letter Detail : <span class="bold"><?php echo $cur_entry->sanc_value; ?></span></p>

	 <?php
        if($ledger_q->num_rows() > 0){
		if( $cheque_no != NULL && $name != NULL)
                {
			for($i=0; $i<$length; $i++)
        		{
				if($cheque[$i] != 1){
				echo "Bank Name :" . $bank[$i] . "</br>";
        			echo "Beneficiary Name :" . $benif_name[$i] . "</br>";
        			echo "Cheque No :" . $cheque[$i] . "</br>";
				}
			}
        	}
	}
        ?>
	</td>
	</tr>
	</table>
	<form>
	<input class="hide-print" type="button" onClick="window.print()" value="Print Voucher">
	</form>
</body>
</html>

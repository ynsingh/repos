<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Print - <?php echo "Payment Voucher" ?> </title>
<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');?>
<?php echo link_tag(asset_url() . 'images/favicon.ico', 'shortcut icon', 'image/ico'); ?>
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/printentry.css">
</head>
<body>

<?php
	$this->load->library('number');
        $a = new Number();
        $amt = $approved_amount;
        $word = $a->convert_number($approved_amount);
        $full_word = $word . " only/-";

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
        echo "<strong>Payment Voucher<strong>";
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
	echo "<b>Bill Number</b> : "."<span class=\"value\">".$bill_no."</span>";
	echo "<br>";
	echo "<b>Creation Date</b> : "."<span class=\"value\">".$vc_date."</span>";
	echo "</td>";

        echo "</tr>";

        echo "</table>";
        echo "</td>";

	echo "</tr>";

	echo "<tr>";

        echo "<td>";
        echo "<table width=\"100%\" border=\"1\" style=\"color: black; border-collapse:collapse; border:1px solid #000000;\">";

	echo "<tr>";
        echo "<td style=\"background-color:#efe3b5;\"><b>Amount</b></td>";
        echo "<td><span class=\"value\">".$approved_amount."</span></td>";
	echo "</tr>";

	echo "<tr>";
        echo "<td style=\"background-color:#efe3b5;\"><b>The Sum of</b></td>";
        echo "<td>Rs. ".$approved_amount."  ( ".$full_word. " )</td>";
        echo "</tr>";

	echo "<tr>";
        echo "<td style=\"background-color:#efe3b5;\"><b>Paid To</b></td>";
        echo "<td><span class=\"value\">".$paid_to."</span></td>";
	echo "</tr>";

	echo "<tr>";
        echo "<td style=\"background-color:#efe3b5;\"><b>Paid By</b></td>";
        echo "<td><span class=\"value\">".$bank_cash_account."</span></td>";
        echo "</tr>";

	echo "<tr>";
        echo "<td style=\"background-color:#efe3b5;\"><b>Mode of Payment</b></td>";
        echo "<td><span class=\"value\">".$mode_of_payment."</span></td>";
        echo "</tr>";

	echo "<tr>";
        echo "<td style=\"background-color:#efe3b5;\"><b>Expense</b></td>";
        echo "<td><span class=\"value\">".$expense_type."</span></td>";
	echo "</tr>";

	echo "<tr>";
        echo "<td style=\"background-color:#efe3b5;\"><b>Fund Name</b></td>";
        echo "<td><span class=\"value\">".$fun_d_id."</span></td>";
        echo "</tr>";

	echo "<tr>";
        echo "<td style=\"background-color:#efe3b5;\"><b>Expenditure Type</b></td>";
        echo "<td><span class=\"value\">".$exp_type."</span></td>";
        echo "</tr>";

	echo "<tr>";
        echo "<td style=\"background-color:#efe3b5;\"><b>Party Name</b></td>";
        echo "<td><span class=\"value\">".$p_name."</span></td>";
	echo "</tr>";

	echo "<tr>";
        echo "<td style=\"background-color:#efe3b5;\"><b>Party Address</b></td>";
        echo "<td><span class=\"value\">".$p_add."</span></td>";
        echo "</tr>";

	echo "<tr>";
	echo "<td style=\"background-color:#efe3b5;\"><b>Sanction Type</b></td>";
        echo "<td><span class=\"value\">".$sanc_type."</span></td>";
	echo "</tr>";

        echo "<tr>";
        echo "<td style=\"background-color:#efe3b5;\"><b>Sanction value</b></td>";
        echo "<td><span class=\"value\">".$sanc_value."</span></td>";
        echo "</tr>";

	echo "</table>";
        echo "</td>";
        
	echo "</tr>";

	echo "<tr>";

        echo "<td>";
	echo "<table width=\"100%\" border=\"1\" style=\"color: black; border-collapse:collapse; border:1px solid #000000;\">";

        echo "<tr valign=\"top\">";
	echo "<br>";

	echo "<td valign=\"middle\" style=\"background-color:#efe3b5;\"><b>Narration</b></td>";
        echo "<td><span class=\"value\">".$narrate."</span></td>";

	echo "</tr>";

        echo "</table>";
        echo "</td>";

        echo "</tr>";

	echo "<tr>";

        echo "<td>";
	echo "<table width=\"100%\" border=\"0\">";
	echo "<br>";

        echo "<td valign=\"top\">";
	echo "<table width=\"100%\" border=\"1\" style=\"color: black; border-collapse:collapse; border:1px solid #000000;\">";

	echo "<td align=\"center\" style=\"background-color:#efe3b5;\"><b>Uploaded By</b></td>";
        echo "<tr>";
        echo "<td>$submitter_id</td>";
        echo "</tr>";
        echo "</td>";

	echo "</table>";
        echo "</td>";

	$this->db->select('approved_by')->from('bill_approval_status')->where('bill_no',$bill_no)->where('status','Approved');
        $app_ry = $this->db->get();
        $app_ry1 = $app_ry->row();
        if ($app_ry->num_rows() > 0)
	{
		echo "<td valign=\"top\">";
        	echo "<table width=\"100%\" border=\"1\" style=\"color: black; border-collapse:collapse; border:1px solid #000000;\">";
        
		echo "<td colspan=\"2\" align=\"center\" style=\"background-color:#efe3b5;\"><b>Approved By</b></td>";
        	echo "<tr>";
        	echo "<td align=\"center\" style=\"background-color:#999999;\"><b>Name</b></td>";
        	echo "<td align=\"center\" style=\"background-color:#999999;\"><b>Designation</b></td>";
        	echo "</tr>";
        	foreach($approved_by as $key => $value)
        	{
                	echo "<tr>";
                	echo "<td>";?><?php echo $value['name']?><?php echo "</td>";
                	echo "<td>";?><?php echo $value['auth']?><?php echo "</td>";
                	echo "</tr>";
        	}
		echo "</table>";
        	echo "</td>";
	}

	echo "<td valign=\"top\">";
        echo "<table width=\"100%\" border=\"1\" style=\"color: black; border-collapse:collapse; border:1px solid #000000;\">";
  
	echo "<td align=\"center\" style=\"background-color:#efe3b5;\"><b>Voucher By</b></td>";
        echo "<tr>";
        echo "<td>$us_name</td>";
        echo "</tr>";

	echo "</table>";
        echo "</td>";

	echo "</table>";
        echo "</td>";

        echo "</tr>";

	echo "</table>";
        echo "</tr>";
?>
<form>
	<input class="hide-print" type="button" onClick="window.print()" value="Print entry">
</form>
</body>
</html>

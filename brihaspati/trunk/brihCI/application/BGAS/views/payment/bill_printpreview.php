<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Print - <?php echo "Payment Voucher" ?> </title>
<?php echo link_tag(asset_url() . 'assets/bgas/images/favicon.ico', 'shortcut icon', 'image/ico'); ?>
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>assets/bgas/css/printentry.css">
</head>
<body>
	        <?php
		        $this->load->library('number');
                        $a = new Number();
                        $amt=$approved_amount;
                        $word = $a->convert_number($approved_amount);
                        $full_word=$word . " only/-";

                echo "<tr valign=\"top\">";
                echo "<table border='0' cellpadding='3'class=\"simple-table report-table\" width=\"55%\">";

                echo "<tr><td align=\"left\">";
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
                                echo img(array('src' => base_url() . "uploads/BGAS/logo/" . $row1.'.'.$ext));
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
		?>
		<br/>
	<?php
         /*   echo $this->config->item('account_name'); ?><br><?php echo $this->config->item('account_address') . "</td>"; ?><?php echo "<td align=\"center\" class=\"bold\" >" . "<h2>Brihaspati General Accounting System</h2><br>"."<h2>Payment Voucher</h2><br>". $this->config->item('account_ins_name'); " </td>"; echo "<td align=\"right\">" . 'Financial year' . '<br>' . date_mysql_to_php_display($this->config->item('account_fy_start')); ?> - <?php echo date_mysql_to_php_display($this->config->item('account_fy_end')); ?><?php echo "</td></tr>";?>
*/
		echo $this->config->item('account_name'); ?><br><?php echo $this->config->item('account_address') . "</td>"; ?><?php echo "<td align=\"center\" class=\"bold\" >"."<h4>" . $this->config->item('account_ins_name')."</h4>"."<h2>Brihaspati General Accounting System</h2>"."<h2>Payment Voucher</h2>"." </td>"; echo "<td align=\"right\">" . 'Financial year' . '<br>' . date_mysql_to_php_display($this->config->item('account_fy_start')); ?> - <?php echo date_mysql_to_php_display($this->config->item('account_fy_end')); ?><?php echo "</td></tr>";?>
<!--	<br/>
	<div id="print-entry-type1" style="text-decoration:underline;" ><span class="value"><b><font size="5" face="Arial"  >Payment Voucher</b></span></div>
	<br />
	<div id="print-account-name"><span class="value"><?php //echo  $this->config->item('account_name'); ?></span></div>
        <div id="print-account-address"><span class="value"><?php //echo $this->config->item('account_address'); ?></span></div>
</br>-->
	<br />
<table>
<tr>
 <td><b><font face="Arial" size="3"><b>Bill Number :</b></font></td><td><b>&nbsp</b></td>
  <td><b><font size="2" face="Arial"><?php echo $bill_no;?></font></b></td>
</tr>
<tr>
  <td><b><font size="3" face="Arial">Creation Date:</font></b></td><td><b>&nbsp</b></td>
  <td ><b><font size="2" face="Arial"><?php echo $vc_date; ?></font></b></td>
</tr>
</table>
<!--<div id="print-entry-number"><?php //echo $current_entry_type['name']; ?><b><font size="3" face="Arial"> Bill Number : <span class="value"><?php //echo $bill_no; ?></span></div>
<div id="print-entry-number"><?php //echo $current_entry_type['name']; ?><b><font size="3" face="Arial"> Creation Date :</b> <span class="value"><?php //echo $vc_date; ?></span></div>
	<br /> -->
	<br />
	<table cellpadding="9" width="55%" style="color: black; border-collapse:collapse;font-size: 2px;margin-left:10px;"  border="1" height="12">
<tr>
  <td bordercolorlight="#000000" bordercolordark="#000000" >
  <font face="Arial" size="3"><b>Amount:</b></font></td>
  <td bordercolorlight="#000000" bordercolordark="#000000"><b><font size="2" face="Arial"><?php echo $approved_amount;?></font></b></td>
</tr>
<tr>
  <td bordercolorlight="#000000" bordercolordark="#000000"><b><font size="3" face="Arial">Mode of Payment:</font></b></td>
  <td bordercolorlight="#000000" bordercolordark="#000000"></font><b><font size="2" face="Arial"><?php echo $mode_of_payment; ?></font></b></td>
</tr>
<tr>
  <td bordercolorlight="#000000" bordercolordark="#000000"><b><font size="3" face="Arial">Paid To:</font></b></td>
  <td bordercolorlight="#000000" bordercolordark="#000000"></font><b><font size="2" face="Arial"><?php  echo $paid_to; ?></font></b></td>
</tr>
<tr>
  <td bordercolorlight="#000000" bordercolordark="#000000"><b><font size="3" face="Arial">The Sum of:</font></b></td>
  <td bordercolorlight="#000000" bordercolordark="#000000"><font face="Arial"><b><font size="2"><?php echo $approved_amount."  ( ".$full_word. " )"; ?></font></b></font></td>
  </tr>
  <tr>
<tr>
	<td bordercolorlight="#000000" bordercolordark="#000000"><b><font size="3" face="Arial">Expense:</font></b></td>
	<td bordercolorlight="#000000" bordercolordark="#000000"><font face="Arial"><b><font size="2"><?php echo $expense_type ?></font></b></font></td>
</tr>
<tr>
        <td bordercolorlight="#000000" bordercolordark="#000000"><b><font size="3" face="Arial">Paid By:</font></b></td>
        <td bordercolorlight="#000000" bordercolordark="#000000"><font face="Arial"><b><font size="2"><?php echo $bank_cash_account ?></font></b></font></td>
</tr>

<tr>
	<td bordercolorlight="#000000" bordercolordark="#000000"><b><font size="3" face="Arial">Party Name:</font></b></td>
	<td bordercolorlight="#000000" bordercolordark="#000000"><font face="Arial"><b><font size="2"><?php echo $p_name; ?></font></b></font></td>
</tr>
<tr>
        <td bordercolorlight="#000000" bordercolordark="#000000"><b><font size="3" face="Arial">Party Address:</font></b></td>
        <td bordercolorlight="#000000" bordercolordark="#000000"><font face="Arial"><b><font size="2"><?php echo $p_add;?></font></b></font></td>
</tr>
<tr>
        <td bordercolorlight="#000000" bordercolordark="#000000"><b><font size="3" face="Arial">Fund Name:</font></b></td>
        <td bordercolorlight="#000000" bordercolordark="#000000"><font face="Arial"><b><font size="2"><?php echo $fund_name;?></font></b></font></td>
</tr>
<tr>
        <td bordercolorlight="#000000" bordercolordark="#000000"><b><font size="3" face="Arial">Expenditure Type:</font></b></td>
        <td bordercolorlight="#000000" bordercolordark="#000000"><font face="Arial"><b><font size="2"><?php echo $exp_type;?></font></b></font></td>
</tr>
<tr>
        <td bordercolorlight="#000000" bordercolordark="#000000"><b><font size="3" face="Arial">Sanction Type:</font></b></td>
        <td bordercolorlight="#000000" bordercolordark="#000000"><font face="Arial"><b><font size="2"><?php echo $sanc_type;?></font></b></font></td>
</tr>
<tr>
        <td bordercolorlight="#000000" bordercolordark="#000000"><b><font size="3" face="Arial">Sanction value:</font></b></td>
        <td bordercolorlight="#000000" bordercolordark="#000000"><font face="Arial"><b><font size="2"><?php echo $sanc_value;?></font></b></font></td>
</tr>

<tr>
        <td bordercolorlight="#000000" bordercolordark="#000000"><b><font size="3" face="Arial">Narration:</font></b></td>
        <td bordercolorlight="#000000" bordercolordark="#000000"><font face="Arial"><b><font size="2"><?php echo $being; ?></font></b></font></td>
</tr>

</table>
	<br />
	    <table width="55%" cellpadding="20"style="color: black;border-collapse:collapse; font-size: 2px;margin-left:10px;" border="1" height="20">
		<tr colspan="4"><th><font size="3" face="Arial">Uploaded By </th><th><font size="3" face="Arial">Approved By</th><th><font size="3" face="Arial">Voucher By</th></tr>
		<tr>
			<td bordercolorlight="#000000" bordercolordark="#000000"><font face="Arial"><b><font size="2"><?php echo $submitter_id?></font></b></font></td>
			<td bordercolorlight="#000000" bordercolordark="#000000"><font face="Arial"><b><font size="2"><?php echo $approved_by ?></font></b></font></td>
			<td bordercolorlight="#000000" bordercolordark="#000000"><font face="Arial"><b><font size="2"><?php echo $user_name ?></font></b></font></td>
		</tr>
		
	</table>
<br>
	<form>
	<input class="hide-print" type="button" onClick="window.print()" value="Print entry">
	<input type="button" onClick="window.location.href='<?php echo base_url();?>bgasindex.php/payment/showupload_bill'" value="Back">
<!--	<input type="button" value="Back" onclick="window.location.href=. base_url() . 'payment/showupload_bill'" />-->
<!--	<button onclick="window.location.href='<?php echo base_url()?>/payment/showupload_bill'">Back</button> -->
</form>
</body>
</html>

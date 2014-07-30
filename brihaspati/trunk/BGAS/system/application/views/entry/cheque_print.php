<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	//	$(".simple-table").hide(function(){
	//	});
	$(".hide-print").click(function(){
	id = $(this).val();
	dc = $(this).attr('name');
		//Ajax for set cheque_print_status in reconciliation table........
		$.ajax({
        		url: <?php echo '\'' . site_url('entry/print_status') . '/\''; ?> + dc,
        		success: function(bank) {
        		bank_cash = $.trim(bank);
			}
        	});
  	});
});
</script>
</head>




<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/printcheque.css">

		<?php 
				setlocale(LC_MONETARY, 'en_IN');
				foreach ($ledger_data as $id => $row){
					$bank_name = $row['bank_name'];
                                        $name= $row['name'];
					$cheque_no=$row['cheque_no'];
					$ledger_id=$row['ledger_id'];
					$entry_id=$row['entry_id'];
					$sign='saperator';
				}
				$space="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

		?>
		</tr>
                <tr height="20px;">
		<?php 
			$this->load->library('number');
			$a = new Number();
			foreach ($ledger_data as $id => $row){
			$amt=$row['amount'];
	        	}
			$word = $a->convert_number($row['amount']);
			$full_word=$word . " only/-";
                        $theRest = substr($full_word, 0, 39);
                        $theRest1 = substr($full_word, 39);

		?>
</tr>
<br />

<table border=0 width="780" class="simple-table">
<tr height="42"><td><td width="550" align="center">
		<?php
				$this->load->library('session');
        			$cheque_type = $this->session->userdata('cheque_type');
				if($cheque_type == 'Order')
				echo"__________<br><u>A/C Payee</u>";
			
		?>
		</td><td width="200" align="right"> 
		<?php
				if($cheque_type == 'Order'){
                      		$this->db->select('date')->from('entries')->where('id',$row['id']);
                      		$entry_date = $this->db->get();
                      		foreach($entry_date->result() as $row1)
                      		{
                                	$date = $row1->date;
                                	$date1 = new DateTime($date);
                                	$actual_date= $date1->format('dmY');
                                	$len=strlen($actual_date);
                                	$split=str_split($actual_date);

                                	for($i=0 ; $i<$len ;$i++)
                                	{
                                        	if($i==0)
                                        	{
                                                	echo"&nbsp;";
                                        	}
                                        		echo"&nbsp;";
                                        		echo $split[$i];
                                        		echo"&nbsp;";
                                	}
				}
		}
	?>	
</td></tr>
<?php
if($cheque_type == 'Order'){ 
echo "<tr height=\"20\"><td width=\"125\"><td width=\"450\" ></td></tr>";
echo "<tr height=\"25\"><td width=\"125\" align=\"right\"><td width=\"450\" >" . "&nbsp;&nbsp;&nbsp;&nbsp;" .$name . "</td></tr>";
echo "<tr height=\"5\"><td width=\"125\"><td width=\"450\" ></td></tr>";
echo "<tr height=\"22\"><td width=\"125\"><td width=\"450\" >$theRest</td><td width=\"184\" align=\"center\"></td></tr>";
echo "<tr height=\"25\"><td width=\"125\"></td><td width=\"450\" >$theRest1</td><td width=\"184\" align=\"center\">" . money_format('%!i',$amt) . "/-" . "</td></tr>";
echo "<tr height=\"200\"></tr>";

}
else{
$space="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
echo "<tr height=\"20\"><td width=\"125\"><td width=\"450\" ></td></tr>";
echo "<tr height=\"25\"><td width=\"125\" align=\"right\"><td width=\"450\" >" . "&nbsp;&nbsp;&nbsp;&nbsp;" .$name . "</td></tr>";
echo "<tr height=\"5\"><td width=\"125\"><td width=\"450\" ></td></tr>";
echo "<tr height=\"22\"><td width=\"125\"><td width=\"450\" >$theRest</td><td width=\"184\" align=\"center\"></td></tr>";
echo "<tr height=\"25\"><td width=\"125\"></td><td width=\"450\" >$theRest1</td><td width=\"184\" align=\"center\">" . money_format('%!i',$amt) . "/-" . "</td></tr>";
echo "<tr height=\"22\"><td width=\"125\"><td width=\"150\">$space$cheque_no</td><td width=\"184\" align=\"center\"></td></tr>";
echo "<tr height=\"200\"></tr>";
}
?>
</table>
	<input class="hide-print" type="button" onClick="window.print()" value="<?php $val=$cheque_no; echo"print cheque";
?>" name="<?php echo $val=$ledger_id; echo $sign; echo $entry_id; ?>">
<p class="hide-print">
<?php echo anchor("entry/show/all", 'Back', array('title' => 'Back to entry page'));?>
<p>

</html>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/printcheque.css">
<head>
<!--script type="text/javascript"-->
<!--/script-->


</head>

<body>
		<?php 
				setlocale(LC_MONETARY, 'en_IN');
				foreach ($ledger_data as $id => $row){
				}
				$this->db->select('name,bank_name,cheque_no')->from('reconcilation')->where('entry_no',$row['id']);
                        	$ledger_q = $this->db->get();
                        	foreach($ledger_q->result() as $row)
                                {
                                	$bank_name = $row->bank_name;
					$name= $row->name;
					$cheque_no= $row->cheque_no;
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
				if($row['cheque_type'] == 'Order')
				echo"__________<br><u>A/C Payee</u>";
			
		?>
		</td><td width="200" align="right"> 
		<?php
		
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
			
	?>	
</td></tr>
<?php 
echo "<tr height=\"20\"><td width=\"125\"><td width=\"450\" ></td></tr>";
echo "<tr height=\"25\"><td width=\"125\" align=\"right\"><td width=\"450\" >" . "&nbsp;&nbsp;&nbsp;&nbsp;" .$name . "</td></tr>";
echo "<tr height=\"5\"><td width=\"125\"><td width=\"450\" ></td></tr>";
echo "<tr height=\"22\"><td width=\"125\"><td width=\"450\" >$theRest</td><td width=\"184\" align=\"center\"></td></tr>";
echo "<tr height=\"25\"><td width=\"125\"></td><td width=\"450\" >$theRest1</td><td width=\"184\" align=\"center\">" . money_format('%!i',$amt) . "/-" . "</td></tr>";
echo "<tr height=\"200\"></tr>";


?>
</table>
</body>
</html>
<br>	
<input class="hide-print" type="button" onClick="window.print()" value="Print check">


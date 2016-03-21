<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');?>

<script type="text/javascript" language="javascript">

function DisableBackButton() {
	window.history.forward()
	}
	DisableBackButton();
	window.onload = DisableBackButton;
	window.onpageshow = function(evt) { if (evt.persisted) DisableBackButton() }
	window.onunload = function() { void (0) }
</script>

<script type="text/javascript">
$(document).ready(function() {
        /* Show and Hide allowed_over 
                $('.payment_mode').change(function() {
                var name = $(".payment_mode").val();
                if(name.match("cash$")){
                        $('.check_no').hide();
                }
                else
                {
                        $('.check_no').show();
                }
        });
        $('.payment_mode').trigger('change');*/
	$('input').each(function(){
		var name = $(this).val();
     //            alert("ytt6ntyyg56r"+name);
		if(name == "select")
		    {
		//	$('.exp-type').hide();
                  //      $('.exp-label').hide();
			$('.sanc-type').hide();
                        $('.sanc-label').hide();
			 $('.sanc-value').hide();
                        $('.sanc-value-label').hide();
		//	$('.fund_name').hide();
                  //      $('.fund-label').hide(); 
			}
		if(name == "Select")
		     {
			$('.exp-type').hide();
                        $('.exp-label').hide();
			$('.fund_name').hide();
                        $('.fund-label').hide(); 
		     }

	  });

});
</script>


<?php
	echo "<table width=\"100%\">";
	echo "<tr valign=\"top\">";

	echo "<td>";
	echo "<table width=\"35%\">";
	echo "<tr>";

	echo form_open_multipart('payment2/p2voucherfilling/'.$bill_no['value']);
	
	echo "<p>";
        echo form_label('BILL NUMBER','bill_no');
        echo "<br />";
        echo form_input($bill_no);
        echo "</p>";
	
	echo "<p>";
        echo form_label('BILL SUBMITTED BY','paid_to');
        echo "<br />";
        echo form_input($paid_to);
        echo "</p>";

	echo "<p>";
        echo form_label('TOTAL PROPOSED AMOUNT','total_amount');
        echo "<br />";
        echo form_input($total_amount);
        echo "</p>";

	echo "<p>";
        echo form_label('PARTY NAME','secunitid');
        echo "<br />";
        echo form_input($secunitid);
        echo "</p>";

        echo "<p>";
        echo form_label('SANCTION TYPE','sanc_type');
        echo "<br />";
        echo form_input($sanc_type);
        echo "</p>";

	echo "<p>";
        echo form_label('NARRATION','narrate');
        echo "<br />";
        echo form_textarea($narrate);
        echo "</p>";

	echo "</tr>";
	echo "</table>";
	echo "</td>";

	echo "<td>";
	echo "<table width=\"30%\">";
	echo "<tr>";

	echo "<p>";
        echo form_label('SUPPLIER BILL NUMBER', 'supplier_bill_no');
        echo "<br />";
        echo form_input($supplier_bill_no);
        echo "</p>";

	echo "<p>";
        echo form_label('BILL SUBMISSION DATE','date');
        echo "<br />";
        echo form_input($submitdate);
        echo "</p>";

	echo "<p>";
        echo form_label('APPROVED AMOUNT','approved_amount');
        echo "<br />";
        echo form_input($approved_amount);
        echo "</p>";

	echo "<p>";
        echo form_label('FUND NAME','fund_id');
        echo "<br />";
        echo form_input($fund);
        echo "</p>";

	echo "<p>";
        echo form_label('SANCTION HEAD','sanc_value');
        echo "<br />";
        echo form_input($sanc_value);
        echo "</p>";

	echo "<p>";
        echo form_label('MODE OF PAYMENT', 'payment_mode');
        echo "<br />";
        echo form_dropdown('payment_mode', $payment_mode, $payment_mode_active, "class =\"payment_mode\"");
        echo "</p>";
	
	echo "</tr>";
        echo "</table>";
        echo "</td>";

        echo "<td>";
        echo "<table width=\"35%\">";
        echo "<tr>";

	echo "<p>";
        echo form_label('PURCHASE ORDER NO', 'purchase_order_no');
        echo "<br />";
        echo form_input($purchase_order_no);
        echo "</p>";

	echo "<span id=\"result\"></span>";
        echo "<p>";
        echo form_label('APPROVED BY','approved_by');
        echo "<br />";
        echo form_input($approved_by);
        echo "</p>";

	echo "<p>";
        echo form_label('PAID TO','paid_to');
        echo "<br />";
        echo form_input($paid_to);
        echo "</p>";

	echo "<p>";    
        echo form_label('EXPENDITURE TYPE','exp_type');
        echo "<br />";
        echo form_input($exp_type);
        echo "</p>";

	echo "<p>";
        echo form_label('EXPENSE HEAD','expensestype');
        echo "<br />";
        echo form_input($expensestype);
        echo "</p>";

	echo "<p>";
        echo form_label('BANK AND CASH ACCOUNT','bank_cash');
        echo "<br/>";
        echo form_dropdown('bank_cash' ,$bank_cash, $bankcash_active);
        echo "</p>";

	echo"</tr>";
	echo"</table>";
	echo"</td>";

	echo"</tr>";
	echo"</table>";

	echo "<br/>";
	echo "</br>";
	echo form_submit('submit', 'Submit');
?>
	<input type="button" onClick="window.back()" value="Back">
	<?php echo form_close();?>





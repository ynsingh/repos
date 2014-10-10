<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');?>

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
	echo form_open_multipart('payment/voucherfilling/'.$bill_no);
        echo "<p>";
        echo form_label(' Amount','approved_amount');
        echo "<br />";
        echo form_input($approved_amount);
        echo "</p>";
	echo "<span id=\"result\"></span>";
	echo "<p>";
        echo form_label(' Approved By','approved_by');
        echo "<br />";
        echo form_input($approved_by);
        echo "</p>";

	echo "<p>";
       echo form_label('Narration','being');
        echo "<br />";
       echo form_textarea($being);
       echo "</p>";

       echo "<p>";
       echo form_label(' Paid To','paid_to');
       echo "<br />";
       echo form_input($paid_to);
       echo "</p>";

       echo "<p>";
       echo form_label('Secondary Unit ID','secunitid');
       echo "<br />";
       echo form_input($secunitid);
       echo "</p>";

       echo "<p class=\"fund-label\">";
       echo form_label('Fund Type','fund_name');
       echo "</p>";
     //  echo "<br />";
	echo "<p class=\"fund_name\">";
       echo form_input($fund);
       echo "</p>";

       echo "<p class=\"exp-label\">";    
       echo form_label('Expenditure Type','exp_type');
       echo "</p>";
     //  echo "<br />";
       echo "<p class=\"exp-type\">";
       echo form_input($exp_type);
       echo "</p>";
       echo "<p class=\"sanc-label\">";
       echo form_label('Sanction Type','sanc_type');
       echo "</p>";
    //   echo "<br />";
       echo "<p class=\"sanc-type\">";
       echo form_input($sanc_type);
       echo "</p>";

       echo "<p class=\"sanc-value-label\">";
       echo form_label('Sanction Value','sanc_value');
       echo "</p>";
     //  echo "<br />";
       echo "<p class=\"sanc-value\">";
       echo form_input($sanc_value);
       echo "</p>";

       echo "<p>";
       echo form_label(' Bank And Cash Account','bank_cash');
       echo "<br/>";
       echo form_dropdown('bank_cash' ,$bank_cash, $bankcash_active);
       echo "</p>";

 /*      echo "<p>";
       echo form_label(' PAN NO','pan_no');
       echo "<br />";
       echo form_input($pan_no);
       echo "</p>";*/

       echo "<p>";
       echo form_label('Method Of Payment', 'payment_mode');
       echo "<br />";
       echo form_dropdown('payment_mode', $payment_mode, $payment_mode_active, "class =\"payment_mode\"");
/*	echo "<p class=\"check_no\">";
        echo form_label('CHECK NO','check_no');
        echo "<br />";
       echo form_input($check_no);
       echo "</p>";*/
 	echo "<br/>";
	echo "<br/>";
	echo "</br>";
	echo form_submit('submit', 'Submit');
?>
	<input type="button" onClick="window.back()" value="Back">
	<?php echo form_close();?>





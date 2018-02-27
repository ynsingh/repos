<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');?>
<script type="text/javascript">

$(document).ready(function() {

    $("#totalamount").keydown(function(event) {
        // Allow: backspace, delete, tab, escape, and enter
        if ( event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 || event.keyCode == 13 || 
             // Allow: Ctrl+A
            (event.keyCode == 65 && event.ctrlKey === true) || 
             // Allow: home, end, left, right
            (event.keyCode >= 35 && event.keyCode <= 39)) {
                 // let it happen, don't do anything
                 return;
        }
        else {
            // Ensure that it is a number and stop the keypress
            if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
                event.preventDefault(); 
            }   
        }
    });

$("#submitted_by").keyup(function(){     
     var email = $('#submitted_by').val();
	var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(regex.test(email)){
		$(this).css('background-color', 'green');
		
	}
	else
	{
		$(this).css('background-color', 'red');
	}
	return false;
});	
       $('.expenses-dropdown').change(function() {
		var ledger_name =$("select.expenses-dropdown option:selected").val();	
                var ledgerArray = ledger_name.split('.');
                                ledgerid = ledgerArray[0];
                                name = ledgerArray[1];
//		alert ("The name of ledger you selected is "+name+".");
		 $.ajax({
                                        url: <?php echo '\'' . site_url('payment/ledger_budget') . '/\''; ?> + name,
                                        success: function(data) {
					var ledger_bal = $.trim(data);
						var text = "Available Budget";
					//	alert(text);
						if(ledger_bal == "0.00")
						{
						alert("Please Add atleast one parent group for this ledger entry for Payment");
						   $("#result").html(text+" = "+ledger_bal);
				//			$("#result1").html(text);
						}
						else{
                                                   $("#result").html(text+" = "+ledger_bal);
					//		$("#result1").html(text);
						}          
				//	alert ("the amount is "+ledger_bal+".");
                                        }
                                 });

        });

                               
});

</script>
<?php
                echo form_open_multipart('payment/bill_upload');
                echo "<p>";
		echo "<span id=\"tooltip-target-1\">";
                echo form_label('Submitter Email ID', 'submitted_by');
                echo "<br />";
                echo form_input($submitted_by);
		echo "</span>";
		echo "<span id=\"tooltip-content-1\">Entry a valid Email id->(abc@gmail.com)</span>";
                echo "</p>";

                echo "<p>";
                echo form_label('Total Amount', 'totalamount');
                echo "<br />";
                echo form_input($totalamount);
                echo "</p>";
                echo "<p>";
                echo form_label('Secondary Unit ID', 'secunit');
                echo "<br />";
                echo form_dropdown('secunit' , $secunit, $secunit_active);
                echo "</p>";

                echo "<p>";
                echo "<td>". form_label('Expenses', 'expenses');
		echo "</td>";
                echo "<br />";
   		echo "<tr>";
                echo "<td>";
		echo form_dropdown('expenses' , $expenses, $expenses_active, "class=\"expenses-dropdown\"");
		echo "</td>";
                echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
         	echo "<span id=\"result\"></span>";
	        //echo "<td class=\"expenses-dropdown\"><div></div></td>";
		echo "</tr>";
		echo "</p>";
		echo "<br />";       
		?>
                <input type="file" name="userfile" />
                <p><input type="submit" value="Submit" name="submit" />
		<input type="button" onclick="history.go(-1);" value="Back">
		</p>
		<?php echo form_close(); ?>



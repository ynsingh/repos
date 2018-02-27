<script type="text/javascript">
	//global variable
	var check = 0;

	function readyFn( jQuery ) {
		// Code to run when the document is ready.
		
		$.ajax({
                                url: <?php echo '\'' . site_url('setting/account/get_account_flag').'\''; ?>,
                                success: function(flag) {
                                        //alert(flag);
                                        var flag = $.trim(flag);
                                        if(flag == 'false'){
                                                alert('Please set the \'chart of account\'');
                                                $('.chart-account').show();     
                                                $('.account-type').hide();
                                        }
                                        else{
                                                $('.chart-account').hide();     
                                                $('.account-type').show();
                                        }
                                }
                });

                $.ajax({
                                url: <?php echo '\'' . site_url('setting/account/get_account_type').'\''; ?>,
                                success: function(account) {
                                        var account_type = $.trim(account);
					//alert('account type='+account_type);
                                        if(account_type == 'mhrd'){
                                                $('.type2').show();     
                                                $('.type1').hide();
                                        }
                                        else{
                                                $('.type2').hide();     
                                                $('.type1').show();
                                        }
                                }
                });
		
		$('.chart-account').live('change', function() {
                        var account = $(".chart-account").val();
			//alert('chart account='+account);
			check++;
                        if(account.match("mhrd$")){
				$('.type1').hide();
                                $('.type2').show();  
                                $('.type2').val() = 'General Funds: Balance of net income/expenditure transferred from I/E Account';                     
                        }
			else{
				$('.type2').hide();
                                $('.type1').show();
			}
                });
		
		$('.type1').live('change', function(){
				$.ajax({
                                	url: <?php echo '\'' . site_url('setting/account/get_account_flag').'\''; ?>,
		                                success: function(flag) {
                        	                var flag = $.trim(flag);
                                	        if(flag == 'false'){
							if(check == 0){
                                        	        	alert('Please set the \'chart of account\' first');	
							}
        	                                }
                                	}
                		});
			}
		);

                $('.chart-account').trigger('change');
       //         $('.type1').trigger('change');

	}

	$(document).ready(readyFn()
	);
</script>

<?php
	echo form_open('setting/account');

	echo "<p>";
	echo form_label('Account Name', 'account_name');
	echo "<br />";
	echo form_input($account_name);
	echo "</p>";

	echo "<p>";
	echo form_label('Account Address', 'account_address');
	echo "<br />";
	echo form_textarea($account_address);
	echo "</p>";

	echo "<p>";
	echo form_label('Account Email', 'account_email');
	echo "<br />";
	echo form_input($account_email);
	echo "</p>";

	echo "<p>";
	echo form_label('Currency', 'account_currency');
	echo "<br />";
	echo form_input($account_currency);
	echo "</p>";

	echo "<p>";
	echo form_label('Date Format', 'account_date');
	echo "<br />";
	echo form_dropdown('account_date', $account_date_options, $account_date);
	echo "</p>";

	echo "<p>";
	echo form_label('Financial Year Start', 'fy_start');
	echo "<br />";
	echo $fy_start;
	echo "</p>";

	echo "<p>";
	echo form_label('Financial Year End', 'fy_end');
	echo "<br />";
	echo $fy_end;
	echo "</p>";

	echo "<p>";
	echo form_label('Timezone');
	echo "<br />";
	echo timezone_menu($account_timezone);
	echo "</p>";

	echo "<p>";
        echo form_label('Chart of Account', 'chart_account');
        echo "<br />";
        echo form_dropdown('chart_account', $chart_account_options, $chart_account, "class=\"chart-account\"");
        echo "</p>";

	echo "<p class=\"account-type\">";
	echo form_label('Account Type', 'account_type');
	echo "<br/>";
	echo form_input($account_type);
	echo "</p>"; 

	echo "<p class=\"type1\">";
        echo form_label('Ledger Name', 'ledger_name');
        echo "<br/>";
        echo form_dropdown('ledger_name', $ledger_name, $ledger_name_active);
        echo "<br/>";
        echo "<b>Note: </b>Account head to which the profit and loss balance will be carry forward.";
        echo "</p>";

	echo "<p class=\"type2\">";
        echo form_label('Ledger Name', 'ledger_name_readonly');
        echo "<br/>";
        echo form_input($ledger_name_readonly);
        echo "<br/>";
        echo "<b>Note: </b>Account head to which the profit and loss balance will be carry forward.";
        echo "</p>";

	echo "<p>";
	echo "<span id=\"tooltip-target-1\">";
	echo form_checkbox('account_locked', 1, $account_locked) . " Account Locked";
	echo "</span>";
	echo "<span id=\"tooltip-content-1\">If enabled prevents any further modifications to the account. Makes the account read-only.</span>";
	echo "</p>";
	//echo "$account_locked";
/*	echo "<p>";
        echo "<span id=\"tooltip-target-2\">";
        echo form_checkbox('useremail_status', 1, $useremail_status) . " User Mail Sent";
        echo "</span>";
        echo "<span id=\"tooltip-content-2\">If enabled then mail sent to User. Makes the account read-only.</span>";
        echo "</p>"; */
	echo "<p>";
        echo "<span id=\"tooltip-target-3\">";
        echo form_checkbox('transcationemail_status', 1, $transcationemail_status) . " Transcation Mail Sent";
        echo "</span>";
        echo "<span id=\"tooltip-content-3\">If enabled then mail sent to User. Makes the account read-only.</span>";

	echo "<p>";
	echo form_submit('submit', 'Update');
	echo " ";
	echo anchor('setting', 'Back', 'Back to Settings');
	echo "</p>";

	echo form_close();
?>

<?php
	if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	/* Add row ledger type */
	if ($current_entry_type['bank_cash_ledger_restriction'] == '4')
		$add_type = "bankcash";
	else if ($current_entry_type['bank_cash_ledger_restriction'] == '5')
		$add_type = "nobankcash";
	else
		$add_type = "all";
?>
<script type="text/javascript">

$(document).ready(function() {
//global variable
var dc = '';

	/*cheque field hide and show functionality*/
        $(".cheque-item").hide(function(){
                });
                $(".bank_value").hide(function(){
                });
                                $("#ch_no").hide(function(){
                                        });
                                        $(".ledger-dropdown").change(function(){
                                                                                        
                                        });


	/* javascript floating point operations */
	var jsFloatOps = function(param1, param2, op) {
		param1 = param1 * 100;
		param2 = param2 * 100;
		param1 = param1.toFixed(0);
		param2 = param2.toFixed(0);
		param1 = Math.floor(param1);
		param2 = Math.floor(param2);
		var result = 0;
		if (op == '+') {
			result = param1 + param2;
			result = result/100;
			return result;
		}
		if (op == '-') {
			result = param1 - param2;
			result = result/100;
			return result;
		}
		if (op == '!=') {
			if (param1 != param2)
				return true;
			else
				return false;
		}
		if (op == '==') {
			if (param1 == param2)
				return true;
			else
				return false;
		}
		if (op == '>') {
			if (param1 > param2)
				return true;
			else
				return false;
		}
		if (op == '<') {
			if (param1 < param2)
				return true;
			else
				return false;
		}
	}

	/* Calculating Dr and Cr total */
	$('.dr-item').live('change', function() {
		var drTotal = 0;
		$("table tr .dr-item").each(function() {
			var curDr = $(this).attr('value');
			curDr = parseFloat(curDr);
			if (isNaN(curDr))
				curDr = 0;
			drTotal = jsFloatOps(drTotal, curDr, '+');
		});
		$("table tr #dr-total").text(drTotal);
		var crTotal = 0;
		$("table tr .cr-item").each(function() {
			var curCr = $(this).attr('value');
			curCr = parseFloat(curCr);
			if (isNaN(curCr))
				curCr = 0;
			crTotal = jsFloatOps(crTotal, curCr, '+');
		});
		$("table tr #cr-total").text(crTotal);

		if (jsFloatOps(drTotal, crTotal, '==')) {
			$("table tr #dr-total").css("background-color", "#FFFF99");
			$("table tr #cr-total").css("background-color", "#FFFF99");
			$("table tr #dr-diff").text("-");
			$("table tr #cr-diff").text("");
		} else {
			$("table tr #dr-total").css("background-color", "#FFE9E8");
			$("table tr #cr-total").css("background-color", "#FFE9E8");
			if (jsFloatOps(drTotal, crTotal, '>')) {
				$("table tr #dr-diff").text("");
				$("table tr #cr-diff").text(jsFloatOps(drTotal, crTotal, '-'));
			} else {
				$("table tr #dr-diff").text(jsFloatOps(crTotal, drTotal, '-'));
				$("table tr #cr-diff").text("");
			}
		}

		bank_cash = -1;
                var ledger_value = $(this).parent().prev().children().attr('value');
                var dr_name = $(this).attr('name');
                //var dr_amount = $(this).val();
		var dr_amount = $(this).attr('value');
                var check = 0;
                $.ajax({
                                        url: <?php echo '\'' . site_url('entry/check_acc') . '/\''; ?> + ledger_value,
                                        success: function(bank) {
                                                bank_cash = $.trim(bank);
                                                if(bank_cash == 0){
                                                        var first_index = dr_name.lastIndexOf("[");
                                                        var last_index = dr_name.lastIndexOf("]"); 
                                                        var fund_index = dr_name.substring(first_index+1, last_index);                                                                                          temp = ".fund-list"+fund_index;
                                                        //var fund_ledger_id = $(temp).val();
							var fund_ledger_id = $(temp).attr('value');
                                                        if(fund_ledger_id != 0)
                                                                check = 1;
                                                        $.ajax({
                                                                url: <?php echo '\'' . site_url('entry/fund_balance/') . '/\''; ?> + fund_ledger_id,
                                                                success: function(data){
                                                                        fund_amount = $.trim(data);
                                                                        fund_amount = parseFloat(fund_amount);
                                                                        if (isNaN(fund_amount))
                                                                                fund_amount = 0;
                                                                        if((jsFloatOps(dr_amount, fund_amount, '>')) && check == 1){
                                                                                alert("Amount payable is more than the available fund("+fund_amount+").");
                                                                        }
                                                        
                                                                }
                                                        });
                                                }
                                        }
                });
	
	});

	$('.cr-item').live('change', function() {
		var drTotal = 0;
		$("table tr .dr-item").each(function() {
			var curDr = $(this).attr('value')
			curDr = parseFloat(curDr);
			if (isNaN(curDr))
				curDr = 0;
			drTotal = jsFloatOps(drTotal, curDr, '+');
		});
		$("table tr #dr-total").text(drTotal);
		var crTotal = 0;
		$("table tr .cr-item").each(function() {
			var curCr = $(this).attr('value')
			curCr = parseFloat(curCr);
			if (isNaN(curCr))
				curCr = 0;
			crTotal = jsFloatOps(crTotal, curCr, '+');
		});
		$("table tr #cr-total").text(crTotal);

		if (jsFloatOps(drTotal, crTotal, '==')) {
			$("table tr #dr-total").css("background-color", "#FFFF99");
			$("table tr #cr-total").css("background-color", "#FFFF99");
			$("table tr #dr-diff").text("-");
			$("table tr #cr-diff").text("");
		} else {
			$("table tr #dr-total").css("background-color", "#FFE9E8");
			$("table tr #cr-total").css("background-color", "#FFE9E8");
			if (jsFloatOps(drTotal, crTotal, '>')) {
				$("table tr #dr-diff").text("");
				$("table tr #cr-diff").text(jsFloatOps(drTotal, crTotal, '-'));
			} else {
				$("table tr #dr-diff").text(jsFloatOps(crTotal, drTotal, '-'));
				$("table tr #cr-diff").text("");
			}
		}
	});

	/* Dr - Cr dropdown changed */
	$('.dc-dropdown').live('change', function() {
		
		var dc = $(this).attr('value');
                var dr_name = $(this).parent().next().next().children().attr('name');
                var ledger_value = $(this).parent().next().children().attr('value');
		var first_index = dr_name.lastIndexOf("[");
                var last_index = dr_name.lastIndexOf("]"); 
                var fund_index = dr_name.substring(first_index+1, last_index);                                               
                var temp = ".fund-list"+fund_index;
		var temp1 = ".type-dropdown"+fund_index;
                var temp2 = ".exp-dropdown"+fund_index;

		if ($(this).parent().next().children().val() != "0") {
                $.ajax({
                                url: <?php echo '\'' . site_url('entry/ledger_code') . '/\''; ?> + ledger_value,
                                success: function(data) {

					account = $.trim(data);
					if(account == 'Income'){
	                                        if(dc == 'D')
	                                                alert("You have made a wrong entry");
                                        }

					if(account == 'Expense' || account == 'Expense-e'){        
		                                if(dc == 'C')
                 	                               alert("You have made a wrong entry");
                                        }
		                        //following code is for fund dropdown list
                		        $.ajax({
                                		url: <?php echo '\'' . site_url('entry/check_acc') . '/\''; ?> + ledger_value,
		                                success: function(bank) {
                		                      bank_or_cash = $.trim(bank);
//alert("inside dc-dropdown ===== dc="+dc+" acc="+account+" bank/cash="+bank_or_cash);
		                                      if((dc == 'D' && account == 'Expense') || (dc == 'D' && account == 'Asset' && bank_or_cash == '0')){
                		                            $(temp).show();
                                		      }else{
		                                            $(temp).hide();
                		                      }

							if((dc == 'C' && account == 'Liability')){
                                                                $(temp1).show();
                                                        }else{
                                                                $(temp1).hide();
                                                        }

                                                        if((dc == 'D' && account == 'Expense') || (dc == 'D' && account == 'Asset' && bank_or_cash == '0')){
                                                                $(temp2).show();
                                                        }else{
                                                                $(temp2).hide();
                                                        }
                                		}
                        		});
				}
			});
		}else{
			$(temp).hide();
			$(temp1).hide();
                        $(temp2).hide();
		}	

		var drValue = $(this).parent().next().next().children().attr('value');
                var crValue = $(this).parent().next().next().next().children().attr('value');
                if ($(this).parent().next().children().val() == "0") {
                
                        return;
                }

                drValue = parseFloat(drValue);
                if (isNaN(drValue))
                        drValue = 0;

                crValue = parseFloat(crValue);
                if (isNaN(crValue))
                        crValue = 0;

                if ($(this).attr('value') == "D") {
                        if (drValue == 0 && crValue != 0) {
                                $(this).parent().next().next().children().attr('value', crValue);
                        }
                        $(this).parent().next().next().next().children().attr('value', "");
                        $(this).parent().next().next().next().children().attr('disabled', 'disabled');
                        $(this).parent().next().next().children().attr('disabled', '');
                } else {
                        if (crValue == 0 && drValue != 0) {
                                $(this).parent().next().next().next().children().attr('value', drValue);
                        }
                        $(this).parent().next().next().children().attr('value', "");
                        $(this).parent().next().next().children().attr('disabled', 'disabled');
                        $(this).parent().next().next().next().children().attr('disabled', '');
                }
                /* Recalculate Total */
                $('.dr-item:first').trigger('change');
                $('.cr-item:first').trigger('change');

	});

	/* Ledger dropdown changed */
	$('.ledger-dropdown').live('change', function() {
		var ledgerid = $(this).attr('value');

		bank_cash = '';
				//Lines added by manshi........
                        var rowid = $(this);
                         $.ajax({
                                        url: <?php echo '\'' . site_url('entry/check_acc') . '/\''; ?> + ledgerid,
                                        success: function(bank) {
                                        bank_cash = $.trim(bank);
                                        //Check account either bank cash or non bank cash.......
                                        if(bank_cash == '1' ){
                                            //    $(".cheque-item").show();
                                              //  $("#ch_no").show();
                                               // $(".bank_value").show();
                                                rowid.parent().next().next().next().children().attr('disabled', 'disabled');
                                          }
                                }
                        });
                //....

		//fund code added by Priyanka
                var dc_value = $(this).parent().prev().children().attr('value');
                var dr_name = $(this).parent().next().children().attr('name');          
		var first_index = dr_name.lastIndexOf("[");
                var last_index = dr_name.lastIndexOf("]"); 
                var fund_index = dr_name.substring(first_index+1, last_index);                                             
                var temp = ".fund-list"+fund_index;
		var temp1 = ".type-dropdown"+fund_index;
                var temp2 = ".exp-dropdown"+fund_index;

		if(ledgerid > 0){
                $.ajax({
                                url: <?php echo '\'' . site_url('entry/ledger_code') . '/\''; ?> + ledgerid,
                                success: function(data) {
					
					account = $.trim(data);

					if(account == 'Income'){
	                                        if(dc_value == 'D')
        	                                        alert("You have made a wrong entry");
                                        }

					if(account == 'Expense' || account == 'Expense-e'){
	                                        if(dc_value == 'C')
        	                                        alert("You have made a wrong entry");
                                        }
                			//following code is for fund dropdown list
			                $.ajax({
                        			url: <?php echo '\'' . site_url('entry/check_acc') . '/\''; ?> + ledgerid,
			                        success: function(bank) {
                        			        bankorcash = $.trim(bank);
				//alert("inside ledger-dropdown ===== dc="+dc_value+" acc="+account+" bank/cash="+bankorcash);
			                                if((dc_value == 'D' && account == 'Expense') || (dc_value == 'D' && account == 'Asset' && bankorcash == '0')){
                        			                $(temp).show();
			                                }else{
                        			                $(temp).hide();
			                                }

							if((dc_value == 'C' && account == 'Liability')){
                                                                $(temp1).show();
                                                        }else{
                                                                $(temp1).hide();
                                                        }

                                                        if((dc_value == 'D' && account == 'Expense') || (dc_value == 'D' && account == 'Asset' && bankorcash == '0')){
                                                                $(temp2).show();
                                                        }else{
                                                                $(temp2).hide();
                                                        }
                        			}
                			});
				}
			});
		}else{
			$(temp).hide();
			$(temp1).hide();
                        $(temp2).hide();
		}

		if(ledgerid == "0") {
                        $(this).parent().next().children().attr('value', "");
                        $(this).parent().next().children().attr('value', "");
                        $(this).parent().next().next().children().attr('value', "");
                        $(this).parent().next().children().attr('disabled', 'disabled');
                } else {
                        $(this).parent().next().children().attr('disabled', '');
                        $(this).parent().next().children().attr('disabled', '');
                        $(this).parent().next().next().children().attr('disabled', '');
                }
                $(this).parent().next().children().trigger('change');
                $(this).parent().next().children().trigger('change');

                var rowid = $(this);
                if(ledgerid > 0){
                        $.ajax({
                                url: <?php echo '\'' . site_url('ledger/balance') . '/\''; ?> + ledgerid,
                                success: function(data) {
                                        var ledger_bal = parseFloat(data);
                                        if (isNaN(ledger_bal))
                                                ledger_bal = 0;
                                        if (jsFloatOps(ledger_bal, 0, '=='))
                                                rowid.parent().next().next().next().next().next().next().next().next().next().next().children().text("0");
                                        else if (jsFloatOps(ledger_bal, 0, '<'))
                                                rowid.parent().next().next().next().next().next().next().next().next().next().next().children().text("Cr " + -data);
                                        else
                                                rowid.parent().next().next().next().next().next().next().next().next().next().next().children().text("Dr " + data);
                                }
                        });
                } else {
                        rowid.parent().next().next().next().next().next().next().next().next().next().next().children().text("");
                }


	});

	/* Recalculate Total */
	$('table td .recalculate').live('click', function() {
		/* Recalculate Total */
		$('.dr-item:first').trigger('change');
		$('.cr-item:first').trigger('change');
	});

	/* Delete ledger row */
	$('table td .deleterow').live('click', function() {
		$(this).parent().parent().next().remove();
		$(this).parent().parent().remove();
		/* Recalculate Total */
		$('.dr-item:first').trigger('change');
		$('.cr-item:first').trigger('change');
	});

	/* Add ledger row */
	$('table td .addrow').live('click', function() {
		var cur_obj = this;
		var add_image_url = $(cur_obj).attr('src');
		$(cur_obj).attr('src', <?php echo '\'' . asset_url() . 'images/icons/ajax.gif' . '\''; ?>);
		$.ajax({
			url: <?php echo '\'' . site_url('entry/addrow/' . $add_type) . '\''; ?>,
			success: function(data) {
				$(cur_obj).parent().parent().next().after(data);
				$(cur_obj).attr('src', add_image_url);
				$('.dc-dropdown').trigger('change');
			}
		});
	});


	/** 
         * Check fund availability code
         * @author Priyanka Rawat       
         */
	$('#fund').live('change', function(){
		 var fund_ledger_id = $(this).children().val();
                //      var fund_amount = 0;
                        var fund_name = $(this).children().attr('name');
                        var first_index = fund_name.lastIndexOf("[");
                        var last_index = fund_name.lastIndexOf("]"); 
                        var dr_index = fund_name.substring(first_index+1, last_index);                                             
                        temp = '#dr_amount' + dr_index;
                        var dr_amount = $(temp).val();  

                        if(fund_ledger_id != 0)
                        {
                        $.ajax({
                                url: <?php echo '\'' . site_url('entry/fund_balance/') . '/\''; ?> + fund_ledger_id,
                                success: function(data){
                                        var fund_amount = $.trim(data);
                                        if (jsFloatOps(dr_amount, fund_amount, '>')) {
                                               alert("Amount payable is more than the available fund("+fund_amount+"). ");
                                        }
                                }
                        });
                        }      

        });

	$('#ledger').live('change', function(){
                var ledgerid = $(this).children().val();
//              alert("ledger id = "+ledgerid);

                var ledger_name = $(this).children().attr('name');
                var first_index = ledger_name.lastIndexOf("[");
                var last_index = ledger_name.lastIndexOf("]"); 
                var temp = ledger_name.substring(first_index+1, last_index);
                var id = "#tr-row"+temp;
//              alert("id = "+id);

                if(ledgerid > 0){
                        $(id).show();
                        $.ajax({
                               url: <?php echo '\'' . site_url('entry/set_group_id') . '/\''; ?> + ledgerid,
                               success: function(data) {
                                      //location.reload();
                                        $(id).val(data);
                               }
                       });
                }else{
                        $(id).hide();
                }
        });

	$('.sanc-type').change(function() {
                var name = $('.sanc-type').val();
                
                if(name != "select"){
                        if(name == "plan"){
                                $('.plan').show();
                                $('.plan-label').show();
                                $('.non-plan').hide();
                                $('.non-plan-label').hide();
                        }
                        else
                        {
                                $('.non-plan').show();
                                $('.non-plan-label').show();
                                $('.plan').hide();
                                $('.plan-label').hide();
                        }
                }else{
                        $('.plan').hide();
                        $('.plan-label').hide();
                        $('.non-plan').hide();
                        $('.non-plan-label').hide();    
                }
        });

	/* On page load initiate all triggers */
	$('.dc-dropdown').trigger('change');
	$('.ledger-dropdown').trigger('change');
	$('.dr-item:first').trigger('change');
	$('.cr-item:first').trigger('change');
	$('.sanc-type').trigger('change');
//	$('#dc').trigger('change');
  //      $('#ledger').trigger('change');
});

	function popupWin(url,popupName)
        {
                Win1=window.open(url,popupName,"resizable=0,scrollbars=1,height=400,width=400,align=left");
        }

</script>

<?php
	echo "<p align=\"right\">"; ?>
        <a href=javascript:popupWin(<?php echo '\'' . site_url('help/entry') . '\''; ?>,"newWin");><img src="<?php echo  asset_url(); ?>images/icons/hand.gif" />Help</a>
        <?php echo "</p>";	

	echo form_open('entry/edit/' . $current_entry_type['label'] . "/" . $entry_id);
	echo "<p>";
	echo form_label('Bill/Voucher Number', 'entry_number');
	echo " ";
	echo $current_entry_type['prefix'] . form_input($entry_number) . $current_entry_type['suffix'];
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "<span id=\"tooltip-target-1\">";
	echo form_label('Bill/Voucher Date', 'entry_date');
	echo " ";
	echo form_input_date_restrict($entry_date);
	echo "</span>";
	echo "<span id=\"tooltip-content-1\">Date format is " . $this->config->item('account_date_format') . ".</span>";
       
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; 
	echo "<span id=\"tooltip-target-2\">";
        echo form_label('Forward Reference Id', 'forward_refrence_id');
        echo " ";
        echo form_input($forward_refrence_id);
        echo "</span>";
        echo "<span id=\"tooltip-content-2\">Enter the Bill/Voucher Id of the related earlier dated transaction</span>";
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

	echo "<span id=\"tooltip-target-3\">";
        echo form_label('Backward Reference Id', 'backward_refrence_id');
        echo " ";
        echo form_input($backward_refrence_id);
        echo "</span>";
        echo "<span id=\"tooltip-content-3\">Enter the Bill/Voucher Id of the related back dated transaction</span>";

	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_label('Vendor/Voucher', 'vendor_number');
        echo " ";
        echo form_input($vendor_number);
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo"</p>";
	
	echo "<p>";
        echo form_label('Sanction Letter No.', 'sanc_letter_no');
        echo " ";
        echo form_input($sanc_letter_no);
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        echo form_label('Sanction Letter Date', 'sanc_letter_date');
        echo " ";
        echo form_input_date_restrict($sanc_letter_date);
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        echo form_label('Sanction Type');
        echo " ";
        echo form_dropdown('sanc_type', $sanc_type, $active_sanc_type, "class = \"sanc-type\"");
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        $plan_attr = array('class' => 'plan-label');
        echo form_label('Plan', 'plan', $plan_attr);
        echo " ";
        echo form_dropdown('plan', $plan, $active_plan, "class = \"plan\"");
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        $non_plan_attr = array('class' => 'non-plan-label');
        echo form_label('Non Plan', 'non_plan', $non_plan_attr);
        echo " ";
        echo form_dropdown('non_plan', $non_plan, $active_non_plan, "class = \"non-plan\"");
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo "</p>";

/*	
		echo "<span class=\"bank_value\">";
                echo form_label('Bank Name', 'bank_name');
                echo " ";
                echo "<td>" . form_input($bank_name) . "</td>";


                echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                echo form_label('Beneficiary Name', 'banif_name');
                echo " ";
                echo "<td>" . form_input($banif_name) . "</td>";
                echo"</span>";

                echo "(A - Asset, L - Libility, I - Income , E - Expenditure)";

                echo"</br>";
*/
	$val='';
	echo "<table class=\"entry-table\">";
	//echo "<thead><tr><th>Type</th><th>Ledger Account</th><th>Dr Amount</th><th>Cr Amount</th><th>Payment/Receipt by</th><th colspan=4 align=\"center\">Available Selections</th></tr></thead>";
	echo "<thead><tr><th>Type</th><th>Ledger Account</th><th>Dr Amount</th><th>Cr Amount</th><th>Payment/Receipt by</th><th>Sec Unit Name</th><th colspan=4 align=\"center\">Available Selections</th></tr></thead>";
	//echo "<thead><tr><th>Type</th><th>Ledger Account</th><th>Dr Amount</th><th>Cr Amount</th><th>Payment/Receipt by</th><th></th><th colspan=2>Actions</th><th>Cur Balance</th></tr></thead>";
	$this->db->select('name,bank_name,cheque_no')->from('reconcilation')->where('entry_no',$entry_id);
        $ledger_q = $this->db->get();
        if ($ledger = $ledger_q->row())
	$val=$ledger->cheque_no;
	foreach ($ledger_dc as $i => $ledger)
	{
		$dr_amount_item = array(
			'name' => 'dr_amount[' . $i . ']',
			//'id' => 'dr_amount[' . $i . ']',
			'id' => 'dr_amount'.$i,
			'maxlength' => '15',
			'size' => '15',
			'value' => isset($dr_amount[$i]) ? $dr_amount[$i] : "",
			'class' => 'dr-item',
		);
		$cr_amount_item = array(
			'name' => 'cr_amount[' . $i . ']',
			//'id' => 'cr_amount[' . $i . ']',
			'id' => 'cr_amount' . $i,
			'maxlength' => '15',
			'size' => '15',
			'value' => isset($cr_amount[$i]) ? $cr_amount[$i] : "",
			'class' => 'cr-item',
		);
		$secondaryid = array(
                        'name' => 'sunitid[' . $i . ']',
                        'id' => 'sunitid' . $i,
                        'maxlength' => '15',
                        'size' => '15',
                        'value' => isset($sunitid[$i]) ? $sunitid[$i] : "",
                );
/*		$cheque = array(
                        'name' => 'cheque[' . $i . ']',
                        'id' => 'cheque[' . $i . ']',
                        'maxlength' => '15',
                        'size' => '15',
                        'disabled'    => 'disable',
                       // 'value' => isset($cheque[$i]) ? $cheque[$i] : "",
			'value'    => $val,
                        'class' => 'cheque-item',
                );
*/
		echo "<tr>";

		echo "<td id = \"dc\">" . form_dropdown_dc('ledger_dc[' . $i . ']', isset($ledger_dc[$i]) ? $ledger_dc[$i] : "D") . "</td>";

		// these checks existed earlier
		/*if ($current_entry_type['bank_cash_ledger_restriction'] == '4')
			echo "<td>" . form_input_ledger('ledger_id[' . $i . ']', isset($ledger_id[$i]) ? $ledger_id[$i] : 0, '', $type = 'bankcash') . "</td>";
		else if ($current_entry_type['bank_cash_ledger_restriction'] == '5')
			echo "<td>" . form_input_ledger('ledger_id[' . $i . ']', isset($ledger_id[$i]) ? $ledger_id[$i] : 0, '', $type = 'nobankcash') . "</td>";
		else
			echo "<td>" . form_input_ledger('ledger_id[' . $i . ']', isset($ledger_id[$i]) ? $ledger_id[$i] : 0) . "</td>";
		*/

		// line added by Priyanka       
		echo "<td id = \"ledger\">" . form_input_ledger('ledger_id[' . $i . ']', isset($ledger_id[$i]) ? $ledger_id[$i] : 0) . "</td>";

		echo "<td id =\"dr\">" . form_input($dr_amount_item) . "</td>";
		echo "<td>" . form_input($cr_amount_item) . "</td>";
		echo "<td id = \"dc\">" . form_dropdown_payt('ledger_payt[' . $i . ']', isset($ledger_payt[$i]) ? $ledger_payt[$i] : "0") . "</td>";
		echo "<td>" . form_input($secondaryid) . "</td>";
			
		//echo "<td>" . form_input($cheque) . "</td>";

		/* code for fund list*/
		$temp_var = "fund-list".$i;
		echo "<td id = \"fund\">" . form_dropdown_fund('fund_list[' . $i . ']', isset($fund_list[$i]) ? $fund_list[$i] : 0, "class = \"".$temp_var."\"") . "</td>";

	/*	$temp1 = "type-dropdown".$i;
                echo "<td>" . form_dropdown_type('income_type[' . $i . ']', isset($income_type[$i]) ? $income_type[$i] : "Select", "class = \"".$temp1."\"") . "</td>";
*/
                $temp2 = "exp-dropdown".$i;
                echo "<td>" . form_dropdown_exptype('expense_type[' . $i . ']', isset($expense_type[$i]) ? $expense_type[$i] : "Select", "class = \"".$temp2."\"") . "</td>";

//		echo "<td>" . img(array('src' => asset_url() . "images/icons/add.png", 'border' => '0', 'alt' => 'Add Ledger', 'class' => 'addrow')) . "</td>";
//		echo "<td>" . img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Remove Ledger', 'class' => 'deleterow')) . "</td>";

//		echo "<td class=\"ledger-balance\"><div></div></td>";

		echo "</tr>";

		/**
                 * Code for diplaying parent child hierarchy
                 * for the selected ledger head.
                 * @author Priyanka Rawat <rpriyanka12@ymail.com>
                 */
                $row_temp = "tr-row".$i;
                $data = array(
                      'name'        => 'parent',
                      'id'          => $row_temp,
                      'value'       => '',
                      //'style'       => 'width:50%; height: 10%; border: none;',
                      'style'       => 'width:200%; border: none;',
                );
                echo "<tr>";
                echo "<td></td>";
                echo "<td>";
                        echo form_input($data);
                        echo "</td>";
                echo "</tr>";
	}

	echo "<tr><td colspan=7></td></tr>";
	echo "<tr id=\"total\"><td colspan=2><strong>Total</strong></td><td id=\"dr-total\">0</td><td id=\"cr-total\">0</td><td>" . img(array('src' => asset_url() . "images/icons/gear.png", 'border' => '0', 'alt' => 'Recalculate Total', 'class' => 'recalculate', 'title' => 'Recalculate Total')) . "</td><td></td><td></td></tr>";
	echo "<tr id=\"difference\"><td colspan=2><strong>Difference</strong></td><td id=\"dr-diff\"></td><td id=\"cr-diff\"></td><td></td><td></td><td></td></tr>";

	echo "</table>";

	echo "<p>";
	echo form_label('Narration', 'entry_narration');
	echo "<br />";
	echo form_textarea($entry_narration);
	echo "</p>";
	
	echo "<p>";
        echo form_label('Entry Type', 'entry_name');
        echo "<br />";
        echo form_dropdown('entry_name', $entry_name, $active_entry_name, "class = \"type_dropdown\"");
        echo "</p>";


	echo "<p>";
	echo form_label('Tag', 'entry_tag');
	echo " ";
	echo form_dropdown('entry_tag', $entry_tags, $entry_tag);
	echo "</p>";

	echo form_hidden('has_reconciliation', $has_reconciliation);

	echo "<p>";
	echo form_submit('submit', 'Update & Verify');
	echo " ";
	echo anchor('entry/edit/' . $current_entry_type['label'] . "/" . $entry_id, 'Reload', array('title' => 'Reload ' . $current_entry_type['name'] . ' Entry Original Data'));
	echo " | ";
	echo anchor('entry/show/' . $current_entry_type['label'], 'Back', array('title' => 'Back to ' . $current_entry_type['name'] . ' Entries'));
	echo "</p>";

	echo form_close();
?>

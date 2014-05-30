<?php
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

		/**
                 * Following code is for fund drop down list 
                 * @author Priyanka Rawat
                 */
                var bank_cash = '';
                var ledger_value = $(this).parent().prev().children().attr('value');
                //alert("ledger value = "+ledger_value);

                $.ajax({
                                        url: <?php echo '\'' . site_url('entry/check_acc') . '/\''; ?> + ledger_value,
                                        success: function(bank) {
                                                bank_cash = $.trim(bank);
                                                //alert("bank cash ="+bank_cash);
                                        }
                });

                if(bank_cash == '0'){
                        var fundtotal = 0;
                        var fund_amount = 0;

                        $("table tr #fund").each(function() {
                                var fund_ledger_id = $(this).children.val();

                                $.ajax({
                                        url: <?php echo '\'' . site_url('entry/ledger_fund/') . '/\''; ?> + fund_ledger_id,
                                        success: function(data){
                                                fund_amount = $.trim(data);
                                                fund_amount = parseFloat(fund_amount);
                                                if (isNaN(fund_amount))
                                                        fund_amount = 0;
                                                fundTotal = jsFloatOps(fundTotal, fund_amount, '+');

                                                if(drTotal > fundTotal)
                                                        alert("Amount payable is more than the available fund.");
                                        }
                                });
                        });
                }
                //...
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
		/**
                 * Following code is for fund drop down list 
                 * @author Priyanka Rawat
                 */
		dc = $(this).attr('value');
		var bank_cash = '';
                var account = '';
                var ledger_value = $(this).parent().next().children().attr('value');
                //alert("ledger value = "+ledger_value);

                $.ajax({
                                        url: <?php echo '\'' . site_url('entry/check_acc') . '/\''; ?> + ledger_value,
                                        success: function(bank) {
                                                bank_cash = $.trim(bank);
                                                //alert("bank cash ="+bank_cash);
                                        }
                });

                
                        var dr_name = $(this).parent().next().next().children().attr('name');             
//                alert("dr name = "+dr_name);

                        $.ajax({
                                url: <?php echo '\'' . site_url('entry/ledger_code') . '/\''; ?> + ledger_value,
                                success: function(data) {
                                        /*var code = $.trim(data);

                                        var n = code.indexOf("40");
                                        if(n == 0)
                                                account = 'Expense';

                                        n = code.indexOf("30");
                                        if(n == 0)
                                                account = 'Income';
                                        
                                        n = code.indexOf("20");
                                        if(n == 0)
                                                account = 'Asset';

					*/

					account = $.trim(data);

                                        if(dc == 'D' && account == 'Income'){
                                                alert("You have made a wrong entry");
                                        }

                                        if(dc == 'C' && account == 'Expense'){
                                                alert("You have made a wrong entry");
                                        }

                                        //following code is for fund dropdown list
                                        var first_index = dr_name.lastIndexOf("[");
                                        var last_index = dr_name.lastIndexOf("]"); 
                                        var fund_index = dr_name.substring(first_index+1, last_index);                                               
                                        temp = ".fund-list"+fund_index;

                                        if((dc == 'D' && account == 'Expense') || (dc == 'D' && account == 'Asset' && bank_cash == '0')){
                                                //alert("i m here 1");
                                                //alert(temp);
                                                $(temp).show();
                                        }else{
                                                //alert("pp 1");
                                                //alert(temp);
                                                $(temp).hide();
                                        }
                                }
                        });
                
                //....

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
		var ledgerid = $(this).val();
		var bank_cash = '';

			//Line added by manshi........
                        var rowid = $(this);
	                         $.ajax({
					
                                        url: <?php echo '\'' . site_url('entry/check_acc') . '/\''; ?> + ledgerid,
                                        success: function(bank) {
                                        bank_cash = $.trim(bank);
                                        if(bank_cash == '1' ){
                                                $(".cheque-item").show();
                                                $("#ch_no").show();
                                                $(".bank_value").show();
                                                rowid.parent().next().next().next().children().attr('disabled', '');
                                          	}
         				//Define Entry Types.........
                                        if(dc == 'C' && bank_cash == '1')
                                        {
                                        var value =$("select.type_dropdown option:selected").val("Payment");
                                        var value = $('select.type_dropdown option:selected').text('Payment');
                                        }
                                        if(dc == 'D' && bank_cash == '1')
                                        {
                                        var value =$("select.type_dropdown option:selected").val("Receipt");
                                        var value = $('select.type_dropdown option:selected').text('Receipt');

                                        }
                                        if( window.globalVar == '1' &&  bank_cash == '1')
                                        {
                                        var value =$("select.type_dropdown option:selected").val("Contra");
                                        var value = $('select.type_dropdown option:selected').text('Contra');   
                                        }
                                        if( window.globalVar == '1' &&  bank_cash == '1')
                                        {
                                        var value =$("select.type_dropdown option:selected").val("Contra");
                                        var value = $('select.type_dropdown option:selected').text('Contra');  
                                        }
                                        if( window.globalVar == '0' &&  bank_cash == '0')
                                        {
                                        var value =$("select.type_dropdown option:selected").val("Journal");
                                        var value = $('select.type_dropdown option:selected').text('Journal');   
                                        }
                        
                                        //Define Globle Variable in jquery......                        
                                        if(bank_cash == '1')
                                        {
                                        window.globalVar = "1";
                                        
                                        }
					if(bank_cash == '0')
                                        {
                                        window.globalVar = "0";
                                        
                                        }

	

					}
                        	});
                //....

		/**
                 * Following code is for fund drop down list 
                 * @author Priyanka Rawat
                 */

                var account = '';
                var dc_value = $(this).parent().prev().children().attr('value');
//              alert("dc value = "+dc_value);
                var dr_name = $(this).parent().next().children().attr('name');          
//              alert("dr name = "+dr_name);

                $.ajax({
                                url: <?php echo '\'' . site_url('entry/ledger_code') . '/\''; ?> + ledgerid,
                                success: function(data) {
                                        /*var code = $.trim(data);
                                        
                                        var n = code.indexOf("40");
                                        if(n == 0)
                                                account = 'Expense';

                                        n = code.indexOf("30");
                                        if(n == 0)
                                                account = 'Income';
                                        
                                        n = code.indexOf("20");
                                        if(n == 0)
                                                account = 'Asset';
					*/

					account = $.trim(data);

                                        if(dc_value == 'D' && account == 'Income'){
                                                alert("You have made a wrong entry");
                                        }

                                        if(dc_value == 'C' && account == 'Expense'){
                                                alert("You have made a wrong entry");
                                        }

                                        //following code is for fund dropdown list
                                        var first_index = dr_name.lastIndexOf("[");
                                        var last_index = dr_name.lastIndexOf("]"); 
                                        var fund_index = dr_name.substring(first_index+1, last_index);                                             
                                        temp = ".fund-list"+fund_index;

                                        if((dc_value == 'D' && account == 'Expense') || (dc_value == 'D' && account == 'Asset' && bank_cash == '0')){
                                                //alert("i m here");
                                                //alert(temp);
                                                $(temp).show();
                                        }else{
                                                //alert("pp");
                                                //alert(temp);
                                                $(temp).hide();
                                        }
                                }
                        });

                //.... 
		
		//this line existed earlier
		//if ($(this).val() == "0") {
		if(ledgerid == "0") {
			$(this).parent().next().children().attr('value', "");
			$(this).parent().next().next().children().attr('value', "");
			$(this).parent().next().children().attr('disabled', 'disabled');
			$(this).parent().next().next().children().attr('disabled', 'disabled');
		} else {
			$(this).parent().next().children().attr('disabled', '');
			$(this).parent().next().next().children().attr('disabled', '');
			$(this).parent().prev().children().trigger('change');
		}
		$(this).parent().next().children().trigger('change');
		$(this).parent().next().next().children().trigger('change');

		//this line existed
		//var ledgerid = $(this).val();

		var rowid = $(this);
		if (ledgerid > 0) {
			$.ajax({
				url: <?php echo '\'' . site_url('ledger/balance') . '/\''; ?> + ledgerid,
				success: function(data) {
					var ledger_bal = parseFloat(data);
					if (isNaN(ledger_bal))
						ledger_bal = 0;
					if (jsFloatOps(ledger_bal, 0, '=='))
						rowid.parent().next().next().next().next().next().next().children().text("0");
					else if (jsFloatOps(ledger_bal, 0, '<'))
						rowid.parent().next().next().next().next().next().next().children().text("Cr " + -data);
					else
						rowid.parent().next().next().next().next().next().next().children().text("Dr " + data);
				}
			});
		} else {
			rowid.parent().next().next().next().next().next().next().children().text("");
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
				$(cur_obj).parent().parent().after(data);
				$(cur_obj).attr('src', add_image_url);
			}
		});
	});

	/** 
         * Check fund availability code
         * @author Priyanka Rawat       
         */
        $('#fund').live('change', function(){
                var fundTotal = 0;

                $("table tr #fund").each(function() {
                        var fund_ledger_id = $(this).children().val();
                        var fund_amount = 0;
                
                        $.ajax({
                                url: <?php echo '\'' . site_url('entry/ledger_fund/') . '/\''; ?> + fund_ledger_id,
                                success: function(data){
                                        fund_amount = $.trim(data);
        
                                        fund_amount = parseFloat(fund_amount);
                                        if (isNaN(fund_amount))
                                                fund_amount = 0;
                                        fundTotal = jsFloatOps(fundTotal, fund_amount, '+');
                                                                        
                                        var drTotal = 0;

                                        $("table tr .dr-item").each(function() {
                                                var curDr = $(this).attr('value');
                                                curDr = parseFloat(curDr);
                                                if (isNaN(curDr))
                                                        curDr = 0;
                                                drTotal = jsFloatOps(drTotal, curDr, '+');
                                        });
                                        if(drTotal > fundTotal)
                                                alert("Amount payable is more than the available fund.");
                                }
                        });
                });	

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


	/* On page load initiate all triggers */
	$('.dc-dropdown').trigger('change');
	$('.ledger-dropdown').trigger('change');
	$('.dr-item:first').trigger('change');
	$('.cr-item:first').trigger('change');
	$('#fund').trigger('change');
	response.setIntHeader("Refresh", 1);	

});
	

</script>

<?php
	echo "<p align=\"right\">";
        echo anchor_popup('help/entry', 'Help'.img(array('src' => asset_url() . "images/icons/tip.png", 'alt' => 'Help')));
        echo "</p>";

	echo form_open('entry/add/' . $current_entry_type['label']);
	echo "<p>";
	echo "<span id=\"tooltip-target-1\">";
	echo form_label('Bill/Voucher Number', 'entry_number');
	echo " ";
	echo $current_entry_type['prefix'] . form_input($entry_number) . $current_entry_type['suffix'];
	echo "</span>";
	echo "<span id=\"tooltip-content-1\">Leave Bill / Voucher Number empty for auto numbering</span>";
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

	echo "<span id=\"tooltip-target-2\">";
	echo form_label('Bill/Voucher Date', 'entry_date');
	echo " ";
	echo form_input_date_restrict($entry_date);
	echo "</span>";
	echo "<span id=\"tooltip-content-2\">Date format is " . $this->config->item('account_date_format') . ".</span>";
	echo "     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ";
//        echo "(A - Asset, L - Libility, I - Income , E - Expenditure)";
//	echo "</p>";

//	echo "<p>";
	echo "<span id=\"tooltip-target-3\">";
        echo form_label('Reference Id', 'backward_refrence_id');
        echo " ";
        echo form_input($backward_refrence_id);
        echo "</span>";
        echo "<span id=\"tooltip-content-3\">Enter the Bill/Voucher Id of the related back dated transaction</span>";
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "</p>";

                echo "<span class=\"bank_value\">";
                echo form_label('Bank Name', 'bank_name');
                echo " ";
                echo  form_input($bank_name);


                echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                echo form_label('Beneficiary Name', 'banif_name');
                echo " ";
                echo form_input($banif_name);
                echo"</span>";

                echo "(A - Asset, L - Libility, I - Income , E - Expenditure)";

                echo"</br>";



	echo "<table class=\"entry-table\">";
	echo "<thead><tr><th>Type</th><th>Ledger Account</th><th>Dr Amount</th><th>Cr Amount</th><th id=\"ch_no\">Cheque No</th><th colspan=2></th><th colspan=2>Cur Balance</th></tr></thead>";

	foreach ($ledger_dc as $i => $ledger)
	{
		$dr_amount_item = array(
			'name' => 'dr_amount[' . $i . ']',
			'id' => 'dr_amount[' . $i . ']',
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
		$cheque = array(
                        'name' => 'cheque[' . $i . ']',
                        'id' => 'cheque[' . $i . ']',
                        'maxlength' => '15',
                        'size' => '15',
                        'disabled'    => 'disable',
                        'value' => isset($cheque[$i]) ? $cheque[$i] : "",
                        'class' => 'cheque-item',
                );

		echo "<tr>";

		echo "<td>" . form_dropdown_dc('ledger_dc[' . $i . ']', isset($ledger_dc[$i]) ? $ledger_dc[$i] : "D") . "</td>";

		// these checks existed earlier
		/*if ($current_entry_type['bank_cash_ledger_restriction'] == '4')
			echo "<td>" . form_input_ledger('ledger_id[' . $i . ']', isset($ledger_id[$i]) ? $ledger_id[$i] : 0, '', $type = 'bankcash') . "</td>";
		else if ($current_entry_type['bank_cash_ledger_restriction'] == '5')
			echo "<td>" . form_input_ledger('ledger_id[' . $i . ']', isset($ledger_id[$i]) ? $ledger_id[$i] : 0, '', $type = 'nobankcash') . "</td>";
		else
			echo "<td>" . form_input_ledger('ledger_id[' . $i . ']', isset($ledger_id[$i]) ? $ledger_id[$i] : 0) . "</td>";
		*/

		// line added by Priyanka	
		echo "<td id =\"ledger\">" . form_input_ledger('ledger_id[' . $i . ']', isset($ledger_id[$i]) ? $ledger_id[$i] : 0) . "</td>";

		echo "<td>" . form_input($dr_amount_item) . "</td>";
		echo "<td>" . form_input($cr_amount_item) . "</td>";
		echo "<td>" . form_input($cheque) . "</td>";	
	
		$temp = "fund-list".$i;
                echo "<td id =\"fund\">" . form_dropdown('fund_list[' . $i . ']', $fund_list, $fund_list_active, "class = \"".$temp."\"") . "</td>";

		echo "<td>" . img(array('src' => asset_url() . "images/icons/add.png", 'border' => '0', 'alt' => 'Add Ledger', 'class' => 'addrow')) . "</td>";
		echo "<td>" . img(array('src' => asset_url() . "images/icons/delete.png", 'border' => '0', 'alt' => 'Remove Ledger', 'class' => 'deleterow')) . "</td>";

		echo "<td class=\"ledger-balance\"><div></div></td>";

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
                      'maxlength'   => '300',
                      //'style'       => 'width:100%',
			'style'       => 'width:200%; border: none;'
                );
                echo "<tr>";
                echo "<td></td>";
                echo "<td>";
                        echo form_input($data);
                        echo "</td>";
                echo "</tr>";
	}

	echo "<tr><td colspan=\"7\"></td></tr>";	
	echo "<tr id=\"entry-total\"><td colspan=2><strong>Total</strong></td><td id=\"dr-total\">0</td><td id=\"cr-total\">0</td><td>" . img(array('src' => asset_url() . "images/icons/gear.png", 'border' => '0', 'alt' => 'Recalculate Total', 'class' => 'recalculate', 'title' => 'Recalculate Total')) . "</td><td></td><td></td></tr>";
	echo "<tr id=\"entry-difference\"><td colspan=2><strong>Difference</strong></td><td id=\"dr-diff\"></td><td id=\"cr-diff\"></td><td></td><td></td><td></td></tr>";

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


	echo "<p id=\"reload\">";
	echo form_submit('submit', 'Create');
	echo " ";
	echo anchor('entry/show/' . $current_entry_type['label'], 'Back', array('title' => 'Back to ' . $current_entry_type['name'] . ' Entries'));
	echo "</p>";

	echo form_close();
?>

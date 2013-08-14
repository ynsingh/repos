<script type="text/javascript">
$(document).ready(function() {
var num = "0";
var parent_code = "0";
var data_code = "0";
var i = "0";
var j = "0";
var rows = "0";
var ledger_code = "0";

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
                if (op == '>') {
                        if (param1 > param2)
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
        }

	/* Calculate ledger code */
/*        $('.ledger-parent').change(function() {
                var parent_id = $(".ledger-parent").val();

                $.ajax({
                        url: <?php echo '\'' . site_url('ledger/get_numOfChild') . '/\''; ?> + parent_id,
                        success: function(json) {
                                var obj = jQuery.parseJSON(json);
                                num = obj['NUM'];
                        }
                });

                $.ajax({
                        url: <?php echo '\'' . site_url('ledger/get_group_code') . '/\''; ?> + parent_id,
                        success: function(json) {
                                var obj = jQuery.parseJSON(json);
                                parent_code = obj['LCODE'];
                        }
                });

		//alert("parent id = "+parent_id);
		//alert("num = "+num);
		//alert("parent code = "+parent_code);

                if(jsFloatOps('0', num, '=='))
                {
                        data_code = parent_code + "01";
                } else{
                        if(jsFloatOps('9', num, '>'))
                        {
                                i = 0;
                                do{
					//alert("inside if part 1 ");
                                        i = jsFloatOps(i, '1', '+');
                                        var l_num = jsFloatOps(num, i, '+');
                                        data_code = parent_code + "0" + l_num;
					//alert("data code 1 = "+data_code);
                                        $.ajax({
                                                url: <?php echo '\'' . site_url('ledger/get_ledger_code') . '/\''; ?> + data_code,
                                                success: function(json) {
                                                        var obj = jQuery.parseJSON(json);
                                                        rows = obj['ROWS'];
							//alert("rows inside if 1 = "+rows);
                                                }
                                        });
                                }while(jsFloatOps(rows, '0', '>'));
                        } else{
                                i = 0;
                                do{
					//alert("inside else part 1");
                                        i = jsFloatOps(i, '1', '+');
                                        var l_num = jsFloatOps(num, i, '+');
                                        data_code = parent_code + l_num;
					//alert("data code 2 = "+data_code);
                                        $.ajax({
                                                url: <?php echo '\'' . site_url('ledger/get_ledger_code') . '/\''; ?> + data_code,
                                                success: function(json) {
                                                        var obj = jQuery.parseJSON(json);
                                                        rows = obj['ROWS'];
							//alert("rows inside else 1 = "+rows);
                                                }
                                        });
                                }while(jsFloatOps(rows, '0', '>'));
                        }
                }//else

                j = 0;
                do{
                        if(jsFloatOps(j, '0', '>'))
                        {
                               // var g_num = jsFloatOps(num, j, '+');
				num = jsFloatOps(num, j, '+');
                                if(jsFloatOps('9', num, '>'))
                                {
                                        i = 0;
                                        do{
						//alert("inside if part 2 ");
                                                i = jsFloatOps(i, '1', '+');
                                                var temp = jsFloatOps(num, i, '+');
                                                data_code = parent_code + "0" + temp;
						//alert("data code 3 = "+data_code);
                                                $.ajax({
                                                        url: <?php echo '\'' . site_url('ledger/get_ledger_code') . '/\''; ?> + data_code,
                                                        success: function(json) {
                                                                var obj = jQuery.parseJSON(json);
                                                                rows = obj['ROWS'];
								//alert("rows inside if 2 = "+rows);
                                                        }
                                                });
                                        }while(jsFloatOps(rows, '0', '>'));
                                } else{
                                        i = 0;
                                        do{
						//alert("inside else part 2");
                                                i = jsFloatOps(i, '1', '+');
                                                var temp = jsFloatOps(num, i, '+');
                                                data_code = parent_code + temp;
						//alert("data code 4 = "+data_code);
                                                $.ajax({
                                                        url: <?php echo '\'' . site_url('ledger/get_ledger_code') . '/\''; ?> + data_code,
                                                        success: function(json) {
                                                                var obj = jQuery.parseJSON(json);
                                                                rows = obj['ROWS'];
								 //alert("rows inside else 2 = "+rows);
                                                        }
                                                });
                                        }while(jsFloatOps(rows, '0', '>'));
                                }       
                        }
                        $.ajax({
                                url: <?php echo '\'' . site_url('ledger/get_groupCode') . '/\''; ?> + data_code,
                                success: function(json) {
                                        var obj = jQuery.parseJSON(json);
                                        rows = obj['ROWS'];
					//alert("rows in group = "+ rows);
                                }
                        });
		
			j = jsFloatOps(j, '1', '+');
                }while(jsFloatOps(rows, '0', '>'));
		//alert ("final data code = "+data_code);
//		 $("#ledger").val(data_code);
		ledger_code = data_code;
        });*/
//        $('.ledger-parent').trigger('change');

/*	$('#myform').submit(function() {
                
        /*        $.ajax({
                        url: ?php echo '\'' . site_url('ledger/set_ledgerCode') . '/\''; ? + ledger_code,
                        success: function() {
                        }
                });
                
		$('input[name=ledger_code]').val(ledger_code);
       });*/

});
</script>
<?php
	echo form_open('ledger/add');
//	$attributes = array('id' => 'myform');
  //      echo form_open('ledger/add/', $attributes);

/*	echo "<p>";
	echo form_label('Ledger code', 'ledger_code');
	echo "<br />";
	$data = array(
        'name'        => 'ledger_code',
        'id'          => 'ledger',
        'value'       => '',
        );
	//echo form_input($ledger_code);
	echo form_input($data);
	echo "</p>";
*/
/*	$data = array(
              'name'  => 'ledger_code',
              'value' => '',
              'id'   => 'ledger-code',
                'style' => 'display:none;',
        );

        echo form_input($data);
*/
	echo "<p>";
	echo form_label('Ledger name', 'ledger_name');
	echo "<br />";
	echo form_input($ledger_name);
	echo "</p>";

	echo "<p>";
	echo form_label('Parent group', 'ledger_group_id');
	echo "<br />";
	echo form_dropdown('ledger_group_id', $ledger_group_id, $ledger_group_active,"class=\"ledger-parent\"");
	echo "</p>";

	echo "<p>";
	echo form_label('Opening balance', 'op_balance');
	echo "<br />";
	echo "<span id=\"tooltip-target-1\">";
	echo form_dropdown_dc('op_balance_dc', $op_balance_dc);
	echo " ";
	echo form_input($op_balance);
	echo "</span>";
	echo "<span id=\"tooltip-content-1\">&nbsp;&nbsp;Assets / Expenses => Dr. Balance<br />Liabilities / Incomes => Cr. Balance</span>";
	echo "</p>";

	echo "<p>";
	echo "<span id=\"tooltip-target-2\">";
	echo form_checkbox('ledger_type_cashbank', 1, $ledger_type_cashbank) . " Bank or Cash Account";
	echo "</span>";
	echo "<span id=\"tooltip-content-2\">Select if Ledger account is a Bank account or a Cash account.</span>";
	echo "</p>";

	echo "<p>";
	echo "<span id=\"tooltip-target-3\">";
	echo form_checkbox('reconciliation', 1, $reconciliation) . " Reconciliation";
	echo "</span>";
	echo "<span id=\"tooltip-content-3\">If enabled account can be reconciled from Reports > Reconciliation</span>";
	echo "</p>";

	echo "<p>";
	echo form_submit('submit', 'Create');
	echo " ";
	echo anchor('account', 'Back', 'Back to Chart of Accounts');
	echo "</p>";

	echo form_close();
?>

<script type="text/javascript">
$(document).ready(function() {
var num = "0";
var parent_code = "0";
var data_code = "0";
var i = "0";
var j = "0";
var rows = "0";
// group_code has been added here 
// since this value be sent at the
//time of form submission
var group_code = "0";

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

        $('.group-parent').change(function() {

		if ($(this).val() == "3" || $(this).val() == "4") {
                        $('.affects-gross').show();
                } else {
                        $('.affects-gross').hide();
                }
	});	
		/* Calculate group code 
                var parent_id = $(".group-parent").val();

                $.ajax({
                        url: <?php echo '\'' . site_url('group/get_numOfChild') . '/\''; ?> + parent_id,
                        success: function(json) {
                                var obj = jQuery.parseJSON(json);
                                num = obj['NUM'];
                        }
                });

                $.ajax({
                        url: <?php echo '\'' . site_url('group/get_group_code') . '/\''; ?> + parent_id,
                        success: function(json) {
                                var obj = jQuery.parseJSON(json);
                                parent_code = obj['GCODE'];
                        }
                });

		//alert("parent id = "+parent_id);
		//alert("num = "+num);
		//alert("parent_code = "+parent_code);

                if(jsFloatOps('0', num, '=='))
                {
                        data_code = parent_code + "01";
                } else{
                        if(jsFloatOps('9', num, '>'))
                        {
                                i = 0;
                                do{
					//alert("inside if part 1");	
	
                                        i = jsFloatOps(i, '1', '+');
                                        var g_num = jsFloatOps(num, i, '+');
                                        data_code = parent_code + "0" + g_num;
	
					//alert("data code 1 = "+data_code);

                                        $.ajax({
                                                url: <?php echo '\'' . site_url('group/get_Groupcode') . '/\''; ?> + data_code,
                                                success: function(json) {
                                                        var obj = jQuery.parseJSON(json);
                                                        rows = obj['ROWS'];
							//alert("rows inside if part 1 = "+rows);
                                                }
                                        });
                                }while(jsFloatOps(rows, '0', '>'));
                        } else{
                                i = 0;
                                do{
					//alert("inside else part 1");

                                        i = jsFloatOps(i, '1', '+');
                                        var g_num = jsFloatOps(num, i, '+');
                                        data_code = parent_code + g_num;

					//alert("data code 2 = "+data_code);

                                        $.ajax({
                                                url: <?php echo '\'' . site_url('group/get_Groupcode') . '/\''; ?> + data_code,
                                                success: function(json) {
                                                        var obj = jQuery.parseJSON(json);
                                                        rows = obj['ROWS'];
							//alert("rows inside else part 1 = "+rows);
                                                }
                                        });
					//alert("rows = "+rows);
                                }while(jsFloatOps(rows, '0', '>'));
                        }
                }//else

                j = 0;
                do{
                        if(jsFloatOps(j, '0', '>'))
                        {
                                //var g_num = jsFloatOps(num, j, '+');
				num = jsFloatOps(num, j, '+');
                                if(jsFloatOps('9', num, '>'))
                                {
                                        i = 0;
                                        do{
						//alert("inside if part 2");

                                                i = jsFloatOps(i, '1', '+');
                                                var temp = jsFloatOps(num, i, '+');
                                                data_code = parent_code + "0" + temp;
			
						//alert("data code 3 = "+data_code);

                                                $.ajax({
                                                        url: <?php echo '\'' . site_url('group/get_Groupcode') . '/\''; ?> + data_code,
                                                        success: function(json) {
                                                                var obj = jQuery.parseJSON(json);
                                                                rows = obj['ROWS'];
								//alert("rows inside if part2 = "+rows);
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
                                                        url: <?php echo '\'' . site_url('group/get_Groupcode') . '/\''; ?> + data_code,
                                                        success: function(json) {
                                                                var obj = jQuery.parseJSON(json);
                                                                rows = obj['ROWS'];
								//alert("rows inside else part 2 = "+rows);
                                                        }
                                                });
                                        }while(jsFloatOps(rows, '0', '>'));
                                }       
                        }
                        $.ajax({
                                url: <?php echo '\'' . site_url('group/get_ledgerCode') . '/\''; ?> + data_code,
                                success: function(json) {
                                        var obj = jQuery.parseJSON(json);
                                        rows = obj['ROWS'];
					//alert("rows for ledger = "+rows);
                                }
                        });

			j = jsFloatOps(j, '1', '+');
                }while(jsFloatOps(rows, '0', '>'));
		//alert(" final data code = "+data_code);
		$('input[name=group_code]').val(data_code);
	
		//has been set in global variable
                // $("#group").val(data_code);
//		group_code = data_code;
//        });
  //      $('.group-parent').trigger('change');

	});*/
	$('.group-parent').trigger('change');

});
</script>
<?php
	echo form_open('group/add');
//	$attributes = array('id' => 'myform');
//	echo form_open('group/add', $attributes);

/*	echo "<p class=\"group-code\">";
	echo form_label('Group code', 'group_code');
	echo "<br />";
	$data = array(
        'name'        => 'group_code',
        'id'          => 'group',
        'value'       => '',
        );
//	echo form_input($group_code);
	echo form_input($data);
	echo "</p>";*/

/*	$data = array(
              'name'  => 'group_code',
              'value' => '',
              'id'   => 'group-code',
		'style' => 'display:none;',
        );

	echo form_input($data);*/

	echo "<p>";
	echo form_label('Group name', 'group_name');
	echo "<br />";
	echo form_input($group_name);
	echo "</p>";

	echo "<p>";
	echo form_label('Parent group', 'group_parent');
	echo "<br />";
	echo form_dropdown('group_parent', $group_parent, $group_parent_active, "class = \"group-parent\"");
	echo "</p>";

	echo "<p class=\"affects-gross\">";
	echo "<span id=\"tooltip-target-1\">";
	echo form_checkbox('affects_gross', 1, $affects_gross) . " Affects Gross Profit/Loss Calculations";
	echo "</span>";
	echo "<span id=\"tooltip-content-1\">If selected the Group account will affect Gross Profit and Loss calculations, otherwise it will affect only Net Profit and Loss calculations.</span>";
	echo "</p>";

	echo "<p>";
	echo form_submit('submit', 'Create');
	echo " ";
	echo anchor('account', 'Back', 'Back to Chart of Accounts');
	echo "</p>";

	echo form_close();

?>

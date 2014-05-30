<script type="text/javascript">

$(document).ready(function() {
 var count = 0;
 var old_amount = 0;
	
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
		if (op == '-') {
                        result = param1 - param2;
                        result = result/100;
                        return result;
                }

        }
/*
procedure live(){
if(count >0)
{
	get 'id' of the value changed;
	get 'amount' of the value changed;

	// update parent projection
	updateParentProjection(id, amount);

	//update target projection
	get 'target_amount' where id = '60';
        target_amount = target_amount + amount;
        set 'target_amount' where id = 60;
}
count++;
}

procedure updateParentProjection(proj_id, proj_amount){
	calculate 'parent_code' for 'this' projection;
        if(length of parent_code > 0)
        {
		get 'amount' where id = parent_code;
		amount = amount + proj_amount;
		set 'amount' where id = parent_code;

		updateParentProjection(parent_code, proj_amount);
	}
}
*/
	$('.projection').click(function() {
		count = jsFloatOps(count, 1, '+');
		//old_amount = $(this).attr('value');		
		var old_proj_amount = $(this).attr('value');
                var old_amount_array = old_proj_amount.split(',');
                
                for (var i = 0; i < old_amount_array.length; i++) {
                        old_amount = old_amount + old_amount_array[i];
                }
	});

	// .projection implies that class = projection
	$('.projection').live('change', function() {   
		count = parseFloat(count);
		if(jsFloatOps(count, 0, '>')){
		        var code = $(this).attr('id');
			//var amount = $(this).attr('value');
			var proj_amount = $(this).attr('value');
			var amount_array = proj_amount.split(',');
			var amount = '';
			for (var i = 0; i < amount_array.length; i++) {
				amount = amount+amount_array[i];
		        }

			var changed_amount = jsFloatOps(amount, old_amount, '-');
			old_amount = '';
			//update parent projection
			updateParentProjection(code, changed_amount);
			
			//update target projection
		        //get 'target_amount' where id = '60';
			// #60 impiles that id = 60
			$.ajax({
                                url: <?php echo '\'' . site_url('projection/get_target_projection_code') . '\''; ?>,
                                success: function(data) {
					code = $.trim(data);
					temp = "#"+code;
					//var target_amount = $(temp).val();
					var target_proj_amount = $(temp).val();
		                        var target_amount_array = target_proj_amount.split(',');
                			var target_amount = '';
		                        for (var i = 0; i < target_amount_array.length; i++) {
                                		target_amount = target_amount + target_amount_array[i];
		                        }
					
                		        target_amount = jsFloatOps(target_amount, changed_amount, '+');
                		        $(temp).val(target_amount);
				}
			});

			//var target_amount = $("#60").val();
			//target_amount = target_amount + changed_amount;
			//target_amount = jsFloatOps(target_amount, changed_amount, '+');
		        //set 'target_amount' where id = 60;
			//$("#60").val(target_amount);
		}
		
	});

	function updateParentProjection(proj_code, proj_amount){
        	//calculate 'parent_code' for 'this' projection;
		parent_code = proj_code.slice(0,-2);
	        if(jsFloatOps(parent_code, 0, '>'))
        	{
	                //get 'amount' where id = parent_code;
			var code = "#"+parent_code;
			//var amount = $(code).val();
			var project_amount = $(this).attr('value');
                        var amount_array = project_amount.split(',');
                        var amount = '';
                        for (var i = 0; i < amount_array.length; i++) {
                                amount = amount+amount_array[i];
                        }

        	        amount = jsFloatOps(amount, proj_amount, '+');
                	//set 'amount' where id = parent_code;
			$(code).val(amount);

	                updateParentProjection(parent_code, proj_amount);
        	}
	};

      $('.projection').trigger('change');
});
</script>

<?php
	setlocale(LC_MONETARY, 'en_IN');

        $counter = 0;
        echo form_open('projection/reappro');
        echo "<table  border=0 cellpadding=6 class=\"simple-table account-table\">";
        echo "<thead><tr><th>Projection Code </th><th>Projection Name </th><th>Allocated Projection</th></tr></thead>";
        if (count($projection) > 0)
        {
                foreach ($projection as $id => $data)
                      {
                                echo "<tr class=\"tr-ledger\">";
                                if($data['code'] < 10000)
                                {
                                echo "<td>";
                                        echo "<strong>";
                                        echo $data['code'];
                                        echo "</strong>";
                                echo "</td>";
                                $name = 'projection_value_' .$data['id'];
                                echo "<td class=\"td-ledger\">";
                                        echo "<strong>";
                                        echo form_label($data['name'], $$name);
                                        echo "</strong>";
                                echo "</td>";
                                }
                                else
                                {
                                echo "<td>";
                                        echo $data['code'];
                                echo "</td>";
                                $name = 'projection_value_' .$data['id'];
                                echo "<td class=\"td-ledger\">";
                                        echo form_label($data['name'], $$name);
                                echo "</td>";
                                }
                                echo "<td class=\"td-ledger\">";
                                        $data2 = array(
                                                'name'        => $name,
                                                'id'          => $data['code'],
                                                'value'       => money_format('%!i', $data['bd_balance']),
                                                'maxlength'   => '100',
                                                'size'        => '20',
                                                'class'       => 'projection',
                                                'style'       => 'width:50%',
                                        );

                                        echo form_input($data2);
                                echo "</td>";
                                $counter++;
                                echo "</tr>";
                                $count = 0;
                        }
        }

        echo "</table>";
        echo "<p>";
        echo form_submit('submit', 'Submit');
        echo " ";
        echo anchor('projectionl', 'Back', 'Back to Projection');
        echo "</p>";
        echo form_close();
?>


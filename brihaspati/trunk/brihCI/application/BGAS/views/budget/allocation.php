<script type="text/javascript">
$(document).ready(function() {
        /* Show and Hide allowed_over */

	/*$("td.target").click(function() {
		alert("Handler for .click() called.");
	});

	$("td.target").trigger('click');*/
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
	}
//	$('#subBudget').each(function() {
	$('#subBudget').change(function() {
	//$('#sub_budget_64').change(function() {
		 var $tempo = "tr." + $(this).val();
		if (($(this).is(':checked'))){
                        alert($tempo);
			//var $tempo = $(this).val();
			$($tempo).show();
                        //$('.budget-expense').show();
                }
                else
                {
                        //alert("yipee");
                        //$('.budget-expense').hide();
			//$("tr.target").hide();
			$($tempo).hide();
                }
	});
//	});
  //      $('#subBudget').trigger('each');
	$('#subBudget').trigger('change');
	//$('#sub_budget_64').trigger('change');
	
	/* Calculating budget total */
        $('.item').live('change', function() {
                var Total = 0;
                $("table tr .item").each(function() {
                        var amt = $(this).attr('value');
                        amt = parseFloat(amt);
                        if (isNaN(amt))
                                amt = 0;
                        Total = jsFloatOps(Total, amt, '+');
                });
                $("#total").text(Total);
                /*var crTotal = 0;
                $("table tr .cr-item").each(function() {
                        var curCr = $(this).attr('value');
                        curCr = parseFloat(curCr);
                        if (isNaN(curCr))
                                curCr = 0;
                        crTotal = jsFloatOps(crTotal, curCr, '+');
                });
                $("table tr #cr-total").text(crTotal);*/
	});
	$('.item').trigger('change');
});
</script>
<?php
	$counter = 0;
	$id = 0;
	$ccount = 0;
	echo form_open('budget/allocation');
        echo "<table>";
        echo "<thead><tr><th></th><th>Budget Heads </th><th>Values</th></tr></thead>";
	if (count($budget) > 0)
        {
                 //foreach ($budget as $data)
		//$this->counter++;
		foreach ($budget as $id => $data)
                      {
                                //"$this->counter++ ";
                                echo "<tr class=\"tr-ledger\">";

                                echo "<td>";
                                echo "<span id=\"tooltip-target-1\">";
                                //echo form_checkbox('sub_budget'. "_" .$data['id'], 1, FALSE) . "";
				$check_name = 'sub_budget'. "_" .$data['id'];
				$data1 = array(
                                        'name'        => 'sub_budget'. "_" .$data['id'],
                                        'id'          => 'subBudget',
					//'id'          => 'sub_budget'. "_" .$data['id'],
                                        'value'       => 'sub_budget'. "_" .$data['id'], 
                                        'checked'     => $$check_name,
                                        //'style'       => 'margin:10px',
                                );
				//echo form_checkbox('sub_budget'. "_" .$data['id'], 1, $$check_name) . "";
				echo form_checkbox($data1). "";
                                echo "</span>";
                                echo "<span id=\"tooltip-content-1\">Select to display list of child budgets in this budget.</span>";

                                echo "</td>";
                                echo "<td class=\"td-ledger\">";
                                        echo $data['name'];
				echo "</td>";
                                echo "<td class=\"td-ledger\">";
				//$name = "name" . $counter;
				//foreach ($name as $id => $row)
				//{
					$name = 'name' . $counter;
				//	if ($data['id'] == $row['id'])
					//	echo form_input($$name);
					$data2 = array(
              					'name'        => $name,
              					'id'          => $name,
              					//'value'       => isset($name) ? $name : "",
						'value'	      => '',
              					'maxlength'   => '100',
              					'size'        => '40',
						'class'	      => 'item',
              					//'style'       => 'width:50%',
            				);

					echo form_input($data2);

				//}
				//echo form_input($budget_value. "_" .$data['id']);
                                echo "</td>";
				$counter++;
                                //echo "<td class=\"td-group\">";
                                  //      echo $this->print_space($this->counter);
                                    //    echo form_input('budget_distribution', '');
                                echo "</tr>";
				$count = 0;
                     			
			                foreach ($child_budget as $name => $child)
                                        {
						if ($child['parent_budget'] == $data['name'])
						{
							//$pname = 'parent_name' . $count;
                                                        //if ($$pname == $data['name'])
                                                        //{

	                                     			//echo "<tr class=\"child-budget\" . $data['id']>";
								$temp = 'sub_budget'. "_" .$data['id'];
								echo "<tr class=$temp bgcolor=\"#FFFACD\">";
								//echo $temp;
								//echo "<tr>";
									echo "<td>";
									echo "</td>";
                        	                	
									echo "<td>";
								//if ($child['parent_budget'] == $data['name'])	
										echo $child['name'];
                                        				echo "</td>";
						
									//echo "<td id=$data['id']>";
									echo "<td>";
										$ccname = 'child' . $data['id'] . $count;
										//$js = 'onClick="sum($data['id']);"';
										echo form_input($$ccname);
                                                			echo "</td>";
                                				echo "</tr>";
							//}		
							$count++;
						}//if
                                        }//for

                        }//for
                        //$this->counter--;
       }//if				

        echo "<tr align=\"right\">";
	echo "<td colspan=2>";
        echo form_label('Total', '');
        echo "&nbsp;";
        echo "</td>";
	echo "<td id=\"total\">";
        echo form_input('');
        echo "</td>";
	echo "</tr>";
	echo "</table>";
	echo "<p>";
        echo form_submit('submit', 'Submit');
        echo " ";
        echo anchor('budgetl', 'Back', 'Back to Budget Heads');
        echo "</p>";
	echo form_close();
?>

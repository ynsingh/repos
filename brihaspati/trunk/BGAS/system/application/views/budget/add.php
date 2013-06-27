<script type="text/javascript">
$(document).ready(function() {
	/* Show and Hide allowed_over */
	/*$('.group-parent').change(function() {
		if ($(this).val() == "3" || $(this).val() == "4") {
			$('.affects-gross').show();
		} else {
			$('.affects-gross').hide();
		}
	});
	$('.group-parent').trigger('change');*/
	/*$('.budget-over').click(function() {
		alert($(this).val());
		if ($(this).val() == "1") {
			$('.budget-expense').hide();
		}
		//if ($(this).val() == "0")
		else
		{
			$('.budget-expense').show();
		}
	});
	$('.budget-over').trigger('click');*/
	$('#budget').change(function() {
    		//this.checked ? ($('.budget-expense').show()) : ($('.budget-expense').hide());
		/*var c = $(this).checked ? '#f00' : '#09f';
		//alert(c);
		if (c == "#f00"){
			$('.budget-expense').show();
                } else {
                        $('.budget-expense').hide();
                }*/		
		//if$('.budget-over').prop('checked', true){
		if (($(this).is(':checked'))){
  			//alert("checked=true");
			$('.budget-expense').show();
		}
		else
		{
			//alert("yipee");
			$('.budget-expense').hide();
		}
		//$("#gift-true").val(($(this).is(':checked')) ? "yes" : "no");
		
		//alert((($(this).is(':checked')) ? "yes" : "no"));
	});
	$('#budget').trigger('change');
});
</script>
<?php
	echo form_open('budget/add');


/*	echo "<p>";
	echo form_label('Budget code', 'budget_code');
	echo "<br />";
	echo form_input($budget_code);
	echo "</p>";

	echo "<p>";
	echo form_label('Budget name', 'budget_name');
	echo "<br />";
	echo form_input($budget_name);
	echo "</p>";
*/
	echo "<p>";
        echo form_label('Budget name', 'group_expenses');
        echo "<br />";
        echo form_dropdown('group_expenses', $group_expenses, $group_expenses_active, "class = \"group-parent\"");
        echo "</p>";

	echo "<p>";
	echo form_label('Budget Amount', 'budget_amount');
	echo "<br />";
	echo form_input($budget_amount);
	echo "</p>";

	echo "<p>";
        echo form_label('Budget type', 'budget_type');
        echo "<br />";
        echo form_input($budget_type);
        echo "</p>";

	/*echo "<p class=\"affects-gross\">";
	echo "<span id=\"tooltip-target-1\">";
	echo form_checkbox('affects_gross', 1, $affects_gross) . " Affects Gross Profit/Loss Calculations";
	echo "</span>";
	echo "<span id=\"tooltip-content-1\">If selected the Group account will affect Gross Profit and Loss calculations, otherwise it will affect only Net Profit and Loss calculations.</span>";
	echo "</p>";
	*/
	
	/*echo "<p class=\"budget-over\">";
        echo "<span id=\"tooltip-target-1\">";
        echo form_checkbox('budget_over', 1, $budget_over) . " Allow over expenses";
        echo "</span>";
        echo "<span id=\"tooltip-content-1\">Select if expenses can exceed the budget.</span>";
        echo "</p>";*/
	$data = array(
    	'name'        => 'budget_over',
    	'id'          => 'budget',
    	'value'       => '1',
    	'checked'     => $budget_over,
    	//'style'       => 'margin:10px',
    	);
	echo "<p>";
	echo "<span id=\"tooltip-target-2\">";
	echo form_checkbox($data) . " Exceed expenses over budget";
	echo "</span>";
        echo "<span id=\"tooltip-content-2\">Select if expenses can exceed the budget.</span>";
	echo "</p>";

	echo "<p class=\"budget-expense\">";
	echo "<span id=\"tooltip-target-3\">";
        echo form_label('Exceed Expenses', 'budget_over_expense');
        echo "<br />";
        echo form_input($budget_over_expense);
	echo "</span>";
        echo "<span id=\"tooltip-content-3\">The value entered will be the main limit, otherwise limit will be amount of budget allocated. Also the amount can be in precentage or fixed/static.</span>";
        echo "</p>";
	
	echo "<p>";
	echo "<p>";
	echo form_submit('submit', 'Create');
	echo " ";
	echo anchor('budgetl', 'Back', 'Back to Budget Heads');
	echo "</p>";

	echo form_close();
?>

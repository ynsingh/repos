<script type="text/javascript">
$(document).ready(function() {
	/* Show and Hide affects_gross */
	$('.group-parent').change(function() {
		if ($(this).val() == "3" || $(this).val() == "4") {
			$('.affects-gross').show();
		} else {
			$('.affects-gross').hide();
		}
	});
	$('.group-parent').trigger('change');
});
</script>
<?php
	echo form_open('budget/edit/' . $budget_id);

/*	echo "<p>";
	echo form_label('Budget Code', 'budget_code');
	echo "<br />";
	echo form_input($budget_code);
	echo "</p>";
*/
	echo form_hidden('budget_code', $budget_code);
	
	echo "<p>";
	echo form_label('Budget Name', 'budget_name');
	echo "<br />";
	echo form_input($budget_name);
	echo "</p>";

/*	echo "<p>";
        echo form_label('Budget Type', 'budget_type');
        echo "<br />";
        echo form_input($budget_type);
        echo "</p>";
*/
	/*echo "<p>";
	echo form_label('Budget Parent', 'group_parent');
	echo "<br />";
	echo form_dropdown('group_parent', $group_parent, $group_parent_active, "class = \"group-parent\"");
	echo "</p>";

	echo "<p class=\"affects-gross\">";
	echo "<span id=\"tooltip-target-1\">";
	echo form_checkbox('affects_gross', 1, $affects_gross) . " Affects Gross Profit/Loss Calculations";
	echo "</span>";
	echo "<span id=\"tooltip-content-1\">If selected the Group account will affect Gross Profit and Loss calculations, otherwise it will affect only Net Profit and Loss calculations.</span>";
	echo "</p>";
	*/
	
/*	echo "<p>";
        echo form_label('B/D Balance', 'budget_bd');
        echo "<br />";
        echo form_input($budget_bd);
        echo "</p>";

	echo "<p>";
        echo form_label('O/P Balance', 'budget_op');
        echo "<br />";
        echo form_input($budget_op);
        echo "</p>";
*/
	echo "<p>";
        echo form_label('Over Expense Allowed', 'budget_over');
        echo "<br />";
        echo form_input($budget_over);
        echo "</p>";

	echo "<p>";
	echo form_hidden('budget_id', $budget_id);
	echo form_submit('submit', 'Update');
	echo " ";
	echo anchor('budgetl', 'Back', 'Back to Budget Heads');
	echo "</p>";

	echo form_close();
?>

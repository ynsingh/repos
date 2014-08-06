<?php
	echo form_open('budget/edit/' . $budget_id);

	echo "<p>";
	echo form_label('Budget Code', 'budget_code');
	echo "<br />";
	echo form_input($budget_code);
	echo "</p>";

	
	echo "<p>";
	echo form_label('Budget Name', 'budget_name');
	echo "<br />";
	echo form_input($budget_name);
	echo "</p>";

	echo "<p>";
        echo form_label('Budget type', 'budget_type');
        echo "<br />";
        echo form_input($budget_type);
        echo "</p>";


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

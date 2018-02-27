<?php
	echo form_open('projection/add');

        echo "<p>";
        echo form_label('Projection Name', 'group_incomes');
        echo "<br />";
        echo form_dropdown('group_incomes', $group_incomes, $group_incomes_active);
        echo "</p>";

        echo "<p>";
        echo form_label('Projection Amount', 'projection_amount');
        echo "<br />";
        echo form_input($projection_amount);
        echo "</p>";

        echo "<p>";
        echo form_label('Projection type', 'projection_type');
        echo "<br />";
        echo form_input($projection_type);
        echo "</p>";

        echo "<p>";
        echo "<p>";
        echo form_submit('submit', 'Create');
        echo " ";
        echo anchor('budgetl', 'Back', 'Back to Budget Heads');
        echo "</p>";

        echo form_close();
?>


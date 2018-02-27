<?php
        echo form_open('payrollsetup/add');
	echo "<p>";
        echo form_label('Main Budget Code', 'main_budgetcode');
        echo "<br />";
        echo form_input($main_budgetcode);
        echo "</p>";

        echo "<p>";
        echo form_label('Salary Budget Code', 'salary_budgetcode');
        echo "<br />";
        echo " ";
	 echo form_dropdown('salary_budgetcode', $salary_budgetcode);
        echo "</p>";


	echo "<p>";
        echo form_label('Cash/Bank Account Code', 'cash_bankcode');
        echo "<br />";
	echo form_dropdown('cash_bankcode', $cash_bankcode);
        echo "</p>";


        echo "<p>";
        echo form_submit('submit', 'Save');
	echo " ";
        echo anchor('payrollsetup', 'Back', 'payrollsetup/index');
        echo "</p>";

        echo form_close();


?>


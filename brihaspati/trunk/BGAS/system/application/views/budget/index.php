<?php
	$this->load->library('budgetlist');
	echo "<table>";
	echo "<tr valign=\"top\">";
	$asset = new Budgetlist();
	echo "<td>";
	$asset->init(0);
	echo "<table border=0 cellpadding=6 class=\"simple-table account-table\">";
	//echo "<thead><tr><th>Budget Code </th><th>Budget Name</th><th>Budget Type</th><th>O/P Balance</th><th>C/L Balance</th><th>Over Expence Allowed</th> <th></th></tr></thead>";
	echo "<thead><tr><th>Budget Code </th><th>Budget Name</th><th>Budget Type</th><th>B/D Balance</th><th>Over Expence Allowed</th> <th></th></tr></thead>";
	$asset->budget_st_main(-1);
	echo "</table>";
	echo "</td>";
	echo "</tr>";
	echo "</table>";


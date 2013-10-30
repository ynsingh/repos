<?php
        $this->load->library('budgetlist');
        echo "<table>";
        echo "<tr valign=\"top\">";
        $asset = new Budgetlist();
        echo "<td>";
        $asset->init(1);
        echo "<table border=0 cellpadding=6 class=\"simple-table account-table\">";
        echo "<thead><tr><th>Projection Code </th><th>Projection Name</th><th>Projection Type</th><th>B/D Balance</th><th>Unearned Projection</th><th></th></tr></thead>";
        $asset->budget_st_main(-1, 'projection');
        echo "</table>";
        echo "</td>";
        echo "</tr>";
        echo "</table>";
?>

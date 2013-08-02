
<?php
        $counter = 0;
        $id = 0;
        $ccount = 0;
	echo form_open('budget/reappro');
        echo "<table>";
        echo "<thead><tr><th>Budget Code </th><th>Budget Heads </th><th>Values</th></tr></thead>";
        if (count($budget) > 0)
        {
                foreach ($budget as $id => $data)
                      {
                                echo "<tr class=\"tr-ledger\">";
				if($data['code'] < 10000)
                                {
                                echo "<td>";
                                        echo "<strong>";
                                        echo $data['code'];
                                        echo "</strong>";
                                echo "</td>";
                                $name = 'budget_value'. "_" .$data['id'];
                                echo "<td class=\"td-ledger\">";
                                        echo "<strong>";
                                        echo form_label($data['budgetname'], $$name);
                                        echo "</strong>";
                                echo "</td>";
				}
				else
				{
				echo "<td>";
                                        echo $data['code'];
                                echo "</td>";
                                $name = 'budget_value'. "_" .$data['id'];
                                echo "<td class=\"td-ledger\">";
                                        echo form_label($data['budgetname'], $$name);
                                echo "</td>";
				}
                                echo "<td class=\"td-ledger\">";
					$data2 = array(
                                                'name'        => $name,
                                                'id'          => $data['code'],
                                                'value'       => $data['bd_balance'],
                                                'maxlength'   => '100',
                                                'size'        => '40',
                                                'class'       => 'budget',
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
        echo anchor('budgetl', 'Back', 'Back to Budget Heads');
        echo "</p>";
        echo form_close();
?>



<?php
	setlocale(LC_MONETARY, 'en_IN');

        $counter = 0;
        $id = 0;
        $ccount = 0;
	echo form_open('budget/reappro');
        echo "<table  border=0 cellpadding=6 class=\"simple-table account-table\">";
        //echo "<thead><tr><th>Budget Code </th><th>Budget Heads </th><th>Values</th></tr></thead>";
	echo "<thead><tr><th>Budget Code </th><th>Budget Heads </th><th>Allocated Budget</th><th>Unallocated Budget</th></tr></thead>";
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
				$bud_name = $name[0];
                                echo "<td class=\"td-ledger\">";
                                        echo "<strong>";
  //                                      echo form_label($data['budgetname'], $$name);
					echo form_label($data['budgetname'], $bud_name);
                                        echo "</strong>";
                                echo "</td>";
				}
				else
				{
				echo "<td>";
                                        echo $data['code'];
                                echo "</td>";
                                $name = 'budget_value'. "_" .$data['id'];
				$bud_name = $name[0];
                                echo "<td class=\"td-ledger\">";
//                                        echo form_label($data['budgetname'], $$name);
					echo form_label($data['budgetname'], $bud_name);
                                echo "</td>";
				}
                                echo "<td class=\"td-ledger\">";
					$data2 = array(
                                                'name'        => $name,
                                                'id'          => $data['code'],
                                                'value'       => money_format('%!i', $data['bd_balance']),
                                                'maxlength'   => '100',
                                                'size'        => '20',
                                                'class'       => 'budget',
						'style'       => 'width:50%',
                                        );

                                        echo form_input($data2);
                                echo "</td>";
                                $counter++;
				echo "<td>";
					$name = 'unallocated_value_' . $data['code'];
					/*$data3 = array(
                                                'name'        => $name,
                                                'id'          => $data['code'],
                                                'value'       => $name,
                                                'maxlength'   => '100',
                                                'size'        => '20',
                                                'class'       => 'budget',
                                                'style'       => 'width:50%',
                                        );*/

                                        echo $$name;
				echo "</td>";
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


<?php
        $counter = 0;
        $id = 0;
        $ccount = 0;
        echo form_open('projection/reappro');
        echo "<table  border=0 cellpadding=6 class=\"simple-table account-table\">";
        echo "<thead><tr><th>Projection Code </th><th>Projection Name </th><th>Allocated Projection</th><th>Unallocated Projection</th></tr></thead>";
        if (count($projection) > 0)
        {
                foreach ($projection as $id => $data)
                      {
                                echo "<tr class=\"tr-ledger\">";
                                if($data['code'] < 10000)
                                {
                                echo "<td>";
                                        echo "<strong>";
                                        echo $data['code'];
                                        echo "</strong>";
                                echo "</td>";
                                $name = 'projection_value_' .$data['id'];
                                echo "<td class=\"td-ledger\">";
                                        echo "<strong>";
                                        echo form_label($data['name'], $$name);
                                        echo "</strong>";
                                echo "</td>";
                                }
                                else
                                {
                                echo "<td>";
                                        echo $data['code'];
                                echo "</td>";
                                $name = 'projection_value_' .$data['id'];
                                echo "<td class=\"td-ledger\">";
                                        echo form_label($data['name'], $$name);
                                echo "</td>";
                                }
                                echo "<td class=\"td-ledger\">";
                                        $data2 = array(
                                                'name'        => $name,
                                                'id'          => $data['code'],
                                                'value'       => $data['bd_balance'],
                                                'maxlength'   => '100',
                                                'size'        => '20',
                                                'class'       => 'projection',
                                                'style'       => 'width:50%',
                                        );

                                        echo form_input($data2);
                                echo "</td>";
                                $counter++;
                                echo "<td>";
                                        $name = 'unallocated_value_' . $data['code'];
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
        echo anchor('projectionl', 'Back', 'Back to Projection');
        echo "</p>";
        echo form_close();
?>


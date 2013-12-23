<?php
 echo form_open('report/update' );
	
	
        echo "<table  border=0 cellpadding=6 class=\"simple-table account-table\">";
        
        echo "<thead><tr><th>Asset Code </th><th>Asset Name  </th><th>Percentage</th></tr></thead>";
	
	foreach ($value->result() as $data)
        {
                  echo "<tr>";
		  echo "<td>" . $data->code . "</td>";
		  echo "<td>" .  $data->name . "</td>";
		  $name = 'dep_value'. "_" . $data->id;
                  $code=$data->code;
		  
			
			 echo "<td>";
				
 			        echo form_label('', $$name);
        			
				$data2 = array(
                                                'name'        => $name,
                                                'id'          => $data->id,
                                                'value'       => $data->percentage,
                                                'maxlength'   => '100',
                                                'size'        => '20',
                                                'class'       => 'budget',
                                                'style'       => 'width:50%',
                                        );

                                        echo form_input($data2);
        			
                         echo "</td>";
                  echo "</tr>";
		
        }

	echo "</table>";
   	echo "<p>";
        echo form_submit('submit', 'Submit');
        echo " ";
        echo anchor('budgetl', 'Back', 'Back to Budget Heads');
        echo "</p>";
 echo form_close();
?>

<?php
 echo form_open('report/update' );
	
	
        echo "<table  border=0 cellpadding=6 class=\"simple-table account-table\">";
        
        echo "<thead><tr><th>Asset Code </th><th>Asset Name  </th><th>Percentage</th></tr></thead>";
	if($counter != 0){
		for($i = 0;$i<=$counter;$i++){
			$key = 'value_'.$i;
			echo "<tr>";
			foreach ($$key->result() as $data)
		        {
				$name = 'dep_value'. "_" . $data->ERPMIM_ID;
			}
		
			 echo "<td>" . $this->depreciation[$name]['code'] . "</td>";
			 echo "<td>" .  $data->ERPMIM_Item_Brief_Desc . "</td>";
			 echo "<td>";
                                
                                echo form_label('', $$name);
                                
                                $data2 = array(
                                                'name'        => $name,
                                                'id'          => $data->ERPMIM_ID,
                                                'value'       => $data->ERPMIM_Depreciation_Percentage,
                                                'maxlength'   => '100',
                                                'size'        => '20',
                                                'class'       => 'budget',
                                                'style'       => 'width:50%',
                                        );

                                echo form_input($data2);
                                echo "</td>";
			echo "</tr>";
		}
	}
		
	echo "</table>";
   	echo "<p>";
        echo form_submit('submit', 'Submit');
        echo " ";
        echo anchor('report/depreciation', 'Back', 'Back to the Depreciation');
        echo "</p>";
 echo form_close();
?>

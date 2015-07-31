<?php
 echo form_open('report/update' );
	
	
        echo "<table  border=0 cellpadding=4 class=\"simple-table account-table\" width=\"70%\">";
        
        echo "<thead><tr><th>Asset Name  </th><th>Percentage in (%)</th><th>Life Time (Years)</th></tr></thead>";
		  echo "<tr class=\"tr-balance\"><td colspan=\"4\"><b>Tangible Assets</b></td></tr>";
/*	$this->db->from('depreciation_master')->where('parent_id', 1);
                $depreciation_master=$this->db->get();
		foreach ($depreciation_master->result() as $row)
                {
                                $options = $row->percentage.'#'.$row->life_time;
				
				 echo "<tr>";
					echo "<td>" . $row->name . "</td>";
                                        echo "<td>".$row->percentage ."</td>";
                                        echo "<td>" . $row->life_time . "</td>";
				echo "</tr>";
                }
		echo "<tr class=\"tr-balance\"><td colspan=\"4\"><b>Intangible Assets</b></td></tr>";
                $this->db->from('depreciation_master')->where('parent_id', 2);
                $depreciation_master=$this->db->get();
                foreach ($depreciation_master->result() as $row)
                {
                                $options = $row->percentage.'#'.$row->life_time;

                                 echo "<tr>";
                                        echo "<td>" . $row->name . "</td>";
                                        echo "<td>".$row->percentage ."</td>";
                                        echo "<td>" . $row->life_time . "</td>";
                                echo "</tr>";
                }

*/


/*	if($counter != 0){
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
	}*/
	if (count($dep) > 0)
        {
                foreach ($dep as $id => $data)
                      {
			 echo "<tr>";
                                        echo "<td>" . $data['name'] . "</td>";
                                        echo "<td>";
					$name = 'budget_value'. "_" .$data['id'];
                                	$bud_name = $name[0];
                                	$data2 = array(
                                                'name'        => $name,
                                                'id'          => $data['id'],
                                                'value'       => $data['percentage'],
                                                'maxlength'   => '100',
                                                'size'        => '20',
                                        );

                                echo form_input($data2);
                                echo "</td>";
				 echo "<td>";
                                        $name1 = 'budget_value1'. "_" .$data['id'];
                                        $bud_name1 = $name1[0];
                                        $data1 = array(
                                                'name'        => $name1,
                                                'id'          => $data['id'],
                                                'value'       => $data['life_time'],
                                                'maxlength'   => '100',
                                                'size'        => '20',
                                        );

                                echo form_input($data1);
                                echo "</td>";


                        echo "</tr>";

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

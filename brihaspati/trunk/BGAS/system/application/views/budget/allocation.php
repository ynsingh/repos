<script type="text/javascript">
$(document).ready(function() {
        /* Show and Hide allowed_over */
        //$('.checkbox-c').change(function() {
                if ($checkbox-c == "0") {
                        $('.child-budget').hide();
                } else {
                        $('.child-budget').show();
                }
        //});
        //$('.checkbox-c').trigger('change');
});
</script>
<?php
     //  $this->load->library('budgetlist');
	$counter = 0;
	$id = 0;
	$ccount = 0;
	echo form_open('budget/allocation');
        echo "<table>";
       // echo "<tr valign=\"top\">";
       // $asset = new Budgetlist();
       // echo "<td>";
        //$asset->init(0);
    //    echo "<table border=0 cellpadding=6 class=\"simple-table account-table\">";
        echo "<thead><tr><th></th><th>Budget Heads </th><th>Values</th></tr></thead>";
       // $asset->budget_allocation();
      //  echo "</table>";
       // echo "</td>";
       // echo "</tr>";
	
	//print_r($budget);
	//if (count($this->budget) > 0)
          //      {
  //                      $this->counter;
	if (count($budget) > 0)
        {
                 //foreach ($budget as $data)
		//$this->counter++;
		foreach ($budget as $id => $data)
                      {
                                //"$this->counter++ ";
                                echo "<tr class=\"tr-ledger\">";

                                echo "<td class=\"checkbox-c\">";
                                echo "<span id=\"tooltip-target-1\">";
                                echo form_checkbox('sub_budget'. "_" .$data['id'], 1, FALSE) . "";
				//echo form_checkbox('sub_budget'. "_" .$data['id'], 1, $sub_budget. "_" .$data['id']) . "";
                                echo "</span>";
                                echo "<span id=\"tooltip-content-1\">Select to display list of child budgets in this budget.</span>";

                                echo "</td>";
                                echo "<td class=\"td-ledger\">";
                                        echo $data['name'];
				echo "</td>";
				/*echo "<td>";
				//echo "<ul>";
					//echo "<li>";
					foreach ($child_budget as $name => $child)
					{
						if ($child['parent_budget'] == $data['name'])
							echo $child['name'];
					//	echo "&nbsp;";
					}
                                //	echo "</li>";
				//echo "</ul>";
				echo "</td>";*/
                                echo "<td class=\"td-ledger\">";
				//$name = "name" . $counter;
				//foreach ($name as $id => $row)
				//{
					$name = 'name' . $counter;
				//	if ($data['id'] == $row['id'])
						echo form_input($$name);
						//echo form_input($name);
				//}
				//echo form_input($budget_value. "_" .$data['id']);
                                echo "</td>";
				$counter++;
                                //echo "<td class=\"td-group\">";
                                  //      echo $this->print_space($this->counter);
                                    //    echo form_input('budget_distribution', '');
                                echo "</tr>";
				//$ccount = 0;
                                        foreach ($child_budget as $name => $child)
                                        {
						if ($child['parent_budget'] == $data['name'])
						{
							//$pname = 'parent_name' . $count;
                                                        //if ($$pname == $data['name'])
                                                        //{

	                                     			echo "<tr class=\"child-budget\">";
								echo "<td>";
								echo "</td>";
                        	                	
								echo "<td>";
								//if ($child['parent_budget'] == $data['name'])	
									echo $child['name'];
                                        			echo "</td>";
						
								echo "<td>";
								//echo form_input($budget_value. "_" .$child['id']);
								//$count = 0;
								//for(; $ccount < $child_counter; $ccount++)
								if($ccount < $child_counter)
								{
									$pname = 'parent_name' . $ccount;	
									if ($$pname == $data['name'])
									{
										$cname = 'child_name' . $ccount;
										echo form_input($$cname);
										$ccount++;
										//break;
									}
								}
                                                		echo "</td>";
                                				echo "</tr>";
							//}		
				//			$count++;
						}//if
                                        }//for

                        }//for
                        //$this->counter--;
       }//if				
					/*echo "<tr>";
					foreach ($child_budget as $child)
                                        {
                                                //if ($child['parent_budget'] == $data['name'])
							
                                                      echo $child['parent_budget'];
                                        }
					echo "</tr>";*/

		/*foreach ($budget as $key => $value)
		{
			echo "<tr class=\"tr-ledger\">";
                        echo "<td class=\"td-ledger\">";
			echo "<td class=\"td-ledger\">";
                        echo "&nbsp;" .  $value;
                        echo "</td>";
		}
	*/
	/*if ($counter > 0)
        {
	        //$this->counter++;
                foreach ($budget as $id => $data)
                {
			echo "<tr class=\"tr-ledger\">";
                        echo "<td class=\"td-ledger\">";
                        echo "<span id=\"tooltip-target-1\">";
                        echo form_checkbox('sub_budget' . "#" . $this->counter, 1, $sub_budget . "#" . $this->counter) . "";
                        echo "</span>";
                        echo "<span id=\"tooltip-content-1\">Select to display list of sub budgets in this budget.</span>";

                        echo "<td class=\"td-ledger\">";
                        echo "&nbsp;" .  $data['name'];
                        echo "</td>";

                        echo "<td class=\"td-group\">";
                        echo $this->print_space($this->counter);
                        echo form_input($budget_distribution . "#" . $this->counter, '');
                       //echo "&nbsp;" .  $data['bd_balance'];
                        echo "</td>";

                        echo "</tr>";
                }//for
                $this->counter--;
	}//if
	*/

        echo "<tr align=\"right\">";
	echo "<td colspan=2>";
        echo form_label('Total', '');
        echo "&nbsp;";
        echo "</td>";
	echo "<td>";
        echo form_input('');
        echo "</td>";
	echo "</tr>";
	echo "</table>";
	echo "<p>";
        echo form_submit('submit', 'Submit');
        echo " ";
        echo anchor('budgetl', 'Back', 'Back to Budget Heads');
        echo "</p>";
	echo form_close();
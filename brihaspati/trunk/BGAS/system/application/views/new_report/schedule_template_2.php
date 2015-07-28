<?php
/*	setlocale(LC_MONETARY, 'en_IN');

	$i = 0;
	$total_ab = 0;
	$total_new_sum = 0;
	$total_old_sum = 0;	

	//if(count($designated_earmarked_funds) > 0){
	        foreach($designated_earmarked_funds_group as $name => $data){
			$temp = "add".$data['name'];
        		$$temp = 0;
			$temp = "uti".$data['name'];
                        $$temp = 0;
                }

		foreach($designated_earmarked_funds_ledger as $name => $data){
                        $temp = "add".$data['name'];
                        $$temp = 0;
                        $temp = "uti".$data['name'];
                        $$temp = 0;
                }
        //}

	$this->load->library('balancesheet');

	if($id != '')
                $i = $id;

	$object = new Balancesheet();
//	$db = $object->getPreviousYearDetails();
	$count = count($designated_earmarked_funds_group) + count($designated_earmarked_funds_ledger);
	echo "<table border=0 class=\"simple-table balance-sheet-table\" >";
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"".$count."\">FUND WISE BREAKUP</th><th align=\"center\" colspan=\"2\">TOTAL</th></tr></thead>";

	echo "<tr>";
                echo "<td>";
                echo "</td>";
		//if(count($designated_earmarked_funds) > 0){
		
			foreach($designated_earmarked_funds_group as $name => $data){
                		echo "<td align=\"center\">";
					echo "<strong>" . $data['name'] . "</strong>";
		                echo "</td>";
			}

			foreach($designated_earmarked_funds_ledger as $name => $data){
                                echo "<td align=\"center\">";
                                        echo "<strong>" . $data['name'] . "</strong>";
                                echo "</td>";
                        }
		//}			
		
		echo "<td>"; 
			echo "<strong>CURRENT YEAR</strong>";           
                echo "</td>";

		echo "<td>";
                        echo "<strong>PREVIOUS YEAR</strong>";
                echo "</td>";
        
	echo "</tr>";

	//Fetch opening balance from db
	echo "<tr>";
		echo "<td>";
			echo "<strong> a) Opening balance of the funds </strong>";
        	echo "</td>";

		$new_op_balance = 0;
		$old_op_balance = 0;

		//if(count($designated_earmarked_funds) > 0){
			//$new_op_balance = 0;
			//$old_op_balance = 0;

                        foreach($designated_earmarked_funds_group as $name => $data){
				$object = new Balancesheet();
				$object->init($data['id']);

			//	$object->callToOpBalance('new');
			//	$opening_balance = $object->opening_balance;
				$opening_balance = $object->opbalance;
				$new_op_balance = $new_op_balance + $opening_balance;
				$temp = "add".$data['name'];
				$$temp = $$temp + $opening_balance;			
			//	$object->callToOpBalance('old');
			//	$old_op_balance = $old_op_balance + $object->opening_balance_prev;
				$old_op_balance = $old_op_balance + $object->opbalance_prev;
                                echo "<td align=\"right\">";
                                        echo money_format('%!i', convert_cur($opening_balance));
                                echo "</td>";
                        }

			foreach($designated_earmarked_funds_ledger as $name => $data){
                                $object = new Balancesheet();
                                $object->init_led($data['id']);

                        //        $object->callToOpBalance('new');
                          //      $opening_balance = $object->opening_balance;
				$opening_balance = $object->opbalance;
                                $new_op_balance = $new_op_balance + $opening_balance;
                                $temp = "add".$data['name'];
                                $$temp = $$temp + $opening_balance;
                       //         $object->callToOpBalance('old');
                          //      $old_op_balance = $old_op_balance + $object->opening_balance_prev;
				$old_op_balance = $old_op_balance + $object->opbalance_prev;
                                echo "<td align=\"right\">";
                                        echo money_format('%!i', convert_cur($opening_balance));
                                echo "</td>";
                        }
                //}
	
		$total_new_sum = $total_new_sum + $new_op_balance;
		$total_old_sum = $total_old_sum + $old_op_balance;

		echo "<td align=\"right\">";
                      echo money_format('%!i', convert_cur($new_op_balance));
                echo "</td>";

                echo "<td align=\"right\">";
                      echo money_format('%!i', convert_cur($old_op_balance));
                echo "</td>";

	echo "</tr>";

	echo "<tr>";
                echo "<td>";
                        echo "<strong> b) Additions to the Funds: </strong>";
                echo "</td>";
	echo "</tr>";

	echo "<tr>";
		echo "<td>";
			echo "&nbsp;&nbsp;<strong> i. Donation/grants</strong>";
		echo "</td>";

		$new_sum = 0;
		$old_sum = 0;
                //if(count($designated_earmarked_funds) > 0){
			//$new_sum = 0;
			//$old_sum = 0;
                        foreach($designated_earmarked_funds_group as $name => $data){
            			$new_donation = 0;
				$old_donation = 0;
                               	$object = new Balancesheet();        
				$object->init($data['id']);
				//$new_donation = $object->additionsToFunds($data['id'], 'donation');
				$new_donation = $object->additionToFundsDonation();
				$new_sum = $new_sum + $new_donation;
				$temp = "add".$data['name'];
				$$temp = $$temp + $new_donation;
//				$db = $object->getPreviousYearDetails();
				$old_donation = $object->additionToFundsDonationPrev();
				$old_sum = $old_sum + $old_donation;
				echo "<td align=\"right\">";
					echo money_format('%!i', convert_cur($new_donation));
                                echo "</td>";
			}

			foreach($designated_earmarked_funds_ledger as $name => $data){
                                $new_donation = 0;
                                $old_donation = 0;
                                $object = new Balancesheet();
                                $object->init_led($data['id']);
                                //$new_donation = $object->additionsToFunds($data['id'], 'donation');
                                $new_donation = $object->additionToFundsDonation();
                                $new_sum = $new_sum + $new_donation;
                                $temp = "add".$data['name'];
                                $$temp = $$temp + $new_donation;
//                              $db = $object->getPreviousYearDetails();
                                $old_donation = $object->additionToFundsDonationPrev();
                                $old_sum = $old_sum + $old_donation;
                                echo "<td align=\"right\">";
                                        echo money_format('%!i', convert_cur($new_donation));
                                echo "</td>";
                        }
		//}

		$total_new_sum = $total_new_sum + $new_sum;
		$total_old_sum = $total_old_sum + $old_sum;

		echo "<td align=\"right\">";
                      echo money_format('%!i', convert_cur($new_sum));
                echo "</td>";

                echo "<td align=\"right\">";
                      echo money_format('%!i', convert_cur($old_sum));
                echo "</td>";
        echo "</tr>";	

	echo "<tr>";
                echo "<td>";
                        echo "&nbsp;&nbsp;<strong> ii. Income from investments made of the funds</strong>";
                echo "</td>";

		$new_sum = 0;
		$old_sum = 0;
                //if(count($designated_earmarked_funds) > 0){
			//$new_sum = 0;
                        //$old_sum = 0;
                        foreach($designated_earmarked_funds_group as $name => $data){
				$new_donation = 0;
                                $old_donation = 0;
                                $object = new Balancesheet();
                                $object->init($data['id']);

                                //$new_donation = $object->additionsToFunds($data['id'], 'donation');
                                $new_donation = $object->additionsToFunds('Earn');
                                $new_sum = $new_sum + $new_donation;
				$temp = "add".$data['name'];
				$$temp = $$temp + $new_donation;

//				$db = $object->getPreviousYearDetails();
                                $old_donation = $object->additionsToFundsPrev('Earn');
                                $old_sum = $old_sum + $old_donation;
                                echo "<td align=\"right\">";
					echo money_format('%!i', convert_cur($new_donation));
                                echo "</td>";
                        }

			foreach($designated_earmarked_funds_ledger as $name => $data){
                                $new_donation = 0;
                                $old_donation = 0;
                                $object = new Balancesheet();
                                $object->init_led($data['id']);

                                //$new_donation = $object->additionsToFunds($data['id'], 'donation');
                                $new_donation = $object->additionsToFunds('Earn');
                                $new_sum = $new_sum + $new_donation;
                                $temp = "add".$data['name'];
                                $$temp = $$temp + $new_donation;

//                              $db = $object->getPreviousYearDetails();
                                $old_donation = $object->additionsToFundsPrev('Earn');
                                $old_sum = $old_sum + $old_donation;
                                echo "<td align=\"right\">";
                                        echo money_format('%!i', convert_cur($new_donation));
                                echo "</td>";
                        }
                //}

		$total_new_sum = $total_new_sum + $new_sum;
		$total_old_sum = $total_old_sum + $old_sum;

                echo "<td align=\"right\">";
                      echo money_format('%!i', convert_cur($new_sum));
                echo "</td>";

                echo "<td align=\"right\">";
                      echo money_format('%!i', convert_cur($old_sum));
                echo "</td>";
        echo "</tr>";

	echo "<tr>";
                echo "<td>";
                        echo "&nbsp;&nbsp;<strong> iii. Accrued interest on investments of the funds</strong>";
                echo "</td>";

		$new_sum = 0;
		$old_sum = 0;
                //if(count($designated_earmarked_funds) > 0){
			//$new_sum = 0;
                        //$old_sum = 0;
                        foreach($designated_earmarked_funds_group as $name => $data){
				$new_donation = 0;
                                $old_donation = 0;
                                $object = new Balancesheet();
                                $object->init($data['id']);

                                //$new_donation = $object->additionsToFunds($data['id'], 'donation');
                                $new_donation = $object->additionsToFunds('Accru');
                                $new_sum = $new_sum + $new_donation;
				$temp = "add".$data['name'];
				$$temp = $$temp + $new_donation;

//				$db = $object->getPreviousYearDetails();
                                $old_donation = $object->additionsToFundsPrev('Accru');
                                $old_sum = $old_sum + $old_donation;
                                echo "<td align=\"right\">";
                                        echo money_format('%!i', convert_cur($new_donation));
                                echo "</td>";
                        }

			foreach($designated_earmarked_funds_ledger as $name => $data){
                                $new_donation = 0;
                                $old_donation = 0;
                                $object = new Balancesheet();
                                $object->init_led($data['id']);

                                //$new_donation = $object->additionsToFunds($data['id'], 'donation');
                                $new_donation = $object->additionsToFunds('Accru');
                                $new_sum = $new_sum + $new_donation;
                                $temp = "add".$data['name'];
                                $$temp = $$temp + $new_donation;

//                              $db = $object->getPreviousYearDetails();
                                $old_donation = $object->additionsToFundsPrev('Accru');
                                $old_sum = $old_sum + $old_donation;
                                echo "<td align=\"right\">";
                                        echo money_format('%!i', convert_cur($new_donation));
                                echo "</td>";
                        }
                //}

		$total_new_sum = $total_new_sum + $new_sum;
                $total_old_sum = $total_old_sum + $old_sum;

                echo "<td align=\"right\">";
                      echo money_format('%!i', convert_cur($new_sum));
                echo "</td>";

                echo "<td align=\"right\">";
                      echo money_format('%!i', convert_cur($old_sum));
                echo "</td>";
        echo "</tr>";
	
	$net_total_new = $total_new_sum;
	$net_total_old = $total_old_sum;

	//Display total for the given schedule
	echo "<tr>";
                echo "<td class=\"bold\">";
                        echo "TOTAL (a+b)";
                echo "</td>";

		//if(count($designated_earmarked_funds) > 0){

                        foreach($designated_earmarked_funds_group as $name => $data){
                                echo "<td align=\"right\">";
					$temp = "add".$data['name'];
                                        echo "<strong>" .  money_format('%!i', convert_cur($$temp)) ."</strong>";
                                echo "</td>";
                        }

			foreach($designated_earmarked_funds_ledger as $name => $data){
                                echo "<td align=\"right\">";
                                        $temp = "add".$data['name'];
                                        echo "<strong>" .  money_format('%!i', convert_cur($$temp)) ."</strong>";
                                echo "</td>";
                        }
                //}

                echo "<td align=\"right\">";
			echo "<strong>" . money_format('%!i', convert_cur($total_new_sum)) ."</strong>";
                echo "</td>";
                echo "<td align=\"right\">";
			echo "<strong>" . money_format('%!i', convert_cur($total_old_sum)) ."</strong>";
                echo "</td>";

        echo "</tr>";
	
	echo "<tr>";
                echo "<td class=\"bold\">";
			echo "<strong>c) Utilisation/Expenditure towards objectives of funds</strong>";
		echo "</td>";
        echo "</tr>";

	 echo "<tr>";
                echo "<td>";
                        echo "&nbsp;&nbsp;<strong> i. Capital Expenditure</strong>";
                echo "</td>";
		
		$total_new_sum = 0;
		$total_old_sum = 0;
                $new_sum = 0;
                $old_sum = 0;
                //if(count($designated_earmarked_funds) > 0){
                        //$new_sum = 0;
                        //$old_sum = 0;
                        foreach($designated_earmarked_funds_group as $name => $data){
                                $new_donation = 0;
                                $old_donation = 0;
                                $object = new Balancesheet();
                                $object->init($data['id']);

                                //$new_donation = $object->additionsToFunds($data['id'], 'donation');
                                $new_donation = $object->additionsToFunds('Capital');
                                $new_sum = $new_sum + $new_donation;
				$temp = "uti".$data['name'];
				$$temp = $$temp + $new_donation;

//				$db = $object->getPreviousYearDetails();
                                $old_donation = $object->additionsToFundsPrev('Capital');
                                $old_sum = $old_sum + $old_donation;
                                echo "<td align=\"right\">";
                                        echo money_format('%!i', convert_cur($new_donation));
                                echo "</td>";
                        }

			foreach($designated_earmarked_funds_ledger as $name => $data){
                                $new_donation = 0;
                                $old_donation = 0;
                                $object = new Balancesheet();
                                $object->init_led($data['id']);

                                //$new_donation = $object->additionsToFunds($data['id'], 'donation');
                                $new_donation = $object->additionsToFunds('Capital');
                                $new_sum = $new_sum + $new_donation;
                                $temp = "uti".$data['name'];
                                $$temp = $$temp + $new_donation;

//                              $db = $object->getPreviousYearDetails();
                                $old_donation = $object->additionsToFundsPrev('Capital');
                                $old_sum = $old_sum + $old_donation;
                                echo "<td align=\"right\">";
                                        echo money_format('%!i', convert_cur($new_donation));
                                echo "</td>";
                        }
                //}

                $total_new_sum = $total_new_sum + $new_sum;
		$total_old_sum = $total_old_sum + $old_sum;
		
                echo "<td align=\"right\">";
                      echo money_format('%!i', convert_cur($new_sum));
                echo "</td>";

                echo "<td align=\"right\">";
                      echo money_format('%!i', convert_cur($old_sum));
                echo "</td>";
        echo "</tr>";

	echo "<tr>";
                echo "<td>";
                        echo "&nbsp;&nbsp;<strong> ii. Revenue Expenditure</strong>";
                echo "</td>";

                $new_sum = 0;
                $old_sum = 0;
                //if(count($designated_earmarked_funds) > 0){
                        //$new_sum = 0;
                        //$old_sum = 0;
                        foreach($designated_earmarked_funds_group as $name => $data){
                                $new_donation = 0;
                                $old_donation = 0;
                                $object = new Balancesheet();
                                $object->init($data['id']);

                                //$new_donation = $object->additionsToFunds($data['id'], 'donation');
                                $new_donation = $object->additionsToFunds('Revenue');
                                $new_sum = $new_sum + $new_donation;
				$temp = "uti".$data['name'];
                                $$temp = $$temp + $new_donation;
                
                                $old_donation = $object->additionsToFundsPrev('Revenue');
                                $old_sum = $old_sum + $old_donation;
                                echo "<td align=\"right\">";
                                        echo money_format('%!i', convert_cur($new_donation));
                                echo "</td>";
//                        }
//                }
                        }

			foreach($designated_earmarked_funds_ledger as $name => $data){
                                $new_donation = 0;
                                $old_donation = 0;
                                $object = new Balancesheet();
                                $object->init_led($data['id']);

                                //$new_donation = $object->additionsToFunds($data['id'], 'donation');
                                $new_donation = $object->additionsToFunds('Revenue');
                                $new_sum = $new_sum + $new_donation;
                                $temp = "uti".$data['name'];
                                $$temp = $$temp + $new_donation;

                                $old_donation = $object->additionsToFundsPrev('Revenue');
                                $old_sum = $old_sum + $old_donation;
                                echo "<td align=\"right\">";
                                        echo money_format('%!i', convert_cur($new_donation));
                                echo "</td>";
//                        }
//                }
                        }
                //}

                $total_new_sum = $total_new_sum + $new_sum;
		$total_old_sum = $total_old_sum + $old_sum;
		$net_total_new = $net_total_new - $total_new_sum;
		$net_total_old = $net_total_old - $total_old_sum;

                echo "<td align=\"right\">";
                      echo money_format('%!i', convert_cur($new_sum));
                echo "</td>";

                echo "<td align=\"right\">";
                      echo money_format('%!i', convert_cur($old_sum));
                echo "</td>";
        echo "</tr>";

	echo "<tr>";
                echo "<td class=\"bold\">";
			echo "<string> TOTAL (c) </strong>";
		echo "</td>";

                //if(count($designated_earmarked_funds) > 0){

                        foreach($designated_earmarked_funds_group as $name => $data){
                                echo "<td align=\"right\">";
					$temp = "uti".$data['name'];
                                        echo "<strong>" . money_format('%!i', convert_cur($$temp)) . "</strong>";
                                echo "</td>";
                        }

			foreach($designated_earmarked_funds_ledger as $name => $data){
                                echo "<td align=\"right\">";
                                        $temp = "uti".$data['name'];
                                        echo "<strong>" . money_format('%!i', convert_cur($$temp)) . "</strong>";
                                echo "</td>";
                        }
                //}

                echo "<td align=\"right\">";
			echo "<strong>" . money_format('%!i', convert_cur($total_new_sum)). "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
			echo "<strong>" . money_format('%!i', convert_cur($total_old_sum)). "</strong>";
                echo "</td>";
	echo "</tr>";

	echo "<tr>";
                echo "<td class=\"bold\">";
                        echo "NET BALANCE AS AT THE YEAR-END (a+b-c)";
                echo "</td>";

		//if(count($designated_earmarked_funds) > 0){

                        foreach($designated_earmarked_funds_group as $name => $data){
				$net_val = 0;
				$temp = "add".$data['name'];
				$net_val = $$temp;
				$temp = "uti".$data['name'];
				$net_val = $net_val - $$temp;
                                echo "<td align=\"right\">";
                                        echo "<strong>" . money_format('%!i', convert_cur($net_val)). "</strong>";
                                echo "</td>";
                        }

			foreach($designated_earmarked_funds_ledger as $name => $data){
                                $net_val = 0;
                                $temp = "add".$data['name'];
                                $net_val = $$temp;
                                $temp = "uti".$data['name'];
                                $net_val = $net_val - $$temp;
                                echo "<td align=\"right\">";
                                        echo "<strong>" . money_format('%!i', convert_cur($net_val)). "</strong>";
                                echo "</td>";
                        }
        	//}	

                echo "<td align=\"right\">";
		        echo "<strong>" . money_format('%!i', convert_cur($net_total_new)) . "</strong>";
                echo "</td>";

                echo "<td align=\"right\">";
			echo "<strong>" . money_format('%!i', convert_cur($net_total_old)) . "</strong>";
                echo "</td>";

        echo "</tr>";

	echo "</table>";   */
?>

<?php
//        echo form_open('admin/user/aggregatoraccounts/'.$user_name);
	
        echo "<p>";
        echo form_label('Username ');
        echo " :  ".$user_name;
        echo "</p>";
	
        echo  "<p>";
        echo form_label('User Role ');
        echo " :  ".$role;
        
        echo "</p>";
		
		$aggact="";
                $db1=$this->load->database('login', TRUE);
                $db1->from('aggregateaccounts')->where('username', $user_name);
                $agglist = $db1->get();
		foreach($agglist->result() as $row)
                {
			$aggact = $row->accounts;
		}
	        $accarray = array();
	        $accarray = explode(",", $aggact);

                $restacct = array();
                $restacc = array_diff($accounts, $accarray);
		$restacc =array_merge($restacc);
		echo "<br>";
                $max = sizeof($restacc);
                $data=array();
		echo "User ".$user_name. " has currently Aggregator of ". $aggact." accounts";
		echo "</br>";
		echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"25%\">";
	        echo "<thead><tr><th>S.No</th><th>Account Name</th><th>Add/Drop Account</th><th></th></tr></thead>";
		for($j=0; $j<sizeof($accarray); $j++)
		{
			$t=$j+1;
			echo "<tr>";
			echo "<td>".$t."</td>";
			echo "<td>".$accarray[$j]."</td>";
			echo "<td>".anchor('admin/user/delaggact/'.$user_name."/".$accarray[$j], 'Drop'); "</td>";
			echo "</tr>";
		}
		$p=$t+1;
		$i=1;
                $counter=0;
		$newact=array();
                while($max>0)
                {
                        $max=$max-1;
                        $cnt=$counter+1;
                        echo "<tr>";
                        echo "<td>".$p."</td>";
                          echo "<td>".$restacc[$counter]."<br></td>";
			  
                        echo "<td>".anchor('admin/user/addaggact/'.$user_name."/".$restacc[$counter], 'Add'); "</td>";
                        echo "</tr>";
                        $counter=$counter+1;
			$p=$p+1;
                }


                echo"<br>";
                echo "<tbody>";
                echo "</table>";

        echo "<p>";
        echo " ";
        echo anchor('admin/user', 'Back', array('title' => 'Back to user list'));
        echo "</p>";
        echo form_close();


<?php
	if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	echo form_open('log/LogReport/'.$name);
        echo "<p>";
        echo form_label('Search By', 'search_by');
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_dropdown('search_by', $search_by, $search_by_active);
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	echo "<span id=\"tooltip-target-1\">";
        echo form_label('Text', 'text');
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_input($text);
	echo "</span>";
	echo "<span id=\"tooltip-content-1\"> Please enter date in dd mm yyyy format.</span>";echo "</span>";
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_submit('submit', 'Search');
        echo " ";
        echo "</p>";
        echo form_close();
?>
<?php
	//Chart of account reporting system....
	if($name == 'COA'){
		echo "<table border=0 class=\"simple-table\">";
		echo "<thead><tr><th width=\"90\">Date</th><th>Host IP</th><th>User</th><th>Message</th><th>Browser</th></tr></thead>";
		if($search == ''){
			$this->db->from('logs')->where('url LIKE', '/ledger'.'%');
			$logs_q = $this->db->get();	
		}else{
			if($search == "Select"){
                		$this->messages->add('Please select search type from drop down list.', 'error');
                        	redirect('log/LogReport/'.$name);
	                }else{
				$field = $search . '      ' . 'LIKE';
	                        $a = trim($text['value']);
				echo"<br>";
				if($search == 'date')
				$a=$datetext;
				$this->db->from('logs')->where('url LIKE', '/ledger'.'%')->where($field, '%' . $a . '%')->order_by('date', 'desc');
                        	$logs_q=$this->db->get();
			}
			if($logs_q->num_rows() == 0)
                        {
                                $this->messages->add('Text is not found.', 'error');
                                redirect('log/LogReport/'.$name);
                                return;
                        }

		}
			foreach ($logs_q->result() as $row){
				echo "<tr>";
				echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
				echo "<td>" . $row->host_ip . "</td>";
				echo "<td>" . $row->user . "</td>";
				echo "<td>" . $row->message_title . "</td>";
				echo "<td>" . character_limiter($row->user_agent, 30) . "</td>";
				echo "</tr>";
			}
	}
	//Budget reporting system....
	if($name == 'BugtLog'){
		if($search == ''){
                	$this->db->from('logs')->where('url LIKE', '/budget'.'%');
                	$logs_q = $this->db->get();
		}else{
                	if($search == "Select"){
                                $this->messages->add('Please select search type from drop down list.', 'error');
                                redirect('log/LogReport/'.$name);
                        }else{
                        	$field = $search . '      ' . 'LIKE';
                                $a = trim($text['value']);
				 if($search == 'date')
                                        $a=$datetext;
                                echo"<br>";
				$this->db->from('logs')->where('url LIKE', '/budget'.'%')->where($field, '%' . $a . '%');
                                $logs_q=$this->db->get();
			}
			if($logs_q->num_rows() == 0)
                        {
                                $this->messages->add('Text is not found.', 'error');
                                redirect('log/LogReport/'.$name);
                                return;
                        }

		}
                echo "<table border=0 class=\"simple-table\">";
                echo "<thead><tr><th width=\"90\">Date</th><th>Host IP</th><th>User</th><th>Message</th><th>Browser</th></tr></thead>";
		foreach ($logs_q->result() as $row){
			echo "<tr>";
                        echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
                        echo "<td>" . $row->host_ip . "</td>";
                        echo "<td>" . $row->user . "</td>";
                        echo "<td>" . $row->message_title . "</td>";
                        echo "<td>" . character_limiter($row->user_agent, 30) . "</td>";
                        echo "</tr>";
		}
		
	}
	//Transaction log reporting system....
	if($name == 'TrnsLog'){
		if($search == ''){
                	$this->db->from('logs')->where('url LIKE', '/entry'.'%');
                	$logs_q = $this->db->get();
		}else{
                        if($search == "Select"){
                                $this->messages->add('Please select search type from drop down list.', 'error');
                                redirect('log/LogReport/'.$name);
                        }else{
			  	$field = $search . '      ' . 'LIKE';
                                $a = trim($text['value']);
				if($search == 'date')
                                        $a=$datetext;
                                echo"<br>";
                                $this->db->from('logs')->where('url LIKE', '/entry'.'%')->where($field, '%' . $a . '%');
                                $logs_q=$this->db->get();
                        }
			if($logs_q->num_rows() == 0)
                        {
                                $this->messages->add('Text is not found.', 'error');
                                redirect('log/LogReport/'.$name);
                                return;
                        }

			if($logs_q->num_rows() == 0)
                	{
				$this->messages->add('Text is not found.', 'error');
                        	redirect('log/LogReport/'.$name);
                        	return;
			}
                }
                echo "<table border=0 class=\"simple-table\">";
                echo "<thead><tr><th width=\"90\">Date</th><th>Host IP</th><th>User</th><th>Message</th><th>Browser</th></tr></thead>";
		foreach ($logs_q->result() as $row){
			echo "<tr>";
                        echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
                        echo "<td>" . $row->host_ip . "</td>";
                        echo "<td>" . $row->user . "</td>";
                        echo "<td>" . $row->message_title . "</td>";
                        echo "<td>" . character_limiter($row->user_agent, 30) . "</td>";
                        echo "</tr>";
		}
	}
	//Other reporting system....
	if($name == 'OtherLog'){
		if($search == ''){
			$this->db->from('logs')->order_by('id', 'desc');
			$this->db->not_like('url','/budget');		
			$this->db->not_like('url','/entry');		
			$this->db->not_like('url','/ledger');		
                	$logs_q = $this->db->get();
		}else{
                	if($search == "Select"){
                                $this->messages->add('Please select search type from drop down list.', 'error');
                                redirect('log/LogReport/'.$name);
                        }else{
				$field = $search . '      ' . 'LIKE';
                                $a = trim($text['value']);
				if($search == 'date')
                                        $a=$datetext;
                                echo"<br>";
				$this->db->from('logs')->order_by('id', 'desc');
                		$this->db->not_like('url','/budget');
                		$this->db->not_like('url','/entry');
                		$this->db->not_like('url','/ledger');
				$this->db->where($field, '%' . $a . '%');
                		$logs_q = $this->db->get();
                        }
			if($logs_q->num_rows() == 0)
                        {
                                $this->messages->add('Text is not found.', 'error');
                                redirect('log/LogReport/'.$name);
                                return;
                        }

                }

                echo "<table border=0 class=\"simple-table\">";
                echo "<thead><tr><th width=\"90\">Date</th><th>Host IP</th><th>User</th><th>Message</th><th>Browser</th></tr></thead>";

		foreach ($logs_q->result() as $row){
				echo "<tr>";
                                echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
                                echo "<td>" . $row->host_ip . "</td>";
                                echo "<td>" . $row->user . "</td>";
                                echo "<td>" . $row->message_title . "</td>";
                                echo "<td>" . character_limiter($row->user_agent, 30) . "</td>";
                                echo "</tr>";
		}
	}
	echo "</table>";
?>
        <!--<div id="pagination-container">
	<?php //echo $this->pagination->create_links(); ?>
	</div>-->


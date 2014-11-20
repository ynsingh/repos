<?php
	if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	echo form_open('log/LogReport/'.$name);
        echo "<p>";
        echo form_label('Search By', 'search_by');
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_dropdown('search_by', $search_by, $search_by_active);
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_label('Text', 'text');
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_input($text);
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        echo form_submit('submit', 'Search');
        echo " ";
        echo "</p>";
        echo form_close();
?>
<?php
//			echo "search==============$search";
	if($name == 'COA')
	{
		echo "<table border=0 class=\"simple-table\">";
		echo "<thead><tr><th width=\"90\">Date</th><th>Host IP</th><th>User</th><th>Message</th><th>Browser</th></tr></thead>";
		if($search == '')
		{
			$this->db->from('logs')->where('url LIKE', '/ledger'.'%');
			$logs_q = $this->db->get();	
		}
		else
		{
			echo "search inside else==============$search";
			if($search == "Select")
        	        {
                	        $this->messages->add('Please select search type from drop down list.', 'error');
                        	redirect('log/LogReport/'.$name);
				//return;
	                }
	                else {
				$field = $search . '      ' . 'LIKE';
	                        $a = trim($text['value']);
				$this->db->from('logs')->where('url LIKE', '/ledger'.'%')->where($field, '%' . $a . '%');
                        	$logs_q=$this->db->get();
			}
		}
				foreach ($logs_q->result() as $row)
				{
					//if(strpos($row->url,'/ledger') !== false)
					//{
					echo "<tr>";
					echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
					echo "<td>" . $row->host_ip . "</td>";
					echo "<td>" . $row->user . "</td>";
					echo "<td>" . $row->message_title . "</td>";
					//echo "<td>" .  anchor($row->url, "Link", array('title' => 'Link to action', 'class' => 'anchor-link-a')) . "</td>";
					echo "<td>" . character_limiter($row->user_agent, 30) . "</td>";
					echo "</tr>";
				//}
				}
			//}
		}
	if($name == 'BugtLog')
	{
                $this->db->from('logs')->where('url LIKE', '/budget'.'%');
                $logs_q = $this->db->get();
                echo "<table border=0 class=\"simple-table\">";
                //echo "<thead><tr><th width=\"90\">Date</th><th>Host IP</th><th>User</th><th>Message</th><th width=\"30\">URL</th><th>Browser</th></tr></thead>";
                echo "<thead><tr><th width=\"90\">Date</th><th>Host IP</th><th>User</th><th>Message</th><th>Browser</th></tr></thead>";

		foreach ($logs_q->result() as $row)
                {
			/*if(strpos($row->url,'/budget') !== false)
			{*/
				echo "<tr>";
                                echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
                                echo "<td>" . $row->host_ip . "</td>";
                                echo "<td>" . $row->user . "</td>";
                                echo "<td>" . $row->message_title . "</td>";
                                //echo "<td>" .  anchor($row->url, "Link", array('title' => 'Link to action', 'class' => 'anchor-link-a')) . "</td>";
                                echo "<td>" . character_limiter($row->user_agent, 30) . "</td>";
                                echo "</tr>";
			//}
		}
		
	}
	if($name == 'TrnsLog')
	{
                $this->db->from('logs')->where('url LIKE', '/entry'.'%');
                $logs_q = $this->db->get();
                echo "<table border=0 class=\"simple-table\">";
                //echo "<thead><tr><th width=\"90\">Date</th><th>Host IP</th><th>User</th><th>Message</th><th width=\"30\">URL</th><th>Browser</th></tr></thead>";
                echo "<thead><tr><th width=\"90\">Date</th><th>Host IP</th><th>User</th><th>Message</th><th>Browser</th></tr></thead>";

		foreach ($logs_q->result() as $row)
                {
			/*if(strpos($row->url,'/entry') !== false)
			{*/
				echo "<tr>";
                                echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
                                echo "<td>" . $row->host_ip . "</td>";
                                echo "<td>" . $row->user . "</td>";
                                echo "<td>" . $row->message_title . "</td>";
                                //echo "<td>" .  anchor($row->url, "Link", array('title' => 'Link to action', 'class' => 'anchor-link-a')) . "</td>";
                                echo "<td>" . character_limiter($row->user_agent, 30) . "</td>";
                                echo "</tr>";
			//}
			
		}
	}
	if($name == 'OtherLog')
	{
		$this->db->from('logs')->order_by('id', 'desc');
		//$this->db->from('logs')->where('url NOT LIKE', '/entry'.'%');
		$this->db->not_like('url','/budget');		
		$this->db->not_like('url','/entry');		
		$this->db->not_like('url','/ledger');		
                //$this->db->from('logs')->where('url NOT LIKE', '/ledger'.'%');
                $logs_q = $this->db->get();
                echo "<table border=0 class=\"simple-table\">";
                //echo "<thead><tr><th width=\"90\">Date</th><th>Host IP</th><th>User</th><th>Message</th><th width=\"30\">URL</th><th>Browser</th></tr></thead>";
                echo "<thead><tr><th width=\"90\">Date</th><th>Host IP</th><th>User</th><th>Message</th><th>Browser</th></tr></thead>";

		foreach ($logs_q->result() as $row)
                {
			//if((strpos($row->url,'/entry') === false) && (strpos($row->url,'/budget') === false) && (strpos($row->url,'/ledger') === false))
                        //{
				
				echo "<tr>";
                                echo "<td>" . date_mysql_to_php_display($row->date) . "</td>";
                                echo "<td>" . $row->host_ip . "</td>";
                                echo "<td>" . $row->user . "</td>";
                                echo "<td>" . $row->message_title . "</td>";
                                //echo "<td>" .  anchor($row->url, "Link", array('title' => 'Link to action', 'class' => 'anchor-link-a')) . "</td>";
                                echo "<td>" . character_limiter($row->user_agent, 30) . "</td>";
                                echo "</tr>";
			//}
		}
	}
	echo "</table>";
?>
        <!--<div id="pagination-container">
	<?php //echo $this->pagination->create_links(); ?>
	</div>-->


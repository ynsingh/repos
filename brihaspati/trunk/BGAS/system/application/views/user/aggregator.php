<?php
                        $username=$this->session->userdata('user_name');
                        $db1=$this->load->database('login', TRUE);
                        $db1->from('aggregateaccounts')->where('username', $username);
                        $userrec = $db1->get();
                        foreach($userrec->result() as $row)
                        {
                                
                                $acountlist=$row->accounts;
                        }
			echo $acountlist;
//?>

<div>
	<div id="left-col">
		<div class="settings-container">
			<div class="settings-title">
				<?php echo anchor('user/aggregatebalancesheet', 'Aggregate BalanceSheet'); ?> 
			</div>
			<div class="settings-desc">
				View aggregate BalanceSheet of Multiple accounts selected
			</div>
		</div>
		<div class="settings-container">
			<div class="settings-title">
				<?php echo anchor('admin/manage', 'Aggregate Budget', array('title' => 'Manage existing accounts')); ?>
			</div>
			<div class="settings-desc">
				View aggregate butdget of Multiple accounts selected	
			</div>
		</div>
<!--		<div class="settings-container">
			<div class="settings-title">
				<?php// echo anchor('admin/user', 'Manage Users', array('title' => 'Manage users')); ?>
			</div>
			<div class="settings-desc">
				Manage users and permissions
			</div>
		</div>-->
	</div>
</div>
<div class="clear">
</div>


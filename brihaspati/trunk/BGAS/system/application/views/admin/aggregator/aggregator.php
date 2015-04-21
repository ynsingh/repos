<?php
                        $username=$this->session->userdata('user_name');
                        $db1=$this->load->database('login', TRUE);
                        $db1->from('aggregateaccounts')->where('username', $username);
                        $userrec = $db1->get();
                        foreach($userrec->result() as $row)
                        {
                                
                                $acountlist=$row->accounts;
                        }
//?>

<div>
	<div id="left-col">
		<div class="settings-container">
			<?php echo "Aggregate Accounts of ".$username. "              ".      $acountlist ?>
		</div>
		<div class="settings-container">
			<div class="settings-title">
				<?php echo anchor('admin/aggregator/aggregatebalancesheet', 'Aggregate BalanceSheet'); ?> 
			</div>
			<div class="settings-desc">
				View aggregate BalanceSheet of Multiple accounts selected
			</div>
		</div>
                <div class="settings-container">
                        <div class="settings-title">
                                <?php echo anchor('user/aggregatebudget', 'Aggregate Budget'); ?>
                                <?//php echo anchor('aggregatebudget', 'Aggregate Budget'); ?>
                        </div>
                        <div class="settings-desc">
                                View aggregate Budget of Multiple accounts selected
                        </div>
                </div>

	</div>
</div>


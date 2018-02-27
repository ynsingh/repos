<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');?>
<div>
	<div id="left-col">
		<div class="settings-container">
			<div class="settings-title">
				<?php echo anchor('report/balancesheet', 'Balance Sheet', array('title' => 'Balance Sheet', 'class' => 'loading')); ?>
			</div>
       	                <div class="settings-desc">
				&nbsp;    
        		</div>
		</div>
		<div class="settings-container">
			<div class="settings-title">
				<?php echo anchor('report/new_balancesheet', 'Balance Sheet MHRD Format', array('title' => 'Balance Sheet MHRD Format', 'class' => 'loading')); ?>
			</div>
			<div class="settings-desc">
				&nbsp;
			</div>
		</div>
		<div class="settings-container">
			<div class="settings-title">
				<?php echo anchor('report/profitandloss', 'Income and Expenditure  Statement', array('title' => 'Income and Expenditure Statement', 'class' => 'loading')); ?>
			</div>
			    <div class="settings-desc">
				&nbsp;        
                       	    </div>
		</div>
		<div class="settings-container">
                        <div class="settings-title">
                                <?php echo anchor('report2/profitandloss_mhrdnew', 'Income and Expenditure MHRD Format', array('title' => 'Income and Expenditure  Statement MHRD Format', 'class' => 'loading')); ?>
                        </div>
                        <div class="settings-desc">
                                &nbsp;
                        </div>
                </div>

		<div class="settings-container">
			<div class="settings-title">
				<?php echo anchor('report/paymentreceipt', 'Payment & Receipt', array('title' => 'Payment & Receipt', 'class' => 'loading')); ?>
			</div>
			<div class="settings-desc">
				&nbsp;
			</div>
		</div>
		<div class="settings-container">
			<div class="settings-title">
				<?php echo anchor('report/trialbalance', 'Trial Balance', array('title' => 'Trial Balance')); ?>
			</div>
			<div class="settings-desc">
				&nbsp;
			</div>
		</div>
		<div class="settings-container">
			<div class="settings-title">
				<?php echo anchor('report/ledgerst', 'Ledger Statement', array('title' => 'Ledger Statement')); ?>
			</div>
			<div class="settings-desc">
				&nbsp;
			</div>
		</div>
		<div class="settings-container">
			<div class="settings-title">
				<?php echo anchor('report/reconciliation/pending', 'Reconciliation ', array('title' => 'Reconciliation ')); ?>
			</div>
			<div class="settings-desc">
				&nbsp;
			</div>
		</div>
		<div class="settings-container">
			<div class="settings-title">
				<?php echo anchor('report/depreciation', 'Depreciation of Asset', array('title' => 'Depreciation of Asset')); ?>
			</div>
			<div class="settings-desc">
				&nbsp;
			</div>
		</div>
	</div>

	<div id="right-col">
		<div class="settings-container">
                        <div class="settings-title">
                                <?php echo anchor('report/dayst', 'Day Book', array('title' => 'Day Book')); ?>
                        </div>
                        <div class="settings-desc">
                                &nbsp;
                        </div>
                </div>
		<div class="settings-container">
                        <div class="settings-title">
                                <?php echo anchor('report/cashst', 'Cash/Bank Book', array('title' => 'Cash /Bank Book')); ?>
                        </div>
                        <div class="settings-desc">
                                &nbsp;
                        </div>
                </div>
                
		<div class="settings-container">
                        <div class="settings-title">
                                <?php echo anchor('unspentbalance/planreport', 'Unspent Plan Balance', array('title' => 'Unspent Plan Balance')); ?>
                        </div>
                        <div class="settings-desc">
                                &nbsp;
                        </div>
                </div>
		<div class="settings-container">
                        <div class="settings-title">
                                <?php echo anchor('unspentbalance/nonplanreport', 'Unspent Non Plan Balance', array('title' => 'Unspent Non Plan Balance')); ?>
                        </div>
                        <div class="settings-desc">
                                &nbsp;
                        </div>
                </div>
		<div class="settings-container">
                        <div class="settings-title">
                                <?php echo anchor('unspentbalance/summaryreport', 'Unspent Summary Balance', array('title' => 'Unspent Summary Balance')); ?>
                        </div>
                        <div class="settings-desc">
                                &nbsp;
                        </div>
                </div>

	
	</div>
</div>
<div class="clear">
</div>

use pico;

ALTER TABLE `items` ADD `item_irstatus` VARCHAR(255) NOT NULL AFTER `item_receivername`;

ALTER TABLE `tender_apply` ADD `ta_guarantee` VARCHAR(255) NOT NULL AFTER `ta_warranty`;

ALTER TABLE `tender_create` ADD `tc_quantity` INT NOT NULL AFTER `tc_workdesc`;
ALTER TABLE `tender_create` ADD `tc_l1status` VARCHAR(255) NOT NULL AFTER `tc_approvedbydate`;

ALTER TABLE `purchase_proposal` CHANGE `pp_deliveryperiod` `pp_deliveryperiod` VARCHAR(255) NOT NULL;
ALTER TABLE `purchase_proposal` CHANGE `pp_deliveryperiodfrom` `pp_deliveryperiodfrom` VARCHAR(255) NOT NULL;
ALTER TABLE `purchase_proposal` CHANGE `pp_deliveryperiodto` `pp_deliveryperiodto` VARCHAR(255) NOT NULL;
ALTER TABLE `purchase_proposal` CHANGE `pp_indentdate` `pp_indentdate` VARCHAR(255) NOT NULL;
ALTER TABLE `purchase_proposal` CHANGE `pp_budgethead2` `pp_budgetamt` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL;
ALTER TABLE `purchase_proposal` ADD `pp_orderstatus` VARCHAR(255) NOT NULL AFTER `pp_payterm`;

ALTER TABLE `purchase_order` CHANGE `po_no` `po_no` VARCHAR(255) NOT NULL;

CREATE TABLE `l1_details` (
  `ld_id` int(11) NOT NULL,
  `ld_tcid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ld_vendorid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ld_tenrefno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ld_remark` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `ld_pcid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ld_preparedby` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ld_preparationdate` date NOT NULL,
  `ld_modifiedby` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ld_modificationdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for table `l1_details`
--
ALTER TABLE `l1_details`
  ADD PRIMARY KEY (`ld_id`);

--
-- AUTO_INCREMENT for table `l1_details`
--
ALTER TABLE `l1_details`
  MODIFY `ld_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `l1_details` ADD `ld_comparativeflag` VARCHAR(255) NOT NULL AFTER `ld_preparationdate`;
ALTER TABLE `l1_details` ADD `ld_taid` VARCHAR(255) NOT NULL AFTER `ld_tcid`;

CREATE TABLE `budget_details` (
  `bd_id` int(11) NOT NULL,
  `bd_ppid` int(11) NOT NULL,
  `bd_budgetdept` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `bd_budgetprojno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `bd_budgethead` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `bd_budgetamount` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for table `budget_details`
--
ALTER TABLE `budget_details`
  ADD PRIMARY KEY (`bd_id`);

ALTER TABLE `budget_details` CHANGE `bd_id` `bd_id` INT(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `budget_details` ADD `bd_budgetused` INT NOT NULL AFTER `bd_budgetamount`;
COMMIT;

ALTER TABLE `cover_type` CHANGE `ct_id` `ct_id` INT(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `cover_type` CHANGE `ct_coverno` `ct_coverno` VARCHAR(255) NOT NULL;

ALTER TABLE `depart_indent` ADD PRIMARY KEY( `di_id`);
ALTER TABLE `depart_indent` CHANGE `di_id` `di_id` INT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `financial_power` CHANGE `fp_creatorid` `fp_creatorid` VARCHAR(255) NOT NULL;
ALTER TABLE `financial_power` CHANGE `fp_creatordate` `fp_creatordate` DATE NOT NULL, CHANGE `fp_modifierid` `fp_modifierid` VARCHAR(255) NOT NULL, CHANGE `fp_modifierdate` `fp_modifierdate` DATE NOT NULL;

ALTER TABLE `items` CHANGE `item_id` `item_id` INT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `product_category` ADD PRIMARY KEY( `pc_id`);
ALTER TABLE `product_category` CHANGE `pc_id` `pc_id` INT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `purchase_committee` CHANGE `pc_id` `pc_id` INT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `purchase_order` ADD PRIMARY KEY( `po_id`);
ALTER TABLE `purchase_order` CHANGE `po_id` `po_id` INT(11) NOT NULL AUTO_INCREMENT, CHANGE `po_ppid` `po_ppid` INT(11) NOT NULL, CHANGE `po_tcid` `po_tcid` INT(11) NOT NULL, CHANGE `po_taid` `po_taid` INT(11) NOT NULL, CHANGE `po_no` `po_no` INT(11) NOT NULL, CHANGE `po_vendorid` `po_vendorid` INT(11) NOT NULL, CHANGE `po_itemid` `po_itemid` INT(11) NOT NULL;

ALTER TABLE `purchase_proposal` ADD PRIMARY KEY( `pp_id`);
ALTER TABLE `purchase_proposal` CHANGE `pp_id` `pp_id` INT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `required_item_details` CHANGE `rid_id` `rid_id` INT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `tender_apply` CHANGE `ta_id` `ta_id` INT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `tender_create` CHANGE `tc_id` `tc_id` INT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `vendor` CHANGE `vendor_id` `vendor_id` INT(11) NOT NULL;
ALTER TABLE `vendor` ADD PRIMARY KEY( `vendor_id`);


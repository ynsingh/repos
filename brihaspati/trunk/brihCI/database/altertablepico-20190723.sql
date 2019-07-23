ALTER TABLE `tender_apply` ADD `ta_tcrefno` VARCHAR(255) NOT NULL AFTER `ta_tcid`;

ALTER TABLE `tender_apply` ADD `ta_status` VARCHAR(255) NOT NULL AFTER `ta_updoc5`;

ALTER TABLE `tender_apply` ADD `ta_approvedby` VARCHAR(255) NOT NULL AFTER `ta_status`;

ALTER TABLE `tender_apply` ADD `ta_approvedbydate` DATE NOT NULL AFTER `ta_approvedby`;

ALTER TABLE `tender_apply` ADD `ta_approvedbydesg` VARCHAR(255) NOT NULL AFTER `ta_approvedby`;

CREATE TABLE `tender_apply_archive` (
  `archive_id` int(11) NOT NULL,
  `ta_id` int(11) NOT NULL,
  `ta_tcid` int(11) NOT NULL,
  `ta_approvedstatus` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_byname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_bydesig` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `archive_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `tender_apply_archive`
  ADD PRIMARY KEY (`archive_id`);

ALTER TABLE `tender_apply_archive`
  MODIFY `archive_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

ALTER TABLE `vendor` ADD `vendor_userid` INT(11) NULL AFTER `vendor_id`;
ALTER TABLE `vendor_archive` ADD `vendor_archive_userid` INT(11) NULL AFTER `vendor_archive_id`;

ALTER TABLE `tender_apply` ADD `ta_warranty` VARCHAR(255) NOT NULL AFTER `ta_totalprice`;

ALTER TABLE `tender_apply` ADD `ta_payment` VARCHAR(255) NOT NULL AFTER `ta_warranty`;

ALTER TABLE `tender_apply` ADD `ta_delivery` VARCHAR(255) NOT NULL AFTER `ta_payment`;

ALTER TABLE `tender_apply` ADD `ta_validity` VARCHAR(255) NOT NULL AFTER `ta_delivery`;

ALTER TABLE `purchase_proposal` ADD `pp_tenrefno` VARCHAR(255) NOT NULL AFTER `pp_gemrefno`;

ALTER TABLE `purchase_proposal` ADD `pp_purchasefrom` VARCHAR(255) NOT NULL AFTER `pp_taid`;


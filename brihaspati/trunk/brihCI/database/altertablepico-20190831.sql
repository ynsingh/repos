ALTER TABLE `stock_items_issued` CHANGE `stock_desc` `sii_desc` BLOB NULL DEFAULT NULL;
ALTER TABLE `items_return` ADD `ir_iiid` INT(11) NOT NULL AFTER `ir_id`;
ALTER TABLE `items_issued` CHANGE `ii_qty` `ii_qty` INT(11) NOT NULL;
ALTER TABLE `items_issued` ADD `ii_irqty` INT(11) NOT NULL DEFAULT '0' AFTER `ii_qty`;
ALTER TABLE `items_return` CHANGE `ir_qty` `ir_qty` INT(11) NOT NULL;
ALTER TABLE `items` ADD `item_podate` DATE NULL AFTER `item_pono`;

CREATE TABLE `specification` (
  `id` int(10) NOT NULL,
  `enquiry_date` date NOT NULL,
  `enquiry_no` varchar(255) NOT NULL,
  `enquiry_lastdate` date NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `item_quantity` int(11) NOT NULL,
  `description` blob NOT NULL,
  `description_upload_filename` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `dept_id` varchar(255) NOT NULL,
  `desig_id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `terms_condition_desc` varchar(255) NOT NULL,
  `terms_condition_filename` varchar(255) NOT NULL,
  `creator_id` varchar(255) NOT NULL,
  `creator_date` date NOT NULL,
  `modifier_id` varchar(255) NOT NULL,
  `modifier_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `specification`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `specification`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

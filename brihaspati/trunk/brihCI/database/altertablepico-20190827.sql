ALTER TABLE `items` ADD `item_desc` BLOB NOT NULL AFTER `item_stock`, ADD `item_pono` VARCHAR(255) NOT NULL AFTER `item_desc`, ADD `item_challanno` VARCHAR(255) NOT NULL AFTER `item_pono`, ADD `item_challandate` DATE NOT NULL AFTER `item_challanno`, ADD `item_transport` VARCHAR(255) NULL AFTER `item_challandate`, ADD `item_receivername` VARCHAR(255) NOT NULL AFTER `item_transport`, ADD `item_creatorname` VARCHAR(255) NOT NULL AFTER `item_receivername`, ADD `item_creatordate` DATE NOT NULL AFTER `item_creatorname`, ADD `item_modifiername` VARCHAR(255) NOT NULL AFTER `item_creatordate`;

ALTER TABLE `items` ADD `item_modifierdate` DATE NOT NULL AFTER `item_modifiername`;

ALTER TABLE `items` CHANGE `item_stock` `item_qty` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL;

CREATE TABLE `stock_items` (
  `stock_id` int(11) NOT NULL,
  `stock_mtid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `stock_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `stock_qty` int(5) NOT NULL,
  `stock_desc` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `stock_items`
  ADD PRIMARY KEY (`stock_id`);
ALTER TABLE `stock_items`
  MODIFY `stock_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `stock_items` ADD UNIQUE( `stock_mtid`, `stock_name`);
ALTER TABLE `items` ADD UNIQUE( `item_mtid`, `item_name`, `item_pono`, `item_challanno`);

CREATE TABLE `stock_items_archive` (
  `stocka_id` int(11) NOT NULL,
  `stocka_stockid` int(11) NOT NULL,
  `stocka_mtid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `stocka_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `stocka_qty` int(5) NOT NULL,
  `stocka_desc` blob,
  `stocka_upstatus` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `stocka_creatorname` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `stocka_creatordate` DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `stock_items_archive`
  ADD PRIMARY KEY (`stocka_id`);
ALTER TABLE `stock_items_archive`
  MODIFY `stocka_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Table structure for table `items_issued`
--

CREATE TABLE `items_issued` (
  `ii_id` int(11) NOT NULL,
  `ii_itemid` int(11) NOT NULL,
  `ii_mtid` int(15) NOT NULL,
  `ii_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ii_qty` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ii_desc` blob NOT NULL,
  `ii_staffpfno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ii_staffname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ii_dept` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ii_receivername` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ii_creatorname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ii_creatordate` date NOT NULL,
  `ii_modifiername` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ii_modifierdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `items_issued`
  ADD PRIMARY KEY (`ii_id`);
ALTER TABLE `items_issued`
  MODIFY `ii_id` int(11) NOT NULL AUTO_INCREMENT;


--
-- Table structure for table `stock_items_issued`
--

CREATE TABLE `stock_items_issued` (
  `sii_id` int(11) NOT NULL,
  `sii_mtid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sii_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sii_qty` int(5) NOT NULL,
  `stock_desc` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `stock_items_issued`
  ADD PRIMARY KEY (`sii_id`),
  ADD UNIQUE KEY `sii_mtid` (`sii_mtid`,`sii_name`);
ALTER TABLE `stock_items_issued`
  MODIFY `sii_id` int(11) NOT NULL AUTO_INCREMENT;


--
-- Table structure for table `stock_items_issued_archive`
--

CREATE TABLE `stock_items_issued_archive` (
  `siia_id` int(11) NOT NULL,
  `siia_stockid` int(11) NOT NULL,
  `siia_mtid` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `siia_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `siia_qty` int(5) NOT NULL,
  `siia_desc` blob,
  `siia_upstatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `siia_creatorname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `siia_creatordate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `stock_items_issued_archive`
  ADD PRIMARY KEY (`siia_id`);
ALTER TABLE `stock_items_issued_archive`
  MODIFY `siia_id` int(11) NOT NULL AUTO_INCREMENT;



--
-- Table structure for table `items_return`
--

CREATE TABLE `items_return` (
  `ir_id` int(11) NOT NULL,
  `ir_itemid` int(11) NOT NULL,
  `ir_mtid` int(15) NOT NULL,
  `ir_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ir_qty` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ir_desc` blob NOT NULL,
  `ir_staffpfno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ir_staffname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ir_dept` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ir_receivername` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ir_creatorname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ir_creatordate` date NOT NULL,
  `ir_modifiername` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ir_modifierdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `items_return`
  ADD PRIMARY KEY (`ir_id`);
ALTER TABLE `items_return`
  MODIFY `ir_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `items` ADD `item_balqty` INT(11) NOT NULL AFTER `item_qty`;
ALTER TABLE `items` CHANGE `item_qty` `item_qty` INT(11) NOT NULL;


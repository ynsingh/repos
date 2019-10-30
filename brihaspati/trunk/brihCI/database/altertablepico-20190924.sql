ALTER TABLE `items_issued` ADD `ii_itemindexno` VARCHAR(255) NULL AFTER `ii_name`;
ALTER TABLE `items_return` ADD `ir_itemindexno` VARCHAR(255) NULL AFTER `ir_name`;
ALTER TABLE `stock_item_transfer` ADD `sit_itemindexno` VARCHAR(255) NULL AFTER `sit_name`;
ALTER TABLE `item_index` ADD `itemmtid` INT(11) NOT NULL AFTER `id`;


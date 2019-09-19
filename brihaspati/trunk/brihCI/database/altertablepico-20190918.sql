CREATE TABLE `payment_order` ( 
        `pay_id` INT(11) NOT NULL AUTO_INCREMENT , 
        `pay_tenderid` INT(11) NOT NULL , 
        `pay_vendorid` INT(11) NOT NULL , 
        `pay_pono` INT(11) NOT NULL , 
        `pay_irno` INT(11) NOT NULL , 
        `pay_totamt` FLOAT(9,2) NOT NULL , 
        `pay_amount` FLOAT(9,2) NOT NULL , 
        `pay_sgstamt` FLOAT(9,2) NULL , 
        `pay_cgstamt` FLOAT(9,2) NULL , 
        `pay_deductamt` FLOAT(9,2) NULL , 
        `pay_date` DATE NOT NULL , 
        `pay_remark` VARCHAR(255) NULL , 
        `pay_checkno` VARCHAR(255) NULL , 
        `pay_paymethod` VARCHAR(255) NULL , 
        `pay_bank` VARCHAR(255) NULL , 
        `pay_accountno` VARCHAR(255) NULL , 
        `pay_creatordate` DATE NOT NULL , 
        `pay_creatorname` VARCHAR(255) NOT NULL , 
        `pay_modifiername` VARCHAR(255) NOT NULL , 
        `pay_modifierdate` DATE NOT NULL , 
        PRIMARY KEY (`pay_id`), 
        UNIQUE (`pay_tenderid`, `pay_vendorid`, `pay_pono`, `pay_irno`)
) ENGINE = InnoDB;

CREATE TABLE `stock_item_transfer` ( 
	`sit_id` INT(11) NOT NULL AUTO_INCREMENT , 
	`sit_mtid` INT(11) NOT NULL , 
	`sit_itemid` INT NOT NULL , 
	`sit_name` VARCHAR(255) NOT NULL , 
	`sit_qty` INT(5) NOT NULL , 
	`sit_uprice` FLOAT(10,2) NOT NULL , 
	`sit_totalprice` FLOAT(10,2) NOT NULL , 
	`sit_desc` VARCHAR(255) NOT NULL , 
	`sit_todept` VARCHAR(255) NOT NULL , 
	`sit_toemppf` VARCHAR(255) NOT NULL , 
	`sit_toreceiver` VARCHAR(255) NOT NULL , 
	`sit_trfdate` DATE NOT NULL , 
	`sit_remark` VARCHAR(255) NOT NULL , 
	`sit_creatorname` VARCHAR(255) NOT NULL , 
	`sit_creatordate` DATE NOT NULL , 
	`sit_modifiername` VARCHAR(255) NOT NULL , 
	`sit_modifierdate` DATE NOT NULL , 
	PRIMARY KEY (`sit_id`)
) ENGINE = InnoDB;

CREATE TABLE `item_index` (
        `id` INT(11) NOT NULL AUTO_INCREMENT ,
        `itemid` INT(11) NOT NULL ,
        `itemno` VARCHAR(255) NOT NULL ,
        `itemstatus` VARCHAR(255) NOT NULL ,
        PRIMARY KEY (`id`),
        UNIQUE (`itemno`)
) ENGINE = InnoDB;


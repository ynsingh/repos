-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 27, 2019 at 01:52 PM
-- Server version: 10.1.39-MariaDB
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pico`
--

-- --------------------------------------------------------
--
-- Table structure for table `cover_type`
--
CREATE TABLE `cover_type` (
  `ct_id` int(11) NOT NULL,
  `ct_coverno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ct_coverfixed` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ct_cover1` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ct_cover2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ct_cover3` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ct_cover4` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ct_coveroptional` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ct_desc` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Indexes for table `cover_type`
--
ALTER TABLE `cover_type`
  ADD PRIMARY KEY (`ct_id`);
--
-- AUTO_INCREMENT for table `cover_type`
--
ALTER TABLE `cover_type`
  MODIFY `ct_id` int(11) NOT NULL AUTO_INCREMENT AUTO_INCREMENT=5;;

INSERT INTO `cover_type` (`ct_id`, `ct_coverno`, `ct_coverfixed`, `ct_cover1`, `ct_cover2`, `ct_cover3`, `ct_cover4`, `ct_coveroptional`, `ct_desc`) VALUES
(1, 'Single Cover', '100', 'Fee or Prequal or Technical or Financial', '', '', '', '', ''),
(2, 'Two Covers', '100', 'Fee or Prequal or Technical', 'Financial', '', '', '', ''),
(3, 'Three Covers', '100', 'Fee', 'Prequal or Technical', 'Financial', '', '', ''),
(4, 'Four Covers', '100', 'Fee', 'Prequal', 'Technical', 'Financial', '', '');
-- --------------------------------------------------------

--
-- Table structure for table `depart_indent`
--
CREATE TABLE `depart_indent` (
  `di_id` int(11) NOT NULL,
  `di_date` date NOT NULL,
  `di_indenterid` int(100) NOT NULL,
  `di_itemid` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `depart_indent`
	 ADD PRIMARY KEY (`di_id`);
ALTER TABLE `depart_indent`
	MODIFY `di_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `financial_power`
--
CREATE TABLE `financial_power` (
  `fp_id` int(11) NOT NULL,
  `fp_typeofpurch` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `fp_subtypepurch` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `fp_authority` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `fp_limit` int(100) NOT NULL,
  `fp_desc` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `fp_creatorid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fp_creatordate` date NOT NULL,
  `fp_modifierid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fp_modifierdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Indexes for table `financial_power`
--
ALTER TABLE `financial_power`
  ADD PRIMARY KEY (`fp_id`);
--
-- AUTO_INCREMENT for table `financial_power`
--
ALTER TABLE `financial_power`
  MODIFY `fp_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `items`
--
CREATE TABLE `items` (
  `item_id` int(11) NOT NULL,
  `item_mtid` int(15) NOT NULL,
  `item_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `item_price` int(15) NOT NULL,
  `item_qty` int(11) NOT NULL,
  `item_balqty` INT(11) NOT NULL, 
  `item_desc` BLOB NOT NULL,
  `item_pono` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `item_podate` DATE NULL,
  `item_challanno` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `item_challandate` DATE NOT NULL,
  `item_transport` VARCHAR(255) COLLATE utf8_unicode_ci NULL,
  `item_receivername` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `item_creatorname` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `item_creatordate` DATE NOT NULL,
  `item_modifiername` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `item_modifierdate` DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`item_id`);

ALTER TABLE `items`
	MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `items` ADD UNIQUE( `item_mtid`, `item_name`, `item_pono`, `item_challanno`);
-- --------------------------------------------------------

--
-- Table structure for table `material_type`
--
CREATE TABLE `material_type` (
  `mt_id` int(11) NOT NULL,
  `mt_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `mt_desc` varchar(500) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Indexes for table `material_type`
--
ALTER TABLE `material_type`
  ADD PRIMARY KEY (`mt_id`),
  ADD UNIQUE KEY `mt_name` (`mt_name`);
--
-- AUTO_INCREMENT for table `material_type`
--
ALTER TABLE `material_type`
  MODIFY `mt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

-- --------------------------------------------------------

--
-- Dumping data for table `material_type`
--
INSERT INTO `material_type` (`mt_id`, `mt_name`, `mt_desc`) VALUES
(1, 'Non-Consumable Store(NCS)', 'Stores satisfying any one of the\r\nfollowing conditions shall be classified as non-consumable stores :\r\n(a) stores which are intended to be used over prolonged periods before\r\nbecoming unusable, or obsolete,\r\n(b) stores having a significant disposal value,\r\n(c) stores which are sub-systems, or parts of an equipment, which can be\r\npotentially repaired and reused, and\r\n(d) stores which are either fabricated, or assembled equipment, and which\r\nif bought as a single item would have been classified Non-Consumable\r\nStores.\r\nAll non-consumable stores have to be entered into the Asset Register of the\r\nInstitute and the NCS Stock register of the appropriate Department.\r\nExamples :(examples are indicative and not exhaustive) plant machinery,\r\nequipment, fabricated equipment, instruments, assembled instruments,\r\nmotors, gas cylinder, workshop machines, furniture and books etc.'),
(2, 'Limited Time Asset Stores(LTAS)', 'Stores satisfying any one of the\r\nfollowing conditions shall be classified as LTAS.\r\n(a) stores which have significant value when purchased but rapidly lose\r\ntheir value/relevance with the lapse of time and have very little or\r\nnegligible disposal value, and/or\r\n(b) stores which can be upgraded either by replacing components/parts or\r\nwhich can be rendered obsolete by the release of new versions or\r\neditions.\r\nAll LTAS shall be entered into the Limited Time Asset Stores Register of the\r\nInstitute and in a separate Limited Time Asset Stock Register in the\r\nappropriate Department.\r\nExamples: (examples are indicative and not exhaustive) Computers, disk and\r\nother peripherals drives which are computer accessories, software, printers,\r\nmonitors, UPS, telephones, mobile, etc.'),
(3, 'Consumable Stores(CS)', 'Stores satisfying any one of the following\r\nconditions shall be classified as CS:\r\n(a) stores which exhaust with lapse of time,\r\n(b) stores which are rendered unusable due to normal wear and tear,\r\n(c) stores which do not have significant disposal value, and\r\n(d) spares of equipment which do not fall either in the NCS or LTAS\r\ncategory.Examples: (examples are indicative and not exhaustive) chemicals, medicines,\r\nstationery items, printer ribbons and cartridges, pen drive, floppies, CD\r\nROMs, magnetic tapes, chips and electronic components like resistors,\r\ncapacitors, connectors etc, electrical components like wire switches, plugs,\r\nbulbs, cells, tool-bits and hand tools etc.');

-- --------------------------------------------------------

--
-- Table structure for table `offline_instrument`
--
CREATE TABLE `offline_instrument` (
  `oi_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `oi_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `oi_shortname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `oi_desc` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `online_bank`
--
CREATE TABLE `online_bank` (
  `ob_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ob_bankname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ob_shortname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ob_desc` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product_category`
--
CREATE TABLE `product_category` (
  `pc_id` int(11)  NOT NULL,
  `pc_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_shortname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_desc` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `product_category`
 ADD PRIMARY KEY (`pc_id`);

ALTER TABLE `product_category`
	MODIFY `pc_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `purchase_committee`
--
CREATE TABLE `purchase_committee` (
  `pc_id` int(11) NOT NULL,
  `pc_desc` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_dept` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_purchasethrough` VARCHAR(255) NOT NULL,
  `pc_purchpricelimit` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pc_rep1` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pc_rep2` varchar(100) COLLATE utf8_unicode_ci  NULL,
  `pc_rep3` varchar(100) COLLATE utf8_unicode_ci  NULL,
  `pc_rep4` varchar(100) COLLATE utf8_unicode_ci  NULL,
  `pc_rep5` varchar(100) COLLATE utf8_unicode_ci  NULL,
  `pc_convener` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_appauth` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_createdby` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_creationdate` Date NOT NULL, 
  `pc_modifiedby` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_modifieddate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Indexes for table `purchase_committee`
--
ALTER TABLE `purchase_committee`
  ADD PRIMARY KEY (`pc_id`);
--
-- AUTO_INCREMENT for table `purchase_committee`
--
ALTER TABLE `purchase_committee`
  MODIFY `pc_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `purchase_com_form_rule`
--
CREATE TABLE `purchase_com_form_rule` (
  `pcfr_id` int(11) NOT NULL,
  `pcfr_purchasethrough` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pcfr_estpurchaseprice` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pcfr_rep1` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pcfr_rep2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pcfr_rep3` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pcfr_appauth` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pcfr_reference` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pcfr_createrid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pcfr_creatordate` date NOT NULL,
  `pcfr_modifierid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pcfr_modifydate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Indexes for table `purchase_com_form_rule`
--
ALTER TABLE `purchase_com_form_rule`
  ADD PRIMARY KEY (`pcfr_id`);
--
-- AUTO_INCREMENT for table `purchase_com_form_rule`
--
ALTER TABLE `purchase_com_form_rule`
  MODIFY `pcfr_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

-- --------------------------------------------------------
--
-- Dumping data for table `purchase_com_form_rule`
--

INSERT INTO `purchase_com_form_rule` (`pcfr_id`, `pcfr_purchasethrough`, `pcfr_estpurchaseprice`, `pcfr_rep1`, `pcfr_rep2`, `pcfr_rep3`, `pcfr_appauth`, `pcfr_reference`, `pcfr_createrid`, `pcfr_creatordate`, `pcfr_modifierid`, `pcfr_modifydate`) VALUES
(1, 'Purchase Committee', 'Above Rs.50 Lakh', 'Finance Officer', 'Officer-in-charge(S&P)', '', 'Deputy Director/DORD', 'As per DFPR (3.1.1)', 'admin', '2019-06-26', 'admin', '2019-06-27'),
(2, 'Purchase Committee', 'Above Rs.2.5 Lakh Upto Rs.50 Lakh', 'Officer-in Charge (A/c -I or II) or their nominee', 'Officer-in Charge (I.A) or their nominee', 'Officer-in Charge (S&P) or their nominee', 'Deputy Director/DORD', 'As per DFPR (3.1.2)', 'admin', '2019-06-26', 'admin', '2019-06-27'),
(3, 'Purchase Committee', 'Above Rs.25000 Upto Rs.2.5 Lakh', 'Officer-in-charge(S&P) or nominee not below Group \'B\'', 'Technical Expert or his nominee not below Group \'B\'', '', 'Head of Department', 'Mentioned in GFR 2017 Rule 155', 'admin', '2019-06-26', '', '0000-00-00'),
(4, 'GeM', 'Above Rs.50,000.00 Upto Rs.30 Lac', 'S&P Section', '', '', 'Deputy Director/DORD', '', 'admin', '2019-06-26', '', '0000-00-00'),
(5, 'Import', 'US $2000 or equivalent', 'Officer-in-charge(S&P)', '', '', 'Indenter', 'Rule 12.7', 'admin', '2019-06-26', '', '0000-00-00'),
(6, 'Import', 'US $10,000 or equivalent foreign currency', 'Officer-in-charge(S&P)', '', '', 'Head of Department', 'Rule 12.9', 'admin', '2019-06-26', '', '0000-00-00'),
(7, 'Import', 'Above US $10,000 or equivalent foreign currency', 'Officer-in-charge(S&P)', '', '', 'Deputy Director/DORD', 'Rule 13.0', 'admin', '2019-06-26', '', '0000-00-00');

-- --------------------------------------------------------

--
-- Table structure for table `purchase_order`
--
CREATE TABLE `purchase_order` (
  `po_id` int(11) NOT NULL,
  `po_ppid` int(11) NOT NULL,
  `po_tcid` int(11) NOT NULL,
  `po_taid` int(11) NOT NULL,
  `po_no` int(1) NOT NULL,
  `po_requisition` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `po_vendorid` int(11) NOT NULL,
  `po_itemid` int(11) NOT NULL,
  `po_date` date NOT NULL,
  `po_orderqty` int(20) NOT NULL,
  `po_term` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `po_createdby` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `po_creationdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE  `purchase_order`
	ADD PRIMARY KEY (`po_id`);
ALTER TABLE `purchase_order`
	MODIFY `po_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `purchase_proposal`
--
CREATE TABLE `purchase_proposal` (
  `pp_id` int(11) NOT NULL,
  `pp_tcid` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `pp_taid` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `pp_purchasefrom` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `pp_gemrefno` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `pp_tenrefno` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `pp_deptindentno` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `pp_deptid` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `pp_indentername` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pp_indenteremail` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `pp_indentdate` date NOT NULL,
  `pp_indenterid` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `pp_materialtypeid` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `pp_itemtotcost` int(15) NOT NULL,
  `pp_budgetdept` int(15) NOT NULL,
  `pp_budgetprojno` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `pp_budgethead` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pp_budgethead2` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pp_vendorid` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `pp_deliveryperiod` date NOT NULL,
  `pp_deliveryperiodfrom` date NOT NULL,
  `pp_deliveryperiodto` date NOT NULL,
  `pp_warranty` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pp_guarantee` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pp_payterm` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pp_hodapproved` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `pp_hodapproveddate` date NOT NULL,
  `pp_budgetcomment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pp_budgetapproved` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `pp_budgetapprovedby` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pp_budgetapproveddate` date NOT NULL,
  `pp_auditobservation` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pp_auditapproved` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pp_auditapprovedby` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pp_auditapproveddate` date NOT NULL,
  `pp_expsanctionstatus` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pp_expsanctionby` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pp_expsanctiondate` date NOT NULL,
  `pp_dordstatus` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pp_dorddate` date NOT NULL,
  `pp_ddstatus` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pp_dddate` date NOT NULL,
  `pp_dstatus` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pp_ddate` date NOT NULL,
  `pp_reqcreationdate` date NOT NULL,
  `pp_reqcreationby` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pp_updatedate` date NOT NULL,
  `pp_updateby` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `purchase_proposal`
	ADD PRIMARY KEY (`pp_id`);
ALTER TABLE `purchase_proposal`
	MODIFY `pp_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `purchase_type`
--
CREATE TABLE `purchase_type` (
  `pt_id` int(11) NOT NULL,
  `purch_type` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `sub_purch_type` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `amt_above` int(15) NOT NULL,
  `amt_below` int(15) NOT NULL,
  `pt_desc` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Indexes for table `purchase_type`
--
ALTER TABLE `purchase_type`
  ADD PRIMARY KEY (`pt_id`),
  ADD UNIQUE KEY `sub_purch_type` (`sub_purch_type`);
--
-- AUTO_INCREMENT for table `purchase_type`
--
ALTER TABLE `purchase_type`
  MODIFY `pt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Dumping data for table `purchase_type`
--

INSERT INTO `purchase_type` (`pt_id`, `purch_type`, `sub_purch_type`, `amt_above`, `amt_below`, `pt_desc`) VALUES
(1, 'Minor', 'Without', 50000, 150000, 'afdsgg'),
(2, 'Minor', 'Without_Quotation', 50000, 150000, 'dsfg');

-- --------------------------------------------------------

--
-- Table structure for table `required_item_details`
--
CREATE TABLE `required_item_details` (
  `rid_id` int(11) NOT NULL,
  `rid_ppid` int(20) NOT NULL,
  `rid_itemdes` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `rid_itemstock` int(20) NOT NULL,
  `rid_itemqtyreq` int(20) NOT NULL,
  `rid_itemunitp` int(20) NOT NULL,
  `rid_itemgstapply` int(20) NOT NULL,
  `rid_gst` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `rid_itemtotcost` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Indexes for table `required_item_details`
--
ALTER TABLE `required_item_details`
  ADD PRIMARY KEY (`rid_id`);
--
-- AUTO_INCREMENT for table `required_item_details`
--
ALTER TABLE `required_item_details`
  MODIFY `rid_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `tender_apply`
--
CREATE TABLE `tender_apply` (
  `ta_id` int(11) NOT NULL,
  `ta_tcid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_tcrefno` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_vendorid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_baseprice` int(20) NOT NULL,
  `ta_gsttax` int(20) NOT NULL,
  `ta_totalprice` int(20) NOT NULL,
  `ta_warranty` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_payment` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_delivery` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_validity` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_updoc1` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `ta_updoc2` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `ta_updoc3` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `ta_updoc4` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `ta_updoc5` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `ta_status` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_approvedby` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_approvedbydate` DATE NOT NULL ,
  `ta_approvedbydesg` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_creatorid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_creationdate` date NOT NULL,
  `ta_modifierid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_modifierdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Indexes for table `tender_apply`
--
ALTER TABLE `tender_apply`
  ADD PRIMARY KEY (`ta_id`);
--
-- AUTO_INCREMENT for table `tender_apply`
--
ALTER TABLE `tender_apply`
  MODIFY `ta_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------
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
  MODIFY `archive_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------
--
-- Table structure for table `tender_bid_openers_selection`
--
CREATE TABLE `tender_bid_openers_selection` (
  `tbos_id` int(11) NOT NULL,
  `tbos_tcid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tbos_bono` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tbos_boname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tbos_bodesig` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tbos_boemail` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tbos_creatorid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tbos_creationdate` date NOT NULL,
  `tbos_modifierid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tbos_modifierdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Indexes for table `tender_bid_openers_selection`
--
ALTER TABLE `tender_bid_openers_selection`
  ADD PRIMARY KEY (`tbos_id`);
--
-- AUTO_INCREMENT for table `tender_bid_openers_selection`
--
ALTER TABLE `tender_bid_openers_selection`
  MODIFY `tbos_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `tender_create`
--
CREATE TABLE `tender_create` (
  `tc_id` int(11) NOT NULL,
  `tc_refno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_tentype` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_contractform` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_coverid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_category` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_allowresub` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_allowwithdra` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_allowoffline` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_paymode` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_offlineinstrumentid` varchar(255) COLLATE utf8_unicode_ci NULL,
  `tc_onlinebankid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_covercontent` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_nitdocfilename` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_nitdocfilesize` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_nitdoctype` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_workitemtitle` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_workdesc` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_prequaldetails` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_prodcatid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_prodsubcat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_contracttype` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_tendervalue` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_bidvaliddays` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_completionm` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_location` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_pincode` int(6) NOT NULL,
  `tc_prebidmeeting` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_prebidmeetplace` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_prebidmeetadd` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_bidopenplace` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_tenderclass` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_tendersubclass` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_invitngofficer` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_invitngoffadd` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_invitngoffemail` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tc_invitngoffphone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_tenderfees` int(15) NOT NULL,
  `tc_tenderfeespayble` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_tenderfeespaybleat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_processingfees` int(15) NOT NULL,
  `tc_surcharge` int(15) NOT NULL,
  `tc_othercharge` int(15) NOT NULL,
  `tc_emdfeesmode` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_emdamount` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_emdpercentage` int(15) NOT NULL,
  `tc_emdexemption` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_emdexemptionper` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_publishingdate` date NOT NULL,
  `tc_publishingdatet` time NOT NULL,
  `tc_docsalestartdate` date NOT NULL,
  `tc_docsalestartdatet` time NOT NULL,
  `tc_docsaleenddate` date NOT NULL,
  `tc_docsaleenddatet` time NOT NULL,
  `tc_seekclailstartdate` date NOT NULL,
  `tc_seekclailstartdatet` time NOT NULL,
  `tc_seekclailenddate` date NOT NULL,
  `tc_seekclailenddatet` time NOT NULL,
  `tc_prebidmeetingdate` date NOT NULL,
  `tc_prebidmeetingdatet` time NOT NULL,
  `tc_bidsubstartdate` date NOT NULL,
  `tc_bidsubstartdatet` time NOT NULL,
  `tc_bidsubenddate` date NOT NULL,
  `tc_bidsubenddatet` time NOT NULL,
  `tc_bidopeningdate` date NOT NULL,
  `tc_bidopeningdatet` time NOT NULL,
  `tc_tenderprepby` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_prepbydesig` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_prepbydate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tc_approvedstatus` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tc_approvedbyname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_approvedbydesig` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_approvedbydate` datetime NOT NULL,
  `tc_creatorid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_creationdate` date NOT NULL,
  `tc_modifierid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_modifierdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Indexes for table `tender_create`
--
ALTER TABLE `tender_create`
  ADD PRIMARY KEY (`tc_id`);
--
-- AUTO_INCREMENT for table `tender_create`
--
ALTER TABLE `tender_create`
  MODIFY `tc_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

CREATE TABLE `tender_create_archive` (
  `archive_id` int(11) NOT NULL,
  `tc_id` int(11) NOT NULL,
  `tc_refno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_approvedstatus` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_byname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_bydesig` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `archive_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Indexes for table `tender_create_archive`
--
ALTER TABLE `tender_create_archive`
  ADD PRIMARY KEY (`archive_id`);
--
-- AUTO_INCREMENT for table `tender_create_archive`
--
ALTER TABLE `tender_create_archive`
  MODIFY `archive_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `tender_query`
--
CREATE TABLE `tender_query` (
  `tq_id` int(11) NOT NULL,
  `tq_email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tq_subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tq_desc` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `tq_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Indexes for table `tender_query`
--
ALTER TABLE `tender_query`
  ADD PRIMARY KEY (`tq_id`);
--
-- AUTO_INCREMENT for table `tender_query`
--
ALTER TABLE `tender_query`
  MODIFY `tq_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `tender_type`
--
CREATE TABLE `tender_type` (
  `tt_id` int(11) NOT NULL,
  `tt_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tt_desc` varchar(5000) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Indexes for table `tender_type`
--
ALTER TABLE `tender_type`
  ADD PRIMARY KEY (`tt_id`);
--
-- AUTO_INCREMENT for table `tender_type`
--
ALTER TABLE `tender_type`
  MODIFY `tt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- Dumping data for table `tender_type`
--

INSERT INTO `tender_type` (`tt_id`, `tt_name`, `tt_desc`) VALUES
(1, 'Single Bid System', 'This mode of enquiry can be used where the nature of item to be procured is not technical in nature and the item is a standardised one, value of the item is not much, and no eligibility condition is required to be met by the vendors. In this case, the vendors will be directly quoting the rates with complete specifications.'),
(2, 'Two Bid system', 'For purchasing capital equipment, high value plant, machinery etc. of complex and technical nature, bids may be invited in two parts. Tender enquiry document, complete in all respects, may be issued. However, the tenderers should be asked to bifurcate their quotation in two parts as under: \r\n\r\na) First Part- Technical Bid consisting of Tender fee, Earnest Money Deposit and all technical details along with commercial terms and conditions as required in the tender enquiry documents. \r\n\r\nb) Second Part- Financial Bid indicating item wise price of the items mentioned in the Technical Bid. \r\n\r\nNote: Financial bids should be obtained in the prescribed format only provided to the bidders as part of the tender document.\r\n\r\n The technical bid and the financial bid should be sealed by the bidder in separate envelopes duly superscribed and both these sealed envelopes are to be put in a bigger cover which should also be sealed and superscribed. The technical bids are to be opened in the first instance, at the prescribed date and time. The same will be scrutinized and evaluated by the Technical Evaluation Committee (TEC) with reference to the parameters prescribed in the tender documents and the offer(s) received from the Tenderers. Technical comparative charts shall be prepared by the concerned indenter(s)/ purchaser(s). Thereafter, in the second stage, the financial bids of only technically qualified parties (as decided in the first stage above) are to be opened for further scrutiny, evaluation, ranking and placement/ award of order/ contract. Tenders without Tender fee / EMD shall not be opened.'),
(3, 'Single Tender Quotation', 'Procurement of goods on single quotation basis can be followed in the following circumstances:\r\n\r\n(i) It is in the knowledge of the user department/Centre/School that only a particular firm is the manufacturer of the required goods or proprietary item.\r\n \r\n(ii)In case of emergency, the required goods are necessarily to be purchased from a particular source. The reasons for such decisions to be recorded and prior approval of the competent authority be obtained before effecting the purchase.\r\n\r\n(iii)In case, the purchaser recommends the purchase from a specific vendor on the basis of his/her experience/usage and requirement for a specific research purpose that cannot be made with alternate make/model of the equipment.  \r\n\r\n(iv)For standardization of machinery or spare parts to be compatible with the existing sets of equipment, the required item is to be purchased only from a selected firm.\r\n \r\n(v)The indenter should provide a certificate that the price quoted by the firm is reasonable and the same is a proprietary item.\r\n\r\nPurchase of items of a proprietary nature (i.e. item which do not have substitutes, or are spare parts of existing equipment for which substitute replacements are not available) can be done on the basis of a single quotation irrespective of the value of the item. In such cases, the purchaser must furnish a proprietary certificate (as placed at Annexure-I) countersigned by the Head of the concerned or associated department/centre/school. If the total value of the item is more than Rs. 1.00 lac, a proprietary certificate must be obtained from the supplier stating that they are the only source of supply /manufacture.'),
(4, 'Repeat Order Timesheet 180 Days', 'In exceptional circumstances where the requirement of the purchaser is the same as to the specifications and quantity etc. of an already issued P.O., with the prior permission of the CFA, purchase can be processed on repeat order basis subject to the following conditions:\r\n  \r\n\r\n\r\n\r\n\r\n\r\n(i)Proper purchase procedure (Invitation of quotations by limited/Advertised tender enquiry) has been followed for the original order.\r\n\r\n(ii)There cannot be more than two repeat orders.\r\n\r\n(iii)The repeat order can be made without any change in the rates, specifications,\r\nquantity as well as terms & conditions of supply in each case. The quantities of the repeat orders can be lesser but in no case it can exceed the original order.\r\n\r\n(iv)The repeat order can be placed within 180 days from the date of the issue of original\r\norder only after ensuring from the vendor that there is no reduction in the price of the\r\nitem(s) and the vendor is ready to supply the material without any increase in the rates.\r\n\r\n(v)No repeat order will be placed if buy-back is involved in the purchase.\r\n(vi)Efforts to be made to obtained rebate (quantity discount) from the supplier on the previously ordered prices.'),
(5, 'Rate Contract with Institute Director', 'A Rate Contract (commonly known as RC) is an agreement between the purchaser and the suppliers for supply of specific goods and allied services, if any, at specified price and terms and conditions during the period covered by the Rate Contract. Rate contracts with the registered suppliers, for goods and items of standard types which are identified as common user items and are needed on recurring basis by various Departments/Centre/Schools will be entered by SPS. The discounts, delivery period and other terms & conditions shall be negotiated by a committee constituted by the Director. The recommendations of the committee shall be approved by the Director before they are formally adopted. The period of validity of the Rate contract shall be counted from the date, the rate contract is formally adopted. The process for entering into rate contract arrangements shall be initiated by SPS.'),
(6, 'DGS And D Rate Contracts', 'Director General Supplies and Disposals (DGS&D), New Delhi, conclude the rate contracts\r\nof various items of standard types which are identified as common user items and needed\r\non recurring basis by various Central Government offices/ Departments.\r\nAny item of any value can be purchased on the basis of DGS& D rate contracts directly from\r\nthe manufacturers/ their authorised dealers/ agents. DGS&D Rate contract will not be a\r\nbinding as long as other purchase process is followed.\r\nIn case of items for which DGS&D Rate Contract exist, the SPO will procure these items by\r\nplacing direct orders on the firms as per recommendations of the indenter.'),
(7, 'Purchase under buy back scheme', 'When it is decided with the approval of the CFA to replace an existing old item with a new\r\nand better version, the institute may trade the existing old item while purchasing the new\r\none. For this purpose, a suitable clause is to be incorporated in the bidding document so that\r\nprospective and interested bidders formulate their bids accordingly. Depending upon the\r\nvalue and condition of the old item to be traded, the time as well as the mode of handing\r\nover the old item to the successful bidder should be decided and relevant details in this\r\nregard suitably incorporated in the bidding document. Further, a suitable provision should\r\nalso be kept in the bidding document to enable the purchaser either to trade or not to trade\r\nthe old item while purchasing the new one. If any item is purchased under buy back scheme\r\nfor old one, quotations are to be invited clearly mentioning the specifications of old and new\r\nitem asking for the buyback offer from the vendor. A condemnation report is to be filled up in\r\nGFR-17 form (Annexure-II) for old item, signed by all members of condemnation / write-off\r\ncommittee, approved by the Director and finally to be sent to SPS along with purchase\r\nindent.'),
(8, 'Upgradation', 'In case of Upgradation of assets, the old asset which has been upgraded will be treated as written-off i.e., its value will be written-off from the records. The upgraded new asset will be entered in the records and the sum of cost of old asset and the additional cost paid, will be considered as cost of the upgraded asset.');

-- --------------------------------------------------------

--
-- Table structure for table `tender_upload_doc`
--
CREATE TABLE `tender_upload_doc` (
  `tud_id` int(11) NOT NULL,
  `tud_tcid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tud_filename` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tud_desc` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tud_filetype` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tud_filesize` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tud_creatorid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tud_creationdate` date NOT NULL,
  `tud_modifierid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tud_modifierdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Indexes for table `tender_upload_doc`
--
ALTER TABLE `tender_upload_doc`
  ADD PRIMARY KEY (`tud_id`);
--
-- AUTO_INCREMENT for table `tender_upload_doc`
--
ALTER TABLE `tender_upload_doc`
  MODIFY `tud_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--
CREATE TABLE `vendor` (
  `vendor_id` int(11) NOT NULL,
  `vendor_userid` INT(11) NULL,
  `vendor_companyname` varchar(500) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_name` varchar(100) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_address` varchar(1000) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_pincode` int(6) NOT NULL,
  `vendor_hqaddress` varchar(1000) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_hqpincode` int(6) NOT NULL,
  `vendor_email` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_website` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_contact_person_name` varchar(100) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_phone` int(10) NOT NULL,
  `vendor_mobile` int(10) NOT NULL,
  `vendor_fax` int(10) NOT NULL,
  `vendor_city` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_state` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_gstno` varchar(15) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_pan` varchar(10) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_shop_act_registration_no` varchar(20) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_excise_registration_no` varchar(20) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_bank_account_no` varchar(18) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_type` varchar(100) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_pre_order` longtext COLLATE utf32_unicode_ci NOT NULL,
  `vendor_item_supply` varchar(500) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_blackliststatus` varchar(100) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_blacklistdatefrom` date DEFAULT NULL,
  `vendor_blacklistdateto` date DEFAULT NULL,
  `vendor_blacklistby` varchar(100) COLLATE utf32_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;
--
-- Indexes for table `vendor`
--
ALTER TABLE `vendor`
  ADD PRIMARY KEY (`vendor_id`);
--
-- AUTO_INCREMENT for table `vendor`
--
ALTER TABLE `vendor`
  MODIFY `vendor_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `vendor__archive`
--
CREATE TABLE `vendor_archive` (
  `archive_id` int(11) NOT NULL,
  `vendor_archive_id` int(11) NOT NULL,
  `vendor_archive_userid` INT(11) NULL,
  `vendor_archive_companyname` varchar(500) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_name` varchar(100) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_address` varchar(1000) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_pincode` int(6) NOT NULL,
  `vendor_archive_hqaddress` varchar(1000) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_hqpincode` int(6) NOT NULL,
  `vendor_archive_email` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_website` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_contact_person_name` varchar(100) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_phone` int(10) NOT NULL,
  `vendor_archive_mobile` int(10) NOT NULL,
  `vendor_archive_fax` int(10) NOT NULL,
  `vendor_archive_city` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_state` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_gstno` varchar(15) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_pan` varchar(10) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_shop_act_registration_no` varchar(20) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_excise_registration_no` varchar(20) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_bank_account_no` varchar(18) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_type` varchar(100) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_pre_order` longtext COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_item_supply` varchar(500) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_blackliststatus` varchar(100) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_blacklistdatefrom` date DEFAULT NULL,
  `vendor_archive_blacklistdateto` date DEFAULT NULL,
  `vendor_archive_blacklistby` varchar(100) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_updatedby` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_archive_updatedate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

ALTER TABLE `vendor_archive`
  ADD PRIMARY KEY (`archive_id`);
  
ALTER TABLE `vendor_archive` CHANGE `archive_id` `archive_id` INT(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `logs`
--
CREATE TABLE `logs` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `level` int(1) NOT NULL,
  `host_ip` varchar(25) NOT NULL,
  `user` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `user_agent` varchar(100) NOT NULL,
  `message_title` varchar(255) NOT NULL,
  `message_desc` mediumtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `logs`
  ADD PRIMARY KEY (`id`);
  
ALTER TABLE `logs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------

--
-- Table structure for table `user_role_type`
--

CREATE TABLE `user_role_type` (
  `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `scid` int(10) DEFAULT NULL,
  `deptid` int(10) DEFAULT NULL,
  `usertype` varchar(255) NOT NULL,
  `ext1` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into user_role_type values (1,1,1,1,NULL,'Administrator','');
insert into user_role_type values (2,6,11,NULL,NULL,'PICOAdministrator','');

-- --------------------------------------------------------
--
-- Table structure for table `user_role_type_archive`
--

CREATE TABLE `user_role_type_archive` (
  `urta_id` INT(11) NOT NULL,
  `urta_urtid` INT(11) NOT NULL,
  `urta_userid` int(11) NOT NULL,
  `urta_roleida` int(11) NOT NULL,
  `urta_scida` int(10) DEFAULT NULL,
  `urta_deptida` int(10) DEFAULT NULL,
  `urta_usertypea` varchar(255) NOT NULL,
  `creatorid` varchar(255) NOT NULL,
  `creatordate` date NOT NULL,
  `ext1` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `user_role_type_archive`
  ADD PRIMARY KEY (`urta_id`);

ALTER TABLE `user_role_type_archive`
  MODIFY `urta_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------
--
-- Table structure for table `stock_items`
--

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
-- --------------------------------------------------------
--
-- Table structure for table `stock_items_archive`
--
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

-- --------------------------------------------------------
--
-- Table structure for table `items_issued`
--

CREATE TABLE `items_issued` (
  `ii_id` int(11) NOT NULL,
  `ii_itemid` int(11) NOT NULL,
  `ii_mtid` int(15) NOT NULL,
  `ii_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ii_qty` int(11) NOT NULL,
  `ii_irqty` INT(11) NOT NULL DEFAULT '0',
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

-- --------------------------------------------------------
--
-- Table structure for table `stock_items_issued`
--

CREATE TABLE `stock_items_issued` (
  `sii_id` int(11) NOT NULL,
  `sii_mtid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sii_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sii_qty` int(5) NOT NULL,
  `sii_desc` blob NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `stock_items_issued`
  ADD PRIMARY KEY (`sii_id`),
  ADD UNIQUE KEY `sii_mtid` (`sii_mtid`,`sii_name`);
ALTER TABLE `stock_items_issued`
  MODIFY `sii_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------
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

-- --------------------------------------------------------
--
-- Table structure for table `items_return`
--

CREATE TABLE `items_return` (
  `ir_id` int(11) NOT NULL,
  `ir_iiid` INT(11) NOT NULL,
  `ir_itemid` int(11) NOT NULL,
  `ir_mtid` int(15) NOT NULL,
  `ir_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ir_qty` int(11) NOT NULL,
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

-- --------------------------------------------------------
--
-- Table structure for table `inspection_report`
--
CREATE TABLE `inspection_report` (
  `inr_id` int(11) NOT NULL,
  `inr_itemid` int(11) NOT NULL,
  `inr_no` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inr_pono` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inr_podate` date NOT NULL,
  `inr_challanno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inr_challandate` date NOT NULL,
  `inr_nodate` date NOT NULL,
  `inr_indentno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inr_indentname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inr_indentdept` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inr_suppliername` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inr_itemname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inr_itemqty` int(11) NOT NULL,
  `inr_qtyapprov` int(11) NOT NULL,
  `inr_qtyrej` int(11) NOT NULL DEFAULT '0',
  `inr_reasonforrej` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `inr_replacement` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `inr_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `inr_irstatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `inr_creatorname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inr_creatordate` date NOT NULL,
  `inr_modifiername` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `inr_modifierdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `inspection_report`
  ADD PRIMARY KEY (`inr_id`);
ALTER TABLE `inspection_report`
  MODIFY `inr_id` int(11) NOT NULL AUTO_INCREMENT;
-- --------------------------------------------------------
--
-- Table structure for table `payment_order`
--

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
-- --------------------------------------------------------
--
-- Table structure for table `stock item transfer`
--
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
-- --------------------------------------------------------
--
-- Table structure for table `item index`
--
CREATE TABLE `item_index` ( 
	`id` INT(11) NOT NULL AUTO_INCREMENT , 
	`itemid` INT(11) NOT NULL , 
	`itemno` VARCHAR(255) NOT NULL , 
	`itemstatus` VARCHAR(255) NOT NULL , 
	PRIMARY KEY (`id`), 
	UNIQUE (`itemno`)
) ENGINE = InnoDB;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

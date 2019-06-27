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
  `ct_id` int(20) NOT NULL,
  `ct_coverno` int(20) NOT NULL,
  `ct_coverfixed` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ct_coveroptional` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ct_desc` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cover_type`
--


-- --------------------------------------------------------

--
-- Table structure for table `depart_indent`
--

CREATE TABLE `depart_indent` (
  `di_id` int(100) NOT NULL,
  `di_date` date NOT NULL,
  `di_indenterid` int(100) NOT NULL,
  `di_itemid` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  `fp_creatorid` int(100) NOT NULL,
  `fp_creatordate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fp_modifierid` int(100) NOT NULL,
  `fp_modifierdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `financial_power`
--


-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `item_id` int(15) NOT NULL,
  `item_mtid` int(15) NOT NULL,
  `item_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `item_price` int(15) NOT NULL,
  `item_stock` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `items`
--



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
-- Dumping data for table `material_type`
--

INSERT INTO `material_type` (`mt_id`, `mt_name`, `mt_desc`) VALUES
(1, 'Limited Time Asset Stores(LTAS)', 'sdafafasfsaasdsaf'),
(2, 'Non-Consumable Store(NCS)', 'sdafafasfsaasdsaf'),
(3, 'Consumable Stores(CS)', 'sdafafasfsaasdsaf');

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
  `pc_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_shortname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_desc` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `purchase_committee`
--

CREATE TABLE `purchase_committee` (
  `pc_id` int(15) NOT NULL,
  `pc_desc` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_dept` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_purchpricelimit` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pc_rep1` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pc_rep2` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pc_rep3` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pc_rep4` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pc_rep5` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pc_convener` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_appauth` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_createdby` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_creation date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `pc_modifiedby` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pc_modifieddate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  `po_id` int(20) NOT NULL,
  `po_ppid` int(20) NOT NULL,
  `po_tcid` int(20) NOT NULL,
  `po_taid` int(20) NOT NULL,
  `po_no` int(20) NOT NULL,
  `po_requisition` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `po_vendorid` int(20) NOT NULL,
  `po_itemid` int(20) NOT NULL,
  `po_date` date NOT NULL,
  `po_orderqty` int(20) NOT NULL,
  `po_term` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `po_createdby` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `po_creationdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `purchase_proposal`
--

CREATE TABLE `purchase_proposal` (
  `pp_id` int(20) NOT NULL,
  `pp_tcid` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `pp_taid` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `pp_gemrefno` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
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
  `rid_id` int(20) NOT NULL,
  `rid_ppid` int(20) NOT NULL,
  `rid_itemdes` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `rid_itemstock` int(20) NOT NULL,
  `rid_itemqtyreq` int(20) NOT NULL,
  `rid_itemunitp` int(20) NOT NULL,
  `rid_itemgstapply` int(20) NOT NULL,
  `rid_gst` int(20) NOT NULL,
  `rid_itemtotcost` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tender_apply`
--

CREATE TABLE `tender_apply` (
  `ta_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_tcid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_vendorid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_baseprice` int(20) NOT NULL,
  `ta_gsttax` int(20) NOT NULL,
  `ta_totalprice` int(20) NOT NULL,
  `ta_updoc1` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `ta_updoc2` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `ta_updoc3` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `ta_updoc4` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `ta_updoc5` varchar(400) COLLATE utf8_unicode_ci NOT NULL,
  `ta_creatorid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_creationdate` date NOT NULL,
  `ta_modifierid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ta_modifierdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tender_bid_openers_selection`
--

CREATE TABLE `tender_bid_openers_selection` (
  `tbos_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
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

-- --------------------------------------------------------

--
-- Table structure for table `tender_create`
--

CREATE TABLE `tender_create` (
  `tc_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_refno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_tentype` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_contractform` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_coverid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_category` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_allowresub` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_allowithdra` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_allowoffline` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_paymode` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_offlineinstrumentid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
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
  `tc_tenderfeespayble` int(15) NOT NULL,
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
  `tc_docsalestartdate` date NOT NULL,
  `tc_docsaleenddate` date NOT NULL,
  `tc_seekclailstartdate` date NOT NULL,
  `tc_seekclailenddate` date NOT NULL,
  `tc_prebidmeetingdate` date NOT NULL,
  `tc_bidsubstartdate` date NOT NULL,
  `tc_bidsubenddate` date NOT NULL,
  `tc_bidopeningdate` date NOT NULL,
  `tc_tenderprepby` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_prepbydesig` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_prepbydate` date NOT NULL,
  `tc_approvedbyname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_approvedbydesig` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_approvedbydate` date NOT NULL,
  `tc_creatorid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_creationdate` date NOT NULL,
  `tc_modifierid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_modifierdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tender_upload_doc`
--

CREATE TABLE `tender_upload_doc` (
  `tud_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
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

-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--

CREATE TABLE `vendor` (
  `vendor_id` int(100) NOT NULL,
  `vendor_companyname` varchar(500) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_address` varchar(1000) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_city` varchar(100) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_pincode` int(6) NOT NULL,
  `vendor_ phone` int(10) NOT NULL,
  `vendor_type` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_blackliststatus` varchar(255) COLLATE utf32_unicode_ci NOT NULL,
  `vendor_blacklistdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cover_type`
--
ALTER TABLE `cover_type`
  ADD PRIMARY KEY (`ct_id`);

--
-- Indexes for table `financial_power`
--
ALTER TABLE `financial_power`
  ADD PRIMARY KEY (`fp_id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `material_type`
--
ALTER TABLE `material_type`
  ADD PRIMARY KEY (`mt_id`),
  ADD UNIQUE KEY `mt_name` (`mt_name`);

--
-- Indexes for table `purchase_committee`
--
ALTER TABLE `purchase_committee`
  ADD PRIMARY KEY (`pc_id`);

--
-- Indexes for table `purchase_com_form_rule`
--
ALTER TABLE `purchase_com_form_rule`
  ADD PRIMARY KEY (`pcfr_id`);

--
-- Indexes for table `purchase_type`
--
ALTER TABLE `purchase_type`
  ADD PRIMARY KEY (`pt_id`),
  ADD UNIQUE KEY `sub_purch_type` (`sub_purch_type`);

--
-- Indexes for table `required_item_details`
--
ALTER TABLE `required_item_details`
  ADD PRIMARY KEY (`rid_id`);

--
-- Indexes for table `vendor`
--
ALTER TABLE `vendor`
  ADD PRIMARY KEY (`vendor_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cover_type`
--
ALTER TABLE `cover_type`
  MODIFY `ct_id` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `financial_power`
--
ALTER TABLE `financial_power`
  MODIFY `fp_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `material_type`
--
ALTER TABLE `material_type`
  MODIFY `mt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `purchase_committee`
--
ALTER TABLE `purchase_committee`
  MODIFY `pc_id` int(15) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `purchase_com_form_rule`
--
ALTER TABLE `purchase_com_form_rule`
  MODIFY `pcfr_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `purchase_type`
--
ALTER TABLE `purchase_type`
  MODIFY `pt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `required_item_details`
--
ALTER TABLE `required_item_details`
  MODIFY `rid_id` int(20) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

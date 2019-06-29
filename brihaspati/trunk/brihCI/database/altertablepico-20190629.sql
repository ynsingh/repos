
CREATE TABLE `required_item_details` (
  `rid_id` int(20) NOT NULL,
  `rid_ppid` int(20) NOT NULL,
  `rid_itemdes` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `rid_itemstock` int(20) NOT NULL,
  `rid_itemqtyreq` int(20) NOT NULL,
  `rid_itemunitp` int(20) NOT NULL,
  `rid_itemgstapply` int(20) NOT NULL,
  `rid_gst` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `rid_itemtotcost` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tender_apply`
--

CREATE TABLE `tender_apply` (
  `ta_id` int(11) NOT NULL,
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

-- --------------------------------------------------------

--
-- Table structure for table `tender_create`
--

CREATE TABLE `tender_create` (
  `tc_id` int(255) NOT NULL,
  `tc_refno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_tentype` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_contractform` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_coverid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_category` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_allowresub` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_allowwithdra` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
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
  `tc_prepbydate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tc_approvedbyname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_approvedbydesig` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_approvedbydate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tc_creatorid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_creationdate` date NOT NULL,
  `tc_modifierid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_modifierdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tender_type`
--

CREATE TABLE `tender_type` (
  `tt_id` int(11) NOT NULL,
  `tt_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tt_desc` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tender_type`
--

INSERT INTO `tender_type` (`tt_id`, `tt_name`, `tt_desc`) VALUES
(1, 'single bidd', 'a'),
(2, 'double bid with emmd', 'www'),
(3, 'single tender quotation', 'a'),
(4, 'repeat order timesheet 180', 'aaa'),
(5, 'on rate control with d', 'aaa'),
(6, 'on rate control with dgs and d', 'aaaa'),
(7, 'purchase under buyback scheme', 'aaaaa');


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
-- Indexes for table `tender_apply`
--
ALTER TABLE `tender_apply`
  ADD PRIMARY KEY (`ta_id`);

--
-- Indexes for table `tender_bid_openers_selection`
--
ALTER TABLE `tender_bid_openers_selection`
  ADD PRIMARY KEY (`tbos_id`);

--
-- Indexes for table `tender_create`
--
ALTER TABLE `tender_create`
  ADD PRIMARY KEY (`tc_id`);

--
-- Indexes for table `tender_type`
--
ALTER TABLE `tender_type`
  ADD PRIMARY KEY (`tt_id`);

--
-- Indexes for table `tender_upload_doc`
--
ALTER TABLE `tender_upload_doc`
  ADD PRIMARY KEY (`tud_id`);


--
-- AUTO_INCREMENT for table `tender_apply`
--
ALTER TABLE `tender_apply`
  MODIFY `ta_id` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tender_bid_openers_selection`
--
ALTER TABLE `tender_bid_openers_selection`
  MODIFY `tbos_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tender_create`
--
ALTER TABLE `tender_create`
  MODIFY `tc_id` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tender_type`
--
ALTER TABLE `tender_type`
  MODIFY `tt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tender_upload_doc`
--
ALTER TABLE `tender_upload_doc`
  MODIFY `tud_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vendor`
--
ALTER TABLE `vendor`
  MODIFY `vendor_id` int(100) NOT NULL AUTO_INCREMENT;




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

--
-- Indexes for table `inspection_report`
--
ALTER TABLE `inspection_report`
  ADD PRIMARY KEY (`inr_id`);

--
-- AUTO_INCREMENT for table `inspection_report`
--
ALTER TABLE `inspection_report`
  MODIFY `inr_id` int(11) NOT NULL AUTO_INCREMENT;

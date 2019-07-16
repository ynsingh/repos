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

CREATE TABLE `vendor_archive` (
  `archive_id` int(11) NOT NULL,
  `vendor_archive_id` int(11) NOT NULL,
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



ALTER TABLE `tender_create` ADD `tc_publishingdatet` DATE NOT NULL AFTER `tc_publishingdate`; 

ALTER TABLE `tender_create` CHANGE `tc_publishingdatet` `tc_publishingdatet` TIME NOT NULL;

ALTER TABLE `tender_create` ADD `tc_docsalestartdatet` TIME NOT NULL AFTER `tc_docsalestartdate`;

ALTER TABLE `tender_create` ADD `tc_docsaleenddatet` TIME NOT NULL AFTER `tc_docsaleenddate`;

ALTER TABLE `tender_create` ADD `tc_seekclailstartdatet` TIME NOT NULL AFTER `tc_seekclailstartdate`;

ALTER TABLE `tender_create` ADD `tc_seekclailenddatet` TIME NOT NULL AFTER `tc_seekclailenddate`;

ALTER TABLE `tender_create` ADD `tc_prebidmeetingdatet` TIME NOT NULL AFTER `tc_prebidmeetingdate`;

ALTER TABLE `tender_create` ADD `tc_bidsubstartdatet` TIME NOT NULL AFTER `tc_bidsubstartdate`;

ALTER TABLE `tender_create` ADD `tc_bidsubenddatet` TIME NOT NULL AFTER `tc_bidsubenddate`;

ALTER TABLE `tender_create` ADD `tc_bidopeningdatet` TIME NOT NULL AFTER `tc_bidopeningdate`;

ALTER TABLE `tender_create` CHANGE `tc_approvedbydate` `tc_approvedbydate` DATETIME NOT NULL;

ALTER TABLE `tender_create` ADD `tc_approvedstatus` VARCHAR(100) NOT NULL AFTER `tc_prepbydate`;

CREATE TABLE `tender_create_archive` (
  `archive_id` int(11) NOT NULL AUTO_INCREMENT,
  `tc_id` int(11) NOT NULL,
  `tc_refno` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_approvedstatus` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_byname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tc_bydesig` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `archive_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `tender_create_archive`
  ADD PRIMARY KEY (`archive_id`);

CREATE TABLE `tender_query` (
  `tq_id` int(11) NOT NULL,
  `tq_email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tq_subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tq_desc` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `tq_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `tender_query`
  ADD PRIMARY KEY (`tq_id`);

ALTER TABLE `tender_query`
  MODIFY `tq_id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `tender_query` CHANGE `tq_time` `tq_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

drop table `tender_type`;
CREATE TABLE `tender_type` (
  `tt_id` int(11) NOT NULL,
  `tt_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tt_desc` varchar(5000) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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

ALTER TABLE `tender_type`
  ADD PRIMARY KEY (`tt_id`);
ALTER TABLE `tender_type`
  MODIFY `tt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

COMMIT;


 

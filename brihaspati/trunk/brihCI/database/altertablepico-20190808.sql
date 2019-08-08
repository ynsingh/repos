ALTER TABLE `tender_create` CHANGE `tc_offlineinstrumentid` `tc_offlineinstrumentid` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL;
INSERT INTO `cover_type` (`ct_id`, `ct_coverno`, `ct_coverfixed`, `ct_cover1`, `ct_cover2`, `ct_cover3`, `ct_cover4`, `ct_coveroptional`, `ct_desc`) VALUES
(1, 'Single Cover', '100', 'Fee or Prequal or Technical or Financial', '', '', '', '', ''),
(2, 'Two Covers', '100', 'Fee or Prequal or Technical', 'Financial', '', '', '', ''),
(3, 'Three Covers', '100', 'Fee', 'Prequal or Technical', 'Financial', '', '', ''),
(4, 'Four Covers', '100', 'Fee', 'Prequal', 'Technical', 'Financial', '', '');

use olas;

ALTER TABLE `semester_rule`
  ADD PRIMARY KEY (`semcr_id`);

ALTER TABLE `semester_rule`
  MODIFY `semcr_id` int(11) NOT NULL AUTO_INCREMENT;


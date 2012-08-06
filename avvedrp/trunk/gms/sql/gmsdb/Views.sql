/*View structure for view `project_details_view` */

DELIMITER $$

DROP VIEW IF EXISTS `project_details_view`$$

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `project_details_view` AS 
SELECT
  `p`.`name` AS `ProjectName`,
  `pa`.`id`  AS `Party`,
  (SELECT
     `pr`.`name_of_the_institution` AS `name_of_the_institution`
   FROM `party` `pr`
   WHERE (`pr`.`id` = `ga`.`granter_id`)) AS `Granter`,
  SUM(`ga`.`amount_allocated`) AS `AmountAllocated`,
  (SELECT
     SUM(`ge`.`expense_amount`) AS `ExpenseAmount`
   FROM `grant_expense` `ge`
   WHERE ((`ge`.`grant_allocation_id` = `ga`.`id`)
          AND (`ge`.`projects_id` = `ga`.`projects_id`))
   GROUP BY `ga`.`id`) AS `ExpenseAmt`,
  (SELECT
     SUM(`gr`.`amount`) AS `RecievedAmount`
   FROM `grant_receipt` `gr`
   WHERE ((`gr`.`grant_allocation_id` = `ga`.`id`)
          AND (`gr`.`projects_id` = `ga`.`projects_id`))
   GROUP BY `ga`.`id`) AS `RecievedAmount`,
  (SELECT
     SUM(`ft`.`amount`) AS `FundTransfer`
   FROM ((`fund_transfer` `ft`
       JOIN `grant_allocation` `g`)
      JOIN `projects` `pr`)
   WHERE ((`ft`.`grant_allocation_id` = `g`.`id`)
          AND (`g`.`projects_id` = `pr`.`id`)
          AND (`pr`.`parent_id` = `p`.`id`))
   GROUP BY `ga`.`id`) AS `FundTransferAmount`
FROM ((`party` `pa`
    JOIN `projects` `p`)
   LEFT JOIN `grant_allocation` `ga`
     ON ((`ga`.`projects_id` = `p`.`id`)))
WHERE (`ga`.`party_id` = `pa`.`id`)
GROUP BY `p`.`id`
ORDER BY `p`.`name`$$

DELIMITER ;
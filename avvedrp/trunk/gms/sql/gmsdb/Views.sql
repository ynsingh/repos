/*View structure for view `project_details_view` */

DELIMITER $$

DROP VIEW IF EXISTS `project_details_view`$$

CREATE VIEW `project_details_view` AS 
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



/*View structure for view `project_expense_view` */

DELIMITER $$
DROP VIEW IF EXISTS `project_expense_view`$$
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `project_expense_view` AS
SELECT pa.name_of_the_institution AS Inst,pa.id AS Party,ah.name  AS AcHead ,

(SELECT P.name  FROM   projects P
LEFT JOIN grant_allocation GA ON  ( GA.projects_id=P.id)
WHERE GA.id=ga.id)AS ProjectName,

(SELECT  IF(SUM(g.amount_allocated) IS NULL,0,SUM(g.amount_allocated))  FROM projects prRe
LEFT JOIN grant_allocation g ON  (g.projects_id=prRe.id)
WHERE g.id=ga.id GROUP BY prRe.id) AS AmountAllocated,

(SELECT  IF(SUM(gs.amount) IS NULL,0,SUM(gs.amount))  FROM projects prRe
LEFT JOIN grant_allocation_split gs ON  (gs.projects_id=prRe.id)
WHERE gs.grant_allocation_id=ga.id AND gs.account_head_id=ah.id) AS AccAmt,

(SELECT  IF(SUM(geFE.expense_amount) IS NULL,0,SUM(geFE.expense_amount))  FROM projects prRe
LEFT JOIN grant_expense geFE ON  (geFE.projects_id=prRe.id)
WHERE geFE.grant_allocation_split_id=gas.id) AS ExpAmt

FROM party pa,grant_allocation ga
JOIN grant_allocation_split gas ON (gas.grant_allocation_id=ga.id)
LEFT JOIN account_heads ah ON ( ah.id= gas.account_head_id)
WHERE pa.id=ga.party_id 
GROUP BY ga.id,gas.id$$
DELIMITER ;


/*View structure for view `Project_allocation_acc` */




DELIMITER $$

DROP VIEW IF EXISTS `Project_allocation_acc`$$

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `Project_allocation_acc` AS

SELECT pa.name_of_the_institution AS Inst,pa.id AS Party,ah.name  AS AcHead ,

SUM(ga.amount_allocated) AS AmountAllocated,



(SELECT P.name  FROM   projects P

LEFT JOIN grant_allocation GA ON  ( GA.projects_id=P.id)

WHERE GA.id=ga.id)AS ProjectName,



(SELECT  IF(SUM(gs.amount) IS NULL,0,SUM(gs.amount))  FROM projects prRe

LEFT JOIN grant_allocation_split gs ON  (gs.projects_id=prRe.id)

WHERE gs.grant_allocation_id=ga.id AND gs.account_head_id=ah.id AND gs.grant_period_id=gp.id)  AS AccAmt,



(SELECT  IF(SUM(gr.amount) IS NULL,0,SUM(gr.amount))  FROM projects prRe

LEFT JOIN grant_receipt gr ON  (gr.projects_id=prRe.id)

WHERE gr.grant_allocation_id=ga.id) AS AmtRecvd,



(SELECT IF(SUM(amount) IS NULL,0,SUM(amount)) FROM fund_transfer FT INNER JOIN 

grant_allocation ON(grant_allocation.id=FT.grant_allocation_id) INNER JOIN 

projects ON(projects.id=grant_allocation.projects_id)

WHERE projects.parent_id=pr.id ) AS FundAmt,

                  

(SELECT  IF(SUM(geFE.expense_amount) IS NULL,0,SUM(geFE.expense_amount))  FROM projects prRe

LEFT JOIN grant_expense geFE ON  (geFE.projects_id=prRe.id)

WHERE geFE.grant_allocation_split_id=gas.id) AS ExpAmt



FROM party pa,projects pr,grant_allocation ga

JOIN grant_allocation_split gas ON (gas.grant_allocation_id=ga.id)

LEFT JOIN account_heads ah ON ( ah.id= gas.account_head_id)

LEFT JOIN grant_period gp ON ( gp.id= gas.grant_period_id)

WHERE pa.id=ga.party_id AND  pr.id = ga.projects_id

GROUP BY ga.id,gas.id$$

DELIMITER ;




/*View structure for view `project_header` */

DELIMITER $$

DROP VIEW IF EXISTS `project_header`$$

CREATE VIEW `project_header` AS

SELECT pa.name_of_the_institution AS Inst,pa.id AS Party,ah.name  AS AcHead ,

SUM(ga.amount_allocated) AS AmountAllocated,



(SELECT P.name  FROM   projects P

LEFT JOIN grant_allocation GA ON  ( GA.projects_id=P.id)

WHERE GA.id=ga.id)AS ProjectName,



(SELECT  IF(SUM(gs.amount) IS NULL,0,SUM(gs.amount))  FROM projects prRe

LEFT JOIN grant_allocation_split gs ON  (gs.projects_id=prRe.id)

WHERE gs.grant_allocation_id=ga.id AND gs.account_head_id=ah.id AND gs.grant_period_id=gp.id) AS AccAmt,



(SELECT  IF(SUM(gr.amount) IS NULL,0,SUM(gr.amount))  FROM projects prRe

LEFT JOIN grant_receipt gr ON  (gr.projects_id=prRe.id)

WHERE gr.grant_allocation_id=ga.id) AS AmtRecvd,



(SELECT IF(SUM(amount) IS NULL,0,SUM(amount)) FROM fund_transfer FT INNER JOIN 

grant_allocation ON(grant_allocation.id=FT.grant_allocation_id) INNER JOIN 

projects ON(projects.id=grant_allocation.projects_id)

WHERE projects.parent_id=pr.id ) AS FundAmt,        

         

(SELECT  IF(SUM(geFE.expense_amount) IS NULL,0,SUM(geFE.expense_amount))  FROM projects prRe

LEFT JOIN grant_expense geFE ON  (geFE.projects_id=prRe.id)

WHERE geFE.grant_allocation_split_id=gas.id) AS ExpAmt



FROM party pa,projects pr,grant_allocation ga

JOIN grant_allocation_split gas ON (gas.grant_allocation_id=ga.id)

LEFT JOIN account_heads ah ON ( ah.id= gas.account_head_id)

LEFT JOIN grant_period gp ON ( gp.id= gas.grant_period_id)

WHERE pa.id=ga.party_id AND  pr.id = ga.projects_id

GROUP BY ga.id,gas.id$$

DELIMITER ;







/*View structure for view `Resources` */
DELIMITER $$

DROP VIEW IF EXISTS `Resources`$$

CREATE VIEW `Resources` AS

SELECT pa.name_of_the_institution AS Inst,pa.id AS Party,ED.designation AS designation,pr.name AS ProjectName,

(SELECT PE.emp_no  FROM   projects p

JOIN project_employee PE ON  ( p.id=PE.projects_id)

WHERE p.id=pr.id AND  PE.status = 'Y' AND PE.employee_designation_id=ED.id)AS EmpNmbr

FROM party pa,projects pr, grant_allocation ga,employee_designation ED 

WHERE pa.id=ga.party_id AND  pr.id = ga.projects_id 

GROUP BY ED.id ,ga.id$$

DELIMITER ;




/*View structure for view `Asset` */

DELIMITER $$

DROP VIEW IF EXISTS `Asset`$$

CREATE VIEW `Asset` AS

SELECT 
pa.name_of_the_institution AS Inst,
P.name AS projectName,
IP.name AS AssetName,
IP.asset_code AS assetCode,
IP.cost AS cost

FROM
party pa,
projects P,
item_purchase IP,grant_allocation ga

WHERE
pa.id=ga.party_id AND  P.id = ga.projects_id 
AND IP.projects_id = P.id
AND IP.status = 'Y'
GROUP BY IP.id $$

DELIMITER ;

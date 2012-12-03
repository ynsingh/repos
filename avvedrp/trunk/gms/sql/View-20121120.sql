/*View structure for view `project_expense_view` */

DELIMITER $$
DROP VIEW IF EXISTS `project_expense_view`$$
CREATE VIEW `project_expense_view` AS
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

CREATE VIEW `Project_allocation_acc` AS

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

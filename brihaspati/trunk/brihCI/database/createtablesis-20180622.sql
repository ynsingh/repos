use payroll;

CREATE TABLE `cron` (
`id` int(5) NOT NULL,
`name` varchar(100) DEFAULT NULL,
`command` varchar(255) NOT NULL,
`interval_sec` int(10) NOT NULL,
`last_run_at` datetime DEFAULT NULL,
`next_run_at` datetime DEFAULT NULL,
`is_active` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


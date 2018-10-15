ALTER TABLE `hra_grade` ADD `hg_paycomm` VARCHAR(255) NULL AFTER `hg_workingtype`;
ALTER TABLE `hra_grade` ADD `hg_payrange` VARCHAR(255) NULL AFTER `hg_payscaleid`;
ALTER TABLE `rent_recovery` ADD `rr_paycomm` VARCHAR(255) NULL AFTER `rr_workingtype`;
ALTER TABLE `rent_recovery` ADD `rr_payrange` VARCHAR(255) NULL AFTER `rr_payscaleid`;
ALTER TABLE `ccaallowance_calculation` ADD `cca_paycomm` VARCHAR(255) NULL AFTER `cca_workingtype`;
ALTER TABLE `ccaallowance_calculation` ADD `cca_payrange` VARCHAR(255) NULL AFTER `cca_payscaleid`;

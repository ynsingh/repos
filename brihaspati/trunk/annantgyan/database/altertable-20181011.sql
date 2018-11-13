ALTER TABLE test DROP INDEX testname;
ALTER TABLE `test` ADD UNIQUE `testname` ( `testname`, `subid`);

drop index `prg_category` on `program`;
create index `prg_category`on `program` (`prg_scid`,`prg_category`,`prg_name`,`prg_branch`);

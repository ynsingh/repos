drop index `prg_category` on `program`;
create index `prg_category`(`prg_cid`,`prg_category`,`prg_name`,`prg_branch`) on `program`;

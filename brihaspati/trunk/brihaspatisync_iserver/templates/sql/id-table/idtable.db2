#foreach ($tbl in $tables)
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES ($initialID, '$tbl.Name', 1000, 10);
#set ( $initialID = $initialID + 1 )
#end

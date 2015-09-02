alter table old_asset_register add sanc_type VARCHAR (100) NOT NULL after asset_status;
alter table new_asset_register add sanc_type VARCHAR (100) NOT NULL after asset_status;

dataSource {
	pooled = true
	driverClassName = "com.mysql.jdbc.Driver"
	username = "root"
	password = "devima"
}
hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='com.opensymphony.oscache.hibernate.OSCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop','update'
				url = "jdbc:mysql://192.168.18.103/gms_Ver1"
		}
	}
	
	production {
		dataSource {
			dbCreate = "update"
				url = "jdbc:mysql://localhost/gms_Ver"
		}
	}
}
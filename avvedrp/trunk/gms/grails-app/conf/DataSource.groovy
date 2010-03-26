dataSource {
	pooled = true
	driverClassName = "com.mysql.jdbc.Driver"
	username = "user"
	password = "password"
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
				url = "jdbc:mysql://localhost/gmsiitk"
		}
	}
	
	production {
		dataSource {
			dbCreate = "update"
				url = "jdbc:mysql://localhost/gmsiitk"
		}
	}
}

dataSource {
	pooled = true
	driverClassName = "com.mysql.jdbc.Driver"
	username = "root"
	password = "devima"
}
hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop','update'
				url = "jdbc:mysql://192.168.18.95/demo_data_iitk"
		}
	}
	
	production {
			dataSource {
				dbCreate = "update"
					jndiName = "java:comp/env/MySqlGrailsDS"
			}
	}
}
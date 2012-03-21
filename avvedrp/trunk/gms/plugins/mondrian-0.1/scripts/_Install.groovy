Ant.mkdir(dir:"${basedir}/web-app/jpivot")
Ant.copy(todir:"${basedir}/web-app/jpivot") {
	fileset(dir:"${pluginBasedir}/jpivot")
}

Ant.mkdir(dir:"${basedir}/web-app/wcf")
Ant.copy(todir:"${basedir}/web-app/wcf") {
	fileset(dir:"${pluginBasedir}/wcf")
}

Ant.copy(todir:"${basedir}/web-app/WEB-INF") {
	fileset(dir:"${pluginBasedir}/WEB-INF")
}

Ant.copy(todir:"${basedir}/web-app") {
	fileset(dir:"${pluginBasedir}/jsp")
}
class GmsSettingsService {

    boolean transactional = true

    def serviceMethod() {

    }
    /*method for getting value of each name from gmssettings*/
    def getGmsSettings(def nameInstance)
    {
    	def gmsSettingsInstance = GmsSettings.find("from GmsSettings where name='"+nameInstance+"'")
    	return gmsSettingsInstance
    }
    def getGmsSettingsValue(def nameInstance)
    {
    	def gmsSettingsInstance = GmsSettings.find("from GmsSettings where name='"+nameInstance+"'")
    	return gmsSettingsInstance.value
    }
}

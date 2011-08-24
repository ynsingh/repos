class LmsSettings {

        Integer lms_id
	    String lms_hostname
		String lms_port
	    String lms_username
        String lms_password
        String lms_dbname
	    static constraints = {
                 lms_hostname(blank:false)
				 lms_port(blank:false)
                 lms_username(blank:false)
                 lms_password(blank:false)
                 lms_dbname(blank:false)
	}
    

   String lmsId;
   String lmsName;
   String lmsHost;
   String lmsPaswd;   
   static transients = ["lmsId","lmsName","lmsHost","lmsPaswd"]
}

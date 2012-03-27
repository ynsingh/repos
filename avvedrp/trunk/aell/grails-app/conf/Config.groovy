// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }
newuser_university = 1
url_of_site="www.iitk.ac.in"
roleplay_tabname="Role Play";
roleplay_audname_one="Interviewer";
roleplay_audname_two="Candidate";
upload_path="uploads";
referal_url = "http://aell.amritalearning.com";
login_time_duration = 300;
al_roleid=1003;
ell_integrate_flag =0// if only for career lab, set flag=0
universityId=1

// email sending after registration
mail_send_flag = 0; // if 0 mail functionality disabled
mail_host = "smtp.gmail.com";
mail_authusername = "skknbr3005@gmail.com";
mail_authpassword = "3005skknbr";
mail_from = "no-reply@yourdomain.com";
mail_port = "465"
mail_tosend = "santhoshk@amritalearning.com"
mail_subject = "New user has registered in ELL"
mail_content = "Hi ELL Administrator, A new user has been registered. Review the same and activate the user."
mail_userSubject = "ELL registration - confirmation"
mail_userContent = "You have been successfully registered in ELL. Administrator give you the login access rights to ELL shortly"


ell_build_version = "1.0"; //released on 19Mar2012
grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]
// The default codec used to encode data with ${}
grails.views.default.codec="none" // none, html, base64
grails.views.gsp.encoding="UTF-8"
grails.converters.encoding="UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder=false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable fo AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://www.changeme.com"
    }
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
    }

}

// log4j configuration
log4j = {


    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}


    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
	       'org.codehaus.groovy.grails.web.pages', //  GSP
	       'org.codehaus.groovy.grails.web.sitemesh', //  layouts
	       'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
	       'org.codehaus.groovy.grails.web.mapping', // URL mapping
	       'org.codehaus.groovy.grails.commons', // core / classloading
	       'org.codehaus.groovy.grails.plugins', // plugins
	       'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
	       'org.springframework',
	       'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
}
//Added by the Spring Security Core plugin:
/*grails.plugins.springsecurity.userLookup.userDomainClassName = 'aell.AvlUserMaster'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'aell.AvlRoleUserRel'
grails.plugins.springsecurity.authority.className = 'aell.AvlRoleMaster'*/

/*grails.plugins.springsecurity.userLookup.userDomainClassName = 'aell.EllUserMaster'
	grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'aell.EllUserMasterEllRoleMaster'
	grails.plugins.springsecurity.authority.className = 'aell.EllRoleMaster'*/


// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'aell.AvlUserMaster'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'aell.AvlRoleUserRel'
grails.plugins.springsecurity.authority.className = 'aell.AvlRoleMaster'
grails.plugins.springsecurity.rememberMe.persistent = true
grails.plugins.springsecurity.rememberMe.persistentToken.domainClassName = 'aell.PersistentLogin'
grails.plugins.springsecurity.openid.domainClass = 'aell.OpenID'


fckeditor {
	upload {
		basedir = "/uploads/"
		overwrite = false
		link {
			browser = true
			upload = true
			allowed = []
			denied = ['html', 'htm', 'php', 'php2', 'php3', 'php4', 'php5',
					  'phtml', 'pwml', 'inc', 'asp', 'aspx', 'ascx', 'jsp',
					  'cfm', 'cfc', 'pl', 'bat', 'exe', 'com', 'dll', 'vbs', 'js', 'reg',
					  'cgi', 'htaccess', 'asis', 'sh', 'shtml', 'shtm', 'phtm']
		}
		image {
			browser = true
			upload = true
			allowed = ['jpg', 'gif', 'jpeg', 'png']
			denied = []
		}
		flash {
			browser = false
			upload = false
			allowed = ['swf']
			denied = []
		}
		media {
			browser = true
			upload = true
			allowed = ['mpg','mpeg','avi','wmv','asf','mov','mp3']
			denied = []
		}
	}
}
fckeditor.upload.media.allowed	[ ]
// ell admin page config

ImageFolder="images/"
AudioFolder="Media/"
QuizFolder="quiz"

login_page_title = "ELL Admin Login"
admindashboard_title = "Edit Content"
module = "Module"
module_text = "Module Name"
module_desc = "Module Description"
topic = "Topic"
topic_text = "Topic Name"
topic_desc = "Topic Description"
sub_topic = "Sub Topic"
sub_topic_text = "Sub Topic Name"
sub_topic_desc = "Sub Topic Description"
edit_contents = "Edit Content"
user_manage= "User Management"
user_register="User Registration"
manage_privilege="Manage Privileges"
admin_dashboard_portal="Portal Admin"
user_management="User Management"
user_registration="User Registration"
manage_privileges="Manage Privileges"
role_management="Role Management"
change_password="Change Password"
user_status="Enrolled Users"
current_login_details="Current Active Users"
user_accessed_tabs="Accessed Tab Details"
user_access_details="User Access Details"
report="Report"
account="Account"
user_manage= "User Management"
user_register="User Registration"
manage_privilege="Manage Privileges"
university = "University"
add_new = "Add New"
edit = "Edit"
page_title = "English Language Lab"
copy_right1 = "Developed by Amrita University under ERP, NME ICT, MHRD"

  

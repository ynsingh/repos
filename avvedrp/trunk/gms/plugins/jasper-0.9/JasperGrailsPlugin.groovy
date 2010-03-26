
class JasperGrailsPlugin {
    def version = "0.9"
    def author = "Marcos Pereira"
    def authorEmail = "mfpereira@gmail.com"
    def title = "Easily launch Jasper reports from a Grails application."
    def description = '''
	This plugin adds easy support for launching jasper reports from GSP pages.
	After installing this plugin, run your application and request (app-url)/jasper for a demonstration and instructions.
    '''
    //def documentation = "http://grails.org/Jasper+plugin"
    def dependsOn = [:]

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when this class plugin class is changed
        // the event contains: event.application and event.applicationContext objects
    }

    def onApplicationChange = { event ->
        // TODO Implement code that is executed when any class in a GrailsApplication changes
        // the event contain: event.source, event.application and event.applicationContext objects
    }
}

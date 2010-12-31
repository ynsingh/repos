
class EastwoodChartGrailsPlugin {
    def version = 0.5
    def author = "Seymour Cakes"
    def authorEmail = "seymores @ gmail"
    def title = "This plugin wraps JFreeChart's Eastwood servlet for Grails app"
    def grailsVersion = "1.1-RC1 > *"
    
    def dependsOn = [googleChart:"0.5.0"]
    //def dependsOn = ["":]
   
    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }
   
    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)     
    }

    def doWithWebDescriptor = { xml ->
        def servletElement = xml.'servlet'
        def servletMapElement = xml.'servlet-mapping'

        // Need to get the last element
        servletElement[servletElement.size()-1] + {
            'servlet' {
                'servlet-name'("chart")
                'servlet-class'("org.jfree.eastwood.ChartServlet")
            }
        }

        servletMapElement + {
            'servlet-mapping' {
                'servlet-name'("chart")
                'url-pattern'("/chart")
            }
        }
    }
}

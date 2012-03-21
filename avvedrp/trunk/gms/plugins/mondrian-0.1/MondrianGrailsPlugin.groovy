import com.tonbeller.jpivot.mondrian.MondrianDrillExBean
import com.tonbeller.jpivot.mondrian.MondrianMemberSetBean
import com.tonbeller.jpivot.mondrian.MondrianMemento
import com.tonbeller.jpivot.mondrian.MondrianQuaxBean
import com.tonbeller.jpivot.xmla.XMLA_Memento

class MondrianGrailsPlugin {
	def version = 0.1
	def author = "Tom Dallaway"
	def title = "This plugin installs Pentaho Mondrian into your Grails application."
	def dependsOn = [:]
	
	def doWithSpring = {
		// Add beans to resources.xml
		mondrianDrillExBean(MondrianDrillExBean)
		mondrianMemberSetBean(MondrianMemberSetBean)
		mondrianMemento(MondrianMemento)
		mondrianQuaxBean(MondrianQuaxBean)
		xmlaMemento(XMLA_Memento)
	}
	
	def doWithApplicationContext = { applicationContext ->
		// TODO Implement post initialization spring config (optional)		
	}
	
	def doWithWebDescriptor = { xml ->
		// Add to web.xml
		def cpCount = xml.'context-param'.size()
		if(cpCount > 0) {
			def contextparamElement = xml.'context-param'[cpCount-1]
			contextparamElement + {
				'context-param' {
					'param-name'("contextFactory")
					'param-value'("com.tonbeller.wcf.controller.RequestContextFactoryImpl")
				}
			}
		}
		
		def fCount = xml.'filter'.size()
		if(fCount > 0) {
			def filterElement = xml.'filter'[fCount-1]
			filterElement + {
				'filter' {
					'filter-name'("JPivotController")
					'filter-class'("com.tonbeller.wcf.controller.RequestFilter")
					
					'init-param' {
						'param-name'("errorJSP")
						'param-value'("/error.jsp")
						'description'("URI of error page")
					}
					'init-param' {
						'param-name'("busyJSP")
						'param-value'("/busy.jsp")
						'description'("This page is displayed if a the user clicks on a query before the previous query has finished")
					}
				}
			}
		}
		
		def fmCount = xml.'filter-mapping'.size()
		if(fmCount > 0) {
			def filtermappingElement = xml.'filter-mapping'[fmCount-1]
			filtermappingElement + {
				'filter-mapping' {
					'filter-name'("JPivotController")
					'url-pattern'("/testpage.jsp")
				}
			}
		}
		
		def lCount = xml.'listener'.size()
		if(lCount > 0) {
			def listenerElement = xml.'listener'[lCount-1]
			listenerElement + {
				'listener' {
					'listener-class'("mondrian.web.taglib.Listener")
				}
				'listener' {
					'listener-class'("com.tonbeller.tbutils.res.ResourcesFactoryContextListener")
				}
			}
		}
		
		def sCount = xml.'servlet'.size()
		if(sCount > 0) {
			def servletElement = xml.'servlet'[sCount-1]
			servletElement + {
				'servlet' {
					'servlet-name'("DisplayChart")
					'servlet-class'("org.jfree.chart.servlet.DisplayChart")
				}
				'servlet' {
					'servlet-name'("GetChart")
					'display-name'("GetChart")
					'description'("Default configuration created for servlet.")
					'servlet-class'("com.tonbeller.jpivot.chart.GetChart")
				}
				'servlet' {
					'servlet-name'("Print")
					'display-name'("Print")
					'description'("Default configuration created for servlet.")
					'servlet-class'("com.tonbeller.jpivot.print.PrintServlet")
				}
				'servlet' {
					'servlet-name'("MDXQueryServlet")
					'servlet-class'("mondrian.web.servlet.MDXQueryServlet")
				}
				'servlet' {
					'servlet-name'("MondrianXmlaServlet")
					'servlet-class'("mondrian.xmla.impl.DefaultXmlaServlet")
				}
			}
		}
		
		def smCount = xml.'servlet-mapping'.size()
		if(smCount > 0) {
			def servletmappingElement = xml.'servlet-mapping'[smCount-1]
			servletmappingElement + {
				'servlet-mapping' {
					'servlet-name'("DisplayChart")
					'url-pattern'("/DisplayChart")
				}
				'servlet-mapping' {
					'servlet-name'("Print")
					'url-pattern'("/Print")
				}
				'servlet-mapping' {
					'servlet-name'("GetChart")
					'url-pattern'("/GetChart")
				}
				'servlet-mapping' {
					'servlet-name'("MDXQueryServlet")
					'url-pattern'("/mdxquery")
				}
				'servlet-mapping' {
					'servlet-name'("MondrianXmlaServlet")
					'url-pattern'("/xmla")
				}
			}
		}
		
		def tlCount = xml.'jsp-config'.'taglib'.size()
		if(tlCount > 0) {
			def taglibElement = xml.'jsp-config'.'taglib'[tlCount-1]
			taglibElement + {
				'taglib' {
					'taglib-uri'("http://www.tonbeller.com/wcf")
					'taglib-location'("/WEB-INF/wcf/wcf-tags.tld")
				}
				'taglib' {
					'taglib-uri'("http://www.tonbeller.com/jpivot")
					'taglib-location'("/WEB-INF/jpivot/jpivot-tags.tld")
				}
			}
		}
	}
	
	def doWithDynamicMethods = { ctx ->
		// TODO Implement additions to web.xml (optional)
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

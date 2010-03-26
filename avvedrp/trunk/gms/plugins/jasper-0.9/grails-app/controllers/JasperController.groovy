import org.springframework.context.ApplicationContext

class JasperController {
    JasperService jasperService

    def index = {
    		
        // The default location for jasper reports is "%PROJECT_HOME%/web-app/reports/", but it can be changed in Config.groovy
        // The sample reports for this plugin follow the default and are installed in the app along with the the plugin
        String jasperFilePath = grailsApplication.config.jasper.dir.reports ?: "reports"
        jasperFilePath = JasperService.ensureNoTrailingSeparator(jasperFilePath)

        if (params.SUBREPORT_DIR == null) {
            params.SUBREPORT_DIR = jasperFilePath
        }

        if (params.REPORT_LOCALE == null) {
            params.REPORT_LOCALE = request.getLocale()
        }
        Collection reportData = null
        def testModel = this.getProperties().containsKey('chainModel')?chainModel:null

        if (testModel?.data) {
            log.info "Using chainModel.data"
            try {
                reportData = testModel.data
            } catch (Throwable e) {
                throw new Exception("Expected chainModel.data parameter to be a Collection, but it was ${chainModel.data.class.name}",e)
            }
        } else {
            testModel = this.getProperties().containsKey('model')?model:null
            if (testModel?.data) {
                log.info "Using model.data"
                try {
                    reportData = testModel.data
                } catch (Throwable e) {
                    throw new Exception("Expected model.data parameter to be a Collection, but it was ${model.data.class.name}",e)
                }
            } else if (params?.data) {
                log.info "Using params.data"
                try {
                    reportData = params.data
                } catch (Throwable e) {
                    throw new Exception("Expected data parameter to be a Collection, but it was ${params.data.class.name}",e)
                }
            } else {
                log.info "No data supplied"
        }
        }


        def inline = Boolean.valueOf(params._inline)

        switch (params._format) {
            case "PDF":
                createBinaryFile(jasperFilePath, jasperService.PDF_FORMAT, reportData, params, "pdf", "application/pdf", inline)
                break
            case "HTML":
                render(text: jasperService.generateReport(jasperFilePath, jasperService.HTML_FORMAT, reportData, params), contentType: "text/html")
                break
            case "XML":
                render(text: jasperService.generateReport(jasperFilePath, jasperService.XML_FORMAT, reportData, params), contentType: "text/xml")
                break
            case "CSV":
                response.setHeader("Content-disposition", "attachment; filename=\"" + params._name + ".csv\"");
                render(text: jasperService.generateReport(jasperFilePath, jasperService.CSV_FORMAT, reportData, params), contentType: "text/csv")
                break
            case "XLS":
                createBinaryFile(jasperFilePath, jasperService.XLS_FORMAT, reportData, params, "xls", "application/vnd.ms-excel", false)
                break
            case "RTF":
                createBinaryFile(jasperFilePath, jasperService.RTF_FORMAT, reportData, params, "rtf", "text/rtf", false)
                break
            case "TEXT":
                render(text: jasperService.generateReport(jasperFilePath, jasperService.TEXT_FORMAT, reportData, params), contentType: "text")
                break
            default:
                throw new Exception(message(code: "jasper.controller.invalidFormat", args: [params._format]))
                break
        }
    }


    def createBinaryFile = {jasperFile, format, reportData, params, ext, mime, inline ->
        def data = jasperService.generateReport(jasperFile, format, reportData, params).toByteArray()
        if (!inline) {
            response.setHeader("Content-disposition", "attachment; filename=\"" + params._name + "." + ext + "\"");
        }
        response.contentType = mime
        response.outputStream << data
    }

    def admin = {
        // There is nothing to administer, so just show the demo
        redirect(action:'demo')
    }

    def demo = {
        // This "people" object in this data model is only for displaying the data in a table on the Demo page (demo.gsp) next to the
        // example that invokes the exampleWithData action, below.  It has nothing to do directly with the data that is
        // actually reported.
        List people = makeUpSomeDemoData()
        [people:people]
}

    def exampleWithData = {
        // This "data" object in this data model is the data that drives this Jasper report (i.e. what appears in the
        // detail band)
        List reportDetails = makeUpSomeDemoData()

        chain(controller:'jasper',action:'index',model:[data:reportDetails],params:params)
    }

    protected List makeUpSomeDemoData() {
        List people = [
                new ExamplePersonForReport(name: 'Amy', email: 'amy@example.com'),
                new ExamplePersonForReport(name: 'Brad', email: 'brad@example.com'),
                new ExamplePersonForReport(name: 'Charlie', email: 'charlie@example.com'),
                new ExamplePersonForReport(name: 'Diane', email: 'diane@example.com'),
                new ExamplePersonForReport(name: 'Edward', email: 'edward@example.com')]
        return people
    }

}

/**
 * For demonstration purposes
 */
class ExamplePersonForReport {
    String name
    String email
}

import net.sf.jasperreports.engine.*
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.export.*
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.core.io.FileSystemResourceLoader.FileSystemContextResource
import org.springframework.core.io.Resource
import net.sf.jasperreports.engine.util.JRProperties

class JasperService implements ApplicationContextAware  {
    final int PDF_FORMAT = 1;
    final int HTML_FORMAT = 2;
    final int XML_FORMAT = 3;
    final int CSV_FORMAT = 4;
    final int XLS_FORMAT = 5;
    final int RTF_FORMAT = 6;
    final int TEXT_FORMAT = 7;
    final boolean FORCE_TEMP_FOLDER = false;

    boolean transactional = true
    javax.sql.DataSource dataSource
    ApplicationContext applicationContext

    public static String ensureNoTrailingSeparator(String filename) {
        if (filename.endsWith(File.separator)) {
            filename = filename.substring(0, filename.length() - 1)
        }
        return filename
    }
    public static String ensureNoExtension(String filename) {
        return filename.replaceAll(/\..*$/,'')
    }

    public def generateReport = {String jasperFilePath, Integer format, Collection reportData, Map parameters ->
        JRExporter exporter
        switch (format) {
            case PDF_FORMAT:
                exporter = new JRPdfExporter()
                break
            case HTML_FORMAT:
                exporter = new JRHtmlExporter()
                exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, false)
                break
            case XML_FORMAT:
                exporter = new JRXmlExporter()
                break
            case CSV_FORMAT:
                exporter = new JRCsvExporter()
                break
            case XLS_FORMAT:
                exporter = new JRXlsExporter()
                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
                exporter.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.TRUE);
                exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                break
            case RTF_FORMAT:
                exporter = new JRRtfExporter()
                break
            case TEXT_FORMAT:
                exporter = new JRTextExporter()
                exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, 80)
                exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, 60)
                break
            default:
                throw new Exception("Unknown Report Format")
        }

        ByteArrayOutputStream byteArray = new ByteArrayOutputStream()
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArray)

        Resource reportSpecResource = fetchReportSpec(jasperFilePath, parameters._file)
        JasperPrint jasperPrint



        if (reportData != null) {
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportData);
            if (reportSpecResource.filename.endsWith('.jasper')) {
                jasperPrint = JasperFillManager.fillReport(reportSpecResource.inputStream, parameters, jrBeanCollectionDataSource)
            } else {
                forceTempFolder()
                jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(reportSpecResource.inputStream), parameters, jrBeanCollectionDataSource)
                }
        } else {
            java.sql.Connection conn = dataSource.getConnection()
            try {
                if (reportSpecResource.filename.endsWith('.jasper')) {
                    jasperPrint = JasperFillManager.fillReport(reportSpecResource.inputStream, parameters, conn)
                } else {
                    forceTempFolder()
                    jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(reportSpecResource.inputStream), parameters, conn)
                }
            }
            finally {
                conn.close()
            }
        }

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint)
        exporter.exportReport()
        return byteArray
            }

    /**
     * Forces the Jasper Reports  temp folder to be "~/.grails/.jasper" and ensures that such a folder exists.
     * The user (however the app server is logged in) is much more likely to have read/write/delete rights here than the
     * default location that Jasper Reports uses.
     */
    protected def forceTempFolder() {
        /* TODO This is currently disabled, because it doesn't work. Jasper Reports seems to always use the current
         * folder (.) no matter what.  (I'll be filing a bug report against Jasper Reports itself shortly - Craig Jones 16-Aug-2008)
         */
        if (FORCE_TEMP_FOLDER) {
            // Look up the home folder explicitly (don't trust that tilde notation will work).
            String userHomeDir = System.getProperty('user.home')
            File tempFolder = new File(userHomeDir,"/.grails/.jasper")

            // This is the current official means for setting the temp folder for jasper reports to use when compiling
            // reports on the fly, but it doesn't work
            JRProperties.setProperty(JRProperties.COMPILER_TEMP_DIR, tempFolder.getAbsolutePath())

            // This is a deprecated means for setting the temp folder that supposedly still works (still in the Jasper
            // Reports source code trunk as of 14-Aug-2008, and, in fact, takes precedence over the official method);
            // however, it doesn't work either.
            System.setProperty("jasper.reports.compile.temp", tempFolder.getAbsolutePath())

            if (!tempFolder.exists()) {
                def ant = new AntBuilder()
                ant.mkdir(dir: tempFolder.getAbsolutePath())
                if (!tempFolder.exists()) {
                    throw new Exception("Unable to create temp folder: ${tempFolder.getPath()}")
                }
            }
            log.info "Using temp folder: " + tempFolder.getPath() +" ("+tempFolder.getAbsolutePath()+")"
        }
    }

    protected Resource fetchReportSpec(String folder, String  filename) throws Exception {
        final String baseFilename = ensureNoTrailingSeparator(folder) + File.separator + ensureNoExtension(filename)

        Resource result = applicationContext.getResource(baseFilename+'.jasper')
        if (result.exists()) {
            return result
        }
        result = applicationContext.getResource(baseFilename+'.jrxml')
        if (result.exists()) {
            return result
        }
        // TODO If the following redundant with the above, then remove it.  Does getResource() already look in the filesystem like this?
        result = new FileSystemContextResource(baseFilename+'.jasper')
        if (result.exists()) {
            return result
        }
        result = new FileSystemContextResource(baseFilename+'.jrxml')
        if (result.exists()) {
            return result
        }
        throw new Exception("No such report spec: ${baseFilename}.jasper or .jrxml")
    }

}
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.acls.model.Permission

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class ReportController {

   static defaultAction = 'list'

   def reportService

   def list = {
      params.max = Math.min(params.max ? params.int('max') : 10, 100)
      [reportInstanceList: reportService.list(params),
       reportInstanceTotal: reportService.count()]
   }

   def create = {
      [reportInstance: new Report(params)]
   }

   def save = {
      def report = reportService.create(params.name)
      if (!renderWithErrors('create', report)) {
         redirectShow "Report $report.id created", report.id
      }
   }

   def show = {
      def report = findInstance()
      if (!report) return

      [reportInstance: report]
   }

   def edit = {
      def report = findInstance()
      if (!report) return

      [reportInstance: report]
   }

   def update = {
      def report = findInstance()
      if (!report) return

      reportService.update report, params.name
      if (!renderWithErrors('edit', report)) {
         redirectShow "Report $report.id updated", report.id
      }
   }

   def delete = {
      def report = findInstance()
      if (!report) return

      try {
         reportService.delete report
         flash.message = "Report $params.id deleted"
         redirect action: list
      }
      catch (DataIntegrityViolationException e) {
         redirectShow "Report $params.id could not be deleted", params.id
      }
   }

   def grant = {

      def report = findInstance()
      if (!report) return

      if (!request.post) {
         return [reportInstance: report]
      }

      reportService.addPermission report, params.recipient, params.int('permission')

      redirectShow "Permission $params.permission granted on Report $report.id to $params.recipient", report.id
   }

   private Report findInstance() {
      def report = reportService.get(params.long('id'))
      if (!report) {
         flash.message = "Report not found with id $params.id"
         redirect action: list
      }
      report
   }

   private void redirectShow(message, id) {
      flash.message = message
      redirect action: show, id: id
   }

   private boolean renderWithErrors(String view, Report report) {
      if (report.hasErrors()) {
         render view: view, model: [reportInstance: report]
         return true
      }
      false
   }
}
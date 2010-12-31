class GChartTagLib extends GoogleChartTagLib{

   static namespace = "gchart"
   
   def baseChart(attrs, type) {
         this.setApiStringTo("http://chart.apis.google.com/chart?");
         return super.baseChart(attrs, type)
   }
   
   def setApiStringTo(apiString)
   {
      this.apiString = apiString;
   }
   
}

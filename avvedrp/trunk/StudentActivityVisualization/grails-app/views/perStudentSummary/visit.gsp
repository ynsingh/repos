<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Student Visit Details</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
         
        </div>
        <div class="body">
            <h1>Student Visit Details</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	     <g:sortableColumn property="studentName" title="User ID" />
                   	        
                   	          <g:sortableColumn property="studentName" title="Student Name" />
                   	          
                   	          <g:sortableColumn property="viewCount" title="Visit Count" />
                        
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${perStudentSummaryInstanceList}" status="i" var="perStudentSummaryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${perStudentSummaryInstance.id}">${fieldValue(bean:perStudentSummaryInstance, field:'id')}</g:link></td>
                        
                            
                        
                            <td>${fieldValue(bean:perStudentSummaryInstance, field:'userId')}</td>
                             <td>${fieldValue(bean:perStudentSummaryInstance, field:'studentName')}</td>
                              <td>${fieldValue(bean:perStudentSummaryInstance, field:'viewCount')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <table><tr><td>
            <div class="paginateButtons">
                <g:paginate total="${perStudentSummaryInstanceTotal}" />
            </div>
        </div>
        
     


	<%
		//println "Size = " + perStudentSummaryInstanceList.size()
		
		def studentNames = []
		
		perStudentSummaryInstanceList.each {
		
			studentNames.add (it.studentName)
		}
		
		def chartValue=[]
		def sessionCount = []
		def range = []
		def maxValue=0
		
		perStudentSummaryInstanceList.each {
		if(maxValue<it.sessionCount)
		maxValue=it.sessionCount
		
           sessionCount.add (it.sessionCount)
		}
		
		def resourcesCount = []
		
		perStudentSummaryInstanceList.each {
		if(maxValue<it.resourcesCount)
		maxValue=it.resourcesCount
		
           resourcesCount.add (it.resourcesCount)
           
           
		}
		
		for(int i=0;i<=maxValue;i++)
		{
		range.add(i)
		}
		chartValue.add (sessionCount)
		chartValue.add (resourcesCount)
		
		
		
		
	    	//def labels = ['First','Second','Third']
	    	
	    	labels = sessionCount
	    	
	    	def colors = ['FF0000','00ff00','0000ff']
	    	def values = chartValue
	    	def values2 = [[35,45,10],[3,987,2]]
	    	def values5 = [[0,16.7,23.3,33.3,60,76.7,83.3,86.7,93.3,96.7,100],[30,45,20,50,15,80,60,70,40,55,80],[0,10,16.7,26.7,33.3],[50,10,30,55,60]]
	    	def values3 = [97,12,454,12,5,32,78,4,99,89,98,77,7,77]
	    	def values4 = [[97,12,454,12,5,32,78,4,99,89,98,77,7,77],[1,6,42,15,78,94,26,45,12,10,21,22,33,33]]
	    	def values6 = [[-500,30,700,253],[2,-5,3,6]]
	    	
	    	
		def axesLabels = [:]
		axesLabels.put (0, studentNames);
		axesLabels.put (1, range);
	
		
		
		// for  time spend gragh
		def timeSpent = []
		def rangeTime = []
		 maxValue=0
		
		perStudentSummaryInstanceList.each {
		if(maxValue<it.timeSpent)
		maxValue=it.timeSpent
		
           timeSpent.add (it.timeSpent)
		}
		
		for(int i=0;i<=maxValue;i=i+2)
		{
		rangeTime.add(i)
		}
		
		def axesLabelsTime = [:]
		axesLabelsTime.put (0, studentNames);
		axesLabelsTime.put (1, rangeTime);
	
	
	
	
		// for  time spend gragh
		def viewCount = []
		def rangeView = []
		def rangeRadarView = []
		 maxValue=0
		
		perStudentSummaryInstanceList.each {
		if(maxValue<it.viewCount)
		maxValue=it.viewCount
		
           viewCount.add (it.timeSpent)
		}
		
		for(int i=0;i<=maxValue;i=i+2)
		{
		rangeView.add(i)
		}
		
		for(int i=0;i<=maxValue+10;i=i+5)
		{
		rangeRadarView.add(i)
		}
		
		def axesLabelsView = [:]
		axesLabelsView.put (0, studentNames);
		axesLabelsView.put (1, rangeView);
	
	
	def axesLabelsRadarView = [:]
		axesLabelsRadarView.put (0, studentNames);
		axesLabelsRadarView.put (1, rangeRadarView);
		
    	%>
    
    
     
			
		
       
    <chart:barChart title='Number of Visits' size="${[475,475]}" colors="FF0000,00ff00" type="bvg"
			labels="${labels}" zeroLine="${'0'}" axes="x,y" axesLabels="${axesLabelsView}"  
			fill="${'bg,s,efefef'}" fill="${'bg,s,efefef'}" dataType='simple' data='${viewCount}'  />
       


     <%
     
   
     
     %>
	
     <gchart:radar title='Number of Visits in radar Chart' size="${[475,475]}" colors="${colors}" 
		axes="x,y" axesLabels="${axesLabelsRadarView}" fill="${'bg,s,efefef'}" dataType='text' data='${viewCount}' 
		
		
		
		/>
		
	


    </td></tr></table>
        
    </body>
</html>

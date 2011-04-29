

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>LmsUsage List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New LmsUsage</g:link></span>
        </div>
        <div class="body">
            <h1>LmsUsage List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="session_minutes" title="Sessionminutes" />
                        
                   	        <g:sortableColumn property="student_id" title="Studentid" />
                        
                   	        <g:sortableColumn property="week" title="Week" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${lmsUsageInstanceList}" status="i" var="lmsUsageInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${lmsUsageInstance.id}">${fieldValue(bean:lmsUsageInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:lmsUsageInstance, field:'session_minutes')}</td>
                        
                            <td>${fieldValue(bean:lmsUsageInstance, field:'student_id')}</td>
                        
                            <td>${fieldValue(bean:lmsUsageInstance, field:'week')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${lmsUsageInstanceTotal}" />
            </div>
        </div>
        
        
      <%
      		//println "Size = " + lmsUsageInstanceList.size()
      		
      		def studentIDs = []
      		
      		lmsUsageInstanceList.each {
      		
      			studentIDs.add (it.student_id)
      		}
      		
      		//println "student Names = " + studentNames
      		
      	    	//def labels = ['First','Second','Third']
      	    	
      	    	labels = studentIDs
      	    	
      	    	def colors = ['FF0000','00ff00','0000ff']
      	    	def values = [35,45,10]
      	    	def values2 = [[35,45,10],[3,987,2]]
      	    	def values5 = [[0,16.7,23.3,33.3,60,76.7,83.3,86.7,93.3,96.7,100],[30,45,20,50,15,80,60,70,40,55,80],[0,10,16.7,26.7,33.3],[50,10,30,55,60]]
      	    	def values3 = [97,12,454,12,5,32,78,4,99,89,98,77,7,77]
      	    	def values4 = [[97,12,454,12,5,32,78,4,99,89,98,77,7,77],[1,6,42,15,78,94,26,45,12,10,21,22,33,33]]
      	    	def values6 = [[-500,30,700,253],[2,-5,3,6]]
      	    	
      	    	
      		def axesLabels = [:]
      		axesLabels.put (0, studentIDs);
      		axesLabels.put (1, []);
      		
      		println axesLabels
          	%>
    
        
     <chart:barChart title='Sample Bar Chart' size="${[500,500]}" colors="FF0000|00ff00|0000ff" type="bvs"
			labels="${labels}" zeroLine="${'0.5'}" axes="x,y" axesLabels="${axesLabels}" 
			fill="${'bg,s,efefef'}" fill="${'bg,s,efefef'}" dataType='simple' data='${values}' />
   
        
        
        
        
    </body>
</html>

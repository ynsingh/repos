

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Per CourseActivity List</title>
    </head>
    <body>
                   <div align ="center">
	 	<h2>LMS: ${session.LMS} &nbsp;&nbsp;&nbsp;&nbsp; Site: ${session.siteName}</h2>
	 </div>
  <div class="wrapper">

        <div >
            <h1>CourseActivity List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table width="100%" align="center" >
                    <thead>
                        <tr>
                        
                   	        <th>SlNo</th>
                                <th>UserID</th>
                   	         <th>User Name</th>
                   	        <th>Event</th>
                              <th>Event Count</th>
                        
                        </tr>
                    </thead>

                    <g:each in="${studentResourceList}" status="i" var="studentResourceInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                        
                            <td><%= studentResourceInstance.userId  %></td>
                            <td><%= studentResourceInstance.userName %></td>
                        
                            <td><%= studentResourceInstance.eventId  %></td>
                        
                            <td><%= studentResourceInstance.eventCount %></td>
                        
                        </tr>
                    </g:each>

                </table>
            </div>
            
        </div>
   
   
   
   
   
   





   <%
		//println "Size = " + courseActivityInstanceList.size()
		
		def CourseID = []
		
		courseActivityInstanceList.each {
		
			CourseID.add (it.CourseID)
		}
		
		def chartValue=[]
		def TotView = []
		def range = []
		def maxValue=0
		
		courseActivityInstanceList.each {
		if(maxValue<it.TotView)
		maxValue=it.TotView
		
           TotView.add (it.TotView)
		}
		
		
		
		for(int i=1000;i<=maxValue;i=i+1000)
		{
		range.add(i)
		}
		
		
		
		println axesLabels
		println 
		
	    	
	    	
		def axesLabels = [:]
		axesLabels.put (0, CourseID);
		axesLabels.put (1, range);
	
		
		
		
    	%>
    
    
      


      <!--<chart:barChart title='Course Total' size="${[500,500]}" colors="FF0000,00ff00" type="bhg"
			labels="${labels}" zeroLine="${'0'}" axes="x,y" axesLabels="${axesLabels}"  
			fill="${'bg,s,efefef'}" fill="${'bg,s,efefef'}" dataType='simple' data='${TotView}'  />-->
			
		<table width="100%"  class="tablewrapper" ><tr><td align="center">
       <chart:barChart title='Student Activity' size="${[425,500]}" colors="9f1818,4fa34f" type="bvg" 
       
			labels="${labels}" zeroLine="${'1'}" axes="x,y" axesLabels="${axesLabelsRes}"  legend="${['Assignment Submitted','Content Read']}"
			fill="${'bg,s,efefef'}" fill="${'bg,s,efefef'}" dataType='simple' data='${chartValueRes}'  />
   </td>
   <td align="center">
   <gchart:radar title='Student Activity' size="${[425,500]}" colors="9f1818,4fa34f" 
			axes="x,y" axesLabels="${axesLabelsRes}" fill="${'bg,s,efefef'}" dataType='simple' data='${chartValueRes}'/>
   </td></tr></table>

     <%
     
   
     
     %>
	
   </div>  
	


    
        
    </body>
</html>

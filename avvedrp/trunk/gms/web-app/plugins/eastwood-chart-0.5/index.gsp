<html>
    <head>
        <title>Google Chart API plugin</title>
      <meta name="layout" content="main" />
    </head>
    <body>
    <%
      def labels = ['First','Second','Third']
      def colors = ['FF0000','00ff00','0000ff']
      def values = [35,45,10]
      def values2 = [[35,45,10],[3,987,2]]
      def values5 = [[0,16.7,23.3,33.3,60,76.7,83.3,86.7,93.3,96.7,100],[30,45,20,50,15,80,60,70,40,55,80],[0,10,16.7,26.7,33.3],[50,10,30,55,60]]
      def values3 = [97,12,454,12,5,32,78,4,99,89,98,77,7,77]
      def values4 = [[97,12,454,12,5,32,78,4,99,89,98,77,7,77],[1,6,42,15,78,94,26,45,12,10,21,22,33,33]]
      def values6 = [[-500,30,700,253],[2,-5,3,6]]
      def values8 = [[35],[20],[15]]
    %>
   <h2>Simple Data Examples www</h2>
   <gchart:lineChart title='Sample Line Chart' titleAttrs="${['440000',30]}" colors="${colors}" 
      axes="x,y" gridLines="10,10,1,0" type='xy' lineStyles="${[[3,6,3],[6,3,6],[6,6,7]]}" legend="${labels}" axesLabels="${[0:['Jan','Feb','Mar'],1:[0,10,30,50]]}" fill="${'bg,s,efefef'}" dataType='text' data='${values5}' />
   <chart:lineChart title='Sample Line Chart' titleAttrs="${['440000',30]}" colors="${colors}" 
         axes="x,y" gridLines="10,10,1,0" type='xy' lineStyles="${[[3,6,3],[6,3,6],[6,6,7]]}" legend="${labels}" axesLabels="${[0:['Jan','Feb','Mar'],1:[0,10,30,50]]}" fill="${'bg,s,efefef'}" dataType='text' data='${values5}' />
   
   <br>
   
   <gchart:barChart title='Sample Bar Chart' size="${[200,200]}" colors="FF0000|00ff00|0000ff" type="bvs"
      labels="${labels}" zeroLine="${'0.5'}" axes="x,y" axesLabels="${[0:['Jan','Feb','Mar'],1:[0,10,30,50]]}" fill="${'bg,s,efefef'}" dataType='simple' data='${values}' />
   <chart:barChart title='Sample Bar Chart' size="${[200,200]}" colors="FF0000|00ff00|0000ff" type="bvs"
      labels="${labels}" zeroLine="${'0.5'}" axes="x,y" axesLabels="${[0:['Jan','Feb','Mar'],1:[0,10,30,50]]}" fill="${'bg,s,efefef'}" dataType='simple' data='${values}' />
   
   <chart:barChart title='Sample Bar Chart' size="${[200,200]}" colors="${['78AFA8','D5CD27','A2B180','C9512F','7A6656','D09B2C','324548','B8BFC6','63746D','715991','2790A6','D2232A']}" type="bvg"
      labels="${labels}" zeroLine="${'0.5'}" axes="x,y" axesLabels="${[0:['Jan','Feb','Mar'],1:[0,10,30,50]]}" fill="${'bg,s,efefef'}" dataType='text' data='${values8}' />
   
    
    <br>
   <gchart:pieChart title='Sample Pie Chart' colors="${colors}" 
      labels="${labels}" fill="${'bg,s,efefef'}" dataType='simple' data='${values}' />
   <chart:pieChart title='Sample Pie Chart' colors="${colors}" 
      labels="${labels}" fill="${'bg,s,efefef'}" dataType='simple' data='${values}' />
   <br>Works to here <br/>
   <gchart:vennDiagram title='Sample Venn Diagram' colors="${colors}" 
      labels="${labels}" fill="${'bg,s,efefef'}" dataType='simple' data='${values}' />
    
   <br>
   <gchart:map mapArea="africa" colors="${['ffffff','edf0d4','13390a']}" data="${[4,23,56]}" countries="${['MG','KE','TN']}" />
   <br>
   <gchart:qr labels='Hello World!' size='${[200,200]}' />

   <br>
   <h2>Text Data Examples</h2>
   <gchart:lineChart title='Sample Line Chart' colors="${colors}" 
      axes="x,y" type='lc' shapeRangeFill="${[['c','FF0000',0,1.0,20.0],['a','990066',0,3.0,9.0],['R','220066',0,0.0,0.5]]}" axesLabels="${[0:['Jan','Feb','Mar','Apr','May','Jun']]}" fill="${'bg,s,efefef'}" dataType='text' data='${values3}' />
   <chart:lineChart title='Sample Line Chart' colors="${colors}" 
      axes="x,y" type='lc' shapeRangeFill="${[['c','FF0000',0,1.0,20.0],['a','990066',0,3.0,9.0],['R','220066',0,0.0,0.5]]}" axesLabels="${[0:['Jan','Feb','Mar','Apr','May','Jun']]}" fill="${'bg,s,efefef'}" dataType='text' data='${values3}' />  
      
   <br>
   <gchart:lineChart title='Sample SparkLine Chart' colors="${colors}" 
      type='ls'  fill="${'bg,s,efefef'}" dataType='text' data='${values3}' />
   <chart:lineChart title='Sample SparkLine Chart' colors="${colors}" 
      type='ls'  fill="${'bg,s,efefef'}" dataType='text' data='${values3}' />   
   <br>
   <gchart:barChart title='Sample Bar Chart' size="${[400,200]}" colors="${colors}" type="bvs"
      labels="${labels}" axes="x,y"  axesLabels="${[0:['Jan','Feb','Mar','Apr','May','Jun','Jul']]}" fill="${'bg,s,efefef'}" dataType='text' data='${values3}' />
   <chart:barChart title='Sample Bar Chart' size="${[400,200]}" colors="${colors}" type="bvs"
      labels="${labels}" axes="x,y"  axesLabels="${[0:['Jan','Feb','Mar','Apr','May','Jun','Jul']]}" fill="${'bg,s,efefef'}" dataType='text' data='${values3}' />   
   <br>
   <gchart:pieChart title='Sample Pie Chart' colors="${colors}" 
      labels="${labels}" fill="${'bg,s,efefef'}" dataType='text' data='${values3}' />
   <chart:pieChart title='Sample Pie Chart' colors="${colors}" 
      labels="${labels}" fill="${'bg,s,efefef'}" dataType='text' data='${values3}' />
   <br>
   <gchart:vennDiagram title='Sample Venn Diagram' colors="${colors}" 
      labels="${labels}" fill="${'bg,s,efefef'}" dataType='text' data='${values3}' />
   <br>
   <gchart:scatterPlot title='Sample ScatterPlot' colors="${colors}"
      labels="${labels}" fill="${'bg,s,efefef'}" dataType='text' data='${values4}' />
   
   <chart:scatterPlot title='Sample ScatterPlot' colors="${colors}"
      labels="${labels}" fill="${'bg,s,efefef'}" dataType='text' data='${values4}' />  
      
   <br>
    <gchart:scatterPlot title='Negative ScatterPlot' colors="${colors}" 
        labels="${labels}" fill="${'bg,s,efefef'}" dataType='text' data='${values6}' 
        scale="${[-500,1000,-5,6]}" />
    <chart:scatterPlot title='Negative ScatterPlot' colors="${colors}" 
            labels="${labels}" fill="${'bg,s,efefef'}" dataType='text' data='${values6}' 
        scale="${[-500,1000,-5,6]}" />
   <br>
    <gchart:gmeter title='Sample Google-o-meter' labels='${["Hello"]}' dataType='text' data='${[70]}' />
    
    <br>
    <gchart:radar title='Sample Radar Chart' colors="${colors}" 
      axes="x,y" axesLabels="${[0:['Jan','Feb','Mar','Apr','May','Jun']]}" fill="${'bg,s,efefef'}" dataType='text' data='${values3}' />
   <h2>Extended Data Examples</h2>
   <gchart:lineChart title='Sample Line Chart' colors="${colors}" 
      axes="x,y" type='lc' axesLabels="${[0:['Jan','Feb','Mar','Apr','May','Jun']]}" fill="${'bg,s,efefef'}" dataType='extended' data='${values3}' />

   <gchart:barChart title='Sample Bar Chart' size="${[400,200]}" colors="${colors}" type="bvs"
      labels="${labels}" axes="x,y" axesLabels="${[0:['Jan','Feb','Mar','Apr','May','Jun','Jul']]}" fill="${'bg,s,efefef'}" dataType='extended' data='${values3}' />
   <gchart:pieChart title='Sample Pie Chart' colors="${colors}" 
      labels="${labels}" fill="${'bg,s,efefef'}" dataType='extended' data='${values3}' />

   <gchart:vennDiagram title='Sample Venn Diagram' colors="${colors}" 
      labels="${labels}" fill="${'bg,s,efefef'}" dataType='extended' data='${values3}' />
    </body>
</html>
